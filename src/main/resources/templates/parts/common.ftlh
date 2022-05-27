<#macro page>
<#include "security.ftlh">
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title><@spring.message code="common.title"/></title>
		<link rel="stylesheet" type="text/css" href="../style.css">
	</head>
	<body>
	    <header>
			<ul>
				<li>
					<a href='/'><@spring.message code="common.index"/></a>
				</li>
				<#if roles?? && roles?seq_contains("ROLE_ADMIN")>
					<li>
						<a href="/admin" class='header_a'><@spring.message code="common.admin"/></a>
					</li>
				</#if>

				<#if user??>
					<li>
						<a href='/profile'><@spring.message code="common.profile"/></a>
					</li>
					<li>
						<a href='/friends'><@spring.message code="common.friends"/></a>
					</li>
					<li>
						<a href='/dialogs'><@spring.message code="common.dialogs"/></a>
					</li>
					<li>
						<a href='/rooms'><@spring.message code="common.rooms"/></a>
					</li>
					<li>
						<form method='post' action='/logout'>
							<input type="hidden" name="_csrf" value="${_csrf.token}"/>
							<button class='header_a header_button'><@spring.message code="common.exit"/></button>
						</form>
					</li>
				<#else>
					<li>
						<a href='/register'><@spring.message code="common.register"/></a>
					</li>
					<li>
						<a href='/login'><@spring.message code="common.auth"/></a>
					</li>
				</#if>
			</ul>
			<ul>
				<li>
					<a href='/?lang=en'>English</a>
				</li>
				<li>
					<a href='/?lang=ru'>Русский</a>
				</li>
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