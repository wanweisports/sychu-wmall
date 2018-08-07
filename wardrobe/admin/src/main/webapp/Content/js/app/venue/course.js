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
        $('#course_edit_form').validate({
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

    $(".add-course").on("click", function (e) {
        e.preventDefault();

        $("#course_edit_id").val("");
        $("#course_edit_sportId").val("");
        $("#course_edit_courseName").val("");
        $("#course_edit_courseNote").val("");
    });

    $('.course-list .course-note p').popover({
        placement: "top"
    });

    // 解锁
    $(".course-list").on("click", ".course-open", function (e) {
        e.preventDefault();

        var $this = $(this);
        var courseId = $this.parents("tr").attr("data-id");

        if ($this.attr("submitting") == "submitting") {
            return;
        }
        $this.attr("submitting", "submitting");

        $.post('/admin/venue/unlockCourse', {courseId: courseId}, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "解锁课程成功",
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
    $(".course-list").on("click", ".course-close", function (e) {
        e.preventDefault();

        var $this = $(this);
        var courseId = $this.parents("tr").attr("data-id");

        if ($this.attr("submitting") == "submitting") {
            return;
        }
        $this.attr("submitting", "submitting");

        $.post('/admin/venue/lockCourse', {courseId: courseId}, function (res) {
            $this.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "锁定课程成功",
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
                    'content'   : "锁定课程失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    $(".course-list").on("click", ".edit-course", function (e) {
        e.preventDefault();

        var courseId = $(this).parents("tr").attr("data-id");

        $.getJSON('/admin/venue/getCourse', {courseId: courseId}, function (res) {
            var data = res.data;

            if (res.code == 1) {
                $("#course_edit_id").val(data.orgCourses.id);
                $("#course_edit_sportId").val(data.orgCourses.sportId);
                $("#course_edit_courseName").val(data.orgCourses.courseName);
                $("#course_edit_courseNote").val(data.orgCourses.courseNote);
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "查询课程失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 保存课程
    $(".save-course").on("click", function (e) {
        e.preventDefault();

        var $form = $("#course_edit_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/venue/saveCourse', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "保存课程成功",
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
                    'content'   : "保存课程失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 检索课程
    $(".search-course").on("click", function (e) {
        e.preventDefault();

        var $form = $("#course_form");
        var conditions = $form.serialize();

        window.location.assign("/admin/venue/course?" + conditions);
    });
});
