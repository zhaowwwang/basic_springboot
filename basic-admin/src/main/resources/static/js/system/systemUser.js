var dataID;
var typeVal = $("#typeVal").val();
if(typeVal == 'list'){
    //search();
}else if(typeVal == 'add'){
    getOption("index");
}else if(typeVal == 'edit' || typeVal == 'pwd'){
    var noFill = [];
    if(typeVal == 'pwd'){
        noFill.push("password");
    }
    initDataToView("/systemUser/viewUser.do",parent.dataID,typeVal,noFill);
}else if(typeVal == 'role'){
    initUserRole();
}

function search(opts,callback){
    $.ajax({
        url:'/systemUser/getListData.do',
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
                    '<td>'+value.userName+'</td>'+
                    '<td>'+value.realName+'</td>'+
                    '<td>'+value.orgName+'</td>'+
                    '<td>'+value.userMobile+'</td>'+
                    '<td>'+value.userEmail+'</td>';
                    if(value.userStatus == '1'){
                        appendHtml+='<td>有效</td>';
                    }else {
                        appendHtml+='<td>无效</td>';
                    }
                    appendHtml+='<td>'+value.updateTime+'</td>'+
                    '</tr>';
                });
                $("#dataBodyList").append(appendHtml);
                if(typeof callback == 'function'){
                    callback.call(this,data.data);
                }
            }else{
                layer.alert(data.msg);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
        }
    });
}

//监听右上角的操作按钮
$("#optProject").click(function (e){
    var target = e.target || e.srcElement;
    var opt_id = $(target).attr('opt-id');
    if(opt_id == 'add'){
        addIframe("增加用户信息",'/goToHtml.do?goToHtmlUrl=/system/SystemUserAdd');
        return;
    }
    dataID = $('input:radio[name="ids"]:checked').val();
    if(dataID == null){
        layer.alert("请先选择！");
        return false;
    }
    if(opt_id == 'edit'){
        addIframe("更新用户信息",'/goToHtml.do?goToHtmlUrl=/system/SystemUserEdit');
    }else if(opt_id == 'del'){
        updateDataById("/systemUser/delUser.do",dataID);
    }else if(opt_id == 'role'){
        addIframe("用户授权角色",'/goToHtml.do?goToHtmlUrl=/system/SystemUserRole');
    }else if(opt_id == 'pwd'){
        addIframe("用户强制改密",'/goToHtml.do?goToHtmlUrl=/system/SystemUserPwd');
    }
});

$('.confirm').click(function (){
    if(typeVal == 'add'){
        addAndUpdateUtil("/systemUser/addUser.do",null,"add");
    }else if(typeVal == 'edit') {
        addAndUpdateUtil("/systemUser/updateUser.do", parent.dataID, "edit");
    }else if(typeVal == 'pwd'){
        addAndUpdateUtil("/systemUser/updatePwd.do",parent.dataID,"pwd");
    }else if(typeVal == 'role'){
        updateUserRole();
    }
});

/**
 * @param objcet 可以为数据对象，可以为空，可以为index(表示自动赋值为第一个)
 */
function getOption(object){
    $.ajax({
        url:'/systemOrg/getOrgOption.do',
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                var optionHtml = '';
                var hiddenInputTarget = $('input[name="organizationId"]');
                $.each(data.data,function(n,value) {
                    if((object!=null && value.id == object.organizationId) || (object == 'index' && n == 0)){
                        hiddenInputTarget.next().val(value.orgName);
                        optionHtml += '<span class="optionSelected"';
                    }else{
                        optionHtml += '<span class="optionContent"';
                    }
                    optionHtml += 'rel="'+ value.id+'" id="option'+ value.id +'" onclick="selectData(this.id)">'+ value.orgName +'</span>';
                });
                hiddenInputTarget.parent().next().next().append(optionHtml);
            }else{
                layer.alert(data.msg);
            }
        }
    })
}

function initUserRole(){
    $.ajax({
        url:'/systemUser/getRoleList.do',
        type:"post",
        data:{
            "userId":parent.dataID
        },
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                var optionHtml = '';
                $("#authBody").html("");
                $.each(data.data,function(n,value) {
                    if(n %4 == 0){
                        optionHtml+='<tr>';
                    }
                    optionHtml+='<td>' +
                        '<label><input name="ids" type="checkbox" value="'+ value.roleId +'" ';
                    if(value.select){
                        optionHtml+='checked';
                    }
                    optionHtml+='/>'+ value.roleName +'</label>' +
                        '</td>';
                    if(n == data.data.length-1 || (n > 0 && n%3 == 0)){
                        optionHtml+='</tr>';
                    }
                });
                $("#authBody").append(optionHtml);
            }else{
                layer.alert(data.msg);
            }
        }
    })
}
function updateUserRole(){
    var chk_value =[];
    $('input[name="ids"]:checked').each(function(){
        chk_value.push($(this).val());
    });
    if(chk_value.length<=0){
        layer.alert("请选择系统角色！");
        return;
    }
    $.ajax({
        url:"/systemUser/updateUserRole.do",
        type:"post",
        data:{
            "userId":parent.dataID,
            "roleIds":chk_value
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
                layer.alert(data.msg);
            }
        },
    });
}
