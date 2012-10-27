(function(B,A){A.ui=A.ui||{};
A.ui.CollapsibleSubTable=function(F,E,D){this.id=F;
this.stateInput=D.stateInput;
this.optionsInput=D.optionsInput;
this.expandMode=D.expandMode||A.ui.CollapsibleSubTable.MODE_CLNT;
this.eventOptions=D.eventOptions;
this.formId=E;
this.attachToDom()
};
B.extend(A.ui.CollapsibleSubTable,{MODE_AJAX:"ajax",MODE_SRV:"server",MODE_CLNT:"client",collapse:0,expand:1});
A.BaseComponent.extend(A.ui.CollapsibleSubTable);
var C=A.ui.CollapsibleSubTable.$super;
B.extend(A.ui.CollapsibleSubTable.prototype,(function(){var E=function(){return B(document.getElementById(this.id)).parent()
};
var F=function(){return B(document.getElementById(this.stateInput))
};
var I=function(){return B(document.getElementById(this.optionsInput))
};
var G=function(K,J){this.__switchState();
A.ajax(this.id,K,J)
};
var H=function(J){this.__switchState();
B(document.getElementById(this.formId)).submit()
};
var D=function(J){if(this.isExpanded()){this.collapse(J)
}else{this.expand(J)
}};
return{name:"CollapsibleSubTable",switchState:function(K,J){if(this.expandMode==A.ui.CollapsibleSubTable.MODE_AJAX){G.call(this,K,this.eventOptions,J)
}else{if(this.expandMode==A.ui.CollapsibleSubTable.MODE_SRV){H.call(this,J)
}else{if(this.expandMode==A.ui.CollapsibleSubTable.MODE_CLNT){D.call(this,J)
}}}},collapse:function(J){this.setState(A.ui.CollapsibleSubTable.collapse);
E.call(this).hide()
},expand:function(J){this.setState(A.ui.CollapsibleSubTable.expand);
E.call(this).show()
},isExpanded:function(){return(parseInt(this.getState())==A.ui.CollapsibleSubTable.expand)
},__switchState:function(J){var K=this.isExpanded()?A.ui.CollapsibleSubTable.collapse:A.ui.CollapsibleSubTable.expand;
this.setState(K)
},getState:function(){return F.call(this).val()
},setState:function(J){F.call(this).val(J)
},setOption:function(J){I.call(this).val(J)
},getMode:function(){return this.expandMode
},destroy:function(){C.destroy.call(this)
}}
})())
})(jQuery,window.RichFaces);