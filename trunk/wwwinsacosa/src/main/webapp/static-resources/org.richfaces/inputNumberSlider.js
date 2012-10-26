(function(A,B){A.ui=A.ui||{};
A.ui.InputNumberSlider=A.BaseComponent.extendClass({name:"InputNumberSlider",delay:200,maxValue:100,minValue:0,step:1,tabIndex:0,decreaseSelectedClass:"rf-insl-dec-sel",handleSelectedClass:"rf-insl-hnd-sel",increaseSelectedClass:"rf-insl-inc-sel",init:function(H,D,C){$superInputNumberSlider.constructor.call(this,H);
B.extend(this,D);
this.range=this.maxValue-this.minValue;
this.id=H;
this.element=B(this.attachToDom());
this.input=this.element.children(".rf-insl-inp-cntr").children(".rf-insl-inp");
this.track=this.element.children(".rf-insl-trc-cntr").children(".rf-insl-trc");
this.handleContainer=this.track.children("span");
this.handle=this.handleContainer.children(".rf-insl-hnd, .rf-insl-hnd-dis");
this.tooltip=this.element.children(".rf-insl-tt");
var G=Number(this.input.val());
if(isNaN(G)){G=this.minValue
}this.handleContainer.css("display","block");
this.track.css("padding-right",this.handle.width()+"px");
this.__setValue(G,null,true);
if(!this.disabled){this.decreaseButton=this.element.children(".rf-insl-dec");
this.increaseButton=this.element.children(".rf-insl-inc");
this.track[0].tabIndex=this.tabIndex;
for(var F in C){this[F]+=" "+C[F]
}var E=B.proxy(this.__inputHandler,this);
this.input.change(E);
this.input.submit(E);
this.element.mousewheel(B.proxy(this.__mousewheelHandler,this));
this.track.keydown(B.proxy(this.__keydownHandler,this));
this.decreaseButton.mousedown(B.proxy(this.__decreaseHandler,this));
this.increaseButton.mousedown(B.proxy(this.__increaseHandler,this));
this.track.mousedown(B.proxy(this.__mousedownHandler,this))
}},decrease:function(C){this.setValue(this.value-this.step,C)
},increase:function(C){this.setValue(this.value+this.step,C)
},getValue:function(){return this.value
},setValue:function(D,C){if(!this.disabled){this.__setValue(D,C)
}},__setValue:function(D,C,F){if(!isNaN(D)){if(D>this.maxValue){D=this.maxValue
}else{if(D<this.minValue){D=this.minValue
}}if(D!=this.value){this.input.val(D);
var E=100*(D-this.minValue)/this.range;
this.handleContainer.css("padding-left",E+"%");
this.tooltip.text(D);
this.tooltip.setPosition(this.handle,{from:"LT",offset:[0,5]});
this.value=D;
if(this.onchange&&!F){this.onchange.call(this.element[0],C)
}}}},__inputHandler:function(C){var D=Number(this.input.val());
if(isNaN(D)){this.input.val(this.value)
}else{this.__setValue(D,C)
}},__mousewheelHandler:function(E,F,D,C){F=D||C;
if(F>0){this.increase(E)
}else{if(F<0){this.decrease(E)
}}return false
},__keydownHandler:function(C){if(C.keyCode==37){this.__setValue(Number(this.input.val())-this.step,C);
C.preventDefault()
}else{if(C.keyCode==39){this.__setValue(Number(this.input.val())+this.step,C);
C.preventDefault()
}}},__decreaseHandler:function(D){var C=this;
C.decrease(D);
this.intervalId=window.setInterval(function(){C.decrease(D)
},this.delay);
B(document).one("mouseup",true,B.proxy(this.__clearInterval,this));
this.decreaseButton.addClass(this.decreaseSelectedClass);
D.preventDefault()
},__increaseHandler:function(D){var C=this;
C.increase(D);
this.intervalId=window.setInterval(function(){C.increase(D)
},this.delay);
B(document).one("mouseup",B.proxy(this.__clearInterval,this));
this.increaseButton.addClass(this.increaseSelectedClass);
D.preventDefault()
},__clearInterval:function(C){window.clearInterval(this.intervalId);
if(C.data){this.decreaseButton.removeClass(this.decreaseSelectedClass)
}else{this.increaseButton.removeClass(this.increaseSelectedClass)
}},__mousedownHandler:function(D){this.__mousemoveHandler(D);
this.track.focus();
var C=B(document);
C.mousemove(B.proxy(this.__mousemoveHandler,this));
C.one("mouseup",B.proxy(this.__mouseupHandler,this));
this.handle.addClass(this.handleSelectedClass);
this.tooltip.show()
},__mousemoveHandler:function(C){var D=this.range*(C.pageX-this.track.position().left-this.handle.width()/2)/(this.track.width()-this.handle.width())+this.minValue;
D=Math.round(D/this.step)*this.step;
this.__setValue(D,C);
C.preventDefault()
},__mouseupHandler:function(){this.handle.removeClass(this.handleSelectedClass);
this.tooltip.hide();
B(document).unbind("mousemove",this.__mousemoveHandler)
},destroy:function(C){B(document).unbind("mousemove",this.__mousemoveHandler);
$superInputNumberSlider.destroy.call(this)
}});
$superInputNumberSlider=A.ui.InputNumberSlider.$super
}(window.RichFaces,jQuery));