<%--
  Created by IntelliJ IDEA.
  User: alexandre
  Date: 15/01/2014
  Time: 15:45
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>

<g:jumbotron />

<g:flashMessage/>
<div class="row">
</div>
<div class="row">
    <div class="col-md-8">
        <div class="">
            <h1>${message(code: 'home.threadList.label', default: 'Last Question')}</h1>
        </div>
        <g:each in="${threads}" status="i" var="threadInstance">
            <div class="well" style="padding:0 0 0 20px;">
                <blockquote style="margin-bottom:0;">
                    <g:link controller="thread" action="show" id="${threadInstance.id}">${fieldValue(bean: threadInstance, field: "title")}</g:link>
                    <small><cite title="Source Title">${threadInstance.firstPost.author.displayName}</cite></small>
                </blockquote>
            </div>
        </g:each>

    </div>
    <div class="col-md-4">

        <div class="">
            <h1>${message(code: 'home.tagList.label', default: 'See threads by tags')}</h1>
        </div>
        <div class="well">
        <button type="button" class="btn btn-default btn-lg btn-block">Block level button</button>
        <button type="button" class="btn btn-default btn-lg btn-block">Block level button</button>
        </div>

    </div>
</div>
</body>
</html>