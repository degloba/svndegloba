(function(B,A){A.ui=A.ui||{};
A.ui.TogglePanelItem=A.BaseComponent.extendClass({name:"TogglePanelItem",init:function(E,D){C.constructor.call(this,E);
this.attachToDom(this.id);
this.options=B.extend(this.options,D||{});
this.name=this.options.name;
this.togglePanelId=this.options.togglePanelId;
this.switchMode=this.options.switchMode;
this.disabled=this.options.disabled||false;
this.index=D.index;
this.getTogglePanel().getItems()[this.index]=this;
this.__addUserEventHandler("enter");
this.__addUserEventHandler("leave")
},getName:function(){return this.options.name
},getTogglePanel:function(){return A.$(this.togglePanelId)
},isSelected:function(){return this.getName()==this.getTogglePanel().getSelectItem()
},__addUserEventHandler:function(D){var E=this.options["on"+D];
if(E){A.Event.bindById(this.id,D,E)
}},__enter:function(){A.getDomElement(this.id).style.display="block";
return this.__fireEnter()
},__leave:function(){var D=this.__fireLeave();
if(!D){return false
}A.getDomElement(this.id).style.display="none";
return true
},__fireLeave:function(){return A.Event.fireById(this.id,"leave")
},__fireEnter:function(){return A.Event.fireById(this.id,"enter")
},destroy:function(){var D=this.getTogglePanel();
if(D){delete D.getItems()[this.index]
}C.destroy.call(this)
}});
var C=A.ui.TogglePanelItem.$super
})(jQuery,RichFaces);