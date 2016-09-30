<%@ page contentType="text/html; charset=UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	
<div id="submenu_Videos_List_div">
	<table class="maintable">
		<tr align="center">
			<td class="formtitle">
				<s:text name="label.title.videos"/>
			</td>
		</tr>
		<tr>
			<td class="employeedisplaytd">
				<a title="Complete Module" class="vidbox2" href="http://www.youtube.com/watch?v=gnB7onMvLp8" target="submenu_Videos_List_div"></a>
			</td>
			<td class="employeedisplaytd">
				<a title="Projects" class="vidbox2" href="http://www.youtube.com/watch?v=-jeuYrBLNT0" target="submenu_Videos_List_div" ></a>
			</td>
			<td class="employeedisplaytd">
				<a title="TimeSheet" class="vidbox2" href="http://www.youtube.com/watch?v=J_STh19uD4c" target="submenu_Videos_List_div"></a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="employeedisplaytd"><s:text name="label.title.completeModules"/>
			<td class="employeedisplaytd"><s:text name="label.header.project.name"/>
			<td class="employeedisplaytd"><s:text name="label.header.timeSheet.name"/>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="employeedisplaytd">
				<a title="Leave Module" class="vidbox2" href="http://www.youtube.com/watch?v=X7833IbP4ro" target="submenu_Videos_List_div"></a>
			</td>
			<td class="employeedisplaytd">
				<a title="Expenses" class="vidbox2" href="http://www.youtube.com/watch?v=kSep2TmClIo" target="submenu_Videos_List_div"></a>
			</td>
			<td class="employeedisplaytd">
				<a title="Employee Space" class="vidbox2" href="http://www.youtube.com/watch?v=DcAJnnb6Y6Y" target="submenu_Videos_List_div"></a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="employeedisplaytd"><s:text name="label.title.Leave.list"/>
			<td class="employeedisplaytd"><s:text name="label.title.empexpenses.list"/>
			<td class="employeedisplaytd"><s:text name="label.title.empSpaceModule"/>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="employeedisplaytd">
				<a title="Ess" class="vidbox2" href="http://www.youtube.com/watch?v=hg-3MJrbNYQ" target="submenu_Videos_List_div"></a>
			</td>
			<td class="employeedisplaytd">
				<a title="Messaging" class="vidbox2" href="http://www.youtube.com/watch?v=_L3_T1xm5KE" target="submenu_Videos_List_div"></a>
			</td>
			<td class="employeedisplaytd">
				<a title="Reports" class="vidbox2" href="http://www.youtube.com/watch?v=MWCsb5U5UK0" target="submenu_Videos_List_div"></a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="employeedisplaytd"><s:text name="label.header.common.ess"/>
			<td class="employeedisplaytd"><s:text name="label.header.common.messaging"/>
			<td class="employeedisplaytd"><s:text name="label.header.common.reports"/>
		</tr>
		<tr>
			<td class="employeedisplaytd">
				<a title="Ess" class="vidbox2" href="http://www.youtube.com/watch?v=Ajp5aQ1DwPo" target="submenu_Videos_List_div"></a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="employeedisplaytd"><s:text name="label.header.common.orgChart"/>
			</td>
		</tr>
	</table>
</div>

<script>
	$(document).ready(function() {
		try{
			$(".vidbox2").jqvideobox({
				'width' : 600,
				'height' : 400,
				'getimage' : true,
				'navigation' : true
			});
		}catch(e){
			
		}
		
	});
</script>