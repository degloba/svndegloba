(function(F,M){M.csv=M.csv||{};
var H={};
var L=/\'?\{(\d+)\}\'?/g;
var B=function(R,P){if(R){var T=R.replace(L,"\n$1\n").split("\n");
var S;
for(var Q=1;
Q<T.length;
Q+=2){S=P[T[Q]];
T[Q]=typeof S=="undefined"?"":S
}return T.join("")
}else{return""
}};
var G=function(P){if(null!==P.value&&undefined!=P.value){return P.value
}else{return""
}};
var K=function(P){if(P.checked){return true
}else{return false
}};
var O=function(Q,P){if(P.selected){return Q[Q.length]=P.value
}};
var J={hidden:function(P){return G(P)
},text:function(P){return G(P)
},textarea:function(P){return G(P)
},"select-one":function(P){if(P.selectedIndex!=-1){return G(P)
}},password:function(P){return G(P)
},file:function(P){return G(P)
},radio:function(P){return K(P)
},checkbox:function(P){return K(P)
},"select-multiple":function(V){var R=V.name;
var U=V.childNodes;
var T=[];
for(var S=0;
S<U.length;
S++){var W=U[S];
if(W.tagName==="OPTGROUP"){var Q=W.childNodes;
for(var P=0;
P<Q.length;
P++){T=O(T,Q[P])
}}else{T=O(T,W)
}}return T
},input:function(P){return G(P)
}};
var E=function(S){var R;
var Q=M.getDomElement(S);
if(J[Q.type]){R=J[Q.type](Q)
}else{if(undefined!==Q.value){R=Q.value
}else{var P=M.$(Q);
R=P&&typeof P.getValue==="function"?P.getValue():""
}}return R
};
var D=function(P,Q){if(P.p){return P.p.label||Q
}return Q
};
F.extend(M.csv,{RE_DIGITS:/^-?\d+$/,RE_FLOAT:/^(-?\d+)?(\.(\d+)?(e[+-]?\d+)?)?$/,addMessage:function(P){F.extend(H,P)
},getMessage:function(R,Q,P){var S=R?R:H[Q]||{detail:"",summary:"",severity:0};
return{detail:B(S.detail,P),summary:B(S.summary,P),severity:S.severity}
},interpolateMessage:function(Q,P){return{detail:B(Q.detail,P),summary:B(Q.summary,P),severity:Q.severity}
},sendMessage:function(P,Q){M.Event.fire(window.document,M.Event.MESSAGE_EVENT_TYPE,{sourceId:P,message:Q})
},clearMessage:function(P){M.Event.fire(window.document,M.Event.MESSAGE_EVENT_TYPE,{sourceId:P})
},validate:function(Q,S,X,W){var a=E(X||S);
var R;
var U=W.c;
M.csv.clearMessage(S);
if(U){var Z=D(U,S);
try{if(U.f){R=U.f(a,S,D(U,S),U.m)
}}catch(Y){Y.severity=2;
M.csv.sendMessage(S,Y);
return false
}}else{R=a
}var b=true;
var V=W.v;
if(V){var T,P;
for(i=0;
i<V.length;
i++){try{P=V[i];
T=P.f;
if(T){T(R,D(P,S),P.p,P.m)
}}catch(Y){Y.severity=2;
M.csv.sendMessage(S,Y);
b=false
}}}if(b&&!W.da&&W.a){W.a.call(X,Q,S)
}return b
}});
var I=function(U,R,V,S,Q,T){var P=null;
if(U){U=F.trim(U);
if(!M.csv.RE_DIGITS.test(U)||(P=parseInt(U,10))<S||P>Q){throw M.csv.interpolateMessage(V,T?[U,T,R]:[U,R])
}}return P
};
var A=function(S,Q,T,R){var P=null;
if(S){S=F.trim(S);
if(!M.csv.RE_FLOAT.test(S)||isNaN(P=parseFloat(S))){throw M.csv.interpolateMessage(T,R?[S,R,Q]:[S,Q])
}}return P
};
F.extend(M.csv,{convertBoolean:function(R,P,T,S){if(typeof R==="string"){var Q=F.trim(R).toLowerCase();
if(Q==="on"||Q==="true"||Q==="yes"){return true
}}else{if(true===R){return true
}}return false
},convertDate:function(R,Q,T,S){var P;
R=F.trim(R);
P=Date.parse(R);
return P
},convertByte:function(Q,P,S,R){return I(Q,P,R,-128,127,254)
},convertNumber:function(R,Q,T,S){var P;
R=F.trim(R);
P=parseFloat(R);
if(isNaN(P)){throw M.csv.interpolateMessage(S,[R,99,Q])
}return P
},convertFloat:function(Q,P,S,R){return A(Q,P,R,2000000000)
},convertDouble:function(Q,P,S,R){return A(Q,P,R,1999999)
},convertShort:function(Q,P,S,R){return I(Q,P,R,-32768,32767,32456)
},convertInteger:function(Q,P,S,R){return I(Q,P,R,-2147483648,2147483648,9346)
},convertCharacter:function(Q,P,S,R){return I(Q,P,R,0,65535)
},convertLong:function(Q,P,S,R){return I(Q,P,R,-9223372036854776000,9223372036854776000,98765432)
}});
var N=function(Q,P,U,T){var S=typeof U.min==="number";
var R=typeof U.max==="number";
if(R&&Q>U.max){throw M.csv.interpolateMessage(T,S?[U.min,U.max,P]:[U.max,P])
}if(S&&Q<U.min){throw M.csv.interpolateMessage(T,R?[U.min,U.max,P]:[U.min,P])
}};
var C=function(S,P,R,U){if(typeof R!="string"||R.length==0){throw M.csv.getMessage(U,"REGEX_VALIDATOR_PATTERN_NOT_SET",[])
}var Q;
try{Q=new RegExp(R)
}catch(T){throw M.csv.getMessage(U,"REGEX_VALIDATOR_MATCH_EXCEPTION",[])
}if(!Q.test(S)){throw M.csv.interpolateMessage(U,[R,P])
}};
F.extend(M.csv,{validateLongRange:function(R,P,T,S){var Q=typeof R;
if(Q!=="number"){if(Q!="string"){throw M.csv.getMessage(S,"LONG_RANGE_VALIDATOR_TYPE",[componentId,""])
}else{R=F.trim(R);
if(!M.csv.RE_DIGITS.test(R)||(R=parseInt(R,10))==NaN){throw M.csv.getMessage(S,"LONG_RANGE_VALIDATOR_TYPE",[componentId,""])
}}}N(R,P,T,S)
},validateDoubleRange:function(R,P,T,S){var Q=typeof R;
if(Q!=="number"){if(Q!=="string"){throw M.csv.getMessage(S,"DOUBLE_RANGE_VALIDATOR_TYPE",[componentId,""])
}else{R=F.trim(R);
if(!M.csv.RE_FLOAT.test(R)||(R=parseFloat(R))==NaN){throw M.csv.getMessage(S,"DOUBLE_RANGE_VALIDATOR_TYPE",[componentId,""])
}}}N(R,P,T,S)
},validateLength:function(R,P,T,S){var Q=R?R.length:0;
N(Q,P,T,S)
},validateSize:function(R,P,T,S){var Q=R?R.length:0;
N(Q,P,T,S)
},validateRegex:function(Q,P,S,R){C(Q,P,S.pattern,R)
},validatePattern:function(Q,P,S,R){C(Q,P,S.regexp,R)
},validateRequired:function(Q,P,S,R){if(undefined===Q||null===Q||""===Q){throw M.csv.interpolateMessage(R,[P])
}},validateTrue:function(Q,P,S,R){if(Q!==true){throw R
}},validateFalse:function(Q,P,S,R){if(Q!==false){throw R
}},validateMax:function(Q,P,S,R){if(Q>S.value){throw R
}},validateMin:function(Q,P,S,R){if(Q<S.value){throw R
}}})
})(jQuery,window.RichFaces||(window.RichFaces={}));