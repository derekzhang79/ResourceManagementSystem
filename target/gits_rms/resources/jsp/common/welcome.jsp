<%@taglib prefix="s" uri="/struts-tags" %>
<s:if test="#session.USER_NAME==null">
</s:if>
<s:else>
	<table>
		<tr>
			<td align="left" style="font: bold; color: blue">
				Welcome <s:text name="#session.FIRST_NAME"></s:text>&nbsp;<s:text name="#session.LAST_NAME"></s:text>
			</td>
		</tr>
		<tr>
			<td align="left" style="font: bold; color: blue">
				Your Role: <s:text name="#session.ROLE"></s:text>
			</td>
		</tr>
	</table>	
</s:else>