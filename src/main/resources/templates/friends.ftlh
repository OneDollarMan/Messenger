<#import "parts/common.ftlh" as c>
<@c.page>
    <div id="friends_content">
        <div id="friends_add">
            <form method="post" action="/friends/sendFriendRequest">
                <input type="text" name="username" placeholder="Имя друга">
                <input type="submit" value="Добавить">
            </form>
        </div>
        <div id="friends_columns">
            <div class="friends_column">
                <h3>Друзья</h3>
                <#list c.user.friends as f>
                    <div class="friends_cell">${f.username}</div>
                <#else>
                    Друзей нет
                </#list>
            </div>
            <div class="friends_column">
                <h3>Входящие заявки</h3>
                <#list c.user.inFriendRequests as f>
                    <div class="friends_cell">${f.username}</div>
                <#else>
                    Входящих заявок нет
                </#list>
            </div>
            <div class="friends_column">
                <h3>Исходящие заявки</h3>
                <#list c.user.outFriendRequests as f>
                    <div class="friends_cell">${f.username}</div>
                <#else>
                    Исходящих заявок нет
                </#list>
            </div>
        </div>
    </div>
</@c.page>