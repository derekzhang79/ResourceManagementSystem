<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://code.google.com/p/jcaptcha4struts2/taglib/1.0" prefix="jcaptcha" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Calendar"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<sj:head/>
	<link href="<s:url value="/resources/css/default/black/login.css"/>" rel="stylesheet" type="text/css" />
		    <title>SAAS Contract</title>
	<style>
	

	/* styling for the image wrapper  */
	#image_wrap {
		/* dimensions */
		width:900px;
		margin:15px 0 15px 40px;
		padding:15px 0;

		/* centered */
		text-align:center;

		/* some "skinning" */
		background-color:#efefef;
		border:2px solid #fff;
		outline:1px solid #ddd;
		-moz-ouline-radius:4px;
	}
	</style>
	<style>
		body
		{margin:0px;
			padding:0px;
			font-family:Verdana, Geneva, sans-serif;
			font-size:12px;
			color:#3e3e3e;}
			p{font-size:14px; text-align:justify; padding-left:20px; padding-right:20px;}
	</style>
	
</head>
<body>
<div id="SaasContract">
<center>
<table class="maintable" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top"><table class="pixeltable" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="top"><table class="pixeltable" cellspacing="0" cellpadding="0">
          <tr>
            
            	<td colspan="2" align="center" valign="top" class="header"><div align="left"><font color="white" size="4"><b><%=session.getValue("domain_name") %></b></font><div id="logo" align="right"><img src="resources/images/default/black/R-light_logo.png"  style="padding-right:15px; padding-top:8px;"/>
            </div>			</td>
          </tr>
          <tr bgcolor="#CCCCCC">
          	<td colspan="2" align="left" valign="top">
          	<div id="top-panel4">
      	</div>
      	
		</div>
          	</td>
          </tr>
          <tr bgcolor="#CCCCCC">
            <td colspan="2" align="center" valign="top">
            <table align="center" cellpadding="0" cellspacing="0" class="maintable">
              <tr>
               <td  align="center" valign="top">
               	



              </tr>
            </table>
  <h1><strong>RoosterHR, INC.</strong><br />
    <strong>SaaS USER AGREEMENT</strong></h1>
  <p>BY CLICKING THE &ldquo;I HAVE READ AND ACCEPT THIS AGREEMENT&rdquo;  CHECKBOX, YOU AGREE TO THE FOLLOWING TERMS AND CONDITIONS WHICH CONSTITUTE  ALEGALLY ENFORCEABLE SaaS AGREEMENT (THE &ldquo;AGREEMENT&rdquo;).GOVERNING YOUR USE OF  RoosterHR.com&rsquo;S ONLINE SERVICE (THE &ldquo;SERVICE&rdquo;).IF YOU ARE ENTERING INTO THIS SaaS  AGREEMENT ON BEHALF OF A COMPANY OR OTHER LEGAL ENTITY, YOU REPRESANT THAT YOU  HAVE THE COMPLETE AUTHORITY TO BIND SUCH ENTITY TO THIS AGREEMENT. IF YOU ARE  USING THE SERVICE AS AN INDIVIDUAL. YOU REPRESENT THAT YOU ARE OVER THE AGE OF  18. AS USED IN THIS AGREEMENT, THE TERM &ldquo;GRANTEE&rdquo; ENCOMPASSES THE ENTITY OR PERSON  RESPONSIBLE FOR THIS ACCOUNT AND EACH USER ACCESSING THE SERVICE BY MEANS OF A  VALID ACCOUNT AND ESTABLISHED BY YOU, INCLUDING, IF YOU ARE A CORPORATION, ALL  EMPLOYEES OF YOUR CORPORATION. IF YOU DO NOT HAVE THE AUTHORITY, OR IF YOU DO  NOT AGREE TO BE BOUND BY THESE TERMS AND CONDITIONS, YOU MAY NOT USE THIS  SERVICE.</p>
  <p>Your registration for, or use of, the Service is your acceptance  of this Agreement including any materials available on the RoosterHR Website  incorporated by reference herein.</p>
  <p><strong>1. Privacy &amp;  Security.</strong><br />
    RoosterHR&rsquo;s privacy and security policies may be viewed at http://www.RoosterHR.com.  RoosterHR reserves the right to modify its privacy and security policies from  time to time.</p>
  <p><strong>2. Grant of Rights;  Term and Restrictions.</strong><br />
    Pursuant to the terms and conditions of this Agreement,  RoosterHR hereby grants Grantee a limited, non-exclusive, non-transferable  right to use the Service, solely for Grantee&rsquo;s own internal business purposes  (&ldquo;Grant&rdquo;). This Grant shall commence on the Effective Date (&ldquo;Initial Term&rdquo;),  and will be automatically renewed for successive terms of the same number of  years (&ldquo;Renewal Term&rdquo;) unless terminated by either party upon at       least thirty (30) days prior written notice to  the party.<br />
    RoosterHR and its licensors reserve all rights not expressly  granted to Grantee. Grantee may use the Service for a free trial period not a  exceed beyond May 31, 2009. <br />
    Grantee shall not (i) license, grant, sell, resell,  transfer, assign, distribute or otherwise commercially exploit or make  available to any third party the Service or the Content in any way; (ii)  reverse engineer or access the Service in order to (a) build a competitive product  or service, (b) build a product using similar, features, functions or graphics  of the Service, or (c) copy any ideas, features, functions or graphics of the  service; (iii) modify or make derivative works based upon the Service or the  Content; or (iv) create Internet &ldquo;links&rdquo; to the Service or &ldquo;frame&rdquo; or &ldquo;mirror&rdquo; any  Content on any other server or wireless or Internet-based device. The Grant of  right to use the Service cannot be shared or used by more then one individual User.<br />
  <strong>Please refer Attachment  A for post trial period license costing.</strong></p>
  <p><strong>3. The Service.</strong><br />
    RoosterHR will provide Grantee with use of the Service, and  RoosterHR makes no guarantees as to the continuous availability of the Service  or of any specific feature(s) of the Service.</p>
  <p><strong>4. Internet Delays.</strong><br />
    The Service may be subject to limitations, delays, and other  problems inherent in the use of the Internet and electronic communications. <strong>RoosterHR</strong> is not responsible for any  delays, delivery failures, or other damage resulting from such problems.</p>
  <p><strong>5. Support.</strong><br />
    RoosterHR shall provide a technical help desk, but has no  obligation to provide Grantee with hardcopy documentation, upgrades,  enhancements, or other support unless specifically contracted for by Grantee.</p>
  <p><strong>6. Grantee&rsquo;s  Responsibilities.</strong><br />
    Grantee is responsible for all activity occurring under  Grantee&rsquo;s User accounts and shall abide by all applicable laws, treaties and  regulations in connection with Grantee&rsquo;s use of the Service, including those  related to data privacy, international communications and the transmission of  technical or personal data. Grantee shall: (i) notify RoosterHR immediately of  any unauthorized use of any password or account or any other known or suspected  breach of security; (ii) report to RoosterHR immediately and use reasonable  efforts to stop immediately, any copying or distribution of content that is  known or suspected by Grantee or any User under this Grant; and (iii) not  impersonate another RoosterHR User or provide false identity information to  gain access to or use the Service.</p>
  <p><strong>7. Account  Information and Data.</strong></p>
  <p>By this Grant, RoosterHR does not obtain ownership in any  data, information or material that Grantee submits to the Service in the course  of using the service (&ldquo;Client Data&rdquo;). Grantee, not RoosterHR, shall have sole  responsibility for the accuracy, quality, integrity, legality, reliability,  appropriateness, and intellectual property ownership or right to use of all  Client Data, and RoosterHR shall not be responsible or liable for the deletion,  correction, destruction, damage, loss or failure to store any Client Data.  RoosterHR shall use reasonable efforts to protect Client Data. Grantee agrees  and acknowledges that RoosterHR has no obligation to retain the Client Data,  and may delete such Client Data, on the 31st day after termination. RoosterHR  reserves the right to withhold, remove and/or discard Client Data, without  notice, for any breach, including, without limitation, Grantee&rsquo;s non-payment.  Upon termination for cause, Grantee&rsquo;s right to access or use Client Data  immediately ceases, and RoosterHR shall have no obligation to maintain or  provide any Client Data.</p>
  <p><strong>8. Intellectual  Property.</strong><br />
    RoosterHR alone (and its licensors, where applicable) shall  own all right, title and interest, including all related Intellectual Property  Rights, in and to the RoosterHR SaaS Technology, the Content, and the Service  and any suggestions, ideas, enhancement requests, feedback, recommendations or  other information provided by Grantee or any <br />
    other party relating to the Service. The RoosterHR name, the  RoosterHR logo, and the product names associated with the Service are  trademarks of RoosterHR or third parties, and no right or license is granted to  use them. This Agreement is not a sale and does not convey to Grantee any of ownership  in or related to the Service, the RoosterHR SaaS Technology or the Intellectual  Property Rights owned by RoosterHR. Grantee acknowledges that, except as  specifically provided under this Agreement, no other right,<br />
    title, or internet in these items is granted.</p>
  <p><strong>9. Payment of Fees  and Charges</strong>.</p>
  <p>Grantee shall pay all fees or changes to Grantee&rsquo;s account  in accordance with the fees, charges, and billing terms in effect at the time a  fee or change is due and payable. Grantee is responsible for paying all fees  ordered for the entire Term, without regard to whether all Users are active.  Grantee must provide RoosterHR with valid credit card or approved purchase  order information as a condition to signing up for the Service.</p>
  <p>Charges for other services (&ldquo;Charges&rdquo;) will be made on  as-quoted basis. RoosterHR&rsquo;s<br />
    fee and Charges are  exclusive of all taxes, levies, or duties imposed by taxing authorities, and  Grantee shall be responsible for payment of all such taxes, levies, or duties,  excluding only federal or state taxes based solely on RoosterHR&rsquo;s income.            </p>
  <p>Grantee agrees to update billing and account information  within 30 days of any change. That information includes Grantee&rsquo;s legal company  name, street address, e-mail address, and the name telephone number of an  authorized billing contact and Authorized Administrator. If the contact  information Grantee provides is false or fraudulent, RoosterHR reserves the  right to terminate Grantee&rsquo;s access to the Service, in addition to any other  legal remedies.</p>
  <p>If Grantee believes that the fees or Charges to Grantee&rsquo;s  account are incorrect, Grantee must contact RoosterHR in writing within  forty-five (45) days of the date of the charge in question, to be eligible to  receive an adjustment or credit.</p>
  <p>All payments shall be in United States currency.</p>
  <p><strong>10. Non-Payment and  Suspension.</strong></p>
  <p>In addition to any other rights of RoosterHR, RoosterHR  reserves the right to suspend or terminate this Agreement and Grantee&rsquo;s access  to the Service if Grantee&rsquo;s account becomes delinquent.<br />
    Grantee agrees and acknowledges that RoosterHR has no  obligation to retain Client Data and that<br />
    such Client Data may be irretrievably deleted if Grantee&rsquo;s  account id 30 days or more delinquent.</p>
  <p><strong>11. Termination.</strong></p>
  <p>a. Either party may terminate this Agreement or reduce the  number of Users, effective only upon the expiration of the then current Term,  by notifying the other party in writing at least thirty (30) business days  prior to the date of the end of the current Term. In the case of free trials,  notification provided in writing at least thirty (30) business days prior to  the of the end of  the free trial  indicating Grantee&rsquo;s intention to terminate use of the service shall constitute  notice of termination.</p>
  <p>b. Any breach of Grantee&rsquo;s payment obligations or any use of  the RoosterHR SaaS Technology or Service contrary to the terms and conditions  of this Agreement and the License granted hereby will be deemed a material  breach of this User Agreement. RoosterHR, in its sole discretion, may terminate  Grantee&rsquo;s password, account or use of the Service if Grantee breaches or  otherwise fails to comply with this Agreement. RoosterHR may terminate a free  account at any time in its sole discretion.</p>
  <p><strong>12. Representations &amp;  Warranties.</strong></p>
  <p>Each party represents and warrants that it has the legal  power and authority to enter into this Agreement. RoosterHR represents and  warrants that it will provide the service in a manner consistent with general  industry standards reasonably applicable to the provision thereof and that the  Service will perform substantially in accordance with the online RoosterHR Help  Desk documentation under normal use and circumstances. Grantee represents and  warrants that Grantee has not falsely identified Grantee or any User nor  provided any false information to gain access to the Service and that Grantee&rsquo;s  billing information is correct.</p>
  <p><strong>13. Disclaimer of  Warranties.</strong><br />
    ROOSTERHR AND ITS LICENSORS MAKE NO REPRESENTATION,  WARRANTY, OR GUARANTY AS TO THE RELIABILITY, TIMELINESS, QUALITY, SUITABILITY,  TRUTH, AVAILABILITY, ACCURACY OR COMPLETENESS OF THE SERVICE OR ANY CONTENT.  ROOSTERHR AND ITS LICENSORS DO NOT REPRESENTS OR WARRANT THAT (A) THE USE OF  THE SERVICE WILL BE SECURE, TIMELY, UNINTERRUPTED OR ERROR-FREE OR OPERATE IN  COMBINATION WITH ANY OTHER HARDWARE, SOFTWARE, SYSTEM OR DATA, (B) THE SERVICE  WILL MEET GRANTEE&rsquo;S REQUIREMENTS OR EXPECTATIONS, (C) ANY STORED DATA WILL BE  ACCURATE OR RELIABLE, (D) THE QUALITY OF ANY PRODUCTS, SERVICES, INFORMATION,  OR OTHER MATERIAL PURCHASED OR OBTAINED BY GRANTEE THROUGH THE SERVICE WILL  MEET GRANTEE&rsquo;S REQUIREMENTS OR EXPECTATIONS, (E) ERRORS OR DEFECTS WILL BE  CORRECTED, OR (F) THE SERVICE OR THE SERVICE (S) THAT MAKE THE SERVICE  AVAILABLE ARE FREE OF VIRUSES OR OTHER HARMFULL COMPONENTS. THE SERVICE AND ALL  CONTENT IS PROVIDED TO GRANTEE STRICTLY ON AN &ldquo;AS IS&rdquo; BASIS. ALL CONDITIONS,  REPRESENTATIONS AND WARRANTIES, WHETHER EXPRESS, IMPLIED, STATUTORY OR  OTHERWISE, INCLUDING, WITHOUT LIMITATIONS, ANY IMPLIED, STATUTORY OR OTHERWISE,  INCLUDING, WITHOUT LIMITATION, ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS  FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT OF THIRD PARTY RIGHTS, ARE HEREBY  DISCLAIMED TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW BY ROOSTERHR AND  ITS LICENSORS.</p>
  <p><strong>14. Limitation of  Liability.</strong><br />
    IN NO EVENT SHALL ROOSTERHR&rsquo;S AGGREGATE LIABILITY EXCEED THE  AMOUNTS ACTUALLY PAID BY AND/OR DUE FROM GRANTEE IN THE TWELVE (12) MONTHS  PERIOD IMMEDIATELY PRECEDING THE EVENT GIVING RISE TO ANY SUCH LIABILITY. IN NO  EVENT SHALL ROOSTERHR AND/OR ITS LICENSORS BELIABLE TO ANYONE FOR ANY INDIRECT,  PUNITIVE, SPECIAL, EXEMPLARY, INCIDENTAL, CONSEQUENTIAL OR OTHER DAMAGES OF ANY  TYPE OR KIND (INCLUDING LOSS OF DATA, REVENUE, PROFITS, USE OR OTHER ECONOMIC  ADVANTAGE) ARISING OUT OF, OR IN ANY CONNECTED WITH THIS SERVICE INCLUDING BUT  NOT LIMITED TO THE USE OR INABILITY TO USE THE SERVICE, OR FOR ANY CONTENT  OBTAINED FROM OR THROUGH THE SERVICE, ANY INTERRUPTION, INACCURACY, ERROR OR  OMISSION, REGARDLESS OF CAUSE IN THE CONTENT, EVEN IF THE PARTY FROM WHICH  DAMAGES ARE BEING SOUGHT OR SUCH PARTY&rsquo;S LICENSORS HAVE BEEN PREVIOUSLY ADVISED  OF THE POSSIBILITY OF SUCH DAMAGES.</p>
  <p><strong>15. Indemnification.</strong><br />
    Grantee and every User under this Grant, shall indemnify and  hold RoosterHR, its licensors and their parent organizations, subsidiaries,  affiliates, officers, directors, employees, attorneys and agents harmless from  and against any and all claims, causes of action, costs, damages, losses,  liabilities and expenses (including attorneys&rsquo; fees and costs) arising out of  or in connection with; (i) an allegation that use of the Client Data infringes  the rights of, or has caused harm to, a third party; (ii) violation by Grantee  of  Grantee&rsquo;s representations and  warranties; or (iii) the breach by Grantee or any User (s) under Grantee&rsquo;s  Grant pursuant to this Agreement, provided in any such case, that RoosterHR (a)  gives written notice of the claim promptly to Grantee; (b) gives Grantee sole  control of the defense and settlement of the claim (except Grantee may not  settle any claim, without RoosterHR&rsquo;s consent, unless Grantee unconditionally  releases RoosterHR of all liability and such settlement does not affect  RoosterHR&rsquo;s business or Service,); (c) provides to Grantee all available  information and assistance; and (d) has not compromised or settled such claim.  RoosterHR shall indemnify and hold Grantee and Grantee&rsquo;s authorized Users,  parent organizations, subsidiaries, affiliates, officers. Directors, employees,  attorneys and agents harmless from and against any and all claims, causes of  action, costs, damages, losses, liabilities and expenses (including attorneys&rsquo;  fees and costs) arising out of or in connection with: (i) an allegation that  the Service directly infringes a copyright, a U.S. patent issued as of the  Effective Date, or a trademark of a third party; (ii) a violation by RoosterHR  of its representations or warranties; or (iii) breach of this Agreement by  RoosterHR; provided in any such case, that Grantee (a) promptly gives written  notice of the claim to RoosterHR; (b) gives RoosterHR sole control of the  defense and settlement of the claim ( except RoosterHR may not settle any  claim, without Grantee&rsquo;s consent, unless it unconditionally releases Grantee of  all liability; (c) provides to RoosterHR all available information and  assistance ; and (d) has not compromised or settled such claim. RoosterHR shall  have no indemnification obligation, and Grantee shall indemnify RoosterHR  pursuant to this Agreement, for claims arising from any infringement alleged to  be caused by the combination with any of Grantee&rsquo;s products, service, and  hardware or business process.</p>
  <p><strong>16. Local Laws and  Export Control. </strong><br />
    The Service provided on the RoosterHR Service site includes  and software and technology that may be subject to United States export  controls administered by the U.S. Department of Commerce, the United States  Department of Treasury Office of Foreign Assets Control and other U.S. agencies  and the export control regulations of the European Union. Grantee and each User  of this site acknowledges and agrees that the site shall not be used, and none  of the underlying information, software, or technology may be transferred or  otherwise exported or re-exported to countries as to which the United States  and/or the European union maintains an embargo (collectively, &ldquo;Embargoed Countries&rdquo;),  or to or by a national or resident thereof , or any person or entity on the U.S.  .Department of Commerce&rsquo;s Table of Specially Designated National or the U.S.  Department of Commerce&rsquo;s Table of Denial Orders (collectively, &ldquo;Designated  Nationals&rdquo;). The lists of Embargoed Countries and Designated Nationals are  subject to change without notice. By using the service, Grantee represents and  warrants that  Grantee is not located  in,  under the control of , or a national  or resident of an Embargoed country or Designated National. Grantee agrees to  comply strictly with U.S.  and  European Union export laws and  assume sole responsibility for obtaining any necessary licenses to export or  re-export.<br />
    The Service provided on the site may use encryption  technology that is subject to licensing requirements under the U.S. Export  Administration Regulations, 15 C.F.R.Parts 730-774 and Council Regulation (EC)  No. 1334/2000<br />
    RoosterHR and its licensors make no representation that the  Service is appropriate or available for use in other locations. If  Grantee uses the Service from outside the United States of America  and/or the European Union, Grantee is solely responsible for compliance with  all applicable laws, including without limitation export and import regulations  of other countries. Any diversion of the Content contrary to United States or European  Union (including European Union Member  States) law is prohibited. None of the Content, nor any information acquired  through the use of the Service, is or will be used for nuclear activities,  chemical or biological weapons or missile projects, unless specifically  authorized by the United    States government or appropriate European  body for such purposes.</p>
  <p><strong>&nbsp;</strong></p>
  <p><strong>17. Notice.</strong><br />
    RoosterHR may give notice by means of a general notice on the  Service, electronic mail to Grantee&rsquo;s e-mail address on record in RoosterHR&rsquo;s  account information, or by written<br />
    Communication sent by first class mail or pre-paid post to  Grantee&rsquo;s address on record in RoosterHR&rsquo;s account information.<br />
    Such notice shall be deemed to have been given upon the  expiration of 48 hours after mailing or posting (if sent by first class mail or  pre-paid post) or 12 hours after sending (if sent by email). Grantee may give  notice to RoosterHR (such notice shall be deemed given when received by  RoosterHR.com ) at any time by any of the following: letter delivered by  nationally recognized overnight delivery service or certified, first class  postage prepaid mail to RoosterHR at the following address: RoosterHR, Inc.,  5840 Fair Castle Dr, Troy, MI 48098 addressed to the attention of: Ravi  Venugopal.</p>
  <p><strong>18. Modifications to  terms.</strong></p>
  <p>RoosterHR reserves the right to modify the terms and  conditions of this Agreement or its policies relating to the Service at any  time, effective upon posting of an updated version of this Agreement on the  Service. Grantee is responsible for reviewing this Agreement.<br />
    Continued use of the Service after any such changes shall  constitute Grantee&rsquo;s consent to such changes.</p>
  <p><strong>19. Assignment.</strong><br />
    This Agreement may not be assigned by Grantee without the  prior written approval of RoosterHR but may be assigned without Grantee&rsquo;s  consent by RoosterHR to (i) a parent or subsidiary, (ii) an<br />
    acquirer of assets, or (iii) a successor by merger. Any  purported assignment in violation of this section shall be void.</p>
  <p><strong>20. General.</strong></p>
  <p>The Agreement shall be governed by Michigan law and  controlling United States federal law, without regard to the choice or  conflicts of law provisions of any jurisdiction, and any disputes, actions,  claims or causes of action arising out of or in connection with this Agreement  or the service shall be subject to the exclusive jurisdiction of the state and  federal courts located in Oakland County, Michigan. If any provision of this  Agreement is held by a court of competent jurisdiction to be invalid or  unenforceable, then such provision(s) shall be construed, as nearly as  possible, to reflect the intentions of the invalid or unenforceable  provisions(s), with all other provisions remaining in full force and effect. No  joint venture, partnership, employment, or agency relationship exists between  Grantee or any User and RoosterHR as a result of this agreement or use of the  Service. The failure of RoosterHR to enforce any right or provision in this  Agreement shall not constitute a waiver of such right or provision unless  acknowledged and agreed to by RoosterHR in writing. This Agreement, together  with any applicable Order Form, comprises the entire agreement between Grantee  and RoosterHR and supersedes all prior or contemporaneous negotiations,  discussions or agreements, whether written or oral, between the parties  regarding the subject matter contained herein. Grantee hereby consents and  submits to the jurisdiction and forum of the state federal courts in the State  of Michigan  regarding all controversies arising from this Agreement.</p>
  <p><strong>21. Definitions.</strong><br />
    As used in this Agreement and in any Order Forms now or  hereafter associated herewith;<br />
  &ldquo;Agreement&rdquo; means these terms of use, the original Order  Form, any subsequent Order Forms, whether written or submitted online via the  RoosterHR Order Center, and any materials available on http://www.RoosterHR.com  specifically incorporated by reference herein, as such materials, including the  terms of this Agreement, may be updated by RoosterHR from time to time in its  sole discretion; &ldquo;Client Data&rdquo; shall have meaning given in Paragraph 7 above;  &ldquo;Content&rdquo; means the audio and visual information, documents, software, products  and services contained or made available to Grantee and the User(s) authorized  to use the Service under this Grant in the course of using the Service;  &ldquo;RoosterHR&rdquo; means collectively RoosterHR, Inc., a Michigan corporation, having  its principal place of business at:5840 Faircastle Dr, Troy, MI 48098;  &ldquo;RoosterHR SaaS Technology&rdquo; means all of RoosterHR&rsquo;s proprietary technology  (including software, hardware, products, business concepts, and processes,  logic algorithms, graphical User interfaces (GUI), techniques, designs and  other tangible or intangible technical material or information) made available  to Grantee by RoosterHR in providing the Service; &ldquo;Effective Date&rdquo; means the  earlier of either the date this User Agreement is accepted or the date Grantee  begins using the Service; &ldquo;Intellectual Property Rights&rdquo; means all rights, title  and interest in and to the RoosterHR SaaS Technology, the Content, and all  copyrights, patents, trade secrets, trademarks, service marks or other  intellectual property or proprietary rights and corrections, bug fixes, enhancements,  updates, releases, or other modifications, including custom modifications made  by RoosterHR relating thereto, and the media on which same are furnished;  &ldquo;Order Form(s)&rdquo; means the form evidencing the initial designation of Service  and subsequent Order Forms, specifying, among other things, the edition of the  Service selected and covered by the Grant, the Term, the number of Users, the  applicable Use Fee, the billing period, and other services and changes, as  agreed to between RoosterHR and Grantee, each such Order Form to be incorporated  into and to become a part<br />
    Of this Agreement (in the event of any conflict between the  terms of this Agreement and the terms of any such Order Form, the terms of this  Agreement shall prevail); &ldquo;Service(s)&rdquo; means the specific edition of RoosterHR&rsquo;s  online software as a service, or other offerings developed, operated, and  maintained by RoosterHR, accessible via http://www.RoosterHR.com or another  designated web site or IP address, or ancillary services rendered to Grantee by  RoosterHR, to which Grantee are being granted access under this Agreement,  including the RoosterHR SaaS Technology and the content;<br />
  &ldquo;Term(s) means the period(s) during which a specified number  of Users have the right to use the Service pursuant to the Grant and as  designated on a Form(s); &ldquo;Users(s)&rdquo; means Grantee&rsquo;s employees, representatives,  consultants, contractors or agents who are authorized under the Grant made by  this Agreement to use the Service and who have supplied User identifications  and passwords by Grantee (or by RoosterHR at Grantee&rsquo;s request).</p>
</center>
</div>
<%
	Calendar c = Calendar.getInstance();
	String sMillis = String.valueOf(c.getTimeInMillis());
%>

            <br />
            <s:form action="acceptSaasContract">
		             <table cellpadding="1" cellspacing="0" class="tableBackground" Width="500px"><tr><td>
					<table cellpadding="3" cellspacing="1" class="innerTable" Width="700px">
						<tr>
							<td class="inputtext"><s:text name="label.form.fields.saas.by"/></td>
							<td id="control"><s:textfield name="saasContract.companyName" size="25"></s:textfield>(Company Name)</td>
							<td class="inputerrormessage">
								<s:fielderror fieldName="saasContract.companyName"/>
						 	</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.form.fields.saas.its"/></td>
							<td id="control"><s:textfield name="saasContract.personName" size="25"></s:textfield>(Signatory Person's Name)</td>
							<td class="inputerrormessage">
								<s:fielderror fieldName="saasContract.personName"/>
						 	</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.form.fields.saas.designation"/></td>
							<td id="control"><s:textfield name="saasContract.designation" size="25"></s:textfield></td>
							<td class="inputerrormessage">
								<s:fielderror fieldName="saasContract.designation"/>
						 	</td>
						 	<s:hidden name="username" value="%{hrUserLogin.userName}"></s:hidden>
						 	<s:hidden name="password" value="%{hrUserLogin.password}"></s:hidden>
						 	<s:hidden name="captchacode"></s:hidden>
						</tr>
					</table>
		  			</td></tr>
		  		</table>
		  		<table align="center">
					<tr>
						<td>
				   		   <s:submit name="Accept" value="Accept" cssClass="submitbutton117" ></s:submit>
				           <s:submit action="doLogout" name="Decline" value="Decline" cssClass="submitbutton117" ></s:submit>
					 	</td>
  				   </tr>
				</table>
		  	</s:form>	
            </td>
          </tr>
          <tr>
            <td width="811" align="right" valign="top" class="footer">Roosterhr.com All rights reserved</td>
            <td width="155" align="center" valign="top" class="footer"><a href="#"><img src="resources/images/default/black/rooster_hr_product.png" width="191" height="39" border="0" /></a></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
