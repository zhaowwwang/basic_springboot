$(function () {
    // 左侧
    $(".managementList").click(function () {
        $(this).toggleClass("shadow").siblings(".child_menu").slideToggle()
            .parent("li").siblings().children("a").removeClass("shadow")
            .next(".child_menu").slideUp();
    });

    $(".child_menu li").click(function () {
        var targetHref = $(this).attr("targetHref");
        $("#iframe").attr("src", targetHref);
        $(this).css({"background": "#374A5E"}).children("a").css({"color": "#fff"})
            .parent().siblings("li").css({"background": "#2A3F54"}).children("a").css({"color": "#e7e7e7"});
        $('.child_menu li').removeClass('actives');
        $(this).addClass('actives');
    });

    // 主页
    $("#mainHome").click(function () {
        $("#iframe").attr("src", 'main_index.html');
    });

    // 退出
    $("#logout").click(function () {
        //$(".mengban ,.quitCon").show();
        layer.confirm("确定要退出系统？",
            {icon: 3, title:'警告',anim:1,area: ['30%','30%'],},function(index){
                $.ajax({
                    url: "/login_toLogOut.do",
                    type:"post",
                    dataType:"json",
                    success:function(data){
                        if(data.code == 200){
                            parent.window.location.href="/login_toLogin.do";
                        }
                    },
                });
                layer.close(index);
            });
    });

    $(".btns span").click(function () {
        $(".mengban ,.quitCon ,.remove,.quitCon2").hide();
    })

    // 添加项目
    $(".addProject").click(function () {
        $(".itemCon , .mengban,.addpreject").show();
    });

    $(".btn span").click(function () {
        $(".itemCon,.mengban,.choice").hide();
    });

    // 左侧目录点击添加选中样式
    $('.list>li li').click(function (){
        $('.notice').html(' 当前位置：' + $(this).parent().prev().text() + ' >> ' + $(this).text());
    })
});

$('.mainLogOut').click(function (){
    $.ajax({
        url:'/login_toLogOut.do',
        type:"post",
        dataType:"json",
        success:function(data){
            if(data.code == 200){
                parent.window.location.href="/login_toLogin.do";
            }
        }
    })
});
