(function(E,C){C.Event=C.Event||{};
var B=function(F){if(!F){throw"RichFaces.Event: empty selector"
}var G;
if(RichFaces.BaseComponent&&F instanceof RichFaces.BaseComponent){G=E(C.getDomElement(F.getEventElement()))
}else{G=E(F)
}return G
};
var D=function(F,G){return function(H,I){if(!H[C.RICH_CONTAINER]){H[C.RICH_CONTAINER]={data:I}
}return G.call(F||this,H,this,I)
}
};
var A=function(H,G){var F={};
for(var I in H){F[I]=D(G,H[I])
}return F
};
E.extend(C.Event,{RICH_NAMESPACE:"RICH",EVENT_NAMESPACE_SEPARATOR:".",MESSAGE_EVENT_TYPE:"onmessage",ready:function(F){return E(document).ready(F)
},bind:function(F,H,I,G,K){if(typeof H=="object"){B(F).bind(A(H,I),K)
}else{var J=D(G,I);
B(F).bind(H,K,J);
return J
}},bindById:function(K,G,H,F,J){if(typeof G=="object"){E(document.getElementById(K)).bind(A(G,H),J)
}else{var I=D(F,H);
E(document.getElementById(K)).bind(G,J,I)
}return I
},bindOne:function(F,H,I,G,K){var J=D(G,I);
B(F).one(H,K,J);
return J
},bindOneById:function(K,G,H,F,J){var I=D(F,H);
E(document.getElementById(K)).one(G,J,I);
return I
},unbind:function(F,G,H){return B(F).unbind(G,H)
},unbindById:function(H,F,G){return E(document.getElementById(H)).unbind(F,G)
},bindScrollEventHandlers:function(G,H,F){var I=[];
G=C.getDomElement(G).parentNode;
while(G&&G!=window.document.body){if(G.offsetWidth!=G.scrollWidth||G.offsetHeight!=G.scrollHeight){I.push(G);
C.Event.bind(G,"scroll"+F.getNamespace(),H,F)
}G=G.parentNode
}return I
},unbindScrollEventHandlers:function(G,F){C.Event.unbind(G,"scroll"+F.getNamespace())
},fire:function(F,G,I){var H=E.Event(G);
B(F).trigger(H,[I]);
return !H.isDefaultPrevented()
},fireById:function(I,F,H){var G=E.Event(F);
E(document.getElementById(I)).trigger(G,[H]);
return !G.isDefaultPrevented()
},callHandler:function(F,G,H){return B(F).triggerHandler(G,[H])
},callHandlerById:function(H,F,G){return E(document.getElementById(H)).triggerHandler(F,[G])
},createNamespace:function(G,I,H){var F=[];
F.push(H||C.Event.RICH_NAMESPACE);
if(G){F.push(G)
}if(I){F.push(I)
}return F.join(C.Event.EVENT_NAMESPACE_SEPARATOR)
}})
})(jQuery,window.RichFaces||(window.RichFaces={}));