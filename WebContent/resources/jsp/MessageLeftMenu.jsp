<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<img id="indicatorMessagingLeftPannel" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
<table style="width: 100%;">
	<tr>
  		<td class="messagingLeftPannel">
			<div id="leftPannelMessagingMenu">
				<li>
					<s:url var="setUpMessage" action="setUpMessage"/>		
					<sj:a href="%{setUpMessage}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIMessage"/></sj:a>
	   		    </li>
	   		    <s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
					<li>
						<s:url var="setUpBroadcastMessage" action="setUpBroadcastMessage"/>		
						<sj:a href="%{setUpBroadcastMessage}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIBroadcast"/></sj:a>
					</li>
				</s:if>
	   		   <li>
					<s:url var="getAllMyMessage" action="getAllMyMessage"/>		
					<sj:a href="%{getAllMyMessage}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIInbox"/>&nbsp;(<s:text name="#session.MESSAGE_IN"/>)</sj:a>
					
					<ul>
						<li>
							<s:url var="getAllMyInAlert" action="getAllMyInAlert"/>		
							<sj:a href="%{getAllMyInAlert}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIInAlert"/>&nbsp;(<s:text name="#session.MESSAGE_IN_ALERT"/>)</sj:a>
							<ul>
								<li>
									<s:url var="getAllUnOpenedAlertMails" action="getAllUnOpenedAlertMails"/>		
									<sj:a href="%{getAllUnOpenedAlertMails}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIInAlertUnOpenMails"/>&nbsp;(<s:text name="#session.MESSAGE_IN_ALERT_UNOPENED"/>)</sj:a>
								</li>
								<li>
									<s:url var="getAllOpenedAlertMails" action="getAllOpenedAlertMails"/>		
									<sj:a href="%{getAllOpenedAlertMails}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIInAlertOpenMails"/>&nbsp;(<s:text name="#session.MESSAGE_IN_ALERT_OPENED"/>)</sj:a>
								</li>
							</ul>
						</li>
						<li>
							<s:url var="getAllMyInMessage" action="getAllMyInMessage"/>		
							<sj:a href="%{getAllMyInMessage}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIInMessage"/>&nbsp;(<s:text name="#session.MESSAGE_IN_MESSAGE"/>)</sj:a>
							
								<ul>
									<li>
										<s:url var="getAllUnOpenedComposeMails" action="getAllUnOpenedComposeMails"/>		
										<sj:a href="%{getAllUnOpenedComposeMails}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIInComposeUnOpenMails"/>&nbsp;(<s:text name="#session.MESSAGE_IN_COMPOSE_UNOPENED"/>)</sj:a>
									</li>
									<li>
										<s:url var="getAllOpenedComposeMails" action="getAllOpenedComposeMails"/>		
										<sj:a href="%{getAllOpenedComposeMails}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIInComposeOpenMails"/>&nbsp;(<s:text name="#session.MESSAGE_IN_COMPOSE_OPENED"/>)</sj:a>
									</li>
								</ul>
						</li>
						<li>
							<s:url var="getAllBroadcastMessage" action="getAllBroadcastMessage"/>		
							<sj:a href="%{getAllBroadcastMessage}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIInBroadcast"/>&nbsp;(<s:text name="#session.MESSAGE_IN_BROADCAST"/>)</sj:a>
								<ul>
									<li>
										<s:url var="getAllUnOpenedBroadcastMails" action="getAllUnOpenedBroadcastMails"/>		
										<sj:a href="%{getAllUnOpenedBroadcastMails}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIInBroadcastUnOpenMails"/>&nbsp;(<s:text name="#session.MESSAGE_IN_BROADCAST_UNOPENED"/>)</sj:a>
									</li>
									<li>
										<s:url var="getAllOpenedBroadcastMails" action="getAllOpenedBroadcastMails"/>		
										<sj:a href="%{getAllOpenedBroadcastMails}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIInBroadcastOpenMails"/>&nbsp;(<s:text name="#session.MESSAGE_IN_BROADCAST_OPENED"/>)</sj:a>
									</li>
								</ul>
						</li>		
					</ul>
				</li>
				<li>
					<s:url var="broadcastMsgSearchForm" action="broadcastMsgSearchForm"/>		
					<sj:a href="%{broadcastMsgSearchForm}" targets="righePannelMessagingMenu" indicator="indicatorMessagingLeftPannel"><s:text name="MTIMessageSearch"/></sj:a>
				</li>
			</div>   	
  		</td>
  		<td style="width: 70%;">
  			<s:url var="getMessagingDefaultRightPanel" action="getMessagingDefaultRightPanel"/>
			<sj:div id="righePannelMessagingMenu" href="%{getMessagingDefaultRightPanel}"></sj:div>
  		</td>
	</tr>
</table>


	
