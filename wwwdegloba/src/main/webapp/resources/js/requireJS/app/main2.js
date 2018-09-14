define(function(require)
{
	var $ = require('jquery'),
		arbor = require('./arbor/arbor'); 
		
	 $(function () {
		 arbor.ParticleSystem(1000, 600, 0.5)
	    });
	
});
