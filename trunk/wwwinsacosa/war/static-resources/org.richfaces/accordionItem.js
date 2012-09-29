(function(B,A){A.ui=A.ui||{};
A.ui.AccordionItem=A.ui.TogglePanelItem.extendClass({name:"AccordionItem",init:function(E,D){C.constructor.call(this,E,D);
if(!this.disabled){A.Event.bindById(this.id+":header","click",this.__onHeaderClick,this)
}if(this.isSelected()){var F=this;
B(document).ready(function(){F.__fitToHeight(F.getTogglePanel())
})
}},__onHeaderClick:function(D){this.getTogglePanel().switchToItem(this.getName())
},__header:function(){return B(A.getDomElement(this.id+":header"))
},__content:function(){if(!this.__content_){this.__content_=B(A.getDomElement(this.id+":content"))
}return this.__content_
},__enter:function(){var D=this.getTogglePanel();
if(D.isKeepHeight){this.__content().hide();
this.__fitToHeight(D)
}this.__content().show();
this.__header().addClass("rf-ac-itm-hdr-act").removeClass("rf-ac-itm-hdr-inact");
return this.__fireEnter()
},__fitToHeight:function(D){var G=D.getInnerHeight();
var E=D.getItems();
for(var F in E){G-=E[F].__header().outerHeight()
}this.__content().height(G-20)
},getHeight:function(D){if(D||!this.__height){this.__height=B(A.getDomElement(this.id)).outerHeight(true)
}return this.__height
},__leave:function(){var D=this.__fireLeave();
if(!D){return false
}this.__content().hide();
this.__header().removeClass("rf-ac-itm-hdr-act").addClass("rf-ac-itm-hdr-inact");
return true
}});
var C=A.ui.AccordionItem.$super
})(jQuery,RichFaces);