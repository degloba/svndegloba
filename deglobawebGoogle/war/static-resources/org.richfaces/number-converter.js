RichFaces.csv.addConverter({number:(function(B,A){return function(D,E){var C;
D=B.trim(D);
if(isNaN(D)){throw A.csv.getMessage(E.customMessage,"NUMBER_CONVERTER_NUMBER",[D,0,E.componentId])
}else{C=parseInt(D,10)
}return C
}
})(jQuery,window.RichFaces||(window.RichFaces={}))});