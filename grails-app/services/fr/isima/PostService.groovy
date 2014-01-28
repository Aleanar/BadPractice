package fr.isima

class PostService {

    def getPostById(long id) {
        Post.get(id)
    }

    def newPost(Post post) {
        post.save()
    }

}
