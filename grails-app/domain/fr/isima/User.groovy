package fr.isima

class User {

    String mail
    String password
    String displayName
    String realName
    String website
    String location
    Date birthday
    String aboutMe
    String pathToAvatar

    static hasMany = [postsCreated:Post]

    static constraints = {
    }
}
