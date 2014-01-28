package fr.isima

class Post {

    Date creationDate
    String content
    Date lastEditionDate

    static belongsTo = [author:User]
    static hasMany = [posts:Post, votes:Vote]
    static hasOne = [thread:Thread, post:Post]

    def getCountingVote() {
        return votes.size() - 2 * votes.count {!it.up}
    }

    static constraints = {
        thread(validator: {val, obj ->
            return obj.thread || obj.post
        })
        content blank:false, minSize: 1
    }
}
