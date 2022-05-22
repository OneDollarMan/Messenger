<#import "parts/common.ftlh" as c>
<@c.page>
        <details>
            <summary>Добавить пользователя</summary>
            <form method="post" action="/dialogs/invite/${dialog.id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="text" placeholder="Имя пользователя" name="username">
                <input type="submit">
            </form>
        </details>
</@c.page>