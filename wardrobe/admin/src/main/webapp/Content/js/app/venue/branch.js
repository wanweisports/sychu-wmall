requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',

        "city"      : 'utils/citySelect/citySelect',
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
        "city": {
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

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'city', 'jquery.validate', 'jquery.validate.unobtrusive'], function ($, jqueryAlert) {
    'use strict';

    // 表单校验配置
    $(document).ready(function () {
        $('#branch_form').validate({
            ignore: ":hidden"
        });

        $.initCitySelect($("#id_org_address_select"));
    });

    // 启用
    $(".branch-list").on("click", ".branch-open", function (e) {
        e.preventDefault();

        var $this = $(this);
        var venueId = $this.parents("tr").attr("data-id");

        if ($this.attr("submitting") == "submitting") {
            return;
        }
        $this.attr("submitting", "submitting");

        $.post('/admin/venue/openBranch', {venueId: venueId}, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "启用场馆成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });

                window.setTimeout(function () {
                    window.location.reload();
                }, 1500);
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "启用场馆失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 关闭
    $(".branch-list").on("click", ".branch-close", function (e) {
        e.preventDefault();

        var $this = $(this);
        var venueId = $this.parents("tr").attr("data-id");

        if ($this.attr("submitting") == "submitting") {
            return;
        }
        $this.attr("submitting", "submitting");

        $.post('/admin/venue/closeBranch', {venueId: venueId}, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "关闭场馆成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });

                window.setTimeout(function () {
                    window.location.reload();
                }, 1500);
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "关闭场馆失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 保存场馆
    $(".save-branch").on("click", function (e) {
        e.preventDefault();

        var cityValue = $("#id_org_address_select").val();
        $("#id_org_province").val(cityValue.split("-")[0]);
        $("#id_org_city").val(cityValue.split("-")[1]);
        $("#id_org_district").val(cityValue.split("-")[2]);

        var $form = $("#branch_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/venue/saveBranch', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "设置场馆成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
                window.setTimeout(function () {
                    window.location.reload();
                }, 1500);
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "设置场馆失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });
});
