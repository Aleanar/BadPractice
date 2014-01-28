package fr.isima

class VoteService {

    def postService
    def userService

    def getVoteByPostId(long postId) {
        def listVote = Vote.findAllByPost(postService.getPostById(postId))
        return listVote.size() - 2 * listVote.count { !it.up }
    }

    def addVote(long userId, long postId, boolean up) {
        def user = userService.getUserById(userId)
        def post = postService.getPostById(postId)
        def listVote = Vote.findAllByPostAndUser(post, user)
        if(listVote.isEmpty())
        {
            def vote = new Vote()
            vote.user = user
            vote.post = post
            vote.up = up
            vote.save()
        }
        else
        {
            listVote.first().up = up
            listVote.first().save(flush: true)
        }
    }
}