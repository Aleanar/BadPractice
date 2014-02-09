
<%@ page import="fr.isima.Tag" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>

<div id="show-tag" role="main">
    <h1>${tagInstance?.name}</h1>

    <g:flashMessage />

        <g:if test="${tagInstance?.threads}">

            <g:each in="${tagInstance.threads}" status="i" var="t">
                <div class="well" style="padding:0 0 0 20px;">
                    <blockquote style="margin-bottom:0;">
                        <g:link controller="thread" action="show" id="${t.id}">${fieldValue(bean: t, field: "title")}</g:link>
                        <small><cite title="Source Title">${t.firstPost.author.displayName}</cite></small>
                    </blockquote>
                </div>
            </g:each>

        </g:if>
</div>

</body>
</html>
