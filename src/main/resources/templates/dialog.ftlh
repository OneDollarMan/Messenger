<#import "parts/common.ftlh" as c>
<@c.page>
    <form method="post" action="/dialogs/sendMessage/${dialog.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" placeholder="Сообщение" name="message">
        <input type="submit">
    </form>
    <#list messages as m>
        <div class='message'>
            (${m.date}) ${m.author.username} - ${m.text}
        </div>
    <#else>
        Сообщений нет!
    </#list>
</@c.page>