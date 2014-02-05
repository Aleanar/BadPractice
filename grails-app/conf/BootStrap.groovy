import fr.isima.Post
import fr.isima.Tag
import fr.isima.User
import fr.isima.Rank

class BootStrap {

    def tagService
    def threadService
    def userService

    def init = { servletContext ->

        /*// Default tag
        def tag = new Tag();
        tag.name = "Java"
        tagService.addTag(tag)
        tag = new Tag();
        tag.name = "Groovy"
        tagService.addTag(tag)
        tag = new Tag();
        tag.name = "Grails"
        tagService.addTag(tag)
        tag = new Tag();
        tag.name = "c++"
        tagService.addTag(tag)

        // Users creation
        def author = new User();
        author.mail = "mottetalexandre@gmail.com"
        author.displayName = "Alexandre MOTTET"
        author.password = "PasswOrd!"
        author.realName = ""
        author.website = ""
        author.location = ""
        author.birthday = new Date(System.currentTimeMillis())
        author.aboutMe = ""
        author.pathToAvatar = ""
        author.rank = Rank.Administrator
        author.save()

        author = new User();
        author.mail = "marquesthom@gmail.com"
        author.displayName = "Thomas MARQUES"
        author.password = "Password"
        author.realName = ""
        author.website = ""
        author.location = ""
        author.birthday = new Date(System.currentTimeMillis())
        author.aboutMe = ""
        author.pathToAvatar = ""
        author.rank = Rank.Administrator
        author.save()

        /// Data test
        tag = new Tag()
        tag.name = "tagTest1"
        tagService.addTag(tag)
        tag = new Tag()
        tag.name = "tagTest2"
        tagService.addTag(tag)
        tag = new Tag()
        tag.name = "MyTagTest"
        tagService.addTag(tag)

        def thread = new fr.isima.Thread()
        thread.title = "How to add specific configuration to a Maven plugin"
        int viewCount = 0

        def post = new Post()
        post.creationDate = new Date()
        post.content = "In my pom.xml I configure a plugin to convert certain Protobuf files to Java class files."
        post.lastEditionDate = new Date()
        post.author = author
        post.thread = thread

        thread.firstPost = post
        thread.tags = new HashSet<>()

        tag = new Tag()
        tag.name = "Eclipse"
        tagService.addTag(tag)
        thread.tags.add(tag)

        tag = new Tag()
        tag.name = "Maven"
        tagService.addTag(tag)
        thread.tags.add(tag)

        tag = new Tag()
        tag.name = "Protocol-buffers"
        tagService.addTag(tag)
        thread.tags.add(tag)

        thread.save()
        post.save()

        post = new Post()
        post.creationDate = new Date()
        post.content = "Response 1"
        post.lastEditionDate = new Date()
        post.author = author
        post.thread = thread
        post.save()

        thread.addToPosts(post)

        post = new Post()
        post.creationDate = new Date()
        post.content = "Response 2"
        post.lastEditionDate = new Date()
        post.author = author
        post.thread = thread
        post.save()

        thread.addToPosts(post)

        post = new Post()
        post.creationDate = new Date()
        post.content = "Response 3"
        post.lastEditionDate = new Date()
        post.author = author
        post.thread = thread
        post.save()

        thread.addToPosts(post)

        thread.save()*/
    }
    def destroy = {
    }
}
