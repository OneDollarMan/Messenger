<#import "parts/common.ftlh" as c>
<@c.page>
<h1>Вход</h1>
<form method='POST' id="form_row">
    <input class="input_row" type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input class="input_row" type='text' name='username' placeholder='Никнейм'>
    <input class="input_row" type='password' name='password' placeholder='Пароль'>
    <input class="input_row" type='submit' value="Войти">
</form>
</@c.page>