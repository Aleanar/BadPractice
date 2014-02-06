package fr.isima

class Post {

    Date creationDate
    String content
    Date lastEditionDate
    Thread thread

    static belongsTo = [author:User]
    static hasMany = [posts:Post, votes:Vote]
    static hasOne = [post:Post]

    def getCountingVote() {
        if(votes == null)
            return 0
        return votes.size() - 2 * votes.count {!it.up}
    }

    static mapping = {
        content type:'text'
    }

    static constraints = {
        thread(nullable:true,validator: {val, obj ->
            return (obj.thread != obj.post)
        })
        content null:false, blank:false, minSize: 1
    }
}
