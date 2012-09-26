(function(B,A){A.ui=A.ui||{};
A.ui.PopupPanel.Sizer=function(G,E,F,D){C.constructor.call(this,G)
};
var C=A.BaseComponent.extend(A.ui.PopupPanel.Sizer);
var C=A.ui.PopupPanel.Sizer.$super;
B.extend(A.ui.PopupPanel.Sizer.prototype,(function(D){return{name:"richfaces.ui.PopupPanel.Sizer",doSetupSize:function(J,F){var H=0;
var E=0;
var G=B(A.getDomElement(F));
var I=J.reductionData;
if(I){if(I.w){H=I.w/2
}if(I.h){E=I.h/2
}}if(H>0){if(F.clientWidth>H){if(!F.reducedWidth){F.reducedWidth=G.css("width")
}G.css("width",H+"px")
}else{if(H<4&&F.reducedWidth==4+"px"){G.css("width",H+"px")
}}}else{if(F.reducedWidth){G.css("width",F.reducedWidth);
F.reducedWidth=undefined
}}if(E>0){if(F.clientHeight>E){if(!F.reducedHeight){F.reducedHeight=G.css("height")
}F.style.height=E+"px"
}else{if(E<4&&F.reducedHeight==4+"px"){G.css("height",E+"px")
}}}else{if(F.reducedHeight){G.css("height",F.reducedHeight);
F.reducedHeight=undefined
}}},doSetupPosition:function(I,E,H,G){var F=B(A.getDomElement(E));
if(!isNaN(H)&&!isNaN(G)){F.css("left",H+"px");
F.css("top",G+"px")
}},doPosition:function(F,E){},doDiff:function(F,E){}}
})());
A.ui.PopupPanel.Sizer.Diff=function(F,D,E,G){this.deltaX=F;
this.deltaY=D;
this.deltaWidth=E;
this.deltaHeight=G
};
A.ui.PopupPanel.Sizer.Diff.EMPTY=function(){return new A.ui.PopupPanel.Sizer.Diff(0,0,0,0)
},A.ui.PopupPanel.Sizer.N=function(){};
B.extend(A.ui.PopupPanel.Sizer.N.prototype,A.ui.PopupPanel.Sizer.prototype);
B.extend(A.ui.PopupPanel.Sizer.N.prototype,{name:"richfaces.ui.PopupPanel.Sizer.N",doPosition:function(F,D){var E=B(A.getDomElement(D));
E.css("width",F.width()+"px");
this.doSetupPosition(F,D,0,0)
},doDiff:function(E,D){return new A.ui.PopupPanel.Sizer.Diff(0,D,0,-D)
}});
A.ui.PopupPanel.Sizer.NW=function(){};
B.extend(A.ui.PopupPanel.Sizer.NW.prototype,A.ui.PopupPanel.Sizer.prototype);
B.extend(A.ui.PopupPanel.Sizer.NW.prototype,{name:"richfaces.ui.PopupPanel.Sizer.NW",doPosition:function(E,D){this.doSetupSize(E,D);
this.doSetupPosition(E,D,0,0)
},doDiff:function(E,D){return new A.ui.PopupPanel.Sizer.Diff(E,D,-E,-D)
}});
A.ui.PopupPanel.Sizer.NE=function(){};
B.extend(A.ui.PopupPanel.Sizer.NE.prototype,A.ui.PopupPanel.Sizer.prototype);
B.extend(A.ui.PopupPanel.Sizer.NE.prototype,{name:"richfaces.ui.PopupPanel.Sizer.NE",doPosition:function(E,D){this.doSetupSize(E,D);
this.doSetupPosition(E,D,E.width()-D.clientWidth,0)
},doDiff:function(E,D){return new A.ui.PopupPanel.Sizer.Diff(0,D,E,-D)
}});
A.ui.PopupPanel.Sizer.E=function(){};
B.extend(A.ui.PopupPanel.Sizer.E.prototype,A.ui.PopupPanel.Sizer.prototype);
B.extend(A.ui.PopupPanel.Sizer.E.prototype,{name:"richfaces.ui.PopupPanel.Sizer.E",doPosition:function(F,D){var E=B(A.getDomElement(D));
E.css("height",F.height()+"px");
this.doSetupPosition(F,D,F.width()-D.clientWidth,0)
},doDiff:function(E,D){return new A.ui.PopupPanel.Sizer.Diff(0,0,E,0)
}});
A.ui.PopupPanel.Sizer.SE=function(){};
B.extend(A.ui.PopupPanel.Sizer.SE.prototype,A.ui.PopupPanel.Sizer.prototype);
B.extend(A.ui.PopupPanel.Sizer.SE.prototype,{name:"richfaces.ui.PopupPanel.Sizer.SE",doPosition:function(E,D){this.doSetupSize(E,D);
this.doSetupPosition(E,D,E.width()-D.clientWidth,E.height()-D.clientHeight)
},doDiff:function(E,D){return new A.ui.PopupPanel.Sizer.Diff(0,0,E,D)
}});
A.ui.PopupPanel.Sizer.S=function(){};
B.extend(A.ui.PopupPanel.Sizer.S.prototype,A.ui.PopupPanel.Sizer.prototype);
B.extend(A.ui.PopupPanel.Sizer.S.prototype,{name:"richfaces.ui.PopupPanel.Sizer.S",doPosition:function(F,D){var E=B(A.getDomElement(D));
E.css("width",F.width()+"px");
this.doSetupPosition(F,D,0,F.height()-D.clientHeight)
},doDiff:function(E,D){return new A.ui.PopupPanel.Sizer.Diff(0,0,0,D)
}});
A.ui.PopupPanel.Sizer.SW=function(){};
B.extend(A.ui.PopupPanel.Sizer.SW.prototype,A.ui.PopupPanel.Sizer.prototype);
B.extend(A.ui.PopupPanel.Sizer.SW.prototype,{name:"richfaces.ui.PopupPanel.Sizer.SW",doPosition:function(E,D){this.doSetupSize(E,D);
this.doSetupPosition(E,D,0,E.height()-D.clientHeight)
},doDiff:function(E,D){return new A.ui.PopupPanel.Sizer.Diff(E,0,-E,D)
}});
A.ui.PopupPanel.Sizer.W=function(){};
B.extend(A.ui.PopupPanel.Sizer.W.prototype,A.ui.PopupPanel.Sizer.prototype);
B.extend(A.ui.PopupPanel.Sizer.W.prototype,{name:"richfaces.ui.PopupPanel.Sizer.W",doPosition:function(F,D){var E=B(A.getDomElement(D));
E.css("height",F.height()+"px");
this.doSetupPosition(F,D,0,0)
},doDiff:function(E,D){return new A.ui.PopupPanel.Sizer.Diff(E,0,-E,0)
}});
A.ui.PopupPanel.Sizer.Header=function(){};
B.extend(A.ui.PopupPanel.Sizer.Header.prototype,A.ui.PopupPanel.Sizer.prototype);
B.extend(A.ui.PopupPanel.Sizer.Header.prototype,{name:"richfaces.ui.PopupPanel.Sizer.Header",doPosition:function(E,D){},doDiff:function(E,D){return new A.ui.PopupPanel.Sizer.Diff(E,D,0,0)
}})
})(jQuery,window.RichFaces);