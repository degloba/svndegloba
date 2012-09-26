(function(C,A){A.ui=A.ui||{};
var B={switchMode:"ajax"};
A.ui.CollapsiblePanelItem=A.ui.TogglePanelItem.extendClass({init:function(E,D){A.ui.TogglePanelItem.call(this,E,C.extend({},B,D));
this.headerClass="rf-cp-hdr-"+this.__state()
},__enter:function(){this.__content().show();
this.__header().addClass(this.headerClass);
return true
},__leave:function(){this.__content().hide();
if(this.options.switchMode=="client"){this.__header().removeClass(this.headerClass)
}return true
},__state:function(){return this.getName()==="true"?"exp":"colps"
},__content:function(){return C(A.getDomElement(this.id))
},__header:function(){return C(A.getDomElement(this.togglePanelId+":header"))
}})
})(jQuery,RichFaces);