<%@ page import="fr.isima.Thread" %>

<div class="fieldcontain ${hasErrors(bean: threadInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="thread.title.label" default="Title" />
	</label>
	<g:textField name="thread.title" value="${threadInstance?.title}"/>
</div>

<div class="fieldcontain">
    <label for="tags">
        <g:message code="thread.tags.label" default="Tags" />
    </label>
    <g:textField name="tag.name" value=""/>

</div>

<div class="fieldcontain" >
    <label for="author">
        <g:message code="post.author.label" default="Author" />
    </label>
    <g:textField name="user.displayName" value="Put the user's name here" readonly="true" />
    <comment                             value="${threadInstance?.firstPost?.author?.displayName}" />
</div>

<div class="fieldcontain ${hasErrors(bean: threadInstance?.firstPost, field: 'content', 'error')} ">
    <label for="content">
        <g:message code="post.content.label" default="Content" />

    </label>
    <g:textArea name="post.content" value="${threadInstance?.firstPost?.content}" rows="10" cols="50" />
</div>





