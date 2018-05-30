/**
 * 获取随机数
 * @returns {number}
 * @constructor
 */
function RandomNumBoth(){
    var Max = 5;
    var Min = 1;
    var Range = Max - Min;
    var Rand = Math.random();
    var num = Min + Math.round(Rand * Range);
    return num;
}

/**
 * 增加iframe层-父子操作
 * @param titleName 页面title名称
 * @param contentUrl 需要打开的页面地址
 * @param width 页面宽度
 * @param height 页面高度
 */
function addIframe(titleName,contentUrl,width,height){
    if(width == null){
        width="60%";
    }
    if(height == null){
        height="80%"
    }
    layer.open({
        type: 2,
        title: titleName,
        area: [width,height],
        fixed: false, //不固定
        maxmin: true,
        content: contentUrl,
        anim:RandomNumBoth()
    });
}

/**
 * 校验id为object 所有的input
 * 判断属性 required 是否为true,如果是判断是否为空
 */
function checkInputRequired(object){
    var inputs = $('#'+ object +' input');
    for(var i = 0; i < inputs.length; i++){
        if($(inputs[i]).prop("required") && !$(inputs[i]).val()){
            $(inputs[i]).focus();
            $(inputs[i]).tips({
                side : 2,
                msg : $(inputs[i]).attr('placeholder'),
                bg : '#AE81FF',
                time : 2
            });
            break;
        }
    }
}
/**
 * 增加或更新的通用方法
 * @param url 请求的url地址
 * @param id
 * @param object 页面中需要提交的from的ID
 */
function addAndUpdateUtil(url,id,object){
    checkInputRequired(object);
    $.ajax({
        type : 'post',
        url : url,
        dataType : 'json',
        data : serParam($('#'+object),id),
        success : function (data){
            layer.alert(data.msg,function(){
                layer.close();
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
                parent.pagesClass.LoadInitHtml();
            });
        }
    })
}

/**
 * 序列化$container所有input,select,textarea的key和val
 * @param $container
 * @param id
 * @returns {{}}
 */
function serParam($container,id) {
    var obj = {};
    if(id!=null){obj["id"] = id;}
    $container.find('input,select,textarea').each(function (){
        obj[this.name] = this.value;
        if(this.nodeName.toLowerCase() == 'select'){
            obj[this.name] = $(this).find('option:selected').val();
        }
    });
    return obj;
}

/**
 * 根据ID删除的通用方法
 * @param url
 * @param id
 * @param alertVal
 */
function updateDataById(url, id, alertVal){
    alertVal = alertVal!=null ? alertVal :"确定删除？";
    layer.confirm(alertVal,
        {icon: 3, title:'警告',anim:6},function(index){
            $.ajax({
                url:url,
                type:"post",
                data:{
                    "id":id
                },
                dataType:"json",
                success:function(data){
                    layer.alert(data.msg,function(index){
                        pagesClass.LoadInitHtml();
                        layer.close(index)
                    });
                },
            });
            layer.close(index);
        });
}

/**
 * @Author: wangzw
 * @Description: 更新查看页面初始化数据
 * @param: noFill 过滤属性
 * @Version: 1.0
 * @Date: 2017/11/4 11:54
 */
function initDataToView(url,id,object,noFill){
    $.ajax({
        url:url,
        type:"post",
        data:{
            "id":id
        },
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                var inputs = $('#'+ object +' input,select,img,a,textarea,div');
                var tempNode;
                for(var i = 0; i < inputs.length; i++){
                    tempNode = $(inputs[i])[0];
                    var tempName = tempNode.name;
                    if(tempName == '' || (noFill!=null && noFill.indexOf(tempName) >= 0)){
                        continue;
                    }
                    if(tempNode.nodeName.toLowerCase() == 'input'){
                        tempNode.value = data.data[tempName];
                    }else if(tempNode.nodeName.toLowerCase() == 'div' && tempNode.className == 'divLineBox'){
                        getOption(data.data);
                    }else if(tempNode.nodeName.toLowerCase() == 'img'){
                        tempNode.src = isEmpty(data.data[tempName]) ? "/static/images/no-data.png" : data.data[tempName];
                    }else if(tempNode.nodeName.toLowerCase() == 'a'){
                        tempNode.href = isEmpty(data.data[tempName]) ? "/static/images/no-data.png" : data.data[tempName];
                    }else if(tempNode.nodeName.toLowerCase() == 'textarea'){
                        tempNode.value = data.data[tempName];
                    }
                }
            }
        },
    });
}

function isEmpty(obj) {
    if (obj === null) return true;
    if (typeof obj === 'undefined') {
        return true;
    }
    if (typeof obj === 'string') {
        if (obj === "") {
            return true;
        }
        var reg = new RegExp("^([ ]+)|([　]+)$");
        return reg.test(obj);
    }
    return false;
}

/**
 * @Author: wangzw
 * @Description: 表格点击tr选中本条记录
 * @Version: 1.0
 * @Date: 2017/11/13 14:31
 */
$('#dataBodyList').on('click', function (e){
    var target = e.target || e.srcElement;
    $(target).closest('tr').find('input:radio').prop('checked',true);
});

/**
 * 上传文件
 * @Author: wangzw
 * @Description: 
 * @Version: 1.0
 * @Date: 2017/11/18 11:47
 */
$('#fileTable').on('change', function(e) {
    var target = e.target;
    if (target.nodeName == 'INPUT') {
        var file = target.files[0];
        if(file!= null && !/\.(jpg|png|jpeg|gif)/.test(file.name)){
            layer.alert("请上传jpg|png|jpeg|gif等格式图片");
            return;
        }
        var fd = new FormData();
        fd.append("imgFile", file);
        $.ajax({
            url: "/upload/uploadFile.do",
            type: "POST",
            processData: false,
            contentType: false,
            data: fd,
            dataType:"json",
            success: function(data) {
                if (data.code == 200) {
                    target.previousElementSibling.value = data.data;
                    target.nextElementSibling.style.backgroundImage = 'url(' + data.data + ')';
                    target.nextElementSibling.style.backgroundSize = '100% 100%';
                    return;
                }else {
                    layer.alert(data.msg);
                }
            }
        });
    }
});

/**
 * @Author: wangzw
 * @Description: 页面查询
 * @Version: 1.0
 * @Date: 2017/11/19 11:23
 */
function clickSearch(){
    pagesClass.LoadInitHtml();
}

/**
 * @Author: wangzw
 * @Description:
 * @Version: 1.0
 * @Date: 2017/11/19 12:27
 *     var param = {
        "url": "/ofType/getOfTypeData.do",
        "select" : true,
        "key":"acode",
        "val": "aname",
        "selId": "typeOptionHtml"
    };
 */
function getSelectOption(param){
    $.ajax({
        url: param.url,
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                if(param.select){
                    var optionHtml = '<option value="">请选择..</option>';
                }else{
                    var optionHtml = '';
                }
                $("#"+ param.selId).html("");
                $.each(data.data,function(n,value) {
                    optionHtml+='<option value="'+ value[param.key] +'" ';
                    optionHtml+='>'+ value[param.val] +'</option>'
                });
                $("#"+ param.selId).append(optionHtml);
            }else{
                layer.alert(data.msg);
            }
        }
    })
}