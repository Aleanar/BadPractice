package badpractice

class UserConnectionTagLib {

    def userConnection = {

        if (session['user'])
            out << render(template:"/taglib/userconnected")
        else
            out << render(template:"/taglib/useranonymous")

    }

}
