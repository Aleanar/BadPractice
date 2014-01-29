package fr.isima

enum Rank {
    Administrator("user.rank.admin"),
    Moderator("user.rank.moderator"),
    User("user.rank.user")

    final String value

    Rank(String value) { this.value = value }

    String toString() { value }
}

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
    Rank rank

    static hasMany = [postsCreated:Post]

    static mapping = {
        pathToAvatar type:'text'
    }

    static constraints = {
        mail email:true, blank: false, unique: true
        displayName size: 0..30, blank: false, unique: true
        password nullable: true
        birthday nullable: true
    }
}
