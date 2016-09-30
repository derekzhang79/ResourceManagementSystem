<%@ include file="/resources/includes/taglibs.jsp"%>
<aside id="sidebar_left" class="nano nano-light affix">
                <!-- -------------- Sidebar Left Wrapper  -------------- -->
                <div class="sidebar-left-content nano-content">
                    <!-- -------------- Sidebar Header -------------- -->
                    <header class="sidebar-header">
                        <!-- -------------- Sidebar - Author -------------- -->
                        <div class="sidebar-widget author-widget">
                            <div class="media">
                                <a class="media-left" href="Add-Employee.html">
                                <img src="assets/img/avatars/profile_avatar.jpg" class="img-responsive">
                                </a>
                                <div class="media-body">
                                    <div class="media-links">
                                        <a href="Add-EMployee.html" class="sidebar-menu-toggle">My Profile-</a> <a href="login.html">Logout</a>
                                    </div>
                                    <div class="media-author"><s:text name="application.welcome.message"/>
                                    &nbsp;<s:text name="#session.FIRST_NAME"></s:text>&nbsp;
                                    <s:text name="#session.MIDDLE_NAME"></s:text>&nbsp;
                                    <s:text name="#session.LAST_NAME"></s:text>, &nbsp;
                                    <s:text name="#session.ROLE"></s:text></div>
                                </div>
                            </div>
                        </div>
                        <!-- -------------- Sidebar - Author Menu  -------------- -->
                        <div class="sidebar-widget menu-widget">
                            <div class="row text-center mbn">
                                <div class="col-xs-2 pln prn">
                                    <a href="Dashboard.html" class="text-primary" data-toggle="tooltip" data-placement="top"
                                        title="Dashboard">
                                    <span class="fa fa-dashboard"></span>
                                    </a>
                                </div>
                                <div class="col-xs-4 col-sm-2 pln prn">
                                    <a href="charts-highcharts.html" class="text-info" data-toggle="tooltip"
                                        data-placement="top"
                                        title="Stats">
                                    <span class="fa fa-bar-chart-o"></span>
                                    </a>
                                </div>
                                <div class="col-xs-4 col-sm-2 pln prn">
                                    <a href="sales-stats-products.html" class="text-system" data-toggle="tooltip"
                                        data-placement="top" title="Orders">
                                    <span class="fa fa-info-circle"></span>
                                    </a>
                                </div>
                                <div class="col-xs-4 col-sm-2 pln prn">
                                    <a href="sales-stats-purchases.html" class="text-warning" data-toggle="tooltip"
                                        data-placement="top" title="Invoices">
                                    <span class="fa fa-file"></span>
                                    </a>
                                </div>
                                <div class="col-xs-4 col-sm-2 pln prn">
                                    <a href="basic-profile.html" class="text-alert" data-toggle="tooltip" data-placement="top"
                                        title="Users">
                                    <span class="fa fa-users"></span>
                                    </a>
                                </div>
                                <div class="col-xs-4 col-sm-2 pln prn">
                                    <a href="management-tools-dock.html" class="text-danger" data-toggle="tooltip"
                                        data-placement="top" title="Settings">
                                    <span class="fa fa-cogs"></span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </header>
                    <!-- -------------- /Sidebar Header -------------- -->
                    <!-- -------------- Sidebar Menu  -------------- -->
                     <ul class="nav sidebar-menu">
                        <li class="sidebar-label pt30">	</li>
                       <%--  <s:if test="#session.TimeSheetModule_Menu == true"> --%>
                        
                         <li>
                          <%--   <a  href="Dashboard.html">
                            
                            </a>  --%>  
                            <%-- <s:form id="menuTimesheetFormId"> --%>
							<sj:a href="showTimeSheetTabs.action" formIds="menuTimesheetFormId" 
							targets="mainmenu_div"><span class="fa fa-dashboard"></span>
							 <span class="sidebar-title"><s:text name="MTimesheet"/></span></sj:a>
				<%-- </s:form>   --%>                   
                        </li>
                        <%-- </s:if> --%>
                        <%-- <s:if test="#session.OrgChart_Menu == true"> --%>
                        <li>
                           <%--  <s:form id="menuOrgChartFormId"> --%>
					<sj:a href="showOrgChart.action" formIds="menuOrgChartFormId" 
					targets="mainmenu_div" indicator="indicatorMainMenu">
					 <span class="fa fa-bar-chart"></span><span class="sidebar-title">
					 <s:text name="OrgChart"/></span></sj:a>
				<%-- </s:form>  --%>
                        </li>
                        <%-- </s:if> --%>
                        <%-- <s:if test="#session.LeaveModule_Menu == true"> --%>
                       <li>
                            	<%-- <s:form id="menuLeaveFormId"> --%>
					<%-- <sj:a href="getAllLeaveTab.action" formIds="menuLeaveFormId" targets="mainmenu_div" indicator="indicatorMainMenu"><span class="fa fa-clock-o"></span><span class="sidebar-title"><s:text name="MLeave"/></span></sj:a> --%>   
				<%-- </s:form> --%>
				  <a id="leave" href="#">
                            <span class="fa fa-check-square-o"></span>
                            <span class="sidebar-title">Leave</span>
                            </a>
                        </li>
                        <%-- </s:if> --%>
                       <%--  <s:if test="#session.CUSTOMER_ADD == true ||
					#session.CUSTOMER_VIEW == true ||
					#session.PROJECT_ADD == true ||
					#session.PROJECT_VIEW == true ||
					#session.PROJECTACTIVITY_ADD == true ||
					#session.PROJECTACTIVITY_VIEW == true ||
					#session.TARGETMODULE_VIEW == true ||
					#session.TIMESHEET_APPROVER == 'BOTH'"> --%>
                        <li>
                            <%-- <s:form id="menuProjectFormId"> --%>
							<%-- <sj:a href="MenuPart2MainProjInfo.action" formIds="menuProjectFormId"
							 targets="mainmenu_div" indicator="indicatorMainMenu"> 
							 <span class="fa fa-check-square-o"></span><span class="sidebar-title">
							 <s:text name="MTProjects"/></span></sj:a>  --%>  
							  <a id="projects" href="#">
                            <span class="fa fa-bars"></span>
                            <span class="sidebar-title">Projects</span>
                            </a>
						<%-- </s:form> --%>
                        </li>
                        <%-- </s:if> --%>
                        <%-- <s:if test="#session.ExpenseModule_Menu == true"> --%>
                        <li>
                          <%--   <s:form id="menuExpenseFormId"> --%>
					<%-- <sj:a href="getAllEmpExpenses.action" formIds="menuExpenseFormId"
					 targets="mainmenu_div" indicator="indicatorMainMenu">
					 <span class="fa fa-usd"></span><span class="sidebar-title">
					 <s:text name="MExpense"/></span></sj:a> --%>
					 <a id=expenses href="#">
                            <span class="fa fa-usd"></span>
                            <span class="sidebar-title">Expenses</span>
                            </a>
				<%-- </s:form>  --%>
                        </li>
                        <%-- </s:if> --%>
                        <li>
                            <a href="Recruit.html">
                            <span class="fa fa-check-circle"></span>
                            <span class="sidebar-title">Recruit</span>
                            </a>
                        </li>
                         
                        <li>
                             <a  href="setUpEmployeesNEW.action">
                            <span class="fa fa-credit-card"></span>
                            <span class="sidebar-title">Paystubs</span>
                            </a>
                        </li>
						
                       						
						
						 <li>
                    <a class="accordion-toggle" href="#">
                        <span class="fa fa-comments"></span>
                        <span class="sidebar-title">Chats</span>
		                 </a>
						 </li>
				

                        <!-- -------------- Sidebar Progress Bars -------------- -->
                    </ul>
                    <!-- -------------- /Sidebar Menu  -------------- -->
                    <!-- -------------- Sidebar Hide Button -------------- -->
                    <div class="sidebar-toggler">
                        <a href="#">
                        <span class="fa fa-arrow-circle-o-left"></span>
                        </a>
                    </div>
                    <!-- -------------- /Sidebar Hide Button -------------- -->
                </div>
                <!-- -------------- /Sidebar Left Wrapper  -------------- -->
            </aside>
            <!-- -------------- Main Wrapper -------------- -->
            <section id="content_wrapper">
                <!-- -------------- Topbar Menu Wrapper -------------- -->
                

                <header id="topbar" class="alt">
                    <div class="topbar-left">
                         <ul>
                         <li><i class="fa fa-plus" aria-hidden="true"></i></li>
                         <li>Employee</li>
                    </div>
                    <div class="topbar-right">
                        <div class="btn-group">
                            <i class="fa fa-plus-circle symbol"></i>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-system">
                            <i class="fa fa-users "></i>
                            </button>
                            <p>Customer</p>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn  btn-primary">
                            <i class="fa fa-user"></i>
                            </button>
                            <p>Employee</p>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-info">
                            <i class="fa fa-home"></i>
                            </button>
                            <p>Project</p>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-dark">
                            <i class="fa fa-calendar"></i>
                            </button>
                            <p>Schedule</p>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-dark">
                            <i class="fa fa-tasks"></i>
                            </button>
                            <p>Task</p>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-success">
                            <i class="fa fa-female"></i>
                            </button>
                            <p>Vendor</p>
                        </div>
                        
                    </div>
                </header>
								
												
 	<script type="text/javascript">
 	
 	
 	$('#expenses').click(function(event) {
 		 $.ajax({url: "resources/jsp/common/expenses.jsp", success: function(result){
 			 $("#div1").html("");
             $("#div1").html(result);
         }});
 	})
 	$('#projects').click(function(event) {
 		 $.ajax({url: "resources/jsp/common/empForm.jsp", success: function(result){
 			 $("#div1").html("");
             $("#div1").html(result);
         }});
 	})
 	
 	$('#leave').click(function(event) {
 		 $.ajax({url: "resources/jsp/common/absense-planner.jsp", success: function(result){
 			 $("#div1").html("");
             $("#div1").html(result);
         }});
 	})
 	</script>