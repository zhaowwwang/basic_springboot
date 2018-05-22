var timer;
//晃动屏幕
function showShake(){
    fhi = 1;
    //关闭提示晃动屏幕，注释掉这句话即可
    timer = setInterval(xzfh2, 10);
};
var current = 0;
function xzfh(){
    current = (current)%360;
    document.body.style.transform = 'rotate('+current+'deg)';
    current ++;
    if(current>360){current = 0;}
};
var fhi = 1;
var current2 = 1;
function xzfh2(){
    if(fhi>50){
        document.body.style.transform = 'rotate(0deg)';
        clearInterval(timer);
        return;
    }
    current = (current2)%360;
    document.body.style.transform = 'rotate('+current+'deg)';
    current ++;
    if(current2 == 1){current2 = -1;}else{current2 = 1;}
    fhi++;
};


//服务器校验
function severCheck(){
    if(clientCheck()){
        var loginname = $("#loginname").val();
        var password = $("#password").val();
        var code = loginname+",wzw,"+password+",wzw,"+$("#verifyCode").val();
        $.ajax({
            type: "POST",
            url: '/login_confirmLogin.do',
            data: {loginData:code,tm: new Date().getTime()},
            dataType:'json',
            cache: false,
            success: function(data){
                if(data.code==200){
                    window.location.href="/systemIndex.do";
                }else{
                    var tipsName = "loginname";
                    if(data.code == 98){
                        tipsName="verifyCode";
                    }else if (data.code == 97){
                        tipsName="password";
                    }
                    $("#"+tipsName).tips({
                        side : 1,
                        msg : data.msg,
                        bg : '#FF5080',
                        time : 3
                    });
                    showShake();
                    $("#"+tipsName).focus();
                    return false;
                }
            }
        });
    }
}

$(document).ready(function() {
    changeCode();
    $("#codeImg").bind("click", changeCode);
});

$(document).keyup(function(event) {
    if (event.keyCode == 13) {
        $("#to-recover").trigger("click");
    }
});

function genTimestamp() {
    var time = new Date();
    return time.getTime();
}

function changeCode() {
    $("#codeImg").attr("src","/verifyCode.do?time="+genTimestamp());
}

//客户端校验
function clientCheck() {
    if($("#loginname").val() == "") {
        $("#loginname").tips({
            side : 2,
            msg : '用户名不得为空',
            bg : '#AE81FF',
            time : 3
        });
        showShake();
        $("#loginname").focus();
        return false;
    } else {
        $("#loginname").val(jQuery.trim($('#loginname').val()));
    }
    if ($("#password").val() == "") {
        $("#password").tips({
            side : 2,
            msg : '密码不得为空',
            bg : '#AE81FF',
            time : 3
        });
        showShake();
        $("#password").focus();
        return false;
    }
    if ($("#verifyCode").val() == "") {
        $("#verifyCode").tips({
            side : 1,
            msg : '验证码不得为空',
            bg : '#AE81FF',
            time : 3
        });
        showShake();
        $("#verifyCode").focus();
        return false;
    }
    $("#loginbox").tips({
        side : 1,
        msg : '正在登录 , 请稍后 ...',
        bg : '#68B500',
        time : 5
    });
    return true;
}