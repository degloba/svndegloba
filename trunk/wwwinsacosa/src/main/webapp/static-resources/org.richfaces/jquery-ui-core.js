/*
 * jQuery UI 1.8.6
 *
 * Copyright 2010, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI
 */
(function(A,C){A.ui=A.ui||{};
if(A.ui.version){return 
}A.extend(A.ui,{version:"1.8.6",keyCode:{ALT:18,BACKSPACE:8,CAPS_LOCK:20,COMMA:188,COMMAND:91,COMMAND_LEFT:91,COMMAND_RIGHT:93,CONTROL:17,DELETE:46,DOWN:40,END:35,ENTER:13,ESCAPE:27,HOME:36,INSERT:45,LEFT:37,MENU:93,NUMPAD_ADD:107,NUMPAD_DECIMAL:110,NUMPAD_DIVIDE:111,NUMPAD_ENTER:108,NUMPAD_MULTIPLY:106,NUMPAD_SUBTRACT:109,PAGE_DOWN:34,PAGE_UP:33,PERIOD:190,RIGHT:39,SHIFT:16,SPACE:32,TAB:9,UP:38,WINDOWS:91}});
A.fn.extend({_focus:A.fn.focus,focus:function(D,E){return typeof D==="number"?this.each(function(){var F=this;
setTimeout(function(){A(F).focus();
if(E){E.call(F)
}},D)
}):this._focus.apply(this,arguments)
},scrollParent:function(){var D;
if((A.browser.msie&&(/(static|relative)/).test(this.css("position")))||(/absolute/).test(this.css("position"))){D=this.parents().filter(function(){return(/(relative|absolute|fixed)/).test(A.curCSS(this,"position",1))&&(/(auto|scroll)/).test(A.curCSS(this,"overflow",1)+A.curCSS(this,"overflow-y",1)+A.curCSS(this,"overflow-x",1))
}).eq(0)
}else{D=this.parents().filter(function(){return(/(auto|scroll)/).test(A.curCSS(this,"overflow",1)+A.curCSS(this,"overflow-y",1)+A.curCSS(this,"overflow-x",1))
}).eq(0)
}return(/fixed/).test(this.css("position"))||!D.length?A(document):D
},zIndex:function(G){if(G!==C){return this.css("zIndex",G)
}if(this.length){var E=A(this[0]),D,F;
while(E.length&&E[0]!==document){D=E.css("position");
if(D==="absolute"||D==="relative"||D==="fixed"){F=parseInt(E.css("zIndex"),10);
if(!isNaN(F)&&F!==0){return F
}}E=E.parent()
}}return 0
},disableSelection:function(){return this.bind((A.support.selectstart?"selectstart":"mousedown")+".ui-disableSelection",function(D){D.preventDefault()
})
},enableSelection:function(){return this.unbind(".ui-disableSelection")
}});
A.each(["Width","Height"],function(F,D){var E=D==="Width"?["Left","Right"]:["Top","Bottom"],G=D.toLowerCase(),I={innerWidth:A.fn.innerWidth,innerHeight:A.fn.innerHeight,outerWidth:A.fn.outerWidth,outerHeight:A.fn.outerHeight};
function H(L,K,J,M){A.each(E,function(){K-=parseFloat(A.curCSS(L,"padding"+this,true))||0;
if(J){K-=parseFloat(A.curCSS(L,"border"+this+"Width",true))||0
}if(M){K-=parseFloat(A.curCSS(L,"margin"+this,true))||0
}});
return K
}A.fn["inner"+D]=function(J){if(J===C){return I["inner"+D].call(this)
}return this.each(function(){A(this).css(G,H(this,J)+"px")
})
};
A.fn["outer"+D]=function(J,K){if(typeof J!=="number"){return I["outer"+D].call(this,J)
}return this.each(function(){A(this).css(G,H(this,J,true,K)+"px")
})
}
});
function B(D){return !A(D).parents().andSelf().filter(function(){return A.curCSS(this,"visibility")==="hidden"||A.expr.filters.hidden(this)
}).length
}A.extend(A.expr[":"],{data:function(F,E,D){return !!A.data(F,D[3])
},focusable:function(F){var I=F.nodeName.toLowerCase(),D=A.attr(F,"tabindex");
if("area"===I){var H=F.parentNode,G=H.name,E;
if(!F.href||!G||H.nodeName.toLowerCase()!=="map"){return false
}E=A("img[usemap=#"+G+"]")[0];
return !!E&&B(E)
}return(/input|select|textarea|button|object/.test(I)?!F.disabled:"a"==I?F.href||!isNaN(D):!isNaN(D))&&B(F)
},tabbable:function(E){var D=A.attr(E,"tabindex");
return(isNaN(D)||D>=0)&&A(E).is(":focusable")
}});
A(function(){var D=document.body,E=D.appendChild(E=document.createElement("div"));
A.extend(E.style,{minHeight:"100px",height:"auto",padding:0,borderWidth:0});
A.support.minHeight=E.offsetHeight===100;
A.support.selectstart="onselectstart" in E;
D.removeChild(E).style.display="none"
});
A.extend(A.ui,{plugin:{add:function(E,F,H){var G=A.ui[E].prototype;
for(var D in H){G.plugins[D]=G.plugins[D]||[];
G.plugins[D].push([F,H[D]])
}},call:function(D,F,E){var H=D.plugins[F];
if(!H||!D.element[0].parentNode){return 
}for(var G=0;
G<H.length;
G++){if(D.options[H[G][0]]){H[G][1].apply(D.element,E)
}}}},contains:function(E,D){return document.compareDocumentPosition?E.compareDocumentPosition(D)&16:E!==D&&E.contains(D)
},hasScroll:function(G,E){if(A(G).css("overflow")==="hidden"){return false
}var D=(E&&E==="left")?"scrollLeft":"scrollTop",F=false;
if(G[D]>0){return true
}G[D]=1;
F=(G[D]>0);
G[D]=0;
return F
},isOverAxis:function(E,D,F){return(E>D)&&(E<(D+F))
},isOver:function(I,E,H,G,D,F){return A.ui.isOverAxis(I,H,D)&&A.ui.isOverAxis(E,G,F)
}})
})(jQuery);
/*
 * jQuery UI Widget 1.8.6
 *
 * Copyright 2010, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Widget
 */
(function(B,D){if(B.cleanData){var C=B.cleanData;
B.cleanData=function(E){for(var F=0,G;
(G=E[F])!=null;
F++){B(G).triggerHandler("remove")
}C(E)
}
}else{var A=B.fn.remove;
B.fn.remove=function(E,F){return this.each(function(){if(!F){if(!E||B.filter(E,[this]).length){B("*",this).add([this]).each(function(){B(this).triggerHandler("remove")
})
}}return A.call(B(this),E,F)
})
}
}B.widget=function(F,H,E){var G=F.split(".")[0],J;
F=F.split(".")[1];
J=G+"-"+F;
if(!E){E=H;
H=B.Widget
}B.expr[":"][J]=function(K){return !!B.data(K,F)
};
B[G]=B[G]||{};
B[G][F]=function(K,L){if(arguments.length){this._createWidget(K,L)
}};
var I=new H();
I.options=B.extend(true,{},I.options);
B[G][F].prototype=B.extend(true,I,{namespace:G,widgetName:F,widgetEventPrefix:B[G][F].prototype.widgetEventPrefix||F,widgetBaseClass:J},E);
B.widget.bridge(F,B[G][F])
};
B.widget.bridge=function(F,E){B.fn[F]=function(I){var G=typeof I==="string",H=Array.prototype.slice.call(arguments,1),J=this;
I=!G&&H.length?B.extend.apply(null,[true,I].concat(H)):I;
if(G&&I.charAt(0)==="_"){return J
}if(G){this.each(function(){var K=B.data(this,F),L=K&&B.isFunction(K[I])?K[I].apply(K,H):K;
if(L!==K&&L!==D){J=L;
return false
}})
}else{this.each(function(){var K=B.data(this,F);
if(K){K.option(I||{})._init()
}else{B.data(this,F,new E(I,this))
}})
}return J
}
};
B.Widget=function(E,F){if(arguments.length){this._createWidget(E,F)
}};
B.Widget.prototype={widgetName:"widget",widgetEventPrefix:"",options:{disabled:false},_createWidget:function(F,G){B.data(G,this.widgetName,this);
this.element=B(G);
this.options=B.extend(true,{},this.options,this._getCreateOptions(),F);
var E=this;
this.element.bind("remove."+this.widgetName,function(){E.destroy()
});
this._create();
this._trigger("create");
this._init()
},_getCreateOptions:function(){return B.metadata&&B.metadata.get(this.element[0])[this.widgetName]
},_create:function(){},_init:function(){},destroy:function(){this.element.unbind("."+this.widgetName).removeData(this.widgetName);
this.widget().unbind("."+this.widgetName).removeAttr("aria-disabled").removeClass(this.widgetBaseClass+"-disabled ui-state-disabled")
},widget:function(){return this.element
},option:function(F,G){var E=F;
if(arguments.length===0){return B.extend({},this.options)
}if(typeof F==="string"){if(G===D){return this.options[F]
}E={};
E[F]=G
}this._setOptions(E);
return this
},_setOptions:function(F){var E=this;
B.each(F,function(G,H){E._setOption(G,H)
});
return this
},_setOption:function(E,F){this.options[E]=F;
if(E==="disabled"){this.widget()[F?"addClass":"removeClass"](this.widgetBaseClass+"-disabled ui-state-disabled").attr("aria-disabled",F)
}return this
},enable:function(){return this._setOption("disabled",false)
},disable:function(){return this._setOption("disabled",true)
},_trigger:function(F,G,H){var J=this.options[F];
G=B.Event(G);
G.type=(F===this.widgetEventPrefix?F:this.widgetEventPrefix+F).toLowerCase();
H=H||{};
if(G.originalEvent){for(var E=B.event.props.length,I;
E;
){I=B.event.props[--E];
G[I]=G.originalEvent[I]
}}this.element.trigger(G,H);
return !(B.isFunction(J)&&J.call(this.element[0],G,H)===false||G.isDefaultPrevented())
}}
})(jQuery);
/*
 * jQuery UI Mouse 1.8.6
 *
 * Copyright 2010, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Mouse
 *
 * Depends:
 *	jquery.ui.widget.js
 */
(function(A,B){A.widget("ui.mouse",{options:{cancel:":input,option",distance:1,delay:0},_mouseInit:function(){var C=this;
this.element.bind("mousedown."+this.widgetName,function(D){return C._mouseDown(D)
}).bind("click."+this.widgetName,function(D){if(C._preventClickEvent){C._preventClickEvent=false;
D.stopImmediatePropagation();
return false
}});
this.started=false
},_mouseDestroy:function(){this.element.unbind("."+this.widgetName)
},_mouseDown:function(E){E.originalEvent=E.originalEvent||{};
if(E.originalEvent.mouseHandled){return 
}(this._mouseStarted&&this._mouseUp(E));
this._mouseDownEvent=E;
var D=this,F=(E.which==1),C=(typeof this.options.cancel=="string"?A(E.target).parents().add(E.target).filter(this.options.cancel).length:false);
if(!F||C||!this._mouseCapture(E)){return true
}this.mouseDelayMet=!this.options.delay;
if(!this.mouseDelayMet){this._mouseDelayTimer=setTimeout(function(){D.mouseDelayMet=true
},this.options.delay)
}if(this._mouseDistanceMet(E)&&this._mouseDelayMet(E)){this._mouseStarted=(this._mouseStart(E)!==false);
if(!this._mouseStarted){E.preventDefault();
return true
}}this._mouseMoveDelegate=function(G){return D._mouseMove(G)
};
this._mouseUpDelegate=function(G){return D._mouseUp(G)
};
A(document).bind("mousemove."+this.widgetName,this._mouseMoveDelegate).bind("mouseup."+this.widgetName,this._mouseUpDelegate);
E.preventDefault();
E.originalEvent.mouseHandled=true;
return true
},_mouseMove:function(C){if(A.browser.msie&&!(document.documentMode>=9)&&!C.button){return this._mouseUp(C)
}if(this._mouseStarted){this._mouseDrag(C);
return C.preventDefault()
}if(this._mouseDistanceMet(C)&&this._mouseDelayMet(C)){this._mouseStarted=(this._mouseStart(this._mouseDownEvent,C)!==false);
(this._mouseStarted?this._mouseDrag(C):this._mouseUp(C))
}return !this._mouseStarted
},_mouseUp:function(C){A(document).unbind("mousemove."+this.widgetName,this._mouseMoveDelegate).unbind("mouseup."+this.widgetName,this._mouseUpDelegate);
if(this._mouseStarted){this._mouseStarted=false;
this._preventClickEvent=(C.target==this._mouseDownEvent.target);
this._mouseStop(C)
}return false
},_mouseDistanceMet:function(C){return(Math.max(Math.abs(this._mouseDownEvent.pageX-C.pageX),Math.abs(this._mouseDownEvent.pageY-C.pageY))>=this.options.distance)
},_mouseDelayMet:function(C){return this.mouseDelayMet
},_mouseStart:function(C){},_mouseDrag:function(C){},_mouseStop:function(C){},_mouseCapture:function(C){return true
}})
})(jQuery);
(function(F,G){F.ui=F.ui||{};
var D=/left|center|right/,E=/top|center|bottom/,A="center",B=F.fn.position,C=F.fn.offset;
F.fn.position=function(I){if(!I||!I.of){return B.apply(this,arguments)
}I=F.extend({},I);
var M=F(I.of),L=M[0],O=(I.collision||"flip").split(" "),N=I.offset?I.offset.split(" "):[0,0],K,H,J;
if(L.nodeType===9){K=M.width();
H=M.height();
J={top:0,left:0}
}else{if(L.setTimeout){K=M.width();
H=M.height();
J={top:M.scrollTop(),left:M.scrollLeft()}
}else{if(L.preventDefault){I.at="left top";
K=H=0;
J={top:I.of.pageY,left:I.of.pageX}
}else{K=M.outerWidth();
H=M.outerHeight();
J=M.offset()
}}}F.each(["my","at"],function(){var P=(I[this]||"").split(" ");
if(P.length===1){P=D.test(P[0])?P.concat([A]):E.test(P[0])?[A].concat(P):[A,A]
}P[0]=D.test(P[0])?P[0]:A;
P[1]=E.test(P[1])?P[1]:A;
I[this]=P
});
if(O.length===1){O[1]=O[0]
}N[0]=parseInt(N[0],10)||0;
if(N.length===1){N[1]=N[0]
}N[1]=parseInt(N[1],10)||0;
if(I.at[0]==="right"){J.left+=K
}else{if(I.at[0]===A){J.left+=K/2
}}if(I.at[1]==="bottom"){J.top+=H
}else{if(I.at[1]===A){J.top+=H/2
}}J.left+=N[0];
J.top+=N[1];
return this.each(function(){var S=F(this),U=S.outerWidth(),R=S.outerHeight(),T=parseInt(F.curCSS(this,"marginLeft",true))||0,Q=parseInt(F.curCSS(this,"marginTop",true))||0,W=U+T+parseInt(F.curCSS(this,"marginRight",true))||0,X=R+Q+parseInt(F.curCSS(this,"marginBottom",true))||0,V=F.extend({},J),P;
if(I.my[0]==="right"){V.left-=U
}else{if(I.my[0]===A){V.left-=U/2
}}if(I.my[1]==="bottom"){V.top-=R
}else{if(I.my[1]===A){V.top-=R/2
}}V.left=parseInt(V.left);
V.top=parseInt(V.top);
P={left:V.left-T,top:V.top-Q};
F.each(["left","top"],function(Z,Y){if(F.ui.position[O[Z]]){F.ui.position[O[Z]][Y](V,{targetWidth:K,targetHeight:H,elemWidth:U,elemHeight:R,collisionPosition:P,collisionWidth:W,collisionHeight:X,offset:N,my:I.my,at:I.at})
}});
if(F.fn.bgiframe){S.bgiframe()
}S.offset(F.extend(V,{using:I.using}))
})
};
F.ui.position={fit:{left:function(H,I){var K=F(window),J=I.collisionPosition.left+I.collisionWidth-K.width()-K.scrollLeft();
H.left=J>0?H.left-J:Math.max(H.left-I.collisionPosition.left,H.left)
},top:function(H,I){var K=F(window),J=I.collisionPosition.top+I.collisionHeight-K.height()-K.scrollTop();
H.top=J>0?H.top-J:Math.max(H.top-I.collisionPosition.top,H.top)
}},flip:{left:function(I,K){if(K.at[0]===A){return 
}var M=F(window),L=K.collisionPosition.left+K.collisionWidth-M.width()-M.scrollLeft(),H=K.my[0]==="left"?-K.elemWidth:K.my[0]==="right"?K.elemWidth:0,J=K.at[0]==="left"?K.targetWidth:-K.targetWidth,N=-2*K.offset[0];
I.left+=K.collisionPosition.left<0?H+J+N:L>0?H+J+N:0
},top:function(I,K){if(K.at[1]===A){return 
}var M=F(window),L=K.collisionPosition.top+K.collisionHeight-M.height()-M.scrollTop(),H=K.my[1]==="top"?-K.elemHeight:K.my[1]==="bottom"?K.elemHeight:0,J=K.at[1]==="top"?K.targetHeight:-K.targetHeight,N=-2*K.offset[1];
I.top+=K.collisionPosition.top<0?H+J+N:L>0?H+J+N:0
}}};
if(!F.offset.setOffset){F.offset.setOffset=function(L,I){if(/static/.test(F.curCSS(L,"position"))){L.style.position="relative"
}var K=F(L),N=K.offset(),H=parseInt(F.curCSS(L,"top",true),10)||0,M=parseInt(F.curCSS(L,"left",true),10)||0,J={top:(I.top-N.top)+H,left:(I.left-N.left)+M};
if("using" in I){I.using.call(L,J)
}else{K.css(J)
}};
F.fn.offset=function(H){var I=this[0];
if(!I||!I.ownerDocument){return null
}if(H){return this.each(function(){F.offset.setOffset(this,H)
})
}return C.call(this)
}
}}(jQuery));