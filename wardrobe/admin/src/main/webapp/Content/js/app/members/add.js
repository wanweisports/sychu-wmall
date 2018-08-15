requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',
        "chart"     : 'bower_components/chart.js/dist/Chart',

        "datepicker"    : "bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker",
        "datepicker-zh" : "bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min",
        "alert"     : 'utils/jqueryAlert/alert/alert',

        "jquery.validate"              : 'bower_components/jquery.validation/dist/jquery.validate',
        "jquery.validate.unobtrusive"  : 'bower_components/Microsoft.jQuery.Unobtrusive.Validation/jquery.validate.unobtrusive',

        "base"      : 'js/widgets/base',
        "override"  : 'js/widgets/override',
        "payment"   : 'js/widgets/payment'
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

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'jquery.validate', 'jquery.validate.unobtrusive', "datepicker", "datepicker-zh", 'payment'], function ($, jqueryAlert) {
    'use strict';

    // 表单校验配置
    $(document).ready(function () {
        $('#students_form').validate({
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
    }
    setDatePicker();

    function __saveStudents(callback) {
        var $form= $("#students_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/students/saveStudents', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                $("#class_student_id").val(res.data.studentId);

                callback(1);
            } else {
                callback(0);
            }
        });
    }
    
    function __saveClassStudents(callback) {
        var $form = $("#class_form");
        var conditions = {
            studentId: $("#class_student_id").val(),
            classIds: $("#class_ids").val(),
            state: true
        };

        if ($form.attr("submitting") == "submitting") {
            return false;
        }

        if (!$("#class_ids").val().trim()) {
            callback(2);
            return false;
        }

        $form.attr("submitting", "submitting");

        $.postJSON('/admin/students/saveClassStudents', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                callback(1, res.data);
            } else {
                callback(0);
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "学生分班失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    }

    // 选择班级
    $(".class-list").on("click", ".class-item", function (e) {
        e.preventDefault();

        if ($(this).hasClass("class-item-selected")) {
            $(this).removeClass("class-item-selected");
        } else {
            $(this).addClass("class-item-selected");
        }

        var total = 0;
        var classIds = [];
        $(".class-list").find(".class-item.class-item-selected").each(function (index, item) {
            total += parseInt($(item).attr("data-price"));
            classIds.push($(item).attr("data-id"));
        });

        //var classId = $(this).attr("data-id");
        //var className = $(this).attr("data-name");
        //var classPrice = $(this).attr("data-price");

        //$("#class_id").val(classId);
        //$("#class_name").val(className);
        //$("#class_balance").val(total);

        $("#class_ids").val(classIds.join(","));
        $(".pay-note").text("已选择班级" + classIds.length + "个，总计金额" + total + "元");

        // var classId = $(this).attr("data-id");
        // var className = $(this).attr("data-name");
        // var classPrice = $(this).attr("data-price");
        //
        // $("#class_id").val(classId);
        // $("#class_name").val(className);
        // $("#class_balance").val(classPrice);
    });

    // 保存分班
    $(".save-class").on("click", function (e) {
        e.preventDefault();

        __saveStudents(function (studentStatus) {
            if (!studentStatus) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "保存学生失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
                return;
            }
            __saveClassStudents(function (classStatus, data) {
                if (classStatus == 2) {
                    jqueryAlert({
                        'icon'      : '/Content/images/icon-ok.png',
                        'content'   : "保存学生成功，没有分班",
                        'closeTime' : 2000,
                        'modal'        : true,
                        'isModalClose' : true
                    });

                    window.setTimeout(function () {
                        window.location.href = '/admin/students/list';
                    }, 1500);
                }

                if (classStatus == 0) {
                    jqueryAlert({
                        'icon'      : '/Content/images/icon-error.png',
                        'content'   : "保存学生成功，分班失败",
                        'closeTime' : 2000,
                        'modal'        : true,
                        'isModalClose' : true
                    });

                    window.setTimeout(function () {
                        window.location.href = '/admin/students/list';
                    }, 1500);
                }

                if (classStatus == 1) {
                    jqueryAlert({
                        'icon'      : '/Content/images/icon-ok.png',
                        'content'   : "保存学生成功，分班成功",
                        'closeTime' : 2000,
                        'modal'        : true,
                        'isModalClose' : true
                    });

                    var payment = new $.Payment({
                        orderNo: data.orderNo,
                        orderAmount: data.orderAmount,
                        failure: function () {
                            jqueryAlert({
                                'icon'      : '/Content/images/icon-error.png',
                                'content'   : "学生分班支付失败, 请稍后重试",
                                'closeTime' : 2000,
                                'modal'        : true,
                                'isModalClose' : true
                            });
                        },
                        success: function () {
                            jqueryAlert({
                                'icon'      : '/Content/images/icon-ok.png',
                                'content'   : "学员分班支付成功",
                                'closeTime' : 2000,
                                'modal'        : true,
                                'isModalClose' : true
                            });

                            window.setTimeout(function () {
                                window.location.assign("/admin/students/list");
                            }, 1500);
                        },
                        cancel: function () {
                            window.setTimeout(function () {
                                window.location.assign("/admin/order/list");
                            }, 1500);
                        }
                    });

                    payment.showConfirm();
                }
            });
        });
    });
});
