<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://code.google.com/p/jcaptcha4struts2/taglib/1.0" prefix="jcaptcha" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Calendar"%>
<head>
    <!-- -------------- Meta and Title -------------- -->
    <meta charset="utf-8">
    <title>HCMOne</title>
    <meta name="keywords" content="HTML5, Bootstrap 3, Admin Template, UI Theme"/>
    <meta name="description" content="Alliance - A Responsive HTML5 Admin UI Framework">
    <meta name="author" content="ThemeREX">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
.error{
color: red;
}
</style>
    <!-- -------------- Fonts -------------- -->
    <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700'>
    <link href='https://fonts.googleapis.com/css?family=Lato:400,300,300italic,400italic,700,700italic' rel='stylesheet'
          type='text/css'>

    <!-- -------------- CSS - theme -------------- -->
    <link rel="stylesheet" type="text/css" href="assets/skin/default_skin/css/theme.css">
    
    <!-- -------------- CSS - Custom -------------- -->
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        
    <!-- -------------- MEDIA CSS - Custom -------------- -->
    <link rel="stylesheet" type="text/css" href="assets/css/responsive.css">

    <!-- -------------- CSS - allcp forms -------------- -->
    <link rel="stylesheet" type="text/css" href="assets/allcp/forms/css/forms.css">

    <!-- -------------- Favicon -------------- -->
    <link rel="shortcut icon" href="assets/img/favicon.ico">

    <!-- -------------- IE8 HTML5 support  -------------- -->
    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="utility-page sb-l-c sb-r-c LoginPage">

<!-- -------------- Body Wrap  -------------- -->
<div id="main" class="animated fadeIn">

    <!-- -------------- Main Wrapper -------------- -->
    <section id="content_wrapper">

        <div id="canvas-wrapper">
            <canvas id="demo-canvas"></canvas>
        </div>

        <!-- -------------- Content -------------- -->
        <section id="content">

            <!-- -------------- Login Form -------------- -->
            <div class="allcp-form theme-primary" id="login">
                <!--<div class="text-center mb20 hcmone_logo"><img src="assets/img/logo-hcmone.png" class="img-responsive"
                                                   alt="Logo"/></div>-->

                <div class="panel mw320">

                    <div class="panel-heading pn">
                                    <span class="panel-title">
                                      User Login
                                    </span>
                    </div>
                    <s:form name="form_login" id="form_login" method="post" action="doLogin" cssClass="form-login">
                        <div class="panel-body pn mv10">
                            <div class="section">
                                <label for="username" class="field prepend-icon">
<!--                                     <input type="text" name="username" id="username" class="gui-input"
                                           placeholder="Username"> -->
                                           <s:textfield name="username" id="username" cssClass="gui-input"
                                           placeholder="Username"/>
                                    <label for="username" class="field-icon">
                                        <i class="fa fa-user"></i>
                                    </label>
                                </label>
                                <div class="error">
                                <s:fielderror fieldName="username"/>
                                </div>
                            </div>
                            <!-- -------------- /section -------------- -->

                            <div class="section">
                                <label for="password" class="field prepend-icon">
                                    <!-- <input type="text" name="password" id="password" class="gui-input"
                                           placeholder="Password"> -->
                                           <s:password name="password" id="password" cssClass="gui-input" placeholder="Password"/>
                                    <label for="password" class="field-icon">
                                        <i class="fa fa-lock"></i>
                                    </label>
                                </label>
                                <div class="error">
                                <s:fielderror fieldName="password"/>
                                </div>
                            </div>
                            <!-- -------------- /section -------------- -->
                            	<%
		Calendar c = Calendar.getInstance();
		String sMillis = String.valueOf(c.getTimeInMillis());
	%>
	<div class="section">
	Calculate <img src="getCaptchaImage.action?millis=<%=sMillis%>" alt="captcha image"/> 
	</div>
                            <div class="section">
                                <label for="captchacode" class="field prepend-icon">
										  
	                    				<input type="text" name="captchacode" id="captchacode" class="gui-input" placeholder="Captcha" autocomplete="off"/> 
                                    <label for="captchacode" class="field-icon">
                                        <i class="fa fa-lock"></i>
                                    </label>
                                </label>
                                <div class="error">
                                <s:fielderror fieldName="captchacode"/>
                                </div>
                            </div>
                             <div class="section">
                                                                                                <div class="error">
	                    	<s:actionerror/>
	                    	</div>
	                    	</div>
                            <!-- -------------- /section -------------- -->
                            <div class="section">
                                
                                <!-- <button type="button" id="btnLogin" class="btn btn-bordered btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Log in</button> -->
                                <!-- <button type="button" id="btnLogin" class="btn btn-bordered btn-primary">Log in</button> -->
    			                <s:submit value="Log in" name="button.label.login" cssClass="btn btn-bordered btn-primary"/>
                                
                            </div>
                            <!-- -------------- /section -------------- -->

                            <div class="section sec4">
                                
                                <div class="bs-component pull-left pt5">
                                    <div class="radio-custom radio-primary mb5 lh25">
                                        <input type="radio" id="remember" name="remember">
                                        <label for="remember">Remember me</label>
                                    </div>
                                </div>
                                
                                <div class="bs-component pull-right pt5">
                                    
                                        <a href="forgotPassword.action" class="forgot_pass">Forgot password?</a>
                                    
                                </div>
                                
                                
                                
                            </div>
                            <!-- -------------- /section -------------- -->
                            
                            <div class="section sec4">
                            <div class="bs-component pull-right pt5">
                            </div>
                            <div class="bs-component pull-right pt5">
                            <a href="signup.action" class="forgot_pass">Signup for an account !! </a>
                            </div>
                            </div>
 <!-- -------------- /section -------------- -->
                        </div>
                        <!-- -------------- /Form -------------- -->
                    </s:form>
                </div>
                <!-- -------------- /Panel -------------- -->
            </div>
            <!-- -------------- /Spec Form -------------- -->

        </section>
        <!-- -------------- /Content -------------- -->    

    </section>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <!-- --------------- Accordian --------------- -->   
       

            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    
                  </div>
                  <div class="modal-body">
                     
                        <div class="panel-group Login_Module" id="accordion">
         
                            <div class="panel panel-default">
                               <div class="panel-heading">
                                  <h4 class="panel-title">
                                     <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                        sign up - module selection
                                     </a>
                                  </h4>
                               </div><!--/.panel-heading -->
                               <div id="collapseOne" class="panel-collapse collapse in">
                                  <div class="panel-body">
                                     <form method="post" action="/" id="form-order">
                                    <div class="panel-body pn">
                
                                        
                                        <div class="col-md-6 ph10 mb5">
                                            <div class="section row">
                                                <h3>modules</h3>
                                            </div>
                                            <div class="section row Mod_Selection">
                                                <div class="w40">
                                                    <img class="img-responsive mw40 ib mr10" title="user"
                                                    src="assets/img/recruitment.png">
                                                </div>
                                                <p>HCMOne</p>
                                                
                                                <div class="allcp-form">
                                                    <div class="checkbox-custom checkbox-primary mb5">
                                                        <input type="checkbox" checked="" id="checkboxExample4">
                                                        <label for="checkboxExample4"></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="section row Mod_Selection">
                                                <div class="w40">
                                                    <img class="img-responsive mw40 ib mr10" title="user"
                                                    src="assets/img/recruitment.png">
                                                </div>
                                                <p>Recruitment</p>
                                                <div class="allcp-form">
                                                    <div class="checkbox-custom checkbox-primary mb5">
                                                        <input type="checkbox" checked="" id="checkboxExample">
                                                        <label for="checkboxExample"></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        
                                        <!---------------- Section ---------------->
                                        
                                        <div class="col-md-6 ph10 mb5 Mod_price">
                                            <div class="section row">
                                                <h3>price per month</h3>
                                            </div>
                                            <div class="section row">
                                                <div class="col-md-12 ph10 mb5">
                                                    <p>3USD/per login</p>
                                                </div>
                                                <div class="col-md-12 ph10 mb5">
                                                    <p>3USD/per login</p>
                                                </div>
                                                <div class="col-md-12 ph10 mb5">
                                                    <!--<p>Trial period 2 weeks</p>-->
                                                    <p></p>
                                                </div>
                                                
                                                
                                            </div>
                                        </div>
                                        
                                        <!-- -------------- /section -------------- -->
                                        <div class="col-md-12 ph10 mb5 ModBtn">
                                            <button type="button" class="btn btn-bordered btn-primary" data-dismiss="modal">skip</button>
                                            <button type="button" class="btn btn-bordered btn-primary" id="opentarget">next</button>
                                        </div>
                                        <!-- ------------- /section ----------------->
                                    </div>
                                    <!-- -------------- /Panel Body -------------- -->

                                </form>
                                  </div><!--/.panel-body -->
                               </div><!--/.panel-collapse -->
                            </div><!-- /.panel -->
                            
                            
                            <div class="panel panel-default">
                               <div class="panel-heading">
                                  <h4 class="panel-title">
                                     <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                        manage payment
                                     </a>
                                  </h4>
                               </div><!--/.panel-heading -->
                               <div id="collapseTwo" class="panel-collapse collapse">
                                  <div class="panel-body">
                                     <!-- -------------- Order Form -------------- -->
                        <div class="allcp-form theme-primary mw800" id="order" role="tabpanel">
                            <div class="panel">

                                <form method="post" action="/" id="form-order">
                                    <div class="panel-body pn">
                                        
                                        
                                        <h5 class="panel-title UpdateBill">update billing information</h5>
                                        
                                        
                                        <!---------------- Section ---------------->
                                                                                
                                        <div class="section row">
                                            <div class="col-md-12 ph10 mb5">
                                                <label for="accountstatus" class="Bussiness_acc">
                                                    Bussiness Account Status: </label>
                                                <p class="Acc_active">Account Active</p>
                                            </div>
                                        </div>
                                        
                                        <!---------------- Section ---------------->
                                        <div class="section row">
                                            <div class="col-md-6 ph10 mb5">
                                                <label for="firstname" class="field prepend-icon">
                                                    <input type="text" name="firstname" id="firstname" class="gui-input"
                                                           placeholder="First Name on Card">
                                                    <label for="firstname" class="field-icon">
                                                        <i class="fa fa-user"></i>
                                                    </label>
                                                </label>
                                            </div>
                                            <div class="col-md-6 ph10 mb5">
                                                <label for="lastname" class="field prepend-icon">
                                                    <input type="text" name="lastname" id="lastname" class="gui-input"
                                                           placeholder="Last Name on Card">
                                                    <label for="lastname" class="field-icon">
                                                        <i class="fa fa-user"></i>
                                                    </label>
                                                </label>
                                            </div>
                                        </div>
                                        <!-- -------------- /section -------------- -->

                                        <div class="section row">
                                            <div class="col-md-6 ph10 mb5">
                                                <label for="email" class="field prepend-icon">
                                                    <input type="email" name="email" id="email" class="gui-input"
                                                           placeholder="Billing Contact Email">
                                                    <label for="email" class="field-icon">
                                                        <i class="fa fa-envelope"></i>
                                                    </label>
                                                </label>
                                            </div>
                                            <div class="col-md-6 ph10 mb5">
                                                <label for="address" class="field prepend-icon">
                                                    <input type="address" name="address" id="address" class="gui-input"
                                                           placeholder="Billing Address">
                                                    <label for="email" class="field-icon">
                                                        <i class="fa fa-envelope"></i>
                                                    </label>
                                                </label>
                                            </div>
                                        </div>
                                            <!-- -------------- /section -------------- -->

                                        <div class="section row">
                                            <div class="col-md-6 ph10 mb5">
                                                <label for="zip" class="field prepend-icon">
                                                    <input type="text" name="zip" id="zip" class="gui-input"
                                                           placeholder="Zip">
                                                    <label for="zip" class="field-icon">
                                                        <i class="fa fa-list-ol"></i>
                                                    </label>
                                                </label>
                                            </div>
                                            <!-- -------------- /section -------------- -->

                                            <div class="col-md-6 ph10 mb5">
                                                <label for="states" class="field select">
                                                    <select id="states" name="states">
                                                        <option value="">Choose state</option>
                                                        <option value="AL">Alabama</option>
                                                        <option value="AK">Alaska</option>
                                                        <option value="AZ">Arizona</option>
                                                        <option value="AR">Arkansas</option>
                                                        <option value="CA">California</option>
                                                        <option value="CO">Colorado</option>
                                                        <option value="CT">Connecticut</option>
                                                        <option value="DE">Delaware</option>
                                                        <option value="DC">District Of Columbia</option>
                                                        <option value="FL">Florida</option>
                                                        <option value="GA">Georgia</option>
                                                        <option value="HI">Hawaii</option>
                                                        <option value="ID">Idaho</option>
                                                        <option value="IL">Illinois</option>
                                                        <option value="IN">Indiana</option>
                                                        <option value="IA">Iowa</option>
                                                        <option value="KS">Kansas</option>
                                                        <option value="KY">Kentucky</option>
                                                        <option value="LA">Louisiana</option>
                                                        <option value="ME">Maine</option>
                                                        <option value="MD">Maryland</option>
                                                        <option value="MA">Massachusetts</option>
                                                        <option value="MI">Michigan</option>
                                                        <option value="MN">Minnesota</option>
                                                        <option value="MS">Mississippi</option>
                                                        <option value="MO">Missouri</option>
                                                        <option value="MT">Montana</option>
                                                        <option value="NE">Nebraska</option>
                                                        <option value="NV">Nevada</option>
                                                        <option value="NH">New Hampshire</option>
                                                        <option value="NJ">New Jersey</option>
                                                        <option value="NM">New Mexico</option>
                                                        <option value="NY">New York</option>
                                                        <option value="NC">North Carolina</option>
                                                        <option value="ND">North Dakota</option>
                                                        <option value="OH">Ohio</option>
                                                        <option value="OK">Oklahoma</option>
                                                        <option value="OR">Oregon</option>
                                                        <option value="PA">Pennsylvania</option>
                                                        <option value="RI">Rhode Island</option>
                                                        <option value="SC">South Carolina</option>
                                                        <option value="SD">South Dakota</option>
                                                        <option value="TN">Tennessee</option>
                                                        <option value="TX">Texas</option>
                                                        <option value="UT">Utah</option>
                                                        <option value="VT">Vermont</option>
                                                        <option value="VA">Virginia</option>
                                                        <option value="WA">Washington</option>
                                                        <option value="WV">West Virginia</option>
                                                        <option value="WI">Wisconsin</option>
                                                        <option value="WY">Wyoming</option>
                                                    </select>
                                                    <i class="arrow double"></i>
                                                </label>
                                            </div>
                                        </div>
                                        
                                        
                                        <!-- -------------- /section -------------- -->
                                        

                                        <div class="section PayMentOpt">
                                            <div class="field option PayMent">
                                                <label class="field option mb5">
                                                    <input type="radio" name="payment" checked>
                                                    <span class="radio"></span>
                                                    <img src="assets/img/visa.png" class="img-responsive mw40 ib mr10">
                                                </label>
                                            </div>
                                            <div class="field option PayMent">
                                                <label class="field option mb5">
                                                    <input type="radio" name="payment">
                                                    <span class="radio"></span>
                                                    <img src="assets/img/mastercard.png" class="img-responsive mw40 ib mr10">
                                                </label>
                                            </div>
                                            <div class="field option PayMent">
                                                <div class="PayMent_icon">
                                                    
                                                </div>
                                                <label class="field option mb5">
                                                    <input type="radio" name="payment">
                                                    <span class="radio"></span>
                                                    <img src="assets/img/amex.png" class="img-responsive mw40 ib mr10">
                                                </label>
                                            </div>
                                            <div class="field option PayMent">
                                                <label class="field option mb5">
                                                    <input type="radio" name="payment" checked>
                                                    <span class="radio"></span>
                                                    <img src="assets/img/discover.png" class="img-responsive mw40 ib mr10">
                                                </label>
                                            </div>
                                            <div class="field option PayMent">
                                                <label class="field option mb5">
                                                    <input type="radio" name="payment">
                                                    <span class="radio"></span>
                                                    <img src="assets/img/paypal.png" class="img-responsive mw40 ib mr10">
                                                </label>
                                            </div>
                                            <div class="field option PayMent">
                                                <label class="field option mb5">
                                                    <input type="radio" name="payment">
                                                    <span class="radio"></span>
                                                    <img src="assets/img/google.png" class="img-responsive mw40 ib mr10">
                                                </label>
                                            </div>
                                        </div>
                                        
                                        <!-- -------------- /section -------------- -->
                                        

                                        <div class="section row">
                                            <div class="col-md-6 ph10 mb5">
                                                <label for="cardnum" class="field prepend-icon">
                                                    <input type="text" name="cardno" id="cardnum" class="gui-input"
                                                           placeholder="Card number...">
                                                    <label for="cardnum" class="field-icon">
                                                        <i class="fa fa-credit-card"></i>
                                                    </label>
                                                </label>
                                            </div>
                                            <!-- -------------- /section -------------- -->

                                            <div class="col-md-6 ph10 mb5">
                                                <label for="monthpicker2" class="field prepend-icon">
                                                    <input type="text" id="monthpicker2" name="monthpicker2"
                                                           class="gui-input"
                                                           placeholder="Expiration(MM/YYYY)">
                                                    <label class="field-icon">
                                                        <i class="fa fa-calendar"></i>
                                                    </label>
                                                </label>
                                            </div>
                                            <!-- -------------- /section -------------- -->

                                        </div>
                                        
                                        <div class="col-md-12 ph10 mb5 ModBtn">
                                            <button type="button" class="btn btn-bordered btn-primary" data-dismiss="modal">skip</button>
                                            <button type="button" class="btn btn-bordered btn-primary" data-toggle="modal" data-target="#exampleModal1" data-whatever="@mdo">save</button>
                                        </div>
                                        <!-- ------------- /section ----------------->
                                        
                                    </div>
                                    <!-- -------------- /Panel Body -------------- -->

                                </form>
                            </div>
                            <!-- -------------- /Panel -------------- -->
                        </div>
                        <!-- -------------- /Order Form -------------- -->
                                  </div><!--/.panel-body -->
                               </div><!--/.panel-collapse -->
                            </div><!-- /.panel -->
                        </div><!-- /.panel-group -->
                        
                      
                  </div>
                  
                </div>
              </div>
            </div>
            
    <!-- --------------- /Accordian --------------- -->
    
    
    
    
    
    <!-- -------------- /Main Wrapper -------------- -->

</div>
<!-- -------------- /Body Wrap  -------------- -->

<!-- -------------- Scripts -------------- -->

<!-- -------------- jQuery -------------- -->
<script src="assets/js/jquery/jquery-1.11.3.min.js"></script>
<script src="assets/js/jquery/jquery_ui/jquery-ui.min.js"></script>

<%-- <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.js"></script> --%>

<!-- -------------- CanvasBG JS -------------- -->
<script src="assets/js/plugins/canvasbg/canvasbg.js"></script>

<!-- -------------- Theme Scripts -------------- -->
<script src="assets/js/utility/utility.js"></script>
<script src="assets/js/demo/demo.js"></script>
<script src="assets/js/main.js"></script>

<!-- -------------- Page JS -------------- -->
<!--<script type="text/javascript">
    jQuery(document).ready(function () {

        "use strict";

        // Init Theme Core
        Core.init();

        // Init Demo JS
        Demo.init();

        // Init CanvasBG
        CanvasBG.init({
            Loc: {
                x: window.innerWidth / 5,
                y: window.innerHeight / 10
            }
        });

    });
</script>-->

<script type="text/javascript">

$(document).ready(function () {
 var active = true;
 $('#opentarget').click(function(){
     if (active) {
            active = false;
    $('#collapseOne').collapse('hide');
   $('#collapseTwo').collapse('show');
     }
     else
     {
        active = true; 
     }
  });
 
/*  $("#form_login").validate({
	 errorClass: "error",
	   rules: {
	     // no quoting necessary
	     username: "required",
	     password: "required",
	     captchacode: "required"
	   },
	   messages:{
		   username: "username required",
		   password: "password required",
		   captchacode: "captcha required"
	   }
	 }); */
	
});
</script>
<!-- -------------- /Scripts -------------- -->

</body>

</html>
