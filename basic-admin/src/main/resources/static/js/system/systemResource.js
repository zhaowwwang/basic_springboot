
var typeVal = $("#typeVal").val();
if(typeVal == 'list'){
    //search();
    var param = {
        "url": "/systemResource/getPidResource.do",
        "select" : true,
        "key":"id",
        "val": "resourceName",
        "selId": "selectPid"
    };
    getSelectOption(param);
}else if(typeVal == 'add'){
    var objcet = new Object();
    objcet.pid = 1;
    objcet.resourceStatus = 1;
    objcet.resourceType = 1;
    getOption(objcet);
}else if(typeVal == 'edit'){
    initDataToView("/systemResource/viewResource.do",parent.dataID,"edit",null);
}

function search(opts,callback){
    $.ajax({
        url:'/systemResource/getResourceList.do',
        type:"post",
        data: opts,
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                $("#dataBodyList").html("");
                var appendHtml = '';
                $.each(data.data.list,function(n,value) {
                    appendHtml+='<tr>'+
                        '<td><input type="radio" name="ids" value="'+ value.id +'"/> </td>'+
                        '<td>'+value.resourceName+'</td>'+
                        '<td>'+value.resourceUrl+'</td>'+
                        '<td>'+value.description+'</td>'+
                        '<td>'+value.pidName+'</td>'+
                        '<td>'+value.statusName+'</td>'+
                        '<td>'+value.typeName+'</td>'+
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

var dataID;
//监听右上角的操作按钮
$("#optProject").click(function (e){
    var target = e.target || e.srcElement;
    var opt_id = $(target).attr('opt-id');
    if(opt_id == 'add'){
        addIframe("增加系统资源",'goToHtml.do?goToHtmlUrl=/system/SystemResourceAdd');
        return;
    }
    dataID = $('input:radio[name="ids"]:checked').val();
    if(dataID == null){
        layer.alert("请先选择！");
        return false;
    }
    if(opt_id == 'edit'){
        addIframe("更新角色信息",'/goToHtml.do?goToHtmlUrl=/system/SystemResourceEdit');
    }else if(opt_id == 'del'){
        updateDataById("/systemResource/delResource.do",dataID);
    }
});

function getOption(object,tempName){
    $.ajax({
        url:'/systemResource/getResourceOption.do',
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                var optionHtml = '';
                if(tempName == null || tempName == 'pid'){
                    var hiddenInputTarget = $('input[name="pid"]');
                    $.each(data.data.pidOption,function(n,value) {
                        if((object!=null && value.id == object.pid) || (object == 'index' && n == 0)){
                            hiddenInputTarget.next().val(value.resourceName);
                            optionHtml += '<span class="optionSelected"';
                        }else{
                            optionHtml += '<span class="optionContent"';
                        }
                        optionHtml += 'rel="'+ value.id+'" id="resourceName'+ value.id +'" onclick="selectData(this.id)">'+ value.resourceName +'</span>';
                    });
                    hiddenInputTarget.parent().next().next().append(optionHtml);
                }
                if(tempName == null || tempName == 'resourceStatus'){
                    optionHtml = '';
                    var hiddenInputTarget = $('input[name="resourceStatus"]');
                    $.each(data.data.statusOption,function(n,value) {
                        if(n == object.resourceStatus){
                            hiddenInputTarget.next().val(value);
                            optionHtml += '<span class="optionSelected"';
                        }else{
                            optionHtml += '<span class="optionContent"';
                        }
                        optionHtml += 'rel="'+ n +'" id="resourceStatus'+ n +'" onclick="selectData(this.id)">'+ value +'</span>';
                    });
                    hiddenInputTarget.parent().next().next().append(optionHtml);
                }
                if(tempName == null || tempName == 'resourceType'){
                    optionHtml = '';
                    var hiddenInputTarget = $('input[name="resourceType"]');
                    $.each(data.data.typeOption,function(n,value) {
                        if(n == object.resourceType){
                            hiddenInputTarget.next().val(value);
                            optionHtml += '<span class="optionSelected"';
                        }else{
                            optionHtml += '<span class="optionContent"';
                        }
                        optionHtml += 'rel="'+ n +'" id="resourceType'+ n +'" onclick="selectData(this.id)">'+ value +'</span>';
                    });
                    hiddenInputTarget.parent().next().next().append(optionHtml);
                }
            }else{
                layer.alert(data.msg);
            }
        }
    })
}

$('.confirm').click(function (){
    if(typeVal == 'add'){
        addAndUpdateUtil("/systemResource/addResource.do",null,"add");
    }else{
        addAndUpdateUtil("/systemResource/editResource.do",parent.dataID,"edit");
    }
});