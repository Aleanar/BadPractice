package badpractice

class FlashMessageTagLib {

    def flashMessage = {
        out << render(template:"/taglib/flashmessage")
    }

}
