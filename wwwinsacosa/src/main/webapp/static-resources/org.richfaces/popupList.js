(function(F,J){J.ui=J.ui||{};
J.ui.PopupList=function(N,L,K){this.namespace=this.namespace||"."+J.Event.createNamespace(this.name,N);
var M=F.extend({},E,K);
H.constructor.call(this,N,M);
this.selectListener=L;
this.selectItemCss=M.selectItemCss;
this.scrollContainer=F(M.scrollContainer);
this.itemCss=M.itemCss;
this.listCss=M.listCss;
this.index=-1;
this.lastMouseX=null;
this.lastMouseY=null;
B.call(this);
this.__updateItemsList()
};
J.ui.Popup.extend(J.ui.PopupList);
var H=J.ui.PopupList.$super;
var E={attachToBody:true,positionType:"DROPDOWN",positionOffset:[0,0]};
var B=function(){var K={};
K["click"+this.namespace]=I;
K["mouseover"+this.namespace]=G;
if(!F.browser.msie&&!F.browser.opera){K["mouseenter"+this.namespace]=A;
K["mouseleave"+this.namespace]=D
}J.Event.bind(this.popup,K,this)
};
var D=function(K){J.Event.unbind(this.popup,"mousemove"+this.namespace);
this.lastMouseX=null;
this.lastMouseY=null
};
var C=function(K){this.lastMouseX=K.pageX;
this.lastMouseY=K.pageY
};
var A=function(K){this.lastMouseX=K.pageX;
this.lastMouseY=K.pageY;
J.Event.bind(this.popup,"mousemove"+this.namespace,C,this)
};
var G=function(L){if(this.lastMouseX==null||this.lastMouseX!=L.pageX||this.lastMouseY!=L.pageY){var K=this.__getItem(L);
if(K){this.__select(K)
}}};
var I=function(L){var K=this.__getItem(L);
this.processItem(K);
this.__select(K)
};
F.extend(J.ui.PopupList.prototype,(function(){return{name:"popupList",processItem:function(K){if(this.selectListener.processItem&&typeof this.selectListener.processItem=="function"){this.selectListener.processItem(K)
}},selectItem:function(K){if(this.selectListener.selectItem&&typeof this.selectListener.selectItem=="function"){this.selectListener.selectItem(K)
}else{K.addClass(this.selectItemCss)
}this.__scrollToSelectedItem(this)
},unselectItem:function(K){if(this.selectListener.unselectItem&&typeof this.selectListener.unselectItem=="function"){this.selectListener.unselectItem(K)
}else{K.removeClass(this.selectItemCss)
}},currentSelectItem:function(){if(this.items&&this.index!=-1){return this.items[this.index]
}},getSelectedItemIndex:function(){return this.index
},getItemByIndex:function(K){if(K>=0&&K<this.items.length){return this.items[K]
}},resetSelection:function(){var K=this.currentSelectItem();
if(K){this.unselectItem(F(K))
}this.index=-1
},isPopupList:function(K){var L=K.parents("."+this.listCss).attr("id");
return(L&&(L==this.getId()))
},__updateItemsList:function(){return(this.items=this.popup.find("."+this.itemCss))
},__select:function(L){var K=this.items.index(L);
this.__selectByIndex(K)
},__selectByIndex:function(K,M){if(this.items.length==0||(!M&&this.index==K)){return 
}var L;
if(this.index!=-1){L=this.items.eq(this.index);
this.unselectItem(L)
}if(K==undefined){this.index=-1;
return 
}if(M){this.index+=K;
if(this.index<0){this.index=this.items.length-1
}else{if(this.index>=this.items.length){this.index=0
}}}else{if(K<0){K=0
}else{if(K>=this.items.length){K=this.items.length-1
}}this.index=K
}L=this.items.eq(this.index);
this.selectItem(L)
},__selectCurrent:function(){var K;
if(this.items&&this.index>=0){K=this.items.eq(this.index);
this.processItem(K)
}},__selectPrev:function(){this.__selectByIndex(-1,true)
},__selectNext:function(){this.__selectByIndex(1,true)
},__getItem:function(K){return F(K.target).closest("."+this.itemCss,K.currentTarget).get(0)
},__getItems:function(){return this.items
},__setItems:function(K){this.items=K
},__scrollToSelectedItem:function(){if(this.scrollContainer){var L=0;
this.items.slice(0,this.index).each(function(){L+=this.offsetHeight
});
var K=this.scrollContainer;
if(L<K.scrollTop()){K.scrollTop(L)
}else{L+=this.items.get(this.index).offsetHeight;
if(L-K.scrollTop()>K.get(0).clientHeight){K.scrollTop(L-K.innerHeight())
}}}}}
})())
})(jQuery,window.RichFaces);