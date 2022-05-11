<#import "parts/common.ftlh" as c>
<@c.page>
    <div id="dialog_create">
        <#if friends?has_content>
            <form method="post" action="/dialogs/create">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <select>
                    <#list friends as f>
                        <option value="${f.id}">${f.username}</option>
                    </#list>
                </select>
                <input type="submit">
            </form>
        <#else>
            Для создания диалога добавьте друзей!
        </#if>
    </div>
    <#list dialogs as d>
        <div class='div_dialog'>
            <a href='/dialogs/${d.id}'>${d.id}</a>
            <a href="/dialogs/leave/${d.id}">Выйти</a>
            <#if d.creator.id == c.userId>
                <a href='/dialogs/rm/${d.id}'>Удалить</a>
            </#if>
        </div>
        <#else>
            Диалогов нет!
    </#list>
</@c.page>