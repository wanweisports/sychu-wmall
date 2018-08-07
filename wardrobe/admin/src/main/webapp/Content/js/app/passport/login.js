requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',
        "chart"     : 'bower_components/chart.js/dist/Chart',

        "particles" : 'bower_components/particles-js/particles',

        "override"  : 'js/widgets/override'
    },
    shim: {
        "bootstrap": {
            deps: ["jquery", "override"]
        }
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'override', 'bootstrap', 'particles'], function ($) {
    'use strict';

    // 表单校验配置
    /*$(document).ready(function () {
        $('#login_form').validate({
            ignore: ":hidden"
        });
    });*/

    // 登录跳转
    $(".to-login").on("click", function (e) {
        e.preventDefault();

        var $form = $("#login_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting"/* || !$form.valid()*/) {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/passport/submitLogin', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                location.assign("/admin/dashboard/index");
            } else {
                console.log(res.message || "用户登录失败, 请稍后重试");
                alert(res.message || "用户登录失败, 请稍后重试");
            }
        });
    });


    particlesJS("particles-js", {
        "particles": {
            "number": {
                "value": 160,
                "density": {
                    "enable": false,
                    "value_area": 800
                }
            },
            "color": {
                "value": "#ffffff"
            },
            "shape": {
                "type": "circle",
                "stroke": {
                    "width": 0,
                    "color": "#ffffff"
                },
                "polygon": {
                    "nb_sides": 3
                }
            },
            "opacity": {
                "value": 0.5,
                "random": false
            },
            "size": {
                "value": 3,
                "random": false
            },
            "line_linked": {
                "enable": true,
                "distance": 150,
                "color": "#ffffff",
                "opacity": 0.4,
                "width": 1
            },
            "move": {
                "enable": true,
                "speed": 6,
                "direction": "none",
                "random": false,
                "straight": false,
                "out_mode": "out",
                "bounce": false,
                "attract": {
                    "enable": false,
                    "rotateX": 600,
                    "rotateY": 1200
                }
            }
        }
    });

});
