(function(B,A){A.ui=A.ui||{};
A.ui.CollapsibleSubTableToggler=function(D,C){this.id=D;
this.eventName=C.eventName;
this.expandedControl=C.expandedControl;
this.collapsedControl=C.collapsedControl;
this.forId=C.forId;
this.element=B(document.getElementById(this.id));
if(this.element&&this.eventName){this.element.bind(this.eventName,B.proxy(this.switchState,this))
}};
B.extend(A.ui.CollapsibleSubTableToggler.prototype,(function(){var C=function(D){return B(document.getElementById(D))
};
return{switchState:function(E){var D=A.$(this.forId);
if(D){var F=D.getMode();
if(A.ui.CollapsibleSubTable.MODE_CLNT==F){this.toggleControl(D.isExpanded())
}D.setOption(this.id);
D.switchState(E)
}},toggleControl:function(F){var D=C(this.expandedControl);
var E=C(this.collapsedControl);
if(F){D.hide();
E.show()
}else{E.hide();
D.show()
}}}
})())
})(jQuery,window.RichFaces);