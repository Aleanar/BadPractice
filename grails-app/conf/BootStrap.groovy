import fr.isima.Tag
import fr.isima.User

class BootStrap {

    def tagService;

    def init = { servletContext ->

        // Default tag
        def tag = new Tag();
        tag.name = "tagTest1"
        tagService.addTag(tag)
        tag = new Tag();
        tag.name = "tagTest2"
        tagService.addTag(tag)
        tag = new Tag();
        tag.name = "MyTagTest"
        tagService.addTag(tag)
        tag = new Tag();
        tag.name = "bonjour"
        tagService.addTag(tag)

        // Users creation
        def author = new User();
        author.mail = "marquesthom@gmail.com"
        author.displayName = "Thomas MARQUES"
        author.password = "Password"
        author.realName = ""
        author.website = ""
        author.location = ""
        author.birthday = new Date(System.currentTimeMillis())
        author.aboutMe = ""
        author.pathToAvatar = ""
        author.save()

        author = new User();
        author.mail = "mottetalexandre@gmail.com"
        author.displayName = "Alexandre MOTTET"
        author.password = "PasswOrd!"
        author.realName = ""
        author.website = ""
        author.location = ""
        author.birthday = new Date(System.currentTimeMillis())
        author.aboutMe = ""
        author.pathToAvatar = ""
        author.save()

    }
    def destroy = {
    }
}
