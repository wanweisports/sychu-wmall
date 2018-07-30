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

    $("#skill_form").on("click", ".delete-skill", function (e) {
        e.preventDefault();

        var $this = $(this);
        var $item = $(this).parents(".form-group");
        var size = $("#skill_form").find(".form-group").length;

        if (size <= 1) {
            jqueryAlert({
                'icon'      : '/Content/images/icon-info.png',
                'content'   : "不能删除仅剩下的最后一项",
                'closeTime' : 2000,
                'modal'        : true,
                'isModalClose' : true
            });
            return false;
        }

        if ($this.attr("submitting") == "submitting") {
            return false;
        }

        var submitData = {
            id: $item.find("[name='id']").val(),
            skillName: $item.find("[name='skillName']").val()
        };

        $.postJSON('/admin/venue/deleteSportsSkills', submitData, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                $item.remove();
                $("#skill_form").find(".form-group:last-child .add-skill").show();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "删除评测技能失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    $("#skill_form").on("click", ".add-skill", function (e) {
        e.preventDefault();

        var $item = $(this).parents(".form-group");
        var $nextItem = $item.clone();

        $("#skill_form").append($nextItem);
        $("#skill_form").find(".skill-item:last-child").find("[name='id']").val("").removeAttr("disabled");
        $("#skill_form").find(".skill-item:last-child").find("[name='skillName']").val("").removeAttr("disabled");
        $("#skill_form").find(".skill-item:last-child").find("[name='maxValue']").removeAttr("disabled");
        $(this).hide();
    });
    
    function __formatSubmitData() {
        var $item = $("#skill_form .skill-item");
        var length = $item.length;
        var submitData = [];

        for (var i = 0; i < length; i++) {
            submitData.push({
                sportId: $("#id_skill_sportId").val(),
                skillName: $item.eq(i).find("[name='skillName']").val().trim(),
                id: $item.eq(i).find("[name='id']").val().trim(),
                maxValue: $item.eq(i).find("[name='maxValue']").val().trim()
            });
        }

        return submitData;
    }

    // 保存技能
    $(".save-skill").on("click", function (e) {
        e.preventDefault();

        var $form = $("#skill_form");

        if ($form.attr("submitting") == "submitting") {
            return false;
        }

        var submitData = {};
        submitData.sportId = $("#id_skill_sportId").val();
        submitData.orgSportsSkillsList = __formatSubmitData();

        if (submitData.sportId == "") {
            jqueryAlert({
                'icon'      : '/Content/images/icon-info.png',
                'content'   : "请先选择一项体育项目",
                'closeTime' : 2000,
                'modal'        : true,
                'isModalClose' : true
            });
            return false;
        }
        if (submitData.orgSportsSkillsList.length == 0) {
            jqueryAlert({
                'icon'      : '/Content/images/icon-info.png',
                'content'   : "请输入至少一项评测技能",
                'closeTime' : 2000,
                'modal'        : true,
                'isModalClose' : true
            });
            return false;
        }

        $form.attr("submitting", "submitting");

        $.postJSON('/admin/venue/saveSportsSkills', submitData, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "保存评测技能成功",
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
                    'content'   : "保存评测技能失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });
});
