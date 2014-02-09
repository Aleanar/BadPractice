package fr.isima



import org.junit.*
import grails.test.mixin.*

@TestFor(ThreadController)
@Mock([Thread, Tag, User, Post, ThreadService, UserService, PostService, TagService])
class ThreadControllerTests {

    def populateValidParams(params) {
        assert params != null

        if(Tag.count == 0) {
            def tag = new Tag()
            tag.name = "Java"
            tag.save()

            tag = new Tag()
            tag.name = "C++"
            tag.save()
        }

        params["thread.title"] = "My title"
        params["tag-name-auto"] = "1,2"
        params["post.content"] = "My content"
    }

    def getGoodThread() {
        def tag = new Tag()
        tag.name = "name"
        tag.save()
        def thread = new Thread(params)
        def post = new Post()
        def user = new User()
        user.mail = "mottetalexandre@gmail.com"
        user.displayName = "Alexandre MOTTET"
        user.password = "PasswOrd!"
        user.realName = ""
        user.website = ""
        user.location = ""
        user.birthday = new Date(System.currentTimeMillis())
        user.aboutMe = ""
        user.pathToAvatar = ""
        user.ban = false
        user.reputation = 0
        user.rank = Rank.Administrator
        user.save()
        post.author = user
        post.content = "content"
        post.creationDate = new Date()
        post.lastEditionDate = new Date()
        post.thread = thread
        thread.addToTags(tag)
        thread.title = 0
        thread.viewCount = 0
        thread.firstPost = post

        post.save()

        return thread
    }

    void testIndex() {
        controller.index()
        assert "/thread/create" == response.redirectedUrl
    }

    void testCreate() {
        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        controller.create()

        assert "/oauth/google/authenticate" == response.redirectedUrl

        def user = new User()
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        def model = controller.create()

        assert model.threadInstance != null
    }

    void testSave() {
        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        controller.save()

        assert "/home" == response.redirectedUrl

        def user = new User()
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        populateValidParams(params)
        controller.save()
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/thread/index'

        def thread = getGoodThread()
        assert thread.save() != null

        params.id = thread.id

        def model = controller.show()

        assert model.threadInstance == thread
    }

    void testEdit() {
        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        controller.edit()

        assert "/home" == response.redirectedUrl
        response.reset()

        def user = new User()
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/thread/index'
        response.reset()

        def thread = getGoodThread()

        assert thread.save() != null

        params.id = thread.id

        def model = controller.edit()

        //assert model.threadInstance == thread
    }

    void testUpdate() {
        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        controller.update()

        assert "/home" == response.redirectedUrl
        response.reset()

        def user = new User()
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/thread/index'

        response.reset()

        def thread = getGoodThread()

        assert thread.save() != null

        // test invalid parameters in update
        params.id = thread.id
        params.title = ""

        controller.update()

        assert view == "/thread/edit"
        assert model.threadInstance != null

        thread.clearErrors()
        response.reset()

        populateValidParams(params)
        /*controller.update()

        assert response.redirectedUrl == "/thread/show/$thread.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        thread.clearErrors()

        populateValidParams(params)
        params.id = thread.id
        params.version = -1
        controller.update()

        assert view == "/thread/edit"
        assert model.threadInstance != null
        assert model.threadInstance.errors.getFieldError('version')
        assert flash.message != null*/
    }

    void testDelete() {
        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        controller.delete()

        assert "/home" == response.redirectedUrl
        response.reset()

        def user = new User()
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/thread/index'

        response.reset()

        def thread = getGoodThread()

        assert thread.save() != null
        assert Thread.count() == 1

        params.id = thread.id

        controller.delete()

        assert Thread.count() == 0
        assert Thread.get(thread.id) == null
    }
}
