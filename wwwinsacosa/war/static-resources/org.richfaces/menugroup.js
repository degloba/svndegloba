(function(C,B){B.ui=B.ui||{};
var A={showEvent:"mouseenter",direction:"AA",jointPoint:"AA",positionType:"DDMENUGROUP",showDelay:300};
B.ui.MenuGroup=function(F,E){this.id=F;
this.options={};
C.extend(this.options,A,E||{});
D.constructor.call(this,F,this.options);
this.namespace=this.namespace||"."+B.Event.createNamespace(this.name,this.id);
this.attachToDom(F);
B.Event.bindById(this.id,this.options.showEvent,C.proxy(this.__showHandler,this),this);
this.rootMenu=B.$(this.options.rootMenuId);
this.shown=false;
this.jqueryElement=C(this.element)
};
B.ui.MenuBase.extend(B.ui.MenuGroup);
var D=B.ui.MenuGroup.$super;
C.extend(B.ui.MenuGroup.prototype,B.ui.MenuKeyNavigation);
C.extend(B.ui.MenuGroup.prototype,(function(){return{name:"MenuGroup",show:function(){var E=this.id;
if(this.rootMenu.groupList[E]&&!this.shown){this.rootMenu.invokeEvent("groupshow",B.getDomElement(this.rootMenu.id),null);
this.__showPopup();
this.shown=true
}},hide:function(){var E=this.rootMenu;
if(E.groupList[this.id]&&this.shown){E.invokeEvent("grouphide",B.getDomElement(E.id),null);
this.__hidePopup();
this.shown=false
}},select:function(){this.jqueryElement.removeClass(this.options.unselectItemCss);
this.jqueryElement.addClass(this.options.selectItemCss)
},unselect:function(){this.jqueryElement.removeClass(this.options.selectItemCss);
this.jqueryElement.addClass(this.options.unselectItemCss)
},__showHandler:function(){this.select();
D.__showHandler.call(this)
},__leaveHandler:function(){window.clearTimeout(this.showTimeoutId);
this.showTimeoutId=null;
this.hideTimeoutId=window.setTimeout(C.proxy(function(){this.hide()
},this),this.options.hideDelay);
this.unselect()
},destroy:function(){this.detach(this.id);
D.destroy.call(this)
}}
})())
})(jQuery,RichFaces);