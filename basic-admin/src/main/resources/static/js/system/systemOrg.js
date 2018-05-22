var dataID;
var typeVal = $("#typeVal").val();
if(typeVal == 'list'){
    //search();
}else if(typeVal == 'add'){
    getOption("index");
}else if(typeVal == 'edit'){
    initDataToView("/systemOrg/viewOrg.do",parent.dataID,"edit",null);
}

function search(opts,callback){
    $.ajax({
        url:'/systemOrg/getOrgList.do',
        type:"post",
        data:opts,
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                var appendHtml = '';
                $("#dataBodyList").html("");
                $.each(data.data.list,function(n,value) {
                    appendHtml+='<tr>'+
                        '<td><input type="radio" name="ids" value="'+ value.id +'"/> </td>'+
                        '<td>'+value.orgName+'</td>'+
                        '<td>'+value.pidName+'</td>'+
                        '<td>'+value.orgAddress+'</td>'+
                        '<td>'+value.orgCode+'</td>'+
                        '<td>'+value.createTime+'</td>'+
                        '<td>'+value.updateTime+'</td>'+
                        '</tr>';
                });
                $("#dataBodyList").append(appendHtml);
                if(typeof callback == 'function'){
                    callback.call(this,data.data);
                }
            }else{
                layer.alert(data.msg);
            }
        }
    });
}

//监听右上角的操作按钮
$("#optProject").click(function (e){
    var target = e.target || e.srcElement;
    var opt_id = $(target).attr('opt-id');
    if(opt_id == 'add'){
        addIframe("增加机构",'goToHtml.do?goToHtmlUrl=/system/SystemOrgAdd');
        return;
    }
    dataID = $('input:radio[name="ids"]:checked').val();
    if(dataID == null){
        layer.alert("请先选择！");
        return false;
    }
    if(opt_id == 'edit'){
        addIframe("更新机构信息",'goToHtml.do?goToHtmlUrl=/system/SystemOrgEdit');
    }else if(opt_id == 'del'){
        updateDataById("/systemOrg/delOrg.do",dataID);
    }
});

function getOption(object){
    $.ajax({
        url:'/systemOrg/getOrgOption.do',
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                var optionHtml = '';
                var hiddenInputTarget = $('input[name="pid"]');
                $.each(data.data,function(n,value) {
                    if((object!=null && value.id == object.pid) || (object == 'index' && n == 0)){
                        hiddenInputTarget.next().val(value.orgName);
                        optionHtml += '<span class="optionSelected"';
                    }else{
                        optionHtml += '<span class="optionContent"';
                    }
                    optionHtml += 'rel="'+ value.id+'" id="option'+ value.id +'" onclick="selectData(this.id)">'+ value.orgName +'</span>';
                });
                hiddenInputTarget.parent().next().next().append(optionHtml);
            }else{
                alert(data.msg);
            }
        }
    })
}
$('.confirm').click(function (){
    if(typeVal == 'add'){
        addAndUpdateUtil("/systemOrg/addOrg.do",null,"add");
    }else{
        addAndUpdateUtil("/systemOrg/editOrg.do",parent.dataID,"edit");
    }
});