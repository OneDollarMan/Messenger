<#import "parts/common.ftlh" as c>
<@c.page>
    <div id="dialog_control">
        Пользователи:
        <#list dialog.users as u>
            ${u.username}
        </#list>
    </div>
    <form id="sendMessageForm" method="post" action="/dialogs/sendMessage/${dialog.id}" onsubmit="sendMessage(sendMessageForm)">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" placeholder="Сообщение" name="message">
        <input type="submit" value="Отправить">
    </form>
    <#list messages as m>
        <div class='message'>
            (${m.date}) ${m.author.username} - ${m.text}
        </div>
    <#else>
        Сообщений нет!
    </#list>
</@c.page>