<#import "parts/common.ftlh" as c>
<@c.page>
<div id='dialog'>
    <div id='messages_div_form'>
        <form id="sendMessageForm" method="post" action="/dialogs/sendMessage/${dialog.id}" onsubmit="sendMessage(sendMessageForm); return false">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input id='message_input' type="textField" placeholder="Сообщение" name="message">
            <input type="submit" value="Отправить">
        </form>
    </div>
    <div id="messages_div">
        <#list messages as m>
            <div class='message'>
                 <div class='message_inf_block'>
                    ${m.author.firstName}
                    <div class='message_inf_date'>
                        ${m.date}
                    </div>
                 </div>
                 <div class='message_text_block'>
                    ${m.text}
                 </div>
            </div>
        <#else>
            Сообщений нет!
        </#list>

    </div>

    <div id="dialog_control">
        Пользователи:
        <#list dialog.users as u>
            <a href='/profile/${u.id}'>${u.firstName}</a>
        </#list>
    </div>
</div>
</@c.page>