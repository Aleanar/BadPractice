
<%@ page import="fr.isima.Tag" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-tag" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

            <g:each in="${tagInstanceList}" status="i" var="tagInstance">

                <div class="row">
                    <div class="col-md-4 tag-square">
                        ${fieldValue(bean: tagInstance, field: "name")}
                    </div>
                    <div class="col-md-8">
                        <g:each in="${tagInstance.threads}" status="j" var="tagThreadInstance">
                            <div>
                                <g:link controller="thread" action="show" id="${tagThreadInstance.id}">${tagThreadInstance.title}</g:link>
                            </div>
                        </g:each>
                    </div>
                </div>
                <div class="tag-separator"></div>

            </g:each>

		</div>
	</body>
</html>
