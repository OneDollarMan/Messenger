<#import "parts/common.ftlh" as c>
<@c.page>
    <#list rooms as r>
        <a href='/rooms/${r.id}'>${r.title}</a>
    </#list>
</@c.page>