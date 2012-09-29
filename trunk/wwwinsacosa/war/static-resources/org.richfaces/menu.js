(function(C,B){B.ui=B.ui||{};
var A={positionType:"DROPDOWN",direction:"AA",jointPoint:"AA",selectMenuCss:"rf-ddm-sel",unselectMenuCss:"rf-ddm-unsel"};
B.ui.Menu=function(F,E){this.options={};
C.extend(this.options,A,E||{});
D.constructor.call(this,F,this.options);
this.id=F;
this.namespace=this.namespace||"."+B.Event.createNamespace(this.name,this.id);
this.groupList=new Array();
B.Event.bindById(this.id+"_label",this.options.showEvent,C.proxy(this.__showHandler,this),this);
this.element=C(B.getDomElement(this.id));
if(!B.ui.MenuManager){B.ui.MenuManager={}
}this.menuManager=B.ui.MenuManager
};
B.ui.MenuBase.extend(B.ui.Menu);
var D=B.ui.Menu.$super;
C.extend(B.ui.Menu.prototype,B.ui.MenuKeyNavigation);
C.extend(B.ui.Menu.prototype,(function(){return{name:"Menu",initiateGroups:function(E){for(var G in E){var F=E[G].id;
if(null!=F){this.groupList[F]=new RichFaces.ui.MenuGroup(F,{rootMenuId:this.id,onshow:E[G].onshow,onhide:E[G].onhide,horizontalOffset:E[G].horizontalOffset,verticalOffset:E[G].verticalOffset,jointPoint:E[G].jointPoint,direction:E[G].direction})
}}},show:function(E){if(this.menuManager.openedMenu!=this.id){this.menuManager.shutdownMenu();
this.menuManager.addMenuId(this.id);
this.__showPopup(E)
}},hide:function(){this.__hidePopup();
this.menuManager.deletedMenuId()
},select:function(){this.element.removeClass(this.options.unselectMenuCss);
this.element.addClass(this.options.selectMenuCss)
},unselect:function(){this.element.removeClass(this.options.selectMenuCss);
this.element.addClass(this.options.unselectMenuCss)
},__overHandler:function(){D.__overHandler.call(this);
this.select()
},__leaveHandler:function(){D.__leaveHandler.call(this);
this.unselect()
},destroy:function(){this.detach(this.id);
B.Event.unbindById(this.id+"_label",this.options.showEvent);
D.destroy.call(this)
}}
})());
B.ui.MenuManager={openedMenu:null,activeSubMenu:null,addMenuId:function(E){this.openedMenu=E
},deletedMenuId:function(){this.openedMenu=null
},shutdownMenu:function(){if(this.openedMenu!=null){B.$(B.getDomElement(this.openedMenu)).hide()
}this.deletedMenuId()
},setActiveSubMenu:function(E){this.activeSubMenu=E
},getActiveSubMenu:function(){return this.activeSubMenu
}}
})(jQuery,RichFaces);