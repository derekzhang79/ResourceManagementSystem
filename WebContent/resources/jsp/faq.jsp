<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div>
<br/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center"><table class="formouter" width="100%">
	          <tr>
	            <td><table class="employeeformiinertable">
		                <tr>
			                 <td class="formtitle">
								<s:text name="label.common.faq" />
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner" width="100%"><table class="tablealign">
			<tr>
				<td style="color:#03F"><s:label value="What is HCMOne?" /></td>
			</tr>
			<tr>
				<td><s:label value="An HCMOne is an HR database application used to track 
									information pertaining to your employees. Take all the information in an employee file and store it in 
									a HR database and then provide the capability of reporting on the information and you have a basic HCMOne. 
									This might include the following capabilities: Employee Information, Benefits Management, 
									Employee Self Service, Leave Management, Expense Management, Reporting, Time and Attendance, 
									File Sharing, Organization structure, Project Details and Internal Communication."/></td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="Why You Need HCMOne?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="Any organization that needs to keep track of a larger number of employees than they can handle 
									with paper records or spreadsheets, may be in need of a human resource software application."/>
				</td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="What are the features of HCMOne?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="HCMOne has a list of features for you as follows:
						            Employee Self Services, Expense Management, Leave Management, Time Tracking Module, Benefits, Project Management,
						            Reports, Organization Chart, Internal Communication, File sharing"/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="If I have a question is there product support available?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="Yes, product support is available."/><br/><br/>
					<s:label value="Enterprise SaaS has support covered till the subscription period while the Enterprise Site install has 1 year support covered.
									From second year it has to be bought separately."/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="How long it takes to implement?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="It takes just 7 hours to implement the software"/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="What are the places where you have implementation partners?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="US and India"/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="How do I backup the database?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="If you have taken the Enterprise SaaS Subscription - we will take care of the backup process."/><br/><br/>
					<s:label value="If it’s an Enterprise Site Install you need to take care of your back up process with your IT team."/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="Is there a way to limit access to Some Modules?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="Yes. You can restrict access by tab or allow read-only access to the data you choose to share. Mark the view checkbox to apply read-only access."/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="How do I get upgrades?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="For Enterprise SaaS users upgrades will be free till they use the system and will be automatically updated."/><br/><br/>
					<s:label value="For Enterprise Site Install users upgrades will be free as long as they get support."/><br/><br/>
					<s:label value="During the supported period Enterprise Site Install users will be either trained to upgrade the 
									system on their own or RoosterHR will do the up gradation after getting the required access."/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="How many users are included in HCMOne application?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="With the purchase of HCMOne you may set up as many users as you wish."/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="Is there any training included with the purchase of HCMOne?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="5 hours of web based training free with Enterprise SaaS and Enterprise Site Install while an additional 1 hour 
									of operational training will be given to the Enterprise Site Install users."/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="Are there alerts in this HR system?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="Yes. We have alerts through the application for every module"/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="Are there any ongoing fees?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="Based on the package for SaaS you select"/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="Can I get on a list to receive emails on future HR system articles created on your site?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="Yes – you can subscribe to news letters"/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="Can we share information in HCMOne?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="Yes. You can share your information through HCMOne, we have file sharing feature with 
									this you can share the documents, files to your colleagues."/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="How many people can use application at a time?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="There is no limit to how many users can use the system - we encourage you to extend 
									the application through-out your organizations."/>
			    </td>			
			</tr>
			
			<tr>
				<td style="color:#03F"><br/>
					<s:label value="How do you provide training?" />
				</td>
			</tr>
			<tr>
				<td>
					<s:label value="Web based training"/>
			    </td>			
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>