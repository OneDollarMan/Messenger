<#import "parts/common.ftlh" as c>
<@c.page>
    <div id="friends_content">
        <div id="friends_add">
            <form method="post" action="/friends/sendFriendRequest">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="text" name="username" placeholder="Имя друга">
                <input type="submit" value="Добавить">
            </form>
        </div>
        <div id="friends_columns">
            <div class="friends_column">
                <h3>Друзья</h3>
                <#list c.user.friends as f>
                    <div class="friends_cell">
                        ${f.username}
                        <a href="/friends/removeFriend/${f.id}">X</a>
                    </div>
                <#else>
                    Друзей нет
                </#list>
            </div>
            <div class="friends_column">
                <h3>Входящие заявки</h3>
                <#list c.user.inFriendRequests as f>
                    <div class="friends_cell">
                        ${f.username}
                        <a href="/friends/acceptFriendRequest/${f.id}">V</a>
                        <a href="/friends/declineFriendRequest/${f.id}">X</a>
                    </div>
                <#else>
                    Входящих заявок нет
                </#list>
            </div>
            <div class="friends_column">
                <h3>Исходящие заявки</h3>
                <#list c.user.outFriendRequests as f>
                    <div class="friends_cell">
                        ${f.username}
                        <a href="/friends/cancelFriendRequest/${f.id}">X</a>
                    </div>
                <#else>
                    Исходящих заявок нет
                </#list>
            </div>
        </div>
    </div>
</@c.page>