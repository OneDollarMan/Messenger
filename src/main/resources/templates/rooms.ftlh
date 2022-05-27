<#import "parts/common.ftlh" as c>
<@c.page>
    <div id='rooms_div'>
        <div id='friends_add'>
            <form method='post' action='/rooms/create'>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type='text' name='title' placeholder='<@spring.message code="rooms.title"/>'>
                <input type='submit' value="<@spring.message code="rooms.add"/>">
            </form>
        </div>
        <div id='rooms_lists_div'>
            <div id='joined_rooms_div'>
                <h3><@spring.message code="rooms.rooms"/></h3>
                <#list joined_rooms as r>
                    <div class='div_dialog'>
                        <a href='/rooms/${r.id}'>${r.title}</a>
                    </div>
                </#list>
            </div>

            <div id='open_rooms_div'>
                <h3><@spring.message code="rooms.openedRooms"/></h3>
                <#list open_rooms as r>
                    <div class='div_dialog'>
                        <a href='/rooms/${r.id}'>${r.title}</a>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</@c.page>