<g:if test="${currentPost}">

    <div class="well well-lg" style="position:relative;">
        <g:if test="${isAdmin || currentPost.author.id == userConnected?.id}" >
            <g:link class="btn btn-default btn-sm" style="position:absolute; top:0; right:0;" action="edit" controller="post" id="${currentPost.id}">
                <span class="glyphicon glyphicon-pencil"></span>
            </g:link>
        </g:if>

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

    <div style="margin-right:100px;margin-left:100px;" class="">

        <g:each in="${currentPost.posts}" var="comment">
            <blockquote class="blockquote-reverse" style="font-size:0.9em;">
                ${comment.content}
                <footer>${comment.author.displayName}</footer>
            </blockquote>
        </g:each>

        <g:if test="${session['user']}">

            <div id="comment-form-${currentPost.id}" style="display:none;" class="col-md-6 col-md-offset-4">
                <g:form action="saveComment" role="form">
                    <fieldset>
                        <g:hiddenField id="post" name="post.id" value="${currentPost.id}" />
                        <g:render template="../post/comment"/>
                    </fieldset>
                    <fieldset>
                        <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'thread.comment.create', default: 'Create')}" />
                    </fieldset>
                </g:form>
            </div>
            <div class="clearfix"></div>
            <div>
                <a onclick="$(this).hide(); $('#comment-form-${currentPost.id}').show();" class="pull-right" href="#comment-form-${currentPost.id}">${message(code: 'thread.comment.create.label', default: 'Post a comment')}</a>
            </div>

        </g:if>

    </div>

</g:if>