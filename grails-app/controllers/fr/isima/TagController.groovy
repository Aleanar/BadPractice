package fr.isima

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class TagController {

    def tagService
    def userService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(controller: "home", action: "index", params: params)
    }

    def save() {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(controller: "home")

        def tagInstance = new Tag(params)
        if (!tagService.addTag(tagInstance)) {
            render(view: "create", model: [tagInstance: tagInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tag.entityName.label', default: 'Tag'), tagInstance.id])
        redirect(action: "show", id: tagInstance.id)
    }

    def update(Long id, Long version) {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(controller: "home")

        def tagInstance = Tag.get(id)
        if (!tagInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tag.entityName.label', default: 'Tag'), id])
            redirect(action: "index")
            return
        }

        if (version != null) {
            if (tagInstance.version > version) {
                tagInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'tag.entityName.label', default: 'Tag')] as Object[],
                        "Another user has updated this Tag while you were editing")
                render(view: "edit", model: [tagInstance: tagInstance])
                return
            }
        }

        tagInstance.properties = params

        if (!tagInstance.save(flush: true)) {
            render(view: "edit", model: [tagInstance: tagInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tag.entityName.label', default: 'Tag'), tagInstance.id])
        redirect(action: "show", id: tagInstance.id)
    }

    def delete(Long id) {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(controller: "home")

        def tagInstance = Tag.get(id)
        if (!tagInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tag.entityName.label', default: 'Tag'), id])
            redirect(action: "index")
            return
        }

        try {
            tagInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tag.entityName.label', default: 'Tag'), id])
            redirect(action: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tag.entityName.label', default: 'Tag'), id])
            redirect(action: "show", id: id)
        }
    }

    def getTagsWithName() {
        render tagService.getTagsWithName(params) as JSON
    }

    def insertTag() {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(controller: "home")

        def tagInstance = new Tag(params)

        tagService.addTag(tagInstance)
        render(view: "_resultInsertion", model: [tagInstance: tagInstance, tagsList: tagService.getAllTagsOrderByUse()])
    }
}
