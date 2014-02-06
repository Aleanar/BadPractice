package fr.isima

class PostService {

    def userService

    def getPostById(long id) {

        Post.get(id)

    }

    def newPost(Post post) {

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
        post.save()
    }

}
