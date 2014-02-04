<%@ page import="fr.isima.Post" %>

<g:hiddenField id="thread" name="thread.id" value="${threadInstance.id}" />

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'content', 'error')} ">
    <label for="content">
        <g:message code="post.content.label" default="Content" />
    </label>
    <g:textArea name="content" value="${postInstance?.content}" rows="10" cols="50" placeholder="post.content.placeholder" />
</div>

