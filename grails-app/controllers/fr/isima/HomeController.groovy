package fr.isima

class HomeController {

    def threadService

    def index() {

        [threads: threadService.threads()]

    }
}
