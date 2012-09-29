(function(B,A){A.ui=A.ui||{};
A.ui.PopupPanel.Border=function(H,F,G,E){C.constructor.call(this,H);
this.element=B(A.getDomElement(H));
this.element.css("cursor",G);
var D=this;
this.element.bind("mousedown",{border:D},this.startDrag);
this.modalPanel=F;
this.sizer=E
};
var C=A.BaseComponent.extend(A.ui.PopupPanel.Border);
var C=A.ui.PopupPanel.Border.$super;
B.extend(A.ui.PopupPanel.Border.prototype,(function(D){return{name:"RichFaces.ui.PopupPanel.Border",destroy:function(){if(this.doingDrag){B(document).unbind("mousemove",this.doDrag);
B(document).unbind("mouseup",this.endDrag)
}this.element.unbind("mousedown",this.startDrag);
this.element=null;
this.modalPanel=null
},show:function(){this.element.show()
},hide:function(){this.element.hide()
},startDrag:function(F){var E=F.data.border;
E.doingDrag=true;
E.dragX=F.clientX;
E.dragY=F.clientY;
B(document).bind("mousemove",{border:E},E.doDrag);
B(document).bind("mouseup",{border:E},E.endDrag);
E.modalPanel.startDrag(E);
E.onselectStartHandler=document.onselectstart;
document.onselectstart=function(){return false
}
},getWindowSize:function(){var F=0,E=0;
if(typeof (window.innerWidth)=="number"){F=window.innerWidth;
E=window.innerHeight
}else{if(document.documentElement&&(document.documentElement.clientWidth||document.documentElement.clientHeight)){F=document.documentElement.clientWidth;
E=document.documentElement.clientHeight
}else{if(document.body&&(document.body.clientWidth||document.body.clientHeight)){F=document.body.clientWidth;
E=document.body.clientHeight
}}}return{width:F,height:E}
},doDrag:function(E){var J=E.data.border;
if(!J.doingDrag){return 
}var I=E.clientX;
var F=E.clientY;
var L=J.getWindowSize();
if(I<0){I=0
}else{if(I>=L.width){I=L.width-1
}}if(F<0){F=0
}else{if(F>=L.height){F=L.height-1
}}var P=I-J.dragX;
var O=F-J.dragY;
if(P!=0||O!=0){var H=J.id;
var N=J.sizer.prototype.doDiff(P,O);
var M;
var K=J.modalPanel.cdiv;
if(N.deltaWidth||N.deltaHeight){M=J.modalPanel.invokeEvent("resize",E,null,K)
}else{if(N.deltaX||N.deltaY){M=J.modalPanel.invokeEvent("move",E,null,K)
}}var G;
if(M){G=J.modalPanel.doResizeOrMove(N)
}if(G){if(!G.x){J.dragX=I
}else{if(!N.deltaX){J.dragX-=G.vx||0
}else{J.dragX+=G.vx||0
}}if(!G.y){J.dragY=F
}else{if(!N.deltaY){J.dragY-=G.vy||0
}else{J.dragY+=G.vy||0
}}}}},endDrag:function(F){var E=F.data.border;
E.doingDrag=undefined;
B(document).unbind("mousemove",E.doDrag);
B(document).unbind("mouseup",E.endDrag);
E.modalPanel.endDrag(E);
E.modalPanel.doResizeOrMove(A.ui.PopupPanel.Sizer.Diff.EMPTY);
document.onselectstart=E.onselectStartHandler;
E.onselectStartHandler=null
},doPosition:function(){this.sizer.prototype.doPosition(this.modalPanel,this.element)
}}
})())
})(jQuery,window.RichFaces);