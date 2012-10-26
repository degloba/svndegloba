(function(B,A){A.ui=A.ui||{};
A.ui.CollapsiblePanel=A.ui.TogglePanel.extendClass({name:"CollapsiblePanel",init:function(E,D){A.ui.TogglePanel.call(this,E,D);
this.switchMode=D.switchMode;
this.__addUserEventHandler("beforeswitch");
this.__addUserEventHandler("switch");
this.options.cycledSwitching=true;
var C=this;
B(document.getElementById(this.id)).ready(function(){A.Event.bindById(C.id+":header","click",C.__onHeaderClick,C);
new RichFaces.ui.CollapsiblePanelItem(C.id+":content",{index:0,togglePanelId:C.id,switchMode:C.switchMode,name:"true"}),new RichFaces.ui.CollapsiblePanelItem(C.id+":empty",{index:1,togglePanelId:C.id,switchMode:C.switchMode,name:"false"})
})
},switchPanel:function(C){this.switchToItem(C||"@next")
},__onHeaderClick:function(){this.switchToItem("@next")
},__fireItemChange:function(D,C){return new A.Event.fireById(this.id,"switch",{id:this.id,isExpanded:C.getName()})
},__fireBeforeItemChange:function(D,C){return A.Event.fireById(this.id,"beforeswitch",{id:this.id,isExpanded:C.getName()})
}})
})(jQuery,RichFaces);