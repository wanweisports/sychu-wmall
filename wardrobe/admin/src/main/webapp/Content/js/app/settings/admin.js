requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',

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
        $('#admin_form').validate({
            ignore: ":hidden"
        });
    });

    $(".random-operator").on("click", function (e) {
        e.preventDefault();

        $("#admin_userNo").val("admin" + (new Date()).getTime());
    });

    $(".add-operator").on("click", function (e) {
        e.preventDefault();

        $("#admin_id").val("");
        $("#admin_userNo").val("").removeAttr("readonly");
        $("#admin_userName").val("").removeAttr("readonly");
        $("#admin_realName").val("");
        $("#admin_mobile").val("");
        $("#admin_idCard").val("");
        $("#admin_email").val("");
    });

    $(".admin-list").on("click", ".operator-edit", function (e) {
        e.preventDefault();

        var adminId = $(this).parents("tr").attr("data-id");

        $.getJSON('/admin/settings/getAdmin', {adminId: adminId}, function (res) {
            var data = res.data;

            if (res.code == 1) {
                $("#admin_id").val(data.orgOperator.id);
                $("#admin_userNo").val(data.orgOperator.userNo).attr("readonly", "readonly");
                $("#admin_userName").val(data.orgOperator.userName).attr("readonly", "readonly");
                $("#admin_realName").val(data.orgOperator.realName);
                $("#admin_mobile").val(data.orgOperator.mobile);
                $("#admin_idCard").val(data.orgOperator.idCard);
                $("#admin_email").val(data.orgOperator.email);
                $("#admin_roleId").val(data.orgOperator.roleId);
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "查询管理员失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 保存管理员
    $(".save-operator").on("click", function (e) {
        e.preventDefault();

        var $form = $("#admin_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/settings/saveAdmin', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "保存管理员成功",
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
                    'content'   : "保存管理员失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 检索管理员
    $(".search-admin").on("click", function (e) {
        e.preventDefault();

        var $form = $("#admin_search_form");
        var conditions = $form.serialize();

        window.location.assign("/admin/settings/admin?" + conditions);
    });
});
