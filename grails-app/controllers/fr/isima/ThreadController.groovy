package fr.isima

import org.springframework.dao.DataIntegrityViolationException

class ThreadController {

    def userService
    def threadService
    def tagService
    def postService

    static allowedMethods = [save: "POST",savePost: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [threadInstanceList: Thread.list(params), threadInstanceTotal: Thread.count()]
    }

    def create() {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(uri: "/oauth/google/authenticate")

        def threadInstance = new Thread(params["thread"])
        if(!threadInstance.tags)
            threadInstance.tags = new HashSet<Tag>()
        [threadInstance: threadInstance, tagsList: tagService.getAllTagsOrderByUse()]
    }

    def save() {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(uri: "/oauth/google/authenticate")

        def postInstance = new Post(params["post"])
        def threadInstance = new Thread(params["thread"])
        threadInstance.firstPost = postInstance
        if(!threadInstance.tags)
            threadInstance.tags = new HashSet<Tag>()
        postInstance.thread = threadInstance
        def currentDate = new Date(System.currentTimeMillis())
        postInstance.creationDate = currentDate
        postInstance.lastEditionDate = currentDate

        /// Les tags sont au format idNum1,idNum2,idNum3...
        params.get("tag-name-auto").split(",").each {
            def tag = tagService.getTagById(Long.parseLong(it)).get(0)
            threadInstance.tags.add(tag)
        }

        postInstance.author = session[userService.USER_SESSION_OBJECT_NAME];

        if (!threadService.newThread(threadInstance))
        {
            render(view: "create", model: [threadInstance: threadInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'thread.label', default: 'Thread'), threadInstance.id])
        redirect(action: "show", id: threadInstance.id)
    }

    def savePost() {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(uri: "/oauth/google/authenticate")

        def threadInstance = threadService.getThreadById(Long.parseLong(params.get("thread.id")))
        def postInstance = new Post()
        postInstance.content = params.get("content")
        postInstance.author = userService.getUserById(Long.parseLong(params.get("author.id")))
        def currentDate = new Date()
        postInstance.creationDate = currentDate
        postInstance.lastEditionDate = currentDate
        postInstance.post = null
        postInstance.thread = threadInstance

        if (!postService.newPost(postInstance))
        {
            render(view: "show", id: threadInstance.id, model: [threadInstance: threadInstance, postInstance: postInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'post.label', default: 'Post'), postInstance.id])
        redirect(action: "show", id: threadInstance.id)
    }

    def show(Long id) {
        def threadInstance = Thread.get(id)
        if (!threadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thread.label', default: 'Thread'), id])
            redirect(action: "list")
            return
        }

        [threadInstance: threadInstance]
    }

    def edit(Long id) {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(uri: "/oauth/google/authenticate")

        def threadInstance = Thread.get(id)
        if (!threadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thread.label', default: 'Thread'), id])
            redirect(action: "list")
            return
        }

        [threadInstance: threadInstance]
    }

    def update(Long id, Long version) {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(uri: "/oauth/google/authenticate")

        def threadInstance = Thread.get(id)
        if (!threadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thread.label', default: 'Thread'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (threadInstance.version > version) {
                threadInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'thread.label', default: 'Thread')] as Object[],
                        "Another user has updated this Thread while you were editing")
                render(view: "edit", model: [threadInstance: threadInstance])
                return
            }
        }

        threadInstance.properties = params

        if (!threadInstance.save(flush: true)) {
            render(view: "edit", model: [threadInstance: threadInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'thread.label', default: 'Thread'), threadInstance.id])
        redirect(action: "show", id: threadInstance.id)
    }

    def delete(Long id) {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(uri: "/oauth/google/authenticate")

        def threadInstance = Thread.get(id)
        if (!threadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thread.label', default: 'Thread'), id])
            redirect(action: "list")
            return
        }

        try {
            threadInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'thread.label', default: 'Thread'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'thread.label', default: 'Thread'), id])
            redirect(action: "show", id: id)
        }
    }
}
