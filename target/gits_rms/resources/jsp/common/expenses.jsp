<section id="content" class="animated fadeIn">
				
			<div class="panel">
		 	<div class="panel-body">
			<div class="allcp-form theme-primary">
			  <div class="panel-heading mb20 ph30">
				<span class="panel-title"> Create Expense</span>
				</div>
				
				<div class="section row">
								<div class="col-md-6">
									<div class="section row mb10">
											<label class="field-label col-sm-4 ph10 text-left">
												Expense Date :  </label>
											<div class="col-sm-8 ph15">
											<label for="datepicker1" class="field prepend-icon">
                                                <input type="text" id="datepicker1" name="datepicker1"
                                                       class="gui-input"
                                                       placeholder="Expense Date">
                                                <label class="field-icon">
                                                    <i class="fa fa-calendar"></i>
                                                </label>
                                            </label>
											</div>
									</div>
						        </div>
								
								
								
								
								<div class="col-md-6">
									<div class="section row mb10">
											<label class="field-label col-sm-4 ph10 text-left">
												Currency :  </label>
											<div class="col-sm-8 ph5">
											<label class="field select">
											<select name="set-location" id="set-location" class="empty">
											<option selected="selected" value="0">USD
											</option>
											<option value="1">USD</option>
											<option value="2"> USD</option>
											</select>
											<i class="arrow double"></i>
											</label>
											</div>
									</div>
						        </div>
								
								
								
								
								
			</div>
			
			  
        <!-- -------------- /first row completed -------------- -->
		
		 <div class="section row">
										<div class="col-md-6">
									<div class="section row mb10">
											<label class="field-label col-sm-4 ph10 text-left">
												Payment Method :  </label>
											<div class="col-sm-8 ph15">
											<label class="field select">
											<select name="set-location" id="set-location" class="empty">
											<option selected="selected" value="0">Cash
											</option>
											<option value="1">Net Banking</option>
											<option value="2"> Credit Card</option>
											</select>
											<i class="arrow double"></i>
											</label>
											</div>
									</div>
						        </div>
		 
							
								<div class="col-md-6">
									<div class="section row mb10">
											<label class="field-label col-sm-4 ph10 text-left">
												Expense Type :  </label>
											<div class="col-sm-8 ph5">
											<label class="field">
                                               <input type="text" placeholder="Expense Type" class="gui-input" id="from" name="from">
                                              </label>
											</div>
									</div>
						        </div>
								
			</div>					
			  <div class="section row">		
								
								<div class="col-md-6">
									<div class="section row mb10">
											<label class="field-label col-sm-4 ph10 text-left">
													Amount:  </label>
											<div class="col-sm-8 ph15">
											<label class="field">
                                               <input type="text" name="from" id="from" class="gui-input" placeholder=" Your Amount">
                                              </label>
											</div>
									</div>
						        </div>
								
								
								<div class="col-md-6">
									<div class="section row mb10">
											<label class="field-label col-sm-4 ph10 text-left">
												Payee :  </label>
											<div class="col-sm-8 ph5">
											<label class="field">
                                               <input type="text" name="from" id="from" class="gui-input" placeholder="Your Payee">
                                              </label>
											</div>
									</div>
						        </div>
								
								
			</div>
			<!-- -------------- /second row completed -------------- -->
				
				<div class="section row">
								<div class="col-md-12">
									<div class="section row mb10">
											<label class="field-label col-sm-2 ph10 text-left">
												Note:  </label>
											<div class="col-sm-10 ph8 nopadding-left">
											 <div class="section">
                                                <label class="field prepend-icon">
                            <textarea placeholder="Expenses Notes" name="comment" id="comment" class="gui-textarea"></textarea>
                                                    <label class="field-icon" for="comment">
                                                        <i class="fa fa-list"></i>
                                                    </label>
                                                </label>
                                            </div>
											</div>
									</div>
						        </div>
								
								
								
								
								 
			 					
							 
   </div>
   
   
   
   		<div class="section row">
								
								<div class="col-md-12">
									<div class="section row mb10">
											<label class="field-label col-sm-2 ph8 text-left">
													Attach Files:  </label>
											<div class="col-sm-10 ph8">
											 <label class="field prepend-icon append-button file">
                                                    <span class="button">Choose File</span>
                                                    <input type="file" class="gui-file" name="file1" id="file1"
                                                           onChange="document.getElementById('uploader1').value = this.value;">
                                                    <input type="text" class="gui-input" id="uploader1"
                                                           placeholder="Select File">
                                                    <label class="field-icon">
                                                        <i class="fa fa-cloud-upload"></i>
                                                    </label>
                                                </label>
											</div>
									</div>
						        </div>
								
								
   
   		<!-- -------------- /second row completed -------------- -->
			<div class="section row">
			
					<div class="section row mb10">
					<div class="col-md-12">
											<label class="field-label col-sm-4 ph10 text-left">
													   </label>
											<div class="col-sm-8 ph8">
											 <button type="button" class="btn ladda-button btn-system progress-button progress-button pull-right mt10 mb20 light " onclick="myfun()" data-style="expand-right">
				<span class="ladda-label"><i class="fa fa-hand-o-right" aria-hidden="true"></i> SUBMIT</span>
				<span class="ladda-spinner"></span><span class="ladda-spinner"></span></button>
											</div>
									</div>
		 
				 </div>
			</div>
			
						
			</div>
			
			
			
		 
			
			 
	 	
			 
			
			
			<div class="section row variation">
			
			
			
			<div class="col-md-3 allcp-form theme-primary">
									 
			
                                            <div class="section">
                                                <label class="field select">
                                                    <select name="country" id="country">
                                                        <option value="">Pending</option>
                                                        <option value="AL">Approved </option>
                                                        <option value="DZ">Cancelled </option>
																								
                                                    </select>
                                                    <i class="arrow"></i>
                                                </label>
												
                                            </div>
             
				
				 
			 </div>
			 <div class="col-md-2 filter">
				<a   class="btn-lg btn-system light "> <i class="fa fa-search" aria-hidden="true"></i> SEARCH</a>
            </div>
			
			<div class="col-md-7">
			<div class="col-md-3 approved">
													Approved :2
													</div>
													<div class="col-md-3 canceled">
													Cancelled :1
													</div>
													<div class="col-md-3 pending">
													Pending :1
													</div>
											  
			</div>
			 
			
			 
				<div class="col-md-12 ">
				 <div class="table-responsive mb30">
                                                            <table class="table allcp-form theme-warning tc-checkbox-1 fs13 theme-primary">
                                                                <thead>
                                                                    <tr class="bg-dark">
																      <th class="">Expense Date</th>
                                                                        <th class="">Expense Type</th>
                                                                        <th class="">Payment Method</th>
                                                                        <th class="">Payee</th>
                                                                        <th class="">Status</th>
                                                                        <th class="">Currency</th>
																		 <th class="">Amount</th>
																		 <th class="text-center">Edit</th>
																		 <th class="text-center">Delete</th>
																		
                                                                        </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
																	  <td class=""> 12/02/2016 </td>
                                                                        <td class=""> Travel </td>
																		<td class="">Cash</td>
																		<td class="">Liberty Travels</td>
                                                                        <td class="pending">Pending</td>
                                                                       	<td class="">USD</td>
																		<td class="">500</td>
																		 <td class="text-center"><i aria-hidden="true" class="fa fa-pencil fs18"></i>
																		</td>
																		<td class="text-center"><i aria-hidden="true" class="fa fa-trash fs18"></i></td>
                                                                    </tr>
																	
																	 <tr>
																	  <td class=""> 12/02/2016 </td>
                                                                        <td class=""> Travel </td>
																		<td class="">Cash</td>
																		<td class="">Liberty Travels</td>
                                                                        <td class="approved">Approved</td>
                                                                       	<td class="">USD</td>
																		<td class="">500</td>
																		 <td class="text-center"><i aria-hidden="true" class="fa fa-pencil fs18"></i>
																		</td>
																		<td class="text-center"><i aria-hidden="true" class="fa fa-trash fs18"></i></td>
                                                                    </tr>
																	
																	
																	 <tr>
																	  <td class=""> 12/02/2016 </td>
                                                                        <td class=""> Travel </td>
																		<td class="">Cash</td>
																		<td class="">Liberty Travels</td>
                                                                        <td class="approved">Approved</td>
                                                                       	<td class="">USD</td>
																		<td class="">500</td>
																		 <td class="text-center"><i aria-hidden="true" class="fa fa-pencil fs18"></i>
																		</td>
																		<td class="text-center"><i aria-hidden="true" class="fa fa-trash fs18"></i></td>
                                                                    </tr>
																	
																	 <tr>
																	  <td class=""> 12/02/2016 </td>
                                                                        <td class=""> Travel </td>
																		<td class="">Cash</td>
																		<td class="">Liberty Travels</td>
                                                                        <td class="canceled">Cancelled</td>
                                                                       	<td class="">USD</td>
																		<td class="">500</td>
																		 <td class="text-center"><i aria-hidden="true" class="fa fa-pencil fs18"></i>
																		</td>
																		<td class="text-center"><i aria-hidden="true" class="fa fa-trash fs18"></i></td>
                                                                    </tr>
														  			
																	
											    </tbody>
												
												
												
												</table>
												</div>
											 
													
 </div>
  </div>	
</div>		
			
	
		</div>
		
 </div>
 
 <!--  create expense completed --> 
 
 
										
 
 
 
 
 
 
 
 
 
 
 
 </div>
			
	 
  </section>
  
<script>
function myfun(){
	var empExpVO={
			"amount":200,
			"note":"test",
			"description":"test desc"
	};
  $.ajax({
    type : "POST",
    url  : "http://localhost:8080/HCM-new/insertOrUpdateEmployeeExpensesDetails.action",
    dataType : 'text/javascript',
    data : empExpVO,
    success : function(result){
     alert(result);
    },
    error : function(xhr, errmsg) {alert("No values found..!!");}
}); 

}
</script>  