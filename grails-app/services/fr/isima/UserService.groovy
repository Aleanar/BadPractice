package fr.isima

class UserService {

    final USER_SESSION_OBJECT_NAME = 'user'

    def getAllUsers() {
        User.findAll()
    }

    def getUserById(long id) {
        User.get(id)
    }

    def getUserByMail(String mail) {
        User.findByMail(mail)
    }

    def createUserWithGooglePlusInfo(oauthresources) {

        def user = new User(
                mail:oauthresources.emails[0].value,
                displayName: oauthresources.displayName,
                realName: oauthresources.name.givenName + oauthresources.name.familyName,
                website: oauthresources.url,
                location: oauthresources.placesLived[0].value,
                birthday: "",
                aboutMe: "",
                pathToAvatar: oauthresources.image.url,
                rank: Rank.User,
                reputation: 0
        )

        user.save()

    }

    def changeUserReputation(User user, newReputation) {
        user.setReputation( user.getReputation() + newReputation )
    }

    def updateUserRate(User user, RateElement element) {

        changeUserReputation( user, element.getValue() )

    }

}
