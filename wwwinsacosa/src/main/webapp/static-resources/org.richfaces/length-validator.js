RichFaces.csv.addValidator({length:(function(A){return function(B,C,D){if(D.maximum&&C.length>D.maximum){throw A.csv.getMessage(D.customMessage,"LENGTH_VALIDATOR_MAXIMUM",[D.maximum,B])
}if(D.minimum&&C.length<D.minimum){throw A.csv.getMessage(D.customMessage,"LENGTH_VALIDATOR_MINIMUM",[D.minimum,B])
}}
})(window.RichFaces||(window.RichFaces={}))});