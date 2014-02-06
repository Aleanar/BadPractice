<%@ page import="fr.isima.Post" %>

<g:hiddenField id="thread" name="thread.id" value="${threadInstance.id}" />

<div class="form-group ${hasErrors(bean: postInstance, field: 'content', 'error')} ">
    <g:textArea id="comment-editor" class="form-control" name="content" value="${postInstance?.content}" rows="5" cols="40" placeholder="post.content.placeholder" />
</div>


