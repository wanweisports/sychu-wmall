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
    
    function __renderClassStudentsAttendance(classId, classDate, classStatus) {
        var template = [
            '<tr>',
            '<td>${index}</td>',
            '<td>${studentName}</td>',
            '<td>${studentMobile}</td>',
            '<td>${studentSex}</td>',
            '<td class="${studentStatus}">${studentAttendance}</td>',
            ' </tr>'
        ].join('');
        var btnTemplate = '<a href="javascript:;" class="btn btn-sm btn-primary student-attendance" data-user="${studentId}">' +
            '<i class="fa fa-paw"></i> 签到' +
            '</a>';

        $.getJSON('/admin/class/getClassAttendance', {classId: classId, classDate: classDate}, function (res) {
            var data = res.data;
            var list = [], hasCloseClass = false;

            if (res.code == 1) {
                !!data.orgStudentsResponseList && data.orgStudentsResponseList.forEach(function (item, index) {
                    var listItem = template.replace("${index}", index + 1)
                        .replace("${studentName}", item.orgStudents.realName)
                        .replace("${studentMobile}", item.orgStudents.mobile)
                        .replace("${studentSex}", item.orgStudents.sex == 1 ? "男" : "女")
                        .replace("${studentStatus}", item.orgAttendance ? "text-muted" : "text-danger");

                    hasCloseClass = !!item.orgAttendanceCoach;

                    if (hasCloseClass || classStatus == 3) {
                        listItem = listItem.replace("${studentAttendance}", item.orgAttendance ? "已上课" : "未上课");
                    } else {
                        listItem = listItem.replace("${studentAttendance}",
                            item.orgAttendance ? "已上课" : btnTemplate.replace("${studentId}", item.orgStudents.id));
                    }

                    list.push(listItem);
                });

                $("#attendance_list .user-list tbody").html(list.join(""));

                if (hasCloseClass || classStatus == 3) {
                    $("#attendance_list .close-class-date").hide();
                } else {
                    $("#attendance_list .close-class-date").show();
                }
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "查询签到记录失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    }

    $("#attendance_list .user-list").on("click", ".student-attendance", function (e) {
        e.preventDefault();

        var $this = $(this);
        var conditions = {
            inDate: $("#attendance_date").val(),
            inScheduleId: $("#attendance_schedule").val(),
            inRole: 3,
            inUserId: $this.attr("data-user"),
            inClassID: $("#attendance_classId").val()
        };

        if ($this.attr("submitting") == "submitting") {
            return false;
        }
        $this.attr("submitting", "submitting");

        $.post('/admin/class/signClassAttendance', conditions, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "学员签到成功！",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
                __renderClassStudentsAttendance(conditions.inClassID, conditions.inDate, 2);
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "签到失败, 请稍后重试！",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    $("#attendance_list .close-class-date").on("click", function (e) {
        e.preventDefault();

        var $this = $(this);
        var conditions = {
            inDate: $("#attendance_date").val(),
            inScheduleId: $("#attendance_schedule").val(),
            inRole: 1,
            inUserId: $("#attendance_coach").val(),
            inClassID: $("#attendance_classId").val()
        };

        if ($this.attr("submitting") == "submitting") {
            return false;
        }
        $this.attr("submitting", "submitting");

        $.post('/admin/class/signClassAttendance', conditions, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "此班次结课成功！",
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
                    'content'   : "此班次结课失败, 请稍后重试！",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    $(".class-date-list").on("click", ".class-date", function (e) {
        e.preventDefault();

        var classId = $(this).attr("data-id");
        var classDate = $(this).attr("data-date");
        var classSchedule = $(this).attr("data-schedule");
        var classStatus = $(this).attr("data-status");
        var classCoach = $(this).attr("data-coach");

        $("#attendance_classId").val(classId);
        $("#attendance_date").val(classDate);
        $("#attendance_schedule").val(classSchedule);
        $("#attendance_coach").val(classCoach);

        __renderClassStudentsAttendance(classId, classDate, classStatus);
    });

    // 查询课程上课进度
    $(".class-date-all").on("click", function (e) {
        e.preventDefault();

        location.assign(location.href.replace(/&status=\d/, ""));
    });

    $(".class-date-will").on("click", function (e) {
        e.preventDefault();

        location.assign(location.href.replace(/&status=\d/, "") + "&status=1");
    });

    $(".class-date-finished").on("click", function (e) {
        e.preventDefault();

        location.assign(location.href.replace(/&status=\d/, "") + "&status=2");
    });

    // 检索
    $(".search-class").on("click", function (e) {
        e.preventDefault();

        var $form = $("#class_form");
        var conditions = $form.serialize();

        window.location.assign("/admin/class/progress?" + conditions);
    });
});
