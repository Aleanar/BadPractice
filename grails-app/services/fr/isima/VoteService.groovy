package fr.isima

class VoteService {

    def postService
    def userService

    def getVoteByPostId(long postId) {
        log.info "[VoteService-getVoteByPostId] called for post ${postId}"
        def listVote = Vote.findAllByPost(postService.getPostById(postId))
        return listVote.size() - 2 * listVote.count { !it.up }
    }

    def addVote(long userId, long postId, boolean up) {

        log.info "[VoteService-addVote] called for post ${postId}"

        def user = userService.getUserById(userId)
        def post = postService.getPostById(postId)
        def listVote = Vote.findAllByPostAndUser(post, user)

        if(listVote.isEmpty())
        {
            def vote = new Vote()
            vote.user = user
            vote.post = post
            vote.up = up
            vote.date = new Date()
            vote.save()
        }
        else
        {

            // Reset the specified reputation before editing it
            def rate = (up) ? RateElement.AnswerIncremented : RateElement.AnswerDecremented
            userService.updateUserRate(post.author, rate)

            listVote.first().up = up
            listVote.first().date = new Date()
            listVote.first().save(flush: true)

        }

        // Increment or decrement user reputation
        def rate = (up) ? RateElement.AnswerIncremented : RateElement.AnswerDecremented
        userService.updateUserRate(post.author, rate)

    }
}