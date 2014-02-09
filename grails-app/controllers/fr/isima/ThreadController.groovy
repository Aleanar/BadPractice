package fr.isima

import org.springframework.dao.DataIntegrityViolationException

class ThreadController {

    def userService
    def threadService
    def tagService
    def postService

    static allowedMethods = [save: "POST",savePost: "POST", update: "POST", delete: "GET"]

    def index() {
        log.info "[THREAD-index] called, redirect to create"
        redirect(action: "create", params: params)
    }

    def create() {
        log.info "[THREAD-create] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(uri: "/oauth/google/authenticate");return}

        def threadInstance = new Thread(params["thread"])
        if(!threadInstance.tags)
            threadInstance.tags = new HashSet<Tag>()
        [threadInstance: threadInstance, tagsList: tagService.getAllTagsOrderByUse()]
    }

    def save() {
        log.info "[THREAD-save] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        def postInstance = new Post(params["post"])
        def threadInstance = new Thread(params["thread"])
        threadInstance.firstPost = postInstance
        /*if(!threadInstance.tags)
            threadInstance.tags = new HashSet<Tag>()*/
        postInstance.thread = threadInstance
        def currentDate = new Date(System.currentTimeMillis())
        postInstance.creationDate = currentDate
        postInstance.lastEditionDate = currentDate
        postInstance.author = session[userService.USER_SESSION_OBJECT_NAME];

        /// Les tags sont au format idNum1,idNum2,idNum3...
        def tags = params.get("tag-name-auto")
        if (tags) {
            def list = new ArrayList<Tag>()
            params.get("tag-name-auto")?.split(",")?.each {
                def tag = tagService.getTagById(Long.parseLong(it))
                list.add(tag)
            }
            list.each {
                threadInstance.addToTags(it)
            }
        }
        else
            log.warn "[THREAD-save] tag list is empty"

        if (!threadService.newThread(threadInstance))
        {
            log.error "[THREAD-save] called"
            render(view: "create", model: [threadInstance: threadInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'thread.entityName.label', default: 'Thread'), threadInstance.id])

        redirect(action: "show", id: threadInstance.id)
    }

    def savePost() {
        log.info "[THREAD-savePost] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        def threadInstance = threadService.getThreadById(Long.parseLong(params.get("thread.id")))
        def postInstance = new Post()
        postInstance.content = params.get("content")
        postInstance.author = session[userService.USER_SESSION_OBJECT_NAME]
        def currentDate = new Date()
        postInstance.creationDate = currentDate
        postInstance.lastEditionDate = currentDate
        postInstance.post = null
        postInstance.thread = threadInstance

        if (!postService.newPost(postInstance))
        {
            log.error "[THREAD-savePost] post could not be saved"
            render(view: "show", id: threadInstance.id, model: [threadInstance: threadInstance, postInstance: postInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'post.entityName.label', default: 'Post'), postInstance.id])
        redirect(action: "show", id: threadInstance.id)
    }

    def saveComment() {
        log.info "[THREAD-saveComment] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        def post = postService.getPostById(Long.parseLong(params.get("post.id")))
        def threadInstance = threadService.getThreadById(Long.parseLong(params.get("thread.id")))

        if (post)
        {

            def commentInstance = new Post()
            commentInstance.content = params.get("content")
            commentInstance.author = session[userService.USER_SESSION_OBJECT_NAME]
            def currentDate = new Date()
            commentInstance.creationDate = currentDate
            commentInstance.lastEditionDate = currentDate
            commentInstance.post = post

            if (!postService.newPost(commentInstance))
            {
                log.error "[THREAD-saveComment] comment could not be saved"
                render(view: "show", id: threadInstance.id, model: [threadInstance: threadInstance, commentInstance: commentInstance])
                return
            }

        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'post.entityName.label', default: 'Post'), post.id])
        redirect(action: "show", id: threadInstance.id)

    }

    def show(Long id) {
        log.info "[THREAD-show] called"
        def threadInstance = threadService.getThreadById(id)
        if (!threadInstance) {
            log.warn "[THREAD-show] thread does not exist"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thread.entityName.label', default: 'Thread'), id])
            redirect(action: "index")
            return
        }

        def isEditable = false
        def isAdmin = false
        def userConnected = session[userService.USER_SESSION_OBJECT_NAME]

        if(userConnected) {
            isAdmin = (userConnected.rank == Rank.Administrator)
            isEditable = (isAdmin | (userConnected.id == threadInstance.firstPost.author.id))
        }

        threadService.incrementView(id);
        [threadInstance: threadInstance, isEditable: isEditable, isAdmin: isAdmin, userConnected: userConnected]
    }

    def edit(Long id) {
        log.info "[THREAD-edit] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        def threadInstance = threadService.getThreadById(id)

        if (!threadInstance) {
            log.warn "[THREAD-edit] thread does not exist"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thread.entityName.label', default: 'Thread'), id])
            redirect(action: "index")
            return
        }

        def userConnected = session[userService.USER_SESSION_OBJECT_NAME]
        def isAdmin = (userConnected.rank == Rank.Administrator)

        if(userConnected.id != threadInstance.firstPost.author.id && !isAdmin) {
            log.warn "[THREAD-edit] user could not edit the thread"
            redirect(controller: "home")
            return
        }

        [threadInstance: threadInstance, tagsEnregistred: threadInstance.tags, tagsList: tagService.getAllTagsOrderByUse(), isAdmin: isAdmin]
    }

    def update(Long id, Long version) {
        log.info "[THREAD-update] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        def threadInstance = threadService.getThreadById(id)
        if (!threadInstance) {
            log.warn "[THREAD-update] thread does not exist"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thread.entityName.label', default: 'Thread'), id])
            redirect(action: "index")
            return
        }

        def userConnected = session[userService.USER_SESSION_OBJECT_NAME]
        def isAdmin = (userConnected.rank == Rank.Administrator)

        if(userConnected.id != threadInstance.firstPost.author.id && !isAdmin) {
            log.warn "[THREAD-update] user is not allowed to update thread"
            redirect(controller: "home")
        }

        if (version != null) {
            if (threadInstance.version > version) {
                threadInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'thread.entityName.label', default: 'Thread')] as Object[],
                        "Another user has updated this Thread while you were editing")
                render(view: "edit", model: [threadInstance: threadInstance])
                return
            }
        }

        /// Les tags sont au format idNum1,idNum2,idNum3...
        params.get("tag-name-auto").split(",").each {
            def tag = tagService.getTagById(Long.parseLong(it)).get(0)
            /// On ajoute uniquement les nouveaux tags
            if(!threadInstance.tags.contains(tag))
                threadInstance.addToTags(tag)
        }

        threadInstance.title = params.get("thread.title")
        threadInstance.firstPost.content = params.get("post.content")

        if (!threadService.updateThread(threadInstance)) {
            log.error "[THREAD-update] thread could not be updated"
            render(view: "edit", model: [threadInstance: threadInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'thread.entityName.label', default: 'Thread'), threadInstance.id])
        redirect(action: "show", id: threadInstance.id)
    }

    def delete(Long id) {
        log.info "[THREAD-delete] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        def threadInstance = threadService.getThreadById(id)
        if (!threadInstance) {
            log.warn "[THREAD-delete] thread does not exist"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thread.entityName.label', default: 'Thread'), id])
            redirect(action: "index")
            return
        }

        try {
            threadInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'thread.entityName.label', default: 'Thread'), id])
            redirect(action: "index")
        }
        catch (DataIntegrityViolationException e) {
            log.error "[THREAD-delete] error when deleting thread"
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'thread.entityName.label', default: 'Thread'), id])
            redirect(action: "show", id: id)
        }
    }
}
