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
        mail email:true, blank: false, unique: true
        password blank:false, password:true, size:6..30
        displayName size: 0..30, blank: false, unique: true
    }
}
