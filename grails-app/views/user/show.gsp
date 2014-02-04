
<%@ page import="fr.isima.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
        <h1><g:fieldValue bean="${userInstance}" field="displayName"/></h1>
        <div class="row"><g:badges/></div>
        <div class="row">
            <div class="col-md-9">

                <h2>Last thread</h2>
                <g:if test="${userInstance?.postsCreated}">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>Title</th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${userInstance.postsCreated}" var="p">
                            <g:if test="${p?.thread}">
                                <tr>
                                    <td>
                                        <g:link controller="thread" action="show" id="${p.thread.id}">${p.thread.title}</g:link>
                                    </td>
                                </tr>
                            </g:if>
                        </g:each>
                        </tbody>
                    </table>
                </g:if>

            </div>
            <div class="col-md-3 responsive-utility">

                <div style="position:absolute;top:-40px;right:0px;width:300px;height:500px;overflow:hidden;text-align:center;">
                    <%--<img src="<g:fieldValue bean="${userInstance}" field="pathToAvatar"/>0" style="opacity:0.2;z-index:-1000;" />--%>
                    <a href="https://en.gravatar.com/site/login">
                        <avatar:gravatar email="${userInstance.mail}" style="opacity:0.2;z-index:-1000;" alt="Avatar" size="500"/>
                    </a>
                    <a href="<g:fieldValue bean="${userInstance}" field="website"/>" class="btn btn-success" style="position:absolute;bottom:10px;left:10px;">See Website</a>
                    <div style="position:absolute;bottom:10px;right:10px;"><g:fieldValue bean="${userInstance}" field="location"/></div>
                </div>

            </div>
        </div>
	</body>
</html>
