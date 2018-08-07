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

    // 备份数据库
    $(".backup-database").on("click", function (e) {
        e.preventDefault();

        $.post('/admin/settings/backup', {}, function (res) {
            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "备份数据库成功",
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
                    'content'   : "备份数据库失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

    // 还原数据库
    $(".restore-database").on("click", function (e) {
        e.preventDefault();

        $.post('/admin/settings/restore', {}, function (res) {
            if (res.code == 1) {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-ok.png',
                    'content'   : "还原数据库成功",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
                window.setTimeout(function () {
                    //window.location.reload();
                }, 1500);
            } else {
                jqueryAlert({
                    'icon'      : '/Content/images/icon-error.png',
                    'content'   : "还原数据库失败，请稍后重试",
                    'closeTime' : 2000,
                    'modal'        : true,
                    'isModalClose' : true
                });
            }
        });
    });

});
