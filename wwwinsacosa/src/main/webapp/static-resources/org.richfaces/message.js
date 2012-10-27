(function(E,C){C.ui.Message=function(I,H){G.constructor.call(this,I,H,A);
if(this.options.isMessages){this.severityClasses=["rf-msgs-inf","rf-msgs-wrn","rf-msgs-err","rf-msgs-ftl"];
this.summaryClass="rf-msgs-sum";
this.detailClass="rf-msgs-dtl"
}else{this.severityClasses=["rf-msg-inf","rf-msg-wrn","rf-msg-err","rf-msg-ftl"];
this.summaryClass="rf-msg-sum";
this.detailClass="rf-msg-dtl"
}};
C.ui.Base.extend(C.ui.Message);
var G=C.ui.Message.$super;
var A={showSummary:true,level:0,isMessages:false};
var F=function(K,H,M){var J=E(C.getDomElement(this.id));
var L=M.sourceId;
var I=M.message;
if(!this.options.forComponentId){if(!I){var H;
while(H=C.getDomElement(this.id+":"+L)){E(H).remove()
}}else{D.call(this,L,I)
}}else{if(this.options.forComponentId===L){J.empty();
D.call(this,L,I)
}}};
var D=function(H,J){if(J&&J.severity>=this.options.level){var I=E(C.getDomElement(this.id));
var K=E("<span/>",{"class":(this.severityClasses)[J.severity],id:this.id+":"+H});
if(J.summary){if(this.options.tooltip){K.attr("title",J.summary)
}else{if(this.options.showSummary){K.append(E("<span/>",{"class":(this.summaryClass)}).text(J.summary))
}}}if(this.options.showDetail&&J.detail){K.append(E("<span/>",{"class":(this.detailClass)}).text(J.detail))
}I.append(K)
}};
var B=function(){C.Event.bind(window.document,C.Event.MESSAGE_EVENT_TYPE+this.namespace,F,this)
};
E.extend(C.ui.Message.prototype,{name:"Message",__bindEventHandlers:B})
})(jQuery,window.RichFaces||(window.RichFaces={}));