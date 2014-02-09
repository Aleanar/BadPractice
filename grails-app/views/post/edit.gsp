<%@ page import="fr.isima.Post" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'post.label', default: 'Post')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="edit-post" class="" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:flashMessage/>
			<g:hasErrors bean="${postInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${postInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${postInstance?.id}" />
				<g:hiddenField name="version" value="${postInstance?.version}" />
				<fieldset class="form">

                    <g:hiddenField id="thread" name="thread.id" value="${threadInstance.id}" />

                    <div class="form-group ${hasErrors(bean: postInstance, field: 'content', 'error')} ">
                        <g:textArea id="post-editor" class="form-control" name="content" value="${postInstance?.content}" rows="30" cols="50" placeholder="${message(code: 'post.content.placeholder', default: 'post.content.placeholder')}" />
                    </div>

				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="btn btn-default" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
			</g:form>
		</div>
	</body>
</html>
