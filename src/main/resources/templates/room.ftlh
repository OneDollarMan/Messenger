<#import "parts/common.ftlh" as c>
<@c.page>
<div id='dialog' value='${dialog.id}'>
    <div id='messages_div_form'>
        <form id="sendMessageForm" method="post" action="/dialogs/sendMessage/${dialog.id}" onsubmit="sendMessage(sendMessageForm); return false">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input id='message_input' type="textField" placeholder="<@spring.message code="room.mess"/>" name="message">
            <input type="submit" value="<@spring.message code="room.send"/>">
        </form>
    </div>
    <div id="messages_div">
        <#list dialog.messages as m>
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
            <@spring.message code="room.noMess"/>
        </#list>

    </div>

    <div id="dialog_control">
        <ul id='dialog_ul'>
            <@spring.message code="room.users"/>:
        <#list dialog.users as u>
            <li>
                <a href='/profile/${u.id}'>${u.firstName}</a>
            </li>
        </#list>
        </ul>
        <div id='dialog_control_actions'>
            <details>
                <summary><@spring.message code="room.addUser"/></summary>
                <form method="post" action="/rooms/invite/${dialog.id}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <select name="username">
                        <option selected disabled><@spring.message code="room.selectFriend"/></option>
                        <#list c.user.friends as f>
                            <option name="username" value="${f.username}">${f.firstName} ${f.lastName}</option>
                        </#list>
                    </select>
                    <input type="submit" value="<@spring.message code="room.add"/>">
                </form>
            </details>
            <a href="/dialogs/leave/${dialog.id}"><@spring.message code="room.exit"/></a>
            <#if dialog.creator == c.user>
                <form method="post" action="/rooms/setClose/${dialog.id}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <#if dialog.closed>
                        <input type="radio" id="opened" name="isClosed" value="False">
                        <label for="opened"><@spring.message code="room.opened"/></label>
                        <input type="radio" id="closed" name="isClosed" value="True" checked>
                        <label for="closed"><@spring.message code="room.closed"/></label>
                    <#else>
                        <input type="radio" id="opened" name="isClosed" value="False" checked>
                        <label for="opened"><@spring.message code="room.opened"/></label>
                        <input type="radio" id="closed" name="isClosed" value="True">
                        <label for="closed"><@spring.message code="room.closed"/></label>
                    </#if>
                    <input type="submit" value="<@spring.message code="room.save"/>">
                </form>
                <a href='/dialogs/rm/${dialog.id}'><@spring.message code="room.remove"/></a>
            </#if>
        </div>
    </div>
    <script src="/script.js"></script>
</div>
</@c.page>