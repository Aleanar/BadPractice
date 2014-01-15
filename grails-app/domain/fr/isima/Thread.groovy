package fr.isima

class Thread {

    String title
    int viewCount
    Post firstPost

    static hasMany = [tags:Tag, posts:Post]
    static belongsTo = Tag

    static constraints = {
        title blank:false
    }
}
