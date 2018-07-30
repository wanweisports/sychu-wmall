requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',
        "chart"     : 'bower_components/chart.js/dist/Chart',

        "base"      : 'js/widgets/base',
        "override"  : 'js/widgets/override'
    },
    shim: {
        "bootstrap": {
            deps: ["jquery", "override"]
        },
        "chart": {
            deps: ["jquery"]
        },
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'chart', 'override', 'bootstrap', 'base'], function ($, Chart) {
    'use strict';

    new Chart($("#income_pay_type_chart"), {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [10, 20, 30]
            }]
        }
    });

    // 检索
    $(".search-income").on("click", function (e) {
        e.preventDefault();

        var $form = $("#income_form");
        var conditions = $form.serialize();

        window.location.assign("/admin/data/income?" + conditions);
    });
});
