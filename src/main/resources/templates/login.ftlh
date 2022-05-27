<#import "parts/common.ftlh" as c>
<@c.page>
<h1><@spring.message code="login.entry"/></h1>
<form method='POST' id="form_row">
    <input class="input_row" type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input class="input_row" type='text' name='username' placeholder='<@spring.message code="login.username"/>'>
    <input class="input_row" type='password' name='password' placeholder='<@spring.message code="login.password"/>'>
    <input class="input_row" type='submit' value="<@spring.message code="login.enter"/>">
</form>
</@c.page>