(function(C,B){B.ui=B.ui||{};
var A={mode:"server",attachToBody:false,showDelay:50,hideDelay:300,verticalOffset:0,horizontalOffset:0,showEvent:"mouseover",positionOffset:[0,0],itemCss:"rf-ddm-itm",selectItemCss:"rf-ddm-itm-sel",unselectItemCss:"rf-ddm-itm-unsel",disabledItemCss:"rf-ddm-itm-dis",labelCss:"rf-ddm-lbl",listCss:"rf-ddm-lst",listContainerCss:"rf-ddm-lst-bg"};
B.ui.MenuBase=function(F,E){D.constructor.call(this,F,E);
this.id=F;
this.namespace=this.namespace||"."+B.Event.createNamespace(this.name,this.id);
this.options={};
C.extend(this.options,A,E||{});
this.attachToDom(F);
this.element=B.getDomElement(this.id);
this.displayed=false;
this.options.attachTo=this.id;
this.options.attachToBody=false;
this.options.positionOffset=[this.options.horizontalOffset,this.options.verticalOffset];
this.popup=new RichFaces.ui.Popup(this.id+"_list",{attachTo:this.id,direction:this.options.direction,jointPoint:this.options.jointPoint,positionType:this.options.positionType,positionOffset:this.options.positionOffset,attachToBody:this.options.attachToBody});
this.selectedGroup=null;
B.Event.bindById(this.id,"mouseenter",C.proxy(this.__overHandler,this),this);
B.Event.bindById(this.id,"mouseleave",C.proxy(this.__leaveHandler,this),this);
this.popupElement=B.getDomElement(this.popup.id);
this.popupElement.tabIndex=-1;
this.__updateItemsList();
B.Event.bind(this.items,"mouseenter",C.proxy(this.__itemMouseEnterHandler,this),this);
this.currentSelectedItemIndex=-1;
var G;
G={};
G["keydown"+this.namespace]=this.__keydownHandler;
B.Event.bind(this.popupElement,G,this)
};
B.BaseComponent.extend(B.ui.MenuBase);
var D=B.ui.MenuBase.$super;
C.extend(B.ui.MenuBase.prototype,(function(){return{name:"MenuBase",show:function(){this.__showPopup()
},hide:function(){this.__hidePopup()
},processItem:function(E){if(E&&E.attr("id")&&!this.__isDisabled(E)&&!this.__isGroup(E)){this.invokeEvent("itemclick",B.getDomElement(this.id),null);
this.hide()
}},activateItem:function(F){var E=C(RichFaces.getDomElement(F));
B.Event.fireById(E.attr("id"),"click")
},__showPopup:function(){if(!this.__isShown()){this.invokeEvent("show",B.getDomElement(this.id),null);
this.popup.show();
this.displayed=true;
B.ui.MenuManager.setActiveSubMenu(B.$(this.element))
}this.popupElement.focus()
},__hidePopup:function(){window.clearTimeout(this.showTimeoutId);
this.showTimeoutId=null;
if(this.__isShown()){this.invokeEvent("hide",B.getDomElement(this.id),null);
this.__closeChildGroups();
this.popup.hide();
this.displayed=false;
this.__deselectCurrentItem();
this.currentSelectedItemIndex=-1;
var E=B.$(this.__getParentMenu());
if(this.id!=E.id){E.popupElement.focus();
B.ui.MenuManager.setActiveSubMenu(E)
}}},__closeChildGroups:function(){var E=0;
var F;
for(E in this.items){F=this.items.eq(E);
if(this.__isGroup(F)){B.$(F).hide()
}}},__getParentMenuFromItem:function(E){var F;
if(E){F=E.parents("div."+this.options.itemCss).has("div."+this.options.listContainerCss).eq(1)
}if(F&&F.length>0){return F
}else{F=E.parents("div."+this.options.labelCss);
if(F&&F.length>0){return F
}else{return null
}}},__getParentMenu:function(){var F=C(this.element).parents("div."+this.options.itemCss).has("div."+this.options.listContainerCss).eq(0);
if(F&&F.length>0){return F
}else{var E=this.items.eq(0);
return this.__getParentMenuFromItem(E)
}},__isGroup:function(E){return E.find("div."+this.options.listCss).length>0
},__isDisabled:function(E){return E.hasClass(this.options.disabledItemCss)
},__isShown:function(){return this.displayed
},__itemMouseEnterHandler:function(F){var E=this.__getItemFromEvent(F);
if(E){if(this.currentSelectedItemIndex!=this.items.index(E)){this.__deselectCurrentItem();
this.currentSelectedItemIndex=this.items.index(E)
}}},__selectItem:function(E){if(!B.$(E).isSelected){B.$(E).select()
}},__getItemFromEvent:function(E){return C(E.target).closest("."+this.options.itemCss,E.currentTarget).eq(0)
},__showHandler:function(E){if(!this.__isShown()){this.showTimeoutId=window.setTimeout(C.proxy(function(){this.show()
},this),this.options.showDelay)
}},__leaveHandler:function(){this.hideTimeoutId=window.setTimeout(C.proxy(function(){this.hide()
},this),this.options.hideDelay)
},__overHandler:function(){window.clearTimeout(this.hideTimeoutId);
this.hideTimeoutId=null
},destroy:function(){this.detach(this.id);
B.Event.unbind(this.popupElement,"keydown"+this.namespace);
this.popup.destroy();
this.popup=null;
D.destroy.call(this)
}}
})())
})(jQuery,RichFaces);