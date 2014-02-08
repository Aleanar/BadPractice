
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

            <h1>
                <g:fieldValue bean="${threadInstance}" field="title"/>
                <small><g:fieldValue bean="${threadInstance}" field="viewCount"/> <g:message code="thread.viewCount.label" /></small>
            </h1>

            <g:set var="currentPost" value="${threadInstance.firstPost}" />
            <g:render template="../post/showPost"/>
            
            <g:each in="${threadInstance.getPostsToShow()}" var="post">

                <g:if test="${post.id != threadInstance.firstPost.id}" >
                    <g:set var="currentPost" value="${post}" />
                    <g:render template="../post/showPost"/>
                </g:if>

            </g:each>


            <g:if test="${session['user']}">
                <g:form action="savePost" role="form">
                    <fieldset>
                        <g:render template="../post/form"/>
                    </fieldset>
                    <fieldset>
                        <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                    </fieldset>
                </g:form>
            </g:if>
		</div>
        <g:if test="${isEditable}" >
            <g:form>
                <fieldset class="buttons">
                    <g:hiddenField name="id" value="${threadInstance.id}" />
                    <g:link class="edit" action="edit" id="${threadInstance.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </g:if>
	</body>
</html>
