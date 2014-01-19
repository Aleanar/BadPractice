<g:hasErrors bean="${tagInstance}">
    <ul class="errors" role="alert">
        <g:eachError bean="${tagInstance}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<ul class="tagsList">
    <g:each in="${tagsList}" var="tag">
        <li >${fieldValue(bean: tag, field: "name")} - ${tag.threads ? tag.threads.size() : 0}</li>
    </g:each>
</ul>