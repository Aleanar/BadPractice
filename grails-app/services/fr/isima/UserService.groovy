package fr.isima

class UserService {

    def getAllUsers() {
        User.findAll()
    }

    def getUserById(long id) {
        User.get(id)
    }
}
