(function(C,B){B.ui=B.ui||{};
var A={disabled:false,selectable:true,unselectable:false,mode:"client",stylePrefix:"rf-pm-itm",itemStep:20};
var E={exec:function(G){if(G.expanded){var F=G.options.expandEvent==G.options.collapseEvent&&G.options.collapseEvent=="click";
if(F&&G.__fireEvent("beforeswitch")==false){return false
}if(!G.expanded()){if(G.options.expandEvent=="click"&&G.__fireEvent("beforeexpand")==false){return false
}}else{if(G.options.collapseEvent=="click"&&G.__fireEvent("beforecollapse")==false){return false
}}}var H=G.mode;
if(H=="server"){return this.execServer(G)
}else{if(H=="ajax"){return this.execAjax(G)
}else{if(H=="client"||H=="none"){return this.execClient(G)
}else{B.log.error("SELECT_ITEM.exec : unknown mode ("+H+")")
}}}},execServer:function(F){F.__changeState();
var G={};
G[F.__panelMenu().id]=F.itemName;
G[F.id]=F.id;
C.extend(G,F.options.ajax["parameters"]||{});
B.submitForm(this.__getParentForm(F),G);
return false
},execAjax:function(F){var G=F.__changeState();
B.ajax(F.id,null,C.extend({},F.options.ajax,{}));
F.__restoreState(G);
return true
},execClient:function(I){var H=I.__rfPanelMenu();
var G=H.getSelectedItem();
if(G){G.unselect()
}H.selectedItem(I.itemName);
I.__select();
var F=I.__fireSelect();
if(I.__switch){var J=I.mode;
if(J=="client"||J=="none"){I.__switch(!I.expanded())
}}return F
},__getParentForm:function(F){return C(C(B.getDomElement(F.id)).parents("form")[0])
}};
B.ui.PanelMenuItem=B.BaseComponent.extendClass({name:"PanelMenuItem",init:function(H,G){D.constructor.call(this,H);
var F=C(this.attachToDom());
this.options=C.extend(this.options,A,G||{});
this.mode=this.options.mode;
this.itemName=this.options.name;
var I=this.__rfPanelMenu();
I.addItem(this);
this.selectionClass=this.options.stylePrefix+"-sel";
if(!this.options.disabled){var J=this;
if(this.options.selectable){this.__header().bind("click",function(){if(J.__rfPanelMenu().selectedItem()==J.id){if(J.options.unselectable){return J.unselect()
}}else{return J.select()
}})
}}J=this;
C(this.__panelMenu()).ready(function(){J.__renderNestingLevel()
});
this.__addUserEventHandler("select");
this.__addUserEventHandler("beforeselect")
},selected:function(){return this.__header().hasClass(this.selectionClass)
},select:function(){var F=this.__fireBeforeSelect();
if(!F){return false
}return E.exec(this)
},onCompleteHandler:function(){E.execClient(this)
},unselect:function(){var F=this.__rfPanelMenu();
if(F.selectedItem()==this.itemName){F.selectedItem(null)
}else{B.log.warn("You tried to unselect item (name="+this.itemName+") that isn't seleted")
}this.__unselect();
return this.__fireUnselect()
},__rfParentItem:function(){var F=this.__item().parents(".rf-pm-gr")[0];
if(!F){F=this.__item().parents(".rf-pm-top-gr")[0]
}if(!F){F=this.__panelMenu()
}return F?B.$(F):null
},__getNestingLevel:function(){if(!this.nestingLevel){var F=this.__rfParentItem();
if(F&&F.__getNestingLevel){this.nestingLevel=F.__getNestingLevel()+1
}else{this.nestingLevel=0
}}return this.nestingLevel
},__renderNestingLevel:function(){this.__item().find("td").first().css("padding-left",this.options.itemStep*this.__getNestingLevel())
},__panelMenu:function(){return this.__item().parents(".rf-pm")[0]
},__rfPanelMenu:function(){return B.$(this.__panelMenu())
},__changeState:function(){return this.__rfPanelMenu().selectedItem(this.itemName)
},__restoreState:function(F){if(F){this.__rfPanelMenu().selectedItem(F)
}},__item:function(){return C(B.getDomElement(this.id))
},__header:function(){return this.__item()
},__isSelected:function(){return this.__header().hasClass(this.selectionClass)
},__select:function(){this.__header().addClass(this.selectionClass)
},__unselect:function(){this.__header().removeClass(this.selectionClass)
},__fireBeforeSelect:function(){return B.Event.fireById(this.id,"beforeselect",{item:this})
},__fireSelect:function(){return B.Event.fireById(this.id,"select",{item:this})
},__fireUnselect:function(){return B.Event.fireById(this.id,"unselect",{item:this})
},__fireEvent:function(F,G){return this.invokeEvent(F,B.getDomElement(this.id),G,{id:this.id,item:this})
},__addUserEventHandler:function(F){var G=this.options["on"+F];
if(G){B.Event.bindById(this.id,F,G)
}},__rfTopGroup:function(){var F=this.__item().parents(".rf-pm-top-gr")[0];
return F?F:null
},destroy:function(){var F=this.__rfPanelMenu();
if(F){F.deleteItem(this)
}D.destroy.call(this)
}});
var D=B.ui.PanelMenuItem.$super
})(jQuery,RichFaces);