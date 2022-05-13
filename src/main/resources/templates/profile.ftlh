<#import "parts/common.ftlh" as c>
<@c.page>

    <div id="profile_div">
        <ul>
            <#if u??>
                <#assign user = u>
            <#else>
                <#assign user = c.user>
            </#if>
            <li>Никнейм: ${user.username}</li>
            <li>Имя: ${user.firstName}</li>
            <li>Фамилия: ${user.lastName}</li>
        </ul>
    </div>
</@c.page>