package fr.isima

class Tag {

    String name

    static hasMany = [threads:Thread]

    static constraints = {
        name blank: false, unique:true
    }
}
