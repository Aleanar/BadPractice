package fr.isima

enum Rank {
    Administrator("user.rank.admin"),
    Moderator("user.rank.moderator"),
    User("user.rank.user")

    final String value

    Rank(String value) { this.value = value }

    String toString() { value }
}

enum RateElement {
    ThreadCreated(10),
    AnswerPosted(5),
    CommentPosted(2),
    AnswerDecremented(-10),
    AnswerIncremented(10),
    AnswerAccepted(50)

    final int value

    RateElement(int value) { this.value = value }
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
    int reputation

    static hasMany = [postsCreated:Post]

    static mapping = {
        pathToAvatar type:'text'
    }

    static constraints = {
        mail email:true, blank: false, unique: true
        displayName size: 0..30, blank: false, unique: true
        password nullable: true
        birthday nullable: true
        reputation min:0
    }
}
