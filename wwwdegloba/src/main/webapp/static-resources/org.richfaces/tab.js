(function(B,A){A.ui=A.ui||{};
A.ui.Tab=A.ui.TogglePanelItem.extendClass({name:"Tab",init:function(E,D){C.constructor.call(this,E,D);
this.index=D.index;
this.getTogglePanel().getItems()[this.index]=this;
A.Event.bindById(this.id+":header:active","click",this.__onHeaderClick,this);
A.Event.bindById(this.id+":header:inactive","click",this.__onHeaderClick,this)
},__onHeaderClick:function(D){this.getTogglePanel().switchToItem(this.getName())
},__header:function(E){var D=B(A.getDomElement(this.id+":header"));
if(E){return B(A.getDomElement(this.id+":header:"+E))
}return D
},__content:function(){if(!this.__content_){this.__content_=B(A.getDomElement(this.id))
}return this.__content_
},__enter:function(){this.__content().show();
this.__header("inactive").hide();
this.__header("active").show();
return this.__fireEnter()
},__fireLeave:function(){return A.Event.fireById(this.id+":content","leave")
},__fireEnter:function(){return A.Event.fireById(this.id+":content","enter")
},__addUserEventHandler:function(E){var F=this.options["on"+E];
if(F){var D=A.Event.bindById(this.id+":content",E,F)
}},getHeight:function(D){if(D||!this.__height){this.__height=B(A.getDomElement(this.id)).outerHeight(true)
}return this.__height
},__leave:function(){var D=this.__fireLeave();
if(!D){return false
}this.__content().hide();
this.__header("active").hide();
this.__header("inactive").show();
return true
},destroy:function(){var D=this.getTogglePanel();
if(D&&D.getItems&&D.getItems()[this.index]){delete D.getItems()[this.index]
}A.Event.unbindById(this.id);
A.Event.unbindById(this.id+":header:active");
A.Event.unbindById(this.id+":header:inactive");
C.destroy.call(this)
}});
var C=A.ui.Tab.$super
})(jQuery,RichFaces);