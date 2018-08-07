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

    $(".class-list").on("click", ".class-refresh", function (e) {
        e.preventDefault();

        var classId = $(this).parents("tr").attr("data-id");
        var classStatus = $(this).parents("tr").attr("data-status");

        $("#info_class_id").val(classId);

        $("#class_status").find(".btn-outline-primary.active").removeClass("active");
        $("#class_status").find("input[name='status']:checked").prop("checked", false);

        $("#class_status").find("input[name='status'][value='" + classStatus + "']").prop("checked", true);
        $("#class_status").find("input[name='status'][value='" + classStatus + "']").parents(".btn-outline-primary").addClass("active");
    });

    // 保存班级状态
    $(".save-class-status").on("click", function (e) {
        e.preventDefault();

        var $form = $("#class_status_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting") {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/class/saveClassStatus', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "保存班级状态成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
                window.setTimeout(function () {
                    window.location.reload();
                }, 1500);
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "保存班级状态失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 检索
    $(".search-class").on("click", function (e) {
        e.preventDefault();

        var $form = $("#class_form");
        var conditions = $form.serialize();

        window.location.assign("/admin/class/list?" + conditions);
    });
});
