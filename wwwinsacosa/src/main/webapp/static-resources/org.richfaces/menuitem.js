(function(C,B){B.ui=B.ui||{};
var A={itemCss:"rf-ddm-itm",selectItemCss:"rf-ddm-itm-sel",unselectItemCss:"rf-ddm-itm-unsel",labelCss:"rf-ddm-lbl",mode:"server"};
B.ui.MenuItem=function(F,E){this.options={};
C.extend(this.options,A,E||{});
D.constructor.call(this,F);
this.attachToDom(F);
this.element=C(B.getDomElement(F));
B.Event.bindById(this.id,"click",this.__clickHandler,this);
B.Event.bindById(this.id,"mouseenter",this.select,this);
B.Event.bindById(this.id,"mouseleave",this.unselect,this);
this.selected=false
};
B.BaseComponent.extend(B.ui.MenuItem);
var D=B.ui.MenuItem.$super;
C.extend(B.ui.MenuItem.prototype,(function(){return{name:"MenuItem",select:function(){this.element.removeClass(this.options.unselectItemCss);
this.element.addClass(this.options.selectItemCss);
this.selected=true
},unselect:function(){this.element.removeClass(this.options.selectItemCss);
this.element.addClass(this.options.unselectItemCss);
this.selected=false
},activate:function(){this.invokeEvent("click",B.getDomElement(this.id))
},isSelected:function(){return this.selected
},__clickHandler:function(F){if(C(F.target).is(":input:not(:button):not(:reset):not(:submit)")){return 
}var E=this.__getParentMenu();
if(E){E.processItem(this.element)
}this.__submitForm(B.getDomElement(this.id),F,this.options.params)
},__submitForm:function(F,G,I){var E=this.__getParentForm(F);
var H={};
H[F.id]=F.id;
C.extend(H,I||{});
if(this.options.mode=="server"){B.submitForm(E,H)
}if(this.options.mode=="ajax"&&this.options.submitFunction){this.options.submitFunction.call(this,G)
}},__getParentForm:function(E){return C(C(E).parents("form").get(0))
},__getParentMenu:function(){var E=this.element.parents("div."+this.options.labelCss);
if(E&&E.length>0){return B.$(E)
}else{return null
}}}
})())
})(jQuery,RichFaces);