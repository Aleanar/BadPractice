
<%@ page import="fr.isima.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.entityName.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
        <h1><g:fieldValue bean="${userInstance}" field="displayName"/></h1>
        <div class="row"><g:badges userId="${userInstance.id}"/></div>
        <div class="row">
            <div class="col-md-9">

                <h2><g:message code="user.thread.participated.post" /></h2>
                <g:if test="${userInstance?.postsCreated}">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th><g:message code="user.thread.list.title"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${userInstance.postsCreated*.thread.unique()}" var="p">
                            <g:if test="${p}">
                                <tr>
                                    <td>
                                        <g:link controller="thread" action="show" id="${p.id}">${p.title}</g:link>
                                    </td>
                                </tr>
                            </g:if>
                        </g:each>
                        </tbody>
                    </table>
                </g:if>

                <g:if test="${userInstance.votes*.post?.thread?.size() > 0}">
                    <h2><g:message code="user.thread.participated.vote" /></h2>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th><g:message code="user.thread.list.title"/></th>
                            </tr>
                        </thead>
                        <tbody>
                        <g:each in="${userInstance.votes.sort{-(it.date.time)}*.post?.thread?.unique()}" var="p">
                            <g:if test="${p}">
                                <tr>
                                    <td>
                                        <g:link controller="thread" action="show" id="${p.id}">${p.title}</g:link>
                                    </td>
                                </tr>
                            </g:if>
                        </g:each>
                        </tbody>
                    </table>
                </g:if>

            </div>
            <div class="col-md-3 visible-lg visible-md">

                <div style="position:absolute;top:-40px;right:0px;width:300px;height:500px;overflow:hidden;text-align:center;">
                    <%--<img src="<g:fieldValue bean="${userInstance}" field="pathToAvatar"/>0" style="opacity:0.2;z-index:-1000;" />--%>
                    <avatar:gravatar email="${userInstance.mail}" style="opacity:0.2;z-index:-1000;" alt="Avatar" size="512"/>
                    <a href="<g:fieldValue bean="${userInstance}" field="website"/>" class="btn btn-success" style="position:absolute;bottom:10px;left:10px;">See Website</a>
                    <div style="position:absolute;bottom:10px;right:10px;"><g:fieldValue bean="${userInstance}" field="location"/></div>
                </div>

            </div>
        </div>
    <g:if test="${isAdmin}" >
        <g:form>
            <fieldset class="buttons">
                <g:hiddenField name="id" value="${userInstance.id}" />
                <g:link class="edit" action="edit" id="${userInstance.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
            </fieldset>
        </g:form>
    </g:if>
	</body>
</html>
