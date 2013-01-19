(function(B,A){A.ui=A.ui||{};
A.ui.Accordion=A.ui.TogglePanel.extendClass({name:"Accordion",init:function(E,D){C.constructor.call(this,E,D);
this.items=[];
this.isKeepHeight=D.isKeepHeight||false
},getHeight:function(D){if(D||!this.__height){this.__height=B(A.getDomElement(this.id)).outerHeight(true)
}return this.__height
},getInnerHeight:function(D){if(D||!this.__innerHeight){this.__innerHeight=B(A.getDomElement(this.id)).innerHeight(true)
}return this.__innerHeight
},destroy:function(){A.Event.unbindById(this.id,"."+this.namespace);
C.destroy.call(this)
}});
var C=A.ui.Accordion.$super
})(jQuery,RichFaces);