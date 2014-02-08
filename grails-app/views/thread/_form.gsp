<%@ page import="fr.isima.Thread" %>

<div class="form-group ${hasErrors(bean: threadInstance, field: 'title', 'error')}">
    <label for="title" class="col-sm-2 control-label">
        <g:message code="thread.title.label" default="Title" />
    </label>
    <div class="col-sm-10">
        <g:textField id="title" name="thread.title" class="form-control" value="${threadInstance?.title}" placeholder="${message(code: 'thread.title.placeholder', default: 'thread.title.placeholder')}" />
    </div>
</div>

<div class="form-group">

    <label for="tag-name-auto" class="col-sm-2 control-label">
        <g:message code="thread.tags.label" default="Tags" />
    </label>
    <div class="col-sm-10">
        <g:textField name="tag-name-auto" class="form-control" value="" placeholder="${message(code: 'thread.tags.placeholder', default: 'thread.tags.placeholder')}" /><br/>
        <g:if test="${tagsEnregistred}" >
            <g:message code="thread.tags.already.enregistred" default="Tags : " />
            <g:each in="${tagsEnregistred}" var="tag">
                ${tag.name}
            </g:each>
        </g:if>
    </div>
</div>

<div class="form-group ${hasErrors(bean: threadInstance?.firstPost, field: 'content', 'error')}">
    <label for="content" class="col-sm-2 control-label">
        <g:message code="post.content.label" default="Content" />
    </label>
    <div class="col-sm-10">
        <ckeditor:editor id="content" name="post.content" height="300px" width="100%">
            ${threadInstance?.firstPost?.content}
        </ckeditor:editor>
    </div>
</div>






