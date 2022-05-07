<#macro page>
<#include "security.ftlh">
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Мессенджер</title>
		<link rel="stylesheet" type="text/css" href="../style.css">
	</head>
	<body>
	    <header>
	        <a href='/'>Главная</a>
	        <#if roles?? && roles?seq_contains("ROLE_ADMIN")>
            	<a href="/admin" class='header_a'>Админ-панель</a>
            </#if>
            <#if user??>
                <a href='/dialogs'>Диалоги</a>
                <form method='post' action='/logout'>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                	<button class='header_a header_button'>Выйти</button>
                </form>
            <#else>
                <a href='/register'>Регистрация</a>
                <a href='/login'>Авторизация</a>
            </#if>
	    </header>
	    <#if message??>
        	<div class="message">${message}</div>
        </#if>
		<#nested>
	</body>
</html>
</#macro>