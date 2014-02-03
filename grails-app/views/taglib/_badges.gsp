<b>${reputation}</b> -
<g:if test="${rookieBadgesNumber != 0}">
    ${rookieBadgesNumber}<span class="glyphicon glyphicon-record" style="color:red;"></span>
</g:if>
<g:if test="${amateurBadgesNumber != 0}">
    ${amateurBadgesNumber}<span class="glyphicon glyphicon-record" style="color:blue;"></span>
</g:if>
<g:if test="${ultimateBadgesNumber != 0}">
    ${ultimateBadgesNumber}<span class="glyphicon glyphicon-record" style="color:green;"></span>
</g:if>