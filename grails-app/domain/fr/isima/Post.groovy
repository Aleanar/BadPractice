package fr.isima

class Post {

    Date creationDate
    String content
    Date lastEditionDate

    static belongsTo = [thread:Thread, author:User]
    static hasMany = [posts:Post]

    static constraints = {
    }
}
