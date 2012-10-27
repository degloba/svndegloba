(function(F,C){C.utils=C.utils||{};
C.utils.Cache=function(K,J,I,H){this.key=K.toLowerCase();
this.cache={};
this.cache[this.key]=J||[];
this.originalValues=typeof I=="function"?I(J):I||this.cache[this.key];
this.values=D(this.originalValues);
this.useCache=H||B.call(this)
};
var D=function(H){var J=[];
for(var I=0;
I<H.length;
I++){J.push(H[I].toLowerCase())
}return J
};
var B=function(){var H=true;
for(var I=0;
I<this.values.length;
I++){if(this.values[I].indexOf(this.key)!=0){H=false;
break
}}return H
};
var G=function(J,O){J=J.toLowerCase();
var H=[];
if(J.length<this.key.length){return H
}if(this.cache[J]){H=this.cache[J]
}else{var K=typeof O=="function";
var M=this.cache[this.key];
for(var I=0;
I<this.values.length;
I++){var L=this.values[I];
if(K&&O(J,L)){H.push(M[I])
}else{var N=L.indexOf(J);
if(N==0){H.push(M[I])
}}}if((!this.lastKey||J.indexOf(this.lastKey)!=0)&&H.length>0){this.cache[J]=H;
if(H.length==1){this.lastKey=J
}}}return H
};
var E=function(H){return this.originalValues[this.cache[this.key].index(H)]
};
var A=function(H){H=H.toLowerCase();
return this.cache[H]||this.useCache&&H.indexOf(this.key)==0
};
F.extend(C.utils.Cache.prototype,(function(){return{getItems:G,getItemValue:E,isCached:A}
})())
})(jQuery,RichFaces);