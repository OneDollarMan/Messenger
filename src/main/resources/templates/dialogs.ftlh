<#import "parts/common.ftlh" as c>
<@c.page>
    <div id="dialog_create">
        <#if friends?has_content>
            <form method="post" action="/dialogs/create">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <select name="id">
                    <option selected disabled><@spring.message code="dialogs.sel"/></option>
                    <#list friends as f>
                        <option name="id" value="${f.id}">${f.username}</option>
                    </#list>
                </select>
                <input type="submit" value="<@spring.message code="dialogs.start"/>">
            </form>
        <#else>
            <@spring.message code="dialogs.create"/>
        </#if>
    </div>
    <#list dialogs as d>
        <#if d.secondUser??>
            <div class='div_dialog'>
                <#if d.creator != c.user>
                    <#assign u = d.creator>
                <#else>
                    <#assign u = d.secondUser>
                </#if>
                <a href='/dialogs/${u.id}'>${u.firstName} ${u.lastName}</a>
                <div class='div_dialog_exit'>
                    <a href="/dialogs/leave/${d.id}">X</a>
                </div>
            </div>
        </#if>
        <#else>
            <@spring.message code="dialogs.empty"/>
    </#list>
</@c.page>