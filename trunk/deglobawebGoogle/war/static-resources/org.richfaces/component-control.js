(function(B,A){A.ui=A.ui||{};
A.ui.ComponentControl=A.ui.ComponentControl||{};
B.extend(A.ui.ComponentControl,{execute:function(H,G){var I=G.target;
var D=G.selector;
var J=G.callback;
if(G.onbeforeoperation&&typeof G.onbeforeoperation=="function"){var C=G.onbeforeoperation(H);
if(C=="false"||C==0){return 
}}if(I){for(var F=0;
F<I.length;
F++){var E=document.getElementById(I[F]);
if(E){A.ui.ComponentControl.invokeOnComponent(H,E,J)
}}}if(D){A.ui.ComponentControl.invokeOnComponent(H,D,J)
}},invokeOnComponent:function(C,D,E){if(E&&typeof E=="function"){B(D).each(function(){var F=A.$(this);
if(F){E(C,F)
}})
}}})
})(jQuery,window.RichFaces);