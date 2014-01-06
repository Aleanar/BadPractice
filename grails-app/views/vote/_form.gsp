<%@ page import="fr.isima.Vote" %>



<div class="fieldcontain ${hasErrors(bean: voteInstance, field: 'thread', 'error')} required">
	<label for="thread">
		<g:message code="vote.thread.label" default="Thread" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="thread" name="thread.id" from="${fr.isima.Thread.list()}" optionKey="id" required="" value="${voteInstance?.thread?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: voteInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="vote.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${fr.isima.User.list()}" optionKey="id" required="" value="${voteInstance?.user?.id}" class="many-to-one"/>
</div>

