<%@ page import="fr.isima.Thread" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:set var="entityNameThread" value="${message(code: 'thread.entityName.label', default: '')}" />
        <g:set var="entityNameTag" value="${message(code: 'tag.entityName.label', default: '')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <ckeditor:resources/>
        <g:javascript library="autocomplete"/>
	</head>
	<body>
		<a href="#edit-thread" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityNameThread]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityNameThread]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-thread" class="content scaffold-edit" role="main">
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
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <g:submitButton name="update" class="save btn btn-default" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                        </div>
                    </div>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
