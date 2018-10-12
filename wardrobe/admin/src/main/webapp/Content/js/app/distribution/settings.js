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

    $("#distribution_iframe").on("load", function(event) {//判断 iframe是否加载完成
        $("#distribution_iframe").contents().find(".product-enter").on("click", function (e) {
            e.preventDefault();

            var cid = $(this).attr("data-id");
            console.log(cid);
            $("#distribution_query_list").modal("hide");

            $("#distribution_cid").val(cid);
            $("#distribution_product").val($(this).attr("data-name"));

            //加载商品尺码
            $("#distribution_size").html("");
            $.post("/commodity/getCommoditySizes", {cid: cid}, function (res) {
                if (res.code == 1) {
                    $.each(res.data.sizes, function (index, item) {
                        $("#distribution_size").append('<option value="' + item.sid + '">' + item.size + '</option>');
                    });
                }
            });
        });
    });

});
