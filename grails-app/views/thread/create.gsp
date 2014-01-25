<%@ page import="fr.isima.Tag; fr.isima.Thread" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:set var="entityNameThread" value="${message(code: 'thread.entityName.label', default: '')}" />
        <g:set var="entityNameTag" value="${message(code: 'tag.entityName.label', default: '')}" />
		<title><g:message code="default.create.label" args="[entityNameThread]" /></title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'token.css')}" type="text/css"/>
        <g:javascript library="autocomplete"/>
    </head>
	<body>

		<div id="create-thread" class="col-md-9" role="main">

			<h1><g:message code="thread.create.label" args="[entityNameThread]" /></h1>

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

            <g:hasErrors bean="${threadInstance}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${threadInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>
            <g:hasErrors bean="${threadInstance?.firstPost}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${threadInstance?.firstPost}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <g:render template="form"/>

		</div>

        <div id="create-tag" class="col-md-3">

            <h1><g:message code="default.create.label" args="[entityNameTag]" /></h1>

            <g:formRemote name="tagAddForm" url="[controller:'tag', action:'insertTag']" update="resultAddingTag">
                <g:render template="../tag/form"/>
                <g:submitButton name="tag.create.label"/>
            </g:formRemote>

        </div>

	</body>
</html>
