package fr.isima

import fr.isima.filters.RedirectFilters
import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException
import uk.co.desirableobjects.oauth.scribe.OauthService
import org.scribe.model.Token

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def userService
    def oauthService

    def index() {
        log.info "[USER-index] called, redirect to home"
        redirect(action: "index", controller: "home", params: params)
    }

    def list(Integer max) {
        log.info "[USER-list] called"
        params.max = Math.min(max ?: 10, 100)

        [userInstanceList: userService.getAllUsers(), userInstanceTotal: User.count()]
    }

    def show(Long id) {
        log.info "[USER-show] called"
        def userInstance = userService.getUserById(id)
        if (!userInstance) {
            log.warn "[USER-show] user does not exist"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.entityName.label', default: 'User'), id])
            redirect(action: "index", controller: "home")
            return
        }

        def isAdmin = false
        def isEditable = false
        if(session[userService.USER_SESSION_OBJECT_NAME]) {
            isAdmin = (session[userService.USER_SESSION_OBJECT_NAME].rank == Rank.Administrator)
            isEditable = (isAdmin | (session[userService.USER_SESSION_OBJECT_NAME].id == id))
        }

        [userInstance: userInstance, isEditable: isEditable, isAdmin: isAdmin]
    }

    def edit(Long id) {
        log.info "[USER-edit] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(uri: "/oauth/google/authenticate");return}

        def userConnected = session[userService.USER_SESSION_OBJECT_NAME]
        def isAdmin = false//(userConnected.rank == Rank.Administrator)

        if(userConnected.id != id && !isAdmin) {
            log.warn "[USER-edit] user could not be edited"
            redirect(controller: "home")
            return
        }

        def userInstance = User.get(id)
        if (!userInstance) {
            log.warn "[USER-edit] user not found"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.entityName.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance, isAdmin: isAdmin]
    }

    def update(Long id, Long version) {
        log.info "[USER-update] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        def userConnected = session[userService.USER_SESSION_OBJECT_NAME]
        def isAdmin = (userConnected.rank == Rank.Administrator)

        if(userConnected.id != id && !isAdmin) {
            log.warn "[USER-update] user ${userConnected.id} is not allowed to update user ${id}"
            redirect(controller: "home")
            return
        }

        def userInstance = User.get(id)
        if (!userInstance) {
            log.warn "[USER-update] user ${id} not found"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.entityName.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'user.entityName.label', default: 'User')] as Object[],
                        "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            log.info "[USER-update] user ${id} could not be updated"
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.entityName.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        log.info "[USER-delete] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        if(session[userService.USER_SESSION_OBJECT_NAME].id == id)
            session[userService.USER_SESSION_OBJECT_NAME] = null;

        def userInstance = User.get(id)
        if (!userInstance) {
            log.warn "[USER-delete] user ${id} not found"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.entityName.label', default: 'User'), id])
            redirect(action: "index", controller: "home")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.entityName.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            log.info "[USER-delete] user ${id} could not be deleted"
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.entityName.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }

    def ban(Long id, boolean ban) {

        log.info "[USER-ban] called"
        if(!session[userService.USER_SESSION_OBJECT_NAME]) {redirect(controller: "home");return}

        def userInstance = User.get(id)
        if (!userInstance) {
            log.warn "[USER-ban] user ${id} not found"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.entityName.label', default: 'User'), id])
            redirect(action: "index", controller: "home")
            return
        }

        if (ban) {
            userService.unban(userInstance)
        } else {
            userService.ban(userInstance)
        }

        redirect(action:"show", id:id)

    }

    /**
     * OAuth2 Authentification
     */
    def oauth() {

        // Get OAuth token from session
        def token = session[oauthService.findSessionKeyForAccessToken('google')]

        // Retreive and parse
        def oauthresources = oauthService.getGoogleResource(token, grailsApplication.config.oauth2.userinfo.url)
        oauthresources = JSON.parse(oauthresources.body)

        // User register
        def user = userService.getUserByMail(oauthresources.emails[0].value)
        if (!user) {
            user = userService.createUserWithGooglePlusInfo(oauthresources)
        }

        // Check if user is banned
        if (user.ban) {
            flash.message = message(code: 'user.ban.information.message', default: 'Yout user is ban')
            redirect(action: "index", controller: "home")
            return
        }

        session['user'] = user

        // Last redirection
        redirect(uri: session[RedirectFilters.LAST_URL])

    }

    def logout() {
        session['user'] = null
        redirect(controller: "home")
    }
}
