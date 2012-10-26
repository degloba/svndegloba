(function(H,D,E){var A="Push";
var G=D.Event.RICH_NAMESPACE;
var K=D.Event.EVENT_NAMESPACE_SEPARATOR;
var B="dataAvailable"+K+G+K+A;
var L="error"+K+G+K+A;
var C=function(M){return B+K+M
};
var I=function(M){return L+K+M
};
D.Push=(function(){var N={};
var P={};
var T={};
var Q=null;
var O=null;
var M=/^(<!--[^>]+-->\s*)+/;
var S=function(V){var X=V.responseBody.replace(M,"");
if(X){var Z=E.parseJSON(X);
if(Z){for(var W=0;
W<Z.length;
W++){var Y=Z[W];
D.Event.fire(document,C(Y.topic),Y.data)
}}}jQuery.atmosphere.request.requestCount=0
};
var U=function(){var V=function(b){var a=E.parseJSON(b);
for(var Z in a.failures){D.Event.fire(document,I(Z),a.failures[Z])
}if(a.sessionId){O=a.sessionId;
E.atmosphere.subscribe(Q+"?__richfacesPushAsync=1&pushSessionId="+O,S,{})
}};
var Y=new Array();
for(var W in T){Y.push(W)
}var X={pushTopic:Y};
if(O){X.forgetPushSessionId=O
}E.ajax({data:X,dataType:"text",success:V,traditional:true,type:"POST",url:Q})
};
var R=function(){E.atmosphere.closeSuspendedConnection()
};
return{increaseSubscriptionCounters:function(V){if(isNaN(T[V]++)){T[V]=1;
N[V]=true
}},decreaseSubscriptionCounters:function(V){if(--T[V]==0){delete T[V];
P[V]=true
}},setPushUrl:function(V){if(V.charAt(0)=="/"){Q=location.protocol+"//"+location.host+V
}else{Q=V
}},updateConnection:function(){if(E.isEmptyObject(T)){R()
}else{if(!E.isEmptyObject(N)||!E.isEmptyObject(P)){R();
U()
}}N={};
P={}
}}
}());
E(document).ready(D.Push.updateConnection);
var F=function(M){if(M.type=="event"){if(M.status!="success"){return 
}}else{if(M.type!="error"){return 
}}D.Push.updateConnection()
};
H.ajax.addOnEvent(F);
H.ajax.addOnError(F);
D.ui=D.ui||{};
D.ui.Push=D.BaseComponent.extendClass({name:A,init:function(N,M){J.constructor.call(this,N);
this.attachToDom();
this.__address=M.address;
this.__handlers={};
if(M.ondataavailable){this.__bindDataHandler(M.ondataavailable)
}if(M.onerror){this.__bindErrorHandler(M.onerror)
}D.Push.increaseSubscriptionCounters(this.__address)
},__bindDataHandler:function(N){var M=C(this.__address);
this.__handlers.data=D.Event.bind(document,M,new Function("event",N))
},__unbindDataHandler:function(){if(this.__handlers.data){var M=C(this.__address);
D.Event.unbind(document,M,this.__handlers.data);
this.__handlers.data=null
}},__bindErrorHandler:function(N){var M=I(this.__address);
this.__handlers.error=D.Event.bind(document,M,new Function("event",N))
},__unbindErrorHandler:function(){if(this.__handlers.error){var M=I(this.__address);
D.Event.unbind(document,M,this.__handlers.error);
this.__handlers.error=null
}},destroy:function(){this.__unbindDataHandler();
this.__unbindErrorHandler();
D.Push.decreaseSubscriptionCounters(this.__address);
J.destroy.call(this)
}});
var J=D.ui.Push.$super
}(jsf,window.RichFaces,jQuery));