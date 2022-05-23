<#import "parts/common.ftlh" as c>
<@c.page>
<h1>Регистрация</h1>
<form method='POST' id="form_row">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input class="input_row" type='text' name='username' placeholder='Никнейм'>
    <input class="input_row" type='email' name='email' placeholder='Email'>
    <input class="input_row" type='text' name='firstName' placeholder='Имя'>
    <input class="input_row" type='text' name='lastName' placeholder='Фамилия'>
    <input class="input_row" type='password' name='password' placeholder='Пароль'>
    <input class="input_row" type='submit' value="Зарегистрироваться">
</form>
</@c.page>