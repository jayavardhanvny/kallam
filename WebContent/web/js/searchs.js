var winSelector=null;var gForm=null;var gFieldKey=null;var gFieldText=null;var gValidate=false;var gIsMultiLineSearch=false;var baseImage="Question.jpg";function selGetRef(a){if(document.getElementById){return document.getElementById(a);}if(document.all){return document.all[a];}if(document.layers){return document.layers[a];
}}function SearchElements(a,c,b){this.campo=a;this.esRef=c;this.valor=b;}function valorCombo(a){if(a.selectedIndex==-1){return null;}else{return a.options[a.selectedIndex].value;}}function textoCombo(a){if(a.selectedIndex==-1){return"";}else{return a.options[a.selectedIndex].text;}}function windowSearch(d,n,a,e,m,j,o,c){var k="";
var f="";closeWindowSearch();winSelector=null;if(n==null){n=(screen.height-100);}if(a==null){a=(screen.width-10);}if(e==null){e=parseInt((screen.height-n)/2);}if(m==null){m=parseInt((screen.width-a)/2);}if(j==null){j="SELECTOR";}if(c!=null&&c!=""){f="inpNameValue="+encodeURIComponent(c);}var h;if(o!=null){var l=o.length;
for(var g=0;g<l;g++){if(f!=""){f+="&";}if(o[g]=="isMultiLine"&&o[g+1]=="Y"){gIsMultiLineSearch=true;}f+=o[g]+"="+((o[g+1]!=null)?encodeURIComponent(o[g+1]):"");if(o[g]=="Command"){h=true;}g++;}}if(navigator.appName.indexOf("Netscape")){k="alwaysRaised=1, dependent=1, directories=0, hotkeys=0, menubar=0, ";
}var b=k+"height="+n+", width="+a+", left="+m+", top="+e+", screenX="+m+", screenY="+e+", location=0, resizable=1, scrollbars=1, status=0, toolbar=0, titlebar=0";winSelector=window.open(d+((f=="")?"":"?"+f),j,b);if(winSelector!=null){winSelector.focus();enableEvents();}}function closeWindowSearch(){if(winSelector&&!winSelector.closed){winSelector.close();
winSelector=null;disableEvents();}}function openSearch(c,k,a,g,j,h,f,d,b){if(a!=null){gForm=(h==null)?"forms[0]":h;gFieldKey=f;gFieldText=d;strValueId=(b==null)?"":b;gValidate=(j==null)?false:j;var l=new Array();for(var e=9;arguments[e]!=null;e++){l[e-9]=arguments[e];}if(a.indexOf("Location")!=-1){windowSearch(a,300,600,c,k,g,l,b);
}else{windowSearch(a,null,900,c,k,g,l,b);}}}function openMultiSearch(b,h,a,e,g,f,d){if(a!=null){gForm=(f==null)?"forms[0]":f;gFieldKey=d;gValidate=(g==null)?false:g;var j=new Array();for(var c=7;arguments[c]!=null;c++){j[c-7]=arguments[c];}windowSearch(a,null,900,b,h,e,j,null);}}function openPAttribute(c,k,a,g,j,h,f,d,b){if(a!=null){gForm=(h==null)?"forms[0]":h;
gFieldKey=f;gFieldText=d;strValueId=(b==null)?"":b;gValidate=(j==null)?false:j;var l=new Array();for(var e=9;arguments[e]!=null;e++){l[e-9]=arguments[e];}windowSearch(a,450,650,c,k,g,l,b);}}function getField(fieldName){if(gIsMultiLineSearch){return buscarHijo(gFilaActual,"name",fieldName);}else{return eval("document."+gForm+"."+fieldName);
}}function closeSearch(action,strKey,strText,parameters,wait){if(wait!=false){setTimeout(function(){closeSearch(action,strKey,strText,parameters,false);},100);return;}else{if(winSelector==null){return true;}if(gForm!=null&&gFieldKey!=null&&gFieldText!=null){var key=getField(gFieldKey);if(key!=null){if(action=="SAVE"){if(strKey==null||strKey==""){showJSMessage(31);
winSelector.focus();return false;}key.value=strKey;var text=getField(gFieldText);if(text!=null){text.value=strText;}if(parameters!=null&&parameters.length>0){var total=parameters.length;for(var i=0;i<total;i++){var obj=getField(((parameters[i].esRef)?gFieldKey:"")+parameters[i].campo);if(obj!=null&&obj.type){obj.value=parameters[i].valor;
}}}if(key.onchange){key.onchange();}try{changeToEditingMode("force");}catch(e){}}else{if(action=="CLEAR"){strKey="";strText="";key.value="";var text=getField(gFieldText);text.value="";if(parameters!=null&&parameters.length>0){var total=parameters.length;for(var i=0;i<total;i++){var obj=getField(((parameters[i].esRef)?gFieldKey:"")+parameters[i].campo);
if(obj!=null&&obj.type){obj.value="";}}}if(key.onchange){key.onchange();}try{changeToEditingMode("force");}catch(e){}}else{if(action=="SAVE_IMAGE"){if(strKey==null||strKey==""){showJSMessage(31);winSelector.focus();return false;}key.value=strKey;if(typeof baseDirectory!="unknown"){eval("document.images['"+gFieldText+"'].src=\""+baseDirectory+"images/"+strText+'"');
}else{eval("document.images['"+gFieldText+"'].src=\""+baseDirection+"images/"+strText+'"');}if(parameters!=null&&parameters.length>0){var total=parameters.length;for(var i=0;i<total;i++){var obj=getField(((parameters[i].esRef)?gFieldKey:"")+parameters[i].campo);if(obj!=null&&obj.type){obj.value=parameters[i].valor;
}}}if(key.onchange){key.onchange();}try{changeToEditingMode("force");}catch(e){}}else{if(action=="CLEAR_IMAGE"){strKey="";strText="";key.value="";var text=getField(gFieldText);if(typeof baseDirectory!="unknown"){text.src=baseDirectory+"images/"+baseImage;}else{text.src=baseDirection+"images/"+baseImage;
}if(parameters!=null&&parameters.length>0){var total=parameters.length;for(var i=0;i<total;i++){var obj=getField(((parameters[i].esRef)?gFieldKey:"")+parameters[i].campo);if(obj!=null&&obj.type){obj.value="";}}}if(key.onchange){key.onchange();}try{changeToEditingMode("force");}catch(e){}}}}}}}closeWindowSearch();
if(gValidate){if(!debugSearch(strKey,strText,gFieldKey,parameters)){return false;}}window.focus();return true;}}function closeMultiSearch(action,data,parameters){if(winSelector==null){return true;}if(gForm!=null&&gFieldKey!=null){var key=eval("document."+gForm+"."+gFieldKey);if(key!=null){if(action=="SAVE"){if(data==null||data.length==0){showJSMessage(31);
winSelector.focus();return false;}addElementsToList(key,data);if(parameters!=null&&parameters.length>0){var total=parameters.length;for(var i=0;i<total;i++){var obj=eval("document."+gForm+"."+((parameters[i].esRef)?gFieldKey:"")+parameters[i].campo);if(obj!=null&&obj.type){obj.value=parameters[i].valor;
}}}}else{if(action=="CLEAR"){clearList(key);if(parameters!=null&&parameters.length>0){var total=parameters.length;for(var i=0;i<total;i++){var obj=eval("document."+gForm+"."+((parameters[i].esRef)?gFieldKey:"")+parameters[i].campo);if(obj!=null&&obj.type){obj.value="";}}}}}}}closeWindowSearch();if(gValidate){if(!debugSearch(data,gFieldKey)){return false;
}}window.focus();return true;}function toLayer(c,b){var a=selGetRef(b);if(c==null){c="";}if(document.layers){a.document.write(c);a.document.close();}else{if(document.all){a.innerHTML=c;}else{if(document.getElementById){range=document.createRange();range.setStartBefore(a);domfrag=range.createContextualFragment(c);
while(a.hasChildNodes()){a.removeChild(a.lastChild);}a.appendChild(domfrag);}}}}function enableEvents(){if(document.layers){document.captureEvents(Event.UNLOAD);}window.onunload=function(){closeSearch();};hasCloseWindowSearch=true;}function disableEvents(){if(document.layers){window.releaseEvents(Event.UNLOAD);
}window.onunload=function(){};hasCloseWindowSearch=false;}function infoSelectFilters(b,a){if(!a){a="Search";}setGridFilters(b);updateGridDataAfterFilter();if(a==="Search"){dijit.byId("grid").requestParams["newFilter"]="0";}else{if(a==="Save"){dijit.byId("grid").requestParams["newFilter"]="1";}}return true;
}function updateHeader(b,a){return true;}function depurarSelector_validateSelector_wrapper(a){if(typeof validateSelector=="function"){return validateSelector(a);}else{return depurarSelector(a);}}function onRowDblClick(a){var b=dijit.byId("grid").getSelectedRows();if(b==null||b==""||b.length>1){return false;
}depurarSelector_validateSelector_wrapper("SAVE");}function getSelectedValues(){var a=dijit.byId("grid").getSelectedRows();if(a==null||a.length==0){return"";}return a[0];}function getSelectdText(){var a=dijit.byId("grid").getSelectedRows();if(a==null||a.length==0){return"";}return a[0];}function getSelectedPos(){var a=dijit.byId("grid").getSelectedRowsPos();
if(a==null||a.length==0){return"";}return a[0];}function isMultipleSelected(){var a=dijit.byId("grid").getSelectedRows();if(a==null||a==""){return false;}return(a.length>1);}function onGridLoadDo(){if(selectedRow==null){return true;}if(selectedRow<=0){dijit.byId("grid").goToFirstRow();}else{dijit.byId("grid").goToRow(selectedRow);
}var a=new Array();a["newFilter"]="0";dijit.byId("grid").setRequestParams(a);return true;}function setGridFilters(a){var d=[];d["newFilter"]="1";if(a!=null&&a.length>0){var c=a.length;for(var b=0;b<c;b++){d[a[b][0]]=a[b][1];}}dijit.byId("grid").setRequestParams(d);return true;}function updateGridData(){dijit.byId("grid").refreshGridData();
return true;}function updateGridDataAfterFilter(){dijit.byId("grid").refreshGridDataAfterFilter();return true;}function setFilters(d){if(!d){d="Search";}var f=document.forms[0];var g=new Array();var e=0;g[e++]=new Array("clear","true");var a=f.getElementsByTagName("INPUT");for(var c=0;c<a.length;c++){if(a[c].name.toUpperCase()!="COMMAND"&&a[c].name.toUpperCase()!="ISPOPUPCALL"){if(a[c].type.toUpperCase()=="RADIO"){if(a[c].checked){g[e++]=new Array(a[c].name,a[c].value);
}}else{if(a[c].type.toUpperCase()=="CHECKBOX"){if(a[c].checked){g[e++]=new Array(a[c].name,a[c].value);}else{g[e++]=new Array(a[c].name,"N");}}else{g[e++]=new Array(a[c].name,a[c].value);}}}}var b=f.getElementsByTagName("SELECT");for(var c=0;c<b.length;c++){if((b[c].selectedIndex)!=-1){g[e++]=new Array(b[c].name,b[c].options[b[c].selectedIndex].value);
}}infoSelectFilters(g,d);}function calculateNumRows(){resizeAreaInfo();document.getElementById("grid_sample").style.display="block";var f=document.getElementById("grid_sample_header");var d=document.getElementById("grid_sample_row");var a,e;if(isIE9Strict){a=parseInt(getComputedStyle(f,null).getPropertyValue("height").replace("px",""),10)+4;
e=parseInt(getComputedStyle(d,null).getPropertyValue("height").replace("px",""),10)+1;}else{a=f.clientHeight+1;e=d.clientHeight+1;}if(getBrowserInfo("name").toUpperCase().indexOf("CHROME")!=-1||getBrowserInfo("name").toUpperCase().indexOf("SAFARI")!=-1){a=a+1;e=e-1;}var g=document.getElementById("messageBoxID");
var j=document.getElementById("related_info_cont");var k=document.getElementById("client_middle").clientHeight;var b=0;var c=0;var h=0;if(document.getElementById("grid_bookmark")){b=document.getElementById("grid_bookmark").clientHeight;}if(document.getElementById("grid_toptext")){c=document.getElementById("grid_toptext").clientHeight;
}if(document.getElementById("grid_bottomtext")){h=document.getElementById("grid_bottomtext").clientHeight;}k=k-b-a-c-h-(j?j.clientHeight:0)-(g?g.clientHeight:0);k=k-20;var i=(k)/(e);i=parseInt(i);if(i>1){i-=1;}document.getElementById("grid_sample").style.display="none";return i;}