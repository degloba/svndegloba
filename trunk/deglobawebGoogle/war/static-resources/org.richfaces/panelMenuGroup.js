(function(C,B){B.ui=B.ui||{};
var A={expanded:false,stylePrefix:"rf-pm-gr",expandEvent:"click",collapseEvent:"click",selectable:false,unselectable:false};
var E={exec:function(G,F){var H=G.mode;
if(H=="server"){return this.execServer(G)
}else{if(H=="ajax"){return this.execAjax(G)
}else{if(H=="client"||H=="none"){return this.execClient(G,F)
}else{B.log.error("EXPAND_ITEM.exec : unknown mode ("+H+")")
}}}},execServer:function(F){F.__changeState();
B.submitForm(this.__getParentForm(F),F.options.ajax["parameters"]||{});
return false
},execAjax:function(G){var F=G.__changeState();
B.ajax(G.id,null,C.extend({},G.options.ajax,{}));
G.__restoreState(F);
return true
},execClient:function(G,F){if(F){G.__expand()
}else{G.__collapse()
}return G.__fireEvent("switch")
},__getParentForm:function(F){return C(C(B.getDomElement(F.id)).parents("form")[0])
}};
B.ui.PanelMenuGroup=B.ui.PanelMenuItem.extendClass({name:"PanelMenuGroup",init:function(H,G){D.constructor.call(this,H,C.extend({},A,G||{}));
this.options.bubbleSelection=this.__rfPanelMenu().options.bubbleSelection;
this.options.expandSingle=this.__rfPanelMenu().options.expandSingle;
if(!this.options.disabled){var I=this;
if(!this.options.selectable){if(this.options.expandEvent==this.options.collapseEvent){this.__header().bind(this.options.expandEvent,function(){I.switchExpantion()
})
}else{this.__header().bind(this.options.expandEvent,function(){if(I.collapsed()){return I.expand()
}});
this.__header().bind(this.options.collapseEvent,function(){if(I.expanded()){return I.collapse()
}})
}}else{if(this.options.expandEvent==this.options.collapseEvent){if(this.options.expandEvent!="click"){this.__header().bind(this.options.expandEvent,function(){I.switchExpantion()
})
}}else{if(this.options.expandEvent!="click"){this.__header().bind(this.options.expandEvent,function(){if(I.collapsed()){return I.expand()
}})
}if(this.options.collapseEvent!="click"){this.__header().bind(this.options.collapseEvent,function(){if(I.expanded()){return I.collapse()
}})
}}}if(this.options.selectable||this.options.bubbleSelection){this.__content().bind("select",function(J){if(I.options.selectable&&I.__isMyEvent(J)){I.expand()
}if(I.options.bubbleSelection&&!I.__isMyEvent(J)){I.__select();
if(!I.expanded()){I.expand()
}}});
this.__content().bind("unselect",function(J){if(I.options.selectable&&I.__isMyEvent(J)){I.collapse()
}if(I.options.bubbleSelection&&!I.__isMyEvent(J)){I.__unselect()
}})
}if(I.options.expandSingle){var F=this;
I.__group().bind("expand",function(J){F.__rfPanelMenu().__collapseGroups(J);
J.stopPropagation()
})
}}},expanded:function(){return this.__getExpandValue()
},expand:function(){if(this.expanded()){return 
}if(!this.__fireEvent("beforeexpand")){return false
}E.exec(this,true)
},__expand:function(){this.__updateStyles(true);
return this.__fireEvent("expand")
},collapsed:function(){return !this.__getExpandValue()
},collapse:function(){if(!this.expanded()){return 
}if(!this.__fireEvent("beforecollapse")){return false
}E.exec(this,false)
},__collapse:function(){this.__updateStyles(false);
this.__childGroups().each(function(F,G){B.$(G.id).__collapse()
});
return this.__fireEvent("collapse")
},__updateStyles:function(F){if(F){this.__content().removeClass("rf-pm-colps").addClass("rf-pm-exp");
this.__header().removeClass("rf-pm-hdr-colps").addClass("rf-pm-hdr-exp");
this.__setExpandValue(true)
}else{this.__content().addClass("rf-pm-colps").removeClass("rf-pm-exp");
this.__header().addClass("rf-pm-hdr-colps").removeClass("rf-pm-hdr-exp");
this.__setExpandValue(false)
}},switchExpantion:function(){var F=this.__fireEvent("beforeswitch");
if(!F){return false
}if(this.expanded()){this.collapse()
}else{this.expand()
}},onCompleteHandler:function(){if(this.options.selectable){D.onCompleteHandler.call(this)
}E.execClient(this,this.expanded())
},__switch:function(F){if(F){this.__expand()
}else{this.__collapse()
}return this.__fireEvent("switch")
},__childGroups:function(){return this.__content().children(".rf-pm-gr")
},__group:function(){return C(B.getDomElement(this.id))
},__header:function(){return C(B.getDomElement(this.id+":hdr"))
},__content:function(){return C(B.getDomElement(this.id+":cnt"))
},__expandValueInput:function(){return document.getElementById(this.id+":expanded")
},__getExpandValue:function(){return this.__expandValueInput().value=="true"
},__setExpandValue:function(H){var F=this.__expandValueInput();
var G=F.value;
F.value=H;
return G
},__changeState:function(){var F={};
F.expanded=this.__setExpandValue(!this.__getExpandValue());
if(this.options.selectable){F.itemName=this.__rfPanelMenu().selectedItem(this.itemName)
}return F
},__restoreState:function(F){if(!F){return 
}if(F.expanded){this.__setExpandValue(F.expanded)
}if(F.itemName){this.__rfPanelMenu().selectedItem(F.itemName)
}},__isMyEvent:function(F){return this.id==F.target.id
},destroy:function(){B.Event.unbindById(this.id,"."+this.namespace);
D.destroy.call(this)
}});
var D=B.ui.PanelMenuGroup.$super
})(jQuery,RichFaces);