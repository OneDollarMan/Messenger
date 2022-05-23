<#import "parts/common.ftlh" as c>
<@c.page>
    <#if u??>
        <#assign user = u>
    <#else>
        <#assign user = c.user>
    </#if>
    <div id="profile_div">
        <h1>Профиль ${user.username}</h1>
        <ul id="profile_ul">
            <li>
                <div class="profile_label">Никнейм: ${user.username}</div>
                <#if u??>
                    <#if u.friends?seq_contains(c.user)>
                        <a href="/friends/removeFriend/${u.id}">Удалить из друзей</a>
                    <#else>
                        <a href="/friends/sendFriendRequest?username=${u.username}">Добавить в друзья</a>
                    </#if>
                </#if>
            </li>
            <li>
                <div class="profile_label">Имя: ${user.firstName}</div>
                <#if !u??>
                <form method='post' action='/changeFirstName'>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type='text' name='firstName' placeholder='Новое имя'>
                    <input type='submit' value='Сохранить'>
                </form>
                </#if>
            </li>
            <li>
                <div class="profile_label">Фамилия: ${user.lastName}</div>
                <#if !u??>
                <form method='post' action='/changeLastName'>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type='text' name='lastName' placeholder='Новая фамилия'>
                    <input type='submit' value='Сохранить'>
                </form>
                </#if>
            </li>
        </ul>
    </div>
</@c.page>