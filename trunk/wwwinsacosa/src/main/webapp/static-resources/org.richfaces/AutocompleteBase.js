(function(D,O){O.ui=O.ui||{};
O.ui.AutocompleteBase=function(T,U,R,S){K.constructor.call(this,T);
this.selectId=U;
this.fieldId=R;
this.options=D.extend({},C,S);
this.namespace=this.namespace||"."+O.Event.createNamespace(this.name,this.selectId);
this.currentValue="";
this.tempValue=this.getValue();
this.isChanged=this.tempValue.length!=0;
B.call(this)
};
O.BaseComponent.extend(O.ui.AutocompleteBase);
var K=O.ui.AutocompleteBase.$super;
var C={changeDelay:8};
var B=function(){var R={};
if(this.options.buttonId){R["mousedown"+this.namespace]=P;
R["mouseup"+this.namespace]=G;
O.Event.bindById(this.options.buttonId,R,this)
}R={};
R["focus"+this.namespace]=M;
R["blur"+this.namespace]=A;
R["click"+this.namespace]=L;
R[(D.browser.opera||D.browser.mozilla?"keypress":"keydown")+this.namespace]=J;
R["change"+this.namespace]=function(S){if(this.focused){S.stopPropagation()
}};
O.Event.bindById(this.fieldId,R,this);
R={};
R["mousedown"+this.namespace]=Q;
R["mouseup"+this.namespace]=G;
O.Event.bindById(this.selectId,R,this)
};
var Q=function(){this.isMouseDown=true
};
var G=function(){O.getDomElement(this.fieldId).focus()
};
var P=function(R){this.isMouseDown=true;
if(this.timeoutId){window.clearTimeout(this.timeoutId);
this.timeoutId=null
}O.getDomElement(this.fieldId).focus();
if(this.isVisible){this.__hide(R)
}else{I.call(this,R)
}};
var M=function(R){if(!this.focused){this.__focusValue=this.getValue();
this.focused=true;
this.invokeEvent("focus",O.getDomElement(this.fieldId),R)
}};
var A=function(R){if(this.isMouseDown){O.getDomElement(this.fieldId).focus();
this.isMouseDown=false
}else{if(!this.isMouseDown){if(this.isVisible){var S=this;
this.timeoutId=window.setTimeout(function(){S.__hide(R)
},200)
}if(this.focused){this.focused=false;
this.invokeEvent("blur",O.getDomElement(this.fieldId),R);
if(this.__focusValue!=this.getValue()){this.invokeEvent("change",O.getDomElement(this.fieldId),R)
}}}}};
var L=function(R){};
var H=function(S){if(this.isChanged){if(this.getValue()==this.tempValue){return 
}}this.isChanged=false;
var T=this.getValue();
var R=T!=this.currentValue;
if(S.keyCode==O.KEYS.LEFT||S.keyCode==O.KEYS.RIGHT||R){if(R){this.currentValue=this.getValue();
this.__onChangeValue(S,undefined,(!this.isVisible?this.__show:undefined))
}else{if(this.isVisible){this.__onChangeValue(S)
}}}};
var I=function(R){if(this.isChanged){this.isChanged=false;
H.call(this,{})
}else{!this.__updateState(R)&&this.__show(R)
}};
var J=function(R){switch(R.keyCode){case O.KEYS.UP:R.preventDefault();
if(this.isVisible){this.__onKeyUp(R)
}break;
case O.KEYS.DOWN:R.preventDefault();
if(this.isVisible){this.__onKeyDown(R)
}else{I.call(this,R)
}break;
case O.KEYS.PAGEUP:if(this.isVisible){R.preventDefault();
this.__onPageUp(R)
}break;
case O.KEYS.PAGEDOWN:if(this.isVisible){R.preventDefault();
this.__onPageDown(R)
}break;
case O.KEYS.HOME:if(this.isVisible){R.preventDefault();
this.__onKeyHome(R)
}break;
case O.KEYS.END:if(this.isVisible){R.preventDefault();
this.__onKeyEnd(R)
}break;
case O.KEYS.RETURN:if(this.isVisible){R.preventDefault();
this.__onEnter(R);
this.__hide(R);
return false
}break;
case O.KEYS.ESC:this.__hide(R);
break;
default:if(!this.options.selectOnly){var S=this;
window.clearTimeout(this.changeTimerId);
this.changeTimerId=window.setTimeout(function(){H.call(S,R)
},this.options.changeDelay)
}break
}};
var N=function(S){if(!this.isVisible){if(this.__onBeforeShow(S)!=false){this.scrollElements=O.Event.bindScrollEventHandlers(this.selectId,this.__hide,this,this.namespace);
var R=O.getDomElement(this.selectId);
if(this.options.attachToBody){this.parentElement=R.parentNode;
document.body.appendChild(R)
}D(R).setPosition({id:this.fieldId},{type:"DROPDOWN"}).show();
this.isVisible=true;
this.__onShow(S)
}}};
var E=function(R){if(this.isVisible){O.Event.unbindScrollEventHandlers(this.scrollElements,this);
this.scrollElements=null;
D(O.getDomElement(this.selectId)).hide();
this.isVisible=false;
if(this.options.attachToBody&&this.parentElement){this.parentElement.appendChild(O.getDomElement(this.selectId));
this.parentElement=null
}this.__onHide(R)
}};
var F=function(R){if(this.fieldId){O.getDomElement(this.fieldId).value=R;
return R
}else{return""
}};
D.extend(O.ui.AutocompleteBase.prototype,(function(){return{name:"AutocompleteBase",showPopup:function(R){if(!this.focused){O.getDomElement(this.fieldId).focus()
}I.call(this,R)
},hidePopup:function(R){this.__hide(R)
},getNamespace:function(){return this.namespace
},getValue:function(){return this.fieldId?O.getDomElement(this.fieldId).value:""
},setValue:function(R){if(R==this.currentValue){return 
}F.call(this,R);
this.isChanged=true
},__updateInputValue:F,__show:N,__hide:E,__onChangeValue:function(R){},__onKeyUp:function(R){},__onKeyDown:function(R){},__onPageUp:function(R){},__onPageDown:function(R){},__onKeyHome:function(R){},__onKeyEnd:function(R){},__onBeforeShow:function(R){},__onShow:function(R){},__onHide:function(R){},destroy:function(){this.parentNode=null;
if(this.scrollElements){O.Event.unbindScrollEventHandlers(this.scrollElements,this);
this.scrollElements=null
}this.options.buttonId&&O.Event.unbindById(this.options.buttonId,this.namespace);
O.Event.unbindById(this.fieldId,this.namespace);
O.Event.unbindById(this.selectId,this.namespace);
K.destroy.call(this)
}}
})())
})(jQuery,RichFaces);