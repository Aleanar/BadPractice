<%@ page import="fr.isima.Thread" %>

<div class="fieldcontain ${hasErrors(bean: threadInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="thread.title.label" default="Title" />
	</label>
	<g:textField name="thread.title" value="${threadInstance?.title}" placeholder="thread.title.placeholder" />
</div>

<div class="fieldcontain">
    <label for="tags">
        <g:message code="thread.tags.label" default="Tags" />
    </label>
    <g:textField name="tag-name-auto" value="" placeholder="thread.tags.placeholder" />
</div>

<div class="fieldcontain" >
    <label for="author">
        <g:message code="post.author.label" default="Author" />
    </label>
    <g:textField name="user.displayName" value="${threadInstance?.firstPost?.author?.displayName}" readonly="true" />
</div>

<div class="fieldcontain ${hasErrors(bean: threadInstance?.firstPost, field: 'content', 'error')} ">
    <label for="content">
        <g:message code="post.content.label" default="Content" />
    </label>
    <g:textArea name="post.content" value="${threadInstance?.firstPost?.content}" rows="10" cols="50" placeholder="post.content.placeholder" />
</div>





