jQuery(function()
{
    $ = jQuery ;
    //templatemo_banner_slide camera function
    $('#templatemo_banner_slide > div').camera({
        height: 'auto',
        loader: 'bar',
        playPause: false,
        pagination: false,
        thumbnails: false,
        hover: false,
        opacityOnGrid: false,
        imagePath: 'images/'
    });
    changebg();
    
    banner_h1_margin_top = (($(window).height()-280) - 285)/2;
    $(".camera_caption h1").css("marginTop",banner_h1_margin_top);
    $(window).resize(function(){
        banner_h1_margin_top = (($(window).height()-280) - 285)/2;
        $(".camera_caption h1").css("marginTop",banner_h1_margin_top);
    });
    //mobile menu and desktop menu
    hide_left = $(document).width();
    $("#mobile_menu").css({left: hide_left});
    $("#mobile_menu").hide();
    $("#mobile_menu_btn a").click(function(){
        if($('#mobile_menu').is(':visible')) {
            hide_left = $(document).width();
            $("#mobile_menu").animate({left: hide_left},1000,function(){
                $("#mobile_menu").hide();
            });
        }else{
            $("#mobile_menu").show();
            show_left = $(document).width() - 250 ;
            $("#mobile_menu").animate({left: show_left},1000);
        }
        return false;
    });
    jQuery.fn.anchorAnimate = function(settings) {
        settings = jQuery.extend({
            speed : 1100
        }, settings);	
        return this.each(function(){
            var caller = this
            $(caller).click(function (event){
                event.preventDefault();
                var locationHref = window.location.href;
                var elementClick = $(caller).attr("href");
                var destination = $(elementClick).offset().top ;
                $("#menu_mobile_list").fadeOut() ;
                $("html,body").stop().animate({ scrollTop: destination}, settings.speed, function(){
                    // Detect if pushState is available
                    if(history.pushState) {
                        history.pushState(null, null, elementClick);
                    }
                });
                return false;
            });
        });
    }
    //animate scroll function calll
    $("#mobile_menu a").anchorAnimate();
    //main menu auto select and animate scroll
    //define main menu position
    if($(document).scrollTop() > ($(window).height()-105)){
        $("#templatemo_main_menu").css({"position":"fixed","top":"0"});
    }else{
        menu_top = $(window).height() - 105;
        $("#templatemo_main_menu").css({"position":"absolute","top":menu_top});
    }
    $(window).scroll(function(){
        if($(this).scrollTop() > ($(this).height()-105)){
            $("#templatemo_main_menu").css({"position":"fixed","top":"0"});
        }else{
            menu_top = $(this).height() - 105;
            $("#templatemo_main_menu").css({"position":"absolute","top":menu_top});
        }
    });
    $(window).resize(function(){
        if($(this).scrollTop() > ($(this).height()-105)){
            $("#templatemo_main_menu").css({"position":"fixed","top":"0"});
        }else{
            menu_top = $(this).height() - 105;
            $("#templatemo_main_menu").css({"position":"absolute","top":menu_top});
        }
    });
});
//google map
function initialize(){
    //define map
    var map;
    //lat lng
    myLatlng = new google.maps.LatLng(16.800000, 96.150000);
    //define style
    var styles = [
        {
            //set all color
            featureType: "all",
            stylers: [{ hue: "#35a9d8" }]
        },
        {
            //hide business
            featureType: "poi.business",
            elementType: "labels",
            stylers: [{ visibility: "off" }]
        }
    ];
    //map options
    var mapOptions = {
        zoom: 14,
        center: myLatlng ,
        mapTypeControlOptions: {mapTypeIds: [google.maps.MapTypeId.ROADMAP, 'map_style']} ,
        panControl: false , //hide panControl
        zoomControl: false , //hide zoomControl
        mapTypeControl: false , //hide mapTypeControl
        scaleControl: false , //hide scaleControl
        streetViewControl: false , //hide streetViewControl
        overviewMapControl: false , //hide overviewMapControl
    }
    //adding attribute value
    map = new google.maps.Map(document.getElementById('templatemo_contact_map'), mapOptions);
    var styledMap = new google.maps.StyledMapType(styles,{name: "Styled Map"});
    map.mapTypes.set('map_style', styledMap);
    map.setMapTypeId('map_style');
    //add marker
    var marker = new google.maps.Marker({
        position: myLatlng,
        map: map,
        title: 'Welcome to Yangon'
    });
}

function changebg(){
    banner_slider_height = $(window).outerHeight()-285;
	
	var bheight = document.documentElement.clientHeight;
	if(bheight == 0){bheight = 1000;}
    banner_slider_height = (banner_slider_height<bheight) ? bheight : banner_slider_height;
    $("#templatemo_banner_slide > div").height(banner_slider_height);
    $("#templatemo_banner_slide").height(banner_slider_height);
    $(window).resize(function(){
        banner_slider_height = $(window).outerHeight()-285;
        banner_slider_height = (banner_slider_height<bheight) ? bheight : banner_slider_height;
        $("#templatemo_banner_slide > div").height(banner_slider_height);
        $("#templatemo_banner_slide").height(banner_slider_height);
    });
	
}

window.onresize=function(){
	changebg();
}