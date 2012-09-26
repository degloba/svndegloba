(function(D,T){T.ui=T.ui||{};
T.ui.Autocomplete=function(d,b,c){this.namespace="."+T.Event.createNamespace(this.name,d);
this.options={};
Y.constructor.call(this,d,d+I.SELECT,b,c);
this.attachToDom();
this.options=D.extend(this.options,X,c);
this.value="";
this.index=null;
this.isFirstAjax=true;
this.lastMouseX=null;
this.lastMouseY=null;
P.call(this);
O.call(this);
L.call(this,"")
};
T.ui.AutocompleteBase.extend(T.ui.Autocomplete);
var Y=T.ui.Autocomplete.$super;
var X={itemClass:"rf-au-itm",selectedItemClass:"rf-au-itm-sel",subItemClass:"rf-au-opt",selectedSubItemClass:"rf-au-opt-sel",autofill:true,minChars:1,selectFirst:true,ajaxMode:true,lazyClientMode:false,isCachedAjax:true,tokens:"",attachToBody:true,filterFunction:undefined};
var I={SELECT:"List",ITEMS:"Items",VALUE:"Value"};
var A=/^[\n\s]*(.*)[\n\s]*$/;
var N=function(b){var c=[];
b.each(function(){c.push(D(this).text().replace(A,"$1"))
});
return c
};
var P=function(){this.useTokens=(typeof this.options.tokens=="string"&&this.options.tokens.length>0);
if(this.useTokens){var b=this.options.tokens.split("").join("\\");
this.REGEXP_TOKEN_LEFT=new RegExp("[^"+b+"]+$","i");
this.REGEXP_TOKEN_RIGHT=new RegExp("["+b+"]","i");
this.hasSpaceToken=this.options.tokens.indexOf(" ")!=-1
}};
var O=function(){var b={};
b["click"+this.namespace]=b["mouseover"+this.namespace]=J;
if(!D.browser.msie&&!D.browser.opera){b["mouseenter"+this.namespace]=M;
b["mouseleave"+this.namespace]=a
}T.Event.bind(T.getDomElement(this.id+I.ITEMS).parentNode,b,this)
};
var a=function(b){T.Event.unbind(T.getDomElement(this.id+I.ITEMS).parentNode,"mousemove"+this.namespace);
this.lastMouseX=null;
this.lastMouseY=null
};
var Q=function(b){this.lastMouseX=b.pageX;
this.lastMouseY=b.pageY
};
var M=function(b){this.lastMouseX=b.pageX;
this.lastMouseY=b.pageY;
T.Event.bind(T.getDomElement(this.id+I.ITEMS).parentNode,"mousemove"+this.namespace,Q,this)
};
var J=function(d){var c=D(d.target).closest("."+this.options.itemClass,d.currentTarget).get(0);
if(c){if(d.type=="mouseover"){if(this.lastMouseX==null||this.lastMouseX!=d.pageX||this.lastMouseY!=d.pageY){var b=this.items.index(c);
F.call(this,d,b)
}}else{this.__onEnter(d);
T.Selection.setCaretTo(T.getDomElement(this.fieldId));
this.__hide(d)
}}};
var L=function(d,b){var e=D(T.getDomElement(this.id+I.ITEMS));
this.items=e.find("."+this.options.itemClass);
var c=e.data();
e.removeData();
if(this.items.length>0){this.cache=new T.utils.Cache((this.options.ajaxMode?d:""),this.items,b||c.componentData||N,!this.options.ajaxMode)
}};
var E=function(){var c=0;
this.items.slice(0,this.index).each(function(){c+=this.offsetHeight
});
var b=D(T.getDomElement(this.id+I.ITEMS)).parent();
if(c<b.scrollTop()){b.scrollTop(c)
}else{c+=this.items.eq(this.index).outerHeight();
if(c-b.scrollTop()>b.innerHeight()){b.scrollTop(c-b.innerHeight())
}}};
var R=function(b,d){if(this.options.autofill&&d.toLowerCase().indexOf(b)==0){var e=T.getDomElement(this.fieldId);
var f=T.Selection.getStart(e);
this.__setInputValue(b+d.substring(b.length));
var c=f+d.length-b.length;
T.Selection.set(e,f,c)
}};
var H=function(e,h){T.getDomElement(this.id+I.VALUE).value=this.value;
var g=this;
var b=e;
var d=function(i){L.call(g,g.value,i.componentData&&i.componentData[g.id]);
if(g.options.lazyClientMode&&g.value.length!=0){G.call(g,g.value)
}if(g.items.length!=0){if(h){(g.focused||g.isMouseDown)&&h.call(g,b)
}else{g.isVisible&&g.options.selectFirst&&F.call(g,b,0)
}}else{g.__hide(b)
}};
var c=function(i){g.__hide(b);
Z.call(g)
};
this.isFirstAjax=false;
var f={};
f[this.id+".ajax"]="1";
T.ajax(this.id,e,{parameters:f,error:c,complete:d})
};
var V=function(){if(this.index!=null){var b=this.items.eq(this.index);
if(b.removeClass(this.options.selectedItemClass).hasClass(this.options.subItemClass)){b.removeClass(this.options.selectedSubItemClass)
}this.index=null
}};
var F=function(e,b,d){if(this.items.length==0||(!d&&b==this.index)){return 
}if(b==null||b==undefined){V.call(this);
return 
}if(d){if(this.index==null){b=0
}else{b=this.index+b
}}if(b<0){b=0
}else{if(b>=this.items.length){b=this.items.length-1
}}if(b==this.index){return 
}V.call(this);
this.index=b;
var c=this.items.eq(this.index);
if(c.addClass(this.options.selectedItemClass).hasClass(this.options.subItemClass)){c.addClass(this.options.selectedSubItemClass)
}E.call(this);
if(e&&e.keyCode!=T.KEYS.BACKSPACE&&e.keyCode!=T.KEYS.DEL&&e.keyCode!=T.KEYS.LEFT&&e.keyCode!=T.KEYS.RIGHT){R.call(this,this.value,S.call(this))
}};
var G=function(c){var b=this.cache.getItems(c,this.options.filterFunction);
this.items=D(b);
D(T.getDomElement(this.id+I.ITEMS)).empty().append(this.items)
};
var Z=function(){D(T.getDomElement(this.id+I.ITEMS)).removeData().empty();
this.items=[]
};
var C=function(c,e,f){F.call(this,c);
var d=(typeof e=="undefined")?this.__getSubValue():e;
var b=this.value;
this.value=d;
if((this.options.isCachedAjax||!this.options.ajaxMode)&&this.cache&&this.cache.isCached(d)){if(b!=d){G.call(this,d)
}if(this.items.length!=0){f&&f.call(this,c)
}else{this.__hide(c)
}if(c.keyCode==T.KEYS.RETURN||c.type=="click"){this.__setInputValue(d)
}else{if(this.options.selectFirst){F.call(this,c,0)
}}}else{if(c.keyCode==T.KEYS.RETURN||c.type=="click"){this.__setInputValue(d)
}if(d.length>=this.options.minChars){if((this.options.ajaxMode||this.options.lazyClientMode)&&b!=d){H.call(this,c,f)
}}else{if(this.options.ajaxMode){Z.call(this);
this.__hide(c)
}}}};
var S=function(){if(this.index!=null){var b=this.items.eq(this.index);
return this.cache.getItemValue(b)
}return undefined
};
var W=function(){if(this.useTokens){var h=T.getDomElement(this.fieldId);
var g=h.value;
var c=T.Selection.getStart(h);
var d=g.substring(0,c);
var e=g.substring(c);
var f=this.REGEXP_TOKEN_LEFT.exec(d);
var b="";
if(f){b=f[0]
}f=e.search(this.REGEXP_TOKEN_RIGHT);
if(f==-1){f=e.length
}b+=e.substring(0,f);
return b
}else{return this.getValue()
}};
var K=function(k){var j=T.getDomElement(this.fieldId);
var d=j.value;
var b=T.Selection.getStart(j);
var f=d.substring(0,b);
var h=d.substring(b);
var i=f.search(this.REGEXP_TOKEN_LEFT);
var g=i!=-1?i:f.length;
i=h.search(this.REGEXP_TOKEN_RIGHT);
var c=i!=-1?i:h.length;
var e=d.substring(0,g)+k;
b=e.length;
j.value=e+h.substring(c);
j.focus();
T.Selection.setCaretTo(j,b);
return j.value
};
var B=function(){if(this.items.length==0){return -1
}var e=D(T.getDomElement(this.id+I.ITEMS)).parent();
var c=e.scrollTop()+e.innerHeight()+this.items[0].offsetTop;
var d;
var b=(this.index!=null&&this.items[this.index].offsetTop<=c)?this.index:0;
for(b;
b<this.items.length;
b++){d=this.items[b];
if(d.offsetTop+d.offsetHeight>c){b--;
break
}}if(b!=this.items.length-1&&b==this.index){c+=this.items[b].offsetTop-e.scrollTop();
for(++b;
b<this.items.length;
b++){d=this.items[b];
if(d.offsetTop+d.offsetHeight>c){break
}}}return b
};
var U=function(){if(this.items.length==0){return -1
}var e=D(T.getDomElement(this.id+I.ITEMS)).parent();
var c=e.scrollTop()+this.items[0].offsetTop;
var d;
var b=(this.index!=null&&this.items[this.index].offsetTop>=c)?this.index-1:this.items.length-1;
for(b;
b>=0;
b--){d=this.items[b];
if(d.offsetTop<c){b++;
break
}}if(b!=0&&b==this.index){c=this.items[b].offsetTop-e.innerHeight();
if(c<this.items[0].offsetTop){c=this.items[0].offsetTop
}for(--b;
b>=0;
b--){d=this.items[b];
if(d.offsetTop<c){b++;
break
}}}return b
};
D.extend(T.ui.Autocomplete.prototype,(function(){return{name:"Autocomplete",__updateState:function(b){var c=this.__getSubValue();
if(this.items.length==0&&this.isFirstAjax){if((this.options.ajaxMode&&c.length>=this.options.minChars)||this.options.lazyClientMode){this.value=c;
H.call(this,b,this.__show);
return true
}}return false
},__getSubValue:W,__updateInputValue:function(b){if(this.useTokens){return K.call(this,b)
}else{return Y.__updateInputValue.call(this,b)
}},__setInputValue:function(b){this.currentValue=this.__updateInputValue(b)
},__onChangeValue:C,__onKeyUp:function(b){F.call(this,b,-1,true)
},__onKeyDown:function(b){F.call(this,b,1,true)
},__onPageUp:function(b){F.call(this,b,U.call(this))
},__onPageDown:function(b){F.call(this,b,B.call(this))
},__onKeyHome:function(b){F.call(this,b,0)
},__onKeyEnd:function(b){F.call(this,b,this.items.length-1)
},__onBeforeShow:function(b){},__onEnter:function(b){var c=S.call(this);
this.__onChangeValue(b,c);
this.invokeEvent("selectitem",T.getDomElement(this.fieldId),b,c)
},__onShow:function(b){if(this.options.selectFirst){F.call(this,b,0)
}},__onHide:function(b){F.call(this,b)
},destroy:function(){this.items=null;
this.cache=null;
var b=T.getDomElement(this.id+I.ITEMS);
D(b).removeData();
T.Event.unbind(b.parentNode,this.namespace);
Y.destroy.call(this)
}}
})());
D.extend(T.ui.Autocomplete,{setData:function(c,b){D(T.getDomElement(c)).data({componentData:b})
}})
})(jQuery,RichFaces);