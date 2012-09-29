(function(E,B){B.ui=B.ui||{};
B.ui.TooltipMode={client:"client",ajax:"ajax",DEFAULT:"client"};
var A=B.ui.TooltipMode;
var D={jointPoint:"AA",direction:"AA",offset:[10,10],attached:true,mode:A.DEFAULT,hideDelay:0,hideEvent:"mouseleave",showDelay:0,showEvent:"mouseenter",followMouse:true};
var C={exec:function(H,G){var I=H.mode;
if(I==A.ajax){return this.execAjax(H,G)
}else{if(I==A.client){return this.execClient(H,G)
}else{B.log.error("SHOW_ACTION.exec : unknown mode ("+I+")")
}}},execAjax:function(H,G){H.__loading().show();
H.__content().hide();
H.__show(G);
B.ajax(H.id,null,E.extend({},H.options.ajax,{}));
return true
},execClient:function(H,G){H.__show(G);
return H.__fireShow()
}};
B.ui.Tooltip=B.BaseComponent.extendClass({name:"Tooltip",init:function(I,H){F.constructor.call(this,I);
this.namespace="."+B.Event.createNamespace(this.name,this.id);
this.options=E.extend(this.options,D,H||{});
this.attachToDom();
this.mode=this.options.mode;
this.target=this.options.target;
this.shown=false;
this.__addUserEventHandler("hide");
this.__addUserEventHandler("show");
this.__addUserEventHandler("beforehide");
this.__addUserEventHandler("beforeshow");
this.popupId=this.id+":wrp";
this.popup=new B.ui.Popup(this.popupId,{attachTo:this.target,attachToBody:true,positionType:"TOOLTIP",positionOffset:this.options.offset,jointPoint:this.options.jointPoint,direction:this.options.direction});
var G={};
G[this.options.showEvent+this.namespace]=this.__showHandler;
G[this.options.hideEvent+this.namespace]=this.__hideHandler;
B.Event.bindById(this.target,G,this);
if(this.options.hideEvent=="mouseleave"){B.Event.bindById(this.popupId,this.options.hideEvent+this.namespace,this.__hideHandler,this)
}},hide:function(){var G=this;
if(G.hidingTimerHandle){window.clearTimeout(G.hidingTimerHandle);
G.hidingTimerHandle=undefined
}if(this.shown){this.__hide()
}},__hideHandler:function(G){if(G.type=="mouseleave"&&this.__isInside(G.relatedTarget)){return 
}this.hide();
if(this.options.followMouse){B.Event.unbindById(this.target,"mousemove"+this.namespace)
}},__hide:function(){var G=this;
this.__delay(this.options.hideDelay,function(){G.__fireBeforeHide();
G.popup.hide();
G.shown=false;
G.__fireHide()
})
},__mouseMoveHandler:function(G){this.saveShowEvent=G;
if(this.shown){this.popup.show(this.saveShowEvent)
}},__showHandler:function(G){this.show(G);
var H=this;
if(H.options.followMouse){B.Event.bindById(H.target,"mousemove"+H.namespace,H.__mouseMoveHandler,H)
}},show:function(G){var H=this;
if(H.hidingTimerHandle){window.clearTimeout(H.hidingTimerHandle);
H.hidingTimerHandle=undefined
}if(!this.shown){C.exec(this,G)
}},onCompleteHandler:function(){this.__content().show();
this.__loading().hide();
return this.__fireShow()
},__show:function(G){var H=this;
this.__delay(this.options.showDelay,function(){if(!H.options.followMouse){H.saveShowEvent=G
}if(!H.shown){H.__fireBeforeShow();
H.popup.show(H.saveShowEvent)
}H.shown=true
})
},__delay:function(G,I){var H=this;
if(G>0){H.hidingTimerHandle=window.setTimeout(function(){I();
if(H.hidingTimerHandle){window.clearTimeout(H.hidingTimerHandle);
H.hidingTimerHandle=undefined
}},G)
}else{I()
}},__detectAncestorNode:function(G,H){var I=G;
while(I!=null&&I!=H){I=I.parentNode
}return(I!=null)
},__loading:function(){return E(document.getElementById(this.id+":loading"))
},__content:function(){return E(document.getElementById(this.id+":content"))
},__fireHide:function(){return B.Event.fireById(this.id,"hide",{id:this.id})
},__fireShow:function(){return B.Event.fireById(this.id,"show",{id:this.id})
},__fireBeforeHide:function(){return B.Event.fireById(this.id,"beforehide",{id:this.id})
},__fireBeforeShow:function(){return B.Event.fireById(this.id,"beforeshow",{id:this.id})
},__addUserEventHandler:function(G){var H=this.options["on"+G];
if(H){B.Event.bindById(this.id,G+this.namespace,H)
}},__contains:function(H,G){while(G){if(H==G.id){return true
}G=G.parentNode
}return false
},__isInside:function(G){return this.__contains(this.target,G)||this.__contains(this.popupId,G)
},destroy:function(){B.Event.unbindById(this.popupId,this.namespace);
B.Event.unbindById(this.target,this.namespace);
this.popup.destroy();
this.popup=null;
F.destroy.call(this)
}});
var F=B.ui.Tooltip.$super
})(jQuery,RichFaces);