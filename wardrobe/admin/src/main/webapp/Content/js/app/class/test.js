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

    $.postJSON = function (url, data, callback) {
        return $.ajax({
            'type': 'POST',
            'url': url,
            'contentType': 'application/json',
            'data': JSON.stringify(data),
            'dataType': 'json',
            'success': callback
        });
    };

    // 表单校验配置
    $(document).ready(function () {
        $('#test_edit_form').validate({
            ignore: ":hidden"
        });
    });

    // 评测添加弹窗
    $(".test-add").on("click", function (e) {
        e.preventDefault();

        $("#test_edit_id").val("");
    });

    // 班级改变查询班次
    $("#test_edit_classId").on("change", function (e) {
        e.preventDefault();

        var classId = $(this).val();

        $.getJSON('/admin/class/getClassDateByClassId', {classId: classId}, function (res) {
            var data = res.data;
            var tpl = '<option value="$scheduleId">$classDate</option>';
            var html = [];

            if (res.code == 1) {
                html.push(
                    tpl.replace("$scheduleId", "")
                        .replace("$classDate", "请选择评测班次")
                );

                !!data.orgClassScheduleList && data.orgClassScheduleList.forEach(function (item) {
                    if (item.classDate != null) {
                        html.push(
                            tpl.replace("$scheduleId", item.id)
                                .replace("$classDate", item.classDate)
                        );
                    }
                });

                $("#test_edit_classDate").html(html.join(""));
            } else {
                jqueryAlert({
                    'icon': '/Content/images/icon-error.png',
                    'content': "查询评测班次失败，请稍后重试",
                    'closeTime': 2000,
                    'modal': true,
                    'isModalClose': true
                });
            }
        });
    });

    // 保存评测班次
    $(".save-test").on("click", function (e) {
        e.preventDefault();

        var $form = $("#test_edit_form");
        var conditions = $form.serialize();

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        $.post('/admin/class/saveClassTest', conditions, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon': '/Content/images/icon-ok.png',
                    'content': "保存班级评测成功",
                    'closeTime': 2000,
                    'modal': true,
                    'isModalClose': true
                });

                window.setTimeout(function () {
                    window.location.reload();
                }, 1500);
            } else {
                jqueryAlert({
                    'icon': '/Content/images/icon-error.png',
                    'content': "保存班级评测失败, 请稍后重试",
                    'closeTime': 2000,
                    'modal': true,
                    'isModalClose': true
                });
            }
        });
    });

    // 检索
    $(".search-test").on("click", function (e) {
        e.preventDefault();

        var $form = $("#class_test_form");
        var conditions = $form.serialize();

        window.location.assign("/admin/class/test?" + conditions);
    });

    $(".test-start-skills").on("change", "input", function (e) {
        e.preventDefault();

        var value = $(this).val();

        $(this).parents(".form-group").find(".range-value").text("分数：" + value);
    });

    // 评测开始弹窗
    $(".class-test-list").on("click", ".test-start", function (e) {
        e.preventDefault();

        var $this = $(this);
        var testId = $this.parents("tr").attr("data-id");
        var classId = $this.parents("tr").attr("data-classId");

        $("#test_start_id").val($this.parents("tr").attr("data-id"));

        $.getJSON('/admin/class/getClassTestResult', {testId: testId, classId: classId, isView: false}, function (res) {
            var data = res.data;
            var tpl = '<div class="form-group row">' +
                '<label class="col-md-3 form-control-label"><span class="text-danger">*</span> $skillName</label>' +
                '<div class="col-md-6">' +
                '<input type="range" class="form-control pl-0 pr-0" id="test_start_$skillId" name="skill_$skillId" min="0" max="$maxValue" value="0">' +
                '</div><div class="col-md-3 range-value pt-1">分数：0</div>' +
                '</div>';
            var html = [];
            var stpl = '<option value="$studentId">$studentName</option>';
            var shtml = [];

            if (res.code == 1) {
                shtml.push(
                    stpl.replace(/\$studentId/g, "")
                        .replace(/\$studentName/g, "请选择学员")
                );
                !!data.orgSportsSkillsList && data.orgSportsSkillsList.forEach(function (item) {
                    html.push(
                        tpl.replace(/\$skillName/g, item.skillName)
                            .replace(/\$skillId/g, item.id)
                            .replace(/\$maxValue/g, item.maxValue)
                    );
                });
                !!data.orgStudentsList && data.orgStudentsList.forEach(function (item) {
                    shtml.push(
                        stpl.replace(/\$studentId/g, item.id)
                            .replace(/\$studentName/g, item.realName)
                    );
                });

                $(".test-start-skills").html(html.join(""));
                $("#test_start_studentId").html(shtml.join(""));
            } else {
                jqueryAlert({
                    'icon': '/Content/images/icon-error.png',
                    'content': "查询评测技能失败，请稍后重试",
                    'closeTime': 2000,
                    'modal': true,
                    'isModalClose': true
                });
            }
        });
    });

    // 保存评测结果
    $(".save-test-start").on("click", function (e) {
        e.preventDefault();

        var $form = $("#test_start_form");
        var conditions = $form.serializeArray();

        if ($form.attr("submitting") == "submitting" || !$form.valid()) {
            return false;
        }
        $form.attr("submitting", "submitting");

        var submitData = {};
        submitData.orgClassTestResultList = [];
        for (var i = 0; i < conditions.length; i++) {
            var item = conditions[i];

            if (item.name.indexOf("skill_") > -1) {
                submitData.orgClassTestResultList.push({
                    skillId: item.name.replace("skill_", ""),
                    testScore: item.value
                });
            } else {
                submitData[item.name] = item.value;
            }
        }

        $.postJSON('/admin/class/saveClassTestResult', submitData, function (res) {
            $form.attr("submitting", "");

            if (res.code == 1) {
                jqueryAlert({
                    'icon': '/Content/images/icon-ok.png',
                    'content': "保存班级评测结果成功",
                    'closeTime': 2000,
                    'modal': true,
                    'isModalClose': true
                });

                $("#test_start").modal("hide");
            } else {
                jqueryAlert({
                    'icon': '/Content/images/icon-error.png',
                    'content': "保存班级评测结果失败, 请稍后重试",
                    'closeTime': 2000,
                    'modal': true,
                    'isModalClose': true
                });
            }
        });
    });

    $(".class-score-list").on("click", ".test-refresh", function (e) {
        e.preventDefault();

        $("#test_start").modal("show");

        var $this = $(this);
        var testId = $this.parents("tr").attr("data-test");
        var classId = $this.parents("tr").attr("data-class");
        var studentId = $this.parents("tr").attr("data-student");

        $("#test_start_id").val($this.parents("tr").attr("data-id"));

        $.getJSON('/admin/class/getClassTestResult', {testId: testId, classId: classId, isView: false}, function (res) {
            var data = res.data;
            var tpl = '<div class="form-group row">' +
                '<label class="col-md-3 form-control-label"><span class="text-danger">*</span> $skillName</label>' +
                '<div class="col-md-6">' +
                '<input type="range" class="form-control pl-0 pr-0" id="test_start_$skillId" name="skill_$skillId" min="0" max="$maxValue" value="0">' +
                '</div><div class="col-md-3 range-value pt-1">分数：0</div>' +
                '</div>';
            var html = [];
            var stpl = '<option value="$studentId">$studentName</option>';
            var shtml = [];

            if (res.code == 1) {
                shtml.push(
                    stpl.replace(/\$studentId/g, "")
                        .replace(/\$studentName/g, "请选择学员")
                );
                !!data.orgSportsSkillsList && data.orgSportsSkillsList.forEach(function (item) {
                    html.push(
                        tpl.replace(/\$skillName/g, item.skillName)
                            .replace(/\$skillId/g, item.id)
                            .replace(/\$maxValue/g, item.maxValue)
                    );
                });
                !!data.orgStudentsList && data.orgStudentsList.forEach(function (item) {
                    shtml.push(
                        stpl.replace(/\$studentId/g, item.id)
                            .replace(/\$studentName/g, item.realName)
                    );
                });

                $(".test-start-skills").html(html.join(""));
                $("#test_start_studentId").html(shtml.join(""));
                $("#test_start_studentId").val(studentId);
            } else {
                jqueryAlert({
                    'icon': '/Content/images/icon-error.png',
                    'content': "查询评测技能失败，请稍后重试",
                    'closeTime': 2000,
                    'modal': true,
                    'isModalClose': true
                });
            }
        });
    });

    // 评测结果弹窗
    $(".class-test-list").on("click", ".test-query", function (e) {
        e.preventDefault();

        var $this = $(this);
        var testId = $this.parents("tr").attr("data-id");
        var classId = $this.parents("tr").attr("data-classId");

        $("#test_start_id").val($this.parents("tr").attr("data-id"));

        $.getJSON('/admin/class/getClassTestResult', {testId: testId, classId: classId, isView: true}, function (res) {
            var data = res.data;
            if (res.code == 1) {
                var headerTitle = [];
                var headerValue = [];
                var headerTpl = '<thead><tr><th>##</th><th>学员</th>';
                !!data.orgSportsSkillsList && data.orgSportsSkillsList.forEach(function (item) {
                    headerTpl += '<th>' + item.skillName + '</th>';
                    headerTitle.push(item.skillName);
                    headerValue.push(item.maxValue);
                });
                headerTpl += '<th>总计</th><th>评语</th><th>修改</th></tr></thead>';

                var bodyTpl = '<tbody>';
                !!data.scoreList && data.scoreList.forEach(function (item, index) {
                    bodyTpl += '<tr data-student="' + item["studentId"] + '" data-class="' + item["classId"] + '" data-test="' + item["testId"] + '">' +
                        '<td class="text-muted">' + (index + 1) + '</td>' +
                        '<td class="text-muted">' + item["学员"] + '</td>';

                    for (var i = 0; i < headerTitle.length; i++) {
                        var value = item[headerTitle[i]] || 0;
                        var degree = headerValue[i] / 4;
                        var className = "text-muted";
                        if (value >= degree * 3) {
                            className = "text-success";
                        } else if (value >= degree * 2) {
                            className = "text-muted";
                        } else if (value >= degree) {
                            className = "text-warning";
                        } else {
                            className = "text-danger";
                        }
                        bodyTpl += '<td class="' + className + '">' + (item[headerTitle[i]] || 0) + "/" + (headerValue[i]) + '</td>';
                    }

                    bodyTpl += '<td class="text-muted">' + item["总计"] + '</td>';
                    if (!!item["testRemark"]) {
                        bodyTpl += '<td><a href="javascript:;" class="btn btn-sm btn-primary test-comment" data-toggle="popover" data-trigger="hover" ' +
                            'title="" data-content="' + item["testRemark"] + '" data-original-title="评语">' +
                            '<i class="fa fa-commenting-o"></i>' +
                            '</a></td>';
                    } else {
                        bodyTpl += '<td class="text-muted">无</td>';
                    }

                    bodyTpl += '<td><a href="javascript:;" class="btn btn-sm btn-primary test-refresh" data-dismiss="modal" title="重评">' +
                        '<i class="fa fa-refresh"></i> 重评' +
                        '</a></td></tr>';
                });
                bodyTpl += '</tbody>';

                $(".class-score-list").html('<table class="table table-striped table-sm">' + headerTpl + bodyTpl + '</table>');
                $('.test-comment').popover({
                    placement: "left"
                });
            } else {
                jqueryAlert({
                    'icon': '/Content/images/icon-error.png',
                    'content': "查询评测技能结果失败，请稍后重试",
                    'closeTime': 2000,
                    'modal': true,
                    'isModalClose': true
                });
            }
        });
    });

    // 评测删除
    $(".class-test-list").on("click", ".test-delete", function (e) {
        e.preventDefault();

        var $this = $(this);

        var $alert = jqueryAlert({
            'title' : "提示",
            'content' : "您确定要删除此评测吗？",
            'modal'   : true,
            'contentTextAlign' : 'center',
            'buttons' :{
                '关闭' : function () {
                    $alert.close();
                },
                '确定': function () {
                    var testId = $this.parents("tr").attr("data-id");

                    if ($this.attr("submitting") == "submitting") {
                        return false;
                    }
                    $this.attr("submitting", "submitting");

                    $.postJSON('/admin/class/deleteClassTest', {id: testId}, function (res) {
                        $this.attr("submitting", "");

                        if (res.code == 1) {
                            jqueryAlert({
                                'icon': '/Content/images/icon-ok.png',
                                'content': "删除班级评测成功",
                                'closeTime': 2000,
                                'modal': true,
                                'isModalClose': true
                            });

                            window.setTimeout(function () {
                                window.location.reload();
                            }, 1500);
                        } else {
                            jqueryAlert({
                                'icon': '/Content/images/icon-error.png',
                                'content': "删除班级评测失败, 请稍后重试",
                                'closeTime': 2000,
                                'modal': true,
                                'isModalClose': true
                            });
                        }
                    });
                }
            }
        });
    });
});
