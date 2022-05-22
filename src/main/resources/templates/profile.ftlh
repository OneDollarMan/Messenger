<#import "parts/common.ftlh" as c>
<@c.page>

    <div id="profile_div">
        <ul id="profile_ul">
            <#if u??>
                <#assign user = u>
                <a href="/friends/sendFriendRequest?username=${u.username}"><button>Добавить в друзья</button></a>
            <#else>
                <#assign user = c.user>
            </#if>
            <li>
                <div class="profile_label">Никнейм: ${user.username}</div>
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