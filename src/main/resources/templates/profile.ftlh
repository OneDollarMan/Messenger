<#import "parts/common.ftlh" as c>
<@c.page>
    <#if u??>
        <#assign user = u>
    <#else>
        <#assign user = c.user>
    </#if>
    <div id="profile_div">
        <h1><@spring.message code="profile.profile"/> ${user.username}</h1>
        <ul id="profile_ul">
            <li>
                <div class="profile_label"><@spring.message code="profile.username"/>: ${user.username}</div>
                <#if u??>
                    <#if u.friends?seq_contains(c.user)>
                        <a href="/friends/removeFriend/${u.id}"><@spring.message code="profile.removeFriend"/></a>
                    <#else>
                        <a href="/friends/sendFriendRequest?username=${u.username}"><@spring.message code="profile.addFriend"/></a>
                    </#if>
                </#if>
            </li>
            <li>
                <div class="profile_label"><@spring.message code="profile.name"/>: ${user.firstName}</div>
                <#if !u??>
                <form method='post' action='/changeFirstName'>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type='text' name='firstName' placeholder='<@spring.message code="profile.newName"/>'>
                    <input type='submit' value='<@spring.message code="profile.save"/>'>
                </form>
                </#if>
            </li>
            <li>
                <div class="profile_label"><@spring.message code="profile.lastName"/>: ${user.lastName}</div>
                <#if !u??>
                <form method='post' action='/changeLastName'>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type='text' name='lastName' placeholder='<@spring.message code="profile.newLastName"/>'>
                    <input type='submit' value='<@spring.message code="profile.save"/>'>
                </form>
                </#if>
            </li>
        </ul>
    </div>
</@c.page>