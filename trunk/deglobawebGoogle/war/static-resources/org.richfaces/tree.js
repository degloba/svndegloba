(function(F,C){var E="__NEW_NODE_TOGGLE_STATE";
var D="__TRIGGER_NODE_AJAX_UPDATE";
var L="__SELECTION_STATE";
var J=["rf-tr-nd-colps","rf-tr-nd-exp"];
var A=["rf-trn-hnd-colps","rf-trn-hnd-exp"];
var B=["rf-trn-ico-colps","rf-trn-ico-exp"];
C.ui=C.ui||{};
C.ui.TreeNode=C.BaseComponent.extendClass({name:"TreeNode",init:function(P,O){H.constructor.call(this,P);
this.__rootElt=F(this.attachToDom());
this.__children=new Array();
this.__initializeChildren(O);
var N=(O.clientEventHandlers||{})[this.getId().substring(O.treeId.length)]||{};
if(N.bth){C.Event.bind(this.__rootElt,"beforetoggle",new Function("event",N.bth))
}if(N.th){C.Event.bind(this.__rootElt,"toggle",new Function("event",N.th))
}this.__addLastNodeClass()
},destroy:function(){if(this.parent){this.parent.removeChild(this);
this.parent=null
}this.__clientToggleStateInput=null;
this.__clearChildren();
this.__rootElt=null;
H.destroy.call(this)
},__initializeChildren:function(N){var O=this;
this.__rootElt.children(".rf-tr-nd").each(function(){O.addChild(new C.ui.TreeNode(this,N))
})
},__addLastNodeClass:function(){if(this.__rootElt.next("div").length==0){this.__rootElt.addClass("rf-tr-nd-last")
}},__getNodeContainer:function(){return this.__rootElt.find(" > .rf-trn:first")
},__getHandle:function(){return this.__getNodeContainer().find(" > .rf-trn-hnd:first")
},__getContent:function(){return this.__getNodeContainer().find(" > .rf-trn-cnt:first")
},__getIcons:function(){return this.__getContent().find(" > .rf-trn-ico")
},getParent:function(){return this.__parent
},setParent:function(N){this.__parent=N
},addChild:function(P,N){var O;
if(typeof N!="undefined"){O=N
}else{O=this.__children.length
}this.__children.splice(O,0,P);
P.setParent(this)
},removeChild:function(Q){if(this.__children.length){var N=this.__children.indexOf(Q);
if(N!=-1){var O=this.__children.splice(N,1);
if(O){for(var P=0;
P<O.length;
P++){O[P].setParent(undefined)
}}}}},__clearChildren:function(){for(var N=0;
N<this.__children.length;
N++){this.__children[N].setParent(undefined)
}this.__children=new Array()
},isExpanded:function(){return !this.isLeaf()&&this.__rootElt.hasClass("rf-tr-nd-exp")
},isCollapsed:function(){return !this.isLeaf()&&this.__rootElt.hasClass("rf-tr-nd-colps")
},isLeaf:function(){return this.__rootElt.hasClass("rf-tr-nd-lf")
},__canBeToggled:function(){return !this.isLeaf()&&!this.__rootElt.hasClass("rf-tr-nd-exp-nc")&&!this.__loading
},toggle:function(){if(!this.__canBeToggled()){return 
}if(this.isCollapsed()){this.expand()
}else{this.collapse()
}},__updateClientToggleStateInput:function(N){if(!this.__clientToggleStateInput){this.__clientToggleStateInput=F("<input type='hidden' />").appendTo(this.__rootElt).attr({name:this.getId()+E})
}this.__clientToggleStateInput.val(N.toString())
},__fireBeforeToggleEvent:function(){return C.Event.callHandler(this.__rootElt,"beforetoggle")
},__fireToggleEvent:function(){C.Event.callHandler(this.__rootElt,"toggle")
},__makeLoading:function(){this.__loading=true;
this.__getNodeContainer().addClass("rf-trn-ldn")
},__resetLoading:function(){this.__loading=false;
this.__getNodeContainer().removeClass("rf-trn-ldn")
},__changeToggleState:function(P){if(!this.isLeaf()){if(P^this.isExpanded()){if(this.__fireBeforeToggleEvent()===false){return 
}var N=this.getTree();
switch(N.getToggleType()){case"client":this.__rootElt.addClass(J[P?1:0]).removeClass(J[!P?1:0]);
this.__getHandle().addClass(A[P?1:0]).removeClass(A[!P?1:0]);
var O=this.__getIcons();
if(O.length==1){O.addClass(B[P?1:0]).removeClass(B[!P?1:0])
}this.__updateClientToggleStateInput(P);
this.__fireToggleEvent();
break;
case"ajax":case"server":N.__sendToggleRequest(null,this,P);
break
}}}},collapse:function(){this.__changeToggleState(false)
},expand:function(){this.__changeToggleState(true)
},__setSelected:function(O){var N=this.__getContent();
if(O){N.addClass("rf-trn-sel")
}else{N.removeClass("rf-trn-sel")
}this.__selected=O
},isSelected:function(){return this.__selected
},getTree:function(){return this.getParent().getTree()
},getId:function(){return this.__rootElt.attr("id")
}});
var H=C.ui.TreeNode.$super;
C.ui.TreeNode.initNodeByAjax=function(O,Q){var P=F(document.getElementById(O));
var N=Q||{};
var T=P.parent(".rf-tr-nd, .rf-tr");
var U=P.prevAll(".rf-tr-nd").length;
var R=C.$(T[0]);
N.treeId=R.getTree().getId();
var S=new C.ui.TreeNode(P[0],N);
R.addChild(S,U);
var V=R.getTree();
if(V.getSelection().contains(S.getId())){S.__setSelected(true)
}};
C.ui.TreeNode.emitToggleEvent=function(O){var N=document.getElementById(O);
if(!N){return 
}C.$(N).__fireToggleEvent()
};
var M=function(N){return C.$(F(N).closest(".rf-tr"))
};
var K=function(N){return C.$(F(N).closest(".rf-tr-nd"))
};
var G=function(N,O){return N!=M(O)
};
C.ui.Tree=C.ui.TreeNode.extendClass({name:"Tree",init:function(P,N){this.__treeRootElt=F(C.getDomElement(P));
var O={};
O.clientEventHandlers=N.clientEventHandlers||{};
O.treeId=P;
I.constructor.call(this,this.__treeRootElt,O);
this.__toggleType=N.toggleType||"ajax";
this.__selectionType=N.selectionType||"client";
if(N.ajaxSubmitFunction){this.__ajaxSubmitFunction=new Function("event","source","params","complete",N.ajaxSubmitFunction)
}if(N.onbeforeselectionchange){C.Event.bind(this.__treeRootElt,"beforeselectionchange",new Function("event",N.onbeforeselectionchange))
}if(N.onselectionchange){C.Event.bind(this.__treeRootElt,"selectionchange",new Function("event",N.onselectionchange))
}this.__toggleNodeEvent=N.toggleNodeEvent;
if(this.__toggleNodeEvent){this.__treeRootElt.delegate(".rf-trn",this.__toggleNodeEvent,this,this.__nodeToggleActivated)
}if(!this.__toggleNodeEvent||this.__toggleNodeEvent!="click"){this.__treeRootElt.delegate(".rf-trn-hnd","click",this,this.__nodeToggleActivated)
}this.__treeRootElt.delegate(".rf-trn-cnt","mousedown",this,this.__nodeSelectionActivated);
this.__findSelectionInput();
this.__selection=new C.ui.TreeNodeSet(this.__selectionInput.val());
F(document).ready(F.proxy(this.__updateSelectionFromInput,this))
},__findSelectionInput:function(){this.__selectionInput=F(" > .rf-tr-sel-inp",this.__treeRootElt)
},__addLastNodeClass:function(){},destroy:function(){if(this.__toggleNodeEvent){this.__treeRootElt.undelegate(".rf-trn",this.__toggleNodeEvent,this,this.__nodeToggleActivated)
}if(!this.__toggleNodeEvent||this.__toggleNodeEvent!="click"){this.__treeRootElt.undelegate(".rf-trn-hnd","click",this,this.__nodeToggleActivated)
}this.__treeRootElt.undelegate(".rf-trn-cnt","mousedown",this.__nodeSelectionActivated);
this.__treeRootElt=null;
this.__selectionInput=null;
this.__ajaxSubmitFunction=null;
I.destroy.call(this)
},__nodeToggleActivated:function(O){var N=O.data;
if(G(N,this)){return 
}var P=K(this);
P.toggle()
},__nodeSelectionActivated:function(O){var N=O.data;
if(G(N,this)){return 
}var P=K(this);
if(O.ctrlKey){N.__toggleSelection(P)
}else{N.__addToSelection(P)
}},__sendToggleRequest:function(R,O,S){var P=O.getId();
var N={};
N[P+E]=S;
if(this.getToggleType()=="server"){var Q=this.__treeRootElt.closest("form");
C.submitForm(Q,N)
}else{O.__makeLoading();
N[P+D]=S;
this.__ajaxSubmitFunction(R,P,N,function(){var T=C.$(P);
if(T){T.__resetLoading()
}})
}},getToggleType:function(){return this.__toggleType
},getSelectionType:function(){return this.__selectionType
},getTree:function(){return this
},__handleSelectionChange:function(N){var O={oldSelection:this.getSelection().getNodes(),newSelection:N.getNodes()};
if(C.Event.callHandler(this.__treeRootElt,"beforeselectionchange",O)===false){return 
}this.__selectionInput.val(N.getNodeString());
if(this.getSelectionType()=="client"){this.__updateSelection(N)
}else{this.__ajaxSubmitFunction(null,this.getId())
}},__toggleSelection:function(O){var N=this.getSelection().cloneAndToggle(O);
this.__handleSelectionChange(N)
},__addToSelection:function(O){var N=this.getSelection().cloneAndAdd(O);
this.__handleSelectionChange(N)
},__updateSelectionFromInput:function(){this.__findSelectionInput();
this.__updateSelection(new C.ui.TreeNodeSet(this.__selectionInput.val()))
},__updateSelection:function(N){var O=this.getSelection();
O.each(function(){this.__setSelected(false)
});
N.each(function(){this.__setSelected(true)
});
if(O.getNodeString()!=N.getNodeString()){C.Event.callHandler(this.__treeRootElt,"selectionchange",{oldSelection:O.getNodes(),newSelection:N.getNodes()})
}this.__selection=N
},getSelection:function(){return this.__selection
}});
var I=C.ui.Tree.$super;
C.ui.TreeNodeSet=function(){this.init.apply(this,arguments)
};
F.extend(C.ui.TreeNodeSet.prototype,{init:function(N){this.__nodeId=N
},contains:function(N){if(N.getId){return this.__nodeId==N.getId()
}else{return this.__nodeId==N
}},getNodeString:function(){return this.__nodeId
},toString:function(){return this.getNodeString()
},getNodes:function(){if(this.__nodeId){var N=C.$(this.__nodeId);
if(N){return[N]
}else{return null
}}return[]
},cloneAndAdd:function(N){return new C.ui.TreeNodeSet(N.getId())
},cloneAndToggle:function(N){var O;
if(this.contains(N)){O=""
}else{O=N.getId()
}return new C.ui.TreeNodeSet(O)
},each:function(N){F.each(this.getNodes()||[],N)
}})
}(jQuery,RichFaces));