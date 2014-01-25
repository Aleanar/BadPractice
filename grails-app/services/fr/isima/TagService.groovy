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
        tag.save(flush:true)
    }

    /**
     * Return tag with id
     * @param id Id searched
     * @return A list of tags
     */
    def getTagById(Long id) {
        Tag.findAllById(id)
    }

    /**
     * Get all tags beginning like the params.
     * @param params the beginning of the name of the desired tags.
     * @return all tags corresponding.
     */
    def getTagsWithName(params){
        def queryBegin = {
            ilike("name", "${params.q}%") // term is the parameter send by jQuery autocomplete
            projections {
                property("id")
                property("name")
            }
            order("name")
        }
        def queryContains = {
            ilike("name", "%${params.q}%") // term is the parameter send by jQuery autocomplete
            projections {
                property("id")
                property("name")
            }
            order("name")
        }
        def tagsList = Tag.createCriteria().list(queryBegin) // execute  to the get the list of companies
        tagsList.addAll(Tag.createCriteria().list(queryContains))

        def tagsSelectList = [] // to add each company details
        tagsList.unique().each {
            def tagMap = [:]
            tagMap.put("id", it[0])
            tagMap.put("name", it[1])
            tagsSelectList.add(tagMap)
        }
        return tagsSelectList
    }

    def getAllTagsOrderByUse() {
        def tagsList = Tag.findAll();
        tagList.sort(new Comparator<Tag>() {
            @Override
            int compare(Tag o1, Tag o2) {
                return (o1.threads ? o1.threads.size() : 0).compareTo(o2.threads ? o2.threads.size() : 0)
            }
        })
        return tagList
    }
}
