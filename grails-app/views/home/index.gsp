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
<g:flashMessage/>
<div class="row">
    <div class="">
        <div class="pull-left">
            <g:newquestion class="" />
        </div>
        <h1>${message(code: 'home.threadList.label', default: 'Last Question')}</h1>
    </div>
</div>
<div class="row">
    <g:each in="${threads}" status="i" var="threadInstance">
        <div class="col-md-10 well" style="padding:0 0 0 20px;">
               <blockquote style="margin-bottom:0;">
                   <g:link controller="thread" action="show" id="${threadInstance.id}">${fieldValue(bean: threadInstance, field: "title")}</g:link>
                   <small><cite title="Source Title">${threadInstance.firstPost.author.displayName}</cite></small>
               </blockquote>
        </div>
    </g:each>
</div>
</body>
</html>