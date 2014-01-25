import fr.isima.Tag
import fr.isima.User

class BootStrap {

    def tagService;

    def init = { servletContext ->

        // Default tag
        /*def tag = new Tag();
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
        author.save()*/

    }
    def destroy = {
    }
}
