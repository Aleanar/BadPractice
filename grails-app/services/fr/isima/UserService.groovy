package fr.isima

class UserService {

    final USER_SESSION_OBJECT_NAME = 'user'

    def getAllUsers() {
        log.info "[UserService-getAllUsers] called"
        User.findAll()
    }

    def getUserById(long id) {
        log.info "[UserService-getUserById] called for user ${id}"
        User.get(id)
    }

    def getUserByMail(String mail) {
        log.info "[UserService-getUserByMail] called"
        User.findByMail(mail)
    }

    def createUserWithGooglePlusInfo(oauthresources) {

        log.info "[UserService-createUserWithGooglePlusInfo] called"

        def user = new User(
                mail:oauthresources.emails[0].value,
                displayName: oauthresources.displayName,
                realName: oauthresources.name.givenName + oauthresources.name.familyName,
                website: oauthresources.url,
                location: (oauthresources.placesLived)?.getAt(0)?.value?:'',
                birthday: "",
                aboutMe: "",
                pathToAvatar: oauthresources.image.url,
                rank: Rank.User,
                reputation: 0
        )

        user.save()

    }

    def changeUserReputation(User user, newReputation) {
        log.info "[UserService-changeUserReputation] called for user ${user.id}"
        user.setReputation( user.getReputation() + newReputation )
    }

    def updateUserRate(User user, RateElement element) {
        log.info "[UserService-updateUserRate] called for user ${user.id}"
        changeUserReputation( user, element.getValue() )

    }

}
