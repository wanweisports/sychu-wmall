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
        $.validator.messages.required = "请至少选择一项";
        $('#coach_edit_form').validate({
            ignore: ":hidden"
        });
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

    $(".add-coach").on("click", function (e) {
        e.preventDefault();

        $("#coach_edit_id").val("");
        $("#coach_edit_realName").val("");
        $("#coach_edit_mobile").val("");
        $("#coach_edit_idCard").val("");
        $("#coach_edit_email").val("");
        $("input[name='roleId']:checked").prop("checked", false);
        $("input[name='sportId']:checked").prop("checked", false);
        $("input[name='venueId']:checked").prop("checked", false);
    });

    // 解锁
    $(".coach-list").on("click", ".coach-open", function (e) {
        e.preventDefault();

        var $this = $(this);
        var coachId = $this.parents("tr").attr("data-id");

        if ($this.attr("submitting") == "submitting") {
            return;
        }
        $this.attr("submitting", "submitting");

        $.post('/admin/venue/unlockCoaches', {coachId: coachId}, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "解锁教练成功",
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
                    'content'   : "启用教练失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 锁定
    $(".coach-list").on("click", ".coach-close", function (e) {
        e.preventDefault();

        var $this = $(this);
        var coachId = $this.parents("tr").attr("data-id");

        if ($this.attr("submitting") == "submitting") {
            return;
        }
        $this.attr("submitting", "submitting");

        $.post('/admin/venue/lockCoaches', {coachId: coachId}, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "锁定教练成功",
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
                    'content'   : "锁定教练失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    $(".coach-list").on("click", ".edit-coach", function (e) {
        e.preventDefault();

        var coachId = $(this).parents("tr").attr("data-id");

        $.getJSON('/admin/venue/getCoach', {coachId: coachId}, function (res) {
            var data = res.data;

            if (res.code == 1) {
                $("#coach_edit_id").val(data.orgCoaches.id);
                $("#coach_edit_realName").val(data.orgCoaches.realName);
                $("#coach_edit_mobile").val(data.orgCoaches.mobile);
                $("#coach_edit_idCard").val(data.orgCoaches.idCard);
                $("#coach_edit_email").val(data.orgCoaches.email);

                $('input[name="roleId"]:checked').prop("checked", false);
                !!data.orgCoachesRolesList && data.orgCoachesRolesList.forEach(function (item) {
                    $('input[name="roleId"][value="' + item.roleId + '"]').prop("checked", true);
                });

                $('input[name="sportId"]:checked').prop("checked", false);
                !!data.orgSportsCoachesList && data.orgSportsCoachesList.forEach(function (item) {
                    $('input[name="sportId"][value="' + item.sportId + '"]').prop("checked", true);
                });

                $('input[name="venueId"]:checked').prop("checked", false);
                !!data.orgVenueCoachesList && data.orgVenueCoachesList.forEach(function (item) {
                    $('input[name="venueId"][value="' + item.venueId + '"]').prop("checked", true);
                });
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "查询教练失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 保存教练
    $(".save-coach").on("click", function (e) {
        e.preventDefault();

        var $form = $("#coach_edit_form");

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        var submitData = {
            orgCoaches : {
                id       : $("#coach_edit_id").val(),
                realName : $("#coach_edit_realName").val(),
                mobile   : $("#coach_edit_mobile").val(),
                idCard   : $("#coach_edit_idCard").val(),
                email    : $("#coach_edit_email").val()
            },
            roleId  : [],
            sportId : [],
            venueId : []
        };

        $("[name='roleId']:checked").each(function () {
            submitData.roleId.push($(this).val());
        });
        submitData.roleId = submitData.roleId.join(",");
        $("[name='sportId']:checked").each(function () {
            submitData.sportId.push($(this).val());
        });
        submitData.sportId = submitData.sportId.join(",");
        $("[name='venueId']:checked").each(function () {
            submitData.venueId.push($(this).val());
        });
        submitData.venueId = submitData.venueId.join(",");

        $.postJSON('/admin/venue/saveCoach', submitData, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "保存教练成功",
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
                    'content'   : "保存教练失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 检索教练
    $(".search-coaches").on("click", function (e) {
        e.preventDefault();

        var $form = $("#coaches_form");
        var conditions = $form.serialize();

        window.location.assign("/admin/venue/coaches?" + conditions);
    });
});
