(function(B,A){A.ui=A.ui||{};
A.ui.InputBase=function(F,D){C.constructor.call(this,F);
this.namespace=this.getNamespace()||"."+A.Event.createNamespace(this.getName(),this.getId());
this.namespace=this.namespace||"."+A.Event.createNamespace(this.name,this.id);
this.input=B(document.getElementById(F+"Input"));
this.attachToDom();
var E={};
E[(B.browser.opera||B.browser.mozilla?"keypress":"keydown")+this.namespace]=this.__keydownHandler;
E["blur"+this.namespace]=this.__blurHandler;
E["change"+this.namespace]=this.__changeHandler;
E["focus"+this.namespace]=this.__focusHandler;
A.Event.bind(this.input,E,this)
};
A.BaseComponent.extend(A.ui.InputBase);
var C=A.ui.InputBase.$super;
B.extend(A.ui.InputBase.prototype,(function(){return{name:"inputBase",getName:function(){return this.name
},getNamespace:function(){return this.namespace
},__focusHandler:function(D){},__keydownHandler:function(D){},__blurHandler:function(D){},__changeHandler:function(D){},__setInputFocus:function(){this.input.focus()
},__getValue:function(){return this.input.val()
},__setValue:function(D){this.input.val(D);
if(this.defaultLabelClass){if(D==this.defaultLabel){this.input.addClass(this.defaultLabelClass)
}else{this.input.removeClass(this.defaultLabelClass)
}}},getValue:function(){return this.__getValue()
},setValue:function(D){this.__setValue(D)
},getInput:function(){return this.input
},getId:function(){return this.id
},destroy:function(){A.Event.unbindById(this.input,this.namespace);
this.input=null;
C.destroy.call(this)
}}
})())
})(jQuery,window.RichFaces);