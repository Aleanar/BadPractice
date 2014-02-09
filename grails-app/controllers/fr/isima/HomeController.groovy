package fr.isima

class HomeController {

    def threadService

    def index() {

        log.info "[HOME-index]"

        [threads: threadService.threads()]

    }
}
