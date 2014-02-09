package fr.isima



import org.junit.*
import grails.test.mixin.*

@TestFor(ThreadController)
@Mock([Thread, Tag, UserService, PostService, TagService])
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

        assert "/oauth/google/authenticate" == response.redirectedUrl

        def user = new User()
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/thread/show/1'
        assert controller.flash.message != null
        assert Thread.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/thread/list'

        populateValidParams(params)
        def thread = new Thread(params)

        assert thread.save() != null

        params.id = thread.id

        def model = controller.show()

        assert model.threadInstance == thread
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/thread/list'

        populateValidParams(params)
        def thread = new Thread(params)

        assert thread.save() != null

        params.id = thread.id

        def model = controller.edit()

        assert model.threadInstance == thread
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/thread/list'

        response.reset()

        populateValidParams(params)
        def thread = new Thread(params)

        assert thread.save() != null

        // test invalid parameters in update
        params.id = thread.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/thread/edit"
        assert model.threadInstance != null

        thread.clearErrors()

        populateValidParams(params)
        controller.update()

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
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/thread/list'

        response.reset()

        populateValidParams(params)
        def thread = new Thread(params)

        assert thread.save() != null
        assert Thread.count() == 1

        params.id = thread.id

        controller.delete()

        assert Thread.count() == 0
        assert Thread.get(thread.id) == null
        assert response.redirectedUrl == '/thread/list'
    }
}
