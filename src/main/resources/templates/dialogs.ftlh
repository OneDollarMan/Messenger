<#import "parts/common.ftlh" as c>
<@c.page>
    <div id="dialog_create">
        <#if friends?has_content>
            <form method="post" action="/dialogs/create">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <select name="id">
                    <option selected disabled>Выберите друга</option>
                    <#list friends as f>
                        <option name="id" value="${f.id}">${f.username}</option>
                    </#list>
                </select>
                <input type="submit" value="Начать диалог">
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