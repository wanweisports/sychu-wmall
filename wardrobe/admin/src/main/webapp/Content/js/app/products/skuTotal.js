requirejs.config({
    baseUrl: 'Content/',
    paths: {
        "jquery"    : 'bower_components/jquery/dist/jquery',
        "tether"    : 'bower_components/tether/dist/js/tether',
        "bootstrap" : 'bower_components/bootstrap/dist/js/bootstrap',
        "pace"      : 'bower_components/pace/pace',

        "jquery-mousewheel"     : 'bower_components/jquery-mousewheel/jquery.mousewheel.min',
        "datetimepicker" : 'bower_components/datetimepicker/build/jquery.datetimepicker.full.min',

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
        },
        "jquery-mousewheel" : {
            deps: ["jquery"]
        },
        "datetimepicker" : {
            deps: ["jquery", "jquery-mousewheel"]
        }
    },  // 依赖关系
    waitSeconds: 0,
    urlArgs: '_=' + new Date().getTime()
});

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'datetimepicker'], function ($) {
    'use strict';

    // 控件绑定
    $.datetimepicker.setLocale("zh");
    $('#startTime').datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        lang: 'zh',
        onShow: function () {
            this.setOptions({
                maxDate: $('#endTime').val() ? $('#endTime').val() : false
            })
        }
    });
    $('#endTime').datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        lang: 'zh',
        onShow: function () {
            this.setOptions({
                minDate: $('#startTime').val() ? $('#startTime').val() : false
            })
        }
    });
});
