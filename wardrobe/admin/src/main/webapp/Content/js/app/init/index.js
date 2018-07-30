requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',

        "jquery.steps" : 'bower_components/jquery.steps/build/jquery.steps',
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
        "jquery.steps": {
            deps: ["jquery"]
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

require(['jquery', 'alert', 'override', 'bootstrap', 'base', "jquery.steps", 'jquery.validate', 'jquery.validate.unobtrusive'], function ($, jqueryAlert) {
    'use strict';

    // 表单校验配置
    $(document).ready(function () {
        $('#info_form, #admin_form').validate({
            ignore: ":hidden"
        });
    });

    $("#init-steps").steps({
        headerTag: "h3",
        bodyTag: "section",
        autoFocus: false,
        enableKeyNavigation: false,
        enablePagination: false,
        startIndex: 0
    });

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

    // 保存机构信息
    $(".save-settings").on("click", function (e) {
        e.preventDefault();

        var $form= $("#info_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/init/saveSettings', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                $("#admin_id").val(res.data.orgId);
                $("#sport_admin_id").val(res.data.orgId);

                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "初始化机构信息成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });

                $("#init-steps").steps("next");
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "初始化机构信息失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    $(".random-operator").on("click", function (e) {
        e.preventDefault();

        $("#admin_userNo").val("admin" + (new Date()).getTime());
    });

    // 保存超级管理员
    $(".save-operator").on("click", function (e) {
        e.preventDefault();

        var $form= $("#admin_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/init/saveAdmin', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "初始化管理员成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });

                $("#init-steps").steps("next");
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "初始化管理员失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    $(".sport-list").on("click", ".sport-item", function (e) {
       e.preventDefault();

       var $this = $(this);

       if ($this.hasClass("card-accent-success")) {
           $this.removeClass("card-accent-success").addClass("card-accent-secondary");
           $this.find(".sport-setting").removeClass("fa-check-circle-o").addClass("fa-circle-o");
       } else {
           $this.removeClass("card-accent-secondary").addClass("card-accent-success");
           $this.find(".sport-setting").removeClass("fa-circle-o").addClass("fa-check-circle-o");
       }
    });

    // 保存权限
    $(".save-limit").on("click", function (e) {
        e.preventDefault();

        var $this = $(this);

        if ($this.attr("submitting") == "submitting") {
            return false;
        }

        var size = $(".sport-list .sport-item.card-accent-success").length;
        var $success = $(".sport-list .sport-item.card-accent-success");
        if (size <= 0) {
            jqueryAlert({
                'icon'      : '/Content/images/icon-info.png',
                'content'   : "请至少选择一项体育项目",
                'closeTime' : 2000,
                'modal'        : true,
                'isModalClose' : true
            });
            return false;
        }

        $this.attr("submitting", "submitting");

        var submitData = {};
        submitData.orgId = $("#sport_admin_id").val();
        submitData.orgSportsList = [];
        for (var i = 0; i < size; i++) {
            submitData.orgSportsList.push({
                sportIcon: $success.eq(i).attr("data-class"),
                sportName: $success.eq(i).attr("data-ch"),
                sportNameEn: $success.eq(i).attr("data-en")
            });
        }

        $.postJSON('/admin/init/saveSports', submitData, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "开通权限成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });

                $("#init-steps").steps("finish");
                window.setTimeout(function () {
                    window.location.href = '/admin/passport/login';
                }, 1500);
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "开通权限失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });
});
