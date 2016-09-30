  
  /* Edit and save functionality */
  
  /* Profile */
  
  <script>
 $(document).ready(function(){
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
 
});
</script>

  /* EEO */
  
  
  



  



  /* Edit and save functionality */