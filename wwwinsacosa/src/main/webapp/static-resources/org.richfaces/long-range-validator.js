RichFaces.csv.addValidator({"long-range":(function(A){return function(B,D,G){var C=typeof D;
if(C!="number"){if(C!="string"){throw A.csv.getMessage(G.customMessage,"LONG_RANGE_VALIDATOR_TYPE",[B,""])
}else{D=$.trim(D);
if(!A.csv.RE_DIGITS.test(D)||(D=parseInt(D,10))==NaN){throw A.csv.getMessage(G.customMessage,"LONG_RANGE_VALIDATOR_TYPE",[B,""])
}}}var F=typeof G.minimum=="number";
var E=typeof G.maximum=="number";
if(E&&D>G.maximum){if(F){throw A.csv.getMessage(G.customMessage,"LONG_RANGE_VALIDATOR_NOT_IN_RANGE",[G.minimum,G.maximum,B])
}else{throw A.csv.getMessage(G.customMessage,"LONG_RANGE_VALIDATOR_MAXIMUM",[G.maximum,B])
}}if(F&&D<G.minimum){if(E){throw A.csv.getMessage(G.customMessage,"LONG_RANGE_VALIDATOR_NOT_IN_RANGE",[G.minimum,G.maximum,B])
}else{throw A.csv.getMessage(G.customMessage,"LONG_RANGE_VALIDATOR_MINIMUM",[G.minimum,B])
}}}
})(window.RichFaces||(window.RichFaces={}))});