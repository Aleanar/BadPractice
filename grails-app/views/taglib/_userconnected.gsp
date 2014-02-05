<p class="navbar-text">
${message(code:'site.user.hello',args:[session['user'].displayName])}
<g:link controller="user" action="show" id="${session['user'].id}">
    ${message(code:'site.user.seeprofile')}
</g:link>
</p>