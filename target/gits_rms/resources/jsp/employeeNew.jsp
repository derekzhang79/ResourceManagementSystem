<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%-- <%@ taglib prefix="sx" uri="/struts-dojo-tags" %> --%>

<%@ page import="java.util.List, com.gits.rms.vo.EmployeesVO,java.util.ArrayList " %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title> -->
     <!-- -------------- Meta and Title -------------- -->
        <meta charset="utf-8">
        <title>HCMOne-Add Employee</title>
        <meta name="keywords" content="HTML5, Bootstrap 3, Admin Template, UI Theme"/>
        <meta name="description" content="Alliance - A Responsive HTML5 Admin UI Framework">
        <meta name="author" content="ThemeREX">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- -------------- Fonts -------------- -->
        <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700'>
        <link href='https://fonts.googleapis.com/css?family=Lato:400,300,300italic,400italic,700,700italic' rel='stylesheet'
            type='text/css'>
			
		<!-- -------------- Icomoon -------------- -->
    	<link rel="stylesheet" type="text/css" href="assets/fonts/icomoon/icomoon.css">	
	
	
	
	  	<!-- -------------- CSS - allcp forms -------------- -->
        <link rel="stylesheet" type="text/css" href="assets/allcp/forms/css/forms.css">
		
		
			
         <!-- -------------- CSS - theme -------------- -->
    	<link rel="stylesheet" type="text/css" href="assets/skin/default_skin/css/theme.css">

      
        <!-- -------------- Favicon -------------- -->
        <link rel="shortcut icon" href="assets/img/favicon.ico">
        <!-- -------------- CSS - custum -------------- -->
   
        <link rel="stylesheet" type="text/css" href="assets/css/style1.css">
        <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
        <!-- -------------- IE8 HTML5 support  -------------- -->
        <!--[if lt IE 9]>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        
        
 		<!-- -------------- jQuery -------------- -->
 		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="assets/js/jquery/jquery-1.11.3.min.js"></script>
        <script src="assets/js/jquery/jquery_ui/jquery-ui.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap-datetimepicker.min.css">
		<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
        
  		<!-- -------------- MonthPicker JS -------------- -->
        <script src="assets/allcp/forms/js/jquery-ui-monthpicker.min.js"></script>
        <script src="assets/allcp/forms/js/jquery-ui-datepicker.min.js"></script>
        <script src="assets/allcp/forms/js/jquery.spectrum.min.js"></script>
        <script src="assets/allcp/forms/js/jquery.stepper.min.js"></script>
        
        <script src="assets/js/autocomplete-0.3.0.min.js"></script>
        
        
         <%-- <sx:head /> --%>
         
                         <style>
  .ui-autocomplete {
    /* max-height: 100px; */
    overflow-y: auto;
    /* prevent horizontal scrollbar */
    overflow-x: hidden;
    z-index: 999999999999999999999;
  }
  /* IE 6 doesn't support max-height
   * we use height instead, but this forces the menu to always be this tall
   */
  * html .ui-autocomplete {
    /* height: 100px; */
  }
  </style>
</head>
 <body class="basic-profile">
        <!-- -------------- Customizer -------------- -->
        <!-- -------------- /Customizer -------------- -->
        <!-- -------------- Body Wrap  -------------- -->
        <div id="main">
            <!-- -------------- Header  -------------- -->
            <header class="navbar navbar-fixed-top bg-dark">
                
				<div class="navbar-logo-wrapper">
                    <a class="navbar-logo-text" href="Dashboard.html">
                    <img src="assets/img/hcm-logo.png" class="logo">
                    <b>HCMOne</b>
                    </a>
                    <span id="sidebar_left_toggle" class="ad ad-lines"></span>
                </div>
				
				
                <form class="navbar-form navbar-left search-form square" id="fSearch" method="post" action="${pageContext.request.contextPath}/setUpEmployeesNEW.action" role="search">
                   <div class="input-group add-on">
                        <input id="autocompleteEmployee1" type="text" class="form-control" placeholder="Search..." onfocus="this.placeholder=''"
                            onblur="this.placeholder='Search...'">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                    <input type="hidden" name="employee.employeeId" id="autocompleteEmployeeId1" value=0> 
                </form>
                
              
                <ul class="nav navbar-nav navbar-right">
                   <%--  <li class="dropdown dropdown-fuse">
					
                        <div class="navbar-btn btn-group">
                            <div  data-toggle="dropdown" class=" dropdown-toggle">
                            <span class="fa fa-envelope fs26 text-danger"></span>
                            </div>
                            <div data-toggle="dropdown" class=" dropdown-toggle fs18 visible-xl notify">
                            3
                            </div>
                            <div class="dropdown-menu keep-dropdown w375 animated animated-shorter fadeIn" role="menu">
                                <div class="panel mbn">
                                    <div class="panel-menu">
                                        <div class="btn-group btn-group-justified btn-group-nav" role="tablist">
                                            <a href="#nav-tab1" data-toggle="tab"
                                                class="btn btn-primary btn-bordered btn-sm active">Activity</a>
                                            <a href="#nav-tab2" data-toggle="tab"
                                                class="btn btn-primary btn-bordered btn-sm br-l-n br-r-n">Messages</a>
                                            <a href="#nav-tab3" data-toggle="tab" class="btn btn-primary btn-bordered btn-sm">Notifications</a>
                                        </div>
                                    </div>
                                    <div class="panel-body panel-scroller scroller-overlay scroller-navbar pn">
                                        <div class="tab-content br-n pn">
                                            <div id="nav-tab1" class="tab-pane active" role="tabpanel">
                                                <ul class="media-list" role="menu">
                                                    <li class="media">
                                                        <a class="media-left" href="#"> <img src="assets/img/avatars/5.jpg"
                                                            class="mw40 br2" alt="avatar">
                                                        </a>
                                                        <div class="media-body">
                                                            <h5 class="media-heading">New post
                                                                <small class="text-muted">- 09/01/15</small>
                                                            </h5>
                                                            Last Updated 5 days ago by
                                                            <a class="" href="#"> John Doe </a>
                                                        </div>
                                                    </li>
                                                    <li class="media">
                                                        <a class="media-left" href="#"> <img src="assets/img/avatars/2.jpg"
                                                            class="mw40 br2" alt="avatar">
                                                        </a>
                                                        <div class="media-body">
                                                            <h5 class="media-heading">New post
                                                                <small> - 09/01/15</small>
                                                            </h5>
                                                            Last Updated 5 days ago by
                                                            <a class="" href="#"> John Doe </a>
                                                        </div>
                                                    </li>
                                                    <li class="media">
                                                        <a class="media-left" href="#"> <img src="assets/img/avatars/3.jpg"
                                                            class="mw40 br2" alt="avatar">
                                                        </a>
                                                        <div class="media-body">
                                                            <h5 class="media-heading">New post
                                                                <small class="text-muted">- 09/01/15</small>
                                                            </h5>
                                                            Last Updated 5 days ago by
                                                            <a class="" href="#"> John Doe </a>
                                                        </div>
                                                    </li>
                                                    <li class="media">
                                                        <a class="media-left" href="#"> <img src="assets/img/avatars/4.jpg"
                                                            class="mw40 br2" alt="avatar">
                                                        </a>
                                                        <div class="media-body">
                                                            <h5 class="media-heading">New post
                                                                <small class="text-muted">- 09/01/15</small>
                                                            </h5>
                                                            Last Updated 5 days ago by
                                                            <a class="" href="#"> John Doe </a>
                                                        </div>
                                                    </li>
                                                    <li class="media">
                                                        <a class="media-left" href="#"> <img src="assets/img/avatars/5.jpg"
                                                            class="mw40 br2" alt="avatar">
                                                        </a>
                                                        <div class="media-body">
                                                            <h5 class="media-heading">New post
                                                                <small class="text-muted">- 09/01/15</small>
                                                            </h5>
                                                            Last Updated 5 days ago by
                                                            <a class="" href="#"> John Doe </a>
                                                        </div>
                                                    </li>
                                                    <li class="media">
                                                        <a class="media-left" href="#"> <img src="assets/img/avatars/2.jpg"
                                                            class="mw40 br2" alt="avatar">
                                                        </a>
                                                        <div class="media-body">
                                                            <h5 class="media-heading">New post
                                                                <small> - 09/01/15</small>
                                                            </h5>
                                                            Last Updated 5 days ago by
                                                            <a class="" href="#"> John Doe </a>
                                                        </div>
                                                    </li>
                                                    <li class="media">
                                                        <a class="media-left" href="#"> <img src="assets/img/avatars/3.jpg"
                                                            class="mw40 br2" alt="avatar">
                                                        </a>
                                                        <div class="media-body">
                                                            <h5 class="media-heading">New post
                                                                <small class="text-muted">- 09/01/15</small>
                                                            </h5>
                                                            Last Updated 5 days ago by
                                                            <a class="" href="#"> John Doe </a>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div id="nav-tab2" class="tab-pane chat-widget" role="tabpanel">
                                                <div class="media">
                                                    <div class="media-left">
                                                        <a href="#">
                                                        <img class="media-object" alt="64x64"
                                                            src="assets/img/avatars/3.jpg">
                                                        </a>
                                                    </div>
                                                    <div class="media-body">
                                                        <span class="media-status online"></span>
                                                        <h5 class="media-heading">Frank Hill
                                                            <small> - 14:10am</small>
                                                        </h5>
                                                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <div class="media-body">
                                                        <span class="media-status offline"></span>
                                                        <h5 class="media-heading">George Kelly
                                                            <small> - 15:25am</small>
                                                        </h5>
                                                        Sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna
                                                        aliquam erat volutpat.
                                                    </div>
                                                    <div class="media-right">
                                                        <a href="#">
                                                        <img class="media-object" alt="64x64"
                                                            src="assets/img/avatars/1.jpg">
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <div class="media-left">
                                                        <a href="#">
                                                        <img class="media-object" alt="64x64"
                                                            src="assets/img/avatars/2.jpg">
                                                        </a>
                                                    </div>
                                                    <div class="media-body">
                                                        <span class="media-status online"></span>
                                                        <h5 class="media-heading">Frank Hill
                                                            <small> - 15:33am</small>
                                                        </h5>
                                                        Lorem ipsum dolor sit amet, nonummy nibh euismod tinc consectetuer
                                                        adipiscing elit.
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <div class="media-body">
                                                        <span class="media-status offline"></span>
                                                        <h5 class="media-heading">George Kelly
                                                            <small> - 15:43am</small>
                                                        </h5>
                                                        Euismod sed diam nonummy nibh euismod tincidunt ut laoreet dolore
                                                        magna aliquam erat volutpat.
                                                    </div>
                                                    <div class="media-right">
                                                        <a href="#">
                                                        <img class="media-object" alt="64x64"
                                                            src="assets/img/avatars/1.jpg">
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <div class="media-left">
                                                        <a href="#">
                                                        <img class="media-object" alt="64x64"
                                                            src="assets/img/avatars/2.jpg">
                                                        </a>
                                                    </div>
                                                    <div class="media-body">
                                                        <span class="media-status online"></span>
                                                        <h5 class="media-heading">Frank Hill
                                                            <small> - 16:30am</small>
                                                        </h5>
                                                        Corem ipsum dolor sit amet, nonummy nibh euismod tinc co.
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <div class="media-body">
                                                        <span class="media-status offline"></span>
                                                        <h5 class="media-heading">George Kelly
                                                            <small> - 12:30am</small>
                                                        </h5>
                                                        Ubh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
                                                    </div>
                                                    <div class="media-right">
                                                        <a href="#">
                                                        <img class="media-object" alt="64x64"
                                                            src="assets/img/avatars/1.jpg">
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="nav-tab3" class="tab-pane alerts-widget" role="tabpanel">
                                                <div class="media">
                                                    <a class="media-left" href="#"> <span
                                                        class="fa fa-shopping-cart text-success"></span> </a>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">New Product Order
                                                            <small class="text-muted"></small>
                                                        </h5>
                                                        <a href="#">iPad Air</a> - 1 day ago
                                                    </div>
                                                    <div class="media-right">
                                                        <div class="media-response"> Confirm?</div>
                                                        <div class="btn-group">
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-check text-success"></i>
                                                            </button>
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-cog"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <a class="media-left" href="#"> <span
                                                        class="fa fa-comment text-system"></span>
                                                    </a>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">New User Comment
                                                            <small class="text-muted"></small>
                                                        </h5>
                                                        Sam Fisher - I'd like to read more!
                                                    </div>
                                                    <div class="media-right">
                                                        <div class="media-response text-right"> Moderate?</div>
                                                        <div class="btn-group">
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-check text-success"></i>
                                                            </button>
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-pencil"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <a class="media-left" href="#"> <span
                                                        class="fa fa-eye text-warning"></span> </a>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">New User Review
                                                            <small class="text-muted"></small>
                                                        </h5>
                                                        Sebastian Jones - 5 hours ago
                                                    </div>
                                                    <div class="media-right">
                                                        <div class="media-response"> Approve?</div>
                                                        <div class="btn-group">
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-check text-success"></i>
                                                            </button>
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-remove"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <a class="media-left" href="#"> <span class="fa fa-user text-info"></span>
                                                    </a>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">New User Registration
                                                            <small class="text-muted"></small>
                                                        </h5>
                                                        Carlos Santiyago - 7 hours ago
                                                    </div>
                                                    <div class="media-right">
                                                        <div class="media-response"> Approve?</div>
                                                        <div class="btn-group">
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-check text-success"></i>
                                                            </button>
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-remove"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <a class="media-left" href="#"> <span class="fa fa-user text-info"></span>
                                                    </a>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">New User Registration
                                                            <small class="text-muted"></small>
                                                        </h5>
                                                        Douglas Adams - 11 day ago
                                                    </div>
                                                    <div class="media-right">
                                                        <div class="media-response"> Approve?</div>
                                                        <div class="btn-group">
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-check text-success"></i>
                                                            </button>
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-remove"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <a class="media-left" href="#"> <span
                                                        class="fa fa-info text-alert"></span> </a>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">New Invoice
                                                            <small class="text-muted"></small>
                                                        </h5>
                                                        <a href="#">iPad Air</a> - 14 hours ago
                                                    </div>
                                                    <div class="media-right">
                                                        <div class="media-response single">#1234567</div>
                                                        <button type="button"
                                                            class="btn btn-default btn-sm btn-bordered light">
                                                        <i class="fa fa-link"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="media">
                                                    <a class="media-left" href="#"> <span
                                                        class="fa fa-shopping-cart text-success"></span> </a>
                                                    <div class="media-body">
                                                        <h5 class="media-heading">New Product Order
                                                            <small class="text-muted"></small>
                                                        </h5>
                                                        <a href="#">iPad Air</a> - 14 hours ago
                                                    </div>
                                                    <div class="media-right">
                                                        <div class="media-response"> Confirm?</div>
                                                        <div class="btn-group">
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-check text-success"></i>
                                                            </button>
                                                            <button type="button"
                                                                class="btn btn-default btn-sm btn-bordered light">
                                                            <i class="fa fa-cog"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-footer text-center">
                                        <a href="#" class="btn btn-primary btn-sm btn-bordered"> View All </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
					
                    <li class="dropdown dropdown-fuse">
                        <div class="navbar-btn btn-group">
                            <div  data-toggle="dropdown" class="dropdown-toggle">
                            <span class="fa fa-bell fs26 text-primary"></span>
                            </div>
                            <div  data-toggle="dropdown" class=" dropdown-toggle fs18 visible-xl notify1">
                            8
                            </div>
                            <div class="dropdown-menu keep-dropdown w375 animated animated-shorter fadeIn" role="menu">
                                <div class="panel mbn">
                                    <div class="panel-menu">
                                        <span class="panel-icon"><i class="fa fa-tasks"></i></span>
                                        <span class="panel-title fw600"> Activity reports</span>
                                        <button class="btn btn-default light btn-xs btn-bordered pull-right" type="button"><i
                                            class="fa fa-refresh"></i>
                                        </button>
                                    </div>
                                    <div class="panel-body panel-scroller scroller-navbar scroller-overlay scroller-pn pn">
                                        <ol class="timeline-list">
                                            <li class="timeline-item">
                                                <div class="timeline-icon bg-dark light">
                                                    <span class="fa fa-envelope"></span>
                                                </div>
                                                <div class="timeline-desc">
                                                    <b>John Doe</b> Sent you a message.
                                                    <a href="#">View now</a>
                                                </div>
                                                <div class="timeline-date">11:15am</div>
                                            </li>
                                            <li class="timeline-item">
                                                <div class="timeline-icon bg-success">
                                                    <span class="fa fa-info"></span>
                                                </div>
                                                <div class="timeline-desc">
                                                    <b>Admin</b> created invoice for:
                                                    <a href="#">iPad Air</a>
                                                </div>
                                                <div class="timeline-date">6:26pm</div>
                                            </li>
                                            <li class="timeline-item">
                                                <div class="timeline-icon bg-success">
                                                    <span class="fa fa-info"></span>
                                                </div>
                                                <div class="timeline-desc">
                                                    <b>Admin</b> created invoice for:
                                                    <a href="#">iPhone 5s</a>
                                                </div>
                                                <div class="timeline-date">11:45am</div>
                                            </li>
                                            <li class="timeline-item">
                                                <div class="timeline-icon bg-dark light">
                                                    <span class="fa fa-envelope"></span>
                                                </div>
                                                <div class="timeline-desc">
                                                    <b>Lara Johnes</b> Sent you a message.
                                                    <a href="#">View now</a>
                                                </div>
                                                <div class="timeline-date">3:18pm</div>
                                            </li>
                                            <li class="timeline-item">
                                                <div class="timeline-icon bg-primary">
                                                    <span class="fa fa-star"></span>
                                                </div>
                                                <div class="timeline-desc">
                                                    <b>Richard Johnes</b> Added to wishlist:
                                                    <a href="#">iPhone 5c</a>
                                                </div>
                                                <div class="timeline-date">8:15am</div>
                                            </li>
                                            <li class="timeline-item">
                                                <div class="timeline-icon bg-success">
                                                    <span class="fa fa-info"></span>
                                                </div>
                                                <div class="timeline-desc">
                                                    <b>Admin</b> created invoice for:
                                                    <a href="#">Mac Pro</a>
                                                </div>
                                                <div class="timeline-date">9:29pm</div>
                                            </li>
                                            <li class="timeline-item">
                                                <div class="timeline-icon bg-primary">
                                                    <span class="fa fa-star"></span>
                                                </div>
                                                <div class="timeline-desc">
                                                    <b>Douglas Adams</b> Added to wishlist:
                                                    <a href="#">iPad 4</a>
                                                </div>
                                                <div class="timeline-date">3:05am</div>
                                            </li>
                                        </ol>
                                    </div>
                                    <div class="panel-footer text-center">
                                        <a href="#" class="btn btn-primary btn-sm btn-bordered"> View All </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
					
                    <li class="dropdown dropdown-fuse">
                        <div class="navbar-btn btn-group">
                            <div data-toggle="dropdown" class="btn-md dropdown-toggle">
                            <span class="fa fa-info-circle fs26 text-primary"></span>  
                            </div>
                        </div>
                    </li>
					
					    <li class="dropdown dropdown-fuse">
						<div class="navbar-btn btn-group">
                            <div data-toggle="dropdown" class=" btn-md dropdown-toggle">
                           <span class="fa fa-cogs fs26 text-primary"></span> 
                            </div>
                        </div>
                    </li>  --%>
					
			 
					
					
                    <li class="dropdown dropdown-fuse">
                        <div class="navbar-btn btn-group">
                            <div data-toggle="dropdown" class=" btn-md dropdown-toggle">
                           <a href="doLogout.action"> <span class="fa fa-power-off fs26 text-danger"></span></a>  
                            </div>
                        </div>
                    </li>
                </ul>
            </header>
            <!-- -------------- /Header  -------------- -->
            <!-- -------------- Sidebar  -------------- -->
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
                                        <a href="Add-EMployee.html" class="sidebar-menu-toggle">My Profile-</a> <a href="doLogout.action">Logout</a>
                                    </div>
                                    <div class="media-author">
                                    
                                    <%-- <s:text name="#request.APPL_ROLE_LIST"></s:text> --%>
  									
  									
  									

									
  									 <br>
                                    <s:text name="application.welcome.message"/>
                                    &nbsp;<s:text name="#session.FIRST_NAME"></s:text>&nbsp;
                                    <s:text name="#session.MIDDLE_NAME"></s:text>&nbsp;
                                    <s:text name="#session.LAST_NAME"></s:text>, &nbsp;
                                    <s:text name="#session.ROLE"></s:text>
                                    
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- -------------- Sidebar - Author Menu  -------------- -->
                        <div class="sidebar-widget menu-widget">
                            <div class="row text-center mbn">
                                <div class="col-xs-2 pln prn">
                                    <a href="myHome.action" class="text-primary" data-toggle="tooltip" data-placement="top"
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
                          <li>
                            <a  href="myHome.action">
                            <span class="fa fa-dashboard"></span>
                            <span class="sidebar-title">Dashboard</span>
                            </a>                     
                        </li>
						  <li>
                            <a  href="Configuration.html">
                            <span class="fa fa-cogs"></span>
                            <span class="sidebar-title">Configuration</span>
                            </a>                     
                        </li>
												
                        <li>
                            <a  href="setUpEmployees.action">
                            <span class="fa fa-bar-chart"></span>
                            <span class="sidebar-title">Org Chart</span>
                            </a>
                        </li>
                       <li>
                            <a href="getAllEmp.action">
                            <span class="fa fa-clock-o"></span>
                            <span class="sidebar-title">Timesheet</span>
                            </a>
                        </li>
                        <li>
                            <a href="Absence-Planner.html">
                            <span class="fa fa-check-square-o"></span>
                            <span class="sidebar-title">Absence Planner</span>
                            </a>
                        </li>
                        <li>
                             <a  href="setUpEmployeesNEW.action">
                            <span class="fa fa-bars"></span>
                            <span class="sidebar-title">Projects</span>
                            </a>
                        </li>
                        <li>
                            <a href="Recruit.html">
                            <span class="fa fa-check-circle"></span>
                            <span class="sidebar-title">Recruit</span>
                            </a>
                        </li>
                        <li>
                            <a  href="Paystubs.html">
                            <span class="fa fa-credit-card"></span>
                            <span class="sidebar-title">Paystubs</span>
                            </a>
                        </li>
						
                        <li>
                            <a href="Expenses.html">
                            <span class="fa fa-usd"></span>
                            <span class="sidebar-title">Expenses</span>
                            </a>
                        </li>
						
						
						 <li>
							<a class="accordion-toggle" href="#">
                        <span class="fa fa-comments"></span>
                        <span class="sidebar-title">Chats</span>
		                 </a>
						 </li>
 			   <%-- <div class="sidebar-chat">
											
					<div class="friends-nav">
						<div class="box-friends">
							
							<div class="scroller-friends">
								<div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Richard Nyman
											<small><div class="online"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
								<div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Isaiah Ayrton
											<small><div class="online"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
								<div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Destiny Higgins
											<small><div class="online"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
								<div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Richard Nyman
											<small><div class="online"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
								<div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Isaiah Ayrton
											<small><div class="online"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
								<div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Destiny Higgins
											<small><div class="online"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
                                                                <div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Richard Nyman
											<small><div class="online"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
								<div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Isaiah Ayrton
											<small><div class="offline"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
                                                                <div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Destiny Higgins
											<small><div class="offline"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
								<div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Richard Nyman
											<small><div class="offline"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
                                                                <div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Isaiah Ayrton
											<small><div class="offline"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
								<div class="user-friends">
									<a href="#">
										<div class="pull-left"> <img src="assets/img/avatar.jpg" class="img-circle" alt="User Image"> </div>
										<h4>
											Destiny Higgins
											<small><div class="offline"></div></small>
										</h4>
										<p>Hey dude! Wazzap!?</p>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>   --%>
				

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
                <div id="topbar-dropmenu-wrapper">
                    <div class="topbar-menu row">
                        <div class="col-xs-4 col-sm-2">
                            <a href="#" class="service-box bg-danger">
                            <span class="fa fa-music"></span>
                            <span class="service-title">Audio</span>
                            </a>
                        </div>
                        <div class="col-xs-4 col-sm-2">
                            <a href="#" class="service-box bg-success">
                            <span class="fa fa-picture-o"></span>
                            <span class="service-title">Images</span>
                            </a>
                        </div>
                        <div class="col-xs-4 col-sm-2">
                            <a href="#" class="service-box bg-primary">
                            <span class="fa fa-video-camera"></span>
                            <span class="service-title">Videos</span>
                            </a>
                        </div>
                        <div class="col-xs-4 col-sm-2">
                            <a href="#" class="service-box bg-alert">
                            <span class="fa fa-envelope"></span>
                            <span class="service-title">Messages</span>
                            </a>
                        </div>
                        <div class="col-xs-4 col-sm-2">
                            <a href="#" class="service-box bg-system">
                            <span class="fa fa-cog"></span>
                            <span class="service-title">Settings</span>
                            </a>
                        </div>
                        <div class="col-xs-4 col-sm-2">
                            <a href="#" class="service-box bg-dark">
                            <span class="fa fa-user"></span>
                            <span class="service-title">Users</span>
                            </a>
                        </div>
                    </div>
                </div>
                <!-- -------------- /Topbar Menu Wrapper -------------- -->
                <!-- -------------- Topbar -------------- -->
                <header id="topbar" class="alt">
                    <div class="topbar-left">
                         <ul>
						 <li><i class="fa fa-plus" aria-hidden="true"></i></li>
						 <li>Employee</li>
						  
				</ul>
				 <form class="navbar-form navbar-left search-form square" id="fEmployeeSearch" method="post" role="search">
<!--                     <div class="input-group add-on employ-search">
                        <input type="text" id="employees" class="form-control" placeholder="Search Employee..." onfocus="this.placeholder=''"
                            onblur="this.placeholder='Search Employee...'">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div> -->

				<div class="input-group add-on employ-search">

					<input id="autocompleteEmployee" type="text" class="form-control"
						placeholder="Search Employee..." onfocus="this.placeholder=''"
						onblur="this.placeholder='Search Employee...'">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
				<input type="hidden" name="employee.employeeId"
					id="autocompleteEmployeeId" value=0>


			</form>
                    </div>
                    <div class="topbar-right">
                        <div class="btn-group">
                            <i class="fa fa-plus-circle symbol"></i>
                        </div>
                        
						 <a href="Add-Client.html"><div class="btn-group">
                            <button type="button" class="btn btn-system">
                            <i class="fa fa-users "></i>
                            </button>
                            <p>Customer</p>
                        </div></a>
                         <a  href="setUpEmployeesNEW.action">
	       <div class="btn-group">
		   <button type="button" class="btn  btn-primary" >
		       <i class="fa fa-user"></i>
		   </button>
		       <p>Employee</p>
	       </div>
		   </a>
	       
	       <a href="Projects.html"> <div class="btn-group">
		   <button type="button" class="btn btn-info">
		       <i class="fa fa-home"></i>
		   </button>
		       <p>Project</p>
	       </div></a>
                      <a href="Schedule.html">    <div class="btn-group">
                            <button type="button" class="btn btn-dark">
                            <i class="fa fa-calendar"></i>
                            </button>
                            <p>Schedule</p>
                        </div></a>
                     <a href="Task.html">   <div class="btn-group">
                            <button type="button" class="btn btn-dark">
                            <i class="fa fa-tasks"></i>
                            </button>
                            <p>Task</p>
                        </div></a>
                         <a href="Add-Vendor.html">  <div class="btn-group">
                            <button type="button" class="btn btn-success">
                            <i class="fa fa-female"></i>
                            </button>
                            <p>Vendor</p></a>
                        </div>
                    </div>
                </header>
                <!-- -------------- /Topbar -------------- -->
                <!-- -------------- Content -------------- -->
                <section id="content" class="animated fadeIn avatar-display">
                    <div class="row">
                        <div class="col-md-12 mb40 nopadding-left nopadding-right">
                            <div class="row">
                                <div class="col-md-3 nopadding-left nopadding-right">
                                    <div class="col-sm-6 col-md-6 ">
                                        <a href="#">
                                        <!-- assets/img/avatars/profile_avatar_big.jpg -->
                                        <img id="pImage" alt="..." src="#" class="media-object img-responsive">
                                        </a>
                                    </div>
									
                                    <div class="col-sm-6 col-md-6 profile nopadding-left nopadding-right">
                                        <div class="Profile-Name"><span id="avatar-display-employee.empFirstName"><s:property value="employee.empFirstName" /></span>
                                        
                                         <span id="avatar-display-employee.empLastName"> <s:property value="employee.empLastName" /></span></div>
                                        <ul>
                                            <li><i class="fa fa-phone border"></i>
                                             <span id="avatar-display-employee.empMobile"> 
                                             <s:set name="empMobile" value="employee.empMobile"/>
                                             <s:if test="%{#empMobile!=''}">
	<s:property value="employee.empMobile" />
</s:if> <s:else>
    -
</s:else>
</span>
                                             </li>
                                            <li><i class="fa fa-calendar border"></i> <span id="avatar-display-employee.empBirthDate">
                                            <s:property value="employee.empBirthDate" /></span></li>
                                            <li><i class="fa fa-user border"></i><%-- <s:property value="employee.empGender" /> --%>
                                             <span id="avatar-display-employee.empGender">
                                            <s:set name="empGender" value="employee.empGender"/>
											<s:if test="%{#empGender==0}">
	Male
</s:if> <s:elseif test="%{#empGender==1}">
    Female
</s:elseif> <s:else>
    -
</s:else>

</span></li>
                                        </ul>
                                    </div>
                                    <div class="row upload-picture">
                                        <div class="col-md-12 mt10 mb40 ">
                                            <button type="button" class="btn  btn-primary  ml10 " data-style="expand-right">
                                           <!--  <input type="file" onchange="readURL(this);" class="btn  btn-primary  ml10 " data-style="expand-right"> -->
                                            <span class="ladda-label"><i class="fa fa-cloud-upload" aria-hidden="true"></i> Upload Picture</span>
                                            <span class="ladda-spinner"></span></button>
											
																			
								 <div class="btn-group">
                                    <button type="button" class="btn  btn-primary dropdown-toggle ml10" data-toggle="dropdown">
                                        <i class="fa fa-cloud-upload " aria-hidden="true"></i> Resume
                                        <span class="caret ml5"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li>
                                            <a href="#"><i class="fa fa-file-word-o" aria-hidden="true"></i>
  Upload Resume</a>
                                        </li>
										 <li class="divider"></li>
                                        <li>
                                            <a href="#"> <i class="fa fa-building" aria-hidden="true"></i>
 Build Resume</a>
                                        </li>
                                          
                                    </ul>
                                </div>
								
		     </div>
										 
										
                                    </div>
									
									<div id="status">
									<a href="HR-login-for-Onboarding.html" type="button" class="btn btn-danger ml15 mb40 ">Pending Signature</a>
									</div>
									
                                    <div class="row">
                                        <div class="col-md-12 mb40">
                                            <h2 class="media-heading fs20 mb20">About Us</h2>
                                           <!--  <p class="fw400">
                                                Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse
                                                molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros
                                                et accumsan et iusto.  
                                            </p>
                                            <p class="fw400">Typi non habent claritatem insitam; est usus legentis in iis
                                                qui facit eorum claritatem. Investigationes demonstraverunt lectores legere
                                                me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui
                                                sequitur mutationem consuetudium lectorum. 
                                            </p> -->
										
										
                                        </div>
  
                                    </div>
                                </div>
								
                                <div class="col-md-9">
								<div class="row text-right">
								<a href="HR-login-for-Onboarding.html"  class="btn btn-danger mr20 mb10 " >Onboarding Pending</a>
                                       
                                        
                                   <!--  </button>  -->
									</div>
									
<%-- 									  <%
                 List<String> empNameArray = new ArrayList<String>();
                List<Integer> empIdArray = new ArrayList<Integer>();
                for(EmployeesVO employeesVO : employees){
                	empNameArray.add(employeesVO.getEmpFirstName());
                	empIdArray.add(employeesVO.getEmployeeId());
                }%> --%>
               <!--  <input id="testId" type="text" value=/> -->
               <%-- <div id=testId> 
                 <span>"<% out.println(empNameArray); %>" </span>
                </div> --%>
							  <!-- <div id=testId> -->
                   <%--  <span><s:property value="#session.APPL_EMPLOYEE_LIST_1" /></span> --%>
                 <!--  </div> -->
                    <!-- <button  onclick="sessdata()"> Click</button>	 -->
								
                                    <div class="panel-group" id="accordion">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                                    Profile
                                                    </a>
                                                </h4>
                                            </div>
                                            <!--/.panel-heading -->
                                            <div id="collapseOne" class="panel-collapse collapse in">
                                                <div class="panel-body">
															
															
 				<!-- 					    <div class="allcp-form">
										<div class="section">
                                            <label for="timepicker1" class="field prepend-icon">
                                                <input type="text" id="timepicker1" name="timepicker1"
                                                       class="gui-input"
                                                       placeholder="Timepicker Popup">
                                                <label class="field-icon">
                                                    <i class="imoon imoon-clock"></i>
                                                </label>
                                            </label>
                                        </div>
										
										
										  <div class="section">
                                            <label for="datepicker1" class="field prepend-icon">
                                                <input type="text" id="datepicker1" name="datepicker1"
                                                       class="gui-input"
                                                       placeholder="Datepicker Popup">
                                                <label class="field-icon">
                                                    <i class="fa fa-calendar"></i>
                                                </label>
                                            </label>
                                        </div>
										
									 									
										</div>	  -->
 
												
												
                                                    <div class="first-row profile-Editicon">
                                                        <div class="col-md-5 row-left"></div>
                                                        <div class="col-md-7 row-right" id="edit"> <i class="fa fa-pencil-square-o fs26 text-primary" aria-hidden="true"
														id="editbutton" ></i>  <i id="savebutton" style="display:none;" class="fa fa-floppy-o fs26 text-danger" aria-hidden="true"></i>
													 
														</div>
                                                    </div>
													
													
                                                    <div class="default emp-profile">
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">First Name :</div>
                                                            <div class="col-md-6 row-right"><span id="display-employee.empFirstName">
                                                            <s:property value="employee.empFirstName" /></span></div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Last Name :</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-employee.empLastName"><s:property value="employee.empLastName" /></span>
                                                            </div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Location :</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-employee.empCityName"><s:property value="employee.empCityName" /></span>
                                                            </div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Email:</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-employee.empWorkEmail"><s:property value="employee.empWorkEmail" /></span>
                                                            </div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Contact Number:</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-employee.empMobile"><s:property value="employee.empMobile" /></span>
                                                            </div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Work Authorization:</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-employee.empCounName"> <s:property value="employee.empCounName" />
                                                            </div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Gender:</div>
                                                            <div class="col-md-6 row-right"><%-- <s:property value="employee.empGender" /> --%>
                                                            <span id="display-employee.empGender">
                                                                                                        <s:set name="empGender" value="employee.empGender"/>
											<s:if test="%{#empGender==0}">
	Male
</s:if> <s:elseif test="%{#empGender==1}">
    Female
</s:elseif> <s:else>
    -
</s:else>
</span>
</div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Employee Type:</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-employee.empType"><s:property value="employee.empType" />
                                                            </span>
                                                            </div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Department:</div>
                                                            <div class="col-md-6 row-right"><span id="display-employee.employee.departmentIdObj.deptName">
                                                            <s:property value="employee.departmentIdObj.deptName" /></span></div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Role:</div>
                                                            <div class="col-md-6 row-right"><span id="display-employee.employee.roleObj.roleName">
                                                            <s:property value="employee.roleObj.roleName" /></span></div>
                                                        </div>
														<div class="first-row">
														<div class="col-md-5 row-left">Rlite Access</div>
														 <div class="col-md-6 row-right">
													   <form class="allcp-form theme-primary">
                                                        <label class="block option option-primary" id="rliteaccess">
                                                       	
																	<%-- <span id="display-employee.employee.rLiteAccess"> --%>
																	<s:set name="empRliteAccess" value="employee.rLiteAccess"/>
																	
																	<s:if test="%{#empRliteAccess==true}">
	<input type="checkbox" name="checked" value="checked" checked=""> <span class="checkbox"></span>
</s:if> <s:elseif test="%{#empRliteAccess==false}">
    <input type="checkbox" name="checked" value="false"> <span class="checkbox"></span>
</s:elseif> <s:else>
   <input type="checkbox" name="checked" value="false"> <span class="checkbox"></span>
</s:else>

<%-- </span> --%>
</label>
                                                     
														</form>
														</div>
													 </div>


                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Access Type:</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-employee.accessType">
                                                            <s:property value="employee.accessType" /></span>
                                                                                                                       </div>
                                                        </div>
													<!-- 	 <div class="first-row">
                                                            <div class="col-md-5 row-left">Social Security No:</div>
                                                            <div class="col-md-6 row-right">******345</div>
                                                        </div> -->   
														
														
														
                                                    </div>
													
                                                    <div class="showform">
                                                        <form class="allcp-form theme-primary side-popup" id="fEmployee">
                                                             <s:hidden key="employee.employeeId" id="employee.employeeId"/>
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    First Name:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <!-- <input type="text" name="from" id="from" class="gui-input" placeholder="First Name"> -->
                                                                            <s:textfield key="employee.empFirstName"
                                                                             id="employee.empFirstName" name="employee.empFirstName" cssClass="gui-input" placeholder="First Name"/>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Last Name:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <!-- <input type="text" name="from" id="from" class="gui-input" placeholder="Last Name"> -->
                                                                             <s:textfield key="employee.empLastName" id="employee.empLastName" 
                                                                             cssClass="gui-input" placeholder="Last Name"/>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Location :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                           <!--  <input type="text" name="from" id="from" class="gui-input" placeholder="Location"> -->
                                                                            <s:textfield key="employee.empCityName" id="employee.empCityName" 
                                                                            cssClass="gui-input" placeholder="Location"/>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                          
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Email:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                           <!--  <input type="text" name="from" id="from" class="gui-input" placeholder="Email Id"> -->
                                                                            <s:textfield key="employee.empWorkEmail" id="employee.empWorkEmail" cssClass="gui-input" placeholder="Email Id"/>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10 ">
                                                                    Contact Number:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                           <!--  <input type="text" name="from" id="from" class="gui-input" placeholder="Contact Number"> -->
                                                                             <s:textfield key="employee.empMobile" id="employee.empMobile" cssClass="gui-input" placeholder="Contact Number"/>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                             
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Work Authorization:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <!-- <input type="text" name="from" id="from" class="gui-input" placeholder="Work Authorization"> -->
                                                                            <s:textfield key="employee.empCounName" 
                                                                            id="employee.empCounName" cssClass="gui-input"
                                                                             placeholder="Work Authorization"/>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            
                                                            <!-- -------------- /section -------------- -->
                                                           
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Date of Birth:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="employee.empBirthDate" id="empBirthDate"
                                                                             class="gui-input themeDatepicker"  placeholder="Date of Birth"  format="dd/MM/yyyy">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10 ">
                                                                    Gender:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                            <%--     <select name="Select" class="empty">
                                                                                    <option value="0">Male</option>
                                                                                    <option value="1">Female</option>
                                                                                </select> --%>
                                                                                <s:select label="Select a Gender" 
		list="#{0:'Male', 1:'Female'}" 
		name="employee.empGender" key="employee.empGender" id="employee.empGender"
		value="1" />
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                             
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10 ">
                                                                    Employee Type:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                           <!--  <input type="text" name="from" id="from" class="gui-input" placeholder="Employee Type"> -->
                                                                            <s:textfield key="employee.empType" id="employee.empType" cssClass="gui-input" placeholder="Employee Type"/>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                       
                                                            <!-- -------------- /section -------------- -->
                                                          
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Department:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                           <label class="field">
                                                                                <%-- <select name="employee.departmentIdObj.hcmoDepartmentId"
                                                                                 id="employee.departmentIdObj.hcmoDepartmentId" class="empty">
                                                                                    <option value="0">Developement</option>
                                                                                    <option value="1">Design</option>
                                                                                </select>
                                                                                <i class="arrow double"></i> --%>


															<input id="autocomplete-department-profile" type="text"
															class="gui-input" placeholder="Department"
															onfocus="this.placeholder=''"
															onblur="this.placeholder='Department'"> <input
															type="hidden"
															name="employee.departmentIdObj.hcmoDepartmentId"
															id="autocomplete-departmentId-profile" value=0>
														</label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                          
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10 ">
                                                                    Role:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
<%--                                                                                 <select name="employee.roleObj.hcmoRoleId" id="employee.roleObj.hcmoRoleId" class="empty">
                                                                                    <option value="0">Developer</option>
                                                                                    <option value="1">Designer</option>
                                                                                </select>
                                                                                <i class="arrow double"></i> --%>
                                                                                
                                                     <input id="autocomplete-role-profile" type="text"
															class="gui-input" placeholder="Role"
															onfocus="this.placeholder=''"
															onblur="this.placeholder='Role'"> <input
															type="hidden"
															name="employee.roleObj.hcmoRoleId"
															id="autocomplete-roleId-profile" value=0>
															
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																<div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10 ">
                                                                    Rlite Access:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                              <label class="block mt15 option option-primary"  name="employee.rLiteAccess" 
                                                                              id="employee.rLiteAccess">
                                                        <input type="checkbox" checked="" value="checked" name="checked">
                                                        <span class="checkbox"></span> </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																
																
															 
                                                           
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mn">
                                                                    <label class="field-label col-sm-4 nopadding-left">
                                                                    Access Type:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <!-- <input type="text" name="from" id="from" class="gui-input" placeholder="Access Type"> -->
                                                                             <s:textfield key="employee.accessType" id="employee.accessType" cssClass="gui-input" placeholder="Access Type"/>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                        
                                                            <!-- -------------- /section -------------- -->
                                                           
                                                           <!--         <div class="section row mn">
                                                                    <label class="field-label col-sm-4 nopadding-left">
                                                                    Social Security No:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="from" id="from" class="gui-input" placeholder="Social Security No">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div> -->
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                        </form>
                                                    </div>
                                                    <!-- Button trigger modal -->
                                             
													
													
                                                 
                                                    
                                                    <!--/.panel-body -->
                                                </div>
                                                <!--/.panel-collapse -->
                                            </div>
                                            <!-- /.panel -->
											
											
											
						 

                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#Addproject">
                                                        Project
                                                        </a>
                                                    </h4>
                                                </div>
												
                                                <!--/.panel-heading -->
												
                                      <div id="Addproject" class="panel-collapse collapse">
                                       <div class="panel-body">
									   
									   <!-- project -->
									 <div class="panel panel-default">
									   <div class="panel-heading">
										  <h4 class="panel-title">
											 <a class="collapsed" data-toggle="collapse" data-parent="#nested" href="#project">
											 Project
											 </a>
										  </h4>
									   </div><!--/.panel-heading -->
									   <div id="project" class="panel-collapse collapse">
										  <div class="panel-body">
										       <div class="first-row profile-Editicon">
                                                        <div class="col-md-5 row-left"></div>
                                                        <div class="col-md-7 row-right" id="edit"> <i class="fa fa-pencil-square-o fs26 text-primary" aria-hidden="true"
														id="editbutton4"></i>  <i id="savebutton4" style="display:none;" onclick="projectSave()" class="fa fa-floppy-o fs26 text-danger" aria-hidden="true"></i>
													 
														</div>
                                                    </div>
													
													                                                      
													<div class="default4">
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Department: </div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-project.employee.assProj.departmentIdObj.hcmoDepartmentId">
                                                            <s:property value="employee.assProj.departmentIdObj.deptName" /></span></div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Bill Payment:</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-project.employee.assProj.isBillable">
                                                            <s:property value="employee.assProj.isBillable" /></span>	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Client Name:</div>
                                                            <div class="col-md-6 row-right"><span id="display-project.employee.assProj.customerIdObj.customerId">
                                                            <s:property value="employee.assProj.customerIdObj.customerName" /></span></div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Project Name :</div>
                                                            <div class="col-md-6 row-right"><span id="display-project.employee.assProj.projectName.projectId">
                                                            <s:property value="employee.assProj.projectName.projectId" /></span></div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Project Manager :</div>
                                                            <div class="col-md-6 row-right"><span id="display-project.employee.assProj.projOwnerEmpIdObj.employeeId">
                                                            <s:property value="employee.assProj.projOwnerEmpIdObj.employeeId" /></span></div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Estimated Hours  :</div>
                                                            <div class="col-md-6 row-right"><span id="display-project.employee.assProj.allocatedHours">
                                                            <s:property value="employee.assProj.allocatedHours" /></span></div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">From Date  :</div>
                                                            <div class="col-md-6 row-right"><span id="display-project.employee.assProj.projectStartDate">
                                                            <s:property value="employee.assProj.projectStartDate" /></span></div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">End Date:</div>
                                                            <div class="col-md-6 row-right"><span id="display-project.employee.assProj.projectEndDate">
                                                            <s:property value="employee.assProj.projectEndDate" /></span></div>
                                                        </div>
														<div class="first-row mb30">
                                                            <div class="col-md-5 row-left">Description:</div>
                                                            <div class="col-md-6 row-right"><span id="display-project.employee.assProj.description">
                                                            <s:property value="employee.assProj.description" /></span></div>
                                                        </div>
														
													
														
														<div class="table-responsive mb30">
														
									 
                                                            <table class="table allcp-form theme-warning tc-checkbox-1 fs13">
                                                                <thead>
                                                                    <tr class="bg-dark">
																      <th class="">Department</th>
                                                                        <th class="">ClientName</th>
                                                                        <th class="">Project Name</th>
                                                                        <th class="">Project Manager</th>
                                                                        <th class="">Estimated Hours</th>
                                                                        <th class="">Start Date</th>
																		 <th class="">End Date</th>
																		 <th class="">Description</th>
																		 <th class="">Edit</th>
																		 <th class="">Delete</th>
																		
                                                                        </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
																	  <td class=""> CSE </td>
                                                                        <td class=""> Karishma </td>
																		<td class="">Pelicon Heihlight</td>
																		<td class="">Kashave</td>
                                                                        <td class="">48hrs</td>
                                                                       	<td class="">22/05/2016</td>
																		<td class="">24/05/2016</td>
																		<td class="">Duis autem vel eum iriure dolor</td>
																		<td class="text-center"><i aria-hidden="true" class="fa fa-pencil fs18"></i>
																		</td>
																		<td data-target="#myModal12" data-toggle="modal" class="text-center ">
																		<i aria-hidden="true" class="fa fa-trash fs18"></i></td>
																		 
                                                                    </tr>
                                                                  <tr>
																	  <td class=""> CSE </td>
                                                                        <td class=""> Karishma </td>
																		<td class="">Pelicon Heihlight</td>
																		<td class="">Kashave</td>
                                                                        <td class="">48hrs</td>
                                                                       	<td class="">22/05/2016</td>
																		<td class="">24/05/2016</td>
																		<td class="">Duis autem vel eum iriure dolor</td>
																		<td class="text-center"><i aria-hidden="true" class="fa fa-pencil fs18"></i>
																		</td>
																		<td data-target="#myModal12" data-toggle="modal" class="text-center ">
																		<i aria-hidden="true" class="fa fa-trash fs18"></i></td>
																		 
                                                                    </tr>
                                                                  <tr>
																	  <td class=""> CSE </td>
                                                                        <td class=""> Karishma </td>
																		<td class="">Pelicon Heihlight</td>
																		<td class="">Kashave</td>
                                                                        <td class="">48hrs</td>
                                                                       	<td class="">22/05/2016</td>
																		<td class="">24/05/2016</td>
																		<td class="">Duis autem vel eum iriure dolor</td>
																		<td class="text-center"><i aria-hidden="true" class="fa fa-pencil fs18"></i>
																		</td>
																		<td data-target="#myModal12" data-toggle="modal" class="text-center ">
																		<i aria-hidden="true" class="fa fa-trash fs18"></i></td>
																		 
                                                                    </tr>
																	
                                                                </tbody>
																 
                                                            </table>
														 
                                                        </div>

														
												</div>

							<div class="showform4 allcp-form">
							<form id="projSave">
							
																								<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                   Department :  </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field select">
                                                                                                            <select 
                                                                                                            name="employee.assProj.departmentIdObj.hcmoDepartmentId"
																		id="employee.assProj.departmentIdObj.hcmoDepartmentId" class="empty">
                                                                                                                <option selected="selected" value="1">Department1
                                                                                                                </option>
                                                                                                                <option value="1">Department2</option>
                                                                                                                <option value="2"> Department3</option>
                                                                                                            </select>
                                                                                                            <i class="arrow double"></i>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </div>
																								
																								<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                   Bill Payment :  </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                     
                                                                                                    <div class="col-sm-6 nopadding-left ">
																									<label class="block mt15 option option-primary">
																						<input type="checkbox" value=1 name="employee.assProj.isBillable" 
																						id="employee.assProj.isBillable">
																							<span class="checkbox"></span>Billable  </label>
 
                                                                                                    </div>
															<%-- 										  <div class="col-sm-6 nopadding-left ">
																									<label class="block mt15 option option-primary">
																						<input type="checkbox" value="CH" name="inputname">
																							<span class="checkbox"></span>Non Billable  </label>
 
                                                                                                    </div> --%>
                                                                                                	 
                                                                                                    </div>
                                                                                                </div>
																								
																								<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    Client Name :  </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field select">
                                                                                                            <select name="employee.assProj.customerIdObj.customerId" id="employee.assProj.customerIdObj.customerId" class="empty">
                                                                                                                <option selected="selected" value="1">Raj Mohan
                                                                                                                </option>
                                                                                                                <option value="1">Test Name1</option>
                                                                                                                <option value="2"> Test Name2</option>
                                                                                                            </select>
                                                                                                            <i class="arrow double"></i>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </div>
																								
																								<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">Project Name :
                                                                                                     </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field select">
                                                                                                            <select name="employee.assProj.projectName.projectId"
                                                                                                             id="employee.assProj.projectName.projectId" class="empty">
                                                                                                                <option selected="selected" value="1">Vendor Management System
                                                                                                                </option>
                                                                                                                <option value="1">Student Management System</option>
                                                                                                                <option value="2"> Resource Management</option>
                                                                                                            </select>
                                                                                                            <i class="arrow double"></i>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </div>
																								
																								<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    Project Manager : </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field select">
                                                                                                            <select name="employee.assProj.projOwnerEmpIdObj.employeeId" 
                                                                                                            id="employee.assProj.projOwnerEmpIdObj.employeeId" class="empty">
                                                                                                                <option selected="selected" value="0">Vendor Manager  </option>
                                                                                                                <option value="1">Resource manager</option>
                                                                                                                <option value="2"> Test manager</option>
                                                                                                            </select>
                                                                                                            <i class="arrow double"></i>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </div>
																								
																								<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">Estimated Hours :</label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label for="timepicker1" class="field prepend-icon">
                                                                                                        <input type="text" name="employee.assProj.allocatedHours"
																		id="#date" class="gui-input themeTimepicker" placeholder="Estimated Hours">
                                                                                                        <label class="field-icon">
                                                                                                        <i class="fa fa-clock-o"></i>
                                                                                                        </label>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </div>
						 																		<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    From Date :</label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                            <label class="field prepend-icon" for="datepicker1">
                                                                                                                <input type="text" name="employee.assProj.projectStartDate"	id="employee.assProj.projectStartDate" class="gui-input themeDatepicker" placeholder="From Date">
                                                                                                                 <label class="field-icon">                                                                                   
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>  
                                                                                                                </label>                                                                                                              
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div> 
                                                                                                
																								<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">End Date :</label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" name="employee.assProj.projectEndDate"
																				id="employee.assProj.projectEndDate" class="gui-input themeDatepicker " placeholder="End Date">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
								 	<div class="section row mb20">
                                    <label class="field-label col-sm-4 ph10 text-left" for="store-tos">Description :</label>

                                    <div class="col-sm-7 ph7">
                                        <label class="field prepend-icon">
                                <textarea placeholder="Terms of service conditions..." name="employee.assProj.description" id="employee.assProj.description" class="gui-textarea"></textarea>
                                            <label class="field-icon" for="store-tos">
                                                <i class="fa fa-align-justify"></i>
                                            </label>
                                        </label>
                                    </div>
                                </div>

					<!-- <input type="hidden" id="employee.employeeId"
																name="employee.assProj.employeeName.employeeId" value="18">	 -->	
			</form>

							</div>												
														
														
														
														
														
														
														
														
																	
																
																
										  </div><!--/.panel-body -->
									   </div><!--/.panel-collapse -->
									</div><!-- /.panel -->
									
									   <!-- project -->
									   
									   
									     <!-- task -->
																		   
									   <!-- project -->
									 <div class="panel panel-default">
									   <div class="panel-heading">
										  <h4 class="panel-title">
											 <a class="collapsed" data-toggle="collapse" data-parent="#nested" href="#task">
											 Task
											 </a>
										  </h4>
									   </div><!--/.panel-heading -->
									   <div id="task" class="panel-collapse collapse">
										  <div class="panel-body">
										  
													 <div class="first-row profile-Editicon">
                                                        <div class="col-md-5 row-left"></div>
                                                        <div class="col-md-7 row-right" id="edit"> <i class="fa fa-pencil-square-o fs26 text-primary" aria-hidden="true"
														id="editbutton3"></i>  
														<i id="savebutton3" style="display:none;" onclick="taskSave()" class="fa fa-floppy-o fs26 text-danger" aria-hidden="true"></i>
														</div>
                                                    </div>
													
													<div class="default3">
														
												<div class="first-row">
                                                            <div class="col-md-5 row-left">Department: </div>
                                                            <div class="col-md-6 row-right">
                                                             <span id="display-task.employee.proActivity.departmentIdObj.deptName">
                                                             <s:property value="employee.proActivity.departmentIdObj.deptName" /></span>
                                                            </div>
                                                        </div>
														
														
												<div class="first-row">
                                                            <div class="col-md-5 row-left">Project Name: </div>
                                                            <div class="col-md-6 row-right"> 
                                                            <span id="display-task.employee.proActivity.proObj.projectName">
                                                             <s:property value="employee.proActivity.proObj.projectName" /></span>
                                                             </div>
                                                        </div>
											
											<div class="first-row">
                                                            <div class="col-md-5 row-left">Task Name: </div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-task.employee.proActivity.activityName">
                                                             <s:property value="employee.proActivity.activityName" /></span>
                                                            </div>
                                                        </div>
											<div class="first-row">
                                                            <div class="col-md-5 row-left">Estimated Hours : </div>
                                                            <div class="col-md-6 row-right">
                                                             <span id="display-task.employee.proActivity.estimatedHours">
                                                             <s:property value="employee.proActivity.estimatedHours" /></span>
                                                            </div>
                                                        </div>
														<div class="first-row">
                                                            <div class="col-md-5 row-left">From Date  :</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-task.employee.proActivity.taskStartDate">
                                                             <s:property value="employee.proActivity.taskStartDate" /></span>
                                                            </div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">End Date:</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-task.employee.proActivity.taskEndDate">
                                                            <s:property value="employee.proActivity.taskEndDate" /> 
                                                            </span>
                                                            </div>
                                                        </div>
														<div class="first-row mb20">
                                                            <div class="col-md-5 row-left">Description:</div>
                                                            <div class="col-md-6 row-right">
                                                             <span id="display-task.employee.proActivity.description">
                                                             <s:property value="employee.proActivity.description" /> </span>
                                                            </div>
                                                        </div>
														
														
														
														<div class="table-responsive mb30">
														
										 
												  
												  
                                                            <table class="table allcp-form theme-warning tc-checkbox-1 fs13">
                                                                <thead>
                                                                    <tr class="bg-dark">
                                                                        <th class="">Department</th>
                                                                        <th class="">Project Name</th>
                                                                        <th class="">Task Name</th>
																		<th class="">Estimation Hours</th>
																		<th class="">Start Time</th>
                                                                        <th class="">End Time</th>
                                                                        <th class="">Description</th>
																		  <th class="">Edit</th>
																		    <th class="">Delete</th>
																	 
																		
                                                                        </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td class=""> Management Department</td>
																		<td class="">Editing the records</td>
																		<td class="">Management System</td>
																		<td class="">48hrs</td>
                                                                       	<td class="">22/05/2016</td>
																		<td class="">24/05/2016</td>
                                                                        <td class="">Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum. </td>
                                                                     <td class="text-center"><i aria-hidden="true" class="fa fa-pencil fs18"></i>
																		</td>
																		<td data-target="#myModal12" data-toggle="modal" class="text-center ">
																		<i aria-hidden="true" class="fa fa-trash fs18"></i></td>
                                                                    </tr>
																		<tr>
                                                                        <td class=""> Management Department</td>
																		<td class="">Editing the records</td>
																		<td class="">Management System</td>
																		<td class="">48hrs</td>
                                                                       	<td class="">22/05/2016</td>
																		<td class="">24/05/2016</td>
                                                                        <td class="">Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum. </td>
                                                                      <td class="text-center"><i aria-hidden="true" class="fa fa-pencil fs18"></i>
																		</td>
																		<td data-target="#myModal12" data-toggle="modal" class="text-center ">
																		<i aria-hidden="true" class="fa fa-trash fs18"></i></td>
                                                                    </tr>
                                                                  <tr>
                                                                        <td class=""> Management Department</td>
																		<td class="">Editing the records</td>
																		<td class="">Management System</td>
																		<td class="">48hrs</td>
                                                                       	<td class="">22/05/2016</td>
																		<td class="">24/05/2016</td>
                                                                        <td class="">Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum. </td>
                                                                     <td class="text-center"><i aria-hidden="true" class="fa fa-pencil fs18"></i>
																		</td>
																		<td data-target="#myModal12" data-toggle="modal" class="text-center ">
																		<i aria-hidden="true" class="fa fa-trash fs18"></i></td>
                                                                    </tr>

																	
                                                                </tbody>
                                                            </table>
															
														
 
                                                                                        
										
								                          </div>
														  
					  </div>
				<form id="taskForm">	  
					  <div class="showform3 allcp-form">
					  
																		<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                   Department :  </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field select">
                                                                                                            <select name="employee.proActivity.departmentIdObj.hcmoDepartmentId"
																		id="employee.proActivity.departmentIdObj.hcmoDepartmentId" class="empty">
                                                                                                                <option selected="selected" value="1">Department1
                                                                                                                </option>
                                                                                                                <option value="1">Department2</option>
                                                                                                                <option value="2"> Department3</option>
                                                                                                            </select>
                                                                                                            <i class="arrow double"></i>
                                                                                                        </label>
                                                                                                    </div>
                                                                       </div>
																	   <input type="hidden"  name="employee.proActivity.empIdObj.employeeId" id="employee.proActivity.empIdObj.employeeId" value="18">
																	   <div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                Project  Name :  </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field select">
                                                                                                            <select name="employee.proActivity.proObj.projectId"
																		id="employee.proActivity.proObj.projectId" class="empty">
                                                                                                                <option selected="selected" value="1">Vendor Manager  </option>
                                                                                                                <option value="1">Resource manager</option>
                                                                                                                <option value="2"> Test manager</option>
                                                                                                            </select>
                                                                                                            <i class="arrow double"></i>
                                                                                                        </label>
                                                                                                    </div>
                                                                        </div>
																		
																		<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                     Task Name :   </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field select">
                                                                                                            <select name="employee.proActivity.activityName"
																		id="employee.proActivity.activityName"  class="empty">
                                                                                                                <option selected="selected" value="1">Vendor Manager  </option>
                                                                                                                <option value="1">Resource manager</option>
                                                                                                                <option value="2"> Test manager</option>
                                                                                                            </select>
                                                                                                            <i class="arrow double"></i>
                                                                                                        </label>
                                                                                                    </div>
                                                                        </div>
																		
																		<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">Estimated Hours :</label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label for="timepicker1" class="field prepend-icon">
                                                                                                        <input type="text"  id="employee.proActivity.estimatedHours"
																		name="employee.proActivity.estimatedHours" class="gui-input themeTimepicker" placeholder="Start Time">
                                                                                                        <label class="field-icon">
                                                                                                        <i class="fa fa-clock-o"></i>
                                                                                                        </label>
                                                                                                        </label>
                                                                                                    </div>
                                                                        </div>
																		
																		<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    From Date :</label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="employee.proActivity.taskStartDate"
																				name="employee.proActivity.taskStartDate" class="gui-input themeDatepicker" placeholder="From Date">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                        </div>
																		
																		
																		<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">End Date :</label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text"  id="employee.proActivity.taskEndDate"
																				name="employee.proActivity.taskEndDate" class="gui-input themeDatepicker" placeholder="End Date">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                            </div>
																			
																			
																			
																			
																			
																			<div class="section row mb20">
                                    <label class="field-label col-sm-4 ph10 text-left" for="store-tos">Description :</label>

                                    <div class="col-sm-7 ph7">
                                        <label class="field prepend-icon">
                        <textarea placeholder="Terms of service conditions..." name="employee.proActivity.description"
																			id="employee.proActivity.description"  class="gui-textarea"></textarea>
                                            <label class="field-icon" for="store-tos">
                                                <i class="fa fa-align-justify"></i>
                                            </label>
                                        </label>
                                    </div>
                                </div>
																								
																								
							<input type="hidden" id="employee.employeeId"
																name="employee.employeeId" value="18">		
									
									
									
					  </div>
								  					
														
											
									</form>					
														
										  </div><!--/.panel-body -->
									   </div><!--/.panel-collapse -->
									</div><!-- /.panel -->
									
									   <!-- task -->
									
									
									  <!-- approver -->
																		   
									   <!-- project -->
									 <div class="panel panel-default">
									   <div class="panel-heading">
										  <h4 class="panel-title">
											 <a class="collapsed" data-toggle="collapse" data-parent="#nested" href="#approver">
											 Approver
											 </a>
										  </h4>
									   </div><!--/.panel-heading -->
									   <div id="approver" class="panel-collapse collapse">
										  <div class="panel-body">
										   		  <div class="first-row profile-Editicon">
                                                        <div class="col-md-5 row-left"></div>
                                                        <div class="col-md-7 row-right" id="edit"> <i class="fa fa-pencil-square-o fs26 text-primary" aria-hidden="true"
														id="editbutton7"></i>  <i id="savebutton7" onclick="saveapprover()" style="display:none;" class="fa fa-floppy-o fs26 text-danger" aria-hidden="true"></i>
													 
														</div>
                                                    </div>
													
													 <div class="default7">
													
													<div class="first-row">
                                                            <div class="col-md-5 row-left">Approver: </div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-approver.employee.assProj.allocatedHours">
                                                            <s:property value="expApprover.hcmoApprovingEmpId.employeeId" /></span>
                                                            </div>
                                                        </div>	
															<div class="first-row">
                                                            <div class="col-md-5 row-left">Choosed: </div>
                                                            <div class="col-md-6 row-right">	Timesheet</div>
                                                        </div>
													</div>
																
												<div class="showform7 allcp-form">
														<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                Approver:  </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field select">
                                                                                                            <select name="expApprover.hcmoApprovingEmpId.employeeId" id="approverid" class="empty">
                                                                                                                <option selected="selected" value="0">Mangaer </option>
                                                                                                                <option value="1">Director</option>
                                                                                                                <option value="2"> Test manager</option>
                                                                                                            </select>
                                                                                                            <i class="arrow double"></i>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <input type="hidden" id="timesheetAppId"
														name="timeSheetApprover.hcmoEmployeeId.employeeId"
														value="18"> 
														<input type="hidden" id="expAppId" name="expApprover.hcmoEmployeeId.employeeId" value="18">
																								
														<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                Choose:  </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                         <div class="col-md-6 nopadding-left">
                                                                                                    <div class="section">
                                                                                                        <label class="block mt15 option option-primary">
                                                                                                        <input type="checkbox" name="inputname" id="timesheet">
                                                                                                        <span class="checkbox"></span>Time sheet</label>
                                                                                                    </div>
																									</div>
																									
																									<div class="col-md-6">
                                                                                                    <div class="section">
                                                                                                        <label class="block mt15 option option-primary">
                                                                                                        <input type="checkbox" name="inputname" id="expensess">
                                                                                                        <span class="checkbox"></span>  Expense</label>
                                                                                                    </div>
																							    </div>
																								
                                                                                                    </div>
                                                                                                </div>
																								
																								
														
												</div>
														
										  </div><!--/.panel-body -->
									   </div><!--/.panel-collapse -->
									</div><!-- /.panel -->
									
									   <!-- approver -->
									   
									     <!-- shift -->
																		   
									   <!-- project -->
									 <div class="panel panel-default">
									   <div class="panel-heading">
										  <h4 class="panel-title">
											 <a class="collapsed" data-toggle="collapse" data-parent="#nested" href="#shift">
											 Shift
											 </a>
										  </h4>
									   </div><!--/.panel-heading -->
									   <div id="shift" class="panel-collapse collapse">
										  <div class="panel-body">
										   <div class="first-row profile-Editicon">
                                                        <div class="col-md-5 row-left"></div>
                                                        <div class="col-md-7 row-right" id="edit"> <i class="fa fa-pencil-square-o fs26 text-primary" aria-hidden="true"
														id="editbutton8"></i>  <i id="savebutton8" onclick="shiftSave()" style="display:none;" class="fa fa-floppy-o fs26 text-danger" aria-hidden="true"></i>
													 
														</div>
                                                    </div>
													<div class="default8">
										  
													<div class="first-row">
                                                            <div class="col-md-5 row-left">Shift Timing: </div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-shift.shift.shiftType">
                                                            <s:property value="shift.shiftType" /></span>
                                                            </div>
                                                        </div>	
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">From Date  :</div>
                                                            <div class="col-md-6 row-right">
                                                            <span id="display-shift.shift.startDate">
                                                            <s:property value="shift.startDate" /></span>
                                                            </div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">End Date:</div>
                                                            <div class="col-md-6 row-right">
                                                             <span id="display-shift.shift.endDate">
                                                            <s:property value="shift.endDate" /></span>
                                                            </div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Start Time  :</div>
                                                            <div class="col-md-6 row-right">
                                                             <span id="display-shift.shift.startTime">
                                                            <s:property value="shift.startTime" /></span>
                                                            </div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">End Time:</div>
                                                            <div class="col-md-6 row-right"> <span id="display-shift.shift.startTime">
                                                            <s:property value="shift.endTime" /></span>
                                                            </div>
                                                        </div>
												</div>
		<form id="shiftForm">										
<div class="showform8 allcp-form">
														<div class="section row mb5">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                       Shift Timing:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field select">
                                                                                    <select name="shift.shiftType" class="empty">
                                                                                        <option value="0">9AM To 6PM</option>
                                                                                        <option value="1">10AM TO 7PM</option>
                                                                                    </select>
                                                                                    <i class="arrow double"></i>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                   
														  </div>
														  <input type="hidden" name="shift.employee.employeeId" id="shift.employee.employeeId" value=18>
														  <div class="section row mb5">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                       From Date:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                   <label class="field prepend-icon" for="datepicker1">
                                                                                  <input type="text" placeholder="From Date" class="gui-input themeDatepicker" name="shift.startDate" id="shift.startDate">
                                                                                    <label class="field-icon">
                                                                                    <i class="fa fa-calendar"></i>
                                                                                       </label>
                                                                                   </label>
                                                                             </div>
                                                                        </div>
                                                                   
														  </div>
														  
														  	  <div class="section row mb5">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                       End Date:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                   <label class="field prepend-icon" for="datepicker1">
                                                                                  <input type="text" placeholder="End Date" class="gui-input themeDatepicker" name="shift.endDate"
																			id="shift.endDate">
                                                                                    <label class="field-icon">
                                                                                    <i class="fa fa-calendar"></i>
                                                                                       </label>
                                                                                   </label>
                                                                             </div>
                                                                        </div>
                                                                   
														  </div>
														  
														 <div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">Start Time:</label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field prepend-icon" for="timepicker1">
                                                                                                        <input type="text" placeholder="Start Time" class="gui-input themeTimepicker" name="shift.startTime"
																		id="shift.startTime">
                                                                                                        <label class="field-icon">
                                                                                                        <i class="fa fa-clock-o"></i>
                                                                                                        </label>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </div>
														  <div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">End Time :</label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field prepend-icon" for="timepicker1">
                                                                                                        <input type="text" placeholder="End Time" class="gui-input themeTimepicker" name="shift.endTime"
																		id="shift.endTime">
                                                                                                        <label class="field-icon">
                                                                                                        <i class="fa fa-clock-o"></i>
                                                                                                        </label>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </div>
														  

 
</div>												
													 
														
										  </div><!--/.panel-body -->
									   </div><!--/.panel-collapse -->
									</div><!-- /.panel -->
									
									   <!-- shift -->
									   
									   
									   
									
											
														</div>
                                                    <!--/.panel-body -->
                                                </div>
                                                <!--/.panel-collapse -->
                                            </div>
                                            <!-- /.panel -->
											
											
											 
								 
											
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                                        EEO
                                                        </a>
                                                    </h4>
                                                </div>
                                                <!--/.panel-heading -->
                                                <div id="collapseTwo" class="panel-collapse collapse">
                                                    <div class="panel-body">
													
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left"></div>
                                                            <div class="col-md-7 row-right Editicon  nopadding-right"> 
															
															<i class="fa fa-pencil-square-o fs26 text-primary pull-right  message" aria-hidden="true"  id="editbutton1" ></i>
															<i id="savebutton1" style="display:none;"  onclick="eeoSave()" class="fa fa-floppy-o fs26 text-danger pull-right" aria-hidden="true"></i>
															<form class="allcp-form theme-primary">
														 <i class="fa fa-envelope text-primary  fs26 pull-right mr10" aria-hidden="true"></i>
                                                        <label id="rliteaccess" class="block mt15 option option-primary ">
                                                        <input type="checkbox" checked="" value="checked" name="checked">
                                                        <span class="checkbox pull-right" id="message"></span>
														
														</label>
                                                     </form>
													</div>
                                               </div>
														
														
                                                        <div class="default1">
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Ethnic Race :</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-eeo.eeo.ethnicRaceIdObj.ethnicRaceDesc">
                                                            <s:property value="eeo.ethnicRaceIdObj.ethnicRaceDesc" /></span>
                                                            	</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Marital  Status:</div>
                                                                <div class="col-md-6 row-right">
                                                                 <span id="display-eeo.eeo.maritalStatus">
                                                            <s:property value="eeo.maritalStatus" /></span>
                                                                </div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Dependents :</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-eeo.eeo.dependents">
                                                            <s:property value="eeo.dependents" /></span>
                                                                </div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Dependent  Details:</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-eeo.eeo.dependDetail">
                                                            <s:property value="eeo.dependDetail" /></span>
                                                                </div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Veteran:</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-eeo.eeo.veteran">
                                                            <s:property value="eeo.veteran" /></span></div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Military  Status:</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-eeo.eeo.militaryStatus">
                                                            <s:property value="eeo.militaryStatus" /></span>
                                                            </div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Disability :</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-eeo.eeo.disability">
                                                            <s:property value="eeo.disability" /></span>
                                                                </div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Emergency  Contact Name :</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-eeo.eeo.emergenConName">
                                                            <s:property value="eeo.emergenConName" /></span>
                                                                </div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Emergency  Contact Number:</div>
                                                                <div class="col-md-6 row-right">
                                                                 <span id="display-eeo.eeo.emergenPhNo">
                                                            <s:property value="eeo.emergenPhNo" /></span>
                                                                </div>
                                                            </div>
                                                        </div>
													<form id="eeoForm">	
                                                        <div class="showform1">
                                                            <div class="allcp-form theme-primary">
                                                              
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Ethnic  Race:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field select">
                                                                                    <select class="empty" name="eeo.ethnicRaceIdObj.ethnicRaceId" id="eeo.ethnicRaceIdObj.ethnicRaceId">
                                                                                        <option value="0">Developement</option>
                                                                                        <option value="1">Design</option>
                                                                                    </select>
                                                                                    <i class="arrow double"></i>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                   
                                                                </div>
                                                                <!-- -------------- /section -------------- -->
                                                              
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Ethnic  Race Proof:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field prepend-icon append-button file  ">
                                                                                <span class="button btn-primary"  >Choose File</span>
                                                                               <!--  <input type="file" class="gui-file" name="file4" id="file4"
                                                                                    onChange="document.getElementById('uploader4').value = this.value;"
                                                                                    > -->
                                                                                                       <input type="file" class="gui-file" name="eeo.ethnicDocumentId" id="eeo.ethnicDocumentId"
                                                                                   onChange="document.getElementById('eeo-uploader4').value = this.value;"
                                                                                    >
                                                                                <input type="text" class="gui-input" id="uploader4"
                                                                                    placeholder="  File Select">
                                                                                <label class="field-icon">
                                                                                <i class="fa fa-cloud-upload"></i>
                                                                                </label>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                               
                                                                <!-- -------------- /section -------------- -->
                                                                
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10 ">
                                                                        Marital 	Status:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field select">
                                                                                    <select class="empty" name="eeo.maritalStatus" id="eeo.maritalStatus">
                                                                                        <option value="0">Single</option>
                                                                                        <option value="1">Married</option>
                                                                                    </select>
                                                                                    <i class="arrow double"></i>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                              
                                                                <!-- -------------- /section -------------- -->
                                                               
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Dependents:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field">
                                                                                <input type="text" placeholder="Dependents" class="gui-input" id="eeo.dependents" name="eeo.dependents">
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                               
                                                                <!-- -------------- /section -------------- -->
                                                               
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Dependent  Details:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field">
                                                                                <input type="text" placeholder="Dependents Details" class="gui-input" id="eeo.dependDetail" name="eeo.dependDetail">
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                             
                                                                <!-- -------------- /section -------------- -->
                                                             
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Veteran:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field select">
                                                                                    <select class="empty" name="eeo.veteran" id="eeo.veteran">
                                                                                        <option value="0">Developement</option>
                                                                                        <option value="1">Design</option>
                                                                                    </select>
                                                                                    <i class="arrow double"></i>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                              
                                                                <!-- -------------- /section -------------- -->
                                                               
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Veteran Proof:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field prepend-icon append-button file  ">
                                                                                <span class="button btn-primary"  >Choose File</span>
                                                                               <!--  <input type="file" class="gui-file" name="file5" id="file5"
                                                                                    onChange="document.getElementById('uploader5').value = this.value;"> -->
                                                                                     <input type="file" class="gui-file" name="eeo.veteranDocumentId" id="eeo.veteranDocumentId"
                                                                                 onChange="document.getElementById('eeo-uploader5').value = this.value;" 
                                                                                   >
                                                                                <input type="text" class="gui-input" id="uploader5"
                                                                                    placeholder="  File Select">
                                                                                <label class="field-icon">
                                                                                <i class="fa fa-cloud-upload"></i>
                                                                                </label>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                               
                                                                <!-- -------------- /section -------------- -->
                                                                <!-- -------------- /section -------------- -->
                                                                 
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Military Status :</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field">
                                                                                <input type="text" name="eeo.militaryStatus" id="eeo.militaryStatus" class="gui-input" placeholder="Dependents">
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                              
                                                                <!-- -------------- /section -------------- -->
                                                              
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Military  Proof:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field prepend-icon append-button file  ">
                                                                                <span class="button btn-primary"  >Choose File</span>
                                                                               <!--  <input type="file" class="gui-file" name="file6" id="file6"
                                                                                    onChange="document.getElementById('uploader6').value = this.value;"
                                                                                    > -->
                                                                                     <input type="file" class="gui-file" name="eeo.militaryDocumentId" id="eeo.militaryDocumentId"
                                                                                   onChange="document.getElementById('eeo-uploader6').value = this.value;"
                                                                                    >
                                                                                <input type="text" class="gui-input" id="uploader6"
                                                                                    placeholder="  File Select">
                                                                                <label class="field-icon">
                                                                                <i class="fa fa-cloud-upload"></i>
                                                                                </label>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                
                                                                <!-- -------------- /section -------------- -->
                                                                <!-- -------------- /section -------------- -->
                                                                
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Disability  :</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field">
                                                                                <input type="text" name="eeo.disability" id="eeo.disability" class="gui-input" placeholder="Dependents">
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                 
                                                                <!-- -------------- /section -------------- -->
                                                               
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Disability   Proof:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field prepend-icon append-button file  ">
                                                                                <span class="button btn-primary"  >Choose File</span>
                                                                               <!--  <input type="file" class="gui-file" name="file7" id="file7"
                                                                                    onChange="document.getElementById('uploader7').value = this.value;"
                                                                                    > -->
                                                                                    
                                                                                    <input type="file" class="gui-file" name="eeo.disabilityDocumentId" id="eeo.disabilityDocumentId"
                                                                                    onChange="document.getElementById('eeo-uploader7').value = this.value;"
                                                                                    > 
                                                                                <input type="text" class="gui-input" id="uploader7"
                                                                                    placeholder="  File Select">
                                                                                <label class="field-icon">
                                                                                <i class="fa fa-cloud-upload"></i>
                                                                                </label>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                
                                                                <!-- -------------- /section -------------- -->
                                                              
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Emergency contact name  :</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field">
                                                                                <input type="text" name="eeo.emergenConName" id="eeo.emergenConName" class="gui-input" placeholder="Dependents">
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                
                                                                <!-- -------------- /section -------------- -->
                                                                 
                                                                    <div class="section row mb10">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                        Emergency Contact no :</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                <label class="field">
                                                                                <input type="text" name="eeo.emergenPhNo" id="eeo.emergenPhNo" class="gui-input" placeholder="Dependents">
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                 
                                                                <!-- -------------- /section -------------- -->
                                                                <input type="hidden" id="eeo.employee.employeeId" name="eeo.employee.employeeId" value="18" >
																
                                                            </div>
															
                                                        </div>
														
														
                                                    </div>
                                                    <!--/.panel-body -->
                                                </div>
                                                <!--/.panel-collapse -->
                                            </div>
                                            <!-- /.panel -->
											
											
											
											
											
											
											
											
											
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                                        Education
                                                        </a>
                                                    </h4>
                                                </div>
                                                <!--/.panel-heading -->
                                                <div id="collapseThree" class="panel-collapse collapse">
                                                    <div class="panel-body">
													<div class="first-row">
													   <div class="col-md-5 row-left"></div>
                                                            <div class="col-md-7 row-right Editicon  nopadding-right">
													<form class="allcp-form theme-primary">
														 <i class="fa fa-envelope text-primary  fs26 pull-right mr10" aria-hidden="true"></i>
                                                        <label id="rliteaccess" class="block mt15 option option-primary ">
                                                        <input type="checkbox" checked="" value="checked" name="checked">
                                                        <span class="checkbox pull-right" id="message"></span>
														
														</label>
                                                     </form>
													 </div>
													 </div>
                                                       
                                                        <div class="table-responsive">
                                                            <table class="table allcp-form theme-warning tc-checkbox-1 fs13">
                                                                <thead>
                                                                    <tr class="bg-dark">
                                                                        <th class="">Degree</th>
                                                                        <th class="">Major</th>
                                                                        <th class="">Year</th>
                                                                        <th class="">Grade/CGPA</th>
                                                                        <th class="">From Date</th>
                                                                        <th class="">To Date</th>
                                                                        <th class=" ">Graduate</th>
																		<th class="text-center">Edit</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td class="w100">
                                                                            B.E
                                                                        </td>
                                                                        <td class="">CSE</td>
                                                                        <td class="">2011</td>
                                                                        <td class="">A</td>
                                                                        <td class="">03/06/2011</td>
                                                                        <td class="">03/06/2014</td>
                                                                        <td class=" ">Yes                    
                                                                        </td>
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal14"><i class="fa fa-pencil fs18" aria-hidden="true"></i></td>
																		
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="w100">
                                                                            B.E
                                                                        </td>
                                                                        <td class="">CSE</td>
                                                                        <td class="">2011</td>
                                                                        <td class="">A</td>
                                                                        <td class="">03/06/2011</td>
                                                                        <td class="">03/06/2014</td>
                                                                        <td class=" "> No                                             
                                                                        </td>
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal14"><i class="fa fa-pencil fs18" aria-hidden="true"></i></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="w100">
                                                                            B.E
                                                                        </td>
                                                                        <td class="">CSE</td>
                                                                        <td class="">2011</td>
                                                                        <td class="">A</td>
                                                                        <td class="">03/06/2011</td>
                                                                        <td class="">03/06/2014</td>
                                                                        <td class=" ">Yes                                          
                                                                        </td>
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal14"><i class="fa fa-pencil fs18" aria-hidden="true"></i></td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
													 
													 
													 
														<!-- Button trigger modal -->
<button class = "btn btn-primary btn-sm mt20" data-toggle = "modal" data-target = "#myModal8">
 <i class="fa fa-plus-circle"></i> Add  Another
</button>

<!-- Modal -->
<div class = "modal fade" id = "myModal8" tabindex = "-1" role = "dialog" 
   aria-labelledby = "myModalLabel" aria-hidden = "true">
   
   <div class = "modal-dialog">
      <div class = "modal-content">
         
         <div class = "modal-header">
            <button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">
                  &times;
            </button>
            
            <h4 class = "modal-title" id = "myModalLabel">
              <div class="topbar-left top">
                         <ul>
						 <li><i class="fa fa-plus" aria-hidden="true"></i></li>
						 <li>Education</li>
                    </ul></div>
            </h4>
         </div>
         
         <div class = "modal-body">
				<form class="allcp-form theme-primary side-popup" id="eduForm">
                                                             
                                                        <div class="section row mb10">
																<div class="col-md-6">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Degree:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="edu.eduDegree">
                                                                                    <option value="1">BE</option>
                                                                                    <option value="2">ME</option>
																					<option value="3">Msc</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																<div class="col-md-6">
																
																	  <label class="field-label col-sm-4 ph10">
                                                                    Major:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="edu.eduMajor">
                                                                                    <option value="1">CSE</option>
                                                                                    <option value="2">IT</option>
																					  <option value="3">Electronics</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
																
																</div>
																
																
                                                         </div>
                                                         
                                                             
                                                            
															     <!-- -------------- /section -------------- -->	
																       
                                                           <div class="section row mb10">
														   <div class="col-md-6">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Year:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="edu.eduYear">
                                                                                    <option value="1">2010</option>
                                                                                    <option value="2">2011</option>
																					  <option value="3">2012</option>
                                                                                    <option value="4">2013</option>
																					
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																 <div class="col-md-6">
																		 <label class="field-label col-sm-4 ph10">
                                                                    Grade:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="edu.eduGrade">
                                                                                    <option value="1">A</option>
                                                                                    <option value="2">B</option>
																					<option value="3">C</option>
                                                                                    <option value="4">D</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
																 
																 
																 </div>
										  </div>
																  <!-- -------------- /section -------------- -->	
														 
																
																
																		  <!-- -------------- /section -------------- -->	
																
																<div class="section row mb10">
																<div class="col-md-6">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left from-date">
                                                                                                    From Date :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="edu.eduStartDate" name="edu.eduStartDate" class="gui-input themeDatepicker" placeholder="Datepicker Popup">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																									</div>
																									
																									<div class="col-md-6">
																									<label class="field-label col-sm-4 ph10 text-left">
                                                                                                    End Date :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="edu.eduEndDate"
																					name="edu.eduEndDate" class="gui-input themeDatepicker" placeholder="Datepicker Popup">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																									
																									</div>
																									
																									
																		</div>
																		
																				  <!-- -------------- /section -------------- -->	
																
																
																
																 
																		<input type="hidden" id="edu.empIdObj.employeeId"
																name="edu.empIdObj.employeeId" value="18">
																
																					       
                                                           <div class="section row mb10">
														   <div class="col-md-6">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Graduate:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
																		  <label class="switch block mt15">
                                                <input type="checkbox" name="edu.gradStatus"
																				id="edu.gradStatus" value=1 checked>
                                                <label for="t4" data-on="YES" data-off="NO"></label>
                                                <span> </span>
                                            </label>
																				
																				</label>
																					<!--  <label class="option block">
																				<input type="checkbox" name="checked" value="checked" checked>
																				<span class="checkbox"></span>Check</label> -->
																				</label>
																				
                                                                        </div>
                                                                    </div>
																	
																	
																	
																	
                                                                </div>
																
																
																
																
																
																</div>
																
																
																
																
																
																
																
																
				</form>
				
		 
		 
		 
            
         </div>
         
         <div class = "modal-footer">
             
            
            <button type = "button" class = "btn btn-primary" onclick="eduSave()">
               Save
            </button>
         </div>
         
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
  
</div><!-- /.modal -->



															
										 		
															
                                                    </div>
                                                    <!--/.panel-body -->
                                                </div>
                                                <!--/.panel-collapse -->
                                            </div>
                                            <!-- /.panel -->
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
                                                        Work
                                                        </a>
                                                    </h4>
                                                </div>
                                                <!--/.panel-heading -->
                                                <div id="collapseFour" class="panel-collapse collapse">
                                                    <div class="panel-body">
													<div class="first-row">
													   <div class="col-md-5 row-left"></div>
                                                            <div class="col-md-7 row-right Editicon  nopadding-right">
													<form class="allcp-form theme-primary">
														 <i class="fa fa-envelope text-primary  fs26 pull-right mr10" aria-hidden="true"></i>
                                                        <label id="rliteaccess" class="block mt15 option option-primary ">
                                                        <input type="checkbox" checked="" value="checked" name="checked">
                                                        <span class="checkbox pull-right" id="message"></span>
														
														</label>
                                                     </form>
													 </div>
													 </div>
															
													<div class="table-responsive">
                                                            <table class="table allcp-form theme-warning tc-checkbox-1 fs13">
                                                                <thead>
                                                                    <tr class="bg-dark">
                                                                        <th class="">Emp Name</th>
                                                                        <th class="">Job Title</th>
                                                                        <th class="">Start Date</th>
                                                                        <th class="">End Date</th>
                                                                        <th class="">Current Description</th>
																		 <th class="">Edit</th>
																		
                                                                        </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td class="w100">
                                                                         Roshan
                                                                        </td>
																		<td class="">Developer</td>
																		<td class="">03/06/2011</td>
                                                                        <td class="">03/06/2014</td>
                                                                       	<td class=" "> videntur parum clari, fiant sollemnes in futurum. </td>
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal20"><i class="fa fa-pencil fs18" aria-hidden="true"></i></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="w100">
                                                                         Kannankar
                                                                        </td>
																		<td class="">Designer</td>
																		<td class="">03/06/2011</td>
                                                                        <td class="">03/06/2014</td>
                                                                        
																		<td class=" "> videntur parum clari, fiant sollemnes in futurum. </td>
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal20"><i class="fa fa-pencil fs18" aria-hidden="true"></i></td>
                                                                    </tr>
                                                                   <tr>
                                                                        <td class="w100">
                                                                       Karishma
                                                                        </td>
																		<td class="">Designer</td>
																		<td class="">03/06/2011</td>
                                                                        <td class="">03/06/2014</td>
     																	<td class=" "> videntur parum clari, fiant sollemnes in futurum. </td>
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal20"><i class="fa fa-pencil fs18" aria-hidden="true"></i></td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
															
													 
															
                                                        </div>
														
														
														
														
														<!-- Button trigger modal -->
<button class = "btn btn-primary btn-sm mt20" data-toggle = "modal" data-target = "#myModal9">
 <i class="fa fa-plus-circle"></i> Add  Another
</button>

<!-- Modal -->
<div class = "modal fade" id = "myModal9" tabindex = "-1" role = "dialog" 
   aria-labelledby = "myModalLabel" aria-hidden = "true">
   
   <div class = "modal-dialog">
      <div class = "modal-content">
         
         <div class = "modal-header">
            <button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">
                  &times;
            </button>
            
            <h4 class = "modal-title" id = "myModalLabel">
              <div class="topbar-left top">
                         <ul>
						 <li><i class="fa fa-plus" aria-hidden="true"></i></li>
						 <li>Work</li>
                    </ul></div>
            </h4>
         </div>
         
         <div class = "modal-body">
				<form class="allcp-form theme-primary side-popup" id="workexpform">
                                                             
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Employer Name:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" id="workexp.employeerName"
																			name="workexp.employeerName">
                                                                                    <option value="0">BE</option>
                                                                                    <option value="1">ME</option>
																					<option value="1">Msc</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                           <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Job Title:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" id="workexp.empJobTitle" name="workexp.empJobTitle">
                                                                                    <option value="0">CSE</option>
                                                                                    <option value="1">IT</option>
																					  <option value="1">Electronics</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->	
													 
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    From Date :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="datepicker1"
																				name="workexp.fromDate" class="gui-input themeDatepicker" placeholder="Datepicker Popup">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
																				  <!-- -------------- /section -------------- -->	
																
																
																
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    End Date :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="workexp.toDate"
																				name="workexp.toDate" class="gui-input themeDatepicker" placeholder="Datepicker Popup">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
							<div class="section row mb20">
                                    <label for="store-tos" class="field-label col-sm-4 ph10 ">Description :</label>
                                    <div class="col-sm-8 ph10">
                                        <label class="field prepend-icon">
											<textarea class="gui-textarea" id="workexp.comments"
																			name="workexp.comments"placeholder="Terms of service conditions..."></textarea>
                                            <label for="store-tos" class="field-icon">
                                                <i class="fa fa-align-justify"></i>
                                            </label>
                                        </label>
                                    </div>
                                </div>
																
																
																
																
						<input type="hidden" id="workexp.empIdObj.employeeId"
																name="workexp.empIdObj.employeeId" value="18">										
																
																
																
																
				</form>
				
		 
		 
		 
            
         </div>
         
         <div class = "modal-footer">
            
            
            <button type = "button" class = "btn btn-primary" onclick="saveWorkExp()">
               Save
            </button>
         </div>
         
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
  
</div><!-- /.modal -->
														
														
														
	<!-- Modal -->
<div class = "modal fade" id = "myModal20" tabindex = "-1" role = "dialog" 
   aria-labelledby = "myModalLabel" aria-hidden = "true">
   
   <div class = "modal-dialog">
      <div class = "modal-content">
         
         <div class = "modal-header">
            <button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">
                  &times;
            </button>
            
            <h4 class = "modal-title" id = "myModalLabel">
              <div class="topbar-left top">
                         <ul>
						 <li><i class="fa fa-pencil" aria-hidden="true"></i></li>
						 <li>Work</li>
                    </ul></div>
            </h4>
         </div>
         
         <div class = "modal-body">
				<form class="allcp-form theme-primary side-popup">
                                                             
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Employer Name:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="Select">
                                                                                    <option value="0">BE</option>
                                                                                    <option value="1">ME</option>
																					<option value="1">Msc</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                           <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    JOb Title:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="Select">
                                                                                    <option value="0">CSE</option>
                                                                                    <option value="1">IT</option>
																					  <option value="1">Electronics</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->	
													 
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    From Date :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="datepicker1" name="datepicker1" class="gui-input themeDatepicker" placeholder="Datepicker Popup">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
																				  <!-- -------------- /section -------------- -->	
																
																
																
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    End Date :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="datepicker1" name="datepicker1" class="gui-input themeDatepicker" placeholder="Datepicker Popup">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
							<div class="section row mb20">
                                    <label for="store-tos" class="field-label col-sm-4 ph10 ">Description :</label>
                                    <div class="col-sm-8 ph10">
                                        <label class="field prepend-icon">
											<textarea class="gui-textarea" id="store-tos" name="store-tos" placeholder="Terms of service conditions..."></textarea>
                                            <label for="store-tos" class="field-icon">
                                                <i class="fa fa-align-justify"></i>
                                            </label>
                                        </label>
                                    </div>
                                </div>
																
																
																
																
																
																
																
																
																
				</form>
				
		 
		 
		 
            
         </div>
         
         <div class = "modal-footer">
          
            <button type = "button" class = "btn btn-primary">
               Save
            </button>
         </div>
         
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
  
</div><!-- /.modal -->													
 
    </div>
 </div>
    <!--/.panel-collapse -->
 </div>
                                            <!-- /.panel -->
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
                                                        Pay Tab
                                                        </a>
                                                    </h4>
                                                </div>
                                                <!--/.panel-heading -->
                                                <div id="collapseFive" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <div class="first-row Editicon">
                                                            <div class="col-md-5 row-left"></div>
                                                            <div id="edit" class="col-md-7 row-right  nopadding-right"> <i id="editbutton5"  class="fa fa-pencil-square-o fs26 text-primary"></i>  <i  class="fa fa-floppy-o fs26 text-danger" onclick="paytabsave()" style="display:none;" id="savebutton5"></i></div>
                                                        </div>
                                                        <div class="default5">
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Role :</div>
                                                                <div class="col-md-6 row-right">UI Developer	</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Employment Type:</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-paytab.payStub.empType">
                                                            <s:property value="payStub.empType" /></span>
                                                                </div>
                                                            </div>
															<div class="first-row">
                                                                <div class="col-md-5 row-left">Benefits:</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-paytab.payStub.isBenefit">
                                                            <s:property value="payStub.isBenefit" /></span>
                                                            </div>
                                                            </div>
															
															
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Payment Type :</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-paytab.payStub.paymentType">
                                                            <s:property value="payStub.paymentType" /></span>
                                                            </div>
                                                            </div>
															
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Annual Gross Salary  :</div>
                                                                <div class="col-md-6 row-right">
                                                                <span id="display-paytab.payStub.grossSalary">
                                                            <s:property value="payStub.grossSalary" /></span>
                                                            </div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Monthly net Salary:</div>
                                                                <div class="col-md-6 row-right"> 
                                                                <span id="display-paytab.payStub.netSalary">
                                                            <s:property value="payStub.netSalary" />
                                                            </span>
                                                            </div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Frequency of the pay:</div>
                                                                <div class="col-md-6 row-right"> 
                                                                <span id="display-paytab.payStub.payFreq">
                                                            <s:property value="payStub.payFreq" />
                                                            </span></div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Pay Day of the month :</div>
                                                                <div class="col-md-6 row-right">1000</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">First Day of Payroll:</div>
                                                                <div class="col-md-6 row-right">  
                                                                <span id="display-paytab.payStub.firstDay">
                                                            <s:property value="payStub.firstDay" />
                                                            </span>
                                                            </div>
                                                            </div>
															
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Regular Rate :</div>
                                                                <div class="col-md-6 row-right">
                                                                 <span id="display-paytab.payStub.regularRate">
                                                            <s:property value="payStub.regularRate" />
                                                            </span></div>
                                                            </div>
															
															  <div class="first-row">
                                                                <div class="col-md-5 row-left">Overtime Rate :</div>
                                                                <div class="col-md-6 row-right"> <span id="display-paytab.payStub.overtimeRate">
                                                            <s:property value="payStub.overtimeRate" />
                                                            </span>
                                                            </div>
                                                            </div>
															
															
															  <div class="first-row">
                                                                <div class="col-md-5 row-left">Commission %:</div>
                                                                <div class="col-md-6 row-right"> <span id="display-paytab.payStub.commission">
                                                            <s:property value="payStub.commission" />
                                                            </span></div>
                                                            </div>
															
															<h6 class="mt40 mb20" id="widget2">Benefits Medical Insurance :</h6>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">Regular :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">Dental:</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">vision :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">Insurance :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">401K :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">Others :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">FSA :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															
                                                        </div>
														 
														
														<form id="paytabform">
                                                        <div class="showform5">
                                                            <div class="allcp-form theme-primary side-popup">
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Role:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                         <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Your Role" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="section">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Employment Type:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                       <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="payStub.empType"
																	id="payStub.empType">
                                                                                    <option value="0">Option 1</option>
                                                                                    <option value="1">Option 2</option>
																					<option value="1">Option 3</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>

																 <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Benefits:</label>
                                                                    <div class="col-sm-3 ph7">
                                                                       <div class="section">
																	   
																		  <label class="option block switch">
																				<input type="checkbox" name="payStub.isBenefit"
																	id="payStub.isBenefit" value=1 checked>
																				<span class="checkbox"></span>Yes</label> 
																		</div>	
																	</div>	
																		
													<%-- 				<div class="col-sm-4 ph7">
                                                                       <div class="section">			
																			<label class="option block switch">
                                                        <input type="checkbox" name="inputname" value="CH">
                                                        <span class="checkbox"></span>No </label>	
																				
                                                                        </div>
																		
                                                                    </div> --%>
                                                                </div>
																
																
																
																
																
																
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                  Payment Type:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="payStub.paymentType"
																	id="payStub.paymentType">
                                                                                    <option value="0">Option 1</option>
                                                                                    <option value="1">Option 2</option>
																					<option value="1">Option 3</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Annual Gross Salary:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Annual Gross Salary" class="gui-input" id="payStub.grossSalary" name="payStub.grossSalary">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Monthly net Salary:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder=" Monthly net Salary" class="gui-input" id="payStub.netSalary" name="payStub.netSalary">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Frequency of the pay:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                         <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="payStub.payFreq"
																	id="payStub.payFreq">
                                                                                    <option value="0">Option 1</option>
                                                                                    <option value="1">Option 2</option>
																					<option value="1">Option 3</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Pay Day of the month :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                       
																		<div class="section">
																			<label for="datepicker1" class="field prepend-icon">
																				<input type="text" id="datepicker1" name="datepicker1"
																					   class="gui-input"
																					   placeholder=" Pay Day of the month">
																				<label class="field-icon">
																					<i class="fa fa-calendar"></i>
																				</label>
																			</label>
																		</div>
																		
                                                                    </div>
                                                                </div>
																
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    First Day of Payroll:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder=" First Day of Payroll" class="gui-input" id="payStub.firstDay"
																	name="payStub.firstDay">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Regular Rate :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="  Regular Rate" class="gui-input" id="payStub.regularRate" name="payStub.regularRate">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																   <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Overtime Rate :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Overtime Rate" class="gui-input" id="payStub.overtimeRate" name="payStub.overtimeRate">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																
																   <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Commission %:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Commission %" class="gui-input" id="payStub.commission" name="payStub.commission">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																
											 <h6 class="benefits mb20 " id="widget2">Benefits Medical Insurance :</h6>	

																<div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                  Regular :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Regular	" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																<div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                  Dental :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Dental" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																<div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Vision :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Vision" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																<div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Insurance :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Insurance" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																<div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   401K :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="401K" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																<div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Others :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Others" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																<div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                 FSA :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="flexible spending amount" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																
																
																
																
																
																
																
																
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!--/.panel-body -->
                                                </div>
                                                <!--/.panel-collapse -->
                                            </div>
                                            <!-- /.panel -->
                                          
                                        </div>
                                        <!-- /.panel -->
										
										
					



  <!-- /.panel -->
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#worklocation">
                                                       Work Location
                                                        </a>
                                                    </h4>
                                                </div>
                                                <!--/.panel-heading -->
                                                <div id="worklocation" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <div class="first-row Editicon">
                                                            <div class="col-md-5 row-left"></div>
                                                            <div id="edit" class="col-md-7 row-right  nopadding-right"> <i id="editbutton9"  class="fa fa-pencil-square-o fs26 text-primary"></i>  <i  class="fa fa-floppy-o fs26 text-danger" style="display:none;" id="savebutton9"></i></div>
                                                        </div>
                                                        <div class="default9">
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Location Name :</div>
                                                                <div class="col-md-6 row-right">Office	</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Country:</div>
                                                                <div class="col-md-6 row-right">India</div>
                                                            </div>
															<div class="first-row">
                                                                <div class="col-md-5 row-left">State:</div>
                                                                <div class="col-md-6 row-right">Tamil Nadu</div>
                                                            </div>
															
															
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">City :</div>
                                                                <div class="col-md-6 row-right">Chennai</div>
                                                            </div>
															
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Zip :</div>
                                                                <div class="col-md-6 row-right">600 033</div>
                                                            </div>
															
															
												<div class="table-responsive">
                                                            <table class="table allcp-form theme-warning tc-checkbox-1 fs13">
                                                                <thead>
                                                                    <tr class="bg-dark">
                                                                        <th class="">Location</th>
                                                                        <th class="">Address</th>
                                                                        <th class="">Status</th>
                                                                        <th class=" text-center">Edit</th>
                                                                        <th class="text-center">Delete</th>
																	 					
                                                                        </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td class="w100">
                                                                         Office
                                                                        </td>
																		<td class="">122th Main Road</td>
																		<td class="">Approved</td>                                                                    
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal20">
																		<i class="fa fa-pencil fs18" aria-hidden="true"></i></td>
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal20">
																		<i class="fa fa-trash fs18" aria-hidden="true"></i></td>
																		
                                                                    </tr>
                                                                   <tr>
                                                                        <td class="w100">
                                                                         Office
                                                                        </td>
																		<td class="">122th Main Road</td>
																		<td class="">Approved</td>                                                                    
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal20">
																		<i class="fa fa-pencil fs18" aria-hidden="true"></i></td>
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal20">
																		<i class="fa fa-trash fs18" aria-hidden="true"></i></td>
																		
                                                                    </tr>
                                                                   <tr>
                                                                        <td class="w100">
                                                                         Office
                                                                        </td>
																		<td class="">122th Main Road</td>
																		<td class="">Approved</td>                                                                    
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal20">
																		<i class="fa fa-pencil fs18" aria-hidden="true"></i></td>
																		<td class="text-center" data-toggle = "modal" data-target = "#myModal20">
																		<i class="fa fa-trash fs18" aria-hidden="true"></i></td>
																		
                                                                    </tr>
                                                                </tbody>
                                                            </table>
															
													 
															
                                                        </div>
														
														
														
														
														<!-- Button trigger modal -->





			
                                                            
                                                            
															
                                                        </div>
														 
														
														
                                                        <div class="showform9">

                                                            <div class="allcp-form theme-primary side-popup">
															
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Location Name:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                         <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Location Name" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="section">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Country:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                       <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Country" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
 
																
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                 State:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="State" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   City:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="City" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                  Zip:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder=" Zip" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																<div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   </label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                          <button class = "btn btn-primary btn-sm mt20" data-toggle = "modal" data-target = "#myModal9">
															<i class="fa fa-check"></i> Send for approval</button>
															  <button class = "btn btn-primary btn-sm mt20" data-toggle = "modal" data-target = "#workaddmore">
															<i class="fa fa-plus-circle"></i>Add more</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
															
																
                                                         
																
                                                            </div>
                                                        </div>
														
														
                                                    </div>
                                                    <!--/.panel-body -->
                                                </div>
                                                <!--/.panel-collapse -->
                                            </div>
                                            <!-- /.panel -->
                            
										
										
										
		<!-- Modal -->
<div class = "modal fade" id ="workaddmore" tabindex = "-1" role = "dialog" 
   aria-labelledby = "myModalLabel" aria-hidden = "true">
   
   <div class = "modal-dialog">
      <div class = "modal-content">
         
         <div class = "modal-header">
            <button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">
                  &times;
            </button>
            
            <h4 class = "modal-title" id = "myModalLabel">
              <div class="topbar-left top">
                         <ul>
						 <li><i class="fa fa-plus" aria-hidden="true"></i></li>
						 <li>Work Location</li>
                    </ul></div>
            </h4>
         </div>
         
         <div class = "modal-body">
				<form class="allcp-form theme-primary side-popup">
                                                             
                                                                <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Location Name:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Location Name" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                           <div class="section row mb5">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Country:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                           <label class="field">
                                                                            <input type="text" placeholder="Country" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->	
													 
																<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    State :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label class="field">
                                                                            <input type="text" placeholder="State" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
																				  <!-- -------------- /section -------------- -->	
																
																
																
																<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    City :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                 <label class="field">
                                                                            <input type="text" placeholder="City" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
							 
																
																<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    Zip :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                 <label class="field">
                                                                            <input type="text" placeholder="Zip" class="gui-input" id="from" name="from">
                                                                            </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>	
																
																
																
																
																
																
																
				</form>
				
		 
		 
		 
            
         </div>
         
         <div class = "modal-footer">
            
            
            <button type = "button" class = "btn btn-primary">
               Save
            </button>
         </div>
         
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
  
</div><!-- /.modal -->								
										









					
										
										   <!-- /.panel -->
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
                                                       Leave
                                                        </a>
                                                    </h4>
                                                </div>
                                                <!--/.panel-heading -->
                                                <div id="collapseSix" class="panel-collapse collapse">
                                                    <div class="panel-body">
													
														<div class="first-row">
                                                            <div class="col-md-5 row-left"></div>
                                                            <div class="col-md-7 row-right Editicon  nopadding-right "> 
															
															<i class="fa fa-pencil-square-o fs26 text-primary pull-right  message" aria-hidden="true"  id="editbutton6" ></i>
															<i id="savebutton6" style="display:none;" class="fa fa-floppy-o fs26 text-danger pull-right" aria-hidden="true"></i>

													 
													 
													</div>
                                               </div>
														
														
                                                        <div class="default6">
	
															<div class="first-row">
                                                                <div class="col-md-5 row-left">National holidays :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">Accrued PTO :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">Sick leaves  :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">vacation leaves :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">Miscellaneous :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
															<div class="first-row">
                                                                <div class="col-md-5 row-left">Overtime hours max :</div>
                                                                <div class="col-md-6 row-right">Value</div>
                                                            </div>
															
												 	</div>
															
															
													<div class="showform6">	
													
													
														<form class="allcp-form theme-primary side-popup">
                                                             
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   National holidays :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                         <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="from"   class="gui-input" placeholder=" National holidays">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                           <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Accrued PTO :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="from"   class="gui-input" placeholder="Accrued PTO">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->	
																 
																 
																  <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Sick leaves :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                       <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="from"   class="gui-input" placeholder="Sick leaves">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->
																 
																  
																   <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Vacation leaves : </label>
                                                                    <div class="col-sm-7 ph7">
                                                                       <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="from"   class="gui-input" placeholder=" Vacation leaves ">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->
															 
																  <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                   Miscellaneous :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                       <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="from"   class="gui-input" placeholder="Miscellaneous">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->
																 
																  <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Overtime hours max :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                       <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="from"   class="gui-input" placeholder="Overtime hours max">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->
												 
																 
																 
																 
																 
						 
 													
				</form>
				
				
															
														
														
														
													</div>
															
															
															
															
															
															
										</div>
										</div>
										 </div>
										
										
										   <!-- /.panel -->
										   
										      <!-- /.panel -->
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven">
                                                       Assets
                                                        </a>
                                                    </h4>
                                                </div>
                                                <!--/.panel-heading -->
                                                <div id="collapseSeven" class="panel-collapse collapse">
                                                    <div class="panel-body">
													
												  <!-- <div class="first-row">
													   <div class="col-md-5 row-left nopadding-left"> <h6>Hardware</h6></div>
                                                            <div class="col-md-7 row-right Editicon  nopadding-right">
													<form class="allcp-form theme-primary">
														 <i aria-hidden="true" class="fa fa-envelope text-primary  fs26 pull-right mr10"></i>
                                                        <label class="block mt15 option option-primary" id="rliteaccess">
                                                        <input type="checkbox" name="checked" value="checked" checked="">
                                                        <span id="message" class="checkbox pull-right"></span>
														
														</label>
                                                     </form>
													 </div>
													 </div>   -->
															 <h6 class="fs18">Hardware</h6>	
																<div class="table-responsive">
                                                            <table id="assetHardware" class="table allcp-form theme-warning tc-checkbox-1 fs13">
                                                                
																		
																
																
                                                            </table>
															
																		<button data-target="#myModal31" data-toggle="modal" class="btn btn-primary btn-sm mt20 mb20">
																		<i class="fa fa-plus-circle"></i> Add  Another
																		</button>
																		
																		
																		
	<!-- Modal -->
<div class = "modal fade" id ="myModal31" tabindex = "-1" role = "dialog" 
   aria-labelledby = "myModalLabel" aria-hidden = "true">
   
   <div class = "modal-dialog">
      <div class = "modal-content">
          <div class = "modal-header">
            <button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">
                  &times;
            </button>
            
            <h4 class = "modal-title" id = "myModalLabel">
              <div class="topbar-left top">
                         <ul>
						 <li><i class="fa fa-plus" aria-hidden="true"></i></li>
						 <li>Hardware</li>
                    </ul></div>
            </h4>
         </div>
         
         <div class = "modal-body">
				<form class="allcp-form theme-primary side-popup" id="assetForm">
                                                             
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Sr no:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                         <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="assets.srNo" id="assets.srNo"   class="gui-input" placeholder="Sr no">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                           <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Assets:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="assets.assetName" id="assets.assetName"   class="gui-input" placeholder="Asset">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->	
																 
																 
																  <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Id:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                       <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="assets.clientId" name="" id="assets.clientId"  class="gui-input" placeholder="Id">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->
																 
																  
																 
															 <input type="hidden" name="assets.assetIdObj.assetTypeId"
																id="assets.assetIdObj.assetTypeId" value=1
																class="gui-input" placeholder="Id">
																 
																 
													  <!-- -------------- /section -------------- -->
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                   Date Issued :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="datepicker1" name="assets.issuedDate" id="assets.issuedDate"
																												class="gui-input themeDatepicker" placeholder="Date issued">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
																				  <!-- -------------- /section -------------- -->	
																
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                  Date Released :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="datepicker1" name="datepicker1" 
																												class="gui-input themeDatepicker" placeholder="Date Released ">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
																		
																	 <input type="hidden" name="assets.empIdObj.employeeId"
																id="assets.empIdObj.employeeId" value=18
																class="gui-input" placeholder="Id">
															     <!-- -------------- /section -------------- -->	
																 
																 
																 
																 
						 
 													
				</form>
          </div>
          <div class = "modal-footer">
                     
            <button type = "button" class = "btn btn-primary" onclick="saveAsset()">
               Save
            </button>
         </div>
         
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
  
 </div>																	
																		
																		
																		
																		
																		
															
                                                        </div>
														
														
														
														
														
														<h6 class="fs18">Software</h6>
																<div class="table-responsive">
                                                            <table class="table allcp-form theme-warning tc-checkbox-1 fs13">
                                                                <thead>
                                                                    <tr class="bg-dark">
                                                                        <th class="">Sr No</th>
                                                                        <th class="">Assets</th>
                                                                        <th class="">Id</th>
                                                                        <th class="">Date issued</th>
                                                                        <th class="">Date released</th>
														 										
                                                                        </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td class="w100">
                                                                         12112
                                                                        </td>
																		<td class="">value</td>
																		<td class="">12321</td>
                                                                        <td class="">03/06/2014</td>
                                                                       	<td class=" ">12/08/2013 </td>		
																		</tr>
																		
                                                                         <tr>
                                                                        <td class="w100">
                                                                         12112
                                                                        </td>
																		<td class="">value</td>
																		<td class="">12321</td>
                                                                        <td class="">03/06/2014</td>
                                                                       	<td class=" ">12/08/2013 </td>		
																		</tr>
																	 
                                                                   <tr>
                                                                        <td class="w100">
                                                                         12112
                                                                        </td>
																		<td class="">value</td>
																		<td class="">12321</td>
                                                                        <td class="">03/06/2014</td>
                                                                       	<td class=" ">12/08/2013 </td>		
																		</tr>
																	 
                                                                </tbody>
																		
																
																
                                                            </table>
															
																		<button data-target="#myModal30" data-toggle="modal" class="btn btn-primary btn-sm mt20">
																		<i class="fa fa-plus-circle"></i> Add  Another
																		</button>
																		
																		  
<!-- Modal -->
<div class = "modal fade" id = "myModal30" tabindex = "-1" role = "dialog" 
   aria-labelledby = "myModalLabel" aria-hidden = "true">
   
   <div class = "modal-dialog">
      <div class = "modal-content">
          <div class = "modal-header">
            <button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">
                  &times;
            </button>
            
            <h4 class = "modal-title" id = "myModalLabel">
              <div class="topbar-left top">
                         <ul>
						 <li><i class="fa fa-plus" aria-hidden="true"></i></li>
						 <li>Software</li>
                    </ul></div>
            </h4>
         </div>
         
         <div class = "modal-body">
				<form class="allcp-form theme-primary side-popup" id="assetSoftwareForm">
                                                             
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Sr no:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                         <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="assets.srNo" id="assets.srNo"  class="gui-input" placeholder="Sr no">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                           <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Assets:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="assets.assetName" id="assets.assetName"  class="gui-input" placeholder="Asset">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->	
																 
																 
																  <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Id:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                       <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="assets.clientId" id="assets.clientId"  class="gui-input" placeholder="Id">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->
																 
																  
																 
															 <input type="hidden" name="assets.assetIdObj.assetTypeId"
																id="assets.assetIdObj.assetTypeId" value=2
																class="gui-input" placeholder="Id">
																 
																 
													  <!-- -------------- /section -------------- -->
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                   Date Issued :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="datepicker1" name="assets.issuedDate" id="assets.issuedDate"
																												class="gui-input themeDatepicker" placeholder="Date issued">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
																				  <!-- -------------- /section -------------- -->	
																
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                  Date Released :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" name="assets.releasedDate" id="assets.releasedDate" 
																												class="gui-input themeDatepicker" placeholder="Date Released ">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
																		<input type="hidden" id="assets.empIdObj.employeeId"
																name="assets.empIdObj.employeeId" value="18">
																	 
															     <!-- -------------- /section -------------- -->	
																 
																 
																 
																 
						 
 													
				</form>
          </div>
          <div class = "modal-footer">
                     
            <button type = "button" class = "btn btn-primary" onclick="softwareAssetSave()">
               Save
            </button>
         </div>
         
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
  
 </div>
 
 
																		
																		
																		
															
    </div>
 </div>
 </div>
  </div>
										
										
   <!-- /.panel -->
										   
										   
										
										
										
										
										
                                         
                                            <div id="allcp-panel-filter">
										
                                                <div class="col-md-2 nopadding-left mt20 profile-save"> <a class="btn btn-primary " data-filter=".panel-system"  href="HR-login-for-Onboarding.html">   Save</a>
												</div>
													<div class="col-md-2 nopadding-left mt20 text-right profile-save"> <a class="btn btn-primary " data-filter=".panel-system" href="Onboarding.html">  Onboard</a>
												</div>
                                      </div>
                                         
                                    </div>
                                    <!-- /.panel-group -->
                                </div>
                            </div>
                        </div>
                    </div>
        </div>
       
        </div>
        </section>
		
		
		
		
  
  
  
<!-- Modal -->
<div class = "modal fade" id = "myModal14" tabindex = "-1" role = "dialog" 
   aria-labelledby = "myModalLabel" aria-hidden = "true">
   
   <div class = "modal-dialog">
      <div class = "modal-content">
          <div class = "modal-header">
            <button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">
                  &times;
            </button>
            
            <h4 class = "modal-title" id = "myModalLabel">
              <div class="topbar-left top">
                         <ul>
						 <li><i class="fa fa-pencil" aria-hidden="true"></i></li>
						 <li>Education</li>
                    </ul></div>
            </h4>
         </div>
         
         <div class = "modal-body">
				<form class="allcp-form theme-primary side-popup">
                                                             
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Degree:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="Select">
                                                                                    <option value="1">BE</option>
                                                                                    <option value="2">ME</option>
																					<option value="3">Msc</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                           <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Major:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="Select">
                                                                                    <option value="1">CSE</option>
                                                                                    <option value="2">IT</option>
																					  <option value="3">Electronics</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->	
																 
																 
																  <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Year:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="Select">
                                                                                    <option value="1">2011</option>
                                                                                    <option value="2">2012</option>
																					  <option value="3">2013</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->
																 
																  <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Grade / CGPA:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="Select">
                                                                                    <option value="1">A</option>
                                                                                    <option value="2">B</option>
																					  <option value="3">C</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->	
																 
															 
																 
																 
													  <!-- -------------- /section -------------- -->
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    Start Date :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="datepicker1" name="datepicker1" class="gui-input themeDatepicker" placeholder="Datepicker Popup">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>F
																		</div>
																		
																				  <!-- -------------- /section -------------- -->	
																
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    End Date :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="datepicker1" name="datepicker1" class="gui-input themeDatepicker" placeholder="Datepicker Popup">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
																		
																		
																		  <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                     Graduate:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="Select">
                                                                                    <option value="0">YES</option>
                                                                                    <option value="1">NO</option>
																					 
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->	
																 
																 
																 
																 
						 
 													
				</form>
          </div>
          <div class = "modal-footer">
                     
            <button type = "button" class = "btn btn-primary">
               Save
            </button>
         </div>
         
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
  
 </div>
 
 
 
 
 
 
        <!-- -------------- /Content -------------- -->
        </section>
        <!-- -------------- Sidebar Right -------------- -->
        <aside id="sidebar_right" class="nano affix">
            <!-- -------------- Sidebar Right Content -------------- -->
            <div class="sidebar-right-wrapper nano-content">
                <div class="sidebar-block br-n p15">
                    <h6 class="title-divider text-muted mb20"> Visitors Stats
                        <span class="pull-right"> 2015
                        <i class="fa fa-caret-down ml5"></i>
                        </span>
                    </h6>
                    <div class="progress mh5">
                        <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="34"
                            aria-valuemin="0"
                            aria-valuemax="100" style="width: 34%">
                            <span class="fs11">New visitors</span>
                        </div>
                    </div>
                    <div class="progress mh5">
                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="66"
                            aria-valuemin="0"
                            aria-valuemax="100" style="width: 66%">
                            <span class="fs11 text-left">Returnig visitors</span>
                        </div>
                    </div>
                    <div class="progress mh5">
                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="45"
                            aria-valuemin="0"
                            aria-valuemax="100" style="width: 45%">
                            <span class="fs11 text-left">Orders</span>
                        </div>
                    </div>
                    <h6 class="title-divider text-muted mt30 mb10">New visitors</h6>
                    <div class="row">
                        <div class="col-xs-5">
                            <h3 class="text-primary mn pl5">350</h3>
                        </div>
                        <div class="col-xs-7 text-right">
                            <h3 class="text-warning mn">
                                <i class="fa fa-caret-down"></i> 15.7% 
                            </h3>
                        </div>
                    </div>
                    <h6 class="title-divider text-muted mt25 mb10">Returnig visitors</h6>
                    <div class="row">
                        <div class="col-xs-5">
                            <h3 class="text-primary mn pl5">660</h3>
                        </div>
                        <div class="col-xs-7 text-right">
                            <h3 class="text-success-dark mn">
                                <i class="fa fa-caret-up"></i> 20.2% 
                            </h3>
                        </div>
                    </div>
                    <h6 class="title-divider text-muted mt25 mb10">Orders</h6>
                    <div class="row">
                        <div class="col-xs-5">
                            <h3 class="text-primary mn pl5">153</h3>
                        </div>
                        <div class="col-xs-7 text-right">
                            <h3 class="text-success mn">
                                <i class="fa fa-caret-up"></i> 5.3% 
                            </h3>
                        </div>
                    </div>
                    <h6 class="title-divider text-muted mt40 mb20"> Site Statistics
                        <span class="pull-right text-primary fw600">Today</span>
                    </h6>
                </div>
            </div>
        </aside>
        <!-- -------------- /Sidebar Right -------------- -->
        </div>
		
		
		
	
	 

		 
   
   	
		
        <!-- -------------- /Body Wrap  -------------- -->
        <!-- -------------- Scripts -------------- -->
       
        <!-- -------------- HighCharts Plugin -------------- -->
        <script src="assets/js/plugins/highcharts/highcharts.js"></script>
      
        <!-- -------------- Theme Scripts -------------- -->
        <script src="assets/js/utility/utility.js"></script>
        <script src="assets/js/demo/demo.js"></script>
        <script src="assets/js/main.js"></script>
        <script src="assets/js/demo/widgets_sidebar.js"></script>
        <script src="assets/js/pages/forms-widgets.js"></script>
      <script src="assets/js/jquery.validate.js"></script>
      <script src="assets/js/dataTable/jquery.dataTables.min.js" type="text/javascript"></script>
      <%-- <script src="assets/js/autocomplete.js" type="text/javascript"></script> --%>
      
       <script type="text/javascript">
       
       $(function() {
      	 
    	   // get employees
   	    $( "#autocompleteEmployee" ).autocomplete({
   	      source: function( request, response ) {
   	        $.ajax({
   	          url: "searchEmployees.action",
   	          //dataType: "jsonp",
   	          data: {
   	        	  search:  request.term
   	          },
   	          success: function( data ) {
 					var array = data.error ? [] : $.map(data, function(employee) {
						return {
							label: employee.firstName,
							value: employee.firstName,
							id: employee.id
						};
					});
					response(array);
   	          }
   	        });
   	      },
   	      minLength: 2,
   	      select: function( event, ui ) {
   	    	  console.log("ui.item.id : " + ui.item.id);
   	    	  $("#autocompleteEmployeeId").val(ui.item.id);
   	          $("#fEmployeeSearch").submit();
   	      },
   	      open: function() {
   	        $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
   	      },
   	      close: function() {
   	        $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
   	      }
   	    });
   	    
   	    // get departments
   	    $( "#autocomplete-department-profile" ).autocomplete({
     	      source: function( request, response ) {
     	        $.ajax({
     	          url: "searchDepartments.action",
     	          //dataType: "jsonp",
     	          data: {
     	        	  search:  request.term
     	          },
     	          success: function( data ) {
   					var array = data.error ? [] : $.map(data, function(d) {
  						return {
  							label: d.name,
  							value: d.name,
  							id: d.id
  						};
  					});
  					response(array);
     	          }
     	        });
     	      },
     	      minLength: 2,
     	      select: function( event, ui ) {
     	    	  $("#autocomplete-departmentId-profile").val(ui.item.id);
     	      },
     	      open: function() {
     	        $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
     	      },
     	      close: function() {
     	        $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
     	      }
     	    });
   	    
   	    // get roles
   	    $( "#autocomplete-role-profile" ).autocomplete({
     	      source: function( request, response ) {
     	        $.ajax({
     	          url: "searchRoles.action",
     	          //dataType: "jsonp",
     	          data: {
     	        	  search:  request.term
     	          },
     	          success: function( data ) {
   					var array = data.error ? [] : $.map(data, function(d) {
  						return {
  							label: d.name,
  							value: d.name,
  							id: d.id
  						};
  					});
  					response(array);
     	          }
     	        });
     	      },
     	      minLength: 2,
     	      select: function( event, ui ) {
     	    	  $("#autocomplete-roleId-profile").val(ui.item.id);
     	      },
     	      open: function() {
     	        $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
     	      },
     	      close: function() {
     	        $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
     	      }
     	    });
   	    
   	    
   	  });
       
        	$(document).ready(function(){
        		
        /* 	    var availableTags = [
        	                         "ActionScript",
        	                         "AppleScript",
        	                         "Asp",
        	                         "BASIC",
        	                         "C",
        	                         "C++",
        	                         "Clojure",
        	                         "COBOL",
        	                         "ColdFusion",
        	                         "Erlang",
        	                         "Fortran",
        	                         "Groovy",
        	                         "Haskell",
        	                         "Java",
        	                         "JavaScript",
        	                         "Lisp",
        	                         "Perl",
        	                         "PHP",
        	                         "Python",
        	                         "Ruby",
        	                         "Scala",
        	                         "Scheme"
        	                       ];
        	                       $( "#employees" ).autocomplete({
        	                         source: availableTags
        	                       }); */
        	                       
        	                       
        	                     
        		
                $('.themeDatepicker').datepicker({
                	 prevText: '<i class="fa fa-chevron-left"></i>',
                     nextText: '<i class="fa fa-chevron-right"></i>'
                    /*  showButtonPanel: false,
                     beforeShow: function(input, inst) {
                         var newclass = 'allcp-form';
                         var themeClass = $(this).parents('.allcp-form').attr('class');
                         var smartpikr = inst.dpDiv.parent();
                         if (!smartpikr.hasClass(themeClass)) {
                             inst.dpDiv.wrap('<div class="' + themeClass + '"></div>');
                         }
                     } */
                }); 
                
               /*  $('.themeTimepicker').timepicker({
                    //viewMode: 'years',
                   dateFormat: 'dd/mm/yy'
                });  */
                
                            
                 $('.themeTimepicker').timepicker({
                   
                });  
                
        		var employee = {};
        		
        		//loadEmployee();
        		
        		function loadEmployee(){
        		    $.getJSON('${pageContext.request.contextPath}/employeeJSONAction.action', {
        		    	  //employeeId : 18
        		        }, function(jsonResponse) {
        		          
        		          employee = jsonResponse.employee;
        		          console.log("jsonResponse.employee : " + jsonResponse.employee);
        		          $.each(jsonResponse.employee,function(key,value) {
        		              //$("#employee").find("input[name='employee."+key+"']").val(value);
        		              //$(".emp-profile").find("span[id='display-employee."+key+"']").html(value);
        		              
        		              if(key == 'empGender'){
        		            	  
        		            	  if(value == 0){
        		            		  value = 'Male';
        		            	  }else if(value == 1){
        		            		  value = 'Female';
        		            	  }
        		            	  
        		            	  $(".emp-profile").find("span[id='display-employee."+key+"']").html(value);
        		            	  $(".avatar-display").find("span[id='avatar-display-employee."+key+"']").html(value);
        		              }else{
        		            	  $(".emp-profile").find("span[id='display-employee."+key+"']").html(value);
        		            	  $(".avatar-display").find("span[id='avatar-display-employee."+key+"']").html(value);
        		              }
        		              
        		              console.log("key : " + key + " || value : " + value);
        		          });
        		          
        		          showHide('savebutton')

        		        });
        		}
        		
        		   $("#editbutton").click(function(){
        		        $.each(employee,function(key,value) {
        		        	
       /*  		        	if(key=='empBirthDate'){
        		        		Date birthDate = new Date(value);
        		        		//birthDate
        		        	} */
        		            $("#fEmployee").find("input[name='employee."+key+"']").val(value);
        		        });
/*         	               $('.datepicker').datepicker({
        	                    //viewMode: 'years',
        	                   dateFormat: 'dd/mm/yy'
        	                }); */
        	        	$(".default").hide();
        	        	$(".showform").show();
        	        	$("#savebutton").show();
        	     		$("#editbutton").hide();
        	     });
        	    
        	    
        	    function showHide(buttonId){
        	    	
        	    	if(buttonId =='savebutton'){
        		          $(".default").show();
        		          $(".showform").hide();
        		          $("#savebutton").hide();
        		    	  $("#editbutton").show();
        	    	}

        	    }
            
/*                $("#editbutton").click(function(){
                   $(".default").hide();
                   $(".showform").show();
                   $("#savebutton").show();
                $("#editbutton").hide();
               }); */
            
/*             $("#savebutton").click(function(){
                   $(".default").show();
                   $(".showform").hide();
                   $("#savebutton").hide();
            		$("#editbutton").show();
            
               }); */
               
        		//updating selected employee data
        		$('#savebutton').on('click', function(e) {
        			// employee form data
        			var employeeFormData = $( "#fEmployee" ).serialize();
        			 $.ajax({
        			     type: "POST",
        			     //url: "${pageContext.request.contextPath}/updateEmployeeProfile.action",
        			     url: "${pageContext.request.contextPath}/updateEmployeeProfileJSON.action",
        			     data: employeeFormData,
        			     success: function(response){
        			    	 loadEmployee();
        			         //console.log("SUCCESS Employee Form : " + response);
        			    	 //$("#div1").html(response);
        			     },
        			     error: function(e){
        			    	 loadEmployee();
        			         console.log('ERROR Employee Form : ' + e);
        			     }
        			 });
        			 

        		});
               
               ////
               
               $("#editbutton").click(function(){
                   $(".default").hide();
                   $(".showform").show();
                   $("#savebutton").show();
                $("#editbutton").hide();
               });
            
            $("#savebutton").click(function(){
                   $(".default").show();
                   $(".showform").hide();
                   $("#savebutton").hide();
            		$("#editbutton").show();
            
               });
            
              $("#editbutton1").click(function(){
                   $(".default1").hide();
                   $(".showform1").show();
                   $("#savebutton1").show();
                $("#editbutton1").hide();
               });	
            
            $("#savebutton1").click(function(){
                   $(".default1").show();
                   $(".showform1").hide();
                   $("#savebutton1").hide();
            	$("#editbutton1").show();
            
               });
    		   
    		$("#editbutton2").click(function(){
                   $(".default2").hide();
                   $(".showform2").show();
                   $("#savebutton2").show();
                $("#editbutton2").hide();
            });	
            
            $("#savebutton2").click(function(){
                   $(".default2").show();
                   $(".showform2").hide();
                   $("#savebutton2").hide();
            	$("#editbutton2").show();
            
               });

            $("#editbutton5").click(function(){
                   $(".default5").hide();
                   $(".showform5").show();
                   $("#savebutton5").show();
                $("#editbutton5").hide();
               });	
            
            $("#savebutton5").click(function(){
                   $(".default5").show();
                   $(".showform5").hide();
                   $("#savebutton5").hide();
            	$("#editbutton5").show();
            
               });
    		   
    		   
    		    $("#editbutton6").click(function(){
                   $(".default6").hide();
                   $(".showform6").show();
                   $("#savebutton6").show();
                $("#editbutton6").hide();
               });	
            
            $("#savebutton6").click(function(){
                   $(".default6").show();
                   $(".showform6").hide();
                   $("#savebutton6").hide();
            	$("#editbutton6").show();
            
               });
            
    	    $("#editbutton3").click(function(){
    	        $(".default3").hide();
    	        $(".showform3").show();
    	        $("#savebutton3").show();
    	     $("#editbutton3").hide();
    	    });
    	 
    	 $("#savebutton3").click(function(){
    	        $(".default3").show();
    	        $(".showform3").hide();
    	        $("#savebutton3").hide();
    	 	$("#editbutton3").show();
    	 
    	    });
            
    	    $("#editbutton4").click(function(){
    	        $(".default4").hide();
    	        $(".showform4").show();
    	        $("#savebutton4").show();
    	     $("#editbutton4").hide();
    	    });
    	 
    	 $("#savebutton4").click(function(){
    	        $(".default4").show();
    	        $(".showform4").hide();
    	        $("#savebutton4").hide();
    	 	  $("#editbutton4").show();
    	 
    	    });
    		   
    		    $("#editbutton7").click(function(){
    	        $(".default7").hide();
    	        $(".showform7").show();
    	        $("#savebutton7").show();
    	     $("#editbutton7").hide();
    	    });
    	 
    	 $("#savebutton7").click(function(){
    	        $(".default7").show();
    	        $(".showform7").hide();
    	        $("#savebutton7").hide();
    	 	$("#editbutton7").show();
    	 
    	    });
    		   
    		   
    		   
    		    $("#editbutton8").click(function(){
    	        $(".default8").hide();
    	        $(".showform8").show();
    	        $("#savebutton8").show();
    	     $("#editbutton8").hide();
    	    });
    	 
    	 $("#savebutton8").click(function(){
    	        $(".default8").show();
    	        $(".showform8").hide();
    	        $("#savebutton8").hide();
    	 	$("#editbutton8").show();
    	    });
               
            	//$('.datepicker').datepicker(); assetSoftware
            	
    	      	
            	
            	
    	 $.ajax({
             url: 'resources/asset.txt',
             type: 'GET',
             dataType: 'text',
             success: function (data) {
            	 /* $('#expTable thead td').addClass('bg-dark'); */
 					var dataobj = JSON.parse(data)
 					console.log(dataobj);
 					$('#assetHardware').DataTable( {
 					data: dataobj.data,
 					"bAutoWidth": false,
 					columnDefs: [
 						/* { title: "SR NO","width": "50%" },
 						{ title: "ASSETS","width": "20%" },
 						{ title: "ID" ,"width": "20%"},
 						{ title: "DATE ISSUED","width": "20%" },
 						{ title: "DATE RELEASED","width": "20%" } */
 						
 						 {title: "SR NO","width": "20%", "targets": 0 },
 					      { title: "ASSETS","width": "20%", "targets": 1 },
 					      {title: "ID", "width": "20%", "targets": 2 },
 					      {title: "DATE ISSUED", "width": "20%", "targets": 3 },
 					      { title: "DATE RELEASED","width": "20%", "targets": 4 }
 						
 				            
 					],
 					/*  "createdRow": function ( row, data, index ) {
 			                $('th', row).eq(0).addClass('bg-dark');
 			        }, */ 
 			        
 			       /* "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
 			          $('td:eq(0)', nRow).addClass( "avo-lime-h avo-heading-white" );
 			         $('td:eq(1),td:eq(2),td:eq(3)', nRow).addClass( "bg-dark" );
 			        }, */
 					
 					"paging":   false,
 					bFilter: false,
 					bInfo: false
 				      
 					});
 					
 			},
         });
            	
            	
    	 $.ajax({
             url: 'resources/asset.txt',
             type: 'GET',
             dataType: 'text',
             success: function (data) {
            	 /* $('#expTable thead td').addClass('bg-dark'); */
 					var dataobj = JSON.parse(data)
 					console.log(dataobj);
 					$('#assetSoftware').DataTable( {
 					data: dataobj.data,
 					"bAutoWidth": false,
 					columnDefs: [
 						 {title: "SR NO","width": "20%", "targets": 0 },
 					      { title: "ASSETS","width": "20%", "targets": 1 },
 					      {title: "ID", "width": "20%", "targets": 2 },
 					      {title: "DATE ISSUED", "width": "20%", "targets": 3 },
 					      { title: "DATE RELEASED","width": "20%", "targets": 4 }
 						
 				            
 					],
 					"paging":   false,
 					bFilter: false,
 					bInfo: false
 					});
 					
 			},
         });
            	

            	
			});
        	
        	//validation
       	 $("#fEmployee").validate({
       		  rules: {
       			  "employee.empFirstName": "required"
       		  },
       		  messages: {
       			  "employee.empFirstName": "first name required"
       		  }
       		});
        	
       /* 	$( "#employees" ).keyup(function() {
       		$("#employees").autocomplete("myAutocomplete.jsp");
       	  alert( "Handler for .keyup() called." );
       	}); */
        	
        	 function saveWorkExp(){
        		 var str = $( "#workexpform" ).serialize();
        		 $.ajax({

        		     type: "POST",

        		     url: "${pageContext.request.contextPath}/insertOrUpdateWorkExperience.action",

        		     data: str,
        		    	 //"employee.empFirstName=raj&employee.empLastName=pandi&employee.empBirthDate=09/05/1987&employee.empGender=M",
        		    /*  dataType: 'json',
        		     contentType: 'application/json', */

        		     success: function(response){

        		         console.log(response);

        		     },

        		     error: function(e){

        		         alert('Error: ' + e);

        		     }

        		 });
        	 }
        	 
        	 
        	  	function sessdata(){
            		var ses=$("#testId span").html();
            		alert(ses);
            		
            		   var countries = [
                                       { value: 'Andorra', data: 'AD' },
                                       { value: 'India', data: 'IN' },
                                       { value: 'United states', data: 'US' },
                                       { value: 'United Kingdom', data: 'UK' },
                                       { value: 'Zimbabwe', data: 'ZZ' }
                                    ];
                                    $('#employees').autocomplete({
                                   	 source: countries
                                        
                                    }); 
            	}
        	 
        	 function eduSave(){
        		 var str = $( "#eduForm" ).serialize();
        		 $.ajax({

        		     type: "POST",

        		     url: "${pageContext.request.contextPath}/insertOrUpdateEducation.action",

        		     data: str,
        		    	 //"employee.empFirstName=raj&employee.empLastName=pandi&employee.empBirthDate=09/05/1987&employee.empGender=M",
        		    /*  dataType: 'json',
        		     contentType: 'application/json', */

        		     success: function(response){

        		         console.log(response);

        		     },

        		     error: function(e){

        		         alert('Error: ' + e);

        		     }

        		 });
        	 }
        	 
        	 function saveAsset(){
        		 var str = $( "#assetForm" ).serialize();
        		 $.ajax({

        		     type: "POST",

        		     url: "${pageContext.request.contextPath}/insertOrUpdateEmployeeAsset.action",

        		     data: str,
        		    	 //"employee.empFirstName=raj&employee.empLastName=pandi&employee.empBirthDate=09/05/1987&employee.empGender=M",
        		    /*  dataType: 'json',
        		     contentType: 'application/json', */

        		     success: function(response){

        		         console.log(response);

        		     },

        		     error: function(e){

        		         alert('Error: ' + e);

        		     }

        		 });
        	 }
        	 
        	 function projectSave(){
        		 var str = $( "#projSave" ).serialize();
        		 $.ajax({

        		     type: "POST",

        		     url: "${pageContext.request.contextPath}/insertOrUpdateEmpProjectAssign.action",
					 //url: "${pageContext.request.contextPath}/insertOrUpdateEmpProjectAssignJSON.action",
        		     data: str,
        		    	 //"employee.empFirstName=raj&employee.empLastName=pandi&employee.empBirthDate=09/05/1987&employee.empGender=M",
        		    /*  dataType: 'json',
        		     contentType: 'application/json', */

        		     success: function(response){

        		         //console.log(response);

        		     },

        		     error: function(e){

        		         alert('Error: ' + e);

        		     }

        		 });
        	 }
        	 
        	 function taskSave(){
        		 var str = $( "#taskForm" ).serialize();
        		 $.ajax({

        		     type: "POST",

        		     url: "${pageContext.request.contextPath}/insertOrUpdateProjectActivity.action",

        		     data: str,
        		    	 //"employee.empFirstName=raj&employee.empLastName=pandi&employee.empBirthDate=09/05/1987&employee.empGender=M",
        		    /*  dataType: 'json',
        		     contentType: 'application/json', */

        		     success: function(response){

        		         console.log(response);

        		     },

        		     error: function(e){

        		         alert('Error: ' + e);

        		     }

        		 });
        	 }
        	 
        	 function shiftSave(){
        		 var str = $( "#shiftForm" ).serialize();
        		 $.ajax({

        		     type: "POST",

        		     url: "${pageContext.request.contextPath}/insertOrUpdateEmployeeShift.action",

        		     data: str,
        		    	 //"employee.empFirstName=raj&employee.empLastName=pandi&employee.empBirthDate=09/05/1987&employee.empGender=M",
        		    /*  dataType: 'json',
        		     contentType: 'application/json', */

        		     success: function(response){

        		         console.log(response);

        		     },

        		     error: function(e){

        		         alert('Error: ' + e);

        		     }

        		 });
        	 }
        	 function softwareAssetSave(){
        		 var str = $( "#assetSoftwareForm" ).serialize();
        		 $.ajax({

        		     type: "POST",

        		     url: "${pageContext.request.contextPath}/insertOrUpdateEmployeeAsset.action",

        		     data: str,
        		    	 //"employee.empFirstName=raj&employee.empLastName=pandi&employee.empBirthDate=09/05/1987&employee.empGender=M",
        		    /*  dataType: 'json',
        		     contentType: 'application/json', */

        		     success: function(response){

        		         console.log(response);

        		     },

        		     error: function(e){

        		         alert('Error: ' + e);

        		     }

        		 });
        	 }
        	 
        	 function eeoSave(){
        		 var str = $( "#eeoForm" ).serialize();
        		 var ethnicDocument=$('#eeo-uploader4').val();
        		 var veteranDocument=$('#eeo-uploader5').val();
        		 var militaryDocument=$('#eeo-uploader6').val();
        		 var disabilityDocument=$('#eeo-uploader7').val();
        		 
        		 $.ajax({

        		     type: "POST",

        		     url: "${pageContext.request.contextPath}/insertOrUpdateEEO.action",

        		     data: str+'&eeo.ethnicDocumentIdObj.documentName='+encodeURIComponent(ethnicDocument)+'&eeo.veteranDocumentIdObj.documentName='+
        		     encodeURIComponent(veteranDocument)+'&eeo.militaryDocumentIdObj.documentName='+encodeURIComponent(militaryDocument)+'&eeo.disabilityDocumentIdObj.documentName='+
        		     encodeURIComponent(disabilityDocument),

        		     success: function(response){

        		         ///console.log(response);

        		     },

        		     error: function(e){

        		         alert('Error: ' + e);

        		     }

        		 });
        	 }
        	 
        	 
        	 function saveapprover(){
        		 if ((!$('#timesheet').is(":checked")) && (!$('#expensess').is(":checked"))){
        			 alert("Please select atleast one Timesheet or Expenses");
        			 return false;
        		 }
        		 var approverid=$('#approverid').val();
        		 var timesheetEmpId=$('#timesheetAppId').val();
        		 var expApproverEmpid=$('#expAppId').val();
        		 
        		 
        		 approverid = 18;
        		 timesheetEmpId = 18;
        		 
        		 var urlaction;
        		 var dataapprover;
        		 var urlactionExp;
        		 var dataapproverExp;
        		 if ($('#expensess').is(":checked") && $('#timesheet').is(":checked"))
        		 {
        			 urlaction="${pageContext.request.contextPath}/insertOrUpdateTimeSheetApprover.action";
        			 dataapprover='timeSheetApprover.hcmoApprovingEmpId.employeeId='+approverid+'&timeSheetApprover.hcmoEmployeeId.employeeId='+timesheetEmpId;
        			 
        			 urlactionExp="${pageContext.request.contextPath}/insertOrUpdateExpApprover.action";
        			 dataapproverExp='expApprover.approvingEmployeeId.employeeId='+approverid+'&expApprover.hcmoEmployeeId.employeeId='+expApproverEmpid;
        			 approverSaveExpTimesheet(urlaction,dataapprover,urlactionExp,dataapproverExp);
        			
        		 }else if($('#timesheet').is(":checked")){
        			 
        			 urlaction="${pageContext.request.contextPath}/insertOrUpdateTimeSheetApprover.action";
        			 dataapprover='timeSheetApprover.hcmoApprovingEmpId.employeeId='+approverid+'&timeSheetApprover.hcmoEmployeeId.employeeId='+timesheetEmpId;
        			 
        			 $.ajax({

        			     type: "POST",

        			     url: urlaction,

        			     data: dataapprover,

        			     success: function(response){

        			         console.log(response);

        			     },

        			     error: function(e){

        			         alert('Error: ' + e);

        			     }

        			 }); 
        		
        		 }
        		 else{
        			 urlaction="${pageContext.request.contextPath}/insertOrUpdateExpApprover.action";
        			 dataapprover='expApprover.approvingEmployeeId.employeeId='+approverid+'&expApprover.hcmoEmployeeId.employeeId='+expApproverEmpid;
        			 
        			 $.ajax({

        			     type: "POST",

        			     url: urlaction,

        			     data: dataapprover,

        			     success: function(response){

        			         console.log(response);

        			     },

        			     error: function(e){

        			         alert('Error: ' + e);

        			     }

        			 }); 
        			 
        		 }
        		
        		 
        		
        	 }
        	 
        	 
				function approverSaveExpTimesheet(urlaction,dataapprover,urlactionExp,dataapproverExp){
        		 
        		 $.ajax({

        		     type: "POST",

        		     url: urlaction,

        		     data: dataapprover,

        		     success: function(response){

        		         approverSaveTimesheet(urlactionExp,dataapproverExp);

        		     },

        		     error: function(e){

        		        console.log(e);

        		     }

        		 }); 
        		 
        	 }
        	 
        	 function approverSaveTimesheet(urlactionExp,dataapproverExp){
        		 
        		 
        		 $.ajax({

        		     type: "POST",

        		     url: urlactionExp,

        		     data: dataapproverExp,

        		     success: function(response){
        		    	 console.log(response);

        		     },

        		     error: function(e){

        		        console.log(e);

        		     }

        		 }); 
        		 
        	 }
        	 
        	 function paytabsave(){
        		 var str = $( "#paytabform" ).serialize();
        		 	 
        		 $.ajax({

        		     type: "POST",

        		     url: "${pageContext.request.contextPath}/insertOrUpdatePayStub.action",

        		     data: str,

        		     success: function(response){

        		         //console.log(response);

        		     },

        		     error: function(e){

        		         alert('Error: ' + e);

        		     }

        		 });
        	 }
        	 
        	 function changeEmployeeView(){
        		 $("#fSearch").submit();
        	 }
        	 
        	 
        	 function readURL(input) {
        		 console.log(input.files);
                 if (input.files && input.files[0]) {
                     var reader = new FileReader();

                     reader.onload = function (e) {
                         $('#pImage')
                             .attr('src', e.target.result)
                             .width(100)
                        .height(100);
                           
                     };

                     reader.readAsDataURL(input.files[0]);
                 }
             }
        	 
        	
       </script>  

</body>
</html>