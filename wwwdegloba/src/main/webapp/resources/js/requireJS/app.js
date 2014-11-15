requirejs.config({
    //By default load any module IDs from js/lib
    baseUrl: 'resources/js/requireJS/lib',
    //except, if the module ID starts with "app",
    //load it from the js/app directory. paths
    //config is relative to the baseUrl, and
    //never includes a ".js" extension since
    //the paths config could be for a directory.
    paths: {
        app: '../app',
        jquery: 'jquery-2.1.0'
    }
});


// Start the main app logic.
requirejs(['jquery', 'app/magnific-popup/jquery.magnific-popup'],
function   ($ , magnificPopup      ) {
    //jQuery, canvas and the app/sub module are all
    //loaded and can be used here now.
	
	$(document).ready(function(){
		  $("button").click(function(){
		    $("p").hide();
		  });
		  
		  $(".test-popup-link").magnificPopup({type:'image',
			  mainClass: 'mfp-with-zoom', // this class is for CSS animation below
			  
			  zoom: {
				    enabled: true, // By default it's false, so don't forget to enable it

				    duration: 700, // duration of the effect, in milliseconds
				    easing: 'ease-in-out' // CSS transition easing function 
				  }
			  });
		  
		});

	
	// Start loading the main app file. Put all of
	// your application logic in there.
	requirejs(['app/main']);  

});