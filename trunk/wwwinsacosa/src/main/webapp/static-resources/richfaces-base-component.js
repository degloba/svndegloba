(function(C,B,D){B.blankFunction=function(){};
B.BaseComponent=function(F){this.id=F;
this.options=this.options||{}
};
var A={};
var E=function(H,L,G){G=G||{};
var J=B.blankFunction;
J.prototype=H.prototype;
L.prototype=new J();
L.prototype.constructor=L;
L.$super=H.prototype;
if(L.$super==B.BaseComponent.prototype){var I=jQuery.extend({},A,G||{})
}var K=L;
L.extend=function(F,M){M=M||{};
var N=jQuery.extend({},I||G||{},M||{});
return E(K,F,N)
};
return I||G
};
B.BaseComponent.extend=function(G,F){return E(B.BaseComponent,G,F)
};
B.BaseComponent.extendClass=function(G){var F=G.init||B.blankFunction;
var H=this;
H.extend(F);
F.extendClass=H.extendClass;
C.extend(F.prototype,G);
return F
};
C.extend(B.BaseComponent.prototype,(function(F){return{name:"BaseComponent",toString:function(){var G=[];
if(this.constructor.$super){G[G.length]=this.constructor.$super.toString()
}G[G.length]=this.name;
return G.join(", ")
},getValue:function(){return 
},getEventElement:function(){return this.id
},attachToDom:function(I){I=I||this.id;
var H=B.getDomElement(I);
if(H){var G=H[B.RICH_CONTAINER]=H[B.RICH_CONTAINER]||{};
G.component=this
}return H
},detach:function(H){H=H||this.id;
var G=B.getDomElement(H);
G&&G[B.RICH_CONTAINER]&&(G[B.RICH_CONTAINER].component=null)
},invokeEvent:function(J,I,L,N){var K,G;
var M=C.extend({},L,{type:J});
if(!M){if(document.createEventObject){M=document.createEventObject();
M.type=J
}else{if(document.createEvent){M=document.createEvent("Events");
M.initEvent(J,true,false)
}}}M[B.RICH_CONTAINER]={component:this,data:N};
var H=this.options["on"+J];
if(typeof H=="function"){K=H.call(I,M)
}if(B.Event){G=B.Event.callHandler(this,J,N)
}if(G!=false&&K!=false){G=true
}return G
},destroy:function(){}}
})(D));
B.BaseNonVisualComponent=function(F){this.id=F;
this.options=this.options||{}
};
B.BaseNonVisualComponent.extend=function(G,F){return E(B.BaseNonVisualComponent,G,F)
};
B.BaseNonVisualComponent.extendClass=function(G){var F=G.init||B.blankFunction;
var H=this;
H.extend(F);
F.extendClass=H.extendClass;
C.extend(F.prototype,G);
return F
};
C.extend(B.BaseNonVisualComponent.prototype,(function(F){return{name:"BaseNonVisualComponent",toString:function(){var G=[];
if(this.constructor.$super){G[G.length]=this.constructor.$super.toString()
}G[G.length]=this.name;
return G.join(", ")
},getValue:function(){return 
},attachToDom:function(I){I=I||this.id;
var H=B.getDomElement(I);
if(H){var G=H[B.RICH_CONTAINER]=H[B.RICH_CONTAINER]||{};
if(G.attachedComponents){G.attachedComponents[this.name]=this
}else{G.attachedComponents={};
G.attachedComponents[this.name]=this
}}return H
},detach:function(H){H=H||this.id;
var G=B.getDomElement(H);
G&&G[B.RICH_CONTAINER]&&(G[B.RICH_CONTAINER].attachedComponents[this.name]=null)
},destroy:function(){}}
})(D))
})(jQuery,window.RichFaces||(window.RichFaces={}));
(function(B,A){A.ui=A.ui||{};
A.ui.Base=function(F,E,D){this.namespace="."+A.Event.createNamespace(this.name,F);
C.constructor.call(this,F);
this.options=B.extend(this.options,D,E);
this.attachToDom();
this.__bindEventHandlers()
};
A.BaseComponent.extend(A.ui.Base);
var C=A.ui.Base.$super;
B.extend(A.ui.Base.prototype,{__bindEventHandlers:function(){},destroy:function(){A.Event.unbindById(this.id,this.namespace);
C.destroy.call(this)
}})
})(jQuery,window.RichFaces||(window.RichFaces={}));