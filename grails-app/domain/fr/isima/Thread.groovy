package fr.isima

class Thread {

    String title
    int viewCount

    static hasMany = [tags:Tag, posts:Post]
    static belongsTo = Tag

    static constraints = {
    }
}
