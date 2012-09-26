function toolbarHandlers(C){if(C.id&&C.events){jQuery(".rf-tb-itm",document.getElementById(C.id)).bind(C.events)
}var A=C.groups;
if(A&&A.length>0){var F;
var D;
for(D in A){F=A[D];
if(F){var B=F.ids;
var G;
var E=[];
for(G in B){E.push(document.getElementById(B[G]))
}jQuery(E).bind(F.events)
}}}};