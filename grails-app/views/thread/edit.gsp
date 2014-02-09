<%@ page import="fr.isima.Thread" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:set var="entityNameThread" value="${message(code: 'thread.entityName.label', default: '')}" />
        <g:set var="entityNameTag" value="${message(code: 'tag.entityName.label', default: '')}" />
        <title><g:message code="default.edit.label" args="[entityNameThread]" /></title>
        <ckeditor:resources/>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'token.css')}" type="text/css"/>
        <g:javascript library="autocomplete"/>
	</head>
	<body>
		<div id="edit-thread" class="" role="main">
			<h1><g:message code="default.edit.label" args="[entityNameThread]" /></h1>
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
			<g:form method="post" >
				<g:hiddenField name="id" value="${threadInstance?.id}" />
				<g:hiddenField name="version" value="${threadInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
                <fieldset class="buttons">
                    <g:actionSubmit class="btn btn-default" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
			</g:form>
		</div>
	</body>
</html>
