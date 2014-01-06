<%@ page import="fr.isima.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'aboutMe', 'error')} ">
	<label for="aboutMe">
		<g:message code="user.aboutMe.label" default="About Me" />
		
	</label>
	<g:textField name="aboutMe" value="${userInstance?.aboutMe}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'birthday', 'error')} required">
	<label for="birthday">
		<g:message code="user.birthday.label" default="Birthday" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="birthday" precision="day"  value="${userInstance?.birthday}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'displayName', 'error')} ">
	<label for="displayName">
		<g:message code="user.displayName.label" default="Display Name" />
		
	</label>
	<g:textField name="displayName" value="${userInstance?.displayName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="user.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${userInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'mail', 'error')} ">
	<label for="mail">
		<g:message code="user.mail.label" default="Mail" />
		
	</label>
	<g:textField name="mail" value="${userInstance?.mail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'pathToAvatar', 'error')} ">
	<label for="pathToAvatar">
		<g:message code="user.pathToAvatar.label" default="Path To Avatar" />
		
	</label>
	<g:textField name="pathToAvatar" value="${userInstance?.pathToAvatar}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'postsCreated', 'error')} ">
	<label for="postsCreated">
		<g:message code="user.postsCreated.label" default="Posts Created" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.postsCreated?}" var="p">
    <li><g:link controller="post" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="post" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'post.label', default: 'Post')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'realName', 'error')} ">
	<label for="realName">
		<g:message code="user.realName.label" default="Real Name" />
		
	</label>
	<g:textField name="realName" value="${userInstance?.realName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'website', 'error')} ">
	<label for="website">
		<g:message code="user.website.label" default="Website" />
		
	</label>
	<g:textField name="website" value="${userInstance?.website}"/>
</div>

