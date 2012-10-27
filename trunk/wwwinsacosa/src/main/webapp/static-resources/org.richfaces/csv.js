(function(B,A){A.csv=A.csv||{};
var C=/\{(\d+)\}/g;
function D(G,E){var H=G||"";
if(H){var J=H.replace(C,"\n$1\n").split("\n");
var I;
for(var F=1;
F<J.length;
F+=2){I=E[J[F]];
J[F]=typeof I=="undefined"?"":I
}H=J.join("")
}return H
}B.extend(A.csv,{RE_DIGITS:/^-?\d+$/,RE_FLOAT:/^(-?\d+)?(\.(\d+)?(e[+-]?\d+)?)?$/,getMessage:function(E,F){return{detail:D(E.detail,F),summary:D(E.summary,F)}
},sendMessage:function(E,F){A.Event.fire(window.document,A.Event.MESSAGE_EVENT_TYPE,{sourceId:E,message:F})
},clearMessage:function(E){A.Event.fire(window.document,A.Event.MESSAGE_EVENT_TYPE,{sourceId:E,message:""})
},getValue:function(E,G){var H;
var G=G||A.getDomElement(id);
if(G.value){H=G.value
}else{var F=A.$(G);
H=F&&typeof F.getValue=="function"?F.getValue():""
}return H
},validate:function(H,K,G,E){var I=getValue(K);
if(G){try{G.options.componentId=K;
I=getConverter([G.name])(I,G.options)
}catch(J){sendMessage(K,J.message);
return false
}}if(E){var F;
try{for(i=0;
i<E.length;
i++){F=getValidator(E[i].type);
if(F){F(K,I,E[i])
}}}catch(J){sendMessage(K,result);
return false
}}return true
},convertBoolean:function(H,G,F){F=F||{};
var E;
H=B.trim(H).toLowerCase();
E=H=="true"?true:H.length<1?null:false;
return E
},validateLength:function(F,E,G){if(G.maximum&&F.length>G.maximum){throw A.csv.getMessage(E,[G.minimum,G.maximum])
}if(G.minimum&&F.length<G.minimum){throw A.csv.getMessage(E,[G.minimum,G.maximum])
}},addFormValidators:function(F,E){}})
})(jQuery,window.RichFaces||(window.RichFaces={}));