package fr.isima

class PostService {

    def userService

    def getPostById(long id) {

        log.info "[PostService-getPostById] called for post ${id}"
        Post.get(id)

    }

    def newPost(Post post) {
        log.info "[PostService-newPost] called"

        // Answer added
        if (post.thread) {
            userService.updateUserRate(post.author, RateElement.AnswerPosted)
        }
        // Comment added
        else if (post.post) {
            userService.updateUserRate(post.author, RateElement.CommentPosted)
        }

        post.save()

    }

    def update(Post post) {
        log.info "[PostService-update] called for post ${post.id}"
        post.save()
    }

}
