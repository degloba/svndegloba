jQuery.atmosphere=function(){var A;
$(window).unload(function(){if(A){A.abort()
}});
return{version:0.7,response:{status:200,responseBody:"",headers:[],state:"messageReceived",transport:"polling",push:[],error:null,id:0},request:{},abordingConnection:false,logLevel:"info",callbacks:[],activeTransport:null,websocket:null,killHiddenIFrame:null,subscribe:function(B,D,C){jQuery.atmosphere.request=jQuery.extend({timeout:300000,method:"GET",headers:{},contentType:"text/html;charset=ISO-8859-1",cache:true,async:true,ifModified:false,callback:null,dataType:"",url:B,data:"",suspend:true,maxRequest:60,lastIndex:0,logLevel:"info",requestCount:0,fallbackTransport:"streaming",transport:"long-polling"},C);
logLevel=jQuery.atmosphere.request.logLevel||"info";
if(D!=null){jQuery.atmosphere.addCallback(D);
jQuery.atmosphere.request.callback=D
}if(jQuery.atmosphere.request.transport!=jQuery.atmosphere.activeTransport){jQuery.atmosphere.closeSuspendedConnection()
}jQuery.atmosphere.activeTransport=jQuery.atmosphere.request.transport;
if(jQuery.atmosphere.request.transport!="websocket"){jQuery.atmosphere.executeRequest()
}else{if(jQuery.atmosphere.request.transport=="websocket"){if(!window.WebSocket){jQuery.atmosphere.log(logLevel,["Websocket is not supported, using request.fallbackTransport"]);
jQuery.atmosphere.request.transport=jQuery.atmosphere.request.fallbackTransport;
jQuery.atmosphere.executeRequest()
}else{jQuery.atmosphere.executeWebSocket()
}}}},closeSuspendedConnection:function(){abordingConnection=true;
if(A!=null){A.abort()
}if(jQuery.atmosphere.websocket!=null){jQuery.atmosphere.websocket.close();
jQuery.atmosphere.websocket=null
}abordingConnection=false
},executeRequest:function(){if(jQuery.atmosphere.request.transport=="streaming"){if($.browser.msie){jQuery.atmosphere.ieStreaming();
return 
}else{if((typeof window.addEventStream)=="function"){jQuery.atmosphere.operaStreaming();
return 
}}}if(jQuery.atmosphere.request.requestCount++<jQuery.atmosphere.request.maxRequest){jQuery.atmosphere.response.push=function(J){jQuery.atmosphere.request.callback=null;
jQuery.atmosphere.publish(J,null,jQuery.atmosphere.request)
};
var H=jQuery.atmosphere.request;
var C=jQuery.atmosphere.response;
if(H.transport!="polling"){C.transport=H.transport
}var G;
var E=false;
if($.browser.msie){var D=["Msxml2.XMLHTTP","Microsoft.XMLHTTP"];
for(var F=0;
F<D.length;
F++){try{G=new ActiveXObject(D[F])
}catch(I){}}}else{if(window.XMLHttpRequest){G=new XMLHttpRequest()
}}if(H.suspend){A=G
}G.open(H.method,H.url,true);
G.setRequestHeader("X-Atmosphere-Framework",jQuery.atmosphere.version);
G.setRequestHeader("X-Atmosphere-Transport",H.transport);
G.setRequestHeader("X-Cache-Date",new Date().getTime());
for(var B in H.headers){G.setRequestHeader(B,H.headers[B])
}if(!$.browser.msie){G.onerror=function(){E=true;
try{C.status=XMLHttpRequest.status
}catch(J){C.status=404
}C.state="error";
jQuery.atmosphere.invokeCallback(C);
G.abort();
A=null
}
}G.onreadystatechange=function(){if(abordingConnection){return 
}var L=false;
var Q=false;
if(G.readyState==4){jQuery.atmosphere.request=H;
if(H.suspend&&G.status==200){jQuery.atmosphere.executeRequest()
}if($.browser.msie){Q=true
}}else{if(!$.browser.msie&&G.readyState==3&&G.status==200){Q=true
}else{clearTimeout(H.id)
}}if(Q){if(H.transport=="streaming"){C.responseBody=G.responseText.substring(H.lastIndex,G.responseText.length);
if(H.lastIndex==0&&C.responseBody.indexOf("<!-- Welcome to the Atmosphere Framework.")!=-1){var N="<!-- EOD -->";
var J="<!-- EOD -->".length;
var M=C.responseBody.indexOf(N)+J;
if(M!=G.responseText.length){C.responseBody=C.responseBody.substring(M)
}else{L=true
}}H.lastIndex=G.responseText.length;
if(L){return 
}}else{C.responseBody=G.responseText
}if(C.responseBody.indexOf("parent.callback")!=-1){var P=C.responseBody.indexOf("('")+2;
var K=C.responseBody.indexOf("')");
C.responseBody=C.responseBody.substring(P,K)
}try{C.status=G.status;
C.headers=G.getAllResponseHeaders()
}catch(O){C.status=404
}if(H.suspend){C.state="messageReceived"
}else{C.state="messagePublished"
}jQuery.atmosphere.invokeCallback(C)
}};
G.send(H.data);
if(H.suspend){H.id=setTimeout(function(){G.abort();
jQuery.atmosphere.subscribe(H.url,null,H)
},H.timeout)
}}else{jQuery.atmosphere.log(logLevel,["Max re-connection reached."])
}},operaStreaming:function(){var C=jQuery.atmosphere.request.url;
var E=document.createElement("event-source");
var B=jQuery.atmosphere.response;
jQuery.atmosphere.response.push=function(F){jQuery.atmosphere.request.transport="polling";
jQuery.atmosphere.request.callback=null;
jQuery.atmosphere.publish(F,null,jQuery.atmosphere.request)
};
E.setAttribute("src",C);
if(opera.version()<9.5){document.body.appendChild(E)
}var D=function(H){if(H.data){var G=false;
B.responseBody=H.data;
if(H.data.indexOf("<!--")!=-1){G=true
}if(B.responseBody.indexOf("parent.callback")!=-1){var I=B.responseBody.indexOf("('")+2;
var F=B.responseBody.indexOf("')");
B.responseBody=B.responseBody.substring(I,F)
}if(G){return 
}B.state="messageReceived";
jQuery.atmosphere.invokeCallback(B)
}};
E.addEventListener("payload",D,false)
},ieStreaming:function(){var B=jQuery.atmosphere.request.url;
jQuery.atmosphere.response.push=function(D){jQuery.atmosphere.request.transport="polling";
jQuery.atmosphere.request.callback=null;
jQuery.atmosphere.publish(D,null,jQuery.atmosphere.request)
};
transferDoc=new ActiveXObject("htmlfile");
transferDoc.open();
transferDoc.close();
var C=transferDoc.createElement("div");
transferDoc.body.appendChild(C);
C.innerHTML="<iframe src='"+B+"'></iframe>";
transferDoc.parentWindow.callback=jQuery.atmosphere.streamingCallback
},streamingCallback:function(C){var B=jQuery.atmosphere.response;
B.transport="streaming";
B.status=200;
B.responseBody=C;
B.state="messageReceived";
jQuery.atmosphere.invokeCallback(B)
},executeWebSocket:function(){var E=jQuery.atmosphere.request;
var F=false;
jQuery.atmosphere.log(logLevel,["Invoking executeWebSocket"]);
jQuery.atmosphere.response.transport="websocket";
var C=jQuery.atmosphere.request.url;
var G=jQuery.atmosphere.request.callback;
var B=C.replace("http:","ws:").replace("https:","wss:");
var D=new WebSocket(B);
jQuery.atmosphere.websocket=D;
jQuery.atmosphere.response.push=function(I){var J;
var H=jQuery.atmosphere.websocket;
try{J=jQuery.atmosphere.request.data;
H.send(jQuery.atmosphere.request.data)
}catch(K){jQuery.atmosphere.log(logLevel,["Websocket failed. Downgrading to Comet and resending "+J]);
E.transport=E.fallbackTransport;
jQuery.atmosphere.request=E;
jQuery.atmosphere.executeRequest();
H.onclose=function(L){};
H.close()
}};
D.onopen=function(H){F=true;
jQuery.atmosphere.response.state="openning";
jQuery.atmosphere.invokeCallback(jQuery.atmosphere.response)
};
D.onmessage=function(I){var J=I.data;
if(J.indexOf("parent.callback")!=-1){var K=J.indexOf("('")+2;
var H=J.indexOf("')");
jQuery.atmosphere.response.responseBody=J.substring(K,H)
}else{jQuery.atmosphere.response.responseBody=J
}jQuery.atmosphere.invokeCallback(jQuery.atmosphere.response)
};
D.onerror=function(H){jQuery.atmosphere.response.state="error";
jQuery.atmosphere.invokeCallback(jQuery.atmosphere.response)
};
D.onclose=function(H){if(!F){var I=jQuery.atmosphere.request.data;
jQuery.atmosphere.log(logLevel,["Websocket failed. Downgrading to Comet and resending "+I]);
E.transport=E.fallbackTransport;
jQuery.atmosphere.request=E;
jQuery.atmosphere.executeRequest()
}else{jQuery.atmosphere.response.state="closed";
jQuery.atmosphere.invokeCallback(jQuery.atmosphere.response)
}}
},addCallback:function(B){if(jQuery.inArray(B,jQuery.atmosphere.callbacks)==-1){jQuery.atmosphere.callbacks.push(B)
}},removeCallback:function(B){if(jQuery.inArray(B,jQuery.atmosphere.callbacks)!=-1){jQuery.atmosphere.callbacks.splice(index)
}},invokeCallback:function(B){var C=function(D,E){E(B)
};
jQuery.atmosphere.log(logLevel,["Invoking "+jQuery.atmosphere.callbacks.length+" callbacks"]);
if(jQuery.atmosphere.callbacks.length>0){jQuery.each(jQuery.atmosphere.callbacks,C)
}},publish:function(B,D,C){jQuery.atmosphere.request=jQuery.extend({connected:false,timeout:60000,method:"POST",headers:{},cache:true,async:true,ifModified:false,callback:null,dataType:"",url:B,data:"",suspend:false,maxRequest:60,logLevel:"info",requestCount:0,transport:"polling"},C);
if(D!=null){jQuery.atmosphere.addCallback(D)
}jQuery.atmosphere.request.transport="polling";
if(jQuery.atmosphere.request.transport!="websocket"){jQuery.atmosphere.executeRequest()
}else{if(jQuery.atmosphere.request.transport=="websocket"){if(!window.WebSocket){alert("WebSocket not supported by this browser")
}else{jQuery.atmosphere.executeWebSocket()
}}}},unload:function(B){if(window.addEventListener){document.addEventListener("unload",B,false);
window.addEventListener("unload",B,false)
}else{document.attachEvent("onunload",B);
window.attachEvent("onunload",B)
}},kill_load_bar:function(){if(jQuery.atmosphere.killHiddenIFrame==null){jQuery.atmosphere.killHiddenIFrame=document.createElement("iframe");
var B=jQuery.atmosphere.killHiddenIFrame;
B.style.display="block";
B.style.width="0";
B.style.height="0";
B.style.border="0";
B.style.margin="0";
B.style.padding="0";
B.style.overflow="hidden";
B.style.visibility="hidden"
}document.body.appendChild(B);
B.src="about:blank";
document.body.removeChild(B)
},log:function(D,C){if(window.console){var B=window.console[D];
if(typeof B=="function"){B.apply(window.console,C)
}}},warn:function(){log("warn",arguments)
},info:function(){if(logLevel!="warn"){log("info",arguments)
}},debug:function(){if(logLevel=="debug"){log("debug",arguments)
}}}
}();