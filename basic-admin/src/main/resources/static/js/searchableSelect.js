
function showDataListDiv(even){
  var enevTarget = $(even);
  if(enevTarget[0].localName == 'input'){
    enevTarget = enevTarget.parent().next();
  }
  var selectDataListTarget = enevTarget.next();
  var offsetHeight = $(document).height() - (enevTarget.offset().top + 24);
  if(offsetHeight < 100){
    selectDataListTarget.css("margin-top",-(offsetHeight + 25));
  }
  var selectClassName = enevTarget.attr("class");
  if(selectClassName == 'spanBtnSelect-down'){
    enevTarget.attr("class",'spanBtnSelect-up');
    selectDataListTarget.css("display","");
  }else{
    enevTarget.attr("class",'spanBtnSelect-down');
    selectDataListTarget.css("display","none");
  }
}
function selectData(id){
  var selectDataListTarget = $("#"+id).parent();
  selectDataListTarget.find("span").each(function(n,object){
    if(object.id == id){
      $("#"+object.id).attr("class",'optionSelected');
    }else{
      $("#"+object.id).attr("class",'optionContent');
    }
  });
  selectDataListTarget.prev().attr("class",'spanBtnSelect-down');
  selectDataListTarget.parent().find("span").find("input").val($("#"+id).attr("rel"));
  selectDataListTarget.parent().find("span").find("input").next().val($("#"+id).text());
  selectDataListTarget.css("display","none");
}
