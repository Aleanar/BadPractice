package fr.isima

class HomeController {

    def threadService
    def tagService

    def index() {

        log.info "[HOME-index]"

        [threads: threadService.threads(), tags: tagService.getAllTagsOrderByUse()]

    }

    def error404() {
        log.info "[HOME-404] called"
    }
}
