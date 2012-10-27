(function(C,B){B.ui=B.ui||{};
B.ui.InplaceSelect=function(G,E){var F=C.extend({},A,E);
D.constructor.call(this,G,F);
this.getInput().bind("click",C.proxy(this.__clickHandler,this));
F.attachTo=G;
F.scrollContainer=C(document.getElementById(G+"Items")).parent()[0];
this.popupList=new B.ui.PopupList(G+"List",this,F);
this.items=F.items;
this.selValueInput=C(document.getElementById(G+"selValue"));
this.initialValue=this.selValueInput.val();
this.list=C(document.getElementById(G+"List"));
this.list.bind("mousedown",C.proxy(this.__onListMouseDown,this));
this.list.bind("mouseup",C.proxy(this.__onListMouseUp,this));
this.openOnEdit=F.openOnEdit;
this.saveOnSelect=F.saveOnSelect;
this.savedIndex=-1;
this.inputItem=C(document.getElementById(G+"Input"));
this.inputItemWidth=this.inputItem.width();
this.inputWidthDefined=E.inputWidth!==undefined
};
B.ui.InplaceInput.extend(B.ui.InplaceSelect);
var D=B.ui.InplaceSelect.$super;
var A={defaultLabel:"",saveOnSelect:true,openOnEdit:true,showControl:false,itemCss:"rf-is-opt",selectItemCss:"rf-is-sel",listCss:"rf-is-lst-cord",noneCss:"rf-is-none",editCss:"rf-is-fld-cntr",changedCss:"rf-is-chng"};
C.extend(B.ui.InplaceSelect.prototype,(function(){return{name:"inplaceSelect",defaultLabelClass:"rf-is-dflt-lbl",getName:function(){return this.name
},getNamespace:function(){return this.namespace
},onshow:function(){D.onshow.call(this);
if(this.openOnEdit){this.__showPopup()
}},onhide:function(){this.__hidePopup()
},showPopup:function(){this.isSaved=false;
this.element.addClass(this.editCss);
this.editContainer.removeClass(this.noneCss);
this.editState=true;
this.scrollElements=B.Event.bindScrollEventHandlers(this.id,this.__scrollHandler,this);
this.__setInputFocus();
this.onfocus();
this.__showPopup()
},__showPopup:function(){this.popupList.show();
this.__hideLabel()
},__hidePopup:function(){this.popupList.hide();
this.__showLabel()
},__selectItemByValue:function(G){var F;
for(var E=0;
E<this.items.length;
E++){F=this.items[E];
if(F.value==G){this.popupList.__selectByIndex(E);
return 
}}this.popupList.resetSelection()
},onsave:function(){var F=this.popupList.currentSelectItem();
if(F){var E=this.popupList.getSelectedItemIndex();
if(this.items[E].label==this.__getValue()){this.savedIndex=E;
var G=this.getItemValue(F);
this.saveItemValue(G);
this.popupList.__selectByIndex(this.savedIndex)
}else{this.__selectItemByValue(this.getValue())
}}},oncancel:function(){var E=this.popupList.getItemByIndex(this.savedIndex);
if(E){var F=this.getItemValue(E);
this.saveItemValue(F);
this.popupList.__selectByIndex(this.savedIndex)
}else{this.saveItemValue(this.initialValue);
this.__selectItemByValue(this.initialValue)
}},onblur:function(E){this.__hidePopup();
D.onblur.call(this)
},onfocus:function(E){if(!this.__isFocused()){this.__setFocused(true);
this.focusValue=this.selValueInput.val();
this.invokeEvent.call(this,"focus",document.getElementById(this.id),E)
}},processItem:function(F){var E=this.getItemLabel(F);
this.__setValue(E);
this.__setInputFocus();
this.__hidePopup();
if(this.saveOnSelect){this.save()
}this.invokeEvent.call(this,"selectitem",document.getElementById(this.id))
},getItemValue:function(F){var E=C(F).attr("id");
var G;
C.each(this.items,function(){if(this.id==E){G=this.value;
return false
}});
return G
},saveItemValue:function(E){this.selValueInput.val(E)
},getItemLabel:function(G){var F=C(G).attr("id");
var E;
C.each(this.items,function(){if(this.id==F){E=this.label;
return false
}});
return E
},__isValueChanged:function(){return(this.focusValue!=this.selValueInput.val())
},__keydownHandler:function(F){var E;
if(F.keyCode){E=F.keyCode
}else{if(F.which){E=F.which
}}if(this.popupList.isVisible()){switch(E){case B.KEYS.DOWN:F.preventDefault();
this.popupList.__selectNext();
this.__setInputFocus();
break;
case B.KEYS.UP:F.preventDefault();
this.popupList.__selectPrev();
this.__setInputFocus();
break;
case B.KEYS.RETURN:F.preventDefault();
this.popupList.__selectCurrent();
this.__setInputFocus();
return false;
break
}}D.__keydownHandler.call(this,F)
},__blurHandler:function(E){if(this.saveOnSelect||!this.isMouseDown){if(this.isEditState()){this.timeoutId=window.setTimeout(C.proxy(function(){this.onblur(E)
},this),200)
}}else{this.__setInputFocus();
this.isMouseDown=false
}},__clickHandler:function(E){this.__showPopup()
},__onListMouseDown:function(E){this.isMouseDown=true
},__onListMouseUp:function(E){this.isMouseDown=false;
this.__setInputFocus()
},__showLabel:function(E){this.label.show();
this.editContainer.css("position","absolute");
this.inputItem.width(this.inputItemWidth)
},__hideLabel:function(E){this.label.hide();
this.editContainer.css("position","static");
if(!this.inputWidthDefined){this.inputItem.width(this.label.width())
}},getValue:function(){return this.selValueInput.val()
},setValue:function(G){var F;
for(var E=0;
E<this.items.length;
E++){F=this.items[E];
if(F.value==G){this.__setValue(F.label);
this.popupList.__selectByIndex(E);
this.save();
break
}}},destroy:function(){this.popupList.destroy();
this.popupList=null;
D.destroy.call(this)
}}
})())
})(jQuery,window.RichFaces);