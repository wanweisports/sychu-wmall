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
        }
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'chart', 'bootstrap', 'pace', 'base', 'override'], function ($, Chart) {
    'use strict';

    new Chart($("#data_summary_chart"), {
        type: 'bar',
        data: {
            labels: window.summary.labelList,
            datasets: [{
                backgroundColor: "#CCFFCC",
                borderColor: "#CCFFCC",
                label: "入账金额",
                data: window.summary.valuePaymentList
            }, {
                backgroundColor: "#EEA2AD",
                borderColor: "#EEA2AD",
                label:"出账金额",
                data: window.summary.valueRefundList
            }]
        }
    });
});
