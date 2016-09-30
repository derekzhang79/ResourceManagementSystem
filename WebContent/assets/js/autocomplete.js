/**
 * 
 */
       $(function() {
    	 
    	    $( "#autocompleteEmployee" ).autocomplete({
    	      source: function( request, response ) {
    	        $.ajax({
    	          url: "searchEmployees.action",
    	          dataType: "jsonp",
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
    	  });