package fr.isima

class Tag {

    String name

    static hasMany = [threads:Thread]

    static constraints = {
    }
}
