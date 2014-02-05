<g:if test="${currentUser}">

    <div class="pull-right">
    <g:link controller="user" action="show" id="${currentUser.id}" class="btn btn-lg" role="button">
            <div class="pull-left" style="margin-right: 10px;text-align:right;">
                <div style="font-size:0.5em;">
                    <g:fieldValue bean="${currentUser}" field="displayName"/>
                </div>
                <div style="font-size:0.5em;">
                    <g:badges userId="${currentUser.id}"/>
                </div>
            </div>
            <div class=""><avatar:gravatar email="${currentUser.mail}" alt="Avatar"/></div>
    </g:link>
    </div>
    <div class="clearfix"></div>

</g:if>