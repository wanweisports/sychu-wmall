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
        "override": {
            deps: ["tether"]
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
        $('#settings_form').validate({
            ignore: ":hidden"
        });

        $.initCitySelect($("#id_org_address_select"));
    });

    $("#info_is_edit").on("change", function (e) {
        e.preventDefault();

        if (!$(this).prop("checked")) {
            $("#settings_form").find("input,select,textarea").prop("disabled", true);
            $(".save-settings").prop("disabled", true);
        } else {
            $("#settings_form").find("input,select,textarea").prop("disabled", false);
            $(".save-settings").prop("disabled", false);
        }
    });
    $("#info_is_edit").trigger("change");

    // 保存资料
    $(".save-settings").on("click", function (e) {
        e.preventDefault();

        var cityValue = $("#id_org_address_select").val();
        $("#id_org_province").val(cityValue.split("-")[0]);
        $("#id_org_city").val(cityValue.split("-")[1]);
        $("#id_org_district").val(cityValue.split("-")[2]);

        var $form = $("#settings_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/venue/saveSettings', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "保存资料成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            } else {
                jqueryAlert({
                    'icon': '/Content/images/icon-error.png',
                    'content': "保存资料失败，请稍后重试",
                    'closeTime': 2000,
                    'modal': true,
                    'isModalClose': true
                });
            }
        });
    });
});
