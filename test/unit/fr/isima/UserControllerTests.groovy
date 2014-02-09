package fr.isima



import org.junit.*
import grails.test.mixin.*

@TestFor(UserController)
@Mock([User, UserService])
class UserControllerTests {

    def populateValidParams(params) {
        assert params != null

        params["mail"] = "mottetalexandre@gmail.com"
        params["displayName"] = "Alexandre MOTTET"
        params["password"] = "PasswOrd!"
        params["realName"] = ""
        params["website"] = ""
        params["location"] = ""
        params["birthday"] = new Date(System.currentTimeMillis())
        params["aboutMe"] = ""
        params["pathToAvatar"] = ""
        params["rank"] = Rank.Administrator
    }

    void testIndex() {
        controller.index()
        assert "/user/list" == response.redirectedUrl
    }

    void testList() {
        def model = controller.list()

        assert model.userInstanceList.size() == 0
        assert model.userInstanceTotal == 0
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null

        params.id = user.id

        def model = controller.show()

        assert model.userInstance == user
    }

    void testEdit() {
        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        def model = controller.edit()

        assert "/oauth/google/authenticate" == response.redirectedUrl
        response.reset()

        def user = new User()
        user.id = 1
        user.rank = Rank.Administrator
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        controller.edit()

        assert response.redirectedUrl == '/user/list'

        populateValidParams(params)
        user = new User(params)

        assert user.save() != null

        params.id = user.id

        model = controller.edit()

        assert model.userInstance == user
    }

    void testUpdate() {
        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        def model = controller.update()

        assert "/home" == response.redirectedUrl
        response.reset()

        def user = new User()
        user.id = 1
        user.rank = Rank.Administrator
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        response.reset()

        populateValidParams(params)
        user = new User(params)

        assert user.save() != null

        // test invalid parameters in update
        params.id = user.id
        params.mail = "fakeMail"

        controller.update()

        assert view == "/user/edit"

        user.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/user/show/$user.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        user.clearErrors()

        populateValidParams(params)
        params.id = user.id
        params.version = -1
        controller.update()

        assert view == "/user/edit"
        assert flash.message != null
    }

    void testDelete() {
        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        controller.delete()

        assert "/home" == response.redirectedUrl
        response.reset()

        def user = new User()
        user.id = 1
        user.rank = Rank.Administrator
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        response.reset()

        populateValidParams(params)
        user = new User(params)

        assert user.save() != null
        assert User.count() == 1

        params.id = user.id

        controller.delete()

        assert User.count() == 0
        assert User.get(user.id) == null
        assert response.redirectedUrl == '/user/list'
    }
}
