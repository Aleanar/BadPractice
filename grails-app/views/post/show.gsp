
<%@ page import="fr.isima.Thread" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'thread.entityName.label', default: 'Thread')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

		<a href="#show-thread" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-thread" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
            <g:hasErrors bean="${postInstance}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${postInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <h1><g:fieldValue bean="${threadInstance}" field="title"/></h1><br />
            <span class="property-value" name="viewCount"><g:fieldValue bean="${threadInstance}" field="viewCount"/></span><g:message code="thread.viewCount.label" /><br />
            <g:set var="currentPost" value="${threadInstance.firstPost}" />
            <g:render template="../post/showPost"/>

            <g:each in="${threadInstance.getPostsToShow()}" var="post">
                <g:if test="${post.id != threadInstance.firstPost.id}" >
                    <g:set var="currentPost" value="${post}" />
                    <g:render template="../post/showPost"/>
                </g:if>
            </g:each>

            <g:form action="savePost" style="border: 1px darkgreen dashed" >
                <fieldset class="form">
                    <g:render template="../post/form"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>

			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${threadInstance?.id}" />
					<g:link class="edit" action="edit" id="${threadInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
