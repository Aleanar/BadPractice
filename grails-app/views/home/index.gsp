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
<div class="row">
    <h1>${message(code: 'home.threadList.label', default: 'Last Question')}</h1>
    <g:each in="${threads}" status="i" var="threadInstance">
        <div class="col-md-4">
            <g:link action="show" id="${threadInstance.id}">${fieldValue(bean: threadInstance, field: "title")}</g:link>

        </div>
    </g:each>
</div>
</body>
</html>