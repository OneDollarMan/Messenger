<#import "parts/common.ftlh" as c>
<@c.page>
<form method='POST'>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type='text' name='username' placeholder='Никнейм'>
    <input type='text' name='email' placeholder='Email'>
    <input type='text' name='firstName' placeholder='Имя'>
    <input type='text' name='lastName' placeholder='Фамилия'>
    <input type='password' name='password' placeholder='Пароль'>
    <input type='submit'>
</form>
</@c.page>