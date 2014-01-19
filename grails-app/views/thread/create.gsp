<%@ page import="fr.isima.Tag; fr.isima.Thread" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'thread.label', default: 'Thread')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'token.css')}" type="text/css"/>
        <g:javascript library="autocomplete"/>
    </head>
	<body>
		<a href="#create-thread" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-thread" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
        <div id="create-tag">
            <h1><g:message code="default.create.label" args="tag" /></h1>
            <g:formRemote name="tagAddForm" url="[controller:'tag', action:'insertTag']" update="resultAddingTag">
                <g:render template="../tag/form"/>
                <g:submitButton name="tag.create.label"/>
            </g:formRemote>
            <div id="resultAddingTag">
                <g:render template="../tag/resultInsertion"/>
            </div>
        </div>
	</body>
</html>
