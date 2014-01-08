package fr.isima

class TagService {

    /**
     * Return all tags
     * @return tags
     */
    def getTagList() {
        Tag.findAll()
    }

    /**
     * Save the given tag
     * @param tag the tag to be save
     * @return true if succeeded, false otherwise
     */
    def addTag(Tag tag) {
        tag.save()
    }

}
