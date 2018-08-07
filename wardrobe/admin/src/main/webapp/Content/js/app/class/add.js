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
        "timepicker": {
            deps: ["jquery"]
        },
        "datepicker": {
            deps: ["jquery", "bootstrap"]
        },
        "datepicker-zh": {
            deps: ["jquery", "datepicker"]
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

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'jquery.validate', 'jquery.validate.unobtrusive', "timepicker", "datepicker", "datepicker-zh"], function ($, jqueryAlert) {
    'use strict';

    // 表单校验配置
    $(document).ready(function () {
        $('#class_form').validate({
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

    function __saveClass(callback) {
        var $form= $("#class_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/class/saveClass', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                $("#info_class_id").val(res.data.classId);
                $("#class_schedule_id").val(res.data.classId);

                /*jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "保存班级成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });*/
                callback();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "保存班级失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    }
    
    function __saveClassSchedule(callback) {
        var $form = $("#class_schedule_form");
        var conditions = $form.serializeArray();

        if ($form.attr("submitting") == "submitting") {
            return false;
        }

        var type = $("[name='classSchedule']:checked").val();
        var classId = $("#class_schedule_id").val();
        var startDate = $("#class_schedule_startDate").val();
        var endDate = $("#class_schedule_endDate").val();

        var submitData = {};
        submitData.classId = classId;
        submitData.orgClassScheduleList = [];
        for (var i = 0; i < conditions.length; i++) {
            var item = conditions[i];
            if (item.name === "classWeek" &&
                type === "week" &&
                $.trim(item.value) != "" &&
                $.trim(conditions[i + 1].value) != "" &&
                $.trim(conditions[i + 2].value) != "" &&
                $.trim(conditions[i + 3].value) != "") {
                submitData.orgClassScheduleList.push({
                    classWeek: item.value,
                    startDate: startDate,
                    endDate: endDate,
                    startTime: conditions[i + 1].value,
                    endTime: conditions[i + 2].value,
                    coachId: conditions[i + 3].value
                });
                i += 3;
            }
            if (item.name === "classDate" &&
                type === "date" &&
                $.trim(item.value) != "" &&
                $.trim(conditions[i + 1].value) != "" &&
                $.trim(conditions[i + 2].value) != "" &&
                $.trim(conditions[i + 3].value) != "") {
                submitData.orgClassScheduleList.push({
                    classDate: item.value,
                    startTime: conditions[i + 1].value,
                    endTime: conditions[i + 2].value,
                    coachId: conditions[i + 3].value
                });
                i += 3;
            }
        }

        if (submitData.orgClassScheduleList.length == 0) {
            jqueryAlert({
                'icon'      : '/Content/images/icon-info.png',
                'content'   : "请输入至少一项排期项",
                'closeTime' : 2000,
                'modal'        : true,
                'isModalClose' : true
            });
            return false;
        }

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

                callback();
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "保存排班失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    }

    // 保存班级
    $(".save-class-schedule").on("click", function (e) {
        e.preventDefault();

        __saveClass(function () {
            __saveClassSchedule(function () {
                window.setTimeout(function () {
                    window.location.href = '/admin/class/list';
                }, 1500);
            });
        });
    });

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

    $("[name='classSchedule']").on("change", function (e) {
        e.preventDefault();

        var item = $("[name='classSchedule']:checked").val();

        $(".class-week-list").hide();
        $(".class-date-list").hide();
        $(".class-" + item + "-list").show();
    });

    $(".class-week-list").on("click", ".class-week-del", function (e) {
        e.preventDefault();

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

        $item.remove();
        $(".class-week-list").find(".form-group:last-child .class-week-add").show();
        setDatePicker();
    });

    $(".class-week-list").on("click", ".class-week-add", function (e) {
        e.preventDefault();

        var $item = $(this).parents(".form-group");
        var $nextItem = $item.clone();

        $(".class-week-list").append($nextItem);
        $(this).hide();
        setDatePicker();
    });

    $(".class-date-list").on("click", ".class-date-del", function (e) {
        e.preventDefault();

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

        $item.remove();
        $(".class-date-list").find(".form-group:last-child .class-date-add").show();
        setDatePicker();
    });

    $(".class-date-list").on("click", ".class-date-add", function (e) {
        e.preventDefault();

        var $item = $(this).parents(".form-group");
        var $nextItem = $item.clone();

        $(".class-date-list").append($nextItem);
        $(this).hide();
        setDatePicker();
    });

    // 课程变化
    $("#info_course_id").on("change", function (e) {
        e.preventDefault();

        var courseId = $(this).val();

        $.getJSON('/admin/class/getCoachByCourse', {courseId: courseId}, function (res) {
            var data = res.data;
            var tpl = '<option value="$coachId">$coachName</option>';
            var html = [];

            if (res.code == 1) {
                html.push(
                    tpl.replace("$coachId", "")
                        .replace("$coachName", "选择教练")
                );

                !!data.orgCoachesList && data.orgCoachesList.forEach(function (item) {
                    html.push(
                        tpl.replace("$coachId", item.id)
                            .replace("$coachName", item.realName)
                    );
                });

                $("#info_coach_id").html(html.join(""));
                $(".schedule-coach-id").html(html.join(""));
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
});
