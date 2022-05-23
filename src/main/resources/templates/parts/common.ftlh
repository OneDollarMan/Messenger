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
			<ul>
				<li>
					<a href='/'>Главная</a>
				</li>
				<#if roles?? && roles?seq_contains("ROLE_ADMIN")>
					<li>
						<a href="/admin" class='header_a'>Админ-панель</a>
					</li>
				</#if>

				<#if user??>
					<li>
						<a href='/profile'>Профиль</a>
					</li>
					<li>
						<a href='/friends'>Друзья</a>
					</li>
					<li>
						<a href='/dialogs'>Диалоги</a>
					</li>
					<li>
						<a href='/rooms'>Групповые комнаты</a>
					</li>
					<li>
						<form method='post' action='/logout'>
							<input type="hidden" name="_csrf" value="${_csrf.token}"/>
							<button class='header_a header_button'>Выйти</button>
						</form>
					</li>
				<#else>
					<li>
						<a href='/register'>Регистрация</a>
					</li>
					<li>
						<a href='/login'>Авторизация</a>
					</li>
				</#if>
			</ul>
	    </header>
	    <#if message??>
        	<div class="message">${message}</div>
        </#if>
		<div id="content">
			<#nested>
		</div>
	</body>
</html>
</#macro>