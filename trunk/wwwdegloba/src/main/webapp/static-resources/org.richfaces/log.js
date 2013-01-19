(function(A,E){var D=["debug","info","warn","error"];
var F={debug:"debug",info:"info ",warn:"warn ",error:"error"};
var C={debug:1,info:2,warn:3,error:4};
var H={__import:function(M,L){if(M===document){return L
}var I=A();
for(var K=0;
K<L.length;
K++){if(M.importNode){I=I.add(M.importNode(L[K],true))
}else{var J=M.createElement("div");
J.innerHTML=L[K].outerHTML;
for(var N=J.firstChild;
N;
N=N.nextSibling){I=I.add(N)
}}}return I
},__getStyles:function(){var J=jQuery("head");
if(J.length==0){return""
}try{var K=J.clone();
if(K.children().length==J.children().length){return K.children(":not(style):not(link[rel='stylesheet'])").remove().end().html()
}else{var I=new Array();
J.children("style, link[rel='stylesheet']").each(function(){I.push(this.outerHTML)
});
return I.join("")
}}catch(L){return""
}},__openPopup:function(){if(!this.__popupWindow||this.__popupWindow.closed){this.__popupWindow=open("","_richfaces_logWindow","height=400, width=600, resizable = yes, status=no, scrollbars = yes, statusbar=no, toolbar=no, menubar=no, location=no");
var I=this.__popupWindow.document;
I.write('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head>'+this.__getStyles()+"</head><body onunload='window.close()'><div id='richfaces.log' clas='rf-log rf-log-popup'></div></body></html>");
I.close();
this.__initializeControls(I)
}else{this.__popupWindow.focus()
}},__hotkeyHandler:function(I){if(I.ctrlKey&&I.shiftKey){if((this.hotkey||"l").toLowerCase()==String.fromCharCode(I.keyCode).toLowerCase()){this.__openPopup()
}}},__getTimeAsString:function(){var I=new Date();
var J=this.__lzpad(I.getHours(),2)+":"+this.__lzpad(I.getMinutes(),2)+":"+this.__lzpad(I.getSeconds(),2)+"."+this.__lzpad(I.getMilliseconds(),3);
return J
},__lzpad:function(K,L){K=K.toString();
var I=new Array();
for(var J=0;
J<L-K.length;
J++){I.push("0")
}I.push(K);
return I.join("")
},__getMessagePrefix:function(I){return F[I]+"["+this.__getTimeAsString()+"]: "
},__setLevelFromSelect:function(I){this.setLevel(I.target.value)
},__initializeControls:function(M){var K=A("#richfaces\\.log",M);
var J=K.children("button.rf-log-element");
if(J.length==0){J=A("<button type='button' class='rf-log-element'>Clear</button>",M).appendTo(K)
}J.click(A.proxy(this.clear,this));
var N=K.children("select.rf-log-element");
if(N.length==0){N=A("<select class='rf-log-element' name='richfaces.log' />",M).appendTo(K)
}if(N.children().length==0){for(var I=0;
I<D.length;
I++){A("<option value='"+D[I]+"'>"+D[I]+"</option>",M).appendTo(N)
}}N.val(this.getLevel());
N.change(A.proxy(this.__setLevelFromSelect,this));
var L=K.children(".rf-log-contents");
if(L.length==0){L=A("<div class='rf-log-contents'></div>",M).appendTo(K)
}this.__contentsElement=L
},__append:function(I){var K=this.__contentsElement;
if(this.mode=="popup"){var J=this.__popupWindow.document;
A(J.createElement("div")).appendTo(K).append(this.__import(J,I))
}else{A(document.createElement("div")).appendTo(K).append(I)
}},__log:function(L,I){if(!this.__contentsElement){return 
}if(C[L]>=C[this.getLevel()]){var J=A();
J=J.add(A("<span class='rf-log-entry-lbl rf-log-entry-lbl-"+L+"'></span>").text(this.__getMessagePrefix(L)));
var K=A("<span class='rf-log-entry-msg rf-log-entry-msg-"+L+"'></span>");
if(typeof I!="object"||!I.appendTo){K.text(I)
}else{I.appendTo(K)
}J=J.add(K);
this.__append(J)
}},init:function(I){G.constructor.call(this,"richfaces.log");
this.attachToDom();
E.setLog(this);
I=I||{};
this.level=I.level;
this.hotkey=I.hotkey;
this.mode=(I.mode||"inline");
if(this.mode=="popup"){this.__boundHotkeyHandler=A.proxy(this.__hotkeyHandler,this);
A(document).bind("keydown",this.__boundHotkeyHandler)
}else{this.__initializeControls(document)
}},destroy:function(){E.setLog(null);
if(this.__popupWindow){this.__popupWindow.close()
}this.__popupWindow=null;
if(this.__boundHotkeyHandler){A(document).unbind("keydown",this.__boundHotkeyHandler);
this.__boundHotkeyHandler=null
}this.__contentsElement=null;
G.destroy.call(this)
},setLevel:function(I){this.level=I;
this.clear()
},getLevel:function(){return this.level||"info"
},clear:function(){if(this.__contentsElement){this.__contentsElement.children().remove()
}}};
for(var B=0;
B<D.length;
B++){H[D[B]]=(function(){var I=D[B];
return function(J){this.__log(I,J)
}
}())
}E.HtmlLog=E.BaseComponent.extendClass(H);
var G=E.HtmlLog.$super;
jQuery(document).ready(function(){if(typeof jsf!="undefined"){(function(N,M,I){var P=function(R){var Q="<"+R.tagName.toLowerCase();
var S=N(R);
if(S.attr("id")){Q+=(" id="+S.attr("id"))
}if(S.attr("class")){Q+=(" class="+S.attr("class"))
}Q+=" ...>";
return Q
};
var L=function(Q,S){var R=N(S);
Q.append("Element <b>"+S.nodeName+"</b>");
if(R.attr("id")){Q.append(document.createTextNode(" for id="+R.attr("id")))
}N(document.createElement("br")).appendTo(Q);
N("<span class='rf-log-entry-msg-xml'></span>").appendTo(Q).text(R.toXML());
N(document.createElement("br")).appendTo(Q)
};
var O=function(Q){var R=N(document.createElement("span"));
Q.children().each(function(){var S=N(this);
if(S.is("changes")){R.append("Listing content of response <b>changes</b> element:<br />");
S.children().each(function(){L(R,this)
})
}else{L(R,this)
}});
return R
};
var K=function(U){try{var S=M.log;
var Q=U.source;
var X=U.type;
var Z=U.responseCode;
var Y=U.responseXML;
var W=U.responseText;
if(X!="error"){S.info("Received '"+X+"' event from "+P(Q));
if(X=="beforedomupdate"){var T;
if(Y){T=N(Y).children("partial-response")
}var a=N("<span>Server returned responseText: </span><span class='rf-log-entry-msg-xml'></span>").eq(1).text(W).end();
if(T&&T.length){S.debug(a);
S.info(O(T))
}else{S.info(a)
}}}else{var R=U.status;
S.error("Received '"+X+"@"+R+"' event from "+P(Q));
S.error("["+U.responseCode+"] "+U.errorName+": "+U.errorMessage)
}}catch(V){}};
var J=M.createJSFEventsAdapter({begin:K,beforedomupdate:K,success:K,complete:K,error:K});
I.ajax.addOnEvent(J);
I.ajax.addOnError(J)
}(jQuery,RichFaces,jsf))
}})
}(jQuery,RichFaces));