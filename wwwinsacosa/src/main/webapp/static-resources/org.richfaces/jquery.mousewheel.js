/* Copyright (c) 2010 Brandon Aaron (http://brandonaaron.net)
 * Licensed under the MIT License (LICENSE.txt).
 *
 * Thanks to: http://adomas.org/javascript-mouse-wheel/ for some pointers.
 * Thanks to: Mathias Bank(http://www.mathias-bank.de) for a scope bug fix.
 * Thanks to: Seamus Leahy for adding deltaX and deltaY
 *
 * Version: 3.0.4
 * 
 * Requires: 1.2.2+
 */
(function(C){var A=["DOMMouseScroll","mousewheel"];
C.event.special.mousewheel={setup:function(){if(this.addEventListener){for(var D=A.length;
D;
){this.addEventListener(A[--D],B,false)
}}else{this.onmousewheel=B
}},teardown:function(){if(this.removeEventListener){for(var D=A.length;
D;
){this.removeEventListener(A[--D],B,false)
}}else{this.onmousewheel=null
}}};
C.fn.extend({mousewheel:function(D){return D?this.bind("mousewheel",D):this.trigger("mousewheel")
},unmousewheel:function(D){return this.unbind("mousewheel",D)
}});
function B(I){var G=I||window.event,F=[].slice.call(arguments,1),J=0,H=true,E=0,D=0;
I=C.event.fix(G);
I.type="mousewheel";
if(I.wheelDelta){J=I.wheelDelta/120
}if(I.detail){J=-I.detail/3
}D=J;
if(G.axis!==undefined&&G.axis===G.HORIZONTAL_AXIS){D=0;
E=-1*J
}if(G.wheelDeltaY!==undefined){D=G.wheelDeltaY/120
}if(G.wheelDeltaX!==undefined){E=-1*G.wheelDeltaX/120
}F.unshift(I,J,E,D);
return C.event.handle.apply(this,F)
}})(jQuery);