<#import "parts/common.ftlh" as c>
<@c.page>
    <div id='rooms_div'>
        <div id='friends_add'>
            <form method='post' action='/rooms/create'>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type='text' name='title' placeholder='Заголовок'>
                <input type='submit' value="Добавить">
            </form>
        </div>
        <div id='rooms_lists_div'>
            <div id='joined_rooms_div'>
                <h3>Ваши комнаты</h3>
                <#list joined_rooms as r>
                    <div class='div_dialog'>
                        <a href='/rooms/${r.id}'>${r.title}</a>
                    </div>
                </#list>
            </div>

            <div id='open_rooms_div'>
                <h3>Открытые комнаты</h3>
                <#list open_rooms as r>
                    <div class='div_dialog'>
                        <a href='/rooms/${r.id}'>${r.title}</a>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</@c.page>