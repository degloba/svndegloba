if(typeof jsf!="undefined"){(function(F,E,A){var H=function(J){var I="<"+J.tagName.toLowerCase();
var K=F(J);
if(K.attr("id")){I+=(" id="+K.attr("id"))
}if(K.attr("class")){I+=(" class="+K.attr("class"))
}I+=" ...>";
return I
};
var D=function(I,K){var J=F(K);
I.append("Element <b>"+K.nodeName+"</b>");
if(J.attr("id")){I.append(document.createTextNode(" for id="+J.attr("id")))
}F(document.createElement("br")).appendTo(I);
F("<span style='color:dimgray'></span>").appendTo(I).text(J.toXML());
F(document.createElement("br")).appendTo(I)
};
var G=function(I){var J=F(document.createElement("span"));
I.children().each(function(){var K=F(this);
if(K.is("changes")){J.append("Listing content of response <b>changes</b> element:<br />");
K.children().each(function(){D(J,this)
})
}else{D(J,this)
}});
return J
};
var C=function(M){try{var K=E.log;
var I=M.source;
var P=M.type;
var R=M.responseCode;
var Q=M.responseXML;
var O=M.responseText;
if(P!="error"){K.info("Received '"+P+"' event from "+H(I));
if(P=="beforedomupdate"){var L;
if(Q){L=F(Q).children("partial-response")
}var S=F("<span>Server returned responseText: </span><span style='color:dimgray'></span>").eq(1).text(O).end();
if(L&&L.length){K.debug(S);
K.info(G(L))
}else{K.info(S)
}}}else{var J=M.status;
K.error("Received '"+P+"@"+J+"' event from "+H(I));
K.error("["+M.responseCode+"] "+M.errorName+": "+M.errorMessage)
}}catch(N){}};
var B=E.createJSFEventsAdapter({begin:C,beforedomupdate:C,success:C,complete:C,error:C});
A.ajax.addOnEvent(B);
A.ajax.addOnError(B)
}(jQuery,RichFaces,jsf))
};