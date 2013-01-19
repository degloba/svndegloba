(function(B,A){A.ui=A.ui||{};
A.ui.MenuKeyNavigation={__updateItemsList:function(){var C=B("."+this.options.listContainerCss+":first",this.popup.popup).find(">."+this.options.itemCss).not("."+this.options.disabledItemCss);
return(this.items=C)
},__selectPrev:function(){if(-1==this.currentSelectedItemIndex){this.currentSelectedItemIndex=this.items.length-1
}else{this.__deselectCurrentItem()
}if(this.currentSelectedItemIndex>0){this.currentSelectedItemIndex--
}else{this.currentSelectedItemIndex=this.items.length-1
}this.__selectCurrentItem()
},__selectNext:function(){if(-1!=this.currentSelectedItemIndex){this.__deselectCurrentItem()
}if(this.currentSelectedItemIndex<this.items.length-1){this.currentSelectedItemIndex++
}else{this.currentSelectedItemIndex=0
}this.__selectCurrentItem()
},__deselectCurrentItem:function(){this.__deselectByIndex(this.currentSelectedItemIndex)
},__selectCurrentItem:function(){this.__selectByIndex(this.currentSelectedItemIndex)
},__selectFirstItem:function(){this.currentSelectedItemIndex=0;
this.__selectCurrentItem()
},__selectByIndex:function(C){if(-1!=C){A.$(this.items.eq(C)).select()
}},__deselectByIndex:function(C){if(C>-1){A.$(this.items.eq(C)).unselect()
}},__openGroup:function(){var C=this.__getItemByIndex(this.currentSelectedItemIndex);
if(this.__isGroup(C)){A.$(C).show();
A.$(C).__selectFirstItem();
this.active=false
}},__closeGroup:function(){var C=this.__getItemByIndex(this.currentSelectedItemIndex);
if(this.__isGroup(C)){A.$(C).__deselectCurrentItem();
A.$(C).hide();
this.active=true
}},__returnToParentMenu:function(){var C=this.__getItemByIndex(this.currentSelectedItemIndex);
var D;
D=this.__getParentMenu()||this.__getParentMenuFromItem(C);
if(D!=null&&this.id!=A.$(D).id){this.hide();
A.$(D).popupElement.focus()
}else{this.hide()
}},__activateMenuItem:function(){var C=this.__getCurrentItem();
if(C){menuItemId=C.attr("id");
this.activateItem(menuItemId)
}},__getItemByIndex:function(C){if(C>-1){return this.items.eq(C)
}else{return null
}},__getCurrentItem:function(){return this.__getItemByIndex(this.currentSelectedItemIndex)
},__keydownHandler:function(D){var C;
if(D.keyCode){C=D.keyCode
}else{if(D.which){C=D.which
}}activeMenu=A.ui.MenuManager.getActiveSubMenu();
if(this.popup.isVisible()){switch(C){case A.KEYS.DOWN:D.preventDefault();
activeMenu.__selectNext();
break;
case A.KEYS.UP:D.preventDefault();
activeMenu.__selectPrev();
break;
case A.KEYS.LEFT:D.preventDefault();
activeMenu.__returnToParentMenu();
break;
case A.KEYS.RIGHT:D.preventDefault();
activeMenu.__openGroup();
break;
case A.KEYS.ESC:D.preventDefault();
activeMenu.__returnToParentMenu();
break;
case A.KEYS.RETURN:D.preventDefault();
activeMenu.__activateMenuItem();
break
}D.stopPropagation()
}}}
})(jQuery,RichFaces);