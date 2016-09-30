<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
 <section id="content" class="animated fadeIn">
                    <div class="row">
                        <div class="col-md-12 mb40 nopadding-left nopadding-right">
                            <div class="row">
                                <div class="col-md-3 nopadding-left nopadding-right">
                                    <div class="col-sm-6 col-md-6 ">
                                        <a href="#">
                                        <img alt="..." src="assets/img/avatars/profile_avatar_big.jpg" class="media-object img-responsive">
                                        </a>
                                    </div>
									
                                    <div class="col-sm-6 col-md-6 profile nopadding-left nopadding-right">
                                        <div class="Profile-Name">JOHN  DAVID</div>
                                        <ul>
                                            <li><i class="fa fa-phone border"></i> 9840256387</li>
                                            <li><i class="fa fa-calendar border"></i> 01-08-2017</li>
                                            <li><i class="fa fa-user border"></i> Male</li>
                                        </ul>
                                    </div>
                                    <div class="row upload-picture">
                                        <div class="col-md-12 mt10 mb40 ">
                                            <button type="button" class="btn  btn-primary  ml10 " data-style="expand-right">
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
                                    <div class="row">
                                        <div class="col-md-12 mb40">
                                            <h2 class="media-heading fs20 mb20">About Us</h2>
                                            <p class="fw400">
                                                Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse
                                                molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros
                                                et accumsan et iusto.  
                                            </p>
                                            <p class="fw400">Typi non habent claritatem insitam; est usus legentis in iis
                                                qui facit eorum claritatem. Investigationes demonstraverunt lectores legere
                                                me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui
                                                sequitur mutationem consuetudium lectorum. 
                                            </p>
										
										
                                        </div>
  
                                    </div>
                                </div>
                                <div class="col-md-9">
								
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
                                                            <div class="col-md-6 row-right"><span id="display-employee.empFirstName"></span>	</div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Last Name :</div>
                                                            <div class="col-md-6 row-right">	<span id="display-employee.empLastName"></span>	</div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Location :</div>
                                                            <div class="col-md-6 row-right">	<span id="display-employee.empCityName"></span>	</div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Email:</div>
                                                            <div class="col-md-6 row-right"><span id="display-employee.empWorkEmail"></span></div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Contact Number:</div>
                                                            <div class="col-md-6 row-right"><span id="display-employee.lastName"></span></div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Work Authorization:</div>
                                                            <div class="col-md-6 row-right"> <span id="display-employee.lastName"></span></div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Gender:</div>
                                                            <div class="col-md-6 row-right"><span id="display-employee.lastName"></span></div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Employee Type:</div>
                                                            <div class="col-md-6 row-right">Full Time</div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Department:</div>
                                                            <div class="col-md-6 row-right">Application Development</div>
                                                        </div>
                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Role:</div>
                                                            <div class="col-md-6 row-right">Application Designer</div>
                                                        </div>
														<div class="first-row">
														<div class="col-md-5 row-left">Rlite Access</div>
														 <div class="col-md-6 row-right">
													   <form class="allcp-form theme-primary">
                                                        <label class="block option option-primary" id="rliteaccess">
                                                        <input type="checkbox" name="checked" value="checked" checked="">
                                                        <span class="checkbox"></span></label>
                                                     
														</form></div>
												
															</div>


                                                        <div class="first-row">
                                                            <div class="col-md-5 row-left">Access Type:</div>
                                                            <div class="col-md-6 row-right">Application Designer</div>
                                                        </div>
														   
														
														
														
                                                    </div>
													
                                                    <div class="showform">
                                                          <form class="allcp-form theme-primary side-popup" id="employee">
                                                             
                                                             <input type="hidden" name="employee.employeeId" id="employee.employeeId" class="gui-input" placeholder="First Name">
                                                             
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    First Name:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="employee.empFirstName" id="employee.empFirstName" class="gui-input" placeholder="First Name">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Last Name:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="employee.empLastName" id="employee.empLastName" class="gui-input" placeholder="Last Name">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Location :</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="employee.empCityName" id="employee.empCityName" class="gui-input" placeholder="Location">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                          
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Email:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="employee.empWorkEmail" id="employee.empWorkEmail" class="gui-input" placeholder="Email Id">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10 ">
                                                                    Contact Number:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="employee.empMobile" id="employee.empMobile" class="gui-input" placeholder="Contact Number">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                             
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Work Authorization:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="employee.empCounName" id="employee.empCounName" class="gui-input" placeholder="Work Authorization">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            
                                                            <!-- -------------- /section -------------- -->
                                                           
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Date of Birth:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="employee.empBirthDate" id="employee.empBirthDate" class="gui-input" placeholder="Contact Number">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10 ">
                                                                    Gender:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select name="employee.empGender" class="empty" id="employee.empGender">
                                                                                    <option value="0">Male</option>
                                                                                    <option value="1">Female</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                
                                                            
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10 ">
                                                                    Employee Type:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="employee.empType" id="employee.empType" class="gui-input" placeholder="Employee Type">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                       
                                                            <!-- -------------- /section -------------- -->
                                                          
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Department:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select name="employee.departmentIdObj.hcmoDepartmentId" class="empty" id="employee.departmentIdObj">
                                                                                    <option value="0">Developement</option>
                                                                                    <option value="1">Design</option>
                                                                                </select>
<%--                                                                                  <sj:autocompleter  
                  name="employee.teamIdObj.hcmoTeamId"
                list="#request.APPL_TEAM_LIST"
                listKey="hcmoTeamId"
                listValue="teamName"
                  selectBox="true"
                  selectBoxIcon="true"
                  cssClass="empty"
                /> --%>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                          
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10 ">
                                                                    Role:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select name="employee.roleObj.hcmoRoleId" class="empty" id="employee.roleObj">
                                                                                    <option value="0">Developer</option>
                                                                                    <option value="1">Designer</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																<div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10 ">
                                                                    Rlite Access:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                              <label class="block mt15 option option-primary" id="rliteaccess">
                                                        <input type="checkbox" checked="" value="checked" name="employee.rLiteAccess" id="employee.rLiteAccess">
                                                        <span class="checkbox"></span> </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
																
																
																
																
															 
                                                           
                                                            <!-- -------------- /section -------------- -->
                                                            
                                                                <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Access Type:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" name="employee.accessType" id="employee.accessType" class="gui-input" placeholder="Access Type">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                        
                                                            <!-- -------------- /section -------------- -->
                                                           
                                                                 
                                                         
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
                                                        Add Project
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
                                                            <div class="col-md-6 row-right">	Department 1</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Bill Payment:</div>
                                                            <div class="col-md-6 row-right">	Billable	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Client Name:</div>
                                                            <div class="col-md-6 row-right">	John	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Project Name :</div>
                                                            <div class="col-md-6 row-right">	Vendor Mangemnt System	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Project Manager :</div>
                                                            <div class="col-md-6 row-right"> Vendor Manager	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Estimated Hours  :</div>
                                                            <div class="col-md-6 row-right">5hrs	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">From Date  :</div>
                                                            <div class="col-md-6 row-right">	12/06/2016	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">End Date:</div>
                                                            <div class="col-md-6 row-right">	14/06/2016	</div>
                                                        </div>
														<div class="first-row mb30">
                                                            <div class="col-md-5 row-left">Description:</div>
                                                            <div class="col-md-6 row-right"> Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat.	</div>
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
                                                                                                            <select name="set-location" name="assProj.departmentIdObj.hcmoDepartmentId" id="set-location" class="empty">
                                                                                                                <option selected="selected" value="0">Department1
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
																						<input type="checkbox" value="YES" name="assProj.isBillable" id="assProj.isBillable">
																							<span class="checkbox"></span>Billable  </label>
 
                                                                                                    </div>
																									  <div class="col-sm-6 nopadding-left ">
																									<label class="block mt15 option option-primary">
																						<input type="checkbox" value="NO" name="inputname">
																							<span class="checkbox"></span>Non Billable  </label>
 
                                                                                                    </div>
                                                                                                	 
                                                                                                    </div>
                                                                                                </div>
																								
																								<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                    Client Name :  </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field select">
                                                                                                            <select name="set-location" id="set-location" class="empty">
                                                                                                                <option selected="selected" value="0">Raj Mohan
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
                                                                                                            <select name="assProj.projectName" id="assProj.projectName" class="empty">
                                                                                                                <option selected="selected" value="0">Vendor Management System
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
                                                                                                            <select name="set-location" id="set-location" class="empty">
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
                                                                                                        <input type="text" id="timepicker1" name="assproj.ProjActivityEstimatedHours" id="assproj.projActivityEstimatedHours" class="gui-input hasDatepicker" placeholder="Estimated Hours">
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
                                                                                                                <input type="text" id="datepicker1" name="assProj.projActivityStartDate" id="assProj.projActivityStartDate" class="gui-input hasDatepicker" placeholder="From Date">                                                                                                                <label class="field-icon">
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
                                                                                                                <input type="text" id="datepicker1" name="assProj.projActivityEndDate" id="assProj.projActivityEndDate" class="gui-input hasDatepicker" placeholder="End Date">
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
                                <textarea placeholder="Terms of service conditions..." name="assProj.description" id="assProj.description" class="gui-textarea"></textarea>
                                            <label class="field-icon" for="store-tos">
                                                <i class="fa fa-align-justify"></i>
                                            </label>
                                        </label>
                                    </div>
                                </div>
							<input type="hidden" id="assProj.empIdObj.employeeId" name="assProj.empIdObj.employeeId" value="8" >
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
														id="editbutton3"></i>  <i id="savebutton3" style="display:none;" onclick="taskSave()" class="fa fa-floppy-o fs26 text-danger" aria-hidden="true"></i>
													 
														</div>
                                                    </div>
													
													<div class="default3">
														
												<div class="first-row">
                                                            <div class="col-md-5 row-left">Department: </div>
                                                            <div class="col-md-6 row-right">	Department 1</div>
                                                        </div>
														
														
												<div class="first-row">
                                                            <div class="col-md-5 row-left">Project Name: </div>
                                                            <div class="col-md-6 row-right">	Vendor mangemnt System</div>
                                                        </div>
											
											<div class="first-row">
                                                            <div class="col-md-5 row-left">Task Name: </div>
                                                            <div class="col-md-6 row-right"> Addition	</div>
                                                        </div>
											<div class="first-row">
                                                            <div class="col-md-5 row-left">Estimated Hours : </div>
                                                            <div class="col-md-6 row-right">	4hrs</div>
                                                        </div>
														<div class="first-row">
                                                            <div class="col-md-5 row-left">From Date  :</div>
                                                            <div class="col-md-6 row-right">	12/06/2016	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">End Date:</div>
                                                            <div class="col-md-6 row-right">	14/06/2016	</div>
                                                        </div>
														<div class="first-row mb20">
                                                            <div class="col-md-5 row-left">Description:</div>
                                                            <div class="col-md-6 row-right"> Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat.	</div>
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
                                                                                                            <select name="proActivity.departmentIdObj.hcmoDepartmentId" id="proActivity.departmentIdObj.hcmoDepartmentId" class="empty">
                                                                                                                <option selected="selected" value="0">Department1
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
                                                                                                Project  Name :  </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                        <label class="field select">
                                                                                                            <select name="proActivity.proObj.projectId" id="proActivity.proObj.projectId" class="empty">
                                                                                                                <option selected="selected" value="0">Vendor Manager  </option>
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
                                                                                                            <select name="proActivity.activityName" id="proActivity.activityName" class="empty">
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
                                                                                                        <input type="text" id="proActivity.estimatedHours" name="proActivity.estimatedHours" class="gui-input hasDatepicker" placeholder="Start Time">
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
                                                                                                                <input type="text" id="proActivity.taskStartDate" name="proActivity.taskStartDate" class="gui-input hasDatepicker" placeholder="From Date">
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
                                                                                                                <input type="text" id="proActivity.taskEndDate" name="proActivity.taskEndDate" class="gui-input hasDatepicker" placeholder="End Date">
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
                        <textarea placeholder="Terms of service conditions..." name="proActivity.description" id="proActivity.description" class="gui-textarea"></textarea>
                                            <label class="field-icon" for="store-tos">
                                                <i class="fa fa-align-justify"></i>
                                            </label>
                                        </label>
                                    </div>
                                </div>
																								
																								
									<input type="hidden" id="proActivity.empIdObj.employeeId" name="proActivity.empIdObj.employeeId" value="8" >
									
									
									
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
														id="editbutton7"></i>  <i id="savebutton7" style="display:none;" onclick="saveapprover()" class="fa fa-floppy-o fs26 text-danger" aria-hidden="true"></i>
													 
														</div>
                                                    </div>
													<input type="hidden" id="timesheetAppId" name="timeSheetApprover.hcmoEmployeeId.employeeId" value="8" >
													<input type="hidden" id="expAppId" name="expApprover.hcmoEmployeeId.employeeId" value="8" >
													 <div class="default7">
													
													<div class="first-row">
                                                            <div class="col-md-5 row-left">Approver: </div>
                                                            <div class="col-md-6 row-right">	Manager</div>
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
                                                                                                            <select name="approverid" id="approverid" class="empty">
                                                                                                                <option selected="selected" value="0">Mangaer </option>
                                                                                                                <option value="1">Director</option>
                                                                                                                <option value="2"> Test manager</option>
                                                                                                            </select>
                                                                                                            <i class="arrow double"></i>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </div>
																								
														<div class="section row mb5">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                Choose:  </label>
                                                                                                    <div class="col-sm-7 ph7">
                                                                                                         <div class="col-md-6 nopadding-left">
                                                                                                    <div class="section">
                                                                                                        <label class="block mt15 option option-primary">
                                                                                                       <!--  value="CH" -->
                                                                                                        <input type="checkbox" name="inputname" id="timesheet">
                                                                                                        <span class="checkbox"></span>Time sheet</label>
                                                                                                    </div>
																									</div>
																									
																									<div class="col-md-6">
                                                                                                    <div class="section">
                                                                                                        <label class="block mt15 option option-primary">
                                                                                                        <input type="checkbox" name="inputname" id="expenses">
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
														id="editbutton8"></i>  <i id="savebutton8" style="display:none;" onclick="shiftSave()" class="fa fa-floppy-o fs26 text-danger" aria-hidden="true"></i>
													 
														</div>
                                                    </div>
													<div class="default8">
										  
													<div class="first-row">
                                                            <div class="col-md-5 row-left">Shift Timing: </div>
                                                            <div class="col-md-6 row-right"> 9AM-6PM</div>
                                                        </div>	
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">From Date  :</div>
                                                            <div class="col-md-6 row-right">	12/06/2016	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">End Date:</div>
                                                            <div class="col-md-6 row-right">	14/06/2016	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">Start Time  :</div>
                                                            <div class="col-md-6 row-right">	12/06/2016	</div>
                                                        </div>
														
														<div class="first-row">
                                                            <div class="col-md-5 row-left">End Time:</div>
                                                            <div class="col-md-6 row-right">	14/06/2016	</div>
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
                                                                                    <select name="shift.shiftType" class="shift.shiftType">
                                                                                        <option value="0">9AM To 6PM</option>
                                                                                        <option value="1">10AM TO 7PM</option>
                                                                                    </select>
                                                                                    <i class="arrow double"></i>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                   
														  </div>
														  
														  <div class="section row mb5">
                                                                        <label class="field-label col-sm-4 ph10">
                                                                       From Date:</label>
                                                                        <div class="col-sm-7 ph7">
                                                                            <div class="section">
                                                                                   <label class="field prepend-icon" for="datepicker1">
                                                                                  <input type="text" placeholder="From Date" name="shift.startDate" id="shift.startDate" class="gui-input hasDatepicker" name="datepicker1" id="datepicker1">
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
                                                                                  <input type="text" placeholder="End Date" class="gui-input hasDatepicker" name="shift.endDate" id="shift.endDate">
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
                                                                                                        <input type="text" placeholder="Start Time" class="gui-input hasDatepicker" name="shift.startTime" id="shift.startTime">
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
                                                                                                        <input type="text" placeholder="End Time" class="gui-input hasDatepicker" name="shift.endTime" id="shift.endTime">
                                                                                                        <label class="field-icon">
                                                                                                        <i class="fa fa-clock-o"></i>
                                                                                                        </label>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <input type="hidden" id="shift.employee.employeeId" name="shift.employee.employeeId" value="8" >
														  

 
</div>			
</form>									
													 
														
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
															<i id="savebutton1" style="display:none;" class="fa fa-floppy-o fs26 text-danger pull-right"
															 aria-hidden="true" onclick="eeoSave()"></i>
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
                                                                <div class="col-md-6 row-right">	Chennai	</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Marital  Status:</div>
                                                                <div class="col-md-6 row-right">ramrocky@gmail.com</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Dependents :</div>
                                                                <div class="col-md-6 row-right">958601752</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Dependent  Details:</div>
                                                                <div class="col-md-6 row-right"> United States</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Veteran:</div>
                                                                <div class="col-md-6 row-right">Male</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Military  Status:</div>
                                                                <div class="col-md-6 row-right">Full Time</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Disability :</div>
                                                                <div class="col-md-6 row-right">Yes</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Emergency  Contact Name :</div>
                                                                <div class="col-md-6 row-right">Application Designer</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Emergency  Contact Number:</div>
                                                                <div class="col-md-6 row-right">Application Designer</div>
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
                                                                                    <select class="empty" name="eeo.hcmoEEOId.ethnicRaceId" id="eeo.hcmoEEOId.ethnicRaceId">
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
                                                                                <!--  onChange="document.getElementById('uploader4').value = this.value;" -->
                                                                                <input type="file" class="gui-file" name="eeo.ethnicDocumentIdObj.documentName" id="eeo.ethnicDocumentIdObj.documentName"
                                                                                   onChange="document.getElementById('eeo-uploader4').value = this.value;"
                                                                                    >
                                                                                <input type="text" class="gui-input" id="eeo-uploader4"
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
                                                                                <input type="file" class="gui-file" name="eeo.veteranDocumentIdObj.documentName" id="eeo.veteranDocumentIdObj.documentName"
                                                                                 onChange="document.getElementById('eeo-uploader5').value = this.value;" 
                                                                                   >
                                                                                <input type="text" class="gui-input" id="eeo-uploader5"
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
                                                                                <input type="file" class="gui-file" name="eeo.militaryDocumentIdObj.documentName" id="eeo.militaryDocumentIdObj.documentName"
                                                                                   onChange="document.getElementById('eeo-uploader6').value = this.value;"
                                                                                    >
                                                                                <input type="text" class="gui-input" id="eeo-uploader6"
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
                                                                                <input type="file" class="gui-file" name="eeo.disabilityDocumentIdObj.documentName" id="eeo.disabilityDocumentIdObj.documentName"
                                                                                    onChange="document.getElementById('eeo-uploader7').value = this.value;"
                                                                                    >
                                                                                <input type="text" class="gui-input" id="eeo-uploader7"
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
																<input type="hidden" id="eeo.employee.employeeId" name="eeo.employee.employeeId" value="8" >
                                                            </div>
															
                                                        </div>
														
													</form>	
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
                                                                                <select class="empty" name="edu.eduDegree
">
                                                                                    <option value="0">BE</option>
                                                                                    <option value="1">ME</option>
																					<option value="1">Msc</option>
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
                                                                                    <option value="0">CSE</option>
                                                                                    <option value="1">IT</option>
																					  <option value="1">Electronics</option>
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
                                                                                    <option value="0">2010</option>
                                                                                    <option value="1">2011</option>
																					  <option value="0">2012</option>
                                                                                    <option value="1">2013</option>
																					
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
                                                                                    <option value="0">A</option>
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
                                                                                                                <input type="text" id="datepicker1" name="edu.eduStartDate" class="gui-input hasDatepicker" placeholder="Datepicker Popup">
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
                                                                                                                <input type="text" id="datepicker1" name="edu.eduEndDate" class="gui-input hasDatepicker" placeholder="Datepicker Popup">
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
																
																
																
																 
																		
																
																					       
                                                           <div class="section row mb10">
														   <div class="col-md-6">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Graduate:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
																		  <label class="switch block mt15">
                                                <input type="checkbox" name="edu.gradStatus" id="edu.gradStatus" value=1 checked>
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
																
																
																
											<input type="hidden" id="edu.empIdObj.employeeId" name="edu.empIdObj.employeeId" value="8" >					
																
																
																
																
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
                                                             
                                                                <%-- <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Employer Name:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="workexp.employeerName">
                                                                                    <option value="0">BE</option>
                                                                                    <option value="1">ME</option>
																					<option value="1">Msc</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div> --%>
                                                                 <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Employer Name:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                         <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="Employer Name" class="gui-input" id="workexp.employeerName" name="workexp.employeerName">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                         
                                                            <!-- -------------- /section -------------- -->
                                                             
                                                           <%-- <div class="section row mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    JOb Title:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="workexp.empJobTitle">
                                                                                    <option value="0">CSE</option>
                                                                                    <option value="1">IT</option>
																					  <option value="1">Electronics</option>
                                                                                </select>
                                                                                <i class="arrow double"></i>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div> --%>
                                                                  <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    JOb Title:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                         <div class="section">
                                                                            <label class="field">
                                                                            <input type="text" placeholder="JOb Title" class="gui-input" id="workexp.empJobTitle" name="workexp.empJobTitle">
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
                                                                                                                <input type="text" id="datepicker1" name="workexp.fromDate" class="gui-input hasDatepicker" placeholder="Datepicker Popup">
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
                                                                                                                <input type="text" id="datepicker1" name="workexp.toDate" class="gui-input hasDatepicker" placeholder="Datepicker Popup">
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
											<textarea class="gui-textarea" id="store-tos" name="workexp.comments" placeholder="Terms of service conditions..."></textarea>
                                            <label for="store-tos" class="field-icon">
                                                <i class="fa fa-align-justify"></i>
                                            </label>
                                        </label>
                                    </div>
                                </div>
																
																
																
						<input type="hidden" id="workexp.empIdObj.employeeId" name="workexp.empIdObj.employeeId" value="8" >										
																
																
																
																
																
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
                                                                                                                <input type="text" id="datepicker1" name="datepicker1" class="gui-input hasDatepicker" placeholder="Datepicker Popup">
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
                                                                                                                <input type="text" id="datepicker1" name="datepicker1" class="gui-input hasDatepicker" placeholder="Datepicker Popup">
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
                                                            <div id="edit" class="col-md-7 row-right  nopadding-right"> <i id="editbutton5"  class="fa fa-pencil-square-o fs26 text-primary"></i>  <i  class="fa fa-floppy-o fs26 text-danger" style="display:none;" onclick="paytabsave()" id="savebutton5"></i></div>
                                                        </div>
                                                        <div class="default5">
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Role :</div>
                                                                <div class="col-md-6 row-right">UI Developer	</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Employment Type:</div>
                                                                <div class="col-md-6 row-right">Permanent</div>
                                                            </div>
															<div class="first-row">
                                                                <div class="col-md-5 row-left">Benefits:</div>
                                                                <div class="col-md-6 row-right">Yes</div>
                                                            </div>
															
															
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Payment Type :</div>
                                                                <div class="col-md-6 row-right">Monthly</div>
                                                            </div>
															
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Annual Gross Salary  :</div>
                                                                <div class="col-md-6 row-right">300000</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Monthly net Salary:</div>
                                                                <div class="col-md-6 row-right">28000</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Frequency of the pay:</div>
                                                                <div class="col-md-6 row-right">Monthly once</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Pay Day of the month :</div>
                                                                <div class="col-md-6 row-right">1000</div>
                                                            </div>
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">First Day of Payroll:</div>
                                                                <div class="col-md-6 row-right">500</div>
                                                            </div>
															
                                                            <div class="first-row">
                                                                <div class="col-md-5 row-left">Regular Rate :</div>
                                                                <div class="col-md-6 row-right"> 3000</div>
                                                            </div>
															
															  <div class="first-row">
                                                                <div class="col-md-5 row-left">Overtime Rate :</div>
                                                                <div class="col-md-6 row-right">500</div>
                                                            </div>
															
															
															  <div class="first-row">
                                                                <div class="col-md-5 row-left">Commission %:</div>
                                                                <div class="col-md-6 row-right">12%</div>
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
                                                                                <select class="empty" name="payStub.empType" id="payStub.empType">
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
																		  <!-- value="checked" checked -->
																				<input type="checkbox" name="payStub.isBenefit" id ="payStub.isBenefit" >
																				<span class="checkbox"></span>Yes</label> 
																		</div>	
																	</div>	
																		
																	<div class="col-sm-4 ph7">
                                                                       <div class="section">			
																			<label class="option block switch">
                                                        <input type="checkbox" name="inputname" value="CH">
                                                        <span class="checkbox"></span>No </label>	
																				
                                                                        </div>
																		
                                                                    </div>
                                                                </div>
																
																
																
																
																
																
                                                                <div class="section mb10">
                                                                    <label class="field-label col-sm-4 ph10">
                                                                  Payment Type:</label>
                                                                    <div class="col-sm-7 ph7">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="payStub.paymentType" id="payStub.paymentType">
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
                                                                                <select class="empty" name="payStub.payFreq" id="payStub.payFreq">
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
                                                                            <input type="text" placeholder=" First Day of Payroll" class="gui-input" id="payStub.payrollFirstDate" name="payStub.payrollFirstDate">
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
																
																
																
																
									<input type="hidden" id="payStub.employee.employeeId" name="payStub.employee.employeeId" value="8" >							
																
																
																
																
																
                                                            </div>
                                                        </div>
                                                    </div>
                                                   </form>
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
                                                                            <!-- assets.clientId -->
                                                                            <input type="text" name="" id="assets.clientId"  class="gui-input" placeholder="Id">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->
																 
																  
															    <!-- -------------- /section -------------- -->	
																 
																 
																
                                                                            <input type="hidden" name="assets.assetIdObj.assetTypeId" id="assets.assetIdObj.assetTypeId" value=1 class="gui-input" placeholder="Id">
                                                                
															     <!-- -------------- /section -------------- -->	 
															 
																 
																 
													  <!-- -------------- /section -------------- -->
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                   Date Issued :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="datepicker1" name="assets.issuedDate" id="assets.issuedDate"
																												class="gui-input hasDatepicker" placeholder="Date issued">
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
                                                                                                                <input type="text" id="datepicker1" name="assets.releasedDate" id="assets.releasedDate"
																												class="gui-input hasDatepicker" placeholder="Date Released ">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
																		<!--  <input type="hidden" name="assets.clientId" id="assets.clientId" value=10 class="gui-input" placeholder="Id"> -->
																		  <input type="hidden" name="assets.empIdObj.employeeId" id="assets.empIdObj.employeeId" value=5 class="gui-input" placeholder="Id">
																	 
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
                                                                            <input type="text" name="" id="assets.clientId"  class="gui-input" placeholder="Id">
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                </div>
															     <!-- -------------- /section -------------- -->
																 
																  
															    <!-- -------------- /section -------------- -->	
																 
																 
																
                                                                            <input type="hidden" name="assets.assetTypeId.assetTypeId" id="assets.assetTypeId.assetTypeId" value=2 class="gui-input" placeholder="Id">
                                                                
															     <!-- -------------- /section -------------- -->	 
															 
																 
																 
													  <!-- -------------- /section -------------- -->
																<div class="section row mb10">
                                                                                                    <label class="field-label col-sm-4 ph10 text-left">
                                                                                                   Date Issued :</label>
                                                                                                    <div class="col-sm-8 ph8">
                                                                                                        <div class="allcp-form theme-primary">
                                                                                                            <div class="section">
                                                                                                                <label for="datepicker1" class="field prepend-icon">
                                                                                                                <input type="text" id="datepicker1" name="assets.issuedDate" id="assets.issuedDate"
																												class="gui-input hasDatepicker" placeholder="Date issued">
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
                                                                                                                <input type="text" id="datepicker1" name="assets.releasedDate" id="assets.releasedDate"
																												class="gui-input hasDatepicker" placeholder="Date Released ">
                                                                                                                <label class="field-icon">
                                                                                                                <i class="fa fa-calendar"></i>
                                                                                                                </label>
                                                                                                                </label>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
																		</div>
																		
																 
									<input type="hidden" id="assets.empIdObj.employeeId" name="assets.empIdObj.employeeId" value="8" >							 
																 
						 
 													
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
										
                                                <div class="col-md-2 nopadding-left mt20 profile-save"> <a class="btn btn-primary " data-filter=".panel-system"  href="Onboarding.html">   Save</a>
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
                                                                    Major:</label>
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
                                                                    <label class="field-label col-sm-4 ph10">
                                                                    Year:</label>
                                                                    <div class="col-sm-8 ph8">
                                                                        <div class="section">
                                                                            <label class="field select">
                                                                                <select class="empty" name="Select">
                                                                                    <option value="0">2011</option>
                                                                                    <option value="1">2012</option>
																					  <option value="1">2013</option>
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
                                                                                    <option value="0">A</option>
                                                                                    <option value="1">B</option>
																					  <option value="1">C</option>
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
                                                                                                                <input type="text" id="datepicker1" name="datepicker1" class="gui-input hasDatepicker" placeholder="Datepicker Popup">
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
                                                                                                                <input type="text" id="datepicker1" name="datepicker1" class="gui-input hasDatepicker" placeholder="Datepicker Popup">
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
 <script type="text/javascript">       

$( document ).ready(function() {
	
	var employee = {};
	
	loadEmployee();
	
	function loadEmployee(){
	    $.getJSON('${pageContext.request.contextPath}/employeeJSONAction.action', {
	    	  employeeId : 1
	        }, function(jsonResponse) {
	          
	          employee = jsonResponse.employee;
	          
	          $.each(jsonResponse.employee,function(key,value) {
	              //$("#employee").find("input[name='employee."+key+"']").val(value);
	              $(".emp-profile").find("span[id='display-employee."+key+"']").html(value);
	              //console.log("key : " + key + " || value : " + value);
	          });
	          
	          showHide('savebutton')

	        });
	}

    $("#editbutton").click(function(){
	        $.each(employee,function(key,value) {
	            $("#employee").find("input[name='employee."+key+"']").val(value);
	        });
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
   
	//updating selected employee data
	$('#savebutton').on('click', function(e) {
		var employeeFormData = $( "#employee" ).serialize();
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
		         console.log('ERROR Employee Form : ' + e);
		     }
		 });
		 

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
	
});// document ready
 
 function savedata(){
	 
 }
 
 function saveWorkExp(){
	 var str = $( "#workexpform" ).serialize();
	 $.ajax({

	     type: "POST",

	     url: "http://localhost:8080/hanzolabs_hcm/insertOrUpdateWorkExperience.action",

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
 
 function eduSave(){
	 var str = $( "#eduForm" ).serialize();
	 $.ajax({

	     type: "POST",

	     url: "http://localhost:8080/hanzolabs_hcm/insertOrUpdateEducation.action",

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

	     url: "http://localhost:8080/hanzolabs_hcm/insertOrUpdateEmployeeAsset.action",

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

	     url: "http://localhost:8080/hanzolabs_hcm/insertOrUpdateEmpProjectAssign.action",

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
 
 function taskSave(){
	 var str = $( "#taskForm" ).serialize();
	 $.ajax({

	     type: "POST",

	     url: "http://localhost:8080/hanzolabs_hcm/insertOrUpdateProjectActivity.action",

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

	     url: "http://localhost:8080/hanzolabs_hcm/insertOrUpdateEmployeeShift.action",

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

	     url: "http://localhost:8080/hanzolabs_hcm/insertOrUpdateEmployeeAsset.action",

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

	     url: "http://localhost:8080/hanzolabs_hcm/insertOrUpdateEEO.action",

	     data: str+'&eeo.ethnicDocumentIdObj.documentName='+encodeURIComponent(ethnicDocument)+'&eeo.veteranDocumentIdObj.documentName='+
	     encodeURIComponent(veteranDocument)+'&eeo.militaryDocumentIdObj.documentName='+encodeURIComponent(militaryDocument)+'&eeo.disabilityDocumentIdObj.documentName='+
	     encodeURIComponent(disabilityDocument),

	     success: function(response){

	         console.log(response);

	     },

	     error: function(e){

	         alert('Error: ' + e);

	     }

	 });
 }
 
 
 function saveapprover(){
	 var approverid=$('#approverid').val();
	 var timesheetEmpId=$('#timesheetAppId').val();
	 var expApproverEmpid=$('#expAppId').val();
	 var urlaction;
	 var dataapprover;
	 if ($('#timesheet').is(":checked"))
	 {
		 urlaction="http://localhost:8080/hanzolabs_hcm/insertOrUpdateTimeSheetApprover.action";
		 dataapprover='timeSheetApprover.hcmoApprovingEmpId.employeeId='+approverid+'&timeSheetApprover.hcmoEmployeeId.employeeId='+timesheetEmpId;
	
	 }else{
		 
		 urlaction="http://localhost:8080/hanzolabs_hcm/insertOrUpdateExpApprover.action";
		 dataapprover='expApprover.approvingEmployeeId.employeeId='+approverid+'&expApprover.hcmoEmployeeId.employeeId='+expApproverEmpid;
		 
	 }
	
	 
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
 
 
 function paytabsave(){
	 var str = $( "#paytabform" ).serialize();
	 	 
	 $.ajax({

	     type: "POST",

	     url: "http://localhost:8080/hanzolabs_hcm/insertOrUpdatePayStub.action",

	     data: str,

	     success: function(response){

	         console.log(response);

	     },

	     error: function(e){

	         alert('Error: ' + e);

	     }

	 });
 }
 
    </script>