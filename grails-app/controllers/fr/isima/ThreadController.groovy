package fr.isima

import org.springframework.dao.DataIntegrityViolationException

class ThreadController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [threadInstanceList: Thread.list(params), threadInstanceTotal: Thread.count()]
    }

    def create() {
        [threadInstance: new Thread(params)]
    }

    def save() {
        def threadInstance = new Thread(params)
        if (!threadInstance.save(flush: true)) {
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
