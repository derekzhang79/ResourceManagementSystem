<!DOCTYPE html>
<html>
    <head>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        
    </head>
	<body class="basic-profile">
		<!-- -------------- Customizer -------------- -->
		<!-- -------------- /Customizer -------------- -->
		<!-- -------------- Body Wrap  -------------- -->
		<div id="main">
			<!-- -------------- Header  -------------- -->
			<jsp:include page="common/header.jsp" flush="true" />
			<!-- -------------- Sidebar  -------------- -->
			<%-- <jsp:include page="common/menu_load.jsp" flush="true" /> --%>
			<jsp:include page="common/menu.jsp" flush="true" />
			<jsp:include page="common/body.jsp" flush="true" />	
		</div>

		<!-- -------------- /Body Wrap  -------------- -->
		<!-- -------------- Scripts -------------- -->
		<!-- -------------- jQuery -------------- -->
		<script src="assets/js/jquery/jquery-1.11.3.min.js"></script>
		<script src="assets/js/jquery/jquery_ui/jquery-ui.min.js"></script>
		<!-- -------------- HighCharts Plugin -------------- -->
		<script src="assets/js/plugins/highcharts/highcharts.js"></script>
		<!-- -------------- MonthPicker JS -------------- -->
		<script src="assets/allcp/forms/js/jquery-ui-monthpicker.min.js"></script>
		<script src="assets/allcp/forms/js/jquery-ui-datepicker.min.js"></script>
		<script src="assets/allcp/forms/js/jquery.spectrum.min.js"></script>
		<script src="assets/allcp/forms/js/jquery.stepper.min.js"></script>
		<!-- -------------- Theme Scripts -------------- -->
		<script src="assets/js/utility/utility.js"></script>
		<script src="assets/js/demo/demo.js"></script>
		<script src="assets/js/main.js"></script>
		<script src="assets/js/demo/widgets_sidebar.js"></script>
		<script src="assets/js/pages/forms-widgets.js"></script>
		
		<script  src="asset/js/dataTable/jquery.dataTables.min.js"></script>
	
		<script type="text/javascript">
 				$(document).ready(function(){
						
						 $.ajax({
						     type: "POST",
						     url: "${pageContext.request.contextPath}/setUpEmployees.action",
						     data: "hi",
						     success: function(response){
						        $("#body-content").html(response);
						     },
						     error: function(e){
						         console.log('Error setupEmployees : ' + e);
						     }
				
						 });		
				  
				 }); 
			</script>
	</body>
</html>