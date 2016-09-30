	  
	  dojo.event.topic.subscribe("/loadCalendar", function(data, request, widget){
	      alert('inside a topic event. after request');
	      //data : text returned from request(the html)
	      //request: XMLHttpRequest object
	      //widget: widget that published the topic
	      Calendar.setup(
	    		    {
	    		      flat         : "calendar-container", // ID of the parent element
	    		      flatCallback : dateChanged           // our callback function
	    		    }
	    		  );
	  });
