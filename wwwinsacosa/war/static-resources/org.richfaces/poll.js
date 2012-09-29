(function(C,B){B.ui=B.ui||{};
var A={};
B.ui.Poll=function(F,E){D.constructor.call(this,F,E);
this.id=F;
this.attachToDom();
this.interval=E.interval||1000;
this.ontimer=E.ontimer;
this.pollElement=B.getDomElement(this.id);
B.ui.pollTracker=B.ui.pollTracker||{};
if(E.enabled){this.startPoll()
}};
B.BaseComponent.extend(B.ui.Poll);
var D=B.ui.Poll.$super;
C.extend(B.ui.Poll.prototype,(function(){return{name:"Poll",startPoll:function(){this.stopPoll();
var E=this;
B.ui.pollTracker[E.id]=window.setTimeout(function(){try{E.ontimer.call(E.pollElement||window);
E.startPoll()
}catch(F){}},E.interval)
},stopPoll:function(){if(B.ui.pollTracker&&B.ui.pollTracker[this.id]){window.clearTimeout(B.ui.pollTracker[this.id]);
delete B.ui.pollTracker[this.id]
}},setZeroRequestDelay:function(E){if(typeof E.requestDelay=="undefined"){E.requestDelay=0
}},destroy:function(){this.stopPoll();
this.detach(this.id);
D.destroy.call(this)
}}
})())
})(jQuery,RichFaces);