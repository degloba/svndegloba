(function(C,B){B.ui=B.ui||{};
B.ui.InplaceInput=function(J,F){var I=C.extend({},A,F);
D.constructor.call(this,J,I);
this.label=C(document.getElementById(J+"Label"));
var G=this.label.text();
var H=this.__getValue();
this.initialLabel=(G==H)?G:"";
this.useDefaultLabel=G!=H;
this.saveOnBlur=I.saveOnBlur;
this.showControls=I.showControls;
this.getInput().bind("focus",C.proxy(this.__editHandler,this));
if(this.showControls){var E=document.getElementById(J+"Btn");
if(E){E.tabIndex=-1
}this.okbtn=C(document.getElementById(J+"Okbtn"));
this.cancelbtn=C(document.getElementById(J+"Cancelbtn"));
this.okbtn.bind("mousedown",C.proxy(this.__saveBtnHandler,this));
this.cancelbtn.bind("mousedown",C.proxy(this.__cancelBtnHandler,this))
}};
B.ui.InplaceBase.extend(B.ui.InplaceInput);
var D=B.ui.InplaceInput.$super;
var A={defaultLabel:"",saveOnBlur:true,showControl:true,noneCss:"rf-ii-none",readyCss:"rf-ii",editCss:"rf-ii-act",changedCss:"rf-ii-chng"};
C.extend(B.ui.InplaceInput.prototype,(function(){return{name:"inplaceInput",defaultLabelClass:"rf-ii-dflt-lbl",getName:function(){return this.name
},getNamespace:function(){return this.namespace
},__keydownHandler:function(E){this.tabBlur=false;
switch(E.keyCode||E.which){case B.KEYS.ESC:E.preventDefault();
this.cancel();
this.onblur(E);
break;
case B.KEYS.RETURN:E.preventDefault();
this.save();
this.onblur(E);
break;
case B.KEYS.TAB:this.tabBlur=true;
break
}},__blurHandler:function(E){this.onblur(E)
},__isSaveOnBlur:function(){return this.saveOnBlur
},__setInputFocus:function(){this.getInput().unbind("focus",this.__editHandler);
this.getInput().focus()
},__saveBtnHandler:function(E){this.cancelButton=false;
this.save();
this.onblur(E)
},__cancelBtnHandler:function(E){this.cancelButton=true;
this.cancel();
this.onblur(E)
},__editHandler:function(E){D.__editHandler.call(this,E);
this.onfocus(E)
},getLabel:function(){return this.label.text()
},setLabel:function(E){this.label.text(E);
if(E==this.defaultLabel){this.label.addClass(this.defaultLabelClass)
}else{this.label.removeClass(this.defaultLabelClass)
}},isValueChanged:function(){return(this.__getValue()!=this.initialLabel)
},onshow:function(){this.__setInputFocus()
},onhide:function(){if(this.tabBlur){this.tabBlur=false
}else{this.getInput().focus()
}},onfocus:function(E){if(!this.__isFocused()){this.__setFocused(true);
this.focusValue=this.__getValue();
this.invokeEvent.call(this,"focus",document.getElementById(this.id),E)
}},onblur:function(E){if(this.__isFocused()){this.__setFocused(false);
this.invokeEvent.call(this,"blur",document.getElementById(this.id),E);
if(this.isValueSaved()||this.__isSaveOnBlur()){this.save()
}else{this.cancel()
}this.__hide();
if(!this.cancelButton){if(this.__isValueChanged()){this.invokeEvent.call(this,"change",document.getElementById(this.id),E)
}}var F=this;
window.setTimeout(function(){F.getInput().bind("focus",C.proxy(F.__editHandler,F))
},1)
}},__isValueChanged:function(){return(this.focusValue!=this.__getValue())
},__setFocused:function(E){this.focused=E
},__isFocused:function(){return this.focused
},setValue:function(E){this.__setValue(E);
this.save()
}}
})())
})(jQuery,window.RichFaces);