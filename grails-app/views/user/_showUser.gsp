<g:if test="${currentUser}">
    <g:link controller="user" action="show" id="${currentUser.id}">
        <div id="displayUser" style="border: 1px red solid">
        <avatar:gravatar email="${currentUser.mail}" alt="Avatar" size="40"/>
        <%--<g:img uri="${currentUser.pathToAvatar}" width="40" height="40" />--%>
    <br />
    <span class="property-value" name="diplayName"><g:fieldValue bean="${currentUser}" field="displayName"/></span><br />
    <span class="property-value" name="rank"><g:fieldValue bean="${currentUser}" field="rank"/></span><br />
</div>
</g:link>
</g:if>