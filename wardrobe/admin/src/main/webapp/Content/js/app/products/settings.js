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
        "productsLabelSettings" : 'js/widgets/productsLabelSettings'
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

require(['jquery', 'alert', 'override', 'bootstrap', 'base', 'productsLabelSettings'], function ($, jqueryAlert) {
    'use strict';

    /* category *************************************************/
    var $category = new $.ProductsLabelSettings({
        type: "COMM_CATEGORY",
        load: function (opts) {
            opts.confirmBtn.on("click", function (e) {
                e.preventDefault();

                var $form = opts.wrapperForm;
                var conditions = $form.serialize();

                if ($form.attr("submitting") == "submitting") {
                    return false;
                }

                $form.attr("submitting", "submitting");

                $.post('/admin/products/' + opts.type + '/save', conditions, function (res) {
                    $form.attr("submitting", "");

                    if (res.code == 1) {
                        window.location.reload();
                    } else {
                        jqueryAlert({
                            'icon'      : '/Content/images/icon-error.png',
                            'content'   : "商品品类保存失败, 请稍后重试",
                            'closeTime' : 2000,
                            'modal'        : true,
                            'isModalClose' : true
                        });
                    }
                });
            });
        }
    });

    $(".category-list").on("click", ".category-item .fa-remove", function (e) {
        e.preventDefault();

        var $this = $(this);

        var $dialog = jqueryAlert({
            'title'   : '提示',
            'content' : '您确定要删除此商品品类？',
            'modal'   : true,
            'buttons' :{
                '确定' : function () {
                    $dialog.close();

                    if ($this.attr("working") == "working") {
                        return false;
                    }
                    $this.attr("working", "working");

                    $category.delete({dictId: $this.parent().attr("data-id")}, function (status) {
                        $this.attr("working", "");

                        if (status) {
                            window.location.reload();
                        }
                        else {
                            jqueryAlert({
                                'icon'      : '/Content/images/icon-error.png',
                                'content'   : "商品品类删除失败, 请稍后重试",
                                'closeTime' : 2000,
                                'modal'        : true,
                                'isModalClose' : true
                            });
                        }
                    });
                },
                '取消' : function(){
                    $dialog.close();
                }
            }
        });
    });

    /* style *************************************************/
    var $style = new $.ProductsLabelSettings({
        type: "COMM_STYLE",
        load: function (opts) {
            opts.confirmBtn.on("click", function (e) {
                e.preventDefault();

                var $form = opts.wrapperForm;
                var conditions = $form.serialize();

                if ($form.attr("submitting") == "submitting") {
                    return false;
                }

                $form.attr("submitting", "submitting");

                $.post('/admin/products/' + opts.type + '/save', conditions, function (res) {
                    $form.attr("submitting", "");

                    if (res.code == 1) {
                        window.location.reload();
                    } else {
                        jqueryAlert({
                            'icon'      : '/Content/images/icon-error.png',
                            'content'   : "商品风格保存失败, 请稍后重试",
                            'closeTime' : 2000,
                            'modal'        : true,
                            'isModalClose' : true
                        });
                    }
                });
            });
        }
    });

    $(".style-list").on("click", ".style-item .fa-remove", function (e) {
        e.preventDefault();

        var $this = $(this);

        var $dialog = jqueryAlert({
            'title'   : '提示',
            'content' : '您确定要删除此商品风格？',
            'modal'   : true,
            'buttons' :{
                '确定' : function () {
                    $dialog.close();

                    if ($this.attr("working") == "working") {
                        return false;
                    }
                    $this.attr("working", "working");

                    $style.delete({dictId: $this.parent().attr("data-id")}, function (status) {
                        $this.attr("working", "");

                        if (status) {
                            window.location.reload();
                        }
                        else {
                            jqueryAlert({
                                'icon'      : '/Content/images/icon-error.png',
                                'content'   : "商品风格删除失败, 请稍后重试",
                                'closeTime' : 2000,
                                'modal'        : true,
                                'isModalClose' : true
                            });
                        }
                    });
                },
                '取消' : function(){
                    $dialog.close();
                }
            }
        });
    });

    /* material *************************************************/
    var $material = new $.ProductsLabelSettings({
        type: "COMM_MATERIAL",
        load: function (opts) {
            opts.confirmBtn.on("click", function (e) {
                e.preventDefault();

                var $form = opts.wrapperForm;
                var conditions = $form.serialize();

                if ($form.attr("submitting") == "submitting") {
                    return false;
                }

                $form.attr("submitting", "submitting");

                $.post('/admin/products/' + opts.type + '/save', conditions, function (res) {
                    $form.attr("submitting", "");

                    if (res.code == 1) {
                        window.location.reload();
                    } else {
                        jqueryAlert({
                            'icon'      : '/Content/images/icon-error.png',
                            'content'   : "商品材质保存失败, 请稍后重试",
                            'closeTime' : 2000,
                            'modal'        : true,
                            'isModalClose' : true
                        });
                    }
                });
            });
        }
    });

    $(".material-list").on("click", ".material-item .fa-remove", function (e) {
        e.preventDefault();

        var $this = $(this);

        var $dialog = jqueryAlert({
            'title'   : '提示',
            'content' : '您确定要删除此商品材质？',
            'modal'   : true,
            'buttons' :{
                '确定' : function () {
                    $dialog.close();

                    if ($this.attr("working") == "working") {
                        return false;
                    }
                    $this.attr("working", "working");

                    $material.delete({dictId: $this.parent().attr("data-id")}, function (status) {
                        $this.attr("working", "");

                        if (status) {
                            window.location.reload();
                        }
                        else {
                            jqueryAlert({
                                'icon'      : '/Content/images/icon-error.png',
                                'content'   : "商品材质删除失败, 请稍后重试",
                                'closeTime' : 2000,
                                'modal'        : true,
                                'isModalClose' : true
                            });
                        }
                    });
                },
                '取消' : function(){
                    $dialog.close();
                }
            }
        });
    });

});
