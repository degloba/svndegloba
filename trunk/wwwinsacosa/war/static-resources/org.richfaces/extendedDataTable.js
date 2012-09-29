(function(B,E){B.utils=B.utils||{};
B.utils.addCSSText=function(H,F){var G=E("<style></style>").attr({type:"text/css",id:F}).appendTo("head");
try{G.html(H)
}catch(I){G[0].styleSheet.cssText=H
}};
B.utils.getCSSRule=function(H){var J=null;
var I=document.styleSheets;
for(var F=0;
!J&&F<I.length;
F++){var K=I[F].cssRules?I[F].cssRules:I[F].rules;
for(var G=0;
!J&&G<K.length;
G++){if(K[G].selectorText&&K[G].selectorText.toLowerCase()==H.toLowerCase()){J=K[G]
}}}return J
};
B.utils.Ranges=function(){this.ranges=[]
};
B.utils.Ranges.prototype={add:function(F){var G=0;
while(G<this.ranges.length&&F>=this.ranges[G++][1]){}G--;
if(this.ranges[G-1]&&F==(this.ranges[G-1][1]+1)){if(F==(this.ranges[G][0]-1)){this.ranges[G-1][1]=this.ranges[G][1];
this.ranges.splice(G,1)
}else{this.ranges[G-1][1]++
}}else{if(this.ranges[G]){if(this.ranges[G]&&F==(this.ranges[G][0]-1)){this.ranges[G][0]--
}else{if(F==(this.ranges[G][1]+1)){this.ranges[G][1]++
}else{if(F<this.ranges[G][1]){this.ranges.splice(G,0,[F,F])
}else{this.ranges.splice(G+1,0,[F,F])
}}}}else{this.ranges.splice(G,0,[F,F])
}}},remove:function(F){var G=0;
while(G<this.ranges.length&&F>this.ranges[G++][1]){}G--;
if(this.ranges[G]){if(F==(this.ranges[G][1])){if(F==(this.ranges[G][0])){this.ranges.splice(G,1)
}else{this.ranges[G][1]--
}}else{if(F==(this.ranges[G][0])){this.ranges[G][0]++
}else{this.ranges.splice(G+1,0,[F+1,this.ranges[G][1]]);
this.ranges[G][1]=F-1
}}}},clear:function(){this.ranges=[]
},contains:function(F){var G=0;
while(G<this.ranges.length&&F>=this.ranges[G][0]){if(F>=this.ranges[G][0]&&F<=this.ranges[G][1]){return true
}else{G++
}}return false
},toString:function(){var F=new Array(this.ranges.length);
for(var G=0;
G<this.ranges.length;
G++){F[G]=this.ranges[G].join()
}return F.join(";")
}};
var A="rf-edt-c-";
var C=20;
B.ui=B.ui||{};
B.ui.ExtendedDataTable=B.BaseComponent.extendClass({name:"ExtendedDataTable",ranges:new B.utils.Ranges(),resizeData:{},idOfReorderingColumn:"",newWidths:{},timeoutId:null,init:function(I,G,F,H){D.constructor.call(this,I);
this.rowCount=G;
this.ajaxFunction=F;
this.options=H||{};
this.element=this.attachToDom();
this.dragElement=document.getElementById(I+":d");
this.reorderElement=document.getElementById(I+":r");
this.reorderMarkerElement=document.getElementById(I+":rm");
this.widthInput=document.getElementById(I+":wi");
this.selectionInput=document.getElementById(I+":si");
this.header=E(this.element).children(".rf-edt-hdr");
this.headerCells=this.header.find(".rf-edt-hdr-c");
this.footerCells=E(this.element).children(".rf-edt-ftr").find(".rf-edt-ftr-c");
this.resizerHolders=this.header.find(".rf-edt-rsz-cntr");
this.frozenHeaderPartElement=document.getElementById(I+":frozenHeader");
this.frozenColumnCount=this.frozenHeaderPartElement?this.frozenHeaderPartElement.firstChild.rows[0].cells.length:0;
this.scrollElement=document.getElementById(I+":footer");
E(document).ready(E.proxy(this.initialize,this));
E(window).bind("resize",E.proxy(this.updateLayout,this));
E(this.scrollElement).bind("scroll",E.proxy(this.updateScrollPosition,this));
this.bindHeaderHandlers();
E(this.element).bind("rich:onajaxcomplete",E.proxy(this.ajaxComplete,this))
},getColumnPosition:function(H){var F;
for(var G=0;
G<this.headerCells.length;
G++){if(H==this.headerCells[G].className.match(new RegExp(A+"([^\\W]*)"))[1]){F=G
}}return F
},setColumnPosition:function(K,F){var J="";
var H;
for(var G=0;
G<this.headerCells.length;
G++){var I=this.headerCells[G].className.match(new RegExp(A+"([^\\W]*)"))[1];
if(G==F){if(H){J+=I+","+K+","
}else{J+=K+","+I+","
}}else{if(K!=I){J+=I+","
}else{H=true
}}}this.ajaxFunction(null,{"rich:columnsOrder":J})
},setColumnWidth:function(H,F){F=F+"px";
B.utils.getCSSRule("."+A+H).style.width=F;
this.newWidths[H]=F;
var G=new Array();
for(var H in this.newWidths){G.push(H+":"+this.newWidths[H])
}this.widthInput.value=G.toString();
this.updateLayout();
this.adjustResizers();
this.ajaxFunction()
},filter:function(H,I,F){if(typeof (I)=="undefined"||I==null){I=""
}var G={};
G[this.id+"rich:filtering"]=H+":"+I+":"+F;
this.ajaxFunction(null,G)
},clearFiltering:function(){this.filter("","",true)
},sort:function(I,G,F){if(typeof (G)=="string"){G=G.toLowerCase()
}var H={};
H[this.id+"rich:sorting"]=I+":"+G+":"+F;
this.ajaxFunction(null,H)
},clearSorting:function(){this.sort("","",true)
},destroy:function(){E(window).unbind("resize",this.updateLayout);
E(B.getDomElement(this.id+":st")).remove();
D.destroy.call(this)
},bindHeaderHandlers:function(){this.header.find(".rf-edt-rsz").bind("mousedown",E.proxy(this.beginResize,this));
this.headerCells.bind("mousedown",E.proxy(this.beginReorder,this))
},updateLayout:function(){this.headerCells.height("auto");
var L=0;
this.headerCells.each(function(){if(this.clientHeight>L){L=this.clientHeight
}});
this.headerCells.height(L+"px");
this.footerCells.height("auto");
var G=0;
this.footerCells.each(function(){if(this.clientHeight>G){G=this.clientHeight
}});
this.footerCells.height(G+"px");
this.normalPartStyle.width="auto";
var J=this.frozenHeaderPartElement?this.frozenHeaderPartElement.offsetWidth:0;
var I=Math.max(0,this.element.clientWidth-J);
if(I){if(this.parts.width()>I){this.normalPartStyle.width=I+"px"
}this.normalPartStyle.display="block";
this.scrollElement.style.overflowX="";
if(this.scrollElement.clientWidth<this.scrollElement.scrollWidth&&this.scrollElement.scrollHeight==this.scrollElement.offsetHeight){this.scrollElement.style.overflowX="scroll"
}var K=this.scrollElement.firstChild.offsetHeight-this.scrollElement.clientHeight;
if(K){this.scrollElement.style.height=this.scrollElement.offsetHeight+K
}}else{this.normalPartStyle.display="none"
}var F=this.element.clientHeight;
var H=this.element.firstChild;
while(H&&(!H.nodeName||H.nodeName.toUpperCase()!="TABLE")){if(H.nodeName&&H.nodeName.toUpperCase()=="DIV"&&H!=this.bodyElement){F-=H.offsetHeight
}H=H.nextSibling
}if(this.bodyElement.offsetHeight>F||!this.contentElement){this.bodyElement.style.height=F+"px"
}},adjustResizers:function(){var H=this.scrollElement?this.scrollElement.scrollLeft:0;
var G=this.element.clientWidth-3;
var F=0;
for(;
F<this.frozenColumnCount;
F++){if(G>0){this.resizerHolders[F].style.display="none";
this.resizerHolders[F].style.display="";
G-=this.resizerHolders[F].offsetWidth
}if(G<=0){this.resizerHolders[F].style.display="none"
}}H-=3;
for(;
F<this.resizerHolders.length;
F++){if(G>0){this.resizerHolders[F].style.display="none";
if(H>0){this.resizerHolders[F].style.display="";
H-=this.resizerHolders[F].offsetWidth;
if(H>0){this.resizerHolders[F].style.display="none"
}else{G+=H
}}else{this.resizerHolders[F].style.display="";
G-=this.resizerHolders[F].offsetWidth
}}if(G<=0){this.resizerHolders[F].style.display="none"
}}},updateScrollPosition:function(){if(this.scrollElement){var F=this.scrollElement.scrollLeft;
this.parts.each(function(){this.scrollLeft=F
})
}this.adjustResizers()
},initialize:function(){this.bodyElement=document.getElementById(this.id+":b");
this.bodyElement.tabIndex=-1;
this.normalPartStyle=B.utils.getCSSRule("div.rf-edt-cnt").style;
var F=E(this.bodyElement);
this.contentElement=F.children("div:not(.rf-edt-ndt):first")[0];
if(this.contentElement){this.spacerElement=this.contentElement.firstChild;
this.dataTableElement=this.contentElement.lastChild;
this.tbodies=E(document.getElementById(this.id+":tbf")).add(document.getElementById(this.id+":tbn"));
this.rows=this.tbodies[0].rows.length;
this.rowHeight=this.dataTableElement.offsetHeight/this.rows;
if(this.rowCount!=this.rows){this.contentElement.style.height=(this.rowCount*this.rowHeight)+"px"
}F.bind("scroll",E.proxy(this.bodyScrollListener,this));
if(this.options.selectionMode!="none"){this.tbodies.bind("click",E.proxy(this.selectionClickListener,this));
F.bind(window.opera?"keypress":"keydown",E.proxy(this.selectionKeyDownListener,this));
this.initializeSelection()
}}else{this.spacerElement=null;
this.dataTableElement=null
}this.parts=E(this.element).find(".rf-edt-cnt, .rf-edt-ftr-cnt");
this.updateLayout();
this.updateScrollPosition()
},drag:function(F){E(this.dragElement).setPosition({left:Math.max(this.resizeData.left+C,F.pageX)});
return false
},beginResize:function(F){var G=F.currentTarget.parentNode.className.match(new RegExp(A+"([^\\W]*)"))[1];
this.resizeData={id:G,left:E(F.currentTarget).parent().offset().left};
this.dragElement.style.height=this.element.offsetHeight+"px";
E(this.dragElement).setPosition({top:E(this.element).offset().top,left:F.pageX});
this.dragElement.style.display="block";
E(document).bind("mousemove",E.proxy(this.drag,this));
E(document).one("mouseup",E.proxy(this.endResize,this));
return false
},endResize:function(G){E(document).unbind("mousemove",this.drag);
this.dragElement.style.display="none";
var F=Math.max(C,G.pageX-this.resizeData.left);
this.setColumnWidth(this.resizeData.id,F)
},reorder:function(F){E(this.reorderElement).setPosition(F,{offset:[5,5]});
this.reorderElement.style.display="block";
return false
},beginReorder:function(F){if(!E(F.target).is("a, img, :input")){this.idOfReorderingColumn=F.currentTarget.className.match(new RegExp(A+"([^\\W]*)"))[1];
E(document).bind("mousemove",E.proxy(this.reorder,this));
this.headerCells.bind("mouseover",E.proxy(this.overReorder,this));
E(document).one("mouseup",E.proxy(this.cancelReorder,this));
return false
}},overReorder:function(G){if(this.idOfReorderingColumn!=G.currentTarget.className.match(new RegExp(A+"([^\\W]*)"))[1]){var F=E(G.currentTarget);
var H=F.offset();
E(this.reorderMarkerElement).setPosition({top:H.top+F.height(),left:H.left-5});
this.reorderMarkerElement.style.display="block";
F.one("mouseout",E.proxy(this.outReorder,this));
F.one("mouseup",E.proxy(this.endReorder,this))
}},outReorder:function(F){this.reorderMarkerElement.style.display="";
E(F.currentTarget).unbind("mouseup",this.endReorder)
},endReorder:function(F){this.reorderMarkerElement.style.display="";
E(F.currentTarget).unbind("mouseout",this.outReorder);
var I=F.currentTarget.className.match(new RegExp(A+"([^\\W]*)"))[1];
var H="";
var G=this;
this.headerCells.each(function(){var J=this.className.match(new RegExp(A+"([^\\W]*)"))[1];
if(J==I){H+=G.idOfReorderingColumn+","+I+","
}else{if(J!=G.idOfReorderingColumn){H+=J+","
}}});
this.ajaxFunction(F,{"rich:columnsOrder":H})
},cancelReorder:function(F){E(document).unbind("mousemove",this.reorder);
this.headerCells.unbind("mouseover",this.overReorder);
this.reorderElement.style.display="none"
},loadData:function(G){var F=Math.round((this.bodyElement.scrollTop+this.bodyElement.clientHeight/2)/this.rowHeight-this.rows/2);
if(F<=0){F=0
}else{F=Math.min(this.rowCount-this.rows,F)
}this.ajaxFunction(G,{"rich:clientFirst":F})
},bodyScrollListener:function(F){if(this.timeoutId){window.clearTimeout(this.timeoutId);
this.timeoutId=null
}if(Math.max(F.currentTarget.scrollTop-this.rowHeight,0)<this.spacerElement.offsetHeight||Math.min(F.currentTarget.scrollTop+this.rowHeight+F.currentTarget.clientHeight,F.currentTarget.scrollHeight)>this.spacerElement.offsetHeight+this.dataTableElement.offsetHeight){var G=this;
this.timeoutId=window.setTimeout(function(H){G.loadData(H)
},1000)
}},showActiveRow:function(){if(this.bodyElement.scrollTop>this.activeIndex*this.rowHeight+this.spacerElement.offsetHeight){this.bodyElement.scrollTop=Math.max(this.bodyElement.scrollTop-this.rowHeight,0)
}else{if(this.bodyElement.scrollTop+this.bodyElement.clientHeight<(this.activeIndex+1)*this.rowHeight+this.spacerElement.offsetHeight){this.bodyElement.scrollTop=Math.min(this.bodyElement.scrollTop+this.rowHeight,this.bodyElement.scrollHeight-this.bodyElement.clientHeight)
}}},selectRow:function(F){this.ranges.add(F);
for(var G=0;
G<this.tbodies.length;
G++){E(this.tbodies[G].rows[F]).addClass("rf-edt-r-sel")
}},deselectRow:function(F){this.ranges.remove(F);
for(var G=0;
G<this.tbodies.length;
G++){E(this.tbodies[G].rows[F]).removeClass("rf-edt-r-sel")
}},setActiveRow:function(F){if(typeof this.activeIndex=="number"){for(var G=0;
G<this.tbodies.length;
G++){E(this.tbodies[G].rows[this.activeIndex]).removeClass("rf-edt-r-act")
}}this.activeIndex=F;
for(var G=0;
G<this.tbodies.length;
G++){E(this.tbodies[G].rows[this.activeIndex]).addClass("rf-edt-r-act")
}},resetShiftRow:function(){if(typeof this.shiftIndex=="number"){for(var F=0;
F<this.tbodies.length;
F++){E(this.tbodies[F].rows[this.shiftIndex]).removeClass("rf-edt-r-sht")
}}this.shiftIndex=null
},setShiftRow:function(F){this.resetShiftRow();
this.shiftIndex=F;
if(typeof F=="number"){for(var G=0;
G<this.tbodies.length;
G++){E(this.tbodies[G].rows[this.shiftIndex]).addClass("rf-edt-r-sht")
}}},initializeSelection:function(){this.ranges.clear();
var F=this.selectionInput.value.split("|");
this.activeIndex=F[1]||null;
this.shiftIndex=F[2]||null;
this.selectionFlag=null;
var H=this.tbodies[0].rows;
for(var G=0;
G<H.length;
G++){var I=E(H[G]);
if(I.hasClass("rf-edt-r-sel")){this.ranges.add(I[0].rowIndex)
}if(I.hasClass("rf-edt-r-act")){this.activeIndex=I[0].rowIndex
}if(I.hasClass("rf-edt-r-sht")){this.shiftIndex=I[0].rowIndex
}}this.writeSelection()
},writeSelection:function(){this.selectionInput.value=[this.ranges,this.activeIndex,this.shiftIndex,this.selectionFlag].join("|")
},selectRows:function(F){if(typeof F=="number"){F=[F,F]
}var H;
var G=0;
for(;
G<F[0];
G++){if(this.ranges.contains(G)){this.deselectRow(G);
H=true
}}for(;
G<=F[1];
G++){if(!this.ranges.contains(G)){this.selectRow(G);
H=true
}}for(;
G<this.rows;
G++){if(this.ranges.contains(G)){this.deselectRow(G);
H=true
}}this.selectionFlag=typeof this.shiftIndex=="string"?this.shiftIndex:"x";
return H
},processSlectionWithShiftKey:function(G){if(this.shiftIndex==null){this.setShiftRow(this.activeIndex!=null?this.activeIndex:G)
}var F;
if("u"==this.shiftIndex){F=[0,G]
}else{if("d"==this.shiftIndex){F=[G,this.rows-1]
}else{if(G>=this.shiftIndex){F=[this.shiftIndex,G]
}else{F=[G,this.shiftIndex]
}}}return this.selectRows(F)
},onbeforeselectionchange:function(F){return !this.options.onbeforeselectionchange||this.options.onbeforeselectionchange.call(this.element,F)!==false
},onselectionchange:function(G,F,H){if(!G.shiftKey){this.resetShiftRow()
}if(this.activeIndex!=F){this.setActiveRow(F);
this.showActiveRow()
}if(H){this.writeSelection();
if(this.options.onselectionchange){this.options.onselectionchange.call(this.element,G)
}}},selectionClickListener:function(G){if(!this.onbeforeselectionchange(G)){return 
}var I;
if(G.shiftKey||G.ctrlKey){if(window.getSelection){window.getSelection().removeAllRanges()
}else{if(document.selection){document.selection.empty()
}}}var H=G.target;
while(this.tbodies.index(H.parentNode)==-1){H=H.parentNode
}var F=H.rowIndex;
if(this.options.selectionMode=="single"||(this.options.selectionMode!="multipleKeyboardFree"&&!G.shiftKey&&!G.ctrlKey)){I=this.selectRows(F)
}else{if(this.options.selectionMode=="multipleKeyboardFree"||(!G.shiftKey&&G.ctrlKey)){if(this.ranges.contains(F)){this.deselectRow(F)
}else{this.selectRow(F)
}I=true
}else{I=this.processSlectionWithShiftKey(F)
}}this.onselectionchange(G,F,I)
},selectionKeyDownListener:function(G){if(G.ctrlKey&&this.options.selectionMode!="single"&&(G.keyCode==65||G.keyCode==97)&&this.onbeforeselectionchange(G)){this.selectRows([0,rows]);
this.selectionFlag="a";
this.onselectionchange(G,this.activeIndex,true);
G.preventDefault()
}else{var F;
if(G.keyCode==38){F=-1
}else{if(G.keyCode==40){F=1
}}if(F!=null&&this.onbeforeselectionchange(G)){if(typeof this.activeIndex=="number"){F+=this.activeIndex;
if(F>=0&&F<this.rows){var H;
if(this.options.selectionMode=="single"||(!G.shiftKey&&!G.ctrlKey)){H=this.selectRows(F)
}else{if(G.shiftKey){H=this.processSlectionWithShiftKey(F)
}}this.onselectionchange(G,F,H)
}}G.preventDefault()
}}},ajaxComplete:function(F,G){if(G.reinitializeHeader){this.bindHeaderHandlers()
}else{this.selectionInput=document.getElementById(this.id+":si");
if(G.reinitializeBody){this.rowCount=G.rowCount;
this.initialize()
}else{if(this.options.selectionMode!="none"){this.initializeSelection()
}}if(this.spacerElement){this.spacerElement.style.height=(G.first*this.rowHeight)+"px"
}}}});
var D=B.ui.ExtendedDataTable.$super
}(window.RichFaces,jQuery));