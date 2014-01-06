<%@ page import="fr.isima.Thread" %>



<div class="fieldcontain ${hasErrors(bean: threadInstance, field: 'posts', 'error')} ">
	<label for="posts">
		<g:message code="thread.posts.label" default="Posts" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${threadInstance?.posts?}" var="p">
    <li><g:link controller="post" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="post" action="create" params="['thread.id': threadInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'post.label', default: 'Post')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: threadInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="thread.tags.label" default="Tags" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: threadInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="thread.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${threadInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: threadInstance, field: 'viewCount', 'error')} required">
	<label for="viewCount">
		<g:message code="thread.viewCount.label" default="View Count" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="viewCount" type="number" value="${threadInstance.viewCount}" required=""/>
</div>

