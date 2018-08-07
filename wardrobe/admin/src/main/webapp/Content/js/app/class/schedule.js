requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',

        "timepicker"    : "bower_components/jquery-timepicker-wvega/jquery.timepicker",
        "datepicker"    : "bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker",
        "datepicker-zh" : "bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min",
        "alert"         : 'utils/jqueryAlert/alert/alert',

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
        "timepicker": {
            deps: ["jquery"]
        },
        "datepicker": {
            deps: ["jquery", "bootstrap"]
        },
        "datepicker-zh": {
            deps: ["jquery", "datepicker"]
        }
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'alert', 'override', 'bootstrap', 'base', "timepicker", "datepicker", "datepicker-zh"], function ($, jqueryAlert) {
    'use strict';

    function setDatePicker() {
        $('input.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayBtn: true,
            todayHighlight: true,
            toggleActive: true,
            language: "zh-CN",
            daysOfWeekHighlighted: "0,6",
            weekStart: 0
        });
        $('input.timepicker').timepicker({
            timeFormat: 'HH:mm',
            interval: 30
        });
    }
    setDatePicker();

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

    $("[name='classSchedule']").on("change", function (e) {
        e.preventDefault();

        var item = $("[name='classSchedule']:checked").val();

        $(".class-week-list").hide();
        $(".class-date-list").hide();
        $(".class-" + item + "-list").show();
    });

    $(".class-week-list").on("click", ".class-week-del", function (e) {
        e.preventDefault();

        var $this = $(this);
        var $item = $(this).parents(".form-group");
        var size = $(".class-week-list").find(".form-group").length;

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
        $this.attr("submitting", "submitting");

        var submitData = {
            id: $item.find("[name='id']").val()
        };

        if (!submitData.id) {
            $this.attr("submitting", "");

            $item.remove();
            $(".class-week-list").find(".form-group:last-child .class-week-add").show();
            setDatePicker();
            return;
        }

        $.postJSON('/admin/class/deleteClassSchedule', submitData, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                $item.remove();
                $(".class-week-list").find(".form-group:last-child .class-week-add").show();
                setDatePicker();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "删除排期失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    $(".class-week-list").on("click", ".class-week-add", function (e) {
        e.preventDefault();

        var $item = $(this).parents(".form-group");
        var $nextItem = $item.clone();

        $(".class-week-list").append($nextItem);
        $(".class-week-list").find(".form-group:last-child").find("[name='id']").val("").removeAttr("disabled");
        $(".class-week-list").find(".form-group:last-child").find("[name='classWeek']").removeAttr("disabled");
        $(".class-week-list").find(".form-group:last-child").find("[name='startTime']").removeAttr("disabled");
        $(".class-week-list").find(".form-group:last-child").find("[name='endTime']").removeAttr("disabled");
        $(".class-week-list").find(".form-group:last-child").find("[name='coachId']").removeAttr("disabled");
        $(this).hide();
        setDatePicker()
    });

    $(".class-date-list").on("click", ".class-date-del", function (e) {
        e.preventDefault();

        var $this = $(this);
        var $item = $(this).parents(".form-group");
        var size = $(".class-date-list").find(".form-group").length;

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
        $this.attr("submitting", "submitting");

        var submitData = {
            id: $item.find("[name='id']").val()
        };

        if (!submitData.id) {
            $this.attr("submitting", "");

            $item.remove();
            $(".class-date-list").find(".form-group:last-child .class-date-add").show();
            setDatePicker();
            return;
        }

        $.postJSON('/admin/class/deleteClassSchedule', submitData, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                $item.remove();
                $(".class-date-list").find(".form-group:last-child .class-date-add").show();
                setDatePicker();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "删除排期失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    $(".class-date-list").on("click", ".class-date-add", function (e) {
        e.preventDefault();

        var $item = $(this).parents(".form-group");
        var $nextItem = $item.clone();

        $(".class-date-list").append($nextItem);
        $(".class-date-list").find(".form-group:last-child").find("[name='id']").val("").removeAttr("disabled");
        $(".class-date-list").find(".form-group:last-child").find("[name='classDate']").removeAttr("disabled");
        $(".class-date-list").find(".form-group:last-child").find("[name='startTime']").removeAttr("disabled");
        $(".class-date-list").find(".form-group:last-child").find("[name='endTime']").removeAttr("disabled");
        $(".class-date-list").find(".form-group:last-child").find("[name='classDateCoachId']").removeAttr("disabled");
        $(this).hide();
        setDatePicker();
    });

    function __formatSubmitData() {
        var type = $("[name='classSchedule']:checked").val();
        var submitData = [];
        var $item;
        var length, i;

        if (type === "week") {
            $item = $(".class-week-list .schedule-item");
            length = $item.length;
            var startDate = $("#class_schedule_startDate").val();
            var endDate = $("#class_schedule_endDate").val();
            for (i = 0; i < length; i++) {
                submitData.push({
                    id: $item.eq(i).find("[name='id']").val() || "",
                    classWeek: $item.eq(i).find("[name='classWeek']").val(),
                    startDate: startDate,
                    endDate: endDate,
                    startTime: $item.eq(i).find("[name='startTime']").val(),
                    endTime: $item.eq(i).find("[name='endTime']").val(),
                    coachId: $item.eq(i).find("[name='coachId']").val()
                });
            }
        } else {
            $item = $(".class-date-list .schedule-item");
            length = $item.length;
            for (i = 0; i < length; i++) {
                submitData.push({
                    id: $item.eq(i).find("[name='id']").val() || "",
                    classDate: $item.eq(i).find("[name='classDate']").val(),
                    startTime: $item.eq(i).find("[name='startTime']").val(),
                    endTime: $item.eq(i).find("[name='endTime']").val(),
                    coachId: $item.eq(i).find("[name='classDateCoachId']").val()
                });
            }
        }

        return submitData;
    }

    // 保存班级排期
    $(".save-class-schedule").on("click", function (e) {
        e.preventDefault();

        var $form = $("#class_schedule_form");

        if ($form.attr("submitting") == "submitting") {
            return false;
        }

        var classId = $("#class_schedule_id").val();
        var submitData = {};
        submitData.classId = classId;
        submitData.orgClassScheduleList = __formatSubmitData();

        //return console.log(submitData);

        $form.attr("submitting", "submitting");

        $.postJSON('/admin/class/saveClassSchedule', submitData, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "保存排班成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });

                window.setTimeout(function () {
                    window.location.assign("/admin/class/list");
                }, 1500);
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "保存排班失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });
});
