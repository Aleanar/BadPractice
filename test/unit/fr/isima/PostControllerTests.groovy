package fr.isima

import grails.test.mixin.*
import org.junit.*

@TestFor(PostController)
@Mock([Thread, Post, Tag, User, UserService, PostService, ThreadService])
class PostControllerTests {

    def threadService
    def author

    @Before
    void setUp() {
        threadService = new ThreadService()

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

        def post = new Post()
        post.author = user
        post.content = "My content"
        post.creationDate = new Date()
        post.lastEditionDate = new Date()

        def thread = new Thread()
        thread.firstPost = post
        thread.title = "The Title"
        thread.viewCount = 0

        def tag = new Tag()
        tag.name = "name"
        tag.save()
        thread.addToTags(tag)

        threadService.newThread(thread)

        author = user
    }

    def populateValidParams(params) {
        assert params != null

        def thread = threadService.getThreadById(1)

        params["thread"] = thread
        params["content"] = "My content"
        params["creationDate"] = new Date()
        params["lastEditionDate"] = new Date()
    }

    void testIndex() {
        controller.index()
        assert "/home/index" == response.redirectedUrl
    }

    void testCreate() {
        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        def model = controller.create()

        assert "/home" == response.redirectedUrl

        session[controller.userService.USER_SESSION_OBJECT_NAME] = author

        model = controller.create()

        assert model.postInstance != null
    }

    void testSave() {

        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        def model = controller.save()

        assert "/home" == response.redirectedUrl

        session[controller.userService.USER_SESSION_OBJECT_NAME] = author

        controller.save()

        assert view == '/post/create'
    }
}
