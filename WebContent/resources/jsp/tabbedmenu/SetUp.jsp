<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<img id="indicatorMainSetUpMenu" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<div class="setupmenu">
		<ul>
			<s:if test="#session.Part1_Menu == true">
				<li>
					<s:form action="MenuPart1">
						<sj:submit targets="setUpMenuDiv" indicator="indicatorMainSetUpMenu" key="MPart1"></sj:submit>
					</s:form>   
				</li>
			</s:if>
			<s:if test="#session.Part2_Menu == true">
				<li>
					<s:form action="MenuPart2">
						<sj:submit targets="setUpMenuDiv" indicator="indicatorMainSetUpMenu" key="MPart2"></sj:submit>
					</s:form>   
				</li>
			</s:if>
			<s:if test="#session.Part3_Menu == true">
				<li>
					<s:form action="MenuPart3">
						<sj:submit targets="setUpMenuDiv" indicator="indicatorMainSetUpMenu" key="MPart3"></sj:submit>
					</s:form>   
				</li>
			</s:if>
		</ul>
	</div>
	<div id="setUpMenuDiv" ></div>