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

require(['jquery', 'alert', 'bootstrap', 'pace', 'base', 'override'], function ($, jqueryAlert) {
    'use strict';


    $("#distribution_iframe").contents().find(".product-enter").on("click", function (e) {
        e.preventDefault();

        console.log($(this).attr("data-id"));
        $("#distribution_query_list").modal("hide");

        $("#distribution_cid").val($(this).attr("data-id"));
        $("#distribution_product").val($(this).attr("data-name"));
    });

});
