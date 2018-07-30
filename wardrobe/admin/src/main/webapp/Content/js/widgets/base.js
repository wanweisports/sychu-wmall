define(["jquery"], function ($) {
    'use strict';

    //Main navigation
    $.navigation = $('nav > ul.nav');

    $.panelIconOpened = 'icon-arrow-up';
    $.panelIconClosed = 'icon-arrow-down';

    //Default colours
    $.brandPrimary = '#20a8d8';
    $.brandSuccess = '#4dbd74';
    $.brandInfo = '#63c2de';
    $.brandWarning = '#f8cb00';
    $.brandDanger = '#f86c6b';

    $.grayDark = '#2a2c36';
    $.gray = '#55595c';
    $.grayLight = '#818a91';
    $.grayLighter = '#d1d4d7';
    $.grayLightest = '#f8f9fa';

    function resizeBroadcast() {

        var timesRun = 0;
        var interval = setInterval(function () {
            timesRun += 1;
            if (timesRun === 5) {
                clearInterval(interval);
            }
            window.dispatchEvent(new Event('resize'));
        }, 62.5);
    }

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    function init(url) {

        /* ---------- Tooltip ---------- */
        $('[rel="tooltip"],[data-rel="tooltip"]').tooltip({"placement": "bottom", delay: {show: 400, hide: 200}});

        /* ---------- Popover ---------- */
        $('[rel="popover"],[data-rel="popover"],[data-toggle="popover"]').popover();

    }

    // Add class .active to current link
    $.navigation.find('a').each(function () {

        var cUrl = String(window.location).split('?')[0];

        if (cUrl.substr(cUrl.length - 1) == '#') {
            cUrl = cUrl.slice(0, -1);
        }

        if ($($(this))[0].href == cUrl) {
            $(this).addClass('active');

            $(this).parents('ul').add(this).each(function () {
                $(this).parent().addClass('open');
            });
        }
    });

    // Dropdown Menu
    $.navigation.on('click', 'a', function (e) {

        if ($.ajaxLoad) {
            e.preventDefault();
        }

        if ($(this).hasClass('nav-dropdown-toggle')) {
            $(this).parent().toggleClass('open');
            resizeBroadcast();
        }

    });

    /* ---------- Main Menu Open/Close, Min/Full ---------- */
    $('.navbar-toggler').click(function () {

        if ($(this).hasClass('sidebar-toggler')) {
            $('body').toggleClass('sidebar-hidden');
            resizeBroadcast();
        }

        if ($(this).hasClass('sidebar-minimizer')) {
            $('body').toggleClass('sidebar-compact');
            resizeBroadcast();
        }

        if ($(this).hasClass('aside-menu-toggler')) {
            $('body').toggleClass('aside-menu-hidden');
            resizeBroadcast();
        }

        if ($(this).hasClass('mobile-sidebar-toggler')) {
            $('body').toggleClass('sidebar-mobile-show');
            resizeBroadcast();
        }

    });

    $('.sidebar-close').click(function () {
        $('body').toggleClass('sidebar-opened').parent().toggleClass('sidebar-opened');
    });

    /* ---------- Disable moving to top ---------- */
    $('a[href="#"][data-top!=true]').click(function (e) {
        e.preventDefault();
    });


    /****
     * CARDS ACTIONS
     */

    /*$(document).on('click', '.card-actions a', function (e) {
        e.preventDefault();

        if ($(this).hasClass('btn-close')) {
            $(this).parent().parent().parent().fadeOut();
        } else if ($(this).hasClass('btn-minimize')) {
            var $target = $(this).parent().parent().next('.card-block');
            if (!$(this).hasClass('collapsed')) {
                $('i', $(this)).removeClass($.panelIconOpened).addClass($.panelIconClosed);
            } else {
                $('i', $(this)).removeClass($.panelIconClosed).addClass($.panelIconOpened);
            }

        } else if ($(this).hasClass('btn-setting')) {
            $('#myModal').modal('show');
        }

    });*/

    /** 公共函数 **/
    $.sortFloatArrays = function (baseArray, otherArrays) {
        for (var j = 0; j < baseArray.length - 1; j++) {
            for (var i = 0; i < baseArray.length - 1 - j; i++) {
                if (parseFloat(baseArray[i]) > parseFloat(baseArray[i + 1])) {
                    var temp = baseArray[i];
                    baseArray[i] = baseArray[i + 1];
                    baseArray[i + 1] = temp;

                    for (var k = 0; k < otherArrays.length; k++) {
                        temp = otherArrays[k][i];
                        otherArrays[k][i] = otherArrays[k][i + 1];
                        otherArrays[k][i + 1] = temp;
                    }
                }
            }
        }
    };
    
    /*
     * 金额格式化
     * 参数说明：
     * number：要格式化的数字
     * decimals：保留几位小数
     * dec_point：小数点符号
     * thousands_sep：千分位符号
     * */
    $.moneyFormat = function (number, decimals, dec_point, thousands_sep) {
        number = (number + '').replace(/[^0-9+-Ee.]/g, '');

        var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
            s = '',
            toFixedFix = function (n, prec) {
                var k = Math.pow(10, prec);
                return '' + Math.ceil(n * k) / k;
            };

        s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');

        var re = /(-?\d+)(\d{3})/;
        while (re.test(s[0])) {
            s[0] = s[0].replace(re, "$1" + sep + "$2");
        }

        if ((s[1] || '').length < prec) {
            s[1] = s[1] || '';
            s[1] += new Array(prec - s[1].length + 1).join('0');
        }

        return s.join(dec);
    };

    /** * 自定义校验 * **/
    /**
     * 不等于校验
     * <input type="text" value="" name="cus" id="cus" data-val="true" data-val-notequal="姓名不能够等于 123" data-val-notequal-va="123"/>
     * <span data-valmsg-for="cus" data-valmsg-replace="true"></span>
     */
    /*$.validator.addMethod('notequal', function (value, element, params) {
        return value != params["va"];
    });
    $.validator.unobtrusive.adapters.add("notequal", ["va"], function (options) {
        options.rules["notequal"] = {
            va: options.params.va
        };
        options.messages["notequal"] = options.message;
    });*/

});
