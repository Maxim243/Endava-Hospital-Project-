<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<h3><div class="mb-1">New user:</div></h3>
${message?ifExists}
</br>
<@l.login "/registration" true />
</@c.page>
