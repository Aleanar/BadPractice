<%@ page import="fr.isima.Tag; fr.isima.Thread" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:set var="entityNameThread" value="${message(code: 'thread.entityName.label', default: '')}" />
        <g:set var="entityNameTag" value="${message(code: 'tag.entityName.label', default: '')}" />
		<title><g:message code="default.create.label" args="[entityNameThread]" /></title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'token.css')}" type="text/css"/>
        <ckeditor:resources/>
        <g:javascript library="autocomplete"/>
    </head>
	<body>

		<div id="create-thread" class="col-md-9" role="main">

			<h1><g:message code="thread.create.label" args="[entityNameThread]" /></h1>

			<g:message />

            <g:hasErrors bean="${threadInstance}">
                <div class="alert alert-danger" role="alert">
                    <g:eachError bean="${threadInstance}" var="error">
                        <p><g:message error="${error}"/></p>
                    </g:eachError>
                </div>
            </g:hasErrors>

            <g:hasErrors bean="${threadInstance?.firstPost}">
                <div class="alert alert-danger" role="alert">
                    <g:eachError bean="${threadInstance?.firstPost}" var="error">
                        <p><g:message error="${error}"/></p>
                    </g:eachError>
                </div>
            </g:hasErrors>

            <g:form action="save" class="form-horizontal" role="form">
                <fieldset class="form">

                    <g:render template="form"/>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        </div>
                    </div>

                </fieldset>
            </g:form>
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
