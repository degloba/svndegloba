(!window.myfaces)?window.myfaces={}:null;
if(!myfaces.oam){myfaces.oam=new function(){this.setHiddenInput=function(G,J,H){var I=document.forms[G];
if(typeof I=="undefined"){I=document.getElementById(G)
}if(typeof I.elements[J]!="undefined"&&(I.elements[J].nodeName=="INPUT"||I.elements[J].nodeName=="input")){I.elements[J].value=H
}else{var F=document.createElement("input");
F.setAttribute("type","hidden");
F.setAttribute("id",J);
F.setAttribute("name",J);
F.setAttribute("value",H);
I.appendChild(F)
}};
this.clearHiddenInput=function(G,F,H){var I=document.forms[G];
if(typeof I=="undefined"){I=document.getElementById(G)
}var J=I.elements[F];
if(typeof J!="undefined"){I.removeChild(J)
}};
this.submitForm=function(S,T,V,O){var Y="clearFormHiddenParams_"+S.replace(/-/g,"$:").replace(/:/g,"_");
if(typeof window[Y]=="function"){window[Y](S)
}var Q=document.forms[S];
if(typeof Q=="undefined"){Q=document.getElementById(S)
}if(myfaces.core.config.autoScroll&&typeof window.getScrolling!="undefined"){myfaces.oam.setHiddenInput(S,"autoScroll",getScrolling())
}if(myfaces.core.config.ieAutoSave){var Z=navigator.userAgent.toLowerCase();
var U=navigator.appVersion;
if(Z.indexOf("msie")!=-1){if(!(Z.indexOf("ppc")!=-1&&Z.indexOf("windows ce")!=-1&&U>=4)){window.external.AutoCompleteSaveForm(Q)
}}}var W=Q.target;
if(V!=null){Q.target=V
}if((typeof O!="undefined")&&O!=null){for(var N=0,P;
(P=O[N]);
N++){myfaces.oam.setHiddenInput(S,P[0],P[1])
}}myfaces.oam.setHiddenInput(S,S+":_idcl",T);
if(Q.onsubmit){var R=Q.onsubmit();
if((typeof R=="undefined")||R){try{Q.submit()
}catch(X){}}}else{try{Q.submit()
}catch(X){}}Q.target=W;
if((typeof O!="undefined")&&O!=null){for(var N=0,P;
(P=O[N]);
N++){myfaces.oam.clearHiddenInput(S,P[0],P[1])
}}myfaces.oam.clearHiddenInput(S,S+":_idcl",T);
return false
}
}
}(!myfaces.core)?myfaces.core={}:null;
(!myfaces.core.config)?myfaces.core.config={}:null;