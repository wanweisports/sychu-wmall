requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',
        "chart"     : 'bower_components/chart.js/dist/Chart',

        "alert"     : 'utils/jqueryAlert/alert/alert',

        "base"      : 'js/widgets/base',
        "override"  : 'js/widgets/override'
    },
    shim: {
        "bootstrap": {
            deps: ["jquery", "override"],
            exports: "bootstrap"
        },
        "alert": {
            deps: ["jquery"]
        }
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'alert', 'chart', 'bootstrap', 'pace', 'base', 'override'], function ($, jqueryAlert, Chart) {
    'use strict';

    function convertHex(hex, opacity) {
        hex = hex.replace('#', '');
        var r = parseInt(hex.substring(0, 2), 16);
        var g = parseInt(hex.substring(2, 4), 16);
        var b = parseInt(hex.substring(4, 6), 16);

        var result = 'rgba(' + r + ',' + g + ',' + b + ',' + opacity / 100 + ')';
        return result;
    }

    //Cards with Charts
    var labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    var data = {
        labels: labels,
        datasets: [
            {
                label: 'My First dataset',
                backgroundColor: $.brandPrimary,
                borderColor: 'rgba(255,255,255,.55)',
                data: [65, 59, 84, 84, 51, 55, 40]
            },
        ]
    };
    var options = {
        maintainAspectRatio: false,
        legend: {
            display: false
        },
        scales: {
            xAxes: [{
                gridLines: {
                    color: 'transparent',
                    zeroLineColor: 'transparent'
                },
                ticks: {
                    fontSize: 2,
                    fontColor: 'transparent',
                }

            }],
            yAxes: [{
                display: false,
                ticks: {
                    display: false,
                    min: Math.min.apply(Math, data.datasets[0].data) - 5,
                    max: Math.max.apply(Math, data.datasets[0].data) + 5,
                }
            }],
        },
        elements: {
            line: {
                borderWidth: 1
            },
            point: {
                radius: 4,
                hitRadius: 10,
                hoverRadius: 4
            }
        }
    };
    var ctx = $('#card-chart1');
    var cardChart1 = new Chart(ctx, {
        type: 'line',
        data: data,
        options: options
    });

    /* 统计班级量 */
    var classOptions = {
        maintainAspectRatio: false,
        legend: {
            display: false
        },
        scales: {
            xAxes: [{
                gridLines: {
                    color: 'transparent',
                    zeroLineColor: 'transparent'
                },
                ticks: {
                    fontSize: 2,
                    fontColor: 'transparent'
                }

            }],
            yAxes: [{
                display: false,
                ticks: {
                    display: false,
                    min: Math.min.apply(Math, data.datasets[0].data) - 5,
                    max: Math.max.apply(Math, data.datasets[0].data) + 5
                }
            }]
        },
        elements: {
            line: {
                tension: 0.00001,
                borderWidth: 1
            },
            point: {
                radius: 4,
                hitRadius: 10,
                hoverRadius: 4
            }
        }
    };
    function totalClassCount(type) {
        $(".total-class .dropdown-item[data-type='" + type + "']").addClass("active").siblings().removeClass("active");

        $.getJSON('/admin/dashboard/class/total', {type: type}, function (res) {
            var data = res.data;

            if (res.code == 1) {
                var dateCell = type == "year" ? "年" : (type == "total" ? "总" : "月");
                $(".total-class .total-class-name").html(dateCell + "班级数量（正上课/全部）");
                $(".total-class .total-class-count").html("<i class='fa fa-graduation-cap'></i>&nbsp;" + data.working + "/" + (data.start + data.working + data.end));

                var classNum = {
                    labels: ["01月", "02月", "03月", "04月", "05月", "06月", "07月", "08月", "09月", "10月", "11月", "12月"],
                    datasets: [
                        {
                            label: '班级数量',
                            backgroundColor: $.brandInfo,
                            borderColor: 'rgba(255,255,255,.55)',
                            data: [65, 59, 84, 84, 51, 55, 40, 65, 59, 84, 65, 59]
                        }
                    ]
                };
                new Chart($('#total_class_chart'), {
                    type: 'line',
                    data: classNum,
                    options: classOptions
                });
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "统计班级数量失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    }
    totalClassCount("year");
    $(".total-class").on("click", ".dropdown-item", function (e) {
        e.preventDefault();

        totalClassCount($(this).attr("data-type"));
    });
    //end

    /* 统计收支 */
    var incomeOptions = {
        maintainAspectRatio: false,
        legend: {
            display: false
        },
        scales: {
            xAxes: [{
                display: false
            }],
            yAxes: [{
                display: false
            }]
        },
        elements: {
            line: {
                borderWidth: 2
            },
            point: {
                radius: 0,
                hitRadius: 10,
                hoverRadius: 4
            }
        }
    };
    var expendOptions = {
        maintainAspectRatio: false,
        legend: {
            display: false
        },
        scales: {
            xAxes: [{
                display: false
            }],
            yAxes: [{
                display: false
            }]
        },
        elements: {
            line: {
                borderWidth: 2
            },
            point: {
                radius: 0,
                hitRadius: 10,
                hoverRadius: 4
            }
        }

    };
    function totalIncomeExpendClassCount(type, cell) {
        if (cell == "all" || cell == "income") {
            $(".income-class .dropdown-item[data-type='" + type + "']").addClass("active").siblings().removeClass("active");
        }

        if (cell == "all" || cell == "expend") {
            $(".expend-class .dropdown-item[data-type='" + type + "']").addClass("active").siblings().removeClass("active");
        }

        $.getJSON('/admin/dashboard/money/total', {type: type}, function (res) {
            var data = res.data;

            if (res.code == 1) {
                var dateCell = (type == "year" ? "年" : (type == "total" ? "总" : "月"));

                if (cell == "all" || cell == "income") {
                    $(".income-class .income-class-name").html(dateCell + "入账金额（元）");
                    $(".income-class .income-class-count").html("<i class='fa fa-jpy'></i>&nbsp;" + $.moneyFormat(data.total, 0, ".", ","));

                    var incomeNum = {
                        labels: ["01月", "02月", "03月", "04月", "05月", "06月", "07月", "08月", "09月", "10月", "11月", "12月"],
                        datasets: [
                            {
                                label: '入账金额',
                                backgroundColor: 'rgba(255,255,255,.3)',
                                borderColor: 'rgba(255,255,255,.55)',
                                data: [65, 59, 84, 84, 51, 55, 40, 65, 59, 84, 65, 59]
                            }
                        ]
                    };
                    new Chart($('#income_class_chart'), {
                        type: 'line',
                        data: incomeNum,
                        options: incomeOptions
                    });
                }

                if (cell == "all" || cell == "expend") {
                    $(".expend-class .expend-class-name").html(dateCell + "出账金额（元）");
                    $(".expend-class .expend-class-count").html("<i class='fa fa-jpy'></i>&nbsp;" + $.moneyFormat(data.refund, 0, ".", ","));
                    var expendNum = {
                        labels: ["01月", "02月", "03月", "04月", "05月", "06月", "07月", "08月", "09月", "10月", "11月", "12月"],
                        datasets: [
                            {
                                label: '入账金额',
                                backgroundColor: 'rgba(255,255,255,.3)',
                                borderColor: 'transparent',
                                data: [65, 59, 84, 84, 51, 55, 40, 65, 59, 84, 65, 59]
                            }
                        ]
                    };
                    new Chart($('#expend_class_chart'), {
                        type: 'line',
                        data: expendNum,
                        options: expendOptions
                    });
                }
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "统计收支金额失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    }
    totalIncomeExpendClassCount("year", "all");
    $(".income-class").on("click", ".dropdown-item", function (e) {
        e.preventDefault();

        totalIncomeExpendClassCount($(this).attr("data-type"), "income");
    });
    $(".expend-class").on("click", ".dropdown-item", function (e) {
        e.preventDefault();

        totalIncomeExpendClassCount($(this).attr("data-type"), "expend");
    });
    //end

    /*统计学员数量*/
    /* 统计班级量 */
    var studentCharts = null;
    var studentOptions = {
        maintainAspectRatio: true,
        legend: {
            display: false
        },
        scales: {
            xAxes: [{
                gridLines: {
                    drawOnChartArea: true
                }
            }],
            yAxes: [{
                ticks: {
                    beginAtZero: true,
                    maxTicksLimit: 5
                }
            }]
        },
        elements: {
            line: {
                tension: 0.00001,
                borderWidth: 1
            },
            point: {
                radius: 4,
                hitRadius: 10,
                hoverRadius: 4,
                hoverBorderWidth: 3
            }
        }
    };
    function totalStudentsCount(type) {
        $.getJSON('/admin/dashboard/students/total', {type: type}, function (res) {
            var data = res.data;

            if (res.code == 1) {
                $(".total-students-date").text(data.titleShow);
                $(".total-students-all").text(data.total + " Users");
                $(".total-students-class").text(data.totalCreate + " Users");
                $(".total-students-create").text(data.totalCurrent + " Users");
                $(".total-students-create-class").text(data.totalCreateCurrent + " Users");

                var studentTotalNum = {
                    labels: data.labelList,
                    datasets: [
                        {
                            label: '学员数量',
                            backgroundColor: $.brandInfo,
                            borderColor: 'rgba(255,255,255,.55)',
                            data: data.valueList
                        }
                    ]
                };

                if (!studentCharts) {
                    studentCharts = new Chart($('#total_students_chart'), {
                        type: 'line',
                        data: studentTotalNum,
                        options: studentOptions
                    });
                } else {
                    studentCharts.data = studentTotalNum;
                    studentCharts.update();
                }
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "统计学员数量失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    }
    totalStudentsCount("year");
    $(".total-students [name='total_students_type']").on("change", function (e) {
        e.preventDefault();

        totalStudentsCount($(".total-students input[name='total_students_type']:checked").val());
    });
});
