requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',
        "chart"     : 'bower_components/chart.js/dist/Chart',

        "base"      : 'js/widgets/base',
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

require(['jquery', 'override', 'bootstrap', 'base'], function ($) {
    'use strict';

    // 表单校验配置
    /*$(document).ready(function () {
        $('#login_form').validate({
            ignore: ":hidden"
        });
    });*/

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

    $("#income_form").on("click", ".del-settings", function (e) {
        e.preventDefault();

        var $item = $(this).parents(".form-group");
        var size = $("#income_form").find(".form-group").length;

        if (size <= 1) {
            alert("不能删除仅剩下的最后一项！");
            return false;
        }

        $item.remove();
    });

    $("#income_form").on("click", ".add-settings", function (e) {
        e.preventDefault();

        var $item = $(this).parents(".form-group");
        var $nextItem = $item.clone();

        $("#income_form").append($nextItem);
        $(this).hide();
    });

    // 保存收入项
    $(".save-income-settings").on("click", function (e) {
        e.preventDefault();

        var $form = $("#income_form");
        var conditions = $form.serializeArray();

        if ($form.attr("submitting") == "submitting"/* || !$form.valid()*/) {
            return false;
        }
        $form.attr("submitting", "submitting");

        var submitData = {};
        submitData.balanceType = 1;
        submitData.orgBalanceSettingsList = [];
        for (var i = 0; i < conditions.length; i++) {
            var item = conditions[i];

            submitData.orgBalanceSettingsList.push({
                balanceName: item.value,
                priority: i + 1
            });
        }

        $.postJSON('/admin/data/saveBalanceSettings', submitData, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                alert(res.message || "保存收入设置成功");
            } else {
                console.log(res.message || "保存收入设置失败, 请稍后重试");
                alert(res.message || "保存收入设置失败, 请稍后重试");
            }
        });
    });

    $("#expend_form").on("click", ".del-settings", function (e) {
        e.preventDefault();

        var $item = $(this).parents(".form-group");
        var size = $("#expend_form").find(".form-group").length;

        if (size <= 1) {
            alert("不能删除仅剩下的最后一项！");
            return false;
        }

        $item.remove();
    });

    $("#expend_form").on("click", ".add-settings", function (e) {
        e.preventDefault();

        var $item = $(this).parents(".form-group");
        var $nextItem = $item.clone();

        $("#expend_form").append($nextItem);
        $(this).hide();
    });

    // 保存支出项
    $(".save-expend-settings").on("click", function (e) {
        e.preventDefault();

        var $form = $("#expend_form");
        var conditions = $form.serializeArray();

        if ($form.attr("submitting") == "submitting"/* || !$form.valid()*/) {
            return false;
        }
        $form.attr("submitting", "submitting");

        var submitData = {};
        submitData.balanceType = 2;
        submitData.orgBalanceSettingsList = [];
        for (var i = 0; i < conditions.length; i++) {
            var item = conditions[i];

            submitData.orgBalanceSettingsList.push({
                balanceName: item.value,
                priority: i + 1
            });
        }

        $.postJSON('/admin/data/saveBalanceSettings', submitData, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                alert(res.message || "保存支出设置成功");
            } else {
                console.log(res.message || "保存支出设置失败, 请稍后重试");
                alert(res.message || "保存支出设置失败, 请稍后重试");
            }
        });
    });
});
