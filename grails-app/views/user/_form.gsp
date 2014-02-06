<%@ page import="fr.isima.User" %>

<g:if test="${isAdmin}" >
    <div>
        <label for="rank">
            <g:message code="user.rank.label" default="Rank" />
        </label>
        <g:select name="rank" from="${fr.isima.Rank.values()}" value="${userInstance.rank.toString()}">
        </g:select>
    </div>
</g:if>

<div>
    <a href="https://en.gravatar.com/site/login" target="_blank" >
        <avatar:gravatar email="${userInstance.mail}" alt="Avatar" size="100"/>
    </a>
    <br />
    <label>
        <g:message code="user.gravatar.label" default="Mail" />
    </label>
</div>

<div>
	<label for="mail">
		<g:message code="user.mail.label" default="Mail" />
	</label>
    <g:hiddenField name="mail" value="${userInstance.mail}"/>
    ${userInstance?.mail}
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'displayName', 'error')} ">
    <label for="displayName">
        <g:message code="user.displayName.label" default="Display Name" />

    </label>
    <g:textField name="displayName" value="${userInstance?.displayName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'realName', 'error')} ">
	<label for="realName">
		<g:message code="user.realName.label" default="Real Name" />
		
	</label>
	<g:textField name="realName" value="${userInstance?.realName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'birthday', 'error')} required">
    <label for="birthday">
        <g:message code="user.birthday.label" default="Birthday" />
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="birthday" precision="day"  value="${userInstance?.birthday}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'location', 'error')} ">
    <label for="location">
        <g:message code="user.location.label" default="Location" />

    </label>
    <g:textField name="location" value="${userInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'website', 'error')} ">
	<label for="website">
		<g:message code="user.website.label" default="Website" />
		
	</label>
	<g:textField name="website" value="${userInstance?.website}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'aboutMe', 'error')} ">
    <label for="aboutMe">
        <g:message code="user.aboutMe.label" default="About Me" />

    </label>
    <g:textArea rows="5" cols="50"  name="aboutMe" value="${userInstance?.aboutMe}"/>
</div>