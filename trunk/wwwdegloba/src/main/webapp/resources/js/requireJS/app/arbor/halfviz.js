//
// halfviz.js
//
// instantiates all the helper classes, sets up the particle system + renderer
// and maintains the canvas/editor splitview
//

define(function(require)
{
	var $ = require('jquery'),
		arbor = require('./arbor'); 
		
	 $(function () {
		 arbor.ParticleSystem(1000, 600, 0.5)
	    });
	
});
  
