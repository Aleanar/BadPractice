package fr.isima

import grails.test.mixin.*

@TestFor(PostController)
@Mock([Post, UserService, PostService])
class PostControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["thread"] = new Thread()
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

        def user = new User()
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        model = controller.create()

        assert model.postInstance != null
    }

    void testSave() {

        session[controller.userService.USER_SESSION_OBJECT_NAME] = null

        def model = controller.save()

        assert "/home" == response.redirectedUrl

        def user = new User()
        user.id = 1
        user.rank = Rank.Administrator
        session[controller.userService.USER_SESSION_OBJECT_NAME] = user

        controller.save()

        assert model.postInstance != null
        assert view == '/post/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/post/show/1'
        assert controller.flash.message != null
        assert Post.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/post/list'

        populateValidParams(params)
        def post = new Post(params)

        assert post.save() != null

        params.id = post.id

        def model = controller.show()

        assert model.postInstance == post
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/post/list'

        populateValidParams(params)
        def post = new Post(params)

        assert post.save() != null

        params.id = post.id

        def model = controller.edit()

        assert model.postInstance == post
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/post/list'

        response.reset()

        populateValidParams(params)
        def post = new Post(params)

        assert post.save() != null

        // test invalid parameters in update
        params.id = post.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/post/edit"
        assert model.postInstance != null

        post.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/post/show/$post.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        post.clearErrors()

        populateValidParams(params)
        params.id = post.id
        params.version = -1
        controller.update()

        assert view == "/post/edit"
        assert model.postInstance != null
        assert model.postInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/post/list'

        response.reset()

        populateValidParams(params)
        def post = new Post(params)

        assert post.save() != null
        assert Post.count() == 1

        params.id = post.id

        controller.delete()

        assert Post.count() == 0
        assert Post.get(post.id) == null
        assert response.redirectedUrl == '/post/list'
    }
}
