(function(C,B){B.ui=B.ui||{};
B.ui.Popup=function(F,E){D.constructor.call(this,F);
this.options=C.extend({},A,E);
this.positionOptions={type:this.options.positionType,from:this.options.jointPoint,to:this.options.direction,offset:this.options.positionOffset};
this.popup=C(document.getElementById(F));
this.visible=this.options.visible;
this.attachTo=this.options.attachTo;
this.attachToBody=this.options.attachToBody;
this.positionType=this.options.positionType;
this.positionOffset=this.options.positionOffset
};
B.BaseComponent.extend(B.ui.Popup);
var D=B.ui.Popup.$super;
var A={visible:false};
C.extend(B.ui.Popup.prototype,{name:"popup",show:function(E){if(!this.visible){if(this.attachToBody){this.parentElement=this.popup.parent().get(0);
document.body.appendChild(this.popup.get(0))
}this.visible=true
}this.popup.setPosition(E||{id:this.attachTo},this.positionOptions).show()
},hide:function(){if(this.visible){this.popup.hide();
this.visible=false;
if(this.attachToBody&&this.parentElement){this.parentElement.appendChild(this.popup.get(0));
this.parentElement=null
}}},isVisible:function(){return this.visible
},getId:function(){return this.id
},destroy:function(){if(this.attachToBody&&this.parentElement){this.parentElement.appendChild(this.popup.get(0));
this.parentElement=null
}}})
})(jQuery,window.RichFaces);