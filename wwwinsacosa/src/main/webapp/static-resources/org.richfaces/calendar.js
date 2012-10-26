(function(F,L){L.ui=L.ui||{};
var B={getControl:function(Q,N,O,P){var M=F.extend({onclick:(O?"RichFaces.$$('Calendar',this)."+O+"("+(P?P:"")+");":"")+"return true;"},N);
return new E("div",M,[new T(Q)])
},getSelectedDateControl:function(O){if(!O.selectedDate||O.options.showApplyButton){return""
}var P=L.calendarUtils.formatDate(O.selectedDate,(O.timeType?O.datePattern:O.options.datePattern),O.options.monthLabels,O.options.monthLabelsShort);
var N="RichFaces.$$('Calendar',this).showSelectedDate(); return true;";
var M=(O.options.disabled?new E("div",{"class":"rf-cal-tl-btn-dis"},[new ET(P)]):new E("div",{"class":"rf-cal-tl-btn",onclick:N},[new ET(P)]));
return M
},getTimeControl:function(P){if(!P.selectedDate||!P.timeType){return""
}var R=L.calendarUtils.formatDate(P.selectedDate,P.timePattern,P.options.monthLabels,P.options.monthLabelsShort);
var Q="jQuery(this).removeClass('rf-cal-btn-press');";
var O="jQuery(this).addClass('rf-cal-btn-press');";
var N="RichFaces.$$('Calendar',this).showTimeEditor();return true;";
var M=P.options.disabled||P.options.readonly?new E("div",{"class":"rf-cal-tl-btn-btn-dis"},[new ET(R)]):new E("div",{"class":"rf-cal-tl-btn rf-cal-tl-btn-hov rf-cal-btn-press",onclick:N,onmouseover:+Q,onmouseout:+O},[new ET(R)]);
return M
},toolButtonAttributes:{className:"rf-cal-tl-btn",onmouseover:"this.className='rf-cal-tl-btn rf-cal-tl-btn-hov'",onmouseout:"this.className='rf-cal-tl-btn'",onmousedown:"this.className='rf-cal-tl-btn rf-cal-tl-btn-hov rf-cal-tl-btn-btn-press'",onmouseup:"this.className='rf-cal-tl-btn rf-cal-tl-btn-hov'"},nextYearControl:function(M){return(!M.calendar.options.disabled?B.getControl(">>",B.toolButtonAttributes,"nextYear"):"")
},previousYearControl:function(M){return(!M.calendar.options.disabled?B.getControl("<<",B.toolButtonAttributes,"prevYear"):"")
},nextMonthControl:function(M){return(!M.calendar.options.disabled?B.getControl(">",B.toolButtonAttributes,"nextMonth"):"")
},previousMonthControl:function(M){return(!M.calendar.options.disabled?B.getControl("<",B.toolButtonAttributes,"prevMonth"):"")
},currentMonthControl:function(N){var O=L.calendarUtils.formatDate(N.calendar.getCurrentDate(),"MMMM, yyyy",N.monthLabels,N.monthLabelsShort);
var M=N.calendar.options.disabled?new E("div",{className:"rf-cal-tl-btn-dis"},[new T(O)]):B.getControl(O,B.toolButtonAttributes,"showDateEditor");
return M
},todayControl:function(M){return(!M.calendar.options.disabled&&M.calendar.options.todayControlMode!="hidden"?B.getControl(M.controlLabels.today,B.toolButtonAttributes,"today"):"")
},closeControl:function(M){return(M.calendar.options.popup?B.getControl(M.controlLabels.close,B.toolButtonAttributes,"close","false"):"")
},applyControl:function(M){return(!M.calendar.options.disabled&&!M.calendar.options.readonly&&M.calendar.options.showApplyButton?B.getControl(M.controlLabels.apply,B.toolButtonAttributes,"close","true"):"")
},cleanControl:function(M){return(!M.calendar.options.disabled&&!M.calendar.options.readonly&&M.calendar.selectedDate?B.getControl(M.controlLabels.clean,B.toolButtonAttributes,"__resetSelectedDate"):"")
},selectedDateControl:function(M){return B.getSelectedDateControl(M.calendar)
},timeControl:function(M){return B.getTimeControl(M.calendar)
},timeEditorFields:function(M){return M.calendar.timePatternHtml
},header:[new E("table",{border:"0",cellpadding:"0",cellspacing:"0",width:"100%"},[new E("tbody",{},[new E("tr",{},[new E("td",{"class":"rf-cal-tl"},[new ET(function(M){return L.calendarTemplates.evalMacro("previousYearControl",M)
})]),new E("td",{"class":"rf-cal-tl"},[new ET(function(M){return L.calendarTemplates.evalMacro("previousMonthControl",M)
})]),new E("td",{"class":"rf-cal-hdr-month"},[new ET(function(M){return L.calendarTemplates.evalMacro("currentMonthControl",M)
})]),new E("td",{"class":"rf-cal-tl"},[new ET(function(M){return L.calendarTemplates.evalMacro("nextMonthControl",M)
})]),new E("td",{"class":"rf-cal-tl"},[new ET(function(M){return L.calendarTemplates.evalMacro("nextYearControl",M)
})]),new E("td",{"class":"rf-cal-tl rf-cal-btn-close",style:function(M){return(this.isEmpty?"display:none;":"")
}},[new ET(function(M){return L.calendarTemplates.evalMacro("closeControl",M)
})])])])])],footer:[new E("table",{border:"0",cellpadding:"0",cellspacing:"0",width:"100%"},[new E("tbody",{},[new E("tr",{},[new E("td",{"class":"rf-cal-tl-ftr",style:function(M){return(this.isEmpty?"display:none;":"")
}},[new ET(function(M){return L.calendarTemplates.evalMacro("selectedDateControl",M)
})]),new E("td",{"class":"rf-cal-tl-ftr",style:function(M){return(this.isEmpty?"display:none;":"")
}},[new ET(function(M){return L.calendarTemplates.evalMacro("cleanControl",M)
})]),new E("td",{"class":"rf-cal-tl-ftr",style:function(M){return(this.isEmpty?"display:none;":"")
}},[new ET(function(M){return L.calendarTemplates.evalMacro("timeControl",M)
})]),new E("td",{"class":"rf-cal-tl-ftr",style:"background-image:none;",width:"100%"},[]),new E("td",{"class":"rf-cal-tl-ftr",style:function(M){return(this.isEmpty?"display:none;":"")+(M.calendar.options.disabled||M.calendar.options.readonly||!M.calendar.options.showApplyButton?"background-image:none;":"")
}},[new ET(function(M){return L.calendarTemplates.evalMacro("todayControl",M)
})]),new E("td",{"class":"rf-cal-tl-ftr",style:function(M){return(this.isEmpty?"display:none;":"")+"background-image:none;"
}},[new ET(function(M){return L.calendarTemplates.evalMacro("applyControl",M)
})])])])])],timeEditorLayout:[new E("table",{id:function(M){return M.calendar.TIME_EDITOR_LAYOUT_ID
},border:"0",cellpadding:"0",cellspacing:"0","class":"rf-cal-timepicker-cnt"},[new E("tbody",{},[new E("tr",{},[new E("td",{"class":"rf-cal-timepicker-inp",colspan:"2",align:"center"},[new ET(function(M){return L.calendarTemplates.evalMacro("timeEditorFields",M)
})])]),new E("tr",{},[new E("td",{"class":"rf-cal-timepicker-ok"},[new E("div",{id:function(M){return M.calendar.TIME_EDITOR_BUTTON_OK
},"class":"rf-cal-time-btn",style:"float:right;",onmousedown:"jQuery(this).addClass('rf-cal-time-btn-press');",onmouseout:"jQuery(this).removeClass('rf-cal-time-btn-press');",onmouseup:"jQuery(this).removeClass('rf-cal-time-btn-press');",onclick:function(M){return"RichFaces.$('"+M.calendar.id+"').hideTimeEditor(true)"
}},[new E("span",{},[new ET(function(M){return M.controlLabels.ok
})])])]),new E("td",{"class":"rf-cal-timepicker-cancel"},[new E("div",{id:function(M){return M.calendar.TIME_EDITOR_BUTTON_CANCEL
},"class":"rf-cal-time-btn",style:"float:left;",onmousedown:"jQuery(this).addClass('rf-cal-time-btn-press');",onmouseout:"jQuery(this).removeClass('rf-cal-time-btn-press');",onmouseup:"jQuery(this).removeClass('rf-cal-time-btn-press');",onclick:function(M){return"RichFaces.$('"+M.calendar.id+"').hideTimeEditor(false)"
}},[new E("span",{},[new ET(function(M){return M.controlLabels.cancel
})])])])])])])],dayList:[new ET(function(M){return M.day
})],weekNumber:[new ET(function(M){return M.weekNumber
})],weekDay:[new ET(function(M){return M.weekDayLabelShort
})]};
var H=function(M){this.calendar=M;
this.monthLabels=M.options.monthLabels;
this.monthLabelsShort=M.options.monthLabelsShort;
this.weekDayLabels=M.options.weekDayLabels;
this.weekDayLabelsShort=M.options.weekDayLabelsShort;
this.controlLabels=M.options.labels
};
F.extend(H.prototype,{nextYearControl:B.nextYearControl,previousYearControl:B.previousYearControl,nextMonthControl:B.nextMonthControl,previousMonthControl:B.previousMonthControl,currentMonthControl:B.currentMonthControl,selectedDateControl:B.selectedDateControl,cleanControl:B.cleanControl,timeControl:B.timeControl,todayControl:B.todayControl,closeControl:B.closeControl,applyControl:B.applyControl,timeEditorFields:B.timeEditorFields});
var C={showWeekDaysBar:true,showWeeksBar:true,datePattern:"MMM d, yyyy",horizontalOffset:0,verticalOffset:0,dayListMarkup:B.dayList,weekNumberMarkup:B.weekNumber,weekDayMarkup:B.weekDay,headerMarkup:B.header,footerMarkup:B.footer,isDayEnabled:function(M){return true
},dayStyleClass:function(M){return""
},showHeader:true,showFooter:true,direction:"AA",jointPoint:"AA",popup:true,boundaryDatesMode:"inactive",todayControlMode:"select",style:"",className:"",disabled:false,readonly:false,enableManualInput:false,showInput:true,resetTimeOnDateSelect:false,style:"z-index: 3;",showApplyButton:false,selectedDate:null,currentDate:null,defaultTime:{hours:12,minutes:0,seconds:0},mode:"client",hidePopupOnScroll:true,defaultLabel:""};
var K={apply:"Apply",today:"Today",clean:"Clean",ok:"OK",cancel:"Cancel",close:"x"};
var I=["change","dateselect","beforedateselect","currentdateselect","beforecurrentdateselect","currentdateselect","clean","complete","collapse","datemouseout","datemouseover","show","hide","timeselect","beforetimeselect"];
var D=function(M){var N=L.getDomElement(this.INPUT_DATE_ID);
if((N.value==this.options.defaultLabel&&!M)||(M==this.options.defaultLabel&&!N.value)){N.value=M;
if(M){F(N).addClass("rf-cal-dflt-lbl")
}else{F(N).removeClass("rf-cal-dflt-lbl")
}}};
var G=function(M){this.isFocused=M.type=="focus";
if(!this.isFocused&&this.isVisible){return 
}D.call(this,(M.type=="focus"?"":this.options.defaultLabel))
};
L.ui.Calendar=function(j,e,S,q){J.constructor.call(this,j);
this.namespace="."+L.Event.createNamespace(this.name,j);
this.options=F.extend(this.options,C,A[e],S,q);
var c=S.labels||{};
for(var r in K){if(!c[r]){c[r]=K[r]
}}this.options.labels=c;
this.popupOffset=[this.options.horizontalOffset,this.options.verticalOffset];
if(!this.options.popup){this.options.showApplyButton=false
}this.options.boundaryDatesMode=this.options.boundaryDatesMode.toLowerCase();
this.hideBoundaryDatesContent=this.options.boundaryDatesMode=="hidden";
this.options.todayControlMode=this.options.todayControlMode.toLowerCase();
this.setTimeProperties();
this.customDayListMarkup=(this.options.dayListMarkup!=B.dayList);
this.currentDate=this.options.currentDate?this.options.currentDate:(this.options.selectedDate?this.options.selectedDate:new Date());
this.currentDate.setDate(1);
this.selectedDate=this.options.selectedDate;
this.todayDate=new Date();
this.firstWeekendDayNumber=6-this.options.firstWeekDay;
this.secondWeekendDayNumber=(this.options.firstWeekDay>0?7-this.options.firstWeekDay:0);
this.calendarContext=new H(this);
this.DATE_ELEMENT_ID=this.id+"DayCell";
this.WEEKNUMBER_BAR_ID=this.id+"WeekNum";
this.WEEKNUMBER_ELEMENT_ID=this.WEEKNUMBER_BAR_ID+"Cell";
this.WEEKDAY_BAR_ID=this.id+"WeekDay";
this.WEEKDAY_ELEMENT_ID=this.WEEKDAY_BAR_ID+"Cell";
this.POPUP_ID=this.id+"Popup";
this.POPUP_BUTTON_ID=this.id+"PopupButton";
this.INPUT_DATE_ID=this.id+"InputDate";
this.EDITOR_ID=this.id+"Editor";
this.EDITOR_SHADOW_ID=this.id+"EditorShadow";
this.TIME_EDITOR_LAYOUT_ID=this.id+"TimeEditorLayout";
this.DATE_EDITOR_LAYOUT_ID=this.id+"DateEditorLayout";
this.EDITOR_LAYOUT_SHADOW_ID=this.id+"EditorLayoutShadow";
this.TIME_EDITOR_BUTTON_OK=this.id+"TimeEditorButtonOk";
this.TIME_EDITOR_BUTTON_CANCEL=this.id+"TimeEditorButtonCancel";
this.DATE_EDITOR_BUTTON_OK=this.id+"DateEditorButtonOk";
this.DATE_EDITOR_BUTTON_CANCEL=this.id+"DateEditorButtonCancel";
this.CALENDAR_CONTENT=this.id+"Content";
this.firstDateIndex=0;
this.daysData={startDate:null,days:[]};
this.days=[];
this.todayCellId=null;
this.todayCellColor="";
this.selectedDateCellId=null;
this.selectedDateCellColor="";
var W="";
this.isVisible=true;
if(this.options.popup==true){W="display:none; position:absolute;";
this.isVisible=false
}var g="RichFaces.$('"+this.id+"').";
var X='<table id="'+this.CALENDAR_CONTENT+'" border="0" cellpadding="0" cellspacing="0" class="rf-cal-extr rf-cal-popup '+this.options.styleClass+'" style="'+W+this.options.style+'" onclick="'+g+'skipEventOnCollapse=true;"><tbody>';
var Q=(this.options.showWeeksBar?"8":"7");
var V=(this.options.optionalHeaderMarkup)?'<tr><td class="rf-cal-hdr-optnl" colspan="'+Q+'" id="'+this.id+'HeaderOptional"></td></tr>':"";
var h=(this.options.optionalFooterMarkup)?'<tr><td class="rf-cal-ftr-optl" colspan="'+Q+'" id="'+this.id+'FooterOptional"></td></tr>':"";
var m=(this.options.showHeader?'<tr><td class="rf-cal-hdr" colspan="'+Q+'" id="'+this.id+'Header"></td></tr>':"");
var d=(this.options.showFooter?'<tr><td class="rf-cal-ftr" colspan="'+Q+'" id="'+this.id+'Footer"></td></tr>':"");
var R="</tbody></table>";
var Z;
var M;
var Y=[];
var P;
var O=this.options.disabled||this.options.readonly?"":'onclick="'+g+'eventCellOnClick(event, this);" onmouseover="'+g+'eventCellOnMouseOver(event, this);" onmouseout="'+g+'eventCellOnMouseOut(event, this);"';
if(this.options.showWeekDaysBar){Y.push('<tr id="'+this.WEEKDAY_BAR_ID+'">');
if(this.options.showWeeksBar){Y.push('<td class="rf-cal-day-lbl"><br/></td>')
}var o=this.options.firstWeekDay;
for(var f=0;
f<7;
f++){P={weekDayLabel:this.options.weekDayLabels[o],weekDayLabelShort:this.options.weekDayLabelsShort[o],weekDayNumber:o,isWeekend:this.isWeekend(f),elementId:this.WEEKDAY_ELEMENT_ID+f,component:this};
var n=this.evaluateMarkup(this.options.weekDayMarkup,P);
if(o==6){o=0
}else{o++
}Z="rf-cal-day-lbl";
if(P.isWeekend){Z+=" rf-cal-holliday-lbl"
}if(f==6){Z+=" rf-cal-right-c"
}Y.push('<td class="'+Z+'" id="'+P.elementId+'">'+n+"</td>")
}Y.push("</tr>\n")
}var l=[];
var b=0;
this.dayCellClassName=[];
for(k=1;
k<7;
k++){M=(k==6?"rf-btm-c ":"");
l.push('<tr id="'+this.WEEKNUMBER_BAR_ID+k+'">');
if(this.options.showWeeksBar){P={weekNumber:k,elementId:this.WEEKNUMBER_ELEMENT_ID+k,component:this};
var U=this.evaluateMarkup(this.options.weekNumberMarkup,P);
l.push('<td class="rf-cal-week '+M+'" id="'+P.elementId+'">'+U+"</td>")
}for(var f=0;
f<7;
f++){Z=M+(!this.options.dayCellClass?"rf-cal-c-cnt-overflow":(!this.customDayListMarkup?this.options.dayCellClass:""))+" rf-cal-c";
if(f==this.firstWeekendDayNumber||f==this.secondWeekendDayNumber){Z+=" rf-cal-holiday"
}if(f==6){Z+=" rf-cal-right-c"
}this.dayCellClassName.push(Z);
l.push('<td class="'+Z+'" id="'+this.DATE_ELEMENT_ID+b+'" '+O+">"+(this.customDayListMarkup?'<div class="rf-cal-c-cnt'+(this.options.dayCellClass?" "+this.options.dayCellClass:"")+'"></div>':"")+"</td>");
b++
}l.push("</tr>")
}var a=L.getDomElement(this.CALENDAR_CONTENT);
a=F(a).replaceWith(X+V+m+Y.join("")+l.join("")+d+h+R);
this.attachToDom();
a=null;
if(this.options.popup&&!this.options.disabled){var N=new Function("event","RichFaces.$('"+this.id+"').switchPopup();");
L.Event.bindById(this.POPUP_BUTTON_ID,"click"+this.namespace,N,this);
if(!this.options.enableManualInput){L.Event.bindById(this.INPUT_DATE_ID,"click"+this.namespace,N,this)
}if(this.options.defaultLabel){D.call(this,this.options.defaultLabel);
L.Event.bindById(this.INPUT_DATE_ID,"focus"+this.namespace+" blur"+this.namespace,G,this)
}}this.scrollElements=null;
this.isAjaxMode=this.options.mode=="ajax"
};
L.BaseComponent.extend(L.ui.Calendar);
var J=L.ui.Calendar.$super;
var A={};
L.ui.Calendar.addLocale=function(M,N){if(!A[M]){A[M]=N
}};
F.extend(L.ui.Calendar.prototype,{name:"Calendar",destroy:function(){if(this.options.popup&&this.isVisible){this.scrollElements&&L.Event.unbindScrollEventHandlers(this.scrollElements,this);
this.scrollElements=null;
L.Event.unbind(window.document,"click"+this.namespace)
}J.destroy.call(this)
},dateEditorSelectYear:function(M){if(this.dateEditorYearID){F(L.getDomElement(this.dateEditorYearID)).removeClass("rf-cal-edtr-btn-sel")
}this.dateEditorYear=this.dateEditorStartYear+M;
this.dateEditorYearID=this.DATE_EDITOR_LAYOUT_ID+"Y"+M;
F(L.getDomElement(this.dateEditorYearID)).addClass("rf-cal-edtr-btn-sel")
},dateEditorSelectMonth:function(M){this.dateEditorMonth=M;
F(L.getDomElement(this.dateEditorMonthID)).removeClass("rf-cal-edtr-btn-sel");
this.dateEditorMonthID=this.DATE_EDITOR_LAYOUT_ID+"M"+M;
F(L.getDomElement(this.dateEditorMonthID)).addClass("rf-cal-edtr-btn-sel")
},scrollEditorYear:function(P){var N=L.getDomElement(this.DATE_EDITOR_LAYOUT_ID+"TR");
if(this.dateEditorYearID){F(L.getDomElement(this.dateEditorYearID)).removeClass("rf-cal-edtr-btn-sel");
this.dateEditorYearID=""
}if(!P){if(this.dateEditorMonth!=this.getCurrentMonth()){this.dateEditorMonth=this.getCurrentMonth();
F(L.getDomElement(this.dateEditorMonthID)).removeClass("rf-cal-edtr-btn-sel");
this.dateEditorMonthID=this.DATE_EDITOR_LAYOUT_ID+"M"+this.dateEditorMonth;
F(L.getDomElement(this.dateEditorMonthID)).addClass("rf-cal-edtr-btn-sel")
}}if(N){var Q;
var O=this.dateEditorStartYear=this.dateEditorStartYear+P*10;
for(var M=0;
M<5;
M++){N=N.nextSibling;
Q=N.firstChild.nextSibling.nextSibling;
Q.firstChild.innerHTML=O;
if(O==this.dateEditorYear){F(Q.firstChild).addClass("rf-cal-edtr-btn-sel");
this.dateEditorYearID=Q.firstChild.id
}Q=Q.nextSibling;
Q.firstChild.innerHTML=O+5;
if(O+5==this.dateEditorYear){F(Q.firstChild).addClass("rf-cal-edtr-btn-sel");
this.dateEditorYearID=Q.firstChild.id
}O++
}}},updateDateEditor:function(){this.dateEditorYear=this.getCurrentYear();
this.dateEditorStartYear=this.getCurrentYear()-4;
this.scrollEditorYear(0)
},updateTimeEditor:function(){var S=L.getDomElement(this.id+"TimeHours");
var R=L.getDomElement(this.id+"TimeSign");
var O=L.getDomElement(this.id+"TimeMinutes");
var Q=this.selectedDate.getHours();
var M=this.selectedDate.getMinutes();
if(this.timeType==2){var N=(Q<12?"AM":"PM");
R.value=N;
Q=(Q==0?"12":(Q>12?Q-12:Q))
}S.value=(this.timeHoursDigits==2&&Q<10?"0"+Q:Q);
O.value=(M<10?"0"+M:M);
if(this.showSeconds){var U=L.getDomElement(this.id+"TimeSeconds");
var P=this.selectedDate.getSeconds();
U.value=(P<10?"0"+P:P)
}},createEditor:function(){var P=F(L.getDomElement(this.CALENDAR_CONTENT));
var O=parseInt(P.css("z-index"),10);
var M='<div id="'+this.EDITOR_SHADOW_ID+'" class="rf-cal-edtr-shdw" style="position:absolute; display:none;z-index:'+O+'"></div><table border="0" cellpadding="0" cellspacing="0" id="'+this.EDITOR_ID+'" style="position:absolute; display:none;z-index:'+(O+1)+'" onclick="RichFaces.$(\''+this.id+'\').skipEventOnCollapse=true;"><tbody><tr><td class="rf-cal-edtr-cntr" align="center"><div style="position:relative; display:inline-block;">';
var Q='<div id="'+this.EDITOR_LAYOUT_SHADOW_ID+'" class="rf-cal-edtr-layout-shdw"></div>';
var N="</div></td></tr></tbody></table>";
P.after(M+Q+N);
this.isEditorCreated=true;
return L.getDomElement(this.EDITOR_ID)
},createTimeEditorLayout:function(N){F(L.getDomElement(this.EDITOR_LAYOUT_SHADOW_ID)).after(this.evaluateMarkup(B.timeEditorLayout,this.calendarContext));
var P=L.getDomElement(this.id+"TimeHours");
var O;
var M=L.getDomElement(this.id+"TimeMinutes");
if(this.timeType==1){sbjQuery(P).SpinButton({digits:this.timeHoursDigits,min:0,max:23})
}else{sbjQuery(P).SpinButton({digits:this.timeHoursDigits,min:1,max:12});
O=L.getDomElement(this.id+"TimeSign");
sbjQuery(O).SpinButton({})
}sbjQuery(M).SpinButton({digits:2,min:0,max:59});
if(this.showSeconds){var Q=L.getDomElement(this.id+"TimeSeconds");
sbjQuery(Q).SpinButton({digits:2,min:0,max:59})
}this.correctEditorButtons(N,this.TIME_EDITOR_BUTTON_OK,this.TIME_EDITOR_BUTTON_CANCEL);
this.isTimeEditorLayoutCreated=true
},correctEditorButtons:function(Q,N,M){var S=L.getDomElement(N);
var P=L.getDomElement(M);
Q.style.visibility="hidden";
Q.style.display="";
var R=F(S.firstChild).width();
var O=F(P.firstChild).width();
Q.style.display="none";
Q.style.visibility="";
if(R!=O){S.style.width=P.style.width=(R>O?R:O)+"px"
}},createDECell:function(R,P,M,Q,O){if(M==0){return'<div id="'+R+'" class="rf-cal-edtr-btn'+(O?" "+O:"")+'" onmouseover="this.className=\'rf-cal-edtr-btn rf-cal-edtr-tl-over\';" onmouseout="this.className=\'rf-cal-edtr-btn\';" onmousedown="this.className=\'rf-cal-edtr-btn rf-cal-edtr-tl-press\';" onmouseup="this.className=\'rf-cal-edtr-btn rf-cal-edtr-tl-over\';" onclick="RichFaces.$(\''+this.id+"').scrollEditorYear("+Q+');">'+P+"</div>"
}else{var N=(M==1?"RichFaces.$('"+this.id+"').dateEditorSelectMonth("+Q+");":"RichFaces.$('"+this.id+"').dateEditorSelectYear("+Q+");");
return'<div id="'+R+'" class="rf-cal-edtr-btn'+(O?" "+O:"")+'" onmouseover="jQuery(this).addClass(\'rf-cal-edtr-btn-over\');" onmouseout="$(this).removeClass(\'rf-cal-edtr-btn-over\');" onclick="'+N+'">'+P+"</div>"
}},createDateEditorLayout:function(Q){var M='<table id="'+this.DATE_EDITOR_LAYOUT_ID+'" class="rf-cal-monthpicker-cnt" border="0" cellpadding="0" cellspacing="0"><tbody><tr id="'+this.DATE_EDITOR_LAYOUT_ID+'TR">';
var N="</tr></tbody></table>";
var R=0;
this.dateEditorYear=this.getCurrentYear();
var P=this.dateEditorStartYear=this.dateEditorYear-4;
var S='<td align="center">'+this.createDECell(this.DATE_EDITOR_LAYOUT_ID+"M"+R,this.options.monthLabelsShort[R],1,R)+'</td><td align="center" class="rf-cal-monthpicker-split">'+this.createDECell(this.DATE_EDITOR_LAYOUT_ID+"M"+(R+6),this.options.monthLabelsShort[R+6],1,R+6)+'</td><td align="center">'+this.createDECell("","&lt;",0,-1)+'</td><td align="center">'+this.createDECell("","&gt;",0,1)+"</td>";
R++;
for(var O=0;
O<5;
O++){S+='</tr><tr><td align="center">'+this.createDECell(this.DATE_EDITOR_LAYOUT_ID+"M"+R,this.options.monthLabelsShort[R],1,R)+'</td><td align="center" class="rf-cal-monthpicker-split">'+this.createDECell(this.DATE_EDITOR_LAYOUT_ID+"M"+(R+6),this.options.monthLabelsShort[R+6],1,R+6)+'</td><td align="center">'+this.createDECell(this.DATE_EDITOR_LAYOUT_ID+"Y"+O,P,2,O,(O==4?"rf-cal-edtr-btn-sel":""))+'</td><td align="center">'+this.createDECell(this.DATE_EDITOR_LAYOUT_ID+"Y"+(O+5),P+5,2,O+5)+"</td>";
R++;
P++
}this.dateEditorYearID=this.DATE_EDITOR_LAYOUT_ID+"Y4";
this.dateEditorMonth=this.getCurrentMonth();
this.dateEditorMonthID=this.DATE_EDITOR_LAYOUT_ID+"M"+this.dateEditorMonth;
S+='</tr><tr><td colspan="2" class="rf-cal-monthpicker-ok"><div id="'+this.DATE_EDITOR_BUTTON_OK+'" class="rf-cal-time-btn" style="float:right;" onmousedown="jQuery(this).addClass(\'rf-cal-time-btn-press\');" onmouseout="$(this).removeClass(\'rf-cal-time-btn-press\');" onmouseup="$(this).removeClass(\'rf-cal-time-btn-press\');" onclick="RichFaces.$(\''+this.id+"').hideDateEditor(true);\"><span>"+this.options.labels.ok+'</span></div></td><td colspan="2" class="rf-cal-monthpicker-cancel"><div id="'+this.DATE_EDITOR_BUTTON_CANCEL+'" class="rf-cal-time-btn" style="float:left;" onmousedown="jQuery(this).addClass(\'rf-cal-time-btn-press\');" onmouseout="$(this).removeClass(\'rf-cal-time-btn-press\');" onmouseup="$(this).removeClass(\'rf-cal-time-btn-press\');" onclick="RichFaces.$(\''+this.id+"').hideDateEditor(false);\"><span>"+this.options.labels.cancel+"</span></div></td>";
F(L.getDomElement(this.EDITOR_LAYOUT_SHADOW_ID)).after(M+S+N);
F(L.getDomElement(this.dateEditorMonthID)).addClass("rf-cal-edtr-btn-sel");
this.correctEditorButtons(Q,this.DATE_EDITOR_BUTTON_OK,this.DATE_EDITOR_BUTTON_CANCEL);
this.isDateEditorLayoutCreated=true
},createSpinnerTable:function(M){return'<table cellspacing="0" cellpadding="0" border="0"><tbody><tr><td class="rf-cal-sp-inp-ctnr"><input id="'+M+'" name="'+M+'" class="rf-cal-sp-inp" type="text" /></td><td class="rf-cal-sp-btn"><table border="0" cellspacing="0" cellpadding="0"><tbody><tr><td><div id="'+M+'BtnUp" class="rf-cal-sp-up" onmousedown="this.className=\'rf-cal-sp-up rf-cal-sp-press\'" onmouseup="this.className=\'rf-cal-sp-up\'" onmouseout="this.className=\'rf-cal-sp-up\'"><span></span></div></td></tr><tr><td><div id="'+M+'BtnDown" class="rf-cal-sp-down" onmousedown="this.className=\'rf-cal-sp-down rf-cal-sp-press\'" onmouseup="this.className=\'rf-cal-sp-down\'" onmouseout="this.className=\'rf-cal-sp-down\'"><span></span></div></td></tr></tbody></table></td></tr></tbody></table>'
},setTimeProperties:function(){this.timeType=0;
var Q=this.options.datePattern;
var c=[];
var X=/(\\\\|\\[yMdaHhms])|(y+|M+|d+|a|H{1,2}|h{1,2}|m{2}|s{2})/g;
var V;
while(V=X.exec(Q)){if(!V[1]){c.push({str:V[0],marker:V[2],idx:V.index})
}}var M="";
var d="";
var O,b,N,Y,U,e;
var W=this.id;
var f=function(a){return(a.length==0?R.marker:Q.substring(c[Z-1].str.length+c[Z-1].idx,R.idx+R.str.length))
};
for(var Z=0;
Z<c.length;
Z++){var R=c[Z];
var P=R.marker.charAt(0);
if(P=="y"||P=="M"||P=="d"){M+=f(M)
}else{if(P=="a"){e=true;
d+=f(d)
}else{if(P=="H"){b=true;
O=R.marker.length;
d+=f(d)
}else{if(P=="h"){N=true;
O=R.marker.length;
d+=f(d)
}else{if(P=="m"){Y=true;
d+=f(d)
}else{if(P=="s"){this.showSeconds=true;
d+=f(d)
}}}}}}}this.datePattern=M;
this.timePattern=d;
var S=this;
this.timePatternHtml=d.replace(/(\\\\|\\[yMdaHhms])|(H{1,2}|h{1,2}|m{2}|s{2}|a)/g,function(a,h,g){if(h){return h.charAt(1)
}switch(g){case"a":return"</td><td>"+S.createSpinnerTable(W+"TimeSign")+"</td><td>";
case"H":case"HH":case"h":case"hh":return"</td><td>"+S.createSpinnerTable(W+"TimeHours")+"</td><td>";
case"mm":return"</td><td>"+S.createSpinnerTable(W+"TimeMinutes")+"</td><td>";
case"ss":return"</td><td>"+S.createSpinnerTable(W+"TimeSeconds")+"</td><td>"
}});
this.timePatternHtml='<table border="0" cellpadding="0"><tbody><tr><td>'+this.timePatternHtml+"</td></tr></tbody></table>";
if(Y&&b){this.timeType=1
}else{if(Y&&N&&e){this.timeType=2
}}this.timeHoursDigits=O
},eventOnScroll:function(M){this.hidePopup()
},hidePopup:function(){if(!this.options.popup||!this.isVisible){return 
}if(this.invokeEvent("hide",L.getDomElement(this.id))){if(this.isEditorVisible){this.hideEditor()
}this.scrollElements&&L.Event.unbindScrollEventHandlers(this.scrollElements,this);
this.scrollElements=null;
L.Event.unbind(window.document,"click"+this.namespace);
F(L.getDomElement(this.CALENDAR_CONTENT)).hide();
this.isVisible=false;
if(this.options.defaultLabel&&!this.isFocused){D.call(this,this.options.defaultLabel)
}}},showPopup:function(P){if(!this.isRendered){this.isRendered=true;
this.render()
}this.skipEventOnCollapse=false;
if(P&&P.type=="click"){this.skipEventOnCollapse=true
}if(!this.options.popup||this.isVisible){return 
}var M=L.getDomElement(this.id);
if(this.invokeEvent("show",M,P)){var O=L.getDomElement(this.POPUP_ID);
var Q=O.firstChild;
var N=Q.nextSibling;
if(this.options.defaultLabel){if(!this.isFocused){D.call(this,"")
}}if(Q.value){this.__selectDate(Q.value,false,{event:P,element:M})
}if(this.options.showInput){O=O.children
}else{O=N
}F(L.getDomElement(this.CALENDAR_CONTENT)).setPosition(O,{type:"DROPDOWN",from:this.options.jointPoint,to:this.options.direction,offset:this.popupOffset}).show();
this.isVisible=true;
L.Event.bind(window.document,"click"+this.namespace,this.eventOnCollapse,this);
this.scrollElements&&L.Event.unbindScrollEventHandlers(this.scrollElements,this);
this.scrollElements=null;
if(this.options.hidePopupOnScroll){this.scrollElements=L.Event.bindScrollEventHandlers(M,this.eventOnScroll,this)
}}},switchPopup:function(M){this.isVisible?this.hidePopup():this.showPopup(M)
},eventOnCollapse:function(M){if(this.skipEventOnCollapse){this.skipEventOnCollapse=false;
return true
}if(M.target.id==this.POPUP_BUTTON_ID||(!this.options.enableManualInput&&M.target.id==this.INPUT_DATE_ID)){return true
}this.hidePopup();
return true
},setInputField:function(M,N){var O=L.getDomElement(this.INPUT_DATE_ID);
if(O.value!=M){O.value=M;
this.invokeEvent("change",L.getDomElement(this.id),N,this.selectedDate)
}},getCurrentDate:function(){return this.currentDate
},__getSelectedDate:function(){if(!this.selectedDate){return null
}else{return this.selectedDate
}},__getSelectedDateString:function(M){if(!this.selectedDate){return""
}if(!M){M=this.options.datePattern
}return L.calendarUtils.formatDate(this.selectedDate,M,this.options.monthLabels,this.options.monthLabelsShort)
},getPrevYear:function(){var M=this.currentDate.getFullYear()-1;
if(M<0){M=0
}return M
},getPrevMonth:function(M){var N=this.currentDate.getMonth()-1;
if(N<0){N=11
}if(M){return this.options.monthLabels[N]
}else{return N
}},getCurrentYear:function(){return this.currentDate.getFullYear()
},getCurrentMonth:function(M){var N=this.currentDate.getMonth();
if(M){return this.options.monthLabels[N]
}else{return N
}},getNextYear:function(){return this.currentDate.getFullYear()+1
},getNextMonth:function(M){var N=this.currentDate.getMonth()+1;
if(N>11){N=0
}if(M){return this.options.monthLabels[N]
}else{return N
}},isWeekend:function(M){return(M==this.firstWeekendDayNumber||M==this.secondWeekendDayNumber)
},setupTimeForDate:function(N){var M=new Date(N);
if(this.selectedDate&&(!this.options.resetTimeOnDateSelect||(this.selectedDate.getFullYear()==N.getFullYear()&&this.selectedDate.getMonth()==N.getMonth()&&this.selectedDate.getDate()==N.getDate()))){M=L.calendarUtils.createDate(N.getFullYear(),N.getMonth(),N.getDate(),this.selectedDate.getHours(),this.selectedDate.getMinutes(),this.selectedDate.getSeconds())
}else{M=L.calendarUtils.createDate(N.getFullYear(),N.getMonth(),N.getDate(),this.options.defaultTime.hours,this.options.defaultTime.minutes,this.options.defaultTime.seconds)
}return M
},eventCellOnClick:function(P,O){var N=this.days[parseInt(O.id.substr(this.DATE_ELEMENT_ID.length),10)];
if(N.enabled&&N._month==0){var M=L.calendarUtils.createDate(this.currentDate.getFullYear(),this.currentDate.getMonth(),N.day);
if(this.timeType){M=this.setupTimeForDate(M)
}if(this.__selectDate(M,true,{event:P,element:O})&&!this.options.showApplyButton){this.hidePopup()
}}else{if(N._month!=0){if(this.options.boundaryDatesMode=="scroll"){if(N._month==-1){this.prevMonth()
}else{this.nextMonth()
}}else{if(this.options.boundaryDatesMode=="select"){var M=new Date(N.date);
if(this.timeType){M=this.setupTimeForDate(M)
}if(this.__selectDate(M,false,{event:P,element:O})&&!this.options.showApplyButton){this.hidePopup()
}}}}}},eventCellOnMouseOver:function(O,N){var M=this.days[parseInt(N.id.substr(this.DATE_ELEMENT_ID.length),10)];
if(this.invokeEvent("datemouseover",N,O,M.date)&&M.enabled){if(M._month==0&&N.id!=this.selectedDateCellId&&N.id!=this.todayCellId){F(N).addClass("rf-cal-hov")
}}},eventCellOnMouseOut:function(O,N){var M=this.days[parseInt(N.id.substr(this.DATE_ELEMENT_ID.length),10)];
if(this.invokeEvent("datemouseout",N,O,M.date)&&M.enabled){if(M._month==0&&N.id!=this.selectedDateCellId&&N.id!=this.todayCellId){F(N).removeClass("rf-cal-hov")
}}},load:function(N,M){if(N){this.daysData=this.indexData(N,M)
}else{this.daysData=null
}this.isRendered=false;
if(this.isVisible){this.render()
}if(typeof this.afterLoad=="function"){this.afterLoad();
this.afterLoad=null
}},indexData:function(Q,N){var O=Q.startDate.year;
var P=Q.startDate.month;
Q.startDate=new Date(O,P);
Q.index=[];
Q.index[O+"-"+P]=0;
if(N){this.currentDate=Q.startDate;
this.currentDate.setDate(1);
return Q
}var M=L.calendarUtils.daysInMonthByDate(Q.startDate)-Q.startDate.getDate()+1;
while(Q.days[M]){if(P==11){O++;
P=0
}else{P++
}Q.index[O+"-"+P]=M;
M+=(32-new Date(O,P,32).getDate())
}return Q
},getCellBackgroundColor:function(M){return F(M).css("background-color")
},clearEffect:function(M,N,P){if(M){var O=F(L.getDomElement(M)).stop(true,true);
if(N){O.removeClass(N)
}if(P){O.addClass(P)
}}return null
},render:function(){this.isRendered=true;
this.todayDate=new Date();
var r=this.getCurrentYear();
var d=this.getCurrentMonth();
var X=(r==this.todayDate.getFullYear()&&d==this.todayDate.getMonth());
var c=this.todayDate.getDate();
var f=this.selectedDate&&(r==this.selectedDate.getFullYear()&&d==this.selectedDate.getMonth());
var j=this.selectedDate&&this.selectedDate.getDate();
var S=L.calendarUtils.getDay(this.currentDate,this.options.firstWeekDay);
var R=L.calendarUtils.daysInMonthByDate(this.currentDate);
var M=L.calendarUtils.daysInMonth(r,d-1);
var b=0;
var q=-1;
this.days=[];
var W=M-S+1;
if(S>0){while(W<=M){this.days.push({day:W,isWeekend:this.isWeekend(b),_month:q});
W++;
b++
}}W=1;
q=0;
this.firstDateIndex=b;
if(this.daysData&&this.daysData.index[r+"-"+d]!=undefined){var a=this.daysData.index[r+"-"+d];
if(this.daysData.startDate.getFullYear()==r&&this.daysData.startDate.getMonth()==d){var V=V=(this.daysData.days[a].day?this.daysData.days[a].day:this.daysData.startDate.getDate());
while(W<V){this.days.push({day:W,isWeekend:this.isWeekend(b%7),_month:q});
W++;
b++
}}var i=this.daysData.days.length;
var Y;
var h;
while(a<i&&W<=R){h=this.isWeekend(b%7);
Y=this.daysData.days[a];
Y.day=W;
Y.isWeekend=h;
Y._month=q;
this.days.push(Y);
a++;
W++;
b++
}}while(b<42){if(W>R){W=1;
q=1
}this.days.push({day:W,isWeekend:this.isWeekend(b%7),_month:q});
W++;
b++
}this.renderHF();
b=0;
var N;
var U;
var P;
if(this.options.showWeeksBar){P=L.calendarUtils.weekNumber(r,d,this.options.minDaysInFirstWeek,this.options.firstWeekDay)
}this.selectedDayElement=null;
var Z=true;
var l;
var n=(this.options.boundaryDatesMode=="scroll"||this.options.boundaryDatesMode=="select");
this.todayCellId=this.clearEffect(this.todayCellId);
this.selectedDateCellId=this.clearEffect(this.selectedDateCellId);
var Y=L.getDomElement(this.WEEKNUMBER_BAR_ID+"1");
for(var g=1;
g<7;
g++){U=this.days[b];
N=Y.firstChild;
var m;
if(this.options.showWeeksBar){if(Z&&d==11&&(g==5||g==6)&&(U._month==1||(7-(R-U.day+1))>=this.options.minDaysInFirstWeek)){P=1;
Z=false
}m=P;
N.innerHTML=this.evaluateMarkup(this.options.weekNumberMarkup,{weekNumber:P++,elementId:N.id,component:this});
if(g==1&&P>52){P=1
}N=N.nextSibling
}var s=this.options.firstWeekDay;
var Q=null;
while(N){U.elementId=N.id;
U.date=new Date(r,d+U._month,U.day);
U.weekNumber=m;
U.component=this;
U.isCurrentMonth=(U._month==0);
U.weekDayNumber=s;
if(U.enabled!=false){U.enabled=this.options.isDayEnabled(U)
}if(!U.styleClass){U.customStyleClass=this.options.dayStyleClass(U)
}else{var O=this.options.dayStyleClass(U);
U.customStyleClass=U.styleClass;
if(O){U.customStyleClass+=" "+O
}}Q=(this.customDayListMarkup?N.firstChild:N);
Q.innerHTML=this.hideBoundaryDatesContent&&U._month!=0?"":this.evaluateMarkup(this.options.dayListMarkup,U);
if(s==6){s=0
}else{s++
}var o=this.dayCellClassName[b];
if(U._month!=0){o+=" rf-cal-boundary-day";
if(!this.options.disabled&&!this.options.readonly&&n){o+=" rf-cal-btn"
}}else{if(X&&U.day==c){this.todayCellId=N.id;
this.todayCellColor=this.getCellBackgroundColor(N);
o+=" rf-cal-today"
}if(f&&U.day==j){this.selectedDateCellId=N.id;
this.selectedDateCellColor=this.getCellBackgroundColor(N);
o+=" rf-cal-sel"
}else{if(!this.options.disabled&&!this.options.readonly&&U.enabled){o+=" rf-cal-btn"
}}if(U.customStyleClass){o+=" "+U.customStyleClass
}}N.className=o;
b++;
U=this.days[b];
N=N.nextSibling
}Y=Y.nextSibling
}},renderHF:function(){if(this.options.showHeader){this.renderMarkup(this.options.headerMarkup,this.id+"Header",this.calendarContext)
}if(this.options.showFooter){this.renderMarkup(this.options.footerMarkup,this.id+"Footer",this.calendarContext)
}this.renderHeaderOptional();
this.renderFooterOptional()
},renderHeaderOptional:function(){this.renderMarkup(this.options.optionalHeaderMarkup,this.id+"HeaderOptional",this.calendarContext)
},renderFooterOptional:function(){this.renderMarkup(this.options.optionalFooterMarkup,this.id+"FooterOptional",this.calendarContext)
},renderMarkup:function(N,M,O){if(!N){return 
}var P=L.getDomElement(M);
if(!P){return 
}P.innerHTML=this.evaluateMarkup(N,O)
},evaluateMarkup:function(O,Q){if(!O){return""
}var N=[];
var M;
for(var P=0;
P<O.length;
P++){M=O[P];
if(M.getContent){N.push(M.getContent(Q))
}}return N.join("")
},onUpdate:function(){var M=L.calendarUtils.formatDate(this.getCurrentDate(),"MM/yyyy");
L.getDomElement(this.id+"InputCurrentDate").value=M;
if(this.isAjaxMode&&this.callAjax){this.callAjax.call(this,M)
}else{this.render()
}},callAjax:function(P,M){var R=this;
var O=function(S){var U=S&&S.componentData&&S.componentData[R.id];
R.load(U,true)
};
var N=function(S){};
var Q={};
Q[this.id+".ajax"]="1";
L.ajax(this.id,null,{parameters:Q,error:N,complete:O})
},nextMonth:function(){this.changeCurrentDateOffset(0,1)
},prevMonth:function(){this.changeCurrentDateOffset(0,-1)
},nextYear:function(){this.changeCurrentDateOffset(1,0)
},prevYear:function(){this.changeCurrentDateOffset(-1,0)
},changeCurrentDate:function(N,P,O){if(this.getCurrentMonth()!=P||this.getCurrentYear()!=N){var M=new Date(N,P,1);
if(this.invokeEvent("currentdateselect",L.getDomElement(this.id),null,M)){this.currentDate=M;
if(O){this.render()
}else{this.onUpdate()
}this.invokeEvent("currentdateselect",L.getDomElement(this.id),null,M);
return true
}}return false
},changeCurrentDateOffset:function(N,O){var M=new Date(this.currentDate.getFullYear()+N,this.currentDate.getMonth()+O,1);
if(this.invokeEvent("beforecurrentdateselect",L.getDomElement(this.id),null,M)){this.currentDate=M;
this.onUpdate();
this.invokeEvent("currentdateselect",L.getDomElement(this.id),null,M)
}},today:function(P,R){var N=new Date();
var Q=N.getFullYear();
var S=N.getMonth();
var O=N.getDate();
var M=false;
if(O!=this.todayDate.getDate()){M=true;
this.todayDate=N
}if(Q!=this.currentDate.getFullYear()||S!=this.currentDate.getMonth()){M=true;
this.currentDate=new Date(Q,S,1)
}if(this.options.todayControlMode=="select"){R=true
}if(M){if(P){this.render()
}else{this.onUpdate()
}}else{if(this.isVisible&&this.todayCellId&&!R){this.clearEffect(this.todayCellId);
if(this.todayCellColor!="transparent"){F(L.getDomElement(this.todayCellId)).effect("highlight",{easing:"easeInOutSine",color:this.todayCellColor},300)
}}}if(this.options.todayControlMode=="select"&&!this.options.disabled&&!this.options.readonly){if(M&&!P&&this.submitFunction){this.afterLoad=this.selectToday
}else{this.selectToday()
}}},selectToday:function(){if(this.todayCellId){var O=this.days[parseInt(this.todayCellId.substr(this.DATE_ELEMENT_ID.length),10)];
var M=new Date();
var N=new Date(M);
if(this.timeType){N=this.setupTimeForDate(N)
}if(O.enabled&&this.__selectDate(N,true)&&!this.options.showApplyButton){this.hidePopup()
}}},__selectDate:function(O,N,U){if(!U){U={event:null,element:null}
}var M=this.selectedDate;
var V;
if(O){if(typeof O=="string"){O=L.calendarUtils.parseDate(O,this.options.datePattern,this.options.monthLabels,this.options.monthLabelsShort)
}V=O
}else{V=null
}var Q=true;
var R=false;
if((M-V)&&(M!=null||V!=null)){R=true;
Q=this.invokeEvent("beforedateselect",U.element,U.event,O)
}if(Q){if(V!=null){if(V.getMonth()==this.currentDate.getMonth()&&V.getFullYear()==this.currentDate.getFullYear()){this.selectedDate=V;
if(!M||(M-this.selectedDate)){var P=F(L.getDomElement(this.DATE_ELEMENT_ID+(this.firstDateIndex+this.selectedDate.getDate()-1)));
this.clearEffect(this.selectedDateCellId,"rf-cal-sel",(this.options.disabled||this.options.readonly?null:"rf-cal-btn"));
this.selectedDateCellId=P.attr("id");
this.selectedDateCellColor=this.getCellBackgroundColor(P);
P.removeClass("rf-cal-btn");
P.removeClass("rf-cal-hov");
P.addClass("rf-cal-sel");
this.renderHF()
}else{if(this.timeType!=0){this.renderHF()
}}}else{this.selectedDate=V;
if(this.changeCurrentDate(V.getFullYear(),V.getMonth(),N)){}else{this.selectedDate=M;
R=false
}}}else{this.selectedDate=null;
this.clearEffect(this.selectedDateCellId,"rf-cal-sel",(this.options.disabled||this.options.readonly?null:"rf-cal-btn"));
if(this.selectedDateCellId){this.selectedDateCellId=null;
this.renderHF()
}var O=new Date();
if(this.currentDate.getMonth()==O.getMonth()&&this.currentDate.getFullYear()==O.getFullYear()){this.renderHF()
}var S=this.options.todayControlMode;
this.options.todayControlMode="";
this.today(N,true);
this.options.todayControlMode=S
}if(R){this.invokeEvent("dateselect",U.element,U.event,this.selectedDate);
if(!this.options.showApplyButton){this.setInputField(this.selectedDate!=null?this.__getSelectedDateString(this.options.datePattern):"",U.event)
}}}return R
},__resetSelectedDate:function(){if(!this.selectedDate){return 
}if(this.invokeEvent("beforedateselect",null,null,null)){this.selectedDate=null;
this.invokeEvent("dateselect",null,null,null);
this.selectedDateCellId=this.clearEffect(this.selectedDateCellId,"rf-cal-sel",(this.options.disabled||this.options.readonly?null:"rf-cal-btn"));
this.invokeEvent("clean",null,null,null);
this.renderHF();
if(!this.options.showApplyButton){this.setInputField("",null);
this.hidePopup()
}}},showSelectedDate:function(){if(!this.selectedDate){return 
}if(this.currentDate.getMonth()!=this.selectedDate.getMonth()||this.currentDate.getFullYear()!=this.selectedDate.getFullYear()){this.currentDate=new Date(this.selectedDate);
this.currentDate.setDate(1);
this.onUpdate()
}else{if(this.isVisible&&this.selectedDateCellId){this.clearEffect(this.selectedDateCellId);
if(this.selectedDateCellColor!="transparent"){F(L.getDomElement(this.selectedDateCellId)).effect("highlight",{easing:"easeInOutSine",color:this.selectedDateCellColor},300)
}}}},close:function(M){if(M){this.setInputField(this.__getSelectedDateString(this.options.datePattern),null)
}this.hidePopup()
},clonePosition:function(M,N,R){var Q=F(M);
if(!N.length){N=[N]
}R=R||{left:0,top:0};
var O=Q.outerWidth()+"px",X=Q.outerHeight()+"px";
var W=Q.position();
var P=Math.floor(W.left)+R.left+"px",V=Math.floor(W.top)+R.top+"px";
var U;
for(var S=0;
S<N.length;
S++){U=N[S];
U.style.width=O;
U.style.height=X;
U.style.left=P;
U.style.top=V
}},showTimeEditor:function(){var N;
if(this.timeType==0){return 
}if(!this.isEditorCreated){N=this.createEditor()
}else{N=L.getDomElement(this.EDITOR_ID)
}if(!this.isTimeEditorLayoutCreated){this.createTimeEditorLayout(N)
}F(L.getDomElement(this.TIME_EDITOR_LAYOUT_ID)).show();
var M=L.getDomElement(this.EDITOR_SHADOW_ID);
this.clonePosition(L.getDomElement(this.CALENDAR_CONTENT),[N,M]);
this.updateTimeEditor();
F(M).show();
F(N).show();
this.clonePosition(L.getDomElement(this.TIME_EDITOR_LAYOUT_ID),L.getDomElement(this.EDITOR_LAYOUT_SHADOW_ID),{left:3,top:3});
this.isEditorVisible=true
},hideEditor:function(){if(this.isTimeEditorLayoutCreated){F(L.getDomElement(this.TIME_EDITOR_LAYOUT_ID)).hide()
}if(this.isDateEditorLayoutCreated){F(L.getDomElement(this.DATE_EDITOR_LAYOUT_ID)).hide()
}F(L.getDomElement(this.EDITOR_ID)).hide();
F(L.getDomElement(this.EDITOR_SHADOW_ID)).hide();
this.isEditorVisible=false
},hideTimeEditor:function(O){this.hideEditor();
if(O&&this.selectedDate){var Q=this.showSeconds?parseInt(L.getDomElement(this.id+"TimeSeconds").value,10):this.options.defaultTime.seconds;
var M=parseInt(L.getDomElement(this.id+"TimeMinutes").value,10);
var P=parseInt(L.getDomElement(this.id+"TimeHours").value,10);
if(this.timeType==2){if(L.getDomElement(this.id+"TimeSign").value.toLowerCase()=="am"){if(P==12){P=0
}}else{if(P!=12){P+=12
}}}var N=L.calendarUtils.createDate(this.selectedDate.getFullYear(),this.selectedDate.getMonth(),this.selectedDate.getDate(),P,M,Q);
if(N-this.selectedDate&&this.invokeEvent("beforetimeselect",null,null,N)){this.selectedDate=N;
this.renderHF();
if(!this.options.popup||!this.options.showApplyButton){this.setInputField(this.__getSelectedDateString(this.options.datePattern),null)
}this.invokeEvent("timeselect",null,null,this.selectedDate)
}}if(this.options.popup&&!this.options.showApplyButton){this.close(false)
}},showDateEditor:function(){var N;
if(!this.isEditorCreated){N=this.createEditor()
}else{N=L.getDomElement(this.EDITOR_ID)
}if(!this.isDateEditorLayoutCreated){this.createDateEditorLayout(N)
}else{this.updateDateEditor()
}F(L.getDomElement(this.DATE_EDITOR_LAYOUT_ID)).show();
var M=L.getDomElement(this.EDITOR_SHADOW_ID);
this.clonePosition(L.getDomElement(this.CALENDAR_CONTENT),[N,M]);
F(M).show();
F(N).show();
this.clonePosition(L.getDomElement(this.DATE_EDITOR_LAYOUT_ID),L.getDomElement(this.EDITOR_LAYOUT_SHADOW_ID),{left:3,top:3});
this.isEditorVisible=true
},hideDateEditor:function(M){this.hideEditor();
if(M){this.changeCurrentDate(this.dateEditorYear,this.dateEditorMonth)
}},getValue:function(){return this.__getSelectedDate()
},getValueAsString:function(M){return this.__getSelectedDateString(M)
},setValue:function(M){this.__selectDate(M)
},resetValue:function(){this.__resetSelectedDate();
if(this.options.defaultLabel&&!this.isFocused){D.call(this,this.options.defaultLabel)
}},getNamespace:function(){return this.namespace
}})
})(jQuery,RichFaces);