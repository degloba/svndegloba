RichFaces.csv.addConverter({"short":(function(B,A){return function(D,E){var C;
D=B.trim(D);
if(!A.csv.RE_DIGITS.test(D)||(C=parseInt(D,10))<-32768||C>32767){throw A.csv.getMessage(E.customMessage,"SHORT_CONVERTER_SHORT",[D,0,E.componentId])
}return C
}
})(jQuery,window.RichFaces||(window.RichFaces={}))});