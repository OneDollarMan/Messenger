<#import "parts/common.ftlh" as c>
<@c.page>
<form method='POST'>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type='text' name='username' placeholder='Username'>
    <input type='password' name='password' placeholder='Password'>
    <input type='submit'>
</form>
</@c.page>