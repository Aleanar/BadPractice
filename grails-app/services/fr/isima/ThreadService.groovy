package fr.isima

class ThreadService {

    def userService

    /**
     * Save a new thread
     * @param thread the thread to save
     * @return true if succeeded, false otherwise
     */
    def newThread(Thread thread) {
        log.info "[ThreadService-newThread] called"
        boolean status = thread.validate()
        status = thread.firstPost.validate() && status

        if (status)
        {
            thread.save(failOnError: true)
            thread.firstPost.save()
            userService.updateUserRate(thread.firstPost.author, RateElement.ThreadCreated)
        }
        else
            log.warn "[ThreadService-newThread] thread is not a valid one"

        status
    }

    def threads() {
        log.info "[ThreadService-threads] called"
        Thread.findAll()
    }

    /**
     * Use to have the detail of a thread
     * @param id thread identifier
     * @return the thread
     */
    def getThreadById(long id) {
        log.info "[ThreadService-getThreadById] called for thread ${id}"
        Thread.get(id)
    }

    /**
     * Save the post
     * @param post the post to save
     * @return true if succeeded, false otherwise
     */
    def newPost(Post post) {
        log.info "[ThreadService-newPost] called"
        post.save()
    }

    /**
     * Edit the given post
     * @param post the edited post
     * @return true if succeeded, false otherwise
     */
    def editPost(Post post) {
        log.info "[ThreadService-editPost] called for post ${post.id}"
        post.merge()
    }

    /**
     * Vote for the given post
     * @param vote the vote to save
     * @return true if succeeded, false otherwise
     */
    def voteForPost(Vote vote) {
        log.info "[ThreadService-voteForPost] called"
        if (vote.post.thread && !vote.post.post)
            vote.save()
        else
            log.warn "[ThreadService-voteForPost] could not vote primary post"

    }

    def incrementView(Long id) {
        log.info "[ThreadService-incrementView] called"
        ++Thread.get(id).viewCount;
    }

    def updateThread(Thread thread) {
        log.info "[ThreadService-updateThread] called for thread ${thread.id}"
        thread.save(failOnError: true)
    }
}
