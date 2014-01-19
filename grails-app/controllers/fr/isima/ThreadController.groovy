package fr.isima

import org.springframework.dao.DataIntegrityViolationException

class ThreadController {

    def userService
    def threadService
    def tagService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [threadInstanceList: Thread.list(params), threadInstanceTotal: Thread.count()]
    }

    def create() {
        /// Pour les tests
        if(tagService.tagList.size() == 0)
        {
            def tag = new Tag();
            tag.name = "tagTest1"
            tagService.addTag(tag)
            tag = new Tag();
            tag.name = "tagTest2"
            tagService.addTag(tag)
            tag = new Tag();
            tag.name = "MyTagTest"
            tagService.addTag(tag)
        }
        if(userService.allUsers.size() == 0)
        {
            def author = new User();
            author.mail = "marquesthom@gmail.com"
            author.displayName = "Thomas MARQUES"
            author.password = "Password"
            author.realName = ""
            author.website = ""
            author.location = ""
            author.birthday = new Date(System.currentTimeMillis())
            author.aboutMe = ""
            author.pathToAvatar = ""
            author.save()
        }
        ///
        def threadInstance = new Thread(params["thread"])
        if(!threadInstance.tags)
            threadInstance.tags = new HashSet<Tag>()
        [threadInstance: threadInstance, tagsList: tagService.getAllTagsOrderByUse()]
    }

    def save() {
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

        /// Récupérer le user courrant.
        postInstance.author = userService.allUsers.first();
        ///

        if (threadService.newThread(threadInstance))
        {
            render(view: "create", model: [threadInstance: threadInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'thread.label', default: 'Thread'), threadInstance.id])
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
        def threadInstance = Thread.get(id)
        if (!threadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'thread.label', default: 'Thread'), id])
            redirect(action: "list")
            return
        }

        [threadInstance: threadInstance]
    }

    def update(Long id, Long version) {
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
