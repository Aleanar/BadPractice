package badpractice

class HomeTagLib {

    def userService

    def jumbotron = {

        if(pageScope.controllerName == 'home' && !session[userService.USER_SESSION_OBJECT_NAME]) {
            out << render(template:"/taglib/jumbotron")
        }

    }

}
