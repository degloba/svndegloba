(function(C,B){B.ui=B.ui||{};
var E={rejectClass:"rf-ind-rejt",acceptClass:"rf-ind-acpt",draggingClass:"rf-ind-drag"};
B.ui.Draggable=function(H,F){this.options={};
C.extend(this.options,A,F||{});
D.constructor.call(this,H);
this.id=H;
this.namespace=this.namespace||"."+B.Event.createNamespace(this.name,this.id);
this.parentId=this.options.parentId;
this.attachToDom(this.parentId);
this.dragElement=C(document.getElementById(this.options.parentId));
this.dragElement.draggable();
if(F.indicator){var G=document.getElementById(F.indicator);
this.dragElement.data("indicator",true);
this.dragElement.draggable("option","helper",function(){return G
})
}else{this.dragElement.data("indicator",false);
this.dragElement.draggable("option","helper","clone")
}this.dragElement.draggable("option","addClasses",false);
this.dragElement.data("type",this.options.type);
this.dragElement.data("init",true);
this.dragElement.data("id",this.id);
B.Event.bind(this.dragElement,"dragstart"+this.namespace,this.dragStart,this);
B.Event.bind(this.dragElement,"drag"+this.namespace,this.drag,this);
B.Event.bind(this.dragElement,"dragstop"+this.namespace,this.dragStop,this)
};
B.BaseNonVisualComponent.extend(B.ui.Draggable);
var D=B.ui.Draggable.$super;
var A={};
C.extend(B.ui.Draggable.prototype,(function(){return{name:"Draggable",dragStart:function(J){var G=J.rf.data;
var F=G.helper[0];
this.parentElement=F.parentNode;
G.helper.detach().appendTo("body").setPosition(J).show();
if(this.__isCustomDragIndicator()){var I=(G.helper.width()/2);
var H=(G.helper.height()/2);
this.dragElement.data("draggable").offset.click.left=I;
this.dragElement.data("draggable").offset.click.top=H
}},drag:function(H){var G=H.rf.data;
if(this.__isCustomDragIndicator()){var F=B.$(this.options.indicator);
if(F){G.helper.addClass(F.getDraggingClass())
}else{G.helper.addClass(E.draggingClass)
}}this.__clearDraggableCss(G.helper)
},dragStop:function(G){var F=G.rf.data;
F.helper.hide().detach().appendTo(this.parentElement);
if(F.helper[0]!=this.dragElement[0]){F.helper[0]=this.dragElement[0]
}},__isCustomDragIndicator:function(){return this.dragElement.data("indicator")
},__clearDraggableCss:function(F){if(F&&F.removeClass){F.removeClass("ui-draggable-dragging")
}},destroy:function(){this.detach(this.parentId);
B.Event.unbind(this.dragElement,this.namespace);
D.destroy.call(this)
}}
})())
})(jQuery,window.RichFaces);