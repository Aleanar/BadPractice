package fr.isima

import org.springframework.dao.DataIntegrityViolationException

class PostController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def userService
    def postService

    def index() {
        log.info "[POST-index] redirect to home"
        redirect(controller: "home", action: "index", params: params)
    }

    def create() {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        log.info "[POST-create] is called"
        [postInstance: new Post(params)]
    }

    def save() {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        log.info "[POST-save] is called"

        def postInstance = new Post(params)
        postInstance.author = session[userService.USER_SESSION_OBJECT_NAME];
        if (!postInstance.save(flush: true)) {
            log.error "[POST-save] could not save post"
            render(view: "create", model: [postInstance: postInstance])
            return
        }

        log.info "[POST-save] post created, redirect to post view"
        flash.message = message(code: 'default.created.message', args: [message(code: 'post.entityName.label', default: 'Post'), postInstance.id])
        redirect(action: "show", controller: "thread", id: (postInstance.thread ? postInstance.thread.id : postInstance.post.thread.id))
    }

    def show(Long id) {
        log.info "[POST-show] is called"

        def postInstance = postService.getPostById(id)
        if (!postInstance) {
            log.warn "[POST-show] post not found"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.entityName.label', default: 'Post'), id])
            redirect(action: "index")
            return
        }

        [postInstance: postInstance]
    }

    def edit(Long id) {
        log.info "[POST-edit] is called for post ${id}"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        def postInstance = postService.getPostById(id)
        if (!postInstance) {
            log.warn "[POST-edit] post not found"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.entityName.label', default: 'Post'), id])
            redirect(action: "index")
            return
        }

        def userConnected = session[userService.USER_SESSION_OBJECT_NAME]
        def isAdmin = (userConnected.rank == Rank.Administrator)

        if(userConnected.id != postInstance.author.id && !isAdmin)
            redirect(controller: "home")

        [postInstance: postInstance, threadInstance: postInstance.thread]
    }

    def update(Long id, Long version) {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        def postInstance = postService.getPostById(id)
        log.info "[POST-update] is called for post ${id}"
        if (!postInstance) {
            log.warn "[POST-update] post ${id} not found"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.entityName.label', default: 'Post'), id])
            redirect(action: "index")
            return
        }

        def userConnected = session[userService.USER_SESSION_OBJECT_NAME]
        def isAdmin = (userConnected.rank == Rank.Administrator)

        if(userConnected.id != postInstance.author.id && !isAdmin)
            redirect(controller: "home")

        if (version != null) {
            if (postInstance.version > version) {
                postInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'post.entityName.label', default: 'Post')] as Object[],
                        "Another user has updated this Post while you were editing")
                render(view: "edit", model: [postInstance: postInstance])
                return
            }
        }

        postInstance.content = params.get("content")

        if (!postService.update(postInstance)) {
            log.error "[POST-update] could not update post " + postInstance.id
            render(view: "edit", model: [postInstance: postInstance])
            return
        }

        log.info "[POST-update] post ${postInstance} updated"

        flash.message = message(code: 'default.updated.message', args: [message(code: 'post.entityName.label', default: 'Post'), postInstance.id])
        redirect(action: "show", controller: "thread", id: (postInstance.thread ? postInstance.thread.id : postInstance.post.thread.id))
    }

    def delete(Long id) {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(controller: "home")

        def postInstance = postService.getPostById(id)
        if (!postInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.entityName.label', default: 'Post'), id])
            redirect(action: "index")
            return
        }

        try {
            postInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'post.entityName.label', default: 'Post'), id])
            redirect(action: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'post.entityName.label', default: 'Post'), id])
            redirect(action: "show", id: id)
        }
    }
}
