jQuery.effects||(function(H,E){H.effects={};
H.each(["backgroundColor","borderBottomColor","borderLeftColor","borderRightColor","borderTopColor","color","outlineColor"],function(M,L){H.fx.step[L]=function(N){if(!N.colorInit){N.start=K(N.elem,L);
N.end=J(N.end);
N.colorInit=true
}N.elem.style[L]="rgb("+Math.max(Math.min(parseInt((N.pos*(N.end[0]-N.start[0]))+N.start[0],10),255),0)+","+Math.max(Math.min(parseInt((N.pos*(N.end[1]-N.start[1]))+N.start[1],10),255),0)+","+Math.max(Math.min(parseInt((N.pos*(N.end[2]-N.start[2]))+N.start[2],10),255),0)+")"
}
});
function J(M){var L;
if(M&&M.constructor==Array&&M.length==3){return M
}if(L=/rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/.exec(M)){return[parseInt(L[1],10),parseInt(L[2],10),parseInt(L[3],10)]
}if(L=/rgb\(\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*\)/.exec(M)){return[parseFloat(L[1])*2.55,parseFloat(L[2])*2.55,parseFloat(L[3])*2.55]
}if(L=/#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/.exec(M)){return[parseInt(L[1],16),parseInt(L[2],16),parseInt(L[3],16)]
}if(L=/#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])/.exec(M)){return[parseInt(L[1]+L[1],16),parseInt(L[2]+L[2],16),parseInt(L[3]+L[3],16)]
}if(L=/rgba\(0, 0, 0, 0\)/.exec(M)){return A.transparent
}return A[H.trim(M).toLowerCase()]
}function K(N,L){var M;
do{M=H.curCSS(N,L);
if(M!=""&&M!="transparent"||H.nodeName(N,"body")){break
}L="backgroundColor"
}while(N=N.parentNode);
return J(M)
}var A={aqua:[0,255,255],azure:[240,255,255],beige:[245,245,220],black:[0,0,0],blue:[0,0,255],brown:[165,42,42],cyan:[0,255,255],darkblue:[0,0,139],darkcyan:[0,139,139],darkgrey:[169,169,169],darkgreen:[0,100,0],darkkhaki:[189,183,107],darkmagenta:[139,0,139],darkolivegreen:[85,107,47],darkorange:[255,140,0],darkorchid:[153,50,204],darkred:[139,0,0],darksalmon:[233,150,122],darkviolet:[148,0,211],fuchsia:[255,0,255],gold:[255,215,0],green:[0,128,0],indigo:[75,0,130],khaki:[240,230,140],lightblue:[173,216,230],lightcyan:[224,255,255],lightgreen:[144,238,144],lightgrey:[211,211,211],lightpink:[255,182,193],lightyellow:[255,255,224],lime:[0,255,0],magenta:[255,0,255],maroon:[128,0,0],navy:[0,0,128],olive:[128,128,0],orange:[255,165,0],pink:[255,192,203],purple:[128,0,128],violet:[128,0,128],red:[255,0,0],silver:[192,192,192],white:[255,255,255],yellow:[255,255,0],transparent:[255,255,255]};
var F=["add","remove","toggle"],C={border:1,borderBottom:1,borderColor:1,borderLeft:1,borderRight:1,borderTop:1,borderWidth:1,margin:1,padding:1};
function G(){var O=document.defaultView?document.defaultView.getComputedStyle(this,null):this.currentStyle,P={},M,N;
if(O&&O.length&&O[0]&&O[O[0]]){var L=O.length;
while(L--){M=O[L];
if(typeof O[M]=="string"){N=M.replace(/\-(\w)/g,function(Q,R){return R.toUpperCase()
});
P[N]=O[M]
}}}else{for(M in O){if(typeof O[M]==="string"){P[M]=O[M]
}}}return P
}function B(M){var L,N;
for(L in M){N=M[L];
if(N==null||H.isFunction(N)||L in C||(/scrollbar/).test(L)||(!(/color/i).test(L)&&isNaN(parseFloat(N)))){delete M[L]
}}return M
}function I(L,N){var O={_:0},M;
for(M in N){if(L[M]!=N[M]){O[M]=N[M]
}}return O
}H.effects.animateClass=function(L,M,O,N){if(H.isFunction(O)){N=O;
O=null
}return this.each(function(){var S=H(this),P=S.attr("style")||" ",T=B(G.call(this)),R,Q=S.attr("className");
H.each(F,function(U,V){if(L[V]){S[V+"Class"](L[V])
}});
R=B(G.call(this));
S.attr("className",Q);
S.animate(I(T,R),M,O,function(){H.each(F,function(U,V){if(L[V]){S[V+"Class"](L[V])
}});
if(typeof S.attr("style")=="object"){S.attr("style").cssText="";
S.attr("style").cssText=P
}else{S.attr("style",P)
}if(N){N.apply(this,arguments)
}})
})
};
H.fn.extend({_addClass:H.fn.addClass,addClass:function(M,L,O,N){return L?H.effects.animateClass.apply(this,[{add:M},L,O,N]):this._addClass(M)
},_removeClass:H.fn.removeClass,removeClass:function(M,L,O,N){return L?H.effects.animateClass.apply(this,[{remove:M},L,O,N]):this._removeClass(M)
},_toggleClass:H.fn.toggleClass,toggleClass:function(N,M,L,P,O){if(typeof M=="boolean"||M===E){if(!L){return this._toggleClass(N,M)
}else{return H.effects.animateClass.apply(this,[(M?{add:N}:{remove:N}),L,P,O])
}}else{return H.effects.animateClass.apply(this,[{toggle:N},M,L,P])
}},switchClass:function(L,N,M,P,O){return H.effects.animateClass.apply(this,[{add:N,remove:L},M,P,O])
}});
H.extend(H.effects,{version:"1.8.5",save:function(M,N){for(var L=0;
L<N.length;
L++){if(N[L]!==null){M.data("ec.storage."+N[L],M[0].style[N[L]])
}}},restore:function(M,N){for(var L=0;
L<N.length;
L++){if(N[L]!==null){M.css(N[L],M.data("ec.storage."+N[L]))
}}},setMode:function(L,M){if(M=="toggle"){M=L.is(":hidden")?"show":"hide"
}return M
},getBaseline:function(M,N){var O,L;
switch(M[0]){case"top":O=0;
break;
case"middle":O=0.5;
break;
case"bottom":O=1;
break;
default:O=M[0]/N.height
}switch(M[1]){case"left":L=0;
break;
case"center":L=0.5;
break;
case"right":L=1;
break;
default:L=M[1]/N.width
}return{x:L,y:O}
},createWrapper:function(L){if(L.parent().is(".ui-effects-wrapper")){return L.parent()
}var M={width:L.outerWidth(true),height:L.outerHeight(true),"float":L.css("float")},N=H("<div></div>").addClass("ui-effects-wrapper").css({fontSize:"100%",background:"transparent",border:"none",margin:0,padding:0});
L.wrap(N);
N=L.parent();
if(L.css("position")=="static"){N.css({position:"relative"});
L.css({position:"relative"})
}else{H.extend(M,{position:L.css("position"),zIndex:L.css("z-index")});
H.each(["top","left","bottom","right"],function(O,P){M[P]=L.css(P);
if(isNaN(parseInt(M[P],10))){M[P]="auto"
}});
L.css({position:"relative",top:0,left:0})
}return N.css(M).show()
},removeWrapper:function(L){if(L.parent().is(".ui-effects-wrapper")){return L.parent().replaceWith(L)
}return L
},setTransition:function(M,O,L,N){N=N||{};
H.each(O,function(Q,P){unit=M.cssUnit(P);
if(unit[0]>0){N[P]=unit[0]*L+unit[1]
}});
return N
}});
function D(M,L,N,O){if(typeof M=="object"){O=L;
N=null;
L=M;
M=L.effect
}if(H.isFunction(L)){O=L;
N=null;
L={}
}if(typeof L=="number"||H.fx.speeds[L]){O=N;
N=L;
L={}
}if(H.isFunction(N)){O=N;
N=null
}L=L||{};
N=N||L.duration;
N=H.fx.off?0:typeof N=="number"?N:H.fx.speeds[N]||H.fx.speeds._default;
O=O||L.complete;
return[M,L,N,O]
}H.fn.extend({effect:function(O,N,Q,R){var M=D.apply(this,arguments),P={options:M[1],duration:M[2],callback:M[3]},L=H.effects[O];
return L&&!H.fx.off?L.call(this,P):this
},_show:H.fn.show,show:function(M){if(!M||typeof M=="number"||H.fx.speeds[M]||!H.effects[M]){return this._show.apply(this,arguments)
}else{var L=D.apply(this,arguments);
L[1].mode="show";
return this.effect.apply(this,L)
}},_hide:H.fn.hide,hide:function(M){if(!M||typeof M=="number"||H.fx.speeds[M]||!H.effects[M]){return this._hide.apply(this,arguments)
}else{var L=D.apply(this,arguments);
L[1].mode="hide";
return this.effect.apply(this,L)
}},__toggle:H.fn.toggle,toggle:function(M){if(!M||typeof M=="number"||H.fx.speeds[M]||!H.effects[M]||typeof M=="boolean"||H.isFunction(M)){return this.__toggle.apply(this,arguments)
}else{var L=D.apply(this,arguments);
L[1].mode="toggle";
return this.effect.apply(this,L)
}},cssUnit:function(L){var M=this.css(L),N=[];
H.each(["em","px","%","pt"],function(O,P){if(M.indexOf(P)>0){N=[parseFloat(M),P]
}});
return N
}});
H.easing.jswing=H.easing.swing;
H.extend(H.easing,{def:"easeOutQuad",swing:function(M,N,L,P,O){return H.easing[H.easing.def](M,N,L,P,O)
},easeInQuad:function(M,N,L,P,O){return P*(N/=O)*N+L
},easeOutQuad:function(M,N,L,P,O){return -P*(N/=O)*(N-2)+L
},easeInOutQuad:function(M,N,L,P,O){if((N/=O/2)<1){return P/2*N*N+L
}return -P/2*((--N)*(N-2)-1)+L
},easeInCubic:function(M,N,L,P,O){return P*(N/=O)*N*N+L
},easeOutCubic:function(M,N,L,P,O){return P*((N=N/O-1)*N*N+1)+L
},easeInOutCubic:function(M,N,L,P,O){if((N/=O/2)<1){return P/2*N*N*N+L
}return P/2*((N-=2)*N*N+2)+L
},easeInQuart:function(M,N,L,P,O){return P*(N/=O)*N*N*N+L
},easeOutQuart:function(M,N,L,P,O){return -P*((N=N/O-1)*N*N*N-1)+L
},easeInOutQuart:function(M,N,L,P,O){if((N/=O/2)<1){return P/2*N*N*N*N+L
}return -P/2*((N-=2)*N*N*N-2)+L
},easeInQuint:function(M,N,L,P,O){return P*(N/=O)*N*N*N*N+L
},easeOutQuint:function(M,N,L,P,O){return P*((N=N/O-1)*N*N*N*N+1)+L
},easeInOutQuint:function(M,N,L,P,O){if((N/=O/2)<1){return P/2*N*N*N*N*N+L
}return P/2*((N-=2)*N*N*N*N+2)+L
},easeInSine:function(M,N,L,P,O){return -P*Math.cos(N/O*(Math.PI/2))+P+L
},easeOutSine:function(M,N,L,P,O){return P*Math.sin(N/O*(Math.PI/2))+L
},easeInOutSine:function(M,N,L,P,O){return -P/2*(Math.cos(Math.PI*N/O)-1)+L
},easeInExpo:function(M,N,L,P,O){return(N==0)?L:P*Math.pow(2,10*(N/O-1))+L
},easeOutExpo:function(M,N,L,P,O){return(N==O)?L+P:P*(-Math.pow(2,-10*N/O)+1)+L
},easeInOutExpo:function(M,N,L,P,O){if(N==0){return L
}if(N==O){return L+P
}if((N/=O/2)<1){return P/2*Math.pow(2,10*(N-1))+L
}return P/2*(-Math.pow(2,-10*--N)+2)+L
},easeInCirc:function(M,N,L,P,O){return -P*(Math.sqrt(1-(N/=O)*N)-1)+L
},easeOutCirc:function(M,N,L,P,O){return P*Math.sqrt(1-(N=N/O-1)*N)+L
},easeInOutCirc:function(M,N,L,P,O){if((N/=O/2)<1){return -P/2*(Math.sqrt(1-N*N)-1)+L
}return P/2*(Math.sqrt(1-(N-=2)*N)+1)+L
},easeInElastic:function(M,O,L,S,R){var P=1.70158;
var Q=0;
var N=S;
if(O==0){return L
}if((O/=R)==1){return L+S
}if(!Q){Q=R*0.3
}if(N<Math.abs(S)){N=S;
var P=Q/4
}else{var P=Q/(2*Math.PI)*Math.asin(S/N)
}return -(N*Math.pow(2,10*(O-=1))*Math.sin((O*R-P)*(2*Math.PI)/Q))+L
},easeOutElastic:function(M,O,L,S,R){var P=1.70158;
var Q=0;
var N=S;
if(O==0){return L
}if((O/=R)==1){return L+S
}if(!Q){Q=R*0.3
}if(N<Math.abs(S)){N=S;
var P=Q/4
}else{var P=Q/(2*Math.PI)*Math.asin(S/N)
}return N*Math.pow(2,-10*O)*Math.sin((O*R-P)*(2*Math.PI)/Q)+S+L
},easeInOutElastic:function(M,O,L,S,R){var P=1.70158;
var Q=0;
var N=S;
if(O==0){return L
}if((O/=R/2)==2){return L+S
}if(!Q){Q=R*(0.3*1.5)
}if(N<Math.abs(S)){N=S;
var P=Q/4
}else{var P=Q/(2*Math.PI)*Math.asin(S/N)
}if(O<1){return -0.5*(N*Math.pow(2,10*(O-=1))*Math.sin((O*R-P)*(2*Math.PI)/Q))+L
}return N*Math.pow(2,-10*(O-=1))*Math.sin((O*R-P)*(2*Math.PI)/Q)*0.5+S+L
},easeInBack:function(M,N,L,Q,P,O){if(O==E){O=1.70158
}return Q*(N/=P)*N*((O+1)*N-O)+L
},easeOutBack:function(M,N,L,Q,P,O){if(O==E){O=1.70158
}return Q*((N=N/P-1)*N*((O+1)*N+O)+1)+L
},easeInOutBack:function(M,N,L,Q,P,O){if(O==E){O=1.70158
}if((N/=P/2)<1){return Q/2*(N*N*(((O*=(1.525))+1)*N-O))+L
}return Q/2*((N-=2)*N*(((O*=(1.525))+1)*N+O)+2)+L
},easeInBounce:function(M,N,L,P,O){return P-H.easing.easeOutBounce(M,O-N,0,P,O)+L
},easeOutBounce:function(M,N,L,P,O){if((N/=O)<(1/2.75)){return P*(7.5625*N*N)+L
}else{if(N<(2/2.75)){return P*(7.5625*(N-=(1.5/2.75))*N+0.75)+L
}else{if(N<(2.5/2.75)){return P*(7.5625*(N-=(2.25/2.75))*N+0.9375)+L
}else{return P*(7.5625*(N-=(2.625/2.75))*N+0.984375)+L
}}}},easeInOutBounce:function(M,N,L,P,O){if(N<O/2){return H.easing.easeInBounce(M,N*2,0,P,O)*0.5+L
}return H.easing.easeOutBounce(M,N*2-O,0,P,O)*0.5+P*0.5+L
}})
})(jQuery);