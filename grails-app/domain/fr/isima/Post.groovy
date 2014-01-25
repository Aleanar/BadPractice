package fr.isima

class Post {

    Date creationDate
    String content
    Date lastEditionDate

    static belongsTo = [author:User]
    static hasMany = [posts:Post]
    static hasOne = [thread:Thread, post:Post]

    static mapping = {
        content type: 'text'
    }

    static constraints = {
        thread(validator: {val, obj ->
            return obj.thread || obj.post
        })
        content blank:false
    }
}
