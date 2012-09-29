(function(C,B){B.ui=B.ui||{};
function A(E){this.comp=E
}A.prototype={exec:function(F,E){if(E.switchMode=="server"){return this.execServer(F,E)
}else{if(E.switchMode=="ajax"){return this.execAjax(F,E)
}else{if(E.switchMode=="client"){return this.execClient(F,E)
}else{B.log.error("SwitchItems.exec : unknown switchMode ("+this.comp.switchMode+")")
}}}},execServer:function(G,E){if(G){var F=G.__leave();
if(!F){return false
}}this.__setActiveItem(E.getName());
B.submitForm(this.__getParentForm());
return false
},execAjax:function(G,E){var F=C.extend({},this.comp.options.ajax,{});
this.__setActiveItem(E.getName());
B.ajax(this.comp.id,null,F);
if(G){this.__setActiveItem(G.getName())
}return false
},execClient:function(G,E){if(G){var F=G.__leave();
if(!F){return false
}}this.__setActiveItem(E.getName());
E.__enter();
this.comp.__fireItemChange(G,E);
return true
},__getParentForm:function(){return C(B.getDomElement(this.comp.id)).parents("form:first")
},__setActiveItem:function(E){B.getDomElement(this.__getValueInputId()).value=E;
this.comp.activeItem=E
},__getValueInputId:function(){return this.comp.id+"-value"
}};
B.ui.TogglePanel=B.BaseComponent.extendClass({name:"TogglePanel",init:function(F,E){D.constructor.call(this,F);
this.attachToDom();
this.items=[];
this.options=C.extend(this.options,E||{});
this.activeItem=this.options.activeItem;
this.__addUserEventHandler("itemchange");
this.__addUserEventHandler("beforeitemchange")
},getSelectItem:function(){return this.activeItem
},switchToItem:function(F){var E=this.getNextItem(F);
if(E==null){B.log.warn("TogglePanel.switchToItems("+F+"): item with name '"+F+"' not found");
return false
}var H=this.__getItemByName(this.getSelectItem());
var G=this.__fireBeforeItemChange(H,E);
if(!G){B.log.warn("TogglePanel.switchToItems("+F+"): switch has been canceled by beforeItemChange event");
return false
}return this.__itemsSwitcher().exec(H,E)
},getNextItem:function(F){if(F){var E=this.__ITEMS_META_NAMES[F];
if(E){return this.__getItem(E(this))
}else{return this.__getItemByName(F)
}}else{return this.__getItemByName(this.nextItem())
}},onCompleteHandler:function(E){var G=this.__getItemByName(this.activeItem);
var F=this.__getItemByName(E);
this.__itemsSwitcher().execClient(G,F)
},getItems:function(){return this.items
},getItemsNames:function(){var F=[];
for(var E=0;
E<this.items.length;
E++){F.push(this.items[E].getName())
}return F
},nextItem:function(F){var E=this.__getItemIndex(F||this.activeItem);
if(E==-1){return null
}return this.__getItemName(E+1)
},firstItem:function(){return this.__getItemName(0)
},lastItem:function(){return this.__getItemName(this.items.length-1)
},prevItem:function(F){var E=this.__getItemIndex(F||this.activeItem);
if(!this.options.cycledSwitching&&E<1){return null
}return this.__getItemName(E-1)
},__itemsSwitcher:function(){return new A(this)
},__ITEMS_META_NAMES:(function(){function E(F,I,H){var G=I;
while((!F.items[G]||F.items[G].disabled)&&G<F.items.length&&G>0){G+=H
}return G
}return{"@first":function(F){return E(F,0,1)
},"@prev":function(F){return E(F,parseInt(F.__getItemIndex(F.activeItem))-1,-1)
},"@next":function(F){return E(F,parseInt(F.__getItemIndex(F.activeItem))+1,1)
},"@last":function(F){return E(F,F.items.length-1,-1)
}}
})(),__getItemIndex:function(G){var F;
for(var E=0;
E<this.items.length;
E++){F=this.items[E];
if(!F.disabled&&F.getName()===G){return E
}}B.log.info("TogglePanel.getItemIndex: item with name '"+G+"' not found");
return -1
},__addUserEventHandler:function(E){var F=this.options["on"+E];
if(F){B.Event.bindById(this.id,E,F)
}},__getItem:function(E){if(this.options.cycledSwitching){var F=this.items.length;
return this.items[(F+E)%F]
}else{if(E>=0&&E<this.items.length){return this.items[E]
}else{return null
}}},__getItemByName:function(E){return this.__getItem(this.__getItemIndex(E))
},__getItemName:function(E){var F=this.__getItem(E);
if(F==null){return null
}return F.getName()
},__fireItemChange:function(F,E){return new B.Event.fireById(this.id,"itemchange",{id:this.id,oldItem:F,newItem:E})
},__fireBeforeItemChange:function(F,E){return B.Event.fireById(this.id,"beforeitemchange",{id:this.id,oldItem:F,newItem:E})
}});
var D=B.ui.TogglePanel.$super
})(jQuery,RichFaces);