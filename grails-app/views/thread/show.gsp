
<%@ page import="fr.isima.Thread" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'thread.entityName.label', default: 'Thread')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

        <div id="show-thread" role="main">
            <g:flashMessage />

            <g:hasErrors bean="${postInstance}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${postInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <div>
            <h1 class="pull-left">
                <g:fieldValue bean="${threadInstance}" field="title"/>
                <small><g:fieldValue bean="${threadInstance}" field="viewCount"/> <g:message code="thread.viewCount.label" /></small>
            </h1>
            <g:if test="${isEditable}" >
                <div class="btn-group pull-right" style="margin-top:20px;">
                    <g:link class="btn btn-default btn-sm" action="edit" id="${threadInstance.id}"><span class="glyphicon glyphicon-pencil"></span></g:link>

                </div>
            </g:if>
            </div>

            <div class="clearfix"></div>

            <div class="btn-group" style="top:2px;left:4px;">
                <g:each in="${threadInstance.tags}" var="tag">

                    <g:link class="btn btn-success btn-xs" action="show" controller="tag" id="${tag.id}">
                        ${tag.name}
                    </g:link>

                </g:each>
            </div>

            <g:set var="currentPost" value="${threadInstance.firstPost}" />
            <g:render template="../post/showPost"/>
            
            <g:each in="${threadInstance.getPostsToShow()}" var="post">

                <g:if test="${post.id != threadInstance.firstPost.id}" >
                    <g:set var="currentPost" value="${post}" />
                    <g:render template="../post/showPost"/>
                </g:if>

            </g:each>


            <g:if test="${session['user']}">
                <g:form action="savePost" role="form" style="margin-left:100px;">
                    <fieldset>
                        <g:render template="../post/form"/>
                    </fieldset>
                    <fieldset>
                        <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                    </fieldset>
                </g:form>
            </g:if>
		</div>
	</body>
</html>
