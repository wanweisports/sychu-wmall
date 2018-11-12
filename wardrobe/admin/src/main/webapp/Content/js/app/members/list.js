requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',

        "alert"     : 'utils/jqueryAlert/alert/alert',

        "base"      : 'js/widgets/base',
        "override"  : 'js/widgets/override'
    },
    shim: {
        "bootstrap": {
            deps: ["jquery", "override"]
        },
        "alert": {
            deps: ["jquery"]
        }
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'alert', 'override', 'bootstrap', 'base'], function ($, jqueryAlert) {
    'use strict';

    $.postJSON = function(url, data, callback) {
        return $.ajax({
            'type' : 'POST',
            'url' : url,
            'contentType' : 'application/json',
            'data' : JSON.stringify(data),
            'dataType' : 'json',
            'success' : callback
        });
    };

    $(".wardrobe-list").on("click", ".wardrobe-set", function () {
        var wardrobeId = $(this).parents("tr").attr("data-id");

        $.getJSON('/admin/wardrobe/getInfo', {wardrobeId: wardrobeId}, function (res) {
        });
    });

    $(".save-wardrobe").on("click", function (e) {
        e.preventDefault();

        $.post('/admin/wardrobe/saveInfo', {}, function (res) {
            if (res.code == 1) {
                window.location.reload();
            }
            else {
                var $error = jqueryAlert({
                    'title'   : '警 告',
                    'content' : '保存信息失败！！',
                    'modal'   : true,
                    'buttons' : {
                        '知道了' : function () {
                            $error.close();
                        }
                    }
                })
            }
        });
    });
});
