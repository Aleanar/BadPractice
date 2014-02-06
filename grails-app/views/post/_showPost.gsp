<g:if test="${currentPost}">

    <div class="well well-lg">

        <div class="col-md-1">



            <div id="vote" class="text-center" name="vote">
                <g:formRemote name="voteUpForm" url="[controller:'vote', action:'addVote']" method="GET" update="resultAddingVote${currentPost.id}">
                    <g:hiddenField name="up" value="1"/>
                    <g:hiddenField name="postId" value="${currentPost.id}"/>
                    <button class="btn btn-success btn-sm"><i class="glyphicon glyphicon-chevron-up"></i></button>
                </g:formRemote>
                <div id="resultAddingVote${currentPost.id}">
                    <span id="voteResult">${currentPost.getCountingVote()}</span>
                </div>
                <g:formRemote name="voteDownForm" url="[controller:'vote', action:'addVote']" method="GET" update="resultAddingVote${currentPost.id}">
                    <g:hiddenField name="up" value="0"/>
                    <g:hiddenField name="postId" value="${currentPost.id}"/>
                    <button class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-chevron-down"></i></button>
                </g:formRemote>
            </div>


        </div>
        <div class="col-md-11">

            ${currentPost.content}

            <div class="panel-footer">

                <div class="small pull-left">
                    <div class="property-value" name="creationDate">
                        <g:message code="post.creationDate" />
                        <g:formatDate type="date" style="LONG" locale="true" date="${currentPost.creationDate}"/>
                    </div>
                    <div class="property-value" name="lastEditionDate">
                        <g:message code="post.editionDate" />
                        <g:formatDate type="datetime" style="LONG" timeStyle="SHORT" locale="true" date="${currentPost.lastEditionDate}"/>
                    </div>
                </div>
                <g:set var="currentUser" value="${currentPost.author}" />
                <g:render template="../user/showUser" />

            </div>

        </div>
        <div class="clearfix"></div>

    </div>

</g:if>