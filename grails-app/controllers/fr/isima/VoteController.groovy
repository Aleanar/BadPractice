package fr.isima

import org.springframework.dao.DataIntegrityViolationException

import javax.swing.text.html.HTML

class VoteController {

    def voteService
    def userService

    def addVote() {
        if(!session[userService.USER_SESSION_OBJECT_NAME]) redirect(controller: "home")

        def up = params.getAt("up") == '1'
        def userId = session[userService.USER_SESSION_OBJECT_NAME].id
        def postId = Long.parseLong(params.getAt("postId"))

        voteService.addVote(userId, postId, up)
        render voteService.getVoteByPostId(postId).toString()
    }

}
