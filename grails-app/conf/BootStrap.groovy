import fr.isima.Post
import fr.isima.Tag
import fr.isima.User
import fr.isima.Rank

class BootStrap {

    def tagService
    def threadService
    def userService

    def init = { servletContext ->

        /*if(userService.getAllUsers().size() == 0)
        {
            // Default tag
            def tag = new Tag();
            tag.name = "Groovy"
            tagService.addTag(tag)
            tag = new Tag();
            tag.name = "Grails"
            tagService.addTag(tag)
            tag = new Tag();
            tag.name = "c++"
            tagService.addTag(tag)
            tag = new Tag();
            tag.name = "Java"
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
            author.realName = "Thomas Masqué"
            author.website = "https://bitbucket.org/Aleanar/badpractice"
            author.location = "Clermont-Ferrand"
            author.birthday = new Date(System.currentTimeMillis())
            author.aboutMe = ""
            author.pathToAvatar = ""
            author.rank = Rank.Administrator
            author.save()

            def thread = new fr.isima.Thread()
            thread.title = "How to add specific configuration to a Maven plugin"
            thread.viewCount = 0

            def post = new Post()
            post.creationDate = new Date()
            post.content = "In my pom.xml I configure a plugin to convert certain Protobuf files to Java class files."
            post.lastEditionDate = new Date()
            post.author = author
            post.thread = thread

            thread.firstPost = post
            thread.tags = new HashSet<>()

            thread.addToTags(tag)
            tag = new Tag()
            tag.name = "Eclipse"
            tagService.addTag(tag)
            thread.addToTags(tag)

            tag = new Tag()
            tag.name = "Maven"
            tagService.addTag(tag)
            thread.addToTags(tag)

            tag = new Tag()
            tag.name = "Protocol-buffers"
            tagService.addTag(tag)
            thread.addToTags(tag)

            thread.save()
            post.save()

            post = new Post()
            post.creationDate = new Date()
            post.content = "Eclipse configuration in your example is produced by m2eclipse's project configuratior and I'm quite certain it doesn't support such feature right now. Though you can try to submit an enhancement request. See <a href=\"http://www.eclipse.org/m2e/support/\">http://www.eclipse.org/m2e/support/</a>"
            post.lastEditionDate = new Date()
            post.author = author
            post.thread = thread
            post.save()

            thread.addToPosts(post)

            post = new Post()
            post.creationDate = new Date()
            post.content = "<a href=\"https://bugs.eclipse.org/bugs/show_bug.cgi?id=388541\">Login and vote for this Feature-Request</a>:\n \n There already is a patch, improve it."
            post.lastEditionDate = new Date()
            post.author = author
            post.thread = thread
            post.save()

            thread.addToPosts(post)

            thread.save()
        }*/
    }
    def destroy = {
    }
}
