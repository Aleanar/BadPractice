
<%@ page import="fr.isima.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.entityName.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<%--<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--%>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-user" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="aboutMe" title="${message(code: 'user.aboutMe.label', default: 'About Me')}" />
					
						<g:sortableColumn property="birthday" title="${message(code: 'user.birthday.label', default: 'Birthday')}" />
					
						<g:sortableColumn property="displayName" title="${message(code: 'user.displayName.label', default: 'Display Name')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'user.location.label', default: 'Location')}" />
					
						<g:sortableColumn property="mail" title="${message(code: 'user.mail.label', default: 'Mail')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'user.password.label', default: 'Password')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userInstanceList}" status="i" var="userInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "aboutMe")}</g:link></td>
					
						<td><g:formatDate date="${userInstance.birthday}" /></td>
					
						<td>${fieldValue(bean: userInstance, field: "displayName")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "location")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "mail")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "password")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
