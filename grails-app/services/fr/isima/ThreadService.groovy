package fr.isima

class ThreadService {

    def userService

    /**
     * Save a new thread
     * @param thread the thread to save
     * @return true if succeeded, false otherwise
     */
    def newThread(Thread thread) {
        boolean status = thread.validate()
        status = thread.firstPost.validate() && status

        if (status)
        {
            thread.save(failOnError: true)
            thread.firstPost.save()
            userService.updateUserRate(thread.firstPost.author, RateElement.ThreadCreated)
        }

        status
    }

    def threads() {
        Thread.findAll()
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

    def incrementView(Long id) {
        ++Thread.get(id).viewCount;
    }

    def updateThread(Thread thread) {
        thread.save(failOnError: true)
    }
}
