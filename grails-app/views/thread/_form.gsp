<%@ page import="fr.isima.Thread" %>

<g:form action="save" class="form-horizontal" role="form">

    <fieldset class="form">

        <div class="form-group ${hasErrors(bean: threadInstance, field: 'title', 'error')}">
            <label for="title" class="col-sm-2 control-label">
                <g:message code="thread.title.label" default="Title" />
            </label>
            <div class="col-sm-10">
                <g:textField id="title" name="thread.title" class="form-control" value="${threadInstance?.title}" placeholder="thread.title.placeholder" />
            </div>
        </div>

        <div class="form-group">

            <label for="tag-name-auto" class="col-sm-2 control-label">
                <g:message code="thread.tags.label" default="Tags" />
            </label>
            <div class="col-sm-10">
                <g:textField name="tag-name-auto" class="form-control" value="" placeholder="thread.tags.placeholder" />
            </div>
        </div>

        <div class="form-group ${hasErrors(bean: threadInstance?.firstPost, field: 'content', 'error')}">
            <label for="content" class="col-sm-2 control-label">
                <g:message code="post.content.label" default="Content" />
            </label>
            <div class="col-sm-10">
                <g:textArea id="content" class="form-control" name="post.content" value="${threadInstance?.firstPost?.content}" rows="10" cols="50" placeholder="post.content.placeholder" />
            </div>
        </div>

    </fieldset>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </div>
    </div>

</g:form>






