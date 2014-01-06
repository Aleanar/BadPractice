<%@ page import="fr.isima.Post" %>



<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'author', 'error')} required">
	<label for="author">
		<g:message code="post.author.label" default="Author" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="author" name="author.id" from="${fr.isima.User.list()}" optionKey="id" required="" value="${postInstance?.author?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="post.content.label" default="Content" />
		
	</label>
	<g:textField name="content" value="${postInstance?.content}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="post.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${postInstance?.creationDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'lastEditionDate', 'error')} required">
	<label for="lastEditionDate">
		<g:message code="post.lastEditionDate.label" default="Last Edition Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="lastEditionDate" precision="day"  value="${postInstance?.lastEditionDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'posts', 'error')} ">
	<label for="posts">
		<g:message code="post.posts.label" default="Posts" />
		
	</label>
	<g:select name="posts" from="${fr.isima.Post.list()}" multiple="multiple" optionKey="id" size="5" value="${postInstance?.posts*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'thread', 'error')} required">
	<label for="thread">
		<g:message code="post.thread.label" default="Thread" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="thread" name="thread.id" from="${fr.isima.Thread.list()}" optionKey="id" required="" value="${postInstance?.thread?.id}" class="many-to-one"/>
</div>

