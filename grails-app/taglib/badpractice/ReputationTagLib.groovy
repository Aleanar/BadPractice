package badpractice

import fr.isima.UserService

class ReputationTagLib {

    def userService

    def badges = {

        def reputation = session[userService.USER_SESSION_OBJECT_NAME].getReputation()

        int rookieBadgesNumber, amateurBadgesNumber, ultimateBadgesNumber
        if (reputation < 100) {
            rookieBadgesNumber = reputation/10
        } else if (reputation < 500) (
            amateurBadgesNumber = reputation/50
        ) else if (reputation < 2000) {
            ultimateBadgesNumber = reputation/500
        }

        out << render(template:"/taglib/badges", model:[reputation:reputation,
                rookieBadgesNumber:rookieBadgesNumber,
                amateurBadgesNumber:amateurBadgesNumber,
                ultimateBadgesNumber:ultimateBadgesNumber])

    }

}
