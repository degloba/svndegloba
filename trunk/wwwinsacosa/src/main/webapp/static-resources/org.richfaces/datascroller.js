(function(D,A){A.ui=A.ui||{};
var C=function(K,I,G){var M;
var J=function(N){N.data.fn.call(N.data.component,N)
};
var L={};
L.component=G;
for(M in K){var H=D(document.getElementById(M));
L.id=M;
L.page=K[M];
L.element=H;
L.fn=G.processClick;
H.bind("click",F(L),J)
}};
var F=function(I){var G;
var H={};
for(G in I){H[G]=I[G]
}return H
};
var B=function(G,H){if(H.type=="mousedown"){G.addClass("rf-ds-press")
}else{if(H.type=="mouseup"||H.type=="mouseout"){G.removeClass("rf-ds-press")
}}};
A.ui.DataScroller=function(K,J,G){E.constructor.call(this,K);
var I=this.attachToDom();
this.options=G;
this.currentPage=G.currentPage;
if(J&&typeof J=="function"){RichFaces.Event.bindById(K,this.getScrollEventName(),J)
}var H={};
if(G.buttons){D(I).delegate(".rf-ds-btn","mouseup mousedown mouseout",function(L){if(D(this).hasClass("rf-ds-dis")){D(this).removeClass("rf-ds-press")
}else{B(D(this),L)
}});
C(G.buttons.left,H,this);
C(G.buttons.right,H,this)
}if(G.digitals){D(I).delegate(".rf-ds-nmb-btn","mouseup mousedown mouseout",function(L){B(D(this),L)
});
C(G.digitals,H,this)
}};
A.BaseComponent.extend(A.ui.DataScroller);
var E=A.ui.DataScroller.$super;
D.extend(A.ui.DataScroller.prototype,(function(){var G="rich:datascroller:onscroll";
return{name:"RichFaces.ui.DataScroller",processClick:function(H){var J=H.data;
if(J){var I=J.page;
if(I){this.switchToPage(I)
}}},switchToPage:function(H){if(typeof H!="undefined"&&H!=null){RichFaces.Event.fireById(this.id,this.getScrollEventName(),{page:H})
}},fastForward:function(){this.switchToPage("fastforward")
},fastRewind:function(){this.switchToPage("fastrewind")
},next:function(){this.switchToPage("next")
},previous:function(){this.switchToPage("previous")
},first:function(){this.switchToPage("first")
},last:function(){this.switchToPage("last")
},getScrollEventName:function(){return G
},destroy:function(){E.destroy.call(this)
}}
})())
})(jQuery,window.RichFaces);