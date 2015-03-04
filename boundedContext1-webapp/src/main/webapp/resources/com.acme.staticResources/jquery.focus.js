(function(E){function C(H,G){return H===G||(typeof (H.contains)!=="undefined"?H.contains(G):!!(H.compareDocumentPosition(G)&16))
}function B(G){G=E.event.fix(G||window.event),$this=E(this),isFocused=$this.data("focus.isFocused");
if(!isFocused){$this.data("focus.isFocused",true);
G.type="focusin";
return E.event.handle.apply(this,[G])
}}function F(H){var G=[].slice.call(arguments,1),I=E(this);
H=E.event.fix(H||window.event);
window.setTimeout(function(){if(!C(I.get(0),document.activeElement)){I.data("focus.isFocused",false);
H.type="focusout";
return E.event.handle.apply(I.get(0),[H])
}},0)
}function A(I){var G=E(I),H=G.data("focus.handlerReferences")||0;
if(H==0){if(I.addEventListener){I.addEventListener("focus",B,true);
I.addEventListener("blur",F,true)
}else{I.onfocusin=B;
I.onfocusout=F
}}G.data("focus.handlerReferences",H+1);
G.data("focus.isFocused",C(I,document.activeElement))
}function D(I){var G=E(I),H=G.data("focus.handlerReferences")||0;
if(H==1){if(I.removeEventListener){I.removeEventListener("focus",B,true);
I.removeEventListener("blur",F,true)
}else{I.onfocusin=null;
I.onfocusout=null
}G.removeData("focus.handlerReferences");
G.removeData("focus.isFocused")
}else{G.data("focus.handlerReferences",H-1)
}}E.each(["focusin","focusout"],function(H,G){E.event.special[G]={setup:function(){A(this)
},teardown:function(){D(this)
}}
});
E.fn.extend({focusin:function(G){return G?this.bind("focusin",G):this.trigger("focusin")
},focusout:function(G){return G?this.bind("focusout",G):this.trigger("focusout")
}})
})(jQuery);