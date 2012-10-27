RichFaces.csv.addConverter({"boolean":(function(B,A){return function(D,E){var C;
D=B.trim(D).toLowerCase();
C=D=="true"?true:D.length<1?null:false;
return C
}
})(jQuery,window.RichFaces||(window.RichFaces={}))});