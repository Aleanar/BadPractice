package badpractice

class FlashMessageTagLib {

    def message () {
        out << render(template:"/taglib/message")
    }

}
