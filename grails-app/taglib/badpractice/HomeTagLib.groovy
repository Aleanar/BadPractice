package badpractice

class HomeTagLib {

    def jumbotron = {

        if(pageScope.controllerName == 'home') {
            out << render(template:"/taglib/jumbotron")
        }

    }

}
