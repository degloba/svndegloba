/*
 * jQuery UI Resizable 1.9.2
 * http://jqueryui.com
 *
 * Copyright 2012 jQuery Foundation and other contributors
 * Released under the MIT license.
 * http://jquery.org/license
 *
 * http://api.jqueryui.com/resizable/
 *
 * Depends:
 *	jquery.ui.core.js
 *	jquery.ui.mouse.js
 *	jquery.ui.widget.js
 */
(function(C,D){C.widget("ui.resizable",C.ui.mouse,{version:"1.9.2",widgetEventPrefix:"resize",options:{alsoResize:false,animate:false,animateDuration:"slow",animateEasing:"swing",aspectRatio:false,autoHide:false,containment:false,ghost:false,grid:false,handles:"e,s,se",helper:false,maxHeight:null,maxWidth:null,minHeight:10,minWidth:10,zIndex:1000},_create:function(){var H=this,J=this.options;
this.element.addClass("ui-resizable");
C.extend(this,{_aspectRatio:!!(J.aspectRatio),aspectRatio:J.aspectRatio,originalElement:this.element,_proportionallyResizeElements:[],_helper:J.helper||J.ghost||J.animate?J.helper||"ui-resizable-helper":null});
if(this.element[0].nodeName.match(/canvas|textarea|input|select|button|img/i)){this.element.wrap(C('<div class="ui-wrapper" style="overflow: hidden;"></div>').css({position:this.element.css("position"),width:this.element.outerWidth(),height:this.element.outerHeight(),top:this.element.css("top"),left:this.element.css("left")}));
this.element=this.element.parent().data("resizable",this.element.data("resizable"));
this.elementIsWrapper=true;
this.element.css({marginLeft:this.originalElement.css("marginLeft"),marginTop:this.originalElement.css("marginTop"),marginRight:this.originalElement.css("marginRight"),marginBottom:this.originalElement.css("marginBottom")});
this.originalElement.css({marginLeft:0,marginTop:0,marginRight:0,marginBottom:0});
this.originalResizeStyle=this.originalElement.css("resize");
this.originalElement.css("resize","none");
this._proportionallyResizeElements.push(this.originalElement.css({position:"static",zoom:1,display:"block"}));
this.originalElement.css({margin:this.originalElement.css("margin")});
this._proportionallyResize()
}this.handles=J.handles||(!C(".ui-resizable-handle",this.element).length?"e,s,se":{n:".ui-resizable-n",e:".ui-resizable-e",s:".ui-resizable-s",w:".ui-resizable-w",se:".ui-resizable-se",sw:".ui-resizable-sw",ne:".ui-resizable-ne",nw:".ui-resizable-nw"});
if(this.handles.constructor==String){if(this.handles=="all"){this.handles="n,e,s,w,se,sw,ne,nw"
}var K=this.handles.split(",");
this.handles={};
for(var F=0;
F<K.length;
F++){var I=C.trim(K[F]),E="ui-resizable-"+I;
var G=C('<div class="ui-resizable-handle '+E+'"></div>');
G.css({zIndex:J.zIndex});
if("se"==I){G.addClass("ui-icon ui-icon-gripsmall-diagonal-se")
}this.handles[I]=".ui-resizable-"+I;
this.element.append(G)
}}this._renderAxis=function(P){P=P||this.element;
for(var M in this.handles){if(this.handles[M].constructor==String){this.handles[M]=C(this.handles[M],this.element).show()
}if(this.elementIsWrapper&&this.originalElement[0].nodeName.match(/textarea|input|select|button/i)){var N=C(this.handles[M],this.element),O=0;
O=/sw|ne|nw|se|n|s/.test(M)?N.outerHeight():N.outerWidth();
var L=["padding",/ne|nw|n/.test(M)?"Top":/se|sw|s/.test(M)?"Bottom":/^e$/.test(M)?"Right":"Left"].join("");
P.css(L,O);
this._proportionallyResize()
}if(!C(this.handles[M]).length){continue
}}};
this._renderAxis(this.element);
this._handles=C(".ui-resizable-handle",this.element).disableSelection();
this._handles.mouseover(function(){if(!H.resizing){if(this.className){var L=this.className.match(/ui-resizable-(se|sw|ne|nw|n|e|s|w)/i)
}H.axis=L&&L[1]?L[1]:"se"
}});
if(J.autoHide){this._handles.hide();
C(this.element).addClass("ui-resizable-autohide").mouseenter(function(){if(J.disabled){return 
}C(this).removeClass("ui-resizable-autohide");
H._handles.show()
}).mouseleave(function(){if(J.disabled){return 
}if(!H.resizing){C(this).addClass("ui-resizable-autohide");
H._handles.hide()
}})
}this._mouseInit()
},_destroy:function(){this._mouseDestroy();
var E=function(G){C(G).removeClass("ui-resizable ui-resizable-disabled ui-resizable-resizing").removeData("resizable").removeData("ui-resizable").unbind(".resizable").find(".ui-resizable-handle").remove()
};
if(this.elementIsWrapper){E(this.element);
var F=this.element;
this.originalElement.css({position:F.css("position"),width:F.outerWidth(),height:F.outerHeight(),top:F.css("top"),left:F.css("left")}).insertAfter(F);
F.remove()
}this.originalElement.css("resize",this.originalResizeStyle);
E(this.originalElement);
return this
},_mouseCapture:function(F){var G=false;
for(var E in this.handles){if(C(this.handles[E])[0]==F.target){G=true
}}return !this.options.disabled&&G
},_mouseStart:function(G){var J=this.options,F=this.element.position(),E=this.element;
this.resizing=true;
this.documentScroll={top:C(document).scrollTop(),left:C(document).scrollLeft()};
if(E.is(".ui-draggable")||(/absolute/).test(E.css("position"))){E.css({position:"absolute",top:F.top,left:F.left})
}this._renderProxy();
var K=B(this.helper.css("left")),H=B(this.helper.css("top"));
if(J.containment){K+=C(J.containment).scrollLeft()||0;
H+=C(J.containment).scrollTop()||0
}this.offset=this.helper.offset();
this.position={left:K,top:H};
this.size=this._helper?{width:E.outerWidth(),height:E.outerHeight()}:{width:E.width(),height:E.height()};
this.originalSize=this._helper?{width:E.outerWidth(),height:E.outerHeight()}:{width:E.width(),height:E.height()};
this.originalPosition={left:K,top:H};
this.sizeDiff={width:E.outerWidth()-E.width(),height:E.outerHeight()-E.height()};
this.originalMousePosition={left:G.pageX,top:G.pageY};
this.aspectRatio=(typeof J.aspectRatio=="number")?J.aspectRatio:((this.originalSize.width/this.originalSize.height)||1);
var I=C(".ui-resizable-"+this.axis).css("cursor");
C("body").css("cursor",I=="auto"?this.axis+"-resize":I);
E.addClass("ui-resizable-resizing");
this._propagate("start",G);
return true
},_mouseDrag:function(E){var G=this.helper,F=this.options,L={},K=this,I=this.originalMousePosition,M=this.axis;
var O=(E.pageX-I.left)||0,N=(E.pageY-I.top)||0;
var H=this._change[M];
if(!H){return false
}var J=H.apply(this,[E,O,N]);
this._updateVirtualBoundaries(E.shiftKey);
if(this._aspectRatio||E.shiftKey){J=this._updateRatio(J,E)
}J=this._respectSize(J,E);
this._propagate("resize",E);
G.css({top:this.position.top+"px",left:this.position.left+"px",width:this.size.width+"px",height:this.size.height+"px"});
if(!this._helper&&this._proportionallyResizeElements.length){this._proportionallyResize()
}this._updateCache(J);
this._trigger("resize",E,this.ui());
return false
},_mouseStop:function(H){this.resizing=false;
var I=this.options,L=this;
if(this._helper){var G=this._proportionallyResizeElements,E=G.length&&(/textarea/i).test(G[0].nodeName),F=E&&C.ui.hasScroll(G[0],"left")?0:L.sizeDiff.height,K=E?0:L.sizeDiff.width;
var N={width:(L.helper.width()-K),height:(L.helper.height()-F)},J=(parseInt(L.element.css("left"),10)+(L.position.left-L.originalPosition.left))||null,M=(parseInt(L.element.css("top"),10)+(L.position.top-L.originalPosition.top))||null;
if(!I.animate){this.element.css(C.extend(N,{top:M,left:J}))
}L.helper.height(L.size.height);
L.helper.width(L.size.width);
if(this._helper&&!I.animate){this._proportionallyResize()
}}C("body").css("cursor","auto");
this.element.removeClass("ui-resizable-resizing");
this._propagate("stop",H);
if(this._helper){this.helper.remove()
}return false
},_updateVirtualBoundaries:function(G){var J=this.options,I,H,F,K,E;
E={minWidth:A(J.minWidth)?J.minWidth:0,maxWidth:A(J.maxWidth)?J.maxWidth:Infinity,minHeight:A(J.minHeight)?J.minHeight:0,maxHeight:A(J.maxHeight)?J.maxHeight:Infinity};
if(this._aspectRatio||G){I=E.minHeight*this.aspectRatio;
F=E.minWidth/this.aspectRatio;
H=E.maxHeight*this.aspectRatio;
K=E.maxWidth/this.aspectRatio;
if(I>E.minWidth){E.minWidth=I
}if(F>E.minHeight){E.minHeight=F
}if(H<E.maxWidth){E.maxWidth=H
}if(K<E.maxHeight){E.maxHeight=K
}}this._vBoundaries=E
},_updateCache:function(E){var F=this.options;
this.offset=this.helper.offset();
if(A(E.left)){this.position.left=E.left
}if(A(E.top)){this.position.top=E.top
}if(A(E.height)){this.size.height=E.height
}if(A(E.width)){this.size.width=E.width
}},_updateRatio:function(H,G){var I=this.options,J=this.position,F=this.size,E=this.axis;
if(A(H.height)){H.width=(H.height*this.aspectRatio)
}else{if(A(H.width)){H.height=(H.width/this.aspectRatio)
}}if(E=="sw"){H.left=J.left+(F.width-H.width);
H.top=null
}if(E=="nw"){H.top=J.top+(F.height-H.height);
H.left=J.left+(F.width-H.width)
}return H
},_respectSize:function(L,G){var J=this.helper,I=this._vBoundaries,Q=this._aspectRatio||G.shiftKey,P=this.axis,S=A(L.width)&&I.maxWidth&&(I.maxWidth<L.width),M=A(L.height)&&I.maxHeight&&(I.maxHeight<L.height),H=A(L.width)&&I.minWidth&&(I.minWidth>L.width),R=A(L.height)&&I.minHeight&&(I.minHeight>L.height);
if(H){L.width=I.minWidth
}if(R){L.height=I.minHeight
}if(S){L.width=I.maxWidth
}if(M){L.height=I.maxHeight
}var F=this.originalPosition.left+this.originalSize.width,O=this.position.top+this.size.height;
var K=/sw|nw|w/.test(P),E=/nw|ne|n/.test(P);
if(H&&K){L.left=F-I.minWidth
}if(S&&K){L.left=F-I.maxWidth
}if(R&&E){L.top=O-I.minHeight
}if(M&&E){L.top=O-I.maxHeight
}var N=!L.width&&!L.height;
if(N&&!L.left&&L.top){L.top=null
}else{if(N&&!L.top&&L.left){L.left=null
}}return L
},_proportionallyResize:function(){var J=this.options;
if(!this._proportionallyResizeElements.length){return 
}var G=this.helper||this.element;
for(var F=0;
F<this._proportionallyResizeElements.length;
F++){var H=this._proportionallyResizeElements[F];
if(!this.borderDif){var E=[H.css("borderTopWidth"),H.css("borderRightWidth"),H.css("borderBottomWidth"),H.css("borderLeftWidth")],I=[H.css("paddingTop"),H.css("paddingRight"),H.css("paddingBottom"),H.css("paddingLeft")];
this.borderDif=C.map(E,function(K,M){var L=parseInt(K,10)||0,N=parseInt(I[M],10)||0;
return L+N
})
}H.css({height:(G.height()-this.borderDif[0]-this.borderDif[2])||0,width:(G.width()-this.borderDif[1]-this.borderDif[3])||0})
}},_renderProxy:function(){var E=this.element,H=this.options;
this.elementOffset=E.offset();
if(this._helper){this.helper=this.helper||C('<div style="overflow:hidden;"></div>');
var F=(C.ui.ie6?1:0),G=(C.ui.ie6?2:-1);
this.helper.addClass(this._helper).css({width:this.element.outerWidth()+G,height:this.element.outerHeight()+G,position:"absolute",left:this.elementOffset.left-F+"px",top:this.elementOffset.top-F+"px",zIndex:++H.zIndex});
this.helper.appendTo("body").disableSelection()
}else{this.helper=this.element
}},_change:{e:function(G,F,E){return{width:this.originalSize.width+F}
},w:function(H,F,E){var J=this.options,G=this.originalSize,I=this.originalPosition;
return{left:I.left+F,width:G.width-F}
},n:function(H,F,E){var J=this.options,G=this.originalSize,I=this.originalPosition;
return{top:I.top+E,height:G.height-E}
},s:function(G,F,E){return{height:this.originalSize.height+E}
},se:function(G,F,E){return C.extend(this._change.s.apply(this,arguments),this._change.e.apply(this,[G,F,E]))
},sw:function(G,F,E){return C.extend(this._change.s.apply(this,arguments),this._change.w.apply(this,[G,F,E]))
},ne:function(G,F,E){return C.extend(this._change.n.apply(this,arguments),this._change.e.apply(this,[G,F,E]))
},nw:function(G,F,E){return C.extend(this._change.n.apply(this,arguments),this._change.w.apply(this,[G,F,E]))
}},_propagate:function(F,E){C.ui.plugin.call(this,F,[E,this.ui()]);
(F!="resize"&&this._trigger(F,E,this.ui()))
},plugins:{},ui:function(){return{originalElement:this.originalElement,element:this.element,helper:this.helper,position:this.position,size:this.size,originalSize:this.originalSize,originalPosition:this.originalPosition}
}});
C.ui.plugin.add("resizable","alsoResize",{start:function(F,G){var E=C(this).data("resizable"),I=E.options;
var H=function(J){C(J).each(function(){var K=C(this);
K.data("resizable-alsoresize",{width:parseInt(K.width(),10),height:parseInt(K.height(),10),left:parseInt(K.css("left"),10),top:parseInt(K.css("top"),10)})
})
};
if(typeof (I.alsoResize)=="object"&&!I.alsoResize.parentNode){if(I.alsoResize.length){I.alsoResize=I.alsoResize[0];
H(I.alsoResize)
}else{C.each(I.alsoResize,function(J){H(J)
})
}}else{H(I.alsoResize)
}},resize:function(G,I){var F=C(this).data("resizable"),J=F.options,H=F.originalSize,L=F.originalPosition;
var K={height:(F.size.height-H.height)||0,width:(F.size.width-H.width)||0,top:(F.position.top-L.top)||0,left:(F.position.left-L.left)||0},E=function(M,N){C(M).each(function(){var Q=C(this),R=C(this).data("resizable-alsoresize"),P={},O=N&&N.length?N:Q.parents(I.originalElement[0]).length?["width","height"]:["width","height","top","left"];
C.each(O,function(S,U){var T=(R[U]||0)+(K[U]||0);
if(T&&T>=0){P[U]=T||null
}});
Q.css(P)
})
};
if(typeof (J.alsoResize)=="object"&&!J.alsoResize.nodeType){C.each(J.alsoResize,function(M,N){E(M,N)
})
}else{E(J.alsoResize)
}},stop:function(E,F){C(this).removeData("resizable-alsoresize")
}});
C.ui.plugin.add("resizable","animate",{stop:function(I,O){var M=C(this).data("resizable"),J=M.options;
var H=M._proportionallyResizeElements,E=H.length&&(/textarea/i).test(H[0].nodeName),F=E&&C.ui.hasScroll(H[0],"left")?0:M.sizeDiff.height,L=E?0:M.sizeDiff.width;
var G={width:(M.size.width-L),height:(M.size.height-F)},K=(parseInt(M.element.css("left"),10)+(M.position.left-M.originalPosition.left))||null,N=(parseInt(M.element.css("top"),10)+(M.position.top-M.originalPosition.top))||null;
M.element.animate(C.extend(G,N&&K?{top:N,left:K}:{}),{duration:J.animateDuration,easing:J.animateEasing,step:function(){var P={width:parseInt(M.element.css("width"),10),height:parseInt(M.element.css("height"),10),top:parseInt(M.element.css("top"),10),left:parseInt(M.element.css("left"),10)};
if(H&&H.length){C(H[0]).css({width:P.width,height:P.height})
}M._updateCache(P);
M._propagate("resize",I)
}})
}});
C.ui.plugin.add("resizable","containment",{start:function(F,Q){var O=C(this).data("resizable"),J=O.options,L=O.element;
var G=J.containment,K=(G instanceof C)?G.get(0):(/parent/.test(G))?L.parent().get(0):G;
if(!K){return 
}O.containerElement=C(K);
if(/document/.test(G)||G==document){O.containerOffset={left:0,top:0};
O.containerPosition={left:0,top:0};
O.parentData={element:C(document),left:0,top:0,width:C(document).width(),height:C(document).height()||document.body.parentNode.scrollHeight}
}else{var N=C(K),I=[];
C(["Top","Right","Left","Bottom"]).each(function(T,S){I[T]=B(N.css("padding"+S))
});
O.containerOffset=N.offset();
O.containerPosition=N.position();
O.containerSize={height:(N.innerHeight()-I[3]),width:(N.innerWidth()-I[1])};
var P=O.containerOffset,E=O.containerSize.height,M=O.containerSize.width,H=(C.ui.hasScroll(K,"left")?K.scrollWidth:M),R=(C.ui.hasScroll(K)?K.scrollHeight:E);
O.parentData={element:K,left:P.left,top:P.top,width:H,height:R}
}},resize:function(G,Q){var M=C(this).data("resizable"),I=M.options,F=M.containerSize,P=M.containerOffset,N=M.size,O=M.position,R=M._aspectRatio||G.shiftKey,E={top:0,left:0},H=M.containerElement;
if(H[0]!=document&&(/static/).test(H.css("position"))){E=P
}if(O.left<(M._helper?P.left:0)){M.size.width=M.size.width+(M._helper?(M.position.left-P.left):(M.position.left-E.left));
if(R){M.size.height=M.size.width/M.aspectRatio
}M.position.left=I.helper?P.left:0
}if(O.top<(M._helper?P.top:0)){M.size.height=M.size.height+(M._helper?(M.position.top-P.top):M.position.top);
if(R){M.size.width=M.size.height*M.aspectRatio
}M.position.top=M._helper?P.top:0
}M.offset.left=M.parentData.left+M.position.left;
M.offset.top=M.parentData.top+M.position.top;
var L=Math.abs((M._helper?M.offset.left-E.left:(M.offset.left-E.left))+M.sizeDiff.width),S=Math.abs((M._helper?M.offset.top-E.top:(M.offset.top-P.top))+M.sizeDiff.height);
var K=M.containerElement.get(0)==M.element.parent().get(0),J=/relative|absolute/.test(M.containerElement.css("position"));
if(K&&J){L-=M.parentData.left
}if(L+M.size.width>=M.parentData.width){M.size.width=M.parentData.width-L;
if(R){M.size.height=M.size.width/M.aspectRatio
}}if(S+M.size.height>=M.parentData.height){M.size.height=M.parentData.height-S;
if(R){M.size.width=M.size.height*M.aspectRatio
}}},stop:function(F,N){var K=C(this).data("resizable"),G=K.options,L=K.position,M=K.containerOffset,E=K.containerPosition,H=K.containerElement;
var I=C(K.helper),P=I.offset(),O=I.outerWidth()-K.sizeDiff.width,J=I.outerHeight()-K.sizeDiff.height;
if(K._helper&&!G.animate&&(/relative/).test(H.css("position"))){C(this).css({left:P.left-E.left-M.left,width:O,height:J})
}if(K._helper&&!G.animate&&(/static/).test(H.css("position"))){C(this).css({left:P.left-E.left-M.left,width:O,height:J})
}}});
C.ui.plugin.add("resizable","ghost",{start:function(G,H){var F=C(this).data("resizable"),I=F.options,E=F.size;
F.ghost=F.originalElement.clone();
F.ghost.css({opacity:0.25,display:"block",position:"relative",height:E.height,width:E.width,margin:0,left:0,top:0}).addClass("ui-resizable-ghost").addClass(typeof I.ghost=="string"?I.ghost:"");
F.ghost.appendTo(F.helper)
},resize:function(F,G){var E=C(this).data("resizable"),H=E.options;
if(E.ghost){E.ghost.css({position:"relative",height:E.size.height,width:E.size.width})
}},stop:function(F,G){var E=C(this).data("resizable"),H=E.options;
if(E.ghost&&E.helper){E.helper.get(0).removeChild(E.ghost.get(0))
}}});
C.ui.plugin.add("resizable","grid",{resize:function(E,N){var K=C(this).data("resizable"),H=K.options,L=K.size,I=K.originalSize,J=K.originalPosition,O=K.axis,M=H._aspectRatio||E.shiftKey;
H.grid=typeof H.grid=="number"?[H.grid,H.grid]:H.grid;
var G=Math.round((L.width-I.width)/(H.grid[0]||1))*(H.grid[0]||1),F=Math.round((L.height-I.height)/(H.grid[1]||1))*(H.grid[1]||1);
if(/^(se|s|e)$/.test(O)){K.size.width=I.width+G;
K.size.height=I.height+F
}else{if(/^(ne)$/.test(O)){K.size.width=I.width+G;
K.size.height=I.height+F;
K.position.top=J.top-F
}else{if(/^(sw)$/.test(O)){K.size.width=I.width+G;
K.size.height=I.height+F;
K.position.left=J.left-G
}else{K.size.width=I.width+G;
K.size.height=I.height+F;
K.position.top=J.top-F;
K.position.left=J.left-G
}}}}});
var B=function(E){return parseInt(E,10)||0
};
var A=function(E){return !isNaN(parseInt(E,10))
}
})(jQuery);