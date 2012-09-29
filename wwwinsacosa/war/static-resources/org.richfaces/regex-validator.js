RichFaces.csv.addValidator({regex:(function(A){return function(B,D,F){if(typeof F.pattern!="string"||F.pattern.length==0){throw A.csv.getMessage(F.customMessage,"REGEX_VALIDATOR_PATTERN_NOT_SET",[])
}var C;
try{C=new RegExp(F.pattern)
}catch(E){throw A.csv.getMessage(F.customMessage,"REGEX_VALIDATOR_MATCH_EXCEPTION",[])
}if(!C.test(D)){throw A.csv.getMessage(F.customMessage,"REGEX_VALIDATOR_NOT_MATCHED",[F.pattern])
}}
})(window.RichFaces||(window.RichFaces={}))});