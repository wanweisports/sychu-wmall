requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',

        "alert"     : 'utils/jqueryAlert/alert/alert',

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
        }
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'payment'], function ($, jqueryAlert) {
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

    $(".user-list").on("click", ".user-class", function () {
        var classId = $(this).parents("tr").attr("data-class");

        $("#class_student_id").val($(this).parents("tr").attr("data-id"));
        $("#class_students .class-item").removeClass("class-item-selected");
        $("#class_students .class-list .col-md-4").show();

        $("#class_students .save-class").show();
        $("#class_students .save-class-recharge").hide();

        var classIds = $(this).parents("tr").find(".user-list-class a");
        for (var i = 0; i < classIds.length; i++) {
            $("#class_students .class-item[data-id='" + classIds.eq(i).attr("data-class") + "']").parents(".col-md-4").hide();
        }

        if (!!classId) {
            // $(".class-item").removeClass("class-item-selected");
            // $(".class-item[data-id='" + classId + "']").addClass("class-item-selected");
        }
    });

    // TODO 废弃
    $(".user-list").on("click", ".user-recharge", function () {
        var classId = $(this).parents("tr").attr("data-class");

        $("#class_student_id").val($(this).parents("tr").attr("data-id"));
        $("#class_students .class-item").removeClass("class-item-selected");
        $("#class_students .class-list .col-md-4").hide();

        var classIds = $(this).parents("tr").find(".user-list-class a");
        for (var i = 0; i < classIds.length; i++) {
            $("#class_students .class-item[data-id='" + classIds.eq(i).attr("data-class") + "']").parents(".col-md-4").show();
        }

        $(".save-class").hide();
        $(".save-class-recharge").show();

        if (!!classId) {
            // $(".class-item").removeClass("class-item-selected");
            // $(".class-item[data-id='" + classId + "']").addClass("class-item-selected");
        }
    });

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
    });

    // 保存分班
    $(".save-class").on("click", function (e) {
        e.preventDefault();

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
            jqueryAlert({
                'icon'      : '/Content/images/icon-error.png',
                'content'   : "请选择班级",
                'closeTime' : 2000,
                'modal'        : true,
                'isModalClose' : true
            });
            return false;
        }

        $form.attr("submitting", "submitting");

        $.postJSON('/admin/students/saveClassStudents', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "学员分班成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });

                var payment = new $.Payment({
                    orderNo: res.data.orderNo,
                    orderAmount: res.data.orderAmount,
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
                            window.location.reload();
                        }, 1500);
                    },
                    cancel: function () {
                        window.setTimeout(function () {
                            window.location.assign("/admin/order/list");
                        }, 1500);
                    }
                });

                payment.showConfirm(function () {
                    $("#class_students").modal("hide");
                });
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "学生分班失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // TODO 废弃
    // 保存分班退费
    $(".save-class-recharge").on("click", function (e) {
        e.preventDefault();

        var $form = $("#class_form");
        var conditions = {
            studentId: $("#class_student_id").val(),
            classIds: $("#class_ids").val(),
            state: false
        };

        if ($form.attr("submitting") == "submitting") {
            return false;
        }

        if (!$("#class_ids").val().trim()) {
            jqueryAlert({
                'icon'      : '/Content/images/icon-error.png',
                'content'   : "请选择班级",
                'closeTime' : 2000,
                'modal'        : true,
                'isModalClose' : true
            });
            return false;
        }

        $form.attr("submitting", "submitting");

        $.postJSON('/admin/students/saveClassStudents', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "学员退费成功",
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
                    'content'   : "学生退费失败, 请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 检索
    $(".search-students").on("click", function (e) {
        e.preventDefault();

        var $form = $("#students_form");
        var conditions = $form.serialize();

        window.location.assign("/admin/students/list?" + conditions);
    });

    $(".js-status-down").click(function(){
        var cid = $(this).data('id');
        if(window.confirm("确认下架吗？")) {
            $.post("/commodity/updateStatus", {cid: cid, status: 2}, function (res) {
                alert(res.message)
               if(res.code == 1){
                   window.location.reload();
               }
            });
        }
    });
    $(".js-status-top").click(function(){
        var cid = $(this).data('id');
        if(window.confirm("确认上架吗？")) {
            $.post("/commodity/updateStatus", {cid: cid, status: 1}, function (res) {
                alert(res.message)
                if(res.code == 1){
                    window.location.reload();
                }
            });
        }
    });
    $(".js-hot-down").click(function(){
        var cid = $(this).data('id');
        if(window.confirm("确认取消热门吗？")) {
            $.post("/commodity/updateHot", {cid: cid, hot: 2}, function (res) {
                alert(res.message)
                if(res.code == 1){
                    window.location.reload();
                }
            });
        }
    });
    $(".js-hot-top").click(function(){
        var cid = $(this).data('id');
        if(window.confirm("确认热门吗？")) {
            $.post("/commodity/updateHot", {cid: cid, hot: 1}, function (res) {
                alert(res.message)
                if(res.code == 1){
                    window.location.reload();
                }
            });
        }
    });
    $(".js-newly-down").click(function(){
        var cid = $(this).data('id');
        if(window.confirm("确认取消最新吗？")) {
            $.post("/commodity/updateNewly", {cid: cid, newly: 2}, function (res) {
                alert(res.message)
                if(res.code == 1){
                    window.location.reload();
                }
            });
        }
    });
    $(".js-newly-top").click(function(){
        var cid = $(this).data('id');
        if(window.confirm("确认最新吗？")) {
            $.post("/commodity/updateNewly", {cid: cid, newly: 1}, function (res) {
                alert(res.message)
                if(res.code == 1){
                    window.location.reload();
                }
            });
        }
    });
});
