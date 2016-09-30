<%-- <%@ include file="/resources/includes/taglibs.jsp"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
<head>
    <!-- -------------- Meta and Title -------------- -->
    <meta charset="utf-8">
    <title>HCMOne - Dashboard</title>
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

    <!-- -------------- FullCalendar -------------- -->
    <link rel="stylesheet" type="text/css" href="assets/js/plugins/fullcalendar/fullcalendar.min.css">
    <link rel="stylesheet" type="text/css" href="assets/js/plugins/magnific/magnific-popup.css">

    <!-- -------------- Plugins -------------- -->
    <link rel="stylesheet" type="text/css" href="assets/js/plugins/c3charts/c3.min.css">

    <!-- -------------- CSS - theme -------------- -->
    <link rel="stylesheet" type="text/css" href="assets/skin/default_skin/css/theme.css">
        
    <!-- -------------- CSS - Custom -------------- -->
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style1.css">

    <!-- -------------- CSS - allcp forms -------------- -->
    <link rel="stylesheet" type="text/css" href="assets/allcp/forms/css/forms.css">

    <!-- -------------- Favicon -------------- -->
    <link rel="shortcut icon" href="assets/img/favicon.ico">

    <!-- -------------- IE8 HTML5 support  -------------- -->
    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

	<sx:head />
</head>

<body class="dashboard-page">

<!-- -------------- Customizer -------------- -->
<div id="customizer" class="hidden-xs">
    <div class="panel">
         
 
    </div>
</div>
<!-- -------------- /Customizer -------------- -->

<!-- -------------- Body Wrap  -------------- -->
<div id="main">

    <!-- -------------- Header  -------------- -->
    <header class="navbar navbar-fixed-top">
        <div class="navbar-logo-wrapper">
            <a class="navbar-logo-text" href="Dashboard.html">
			<img src="assets/img/hcm-logo.png" class="logo">
                <b>HCMOne</b>
            </a>
            <span id="sidebar_left_toggle" class="ad ad-lines"></span>
        </div>
      
        <form class="navbar-form navbar-left search-form square" role="search">
            <div class="input-group add-on">

                <input type="text" class="form-control" placeholder="Search..." onfocus="this.placeholder=''"
                       onblur="this.placeholder='Search...'">

                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                </div>
            </div>

            
<%--                             <sx:autocompleter label="" 
name="yourLuckyNumber" autoComplete="true" cssStyle="form-control"
list="currencyList"
listKey="hcmoCurrencyId"
listValue="currencyType"
/>  --%>


<%-- 				<sj:autocompleter  
				    name="expApprover.approvingEmployeeId.employeeId"
					list="currencyList"
cssStyle="form-control"

				    selectBox="true"
				    selectBoxIcon="true"
				    
		    	/> --%>
        </form>
       	<!--  <ul class="nav navbar-nav navbar-right">
		
					<li class="dropdown dropdown-fuse">
                             
                             <a  href="Recruit.html"> <img src="assets/img/Rlitesmall.jpg" class="hcm-logoright"></a>
               				 
                    </li>
					
					
                    <li class="dropdown dropdown-fuse">
					
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
                            <span class="fa fa-power-off fs26 text-danger"></span>  
                            </div>
                        </div>
                    </li>
                </ul> -->
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
                                    <s:text name="application.welcome.message"/>
                                    &nbsp;<s:text name="#session.FIRST_NAME"></s:text>&nbsp;
                                    <s:text name="#session.MIDDLE_NAME"></s:text>&nbsp;
                                    <s:text name="#session.LAST_NAME"></s:text>, &nbsp;
                                    <s:text name="#session.ROLE"></s:text>
                                    </div>
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
                            <a  href="Dashboard.html">
                            <span class="fa fa-bar-chart"></span>
                            <span class="sidebar-title">Org Chart</span>
                            </a>
                        </li>
                        <li>
                            <a href="Timesheet.html">
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
                            <a  href="Add-Employee.html">
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
				  <!--  <div class="sidebar-chat">
											
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
				</div> -->
				

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
      
	
	 
	
	<!-- -------------- Topbar -------------- -->
        <header id="topbar" class="alt">
            <div class="topbar-left">
                <ol class="breadcrumb">
                    <li class="breadcrumb-icon">
                        <a href="Dashboard.html">
                            <span class="fa fa-home"></span>
                        </a>
                    </li>
                    <li class="breadcrumb-active">
                        <a href="Dashboard.html">Dashboard</a>
                    </li>
                    <!--<li class="breadcrumb-link">
                        <a href="index.html">Home</a>
                    </li>
                    <li class="breadcrumb-current-item">Dashboard</li>-->
                </ol>
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
		   <a href="setUpEmployeesNEW.action" class="btn  btn-primary">
		       <i class="fa fa-user"></i>
		   </a>
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
        <!-- -------------- /Topbar -------------- -->
	
	
	
	
	
	
	
	

        <!-- -------------- Content -------------- -->
     <section class="table-layout" id="content">
	 <div class="row">
	 
	  <div class="col-md-4  nopadding-left">
	  
	  <div class="col-sm-1">
	  <div class="task-icon"><i class="fa fa-male" aria-hidden="true"></i></div>
	  </div>
		  
		<div class="col-sm-9 addtask-back1">
					<div class="table-responsive">
                                    <table class="table allcp-form theme-warning tc-checkbox-1">
                                        <thead>
                                        <tr class="bg-light Dashboard-table">
                                            <th class="">Last One Week</th>
                                            <th class="">Last Three Days</th>
                                            <th class="">Total</th>
                                         </tr>
                                        </thead>
                                        <tbody>
                                        <tr> 
                                            <td class="">5</td>
                                            <td class="">10</td>
                                            <td class="">15</td>
                                                                                  
                                        </tr>
																				
										</table>
						 </div>		
			</div>
			
			<div class="col-sm-1 Add-plus">
		  <i class="fa fa-plus" aria-hidden="true"></i>  
							 
		 </div>
			
					 
				 
	  </div>
	  
	  
	   <div class="col-md-4">
	 		 <div class="col-md-1">
						 <div class="task-icon1"> <i class="fa fa-clock-o" aria-hidden="true"></i></div>
				 </div>
				 <div class="col-md-9 addtask-back">
				 
					 <div class="table-responsive">
					  
                                    <table class="table allcp-form theme-warning tc-checkbox-1 ">
                                        <thead>
                                        <tr class="bg-light Dashboard-table">
                                            <th class="">Today</th>
											   <th class="">Last One Week</th>
                                            <th class="">Last Three Days</th>
                                            <th class="">Total</th>
                                         </tr>
                                        </thead>
                                        <tbody>
                                        <tr> 
											<td class="">15</td>
                                            <td class="">7</td>
                                            <td class="">5</td>
                                            <td class="">27</td>
                                                                                  
                                        </tr>
																				
										</table>
						 </div>
				 </div>
				  
						<div class="col-md-1 Add-plus1">
						 <i class="fa fa-plus" aria-hidden="true"></i> 
							 
						 </div>
				 
                   
					
					
	   
	   
		 
	  </div>
	  
	  
	  
	    <div class="col-md-4  nopadding-right">
	 		 <div class="col-md-1">
						 <div class="task-icon2"> <i class="fa fa-clock-o" aria-hidden="true"></i></div>
				 </div>
				 <div class="col-md-9 addtask-back">
				 
					 <div class="table-responsive">
					  
                                    <table class="table allcp-form theme-warning tc-checkbox-1 ">
                                        <thead>
                                        <tr class="bg-light Dashboard-table">
                                            <th class="">Today</th>
											   <th class="">Last One Week</th>
                                            <th class="">Last Three Days</th>
                                            <th class="">Total</th>
                                         </tr>
                                        </thead>
                                        <tbody>
                                        <tr> 
											<td class="">15</td>
                                            <td class="">7</td>
                                            <td class="">5</td>
                                            <td class="">27</td>
                                                                                  
                                        </tr>
																				
										</table>
						 </div>
				 </div>
				  
						<div class="col-md-1 Add-plus2">
						 <i class="fa fa-plus" aria-hidden="true"></i> 
							 
						 </div>
				 
                   
					
					
	   
	   
		 
	  </div>
	  
	  
	  
	  
	 </div>
	 
	 </section>
	 
	 
	 
	 
	 
	 
	 
	 
	 
	     <section class="table-layout" id="content">
	 	 <div class="row">

		 <div class="col-md-4">
		 
		<div class="panel mbn btn-primary">
		
 	      <div class="panel-body">
			  
			  
		<div data-ride="carousel" class="carousel slide" id="myCarousel">
  <!-- Indicators -->
 

  <!-- Wrapper for slides -->
  <div role="listbox" class="carousel-inner">
  
    <div class="item">
	
	
	
<div class="mb10 ">
		<div class="text-light fw700 ">Saturday</div>	
		 <div class="text-dark fw700">December 16</div>
	</div>
	
 
	
<div class="mb20">
  <div class="text-light fw700  mb10 mt10 events"><i aria-hidden="true" class="fa fa-file-text-o"></i> Task</div> 
 
<div class="row">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>
 
 
 <div class="row">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>	

<div class="row mb10">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>	

 </div>


 
<div class="mb20 mt20">
 <div class="text-light fw700   mb10 mt20 events"> <i aria-hidden="true" class="fa fa-birthday-cake"></i> Birthday</div>
<div class="row">
 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
 </div>	
 

	
	
 <div class="row">
 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
 </div>
	
	<div class="row mb10">
	 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
	
 </div>
 
 </div>
 
 
 
 
<div class="mt20">
 <div class="text-light fw700   mb20 mt20 events"> <i aria-hidden="true" class="fa fa-calendar"></i> Events</div>
 
 
 <div class="row">
 <div class="col-md-3">
Evendais 
 </div>
 <div class="col-md-9">
Here are general
 </div>							
 </div>	
 
 
 <div class="row">
 <div class="col-md-3">
Gossions 
 </div>
 <div class="col-md-9">
The General Here are general
 </div>							
 </div>	
 
 
 <div class="row">
 <div class="col-md-3">
Evendais 
 </div>
 <div class="col-md-9">
Here are general
 </div>							
 </div>	
 
 </div>
 
 
      
  </div>

    <div class="item active">
	
<div class="mb10 ">
		<div class="text-light fw700 ">Monday</div>	
		 <div class="text-dark fw700">December 9</div>
	</div>
	
<div class="mb20">
 <div class="text-light fw700   mb10 mt10 events"><i aria-hidden="true" class="fa fa-file-text-o"></i> Task</div>
 
<div class="row">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>
 
 
 <div class="row">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>	

<div class="row mb10">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>	

 </div>


 
<div class="mb20 mt20">
 <div class="text-light fw700   mb10 mt20 events"> <i aria-hidden="true" class="fa fa-birthday-cake"></i> Birthday</div>
 
 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
 
 

	
	
 
 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
 
	
	 
	 <div class="col-md-12 mb10">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
	
  
 
 </div>
 
 
 
 
<div class="mt20">
 <div class="text-light fw700   mb20 mt20 events"> <i aria-hidden="true" class="fa fa-calendar"></i> Events</div>
 
 
 <div class="row">
 <div class="col-md-3">
Evendais 
 </div>
 <div class="col-md-9">
Here are general
 </div>							
 </div>	
 
 
 <div class="row">
 <div class="col-md-3">
Gossions 
 </div>
 <div class="col-md-9">
The General Here are general
 </div>							
 </div>	
 
 
 <div class="row">
 <div class="col-md-3">
Evendais 
 </div>
 <div class="col-md-9">
Here are general
 </div>							
 </div>	
 
 </div>
       
    </div>

    <div class="item">
<div class="mb10 ">
		<div class="text-light fw700 ">Friday</div>	
		 <div class="text-dark fw700">December 18</div>
	</div>
	
<div class="mb20">
 <div class="text-light fw700   mb10 mt10 events"><i aria-hidden="true" class="fa fa-file-text-o"></i> Task</div>
 
<div class="row">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>
 
 
 <div class="row">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>	

<div class="row mb10">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>	

 </div>


 
<div class="mb20 mt20">
 <div class="text-light fw700   mb10 mt20 events"> <i aria-hidden="true" class="fa fa-birthday-cake"></i> Birthday</div>
<div class="row">
 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
 </div>	
 

	
	
 <div class="row">
 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
 </div>
	
	<div class="row mb10">
	 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
	
 </div>
 
 </div>
 
 
 
 
<div class="mt20">
 <div class="text-light fw700   mb20 mt20 events"> <i aria-hidden="true" class="fa fa-calendar"></i> Events</div>
 
 
 <div class="row">
 <div class="col-md-3">
Evendais 
 </div>
 <div class="col-md-9">
Here are general
 </div>							
 </div>	
 
 
 <div class="row">
 <div class="col-md-3">
Gossions 
 </div>
 <div class="col-md-9">
The General Here are general
 </div>							
 </div>	
 
 
 <div class="row">
 <div class="col-md-3">
Evendais 
 </div>
 <div class="col-md-9">
Here are general
 </div>							
 </div>	
 
 </div>
    </div>

    <div class="item">
<div class="mb10 ">
		<div class="text-light fw700 ">Saturday</div>	
		 <div class="text-dark fw700">December 16</div>
	</div>
	
<div class="mb20">
 <div class="text-light fw700   mb10 mt10 events"><i aria-hidden="true" class="fa fa-file-text-o"></i> Task</div>
 
<div class="row">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>
 
 
 <div class="row">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>	

<div class="row mb10">
 <div class="col-md-9">
 Curabitur convallis lacus urna,  
 </div>
 <div class="col-md-3">
 1:00pm
 </div>							
 </div>	

 </div>


 
<div class="mb20 mt20">
 <div class="text-light fw700   mb10 mt20 events"> <i aria-hidden="true" class="fa fa-birthday-cake"></i> Birthday</div>
<div class="row">
 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
 </div>	
 

	
	
 <div class="row">
 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
 </div>
	
	<div class="row mb10">
	 <div class="col-md-12">
Kubesh.k Sherman
Lithi k.Rambert
 </div>
	
 </div>
 
 </div>
 
 
 
 
<div class="mt20">
 <div class="text-light fw700   mb20 mt20 events"> <i aria-hidden="true" class="fa fa-calendar"></i> Events</div>
 
 
 <div class="row">
 <div class="col-md-3">
Evendais 
 </div>
 <div class="col-md-9">
Here are general
 </div>							
 </div>	
 
 
 <div class="row">
 <div class="col-md-3">
Gossions 
 </div>
 <div class="col-md-9">
The General Here are general
 </div>							
 </div>	
 
 
 <div class="row">
 <div class="col-md-3">
Evendais 
 </div>
 <div class="col-md-9">
Here are general
 </div>							
 </div>	
 
 </div>
  </div>
  
  
  

  <!-- Left and right controls -->
  <a data-slide="prev" role="button" href="#myCarousel" class="left carousel-control">
    <span aria-hidden="true" class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
	
		
  
  <a data-slide="next" role="button" href="#myCarousel" class="right carousel-control">
    <span aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>



		
	</div>			
	</div>		 
		   
		</div>	

 </div>
 
 </a>
			<div class="col-md-8 leave">
			
			
			
			   <div class="panel" id="pchart6">
                    <div class="panel-heading mt10">
              <span class="panel-title text-info fw600 ph20">
                <i class="fa fa-pencil hidden"></i> Leave</span>
                    </div>
                 <!--    <div class="panel-menu br3 mt20">
                        <div class="chart-legend" data-chart-id="#high-line3">
                            <a type="button" data-chart-id="0" class="legend-item btn btn-sm btn-warning">Today</a>
                            <a type="button" data-chart-id="1" class="legend-item btn btn-primary btn-sm">Last one week</a>
                            <a type="button" data-chart-id="2" class="legend-item btn btn-info btn-sm">Last three days</a>
						  <a type="button" data-chart-id="2" class="legend-item btn btn-info btn-sm">today</a>
                        </div>
                    </div> -->
                    <div class="panel-body pn">
                        <div id="high-line3" style="width: 100%; height: 150px; "></div>
                    </div>
                </div>
				
				<div class="col-md-6 nopadding-left">
				<div class="panel" id="spy1">
                                <div class="panel-heading mt10">
                                    <span class="panel-title  text-info fw600 ph20">Expense</span>
                                </div>
                                <div class="panel-body p20 br-a br6">

                                    <div id="donut-chart1" style="height: 150px; width: 100%;"></div>

                                </div>
                                 

                            </div>
				
				</div>
				
				<div class="col-md-6 nopadding-right">
				
				<div class="panel" id="pchart2">
                    <div class="panel-heading mt10">
              <span class="panel-title text-info fw600 ph20">
                <i class="fa fa-pencil hidden"></i>Time Sheet</span>
                    </div>
                    <div class="panel-menu hidden">
                        <div class="chart-legend" data-chart-id="#high-line">
                            <button type="button" data-chart-id="0" class="legend-item btn btn-sm btn-primary mr10">
                                Data 1
                            </button>
                            <button type="button" data-chart-id="1" class="legend-item btn btn-info btn-sm">Data 2
                            </button>
                        </div>
                    </div>
                    <div class="panel-body pn">
                        <div id="high-line" style="width: 100%; height: 200px;  "></div>

                         
                    </div>
                </div>
				
				</div>
				
				
				
				
				
				
				
				
				
				
		</div>
		
		
			 
			</div>
		 
	 
	 </div>
		
    </section>
		
	
	
 
	 
	 
	  
       
            <!-- -------------- /Column Center -------------- -->

            <!-- -------------- Column Right -------------- -->
            <aside class="chute chute-right chute270 pn hidden" data-chute-height="match">

                <!-- -------------- Activity Timeline -------------- -->
                <ol class="timeline-list pl5 mt5">
                    <li class="timeline-item">
                        <div class="timeline-icon bg-dark light">
                            <span class="fa fa-tags"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Genry</b> Added a new item to his store:
                            <a href="#">Ipod</a>
                        </div>
                        <div class="timeline-date">1:25am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-dark light">
                            <span class="fa fa-tags"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Lion</b> Added a new item to his store:
                            <a href="#">Notebook</a>
                        </div>
                        <div class="timeline-date">3:05am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-success">
                            <span class="fa fa-usd"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Admin</b> created a new invoice for:
                            <a href="#">Software</a>
                        </div>
                        <div class="timeline-date">4:15am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-warning">
                            <span class="fa fa-pencil"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Laura</b> edited her work experience
                        </div>
                        <div class="timeline-date">5:25am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-success">
                            <span class="fa fa-usd"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Admin</b> created a new invoice for:
                            <a href="#">Apple Inc.</a>
                        </div>
                        <div class="timeline-date">7:45am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-dark light">
                            <span class="fa fa-tags"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Genry</b> Added a new item to his store:
                            <a href="#">Ipod</a>
                        </div>
                        <div class="timeline-date">8:25am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-dark light">
                            <span class="fa fa-tags"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Lion</b> Added a new item to his store:
                            <a href="#">Watch</a>
                        </div>
                        <div class="timeline-date">9:35am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-system">
                            <span class="fa fa-fire"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Admin</b> created a new invoice for:
                            <a href="#">Software</a>
                        </div>
                        <div class="timeline-date">4:15am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-warning">
                            <span class="fa fa-pencil"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Laura</b> edited her work experience
                        </div>
                        <div class="timeline-date">5:25am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-success">
                            <span class="fa fa-usd"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Admin</b> created a new invoice for:
                            <a href="#">Software</a>
                        </div>
                        <div class="timeline-date">4:15am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-warning">
                            <span class="fa fa-pencil"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Laura</b> edited her work experience
                        </div>
                        <div class="timeline-date">5:25am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-success">
                            <span class="fa fa-usd"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Admin</b> created a new invoice for:
                            <a href="#">Apple Inc.</a>
                        </div>
                        <div class="timeline-date">7:45am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-dark light">
                            <span class="fa fa-tags"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Genry</b> Added a new item to his store:
                            <a href="#">Ipod</a>
                        </div>
                        <div class="timeline-date">8:25am</div>
                    </li>
                    <li class="timeline-item">
                        <div class="timeline-icon bg-dark light">
                            <span class="fa fa-tags"></span>
                        </div>
                        <div class="timeline-desc">
                            <b>Lion</b> Added a new item to his store:
                            <a href="#">Watch</a>
                        </div>
                        <div class="timeline-date">9:35am</div>
                    </li>
                </ol>

            </aside>
            <!-- -------------- /Column Right -------------- -->

        </section>
        <!-- -------------- /Content -------------- -->

        <!-- -------------- Page Footer -------------- -->
         
        <!-- -------------- /Page Footer -------------- -->

    </section>
    <!-- -------------- /Main Wrapper -------------- -->

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
                            <i class="fa fa-caret-down"></i> 15.7% </h3>
                    </div>
                </div>

                <h6 class="title-divider text-muted mt25 mb10">Returnig visitors</h6>

                <div class="row">
                    <div class="col-xs-5">
                        <h3 class="text-primary mn pl5">660</h3>
                    </div>
                    <div class="col-xs-7 text-right">
                        <h3 class="text-success-dark mn">
                            <i class="fa fa-caret-up"></i> 20.2% </h3>
                    </div>
                </div>

                <h6 class="title-divider text-muted mt25 mb10">Orders</h6>

                <div class="row">
                    <div class="col-xs-5">
                        <h3 class="text-primary mn pl5">153</h3>
                    </div>
                    <div class="col-xs-7 text-right">
                        <h3 class="text-success mn">
                            <i class="fa fa-caret-up"></i> 5.3% </h3>
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


<!-- -------------- Scripts -------------- -->

<!-- -------------- jQuery -------------- -->
<script src="assets/js/jquery/jquery-1.11.3.min.js"></script>
<script src="assets/js/jquery/jquery_ui/jquery-ui.min.js"></script>

<!-- -------------- Sparklines JS -------------- -->
<script src="assets/js/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="assets/js/demo/charts/flot.js"></script>
<!-- -------------- Charts JS -------------- -->

<!-- -------------- Plugins -------------- -->
<script src="assets/js/plugins/jqueryflot/jquery.flot.min.js"></script>
<script src="assets/js/plugins/jqueryflot/jquery.flot.resize.min.js"></script>
<script src="assets/js/plugins/jqueryflot/jquery.flot.pie.min.js"></script>
<script src="assets/js/plugins/jqueryflot/jquery.flot.tooltip.min.js"></script>

<script src="assets/js/plugins/highcharts/highcharts.js"></script>
<script src="assets/js/plugins/circles/circles.js"></script>

<!-- -------------- Theme Scripts -------------- -->
<script src="assets/js/utility/utility.js"></script>
<script src="assets/js/demo/demo.js"></script>
<script src="assets/js/main.js"></script>
<script src="assets/js/demo/widgets_sidebar.js"></script>

<!-- -------------- Page JS -------------- -->
<script src="assets/js/demo/charts/highcharts.js"></script>




<script type="text/javascript">
    jQuery(document).ready(function () {

        "use strict";

        // Init Theme Core
        Core.init();

        // Init Demo JS
        Demo.init();

        // Flot charts JS
        FlotCharts.init();


    });
</script>
<!-- -------------- /Scripts -------------- -->



<script type="text/javascript">
    jQuery(document).ready(function () {

        "use strict";

        // Init Theme Core
        Core.init();

        // Init Demo JS
        Demo.init();

        // ChighCharts JS
        demoHighCharts.init();

    });
</script>
<!-- -------------- /Scripts -------------- -->

<!-- -------------- HighCharts Plugin -------------- -->
<script src="assets/js/plugins/highcharts/highcharts.js"></script>
<script src="assets/js/plugins/c3charts/d3.min.js"></script>
<script src="assets/js/plugins/c3charts/c3.min.js"></script>

<!-- -------------- Simple Circles Plugin -------------- -->
<script src="assets/js/plugins/circles/circles.js"></script>

<!-- -------------- Maps JSs -------------- -->
<script src="assets/js/plugins/jvectormap/jquery.jvectormap.min.js"></script>
<script src="assets/js/plugins/jvectormap/assets/jquery-jvectormap-us-lcc-en.js"></script>

<!-- -------------- FullCalendar Plugin -------------- -->
<script src="assets/js/plugins/fullcalendar/lib/moment.min.js"></script>
<script src="assets/js/plugins/fullcalendar/fullcalendar.min.js"></script>

<!-- -------------- Date/Month - Pickers -------------- -->
<script src="assets/allcp/forms/js/jquery-ui-monthpicker.min.js"></script>
<script src="assets/allcp/forms/js/jquery-ui-datepicker.min.js"></script>

<!-- -------------- Magnific Popup Plugin -------------- -->
<script src="assets/js/plugins/magnific/jquery.magnific-popup.js"></script>
<!-- -------------- Widget JS -------------- -->
<script src="assets/js/demo/widgets.js"></script>
<script src="assets/js/demo/widgets_sidebar.js"></script>
<script src="assets/js/pages/dashboard1.js"></script>
<!-- -------------- /Scripts -------------- -->

</body>

</html>
