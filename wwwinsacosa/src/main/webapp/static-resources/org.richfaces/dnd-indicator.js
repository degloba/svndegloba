(function(C,B){B.ui=B.ui||{};
B.ui.DragIndicator=function(F,E){D.constructor.call(this,F);
this.attachToDom(F);
this.indicator=C(document.getElementById(F));
this.options=E
};
var A={};
B.BaseComponent.extend(B.ui.DragIndicator);
var D=B.ui.DragIndicator.$super;
C.extend(B.ui.DragIndicator.prototype,(function(){return{show:function(){this.indicator.show()
},hide:function(){this.indicator.hide()
},getAcceptClass:function(){return this.options.acceptClass
},getRejectClass:function(){return this.options.rejectClass
},getDraggingClass:function(){return this.options.draggingClass
},getElement:function(){return this.indicator
}}
})())
})(jQuery,window.RichFaces);