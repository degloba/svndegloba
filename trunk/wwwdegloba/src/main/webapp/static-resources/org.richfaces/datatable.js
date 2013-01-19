(function(B,A){A.ui=A.ui||{};
A.ui.DataTable=function(E,D){C.constructor.call(this,E);
this.options=B.extend(this.options,D||{});
this.attachToDom()
};
A.BaseComponent.extend(A.ui.DataTable);
var C=A.ui.DataTable.$super;
B.extend(A.ui.DataTable,{SORTING:"rich:sorting",FILTERING:"rich:filtering",SUBTABLE_SELECTOR:".rf-cst"});
B.extend(A.ui.DataTable.prototype,(function(){var D=function(G,F){A.ajax(this.id,G,{parameters:F})
};
var E=function(J,L,H,G){var K={};
var I=this.id+J;
K[I]=(L+":"+(H||"")+":"+G);
var F=this.options.ajaxEventOption;
for(I in F){if(!K[I]){K[I]=F[I]
}}return K
};
return{name:"RichFaces.ui.DataTable",sort:function(G,H,F){D.call(this,null,E.call(this,A.ui.DataTable.SORTING,G,H,F))
},clearSorting:function(){this.sort("","",true)
},filter:function(G,H,F){D.call(this,null,E.call(this,A.ui.DataTable.FILTERING,G,H,F))
},clearFiltering:function(){this.filter("","",true)
},expandAllSubTables:function(){this.invokeOnSubTables("expand")
},collapseAllSubTables:function(){this.invokeOnSubTables("collapse")
},switchSubTable:function(F){this.getSubTable(F).switchState()
},getSubTable:function(F){return A.$(F)
},invokeOnSubTables:function(G){var F=B(document.getElementById(this.id)).children(A.ui.DataTable.SUBTABLE_SELECTOR);
var H=this.invokeOnComponent;
F.each(function(){if(this.firstChild&&this.firstChild[A.RICH_CONTAINER]&&this.firstChild[A.RICH_CONTAINER].component){var I=this.firstChild[A.RICH_CONTAINER].component;
if(I instanceof RichFaces.ui.CollapsibleSubTable){H(I,G)
}}})
},invokeOnSubTable:function(H,G){var F=this.getSubTable(H);
this.invokeOnComponent(F,G)
},invokeOnComponent:function(F,H){if(F){var G=F[H];
if(typeof G=="function"){G.call(F)
}}},destroy:function(){C.destroy.call(this)
}}
})())
})(jQuery,window.RichFaces);