requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',
        "chart"     : 'bower_components/chart.js/dist/Chart',

        "alert"     : 'utils/jqueryAlert/alert/alert',

        "jquery.validate"              : 'bower_components/jquery.validation/dist/jquery.validate',
        "jquery.validate.unobtrusive"  : 'bower_components/Microsoft.jQuery.Unobtrusive.Validation/jquery.validate.unobtrusive',

        "base"      : 'js/widgets/base',
        "override"  : 'js/widgets/override'
    },
    shim: {
        "bootstrap": {
            deps: ["jquery", "override"]
        },
        "alert": {
            deps: ["jquery"]
        },
        "jquery.validate": {
            deps: ["jquery", "override"]
        },
        "jquery.validate.unobtrusive": {
            deps: ["jquery", "jquery.validate"]
        }
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'jquery.validate', 'jquery.validate.unobtrusive'], function ($, jqueryAlert) {
    'use strict';

    // 表单校验配置
    $(document).ready(function () {
        $('#balance_settings_form').validate();
    });

    // 设置
    $("#bs_settings").on("click", function (e) {
        e.preventDefault();

        var $form = $("#balance_settings_form");
        var conditions = $form.serialize();

        if ($form.attr("working") == "working" || !$form.valid()) {
            return false;
        }
        $form.attr("working", "working");

        $.post("/admin/members/recharge/addRecharge", conditions, function (res) {
            $form.attr("working", "");

            if (res.code == 1) {
                window.location.reload();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "设置充值金额失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 删除
    $(".balance-list").on("click", ".balance-delete", function (e) {
        e.preventDefault();

        var $this = $(this);

        var $dialog = jqueryAlert({
            'title'   : '提示',
            'content' : '您确定要删除此充值金额设定？',
            'modal'   : true,
            'buttons' :{
                '确定' : function () {
                    $dialog.close();

                    if ($this.attr("working") == "working") {
                        return false;
                    }
                    $this.attr("working", "working");

                    $.post("/admin/members/recharge/deleteRecharge", {dictId: $this.attr("data-id")}, function (res) {
                        $this.attr("working", "");

                        if (res.code == 1) {
                            window.location.reload();
                        } else {
                            jqueryAlert({
                                'icon'      : '/Content/images/icon-error.png',
                                'content'   : "充值设置删除失败, 请稍后重试",
                                'closeTime' : 2000,
                                'modal'        : true,
                                'isModalClose' : true
                            });
                        }
                    });
                },
                '取消' : function(){
                    $dialog.close();
                }
            }
        });
    });

});
