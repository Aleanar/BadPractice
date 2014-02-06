<%@ page import="fr.isima.Post" %>

<g:hiddenField id="thread" name="thread.id" value="${threadInstance.id}" />

<div class="form-group ${hasErrors(bean: postInstance, field: 'content', 'error')} ">
    <label for="content">
        <h3><g:message for="post-editor" code="thread.answer.create.label" default="Answer" /></h3>
    </label>
    <g:textArea id="post-editor" class="form-control" name="content" value="${postInstance?.content}" rows="30" cols="50" placeholder="post.content.placeholder" />
</div>


