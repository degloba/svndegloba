(function(C,B){B.ui=B.ui||{};
var A={expandSingle:true,bubbleSelection:true};
B.ui.PanelMenu=B.BaseComponent.extendClass({name:"PanelMenu",init:function(F,E){D.constructor.call(this,F);
this.items={};
this.attachToDom();
this.options=C.extend(this.options,A,E||{});
this.activeItem=this.__getValueInput().value;
this.nestingLevel=0;
this.__addUserEventHandler("collapse");
this.__addUserEventHandler("expand")
},addItem:function(E){this.items[E.itemName]=E
},deleteItem:function(E){delete this.items[E.itemName]
},getSelectedItem:function(){return this.getItem(this.selectedItem())
},getItem:function(E){return this.items[E]
},selectItem:function(E){},selectedItem:function(I){if(typeof I!="undefined"){var H=this.__getValueInput();
var E=H.value;
this.activeItem=I;
H.value=I;
for(var G in this.items){var F=this.items[G];
if(F.__isSelected()){F.__unselect()
}}return E
}else{return this.activeItem
}},__getValueInput:function(){return document.getElementById(this.id+"-value")
},expandAll:function(){},collapseAll:function(){},expandGroup:function(E){},collapseGroup:function(E){},__panelMenu:function(){return C(B.getDomElement(this.id))
},__childGroups:function(){return this.__panelMenu().children(".rf-pm-top-gr")
},__addUserEventHandler:function(E){var F=this.options["on"+E];
if(F){B.Event.bindById(this.id,E,F)
}},__isActiveItem:function(E){return E.itemName==this.activeItem
},__collapseGroups:function(E){var F=B.$(E.target.id).__rfTopGroup();
this.__childGroups().each(function(G,H){if(H.id!=E.target.id&&(!F||H.id!=F.id)){B.$(H.id).__collapse()
}})
},destroy:function(){B.Event.unbindById(this.id,"."+this.namespace);
D.destroy.call(this)
}});
var D=B.ui.PanelMenu.$super
})(jQuery,RichFaces);