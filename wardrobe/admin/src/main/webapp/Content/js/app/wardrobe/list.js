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

    $(".members-list").on("click", ".member-stop", function () {
        var memberId = $(this).parents("tr").attr("data-id");

        var confirmAlert = jqueryAlert({
            'title'   : '警 告',
            'content' : '您确定要停用此用户！',
            'modal'   : true,
            'buttons' : {
                '取消' : function () {
                    confirmAlert.close();
                },
                '确定' : function () {
                    confirmAlert.close();

                    $.postJSON('/admin/members/setCloseStatus', {memberId: memberId}, function (res) {
                        if (res.code == 1) {
                            window.location.reload();
                        }
                    });
                }
            }
        })
    });

    // 检索
    $(".members-query-btn").on("click", function (e) {
        e.preventDefault();

        var $form = $("#members_query_form");
        var conditions = $form.serialize();

        window.location.assign("/admin/members/list?" + conditions);
    });
});
