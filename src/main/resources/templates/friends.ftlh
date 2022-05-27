<#import "parts/common.ftlh" as c>
<@c.page>
    <div id="friends_content">
        <div id="friends_add">
            <form method="get" action="/friends/sendFriendRequest">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="text" name="username" placeholder="<@spring.message code="friends.friendName"/>">
                <input type="submit" value="<@spring.message code="friends.add"/>">
            </form>
        </div>
        <div id="friends_columns">
            <div class="friends_column">
                <h3><@spring.message code="friends.friends"/></h3>
                <#list c.user.friends as f>
                    <div class="friends_cell">
                        <div class="friends_cell_main">
                            <a href="/profile/${f.id}">${f.firstName} ${f.lastName}</a>
                            <a href="/dialogs/${f.id}"><@spring.message code="friends.sendMessage"/></a>
                        </div>
                        <div class="friends_cell_func_div">
                            <a href="/friends/removeFriend/${f.id}">X</a>
                        </div>
                    </div>
                <#else>
                    <@spring.message code="friends.noFriends"/>
                </#list>
            </div>
            <div class="request_column">
                <div class="request_cell">
                    <h3><@spring.message code="friends.inp"/></h3>
                    <#list c.user.inFriendRequests as f>
                        <div class="friends_cell">
                            ${f.username}
                            <a href="/friends/acceptFriendRequest/${f.id}">V</a>
                            <a href="/friends/declineFriendRequest/${f.id}">X</a>
                        </div>
                    <#else>
                        <@spring.message code="friends.noInp"/>
                    </#list>
                </div>

                <div class="request_cell">
                    <h3><@spring.message code="friends.out"/></h3>
                    <#list c.user.outFriendRequests as f>
                        <div class="friends_cell">
                            ${f.username}
                            <a href="/friends/cancelFriendRequest/${f.id}">X</a>
                        </div>
                    <#else>
                        <@spring.message code="friends.noOut"/>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</@c.page>