<#import "parts/common.ftlh" as c>
<@c.page>
    <div id="dialog_control">
        <details>
            <summary>Добавить пользователя</summary>
            <form method="post" action="/dialogs/invite/${dialog.id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="text" placeholder="Имя пользователя" name="username">
                <input type="submit">
            </form>
        </details>
        Пользователи:
        <#list dialog.users as u>
            ${u.username}
        </#list>
    </div>
    <form id="sendMessageForm" method="post" action="/dialogs/sendMessage/${dialog.id}" onsubmit="sendMessage();return false">
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