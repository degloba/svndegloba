(function(A){A.Selection=A.Selection||{};
A.Selection.set=function(D,E,B){if(D.setSelectionRange){D.focus();
D.setSelectionRange(E,B)
}else{if(D.createTextRange){var C=D.createTextRange();
C.collapse(true);
C.moveEnd("character",B);
C.moveStart("character",E);
C.select()
}}};
A.Selection.getStart=function(C){if(C.setSelectionRange){return C.selectionStart
}else{if(document.selection&&document.selection.createRange){var B=document.selection.createRange().duplicate();
B.moveEnd("character",C.value.length);
if(B.text==""){return C.value.length
}return C.value.lastIndexOf(B.text)
}}};
A.Selection.getEnd=function(C){if(C.setSelectionRange){return C.selectionEnd
}else{if(document.selection&&document.selection.createRange){var B=document.selection.createRange().duplicate();
B.moveStart("character",-C.value.length);
return B.text.length
}}};
A.Selection.setCaretTo=function(B,C){if(!C){C=B.value.length
}A.Selection.set(B,C,C)
}
})(window.RichFaces||(window.RichFaces={}));