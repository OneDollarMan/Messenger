<#import "parts/common.ftlh" as c>
<@c.page>
    <a href='/dialogs/create'>Создать диалог</a>
    <#list dialogs as d>
        <div class='div_dialog'>
            <a href='/dialogs/${d.id}'>${d.id}</a>
            <a href='/dialogs/rm/${d.id}'>Удалить</a>
        </div>
        <#else>
            Диалогов нет!
    </#list>
</@c.page>