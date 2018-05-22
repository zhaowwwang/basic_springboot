var dataID;
var typeVal = $("#typeVal").val();
if(typeVal == 'list'){
    //search();
    var param = {
        "url": "/systemOrg/getOrgOption.do",
        "select" : true,
        "key":"orgCode",
        "val": "orgName",
        "selId": "selectOrgCode"
    };
    getSelectOption(param);
}else if(typeVal == 'add'){
    getOption("index");
}else if(typeVal == 'edit'){
    initDataToView("/systemRole/viewRole.do",parent.dataID,"edit",null);
}else if(typeVal == 'auth'){
    viewAuth();
}

function search(opts,callback){
    $.ajax({
        url:'/systemRole/getListData.do',
        type:"post",
        data:opts,
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                $("#dataBodyList").html("");
                var appendHtml = '';
                $.each(data.data.list,function(n,value) {
                    appendHtml+='<tr>'+
                        '<td><input type="radio" name="ids" value="'+ value.id +'"/> </td>'+
                        '<td>'+value.roleName+'</td>'+
                        '<td>'+value.orgName+'</td>'+
                        '<td>'+value.remark+'</td>'+
                        '<td>'+value.createTime+'</td>'+
                        '<td>'+value.updateTime+'</td>'+
                        '</tr>';
                });
                $("#dataBodyList").append(appendHtml);
                if(typeof callback == 'function'){
                    callback.call(this,data.data);
                }
            }else {
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
        addIframe("增加角色",'/goToHtml.do?goToHtmlUrl=/system/SystemRoleAdd');
        return;
    }
    dataID = $('input:radio[name="ids"]:checked').val();
    if(dataID == null){
        layer.alert("请先选择！");
        return false;
    }
    if(opt_id == 'edit'){
        addIframe("更新角色信息",'/goToHtml.do?goToHtmlUrl=/system/SystemRoleEdit');
    }else if(opt_id == 'del'){
        updateDataById("/systemRole/delRole.do",dataID);
    }else if(opt_id == 'auth'){
        addIframe("角色授权信息",'/goToHtml.do?goToHtmlUrl=/system/SystemRoleAuth');
    }else if(opt_id == 'pwd'){
        addIframe("用户强制改密",'/goToHtml.do?goToHtmlUrl=/system/SystemUserPwd');
    }
});

$('.confirm').click(function (){
    if(typeVal == 'add'){
        addAndUpdateUtil("/systemRole/addRole.do",null,"add");
    }else if(typeVal == 'edit'){
        addAndUpdateUtil("/systemRole/editRole.do",parent.dataID,"edit");
    }else if(typeVal == 'auth'){
        updateAuth();
    }
});

function updateAuth(){
    var chk_value =[];
    $('input[name="ids"]:checked').each(function(){
        chk_value.push($(this).val());
    });
    if(chk_value.length<=0){
        layer.alert("请选择系统资源！");
        return;
    }
    $.ajax({
        url:"/systemRole/updateRoleAuth.do",
        type:"post",
        data:{
            "roleId":parent.dataID,
            "ids":chk_value
        },
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                layer.alert(data.msg,function(){
                    layer.close();
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    parent.pagesClass.LoadInitHtml();
                });
            }else{
                alert(data.msg);
            }
        },
    });
}

function viewAuth(){
    $.ajax({
        url:'/systemRole/viewRoleAuth.do',
        type:"post",
        data:{
            "id":parent.dataID
        },
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                $("#authBody").html("");
                var appendHtml = '';
                $.each(data.data,function(n,value) {
                    appendHtml+='<tr>'+
                        '<td rowspan="'+ value.secondResourceList.length +'">' +
                        '<label><input name="ids" type="checkbox" value="'+ value.id +'" ';
                        if(value.select){
                            appendHtml+='checked';
                        }
                        appendHtml+='/>'+ value.resourceName +'</label>' +
                        '</td>';
                    $.each(value.secondResourceList,function(t,valueT) {
                        if(t > 0){
                            appendHtml+='<tr>';
                        }
                        appendHtml+='<td>' +
                            '<label><input name="ids" type="checkbox" value="'+ valueT.id +'"';
                            if(valueT.select){
                                appendHtml+='checked';
                            }
                            appendHtml+='/>'+ valueT.resourceName +'</label>' +
                            '</td>';
                        appendHtml+='<td>';
                            $.each(valueT.buttonResourceList,function(s,valueS) {
                                if(s>0 && s%4 == 0){
                                    appendHtml+='<br>';
                                }
                                appendHtml+='<label><input name="ids" type="checkbox" value="'+ valueS.id +'"';
                                if(valueS.select){
                                    appendHtml+='checked';
                                }
                                appendHtml+='/>'+ valueS.resourceName +'</label>';
                            });
                        appendHtml+='</td>';
                        if(t < value.secondResourceList.length-1){
                            appendHtml+='</tr>';
                        }
                    });
                    appendHtml+='</tr>';
                });
                $("#authBody").append(appendHtml);
            }else {
                layer.alert(data.msg);
            }
        }
    });
}

function getOption(object){
    $.ajax({
        url:'/systemOrg/getOrgOption.do',
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                var optionHtml = '';
                var hiddenInputTarget = $('input[name="orgCode"]');
                $.each(data.data,function(n,value) {
                    if((object!=null && value.orgCode == object.orgCode) || (object == 'index' && n == 0)){
                        hiddenInputTarget.next().val(value.orgName);
                        optionHtml += '<span class="optionSelected"';
                    }else{
                        optionHtml += '<span class="optionContent"';
                    }
                    optionHtml += 'rel="'+ value.orgCode+'" id="option'+ value.orgCode +'" onclick="selectData(this.id)">'+ value.orgName +'</span>';
                });
                hiddenInputTarget.parent().next().next().append(optionHtml);
            }else{
                layer.alert(data.msg);
            }
        }
    })
}