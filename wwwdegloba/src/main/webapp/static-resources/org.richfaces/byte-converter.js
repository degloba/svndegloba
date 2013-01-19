RichFaces.csv.addConverter({"byte":(function(B,A){return function(D,E){var C;
D=B.trim(D);
if(!A.csv.RE_DIGITS.test(D)||(C=parseInt(D,10))<-128||C>127){throw A.csv.getMessage(E.customMessage,"BYTE_CONVERTER_BYTE",[D,0,E.componentId])
}return C
}
})(jQuery,window.RichFaces||(window.RichFaces={}))});