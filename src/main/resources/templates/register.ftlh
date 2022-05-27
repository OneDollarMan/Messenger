<#import "parts/common.ftlh" as c>
<@c.page>
<h1><@spring.message code="register.register"/></h1>
<form method='POST' id="form_row">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input class="input_row" type='text' name='username' placeholder='<@spring.message code="register.username"/>'>
    <input class="input_row" type='email' name='email' placeholder='Email'>
    <input class="input_row" type='text' name='firstName' placeholder='<@spring.message code="register.name"/>'>
    <input class="input_row" type='text' name='lastName' placeholder='<@spring.message code="register.lastName"/>'>
    <input class="input_row" type='password' name='password' placeholder='<@spring.message code="register.password"/>'>
    <input class="input_row" type='submit' value="<@spring.message code="register.signin"/>">
</form>
</@c.page>