(function(E,D){D.ui=D.ui||{};
var C=function(H){H.stopPropagation();
H.preventDefault()
};
var A=function(H){if(typeof H.onselectstart!="undefined"){E(D.getDomElement(H)).bind("selectstart",C)
}else{E(D.getDomElement(H)).bind("mousedown",C)
}};
var G=function(H){if(typeof H.onselectstart!="undefined"){E(D.getDomElement(H)).unbind("selectstart",C)
}else{E(D.getDomElement(H)).unbind("mousedown",C)
}};
var B={width:-1,height:-1,minWidth:-1,minHeight:-1,modal:true,moveable:true,resizeable:false,autosized:false,left:"auto",top:"auto",zindex:100,shadowDepth:5,shadowOpacity:0.1,attachToBody:true};
D.ui.PopupPanel=function(I,H){F.constructor.call(this,I);
this.markerId=I;
this.attachToDom(this.markerId);
this.options=E.extend(this.options,B,H||{});
this.minWidth=this.getMinimumSize(this.options.minWidth);
this.minHeight=this.getMinimumSize(this.options.minHeight);
this.maxWidth=this.options.maxWidth;
this.maxHeight=this.options.maxHeight;
this.baseZIndex=this.options.zindex;
this.div=E(D.getDomElement(I));
this.cdiv=E(D.getDomElement(I+"_container"));
this.contentDiv=E(D.getDomElement(I+"_content"));
this.shadowDiv=E(D.getDomElement(I+"_shadow"));
this.shadeDiv=E(D.getDomElement(I+"_shade"));
this.scrollerDiv=E(D.getDomElement(I+"_content_scroller"));
E(this.shadowDiv).css("opacity",this.options.shadowOpacity);
this.shadowDepth=parseInt(this.options.shadowDepth);
this.borders=new Array();
this.firstHref=E(D.getDomElement(I+"FirstHref"));
if(this.options.resizeable){this.borders.push(new D.ui.PopupPanel.Border(I+"ResizerN",this,"N-resize",D.ui.PopupPanel.Sizer.N));
this.borders.push(new D.ui.PopupPanel.Border(I+"ResizerE",this,"E-resize",D.ui.PopupPanel.Sizer.E));
this.borders.push(new D.ui.PopupPanel.Border(I+"ResizerS",this,"S-resize",D.ui.PopupPanel.Sizer.S));
this.borders.push(new D.ui.PopupPanel.Border(I+"ResizerW",this,"W-resize",D.ui.PopupPanel.Sizer.W));
this.borders.push(new D.ui.PopupPanel.Border(I+"ResizerNW",this,"NW-resize",D.ui.PopupPanel.Sizer.NW));
this.borders.push(new D.ui.PopupPanel.Border(I+"ResizerNE",this,"NE-resize",D.ui.PopupPanel.Sizer.NE));
this.borders.push(new D.ui.PopupPanel.Border(I+"ResizerSE",this,"SE-resize",D.ui.PopupPanel.Sizer.SE));
this.borders.push(new D.ui.PopupPanel.Border(I+"ResizerSW",this,"SW-resize",D.ui.PopupPanel.Sizer.SW))
}if(this.options.moveable&&D.getDomElement(I+"_header")){this.header=new D.ui.PopupPanel.Border(I+"_header",this,"move",D.ui.PopupPanel.Sizer.Header)
}else{E(D.getDomElement(I+"_header")).css("cursor","default")
}};
D.BaseComponent.extend(D.ui.PopupPanel);
var F=D.ui.PopupPanel.$super;
E.extend(D.ui.PopupPanel.prototype,(function(H){return{name:"PopupPanel",saveInputValues:function(I){if(E.browser.msie){E("input[type=checkbox], input[type=radio]",I).each(function(J){E(this).defaultChecked=E(this).checked
})
}},width:function(){return this.getContentElement()[0].clientWidth
},height:function(){return this.getContentElement()[0].clientHeight
},getLeft:function(){return this.cdiv.css("left")
},getTop:function(){return this.cdiv.css("top")
},getInitialSize:function(){if(this.options.autosized){return 15
}else{return E(D.getDomElement(this.markerId+"_header_content")).height()
}},getContentElement:function(){if(!this._contentElement){this._contentElement=this.cdiv
}return this._contentElement
},getSizeElement:function(){return document.body
},getMinimumSize:function(I){return Math.max(I,2*this.getInitialSize()+2)
},__getParsedOption:function(J,I){var K=parseInt(J[I],10);
if(K<0||isNaN(K)){K=this[I]
}return K
},destroy:function(){this._contentElement=null;
this.firstOutside=null;
this.lastOutside=null;
this.firstHref=null;
this.parent=null;
if(this.header){this.header.destroy();
this.header=null
}for(var I=0;
I<this.borders.length;
I++){this.borders[I].destroy()
}this.borders=null;
if(this.domReattached){this.div.remove()
}this.markerId=null;
this.options=null;
this.div=null;
this.cdiv=null;
this.contentDiv=null;
this.shadowDiv=null;
this.scrollerDiv=null;
this.userOptions=null;
this.eIframe=null;
F.destroy.call(this)
},initIframe:function(){if(this.contentWindow){E(this.contentWindow.document.body).css("margin","0px 0px 0px 0px")
}else{}if("transparent"==E(document.body).css("background-color")){E(this).css("filter","alpha(opacity=0)");
E(this).css("opacity","0")
}},setLeft:function(I){if(!isNaN(I)){this.cdiv.css("left",I+"px")
}},setTop:function(I){if(!isNaN(I)){this.cdiv.css("top",I+"px")
}},show:function(X,T){var J=this.cdiv;
if(!this.shown&&this.invokeEvent("beforeshow",X,null,J)){this.preventFocus();
if(!this.domReattached){this.parent=this.div.parent();
var U;
if(T){U=T.domElementAttachment
}if(!U){U=this.options.domElementAttachment
}var S;
if("parent"==U){S=this.parent
}else{if("form"==U){S=this.findForm(J)[0]||document.body
}else{S=document.body
}}if(S!=this.parent){this.saveInputValues(J);
this.shadeDiv.length&&S.appendChild(this.shadeDiv.get(0));
S.appendChild(this.cdiv.get(0));
this.domReattached=true
}else{this.parent.show()
}}var O=E("form",J);
if(this.options.keepVisualState&&O){for(var Y=0;
Y<O.length;
Y++){var I=this;
E(O[Y]).bind("submit",{popup:I},this.setStateInput)
}}var N={};
this.userOptions={};
E.extend(N,this.options);
if(T){E.extend(N,T);
E.extend(this.userOptions,T)
}this.currentMinHeight=this.getMinimumSize(this.__getParsedOption(N,"minHeight"));
this.currentMinWidth=this.getMinimumSize(this.__getParsedOption(N,"minWidth"));
var K=this.getContentElement();
if(!this.options.autosized){if(N.width&&N.width==-1){N.width=300
}if(N.height&&N.height==-1){N.height=200
}}if(N.width&&N.width!=-1){if(this.currentMinWidth>N.width){N.width=this.currentMinWidth
}if(N.width>this.maxWidth){N.width=this.maxWidth
}E(D.getDomElement(K)).css("width",N.width+(/px/.test(N.width)?"":"px"));
this.shadowDiv.css("width",N.width+(/px/.test(N.width)?"":"px"));
this.scrollerDiv.css("width",N.width+(/px/.test(N.width)?"":"px"))
}if(N.height&&N.height!=-1){if(this.currentMinHeight>N.height){N.height=this.currentMinHeight
}if(N.height>this.maxHeight){N.height=this.maxHeight
}E(D.getDomElement(K)).css("height",N.height+(/px/.test(N.height)?"":"px"));
var W=E(D.getDomElement(this.markerId+"_header"))?E(D.getDomElement(this.markerId+"_header")).innerHeight():0;
this.scrollerDiv.css("height",N.height-W+(/px/.test(N.height)?"":"px"))
}var Q;
if(this.options.overlapEmbedObjects&&!this.iframe){this.iframe=this.markerId+"IFrame";
E('<iframe src="javascript:\'\'" frameborder="0" scrolling="no" id="'+this.iframe+'" class="rf-pp-ifr" style="width:'+this.options.width+"px; height:"+this.options.height+'px;"></iframe>').insertBefore(E(":first-child",this.cdiv)[0]);
Q=E(D.getDomElement(this.iframe));
Q.bind("load",this.initIframe);
this.eIframe=Q
}if(N.left){var Z;
if(N.left!="auto"){Z=parseInt(N.left,10)
}else{var L=this.__calculateWindowWidth();
var P=this.width();
if(L>=P){Z=(L-P)/2
}else{Z=0
}}this.setLeft(Math.round(Z));
E(this.shadowDiv).css("left",this.shadowDepth)
}if(N.top){var V;
if(N.top!="auto"){V=parseInt(N.top,10)
}else{var R=this.__calculateWindowHeight();
var a=this.height();
if(R>=a){V=(R-a)/2
}else{V=0
}}this.setTop(Math.round(V));
E(this.shadowDiv).css("top",this.shadowDepth);
E(this.shadowDiv).css("bottom",-this.shadowDepth)
}this.div.css("visibility","");
this.div.css("display","block");
if(this.options.autosized){this.shadowDiv.css("width",this.cdiv[0].clientWidth)
}var M={};
M.parameters=T||{};
this.shown=true;
this.invokeEvent("show",M,null,J)
}},__calculateWindowHeight:function(){var I=document.documentElement;
return self.innerHeight||(I&&I.clientHeight)||document.body.clientHeight
},__calculateWindowWidth:function(){var I=document.documentElement;
return self.innerWidth||(I&&I.clientWidth)||document.body.clientWidth
},startDrag:function(I){A(document.body)
},firstOnfocus:function(I){var J=E(I.data.popup.firstHref);
if(J){J.focus()
}},processAllFocusElements:function(J,N){var I=-1;
var L;
var K="|a|input|select|button|textarea|";
if(J.focus&&J.nodeType==1&&(L=J.tagName)&&(I=K.indexOf(L.toLowerCase()))!=-1&&K.charAt(I-1)==="|"&&K.charAt(I+L.length)==="|"&&!J.disabled&&J.type!="hidden"){N.call(this,J)
}else{if(J!=this.div){var M=J.firstChild;
while(M){if(!M.style||M.style.display!="none"){this.processAllFocusElements(M,N)
}M=M.nextSibling
}}}},processTabindexes:function(I){if(!this.firstOutside){this.firstOutside=I
}if(!I.prevTabIndex){I.prevTabIndex=I.tabIndex;
I.tabIndex=-1
}if(!I.prevAccessKey){I.prevAccessKey=I.accessKey;
I.accessKey=""
}},restoreTabindexes:function(I){if(I.prevTabIndex!=undefined){if(I.prevTabIndex==0){E(I).removeAttr("tabindex")
}else{I.tabIndex=I.prevTabIndex
}I.prevTabIndex=undefined
}if(I.prevAccessKey!=undefined){if(I.prevAccessKey==""){E(I).removeAttr("accesskey")
}else{I.accessKey=I.prevAccessKey
}I.prevAccessKey=undefined
}},preventFocus:function(){if(this.options.modal){this.processAllFocusElements(document,this.processTabindexes);
var I=this;
if(this.firstOutside){E(D.getDomElement(this.firstOutside)).bind("focus",{popup:I},this.firstOnfocus)
}}},restoreFocus:function(){if(this.options.modal){this.processAllFocusElements(document,this.restoreTabindexes);
if(this.firstOutside){E(D.getDomElement(this.firstOutside)).unbind("focus",this.firstOnfocus);
this.firstOutside=null
}}},endDrag:function(J){for(var I=0;
I<this.borders.length;
I++){this.borders[I].show();
this.borders[I].doPosition()
}G(document.body)
},hide:function(M,L){var K=this.cdiv;
this.restoreFocus();
if(this.shown&&this.invokeEvent("beforehide",M,null,K)){this.currentMinHeight=undefined;
this.currentMinWidth=undefined;
this.div.hide();
if(this.parent){if(this.domReattached){this.saveInputValues(K);
var O=this.div.get(0);
this.shadeDiv.length&&O.appendChild(this.shadeDiv.get(0));
O.appendChild(K.get(0));
this.domReattached=false
}}var N={};
N.parameters=L||{};
var I=E("form",K);
if(this.options.keepVisualState&&I){for(var J=0;
J<I.length;
J++){E(I[J]).unbind("submit",this.setStateInput)
}}this.shown=false;
this.invokeEvent("hide",N,null,K)
}},getStyle:function(J,I){return parseInt(E(D.getDomElement(J)).css(I).replace("px",""),10)
},doResizeOrMove:function(R){var M={};
var Y={};
var Q={};
var L={};
var P={};
var O={};
var S={};
var I;
var X=22;
var a=0;
var K=this.getContentElement();
I=this.getStyle(K,"width");
var U=I;
I+=R.deltaWidth||0;
if(I>=this.currentMinWidth||this.options.autosized){L.width=I+"px";
P.width=I+"px";
O.width=I-a+"px";
S.width=I-a+"px"
}else{L.width=this.currentMinWidth+"px";
P.width=this.currentMinWidth+"px";
O.width=this.currentMinWidth-a+"px";
S.width=this.currentMinWidth-a+"px";
M.vx=U-this.currentMinWidth;
M.x=true
}if(I>this.options.maxWidth){if(R.deltaWidth){L.width=this.currentMaxWidth+"px";
P.width=this.currentMaxWidth+"px";
O.width=this.currentMaxWidth-a+"px";
S.width=this.currentMaxWidth-a+"px";
M.vx=U-this.currentMaxWidth
}M.x=true
}if(M.vx&&R.deltaX){R.deltaX=-M.vx
}var W=E(this.cdiv);
if(R.deltaX&&(M.vx||!M.x)){if(M.vx){R.deltaX=M.vx
}var T=this.getStyle(W,"left");
T+=R.deltaX;
Q.left=T+"px"
}I=this.getStyle(K,"height");
var Z=I;
I+=R.deltaHeight||0;
if(I>=this.currentMinHeight||this.options.autosized){if(R.deltaHeight){L.height=I+"px";
P.height=I+"px";
S.height=I-X+"px"
}}else{if(R.deltaHeight){L.height=this.currentMinHeight+"px";
P.height=this.currentMinHeight+"px";
S.height=this.currentMinHeight-X+"px";
M.vy=Z-this.currentMinHeight
}M.y=true
}if(I>this.options.maxHeight){if(R.deltaHeight){L.height=this.currentMaxHeight+"px";
P.height=this.currentMaxHeight+"px";
S.height=this.currentMaxHeight-X+"px";
M.vy=Z-this.currentMaxHeight
}M.y=true
}if(M.vy&&R.deltaY){R.deltaY=-M.vy
}if(R.deltaY&&(M.vy||!M.y)){if(M.vy){R.deltaY=M.vy
}}if(R.deltaY&&(M.vy||!M.y)){if(M.vy){R.deltaY=M.vy
}var J=this.getStyle(W,"top");
J+=R.deltaY;
Q.top=J+"px"
}K.css(L);
this.scrollerDiv.css(S);
if(this.eIframe){this.eIframe.css(S)
}this.shadowDiv.css(P);
W.css(Q);
this.shadowDiv.css(Y);
E.extend(this.userOptions,Q);
E.extend(this.userOptions,L);
var N=this.width();
var V=this.height();
this.reductionData=null;
if(N<=2*this.getInitialSize()){this.reductionData={};
this.reductionData.w=N
}if(V<=2*this.getInitialSize()){if(!this.reductionData){this.reductionData={}
}this.reductionData.h=V
}if(this.header){this.header.doPosition()
}return M
},setSize:function(L,I){var J=L-this.width();
var K=I-this.height();
var M=new D.ui.PopupPanel.Sizer.Diff(0,0,J,K);
this.doResizeOrMove(M)
},moveTo:function(J,I){this.cdiv.css("top",J);
this.cdiv.css("left",I)
},move:function(J,I){var K=new D.ui.PopupPanel.Sizer.Diff(J,I,0,0);
this.doResizeOrMove(K)
},resize:function(J,I){var K=new D.ui.PopupPanel.Sizer.Diff(0,0,J,I);
this.doResizeOrMove(K)
},findForm:function(I){var J=I;
while(J){if(J[0]&&(!J[0].tagName||J[0].tagName.toLowerCase()!="form")){J=E(J).parent()
}else{break
}}return J
},setStateInput:function(K){var I=K.data.popup;
target=E(I.findForm(K.currentTarget));
var J=document.createElement("input");
J.type="hidden";
J.id=I.markerId+"OpenedState";
J.name=I.markerId+"OpenedState";
J.value=I.shown?"true":"false";
target.append(J);
E.each(I.userOptions,function(L,M){J=document.createElement("input");
J.type="hidden";
J.id=I.markerId+"StateOption_"+L;
J.name=I.markerId+"StateOption_"+L;
J.value=M;
target.append(J)
});
return true
}}
})());
E.extend(D.ui.PopupPanel,{showPopupPanel:function(J,I,H){D.Event.ready(function(){D.$(J).show()
})
},hidePopupPanel:function(J,I,H){D.Event.ready(function(){D.$(J).hide()
})
}})
})(jQuery,window.RichFaces);