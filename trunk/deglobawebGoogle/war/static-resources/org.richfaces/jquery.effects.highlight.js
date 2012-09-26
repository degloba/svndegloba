(function(A,B){A.effects.highlight=function(C){return this.queue(function(){var E=A(this),D=["backgroundImage","backgroundColor","opacity"],G=A.effects.setMode(E,C.options.mode||"show"),F={backgroundColor:E.css("backgroundColor")};
if(G=="hide"){F.opacity=0
}A.effects.save(E,D);
E.show().css({backgroundImage:"none",backgroundColor:C.options.color||"#ffff99"}).animate(F,{queue:false,duration:C.duration,easing:C.options.easing,complete:function(){(G=="hide"&&E.hide());
A.effects.restore(E,D);
(G=="show"&&!A.support.opacity&&this.style.removeAttribute("filter"));
(C.callback&&C.callback.apply(this,arguments));
E.dequeue()
}})
})
}
})(jQuery);