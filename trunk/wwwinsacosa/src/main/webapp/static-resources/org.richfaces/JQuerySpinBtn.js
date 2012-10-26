var sbjQuery=jQuery;
sbjQuery.fn.SpinButton=function(A){return this.each(function(){this.spinCfg={min:A&&!isNaN(parseFloat(A.min))?Number(A.min):null,max:A&&!isNaN(parseFloat(A.max))?Number(A.max):null,step:A&&A.step?Number(A.step):1,page:A&&A.page?Number(A.page):10,upClass:A&&A.upClass?A.upClass:"up",downClass:A&&A.downClass?A.downClass:"down",reset:A&&A.reset?A.reset:this.value,delay:A&&A.delay?Number(A.delay):500,interval:A&&A.interval?Number(A.interval):100,_btn_width:20,_btn_height:12,_direction:null,_delay:null,_repeat:null,digits:A&&A.digits?Number(A.digits):1};
this.adjustValue=function(G){var F=this.value.toLowerCase();
if(F=="am"){this.value="PM";
return 
}else{if(F=="pm"){this.value="AM";
return 
}}F=(isNaN(this.value)?this.spinCfg.reset:Number(this.value))+Number(G);
if(this.spinCfg.min!==null){F=(F<this.spinCfg.min?(this.spinCfg.max!=null?this.spinCfg.max:this.spinCfg.min):F)
}if(this.spinCfg.max!==null){F=(F>this.spinCfg.max?(this.spinCfg.min!=null?this.spinCfg.min:this.spinCfg.max):F)
}var H=String(F);
while(H.length<this.spinCfg.digits){H="0"+H
}this.value=H
};
sbjQuery(this).keydown(function(F){switch(F.keyCode){case 38:this.adjustValue(this.spinCfg.step);
break;
case 40:this.adjustValue(-this.spinCfg.step);
break;
case 33:this.adjustValue(this.spinCfg.page);
break;
case 34:this.adjustValue(-this.spinCfg.page);
break
}}).bind("mousewheel",function(F){if(F.wheelDelta>=120){this.adjustValue(this.spinCfg.step)
}else{if(F.wheelDelta<=-120){this.adjustValue(-this.spinCfg.step)
}}F.preventDefault()
}).change(function(F){this.adjustValue(0)
});
var D=this;
var C=document.getElementById(this.id+"BtnUp");
sbjQuery(C).mousedown(function(G){var F=function(){D.adjustValue(D.spinCfg.step)
};
F();
D.spinCfg._delay=window.setTimeout(function(){F();
D.spinCfg._repeat=window.setInterval(F,D.spinCfg.interval)
},D.spinCfg.delay);
D.spinCfg._repeater=true;
return false
}).mouseup(function(F){D.spinCfg._repeater=false;
window.clearInterval(D.spinCfg._repeat);
window.clearTimeout(D.spinCfg._delay)
}).dblclick(function(F){if(sbjQuery.browser.msie){D.adjustValue(D.spinCfg.step)
}}).mouseout(function(F){if(D.spinCfg._repeater){D.spinCfg._repeater=false;
window.clearInterval(D.spinCfg._repeat);
window.clearTimeout(D.spinCfg._delay)
}});
var E=document.getElementById(this.id+"BtnDown");
sbjQuery(E).mousedown(function(G){var F=function(){D.adjustValue(-D.spinCfg.step)
};
F();
D.spinCfg._delay=window.setTimeout(function(){F();
D.spinCfg._repeat=window.setInterval(F,D.spinCfg.interval)
},D.spinCfg.delay);
D.spinCfg._repeater=true;
return false
}).mouseup(function(F){D.spinCfg._repeater=false;
window.clearInterval(D.spinCfg._repeat);
window.clearTimeout(D.spinCfg._delay)
}).dblclick(function(F){if(sbjQuery.browser.msie){D.adjustValue(-D.spinCfg.step)
}}).mouseout(function(F){if(D.spinCfg._repeater){D.spinCfg._repeater=false;
window.clearInterval(D.spinCfg._repeat);
window.clearTimeout(D.spinCfg._delay)
}});
if(this.addEventListener){this.addEventListener("DOMMouseScroll",function(F){if(F.detail>0){this.adjustValue(-this.spinCfg.step)
}else{if(F.detail<0){this.adjustValue(this.spinCfg.step)
}}F.preventDefault()
},false)
}});
function B(D,F){var E=D[F],C=document.body;
while((D=D.offsetParent)&&(D!=C)){if(!sbjQuery.browser.msie||(D.currentStyle.position!="relative")){E+=D[F]
}}return E
}};