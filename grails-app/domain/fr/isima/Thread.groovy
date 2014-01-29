package fr.isima

class Thread {

    String title
    int viewCount
    Post firstPost

    def getPostsToShow() {
        posts.sort(new Comparator<Post>() {
            @Override
            int compare(Post o1, Post o2) {
                if(o1.countingVote == o2.countingVote)
                    return o1.creationDate.compareTo(o2.creationDate)
                return 0 - (o1.countingVote.compareTo(o2.countingVote))
            }
        })
    }


    static hasMany = [tags:Tag, posts:Post]
    static belongsTo = Tag

    static mapping = {
        title type:'text'
    }

    static constraints = {
        title blank:false
    }
}
