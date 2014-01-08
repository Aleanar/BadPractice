package fr.isima

class ThreadService {

    /**
     * Save a new thread
     * @param thread the thread to save
     * @return true if succeeded, false otherwise
     */
    def newThread(Thread thread) {
        thread.save()
    }

    /**
     * Use to have the detail of a thread
     * @param id thread identifier
     * @return the thread
     */
    def getThreadById(long id) {
        Thread.get(id)
    }

    /**
     * Save the post
     * @param post the post to save
     * @return true if succeeded, false otherwise
     */
    def newPost(Post post) {
        post.save()
    }

    /**
     * Edit the given post
     * @param post the edited post
     * @return true if succeeded, false otherwise
     */
    def editPost(Post post) {
        post.merge()
    }

    /**
     * Vote for the given post
     * @param vote the vote to save
     * @return true if succeeded, false otherwise
     */
    def voteForPost(Vote vote) {
        if (vote.post.thread && !vote.post.post)
            vote.save()
    }

}