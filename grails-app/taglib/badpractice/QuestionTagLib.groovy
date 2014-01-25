package badpractice

class QuestionTagLib {

    def newquestion = {

        if (session['user'])
            out << render(template:"/taglib/newquestion")

    }

}
