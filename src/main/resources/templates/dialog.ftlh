<#import "parts/common.ftlh" as c>
<@c.page>
<div id='dialog' value='${dialog.id}'>
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
                    <a href='/profile/${m.author.id}'>${m.author.firstName}</a>
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
        <ul id='dialog_ul'>
        Пользователи:
        <#list dialog.users as u>
            <li>
                <a href='/profile/${u.id}'>${u.firstName}</a>
            </li>
        </#list>
        </ul>
        <div id='dialog_control_actions'>
            <a href="/dialogs/leave/${dialog.id}">Выйти</a>
            <#if dialog.creator == c.user>
                <a href='/dialogs/rm/${dialog.id}'>Удалить</a>
            </#if>
        </div>
    </div>
    <script src="/script.js"></script>
</div>
</@c.page>