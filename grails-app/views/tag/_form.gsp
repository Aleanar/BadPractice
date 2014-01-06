<%@ page import="fr.isima.Tag" %>



<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="tag.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${tagInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'threads', 'error')} ">
	<label for="threads">
		<g:message code="tag.threads.label" default="Threads" />
		
	</label>
	<g:select name="threads" from="${fr.isima.Thread.list()}" multiple="multiple" optionKey="id" size="5" value="${tagInstance?.threads*.id}" class="many-to-many"/>
</div>

