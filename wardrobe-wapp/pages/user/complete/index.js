var util = require("../../../utils/util.js");
var app = getApp();

Page({
    data: {
        sizeIndex     : 0,
        userSizeList  : [],

        showTopTips   : "",
        isGetSMSCode  : false,

        isTimer       : false,
        isTimerCount  : 60,
        smsText       : "获取验证码",
        _t            : null,

        today         : util.formatDate(new Date()),
        today100      : util.formatDate(new Date("1918-01-01 00:00:00")),

        mobile        : "",
        code          : "",
        inviteCode    : "",
        usualSize     : "",
        birthday      : "选择生日"

    },
    onLoad: function () {
        this.getUserSizeDicts();
    },
    getUserSizeDicts: function () {
        var that = this;

        app.wxRequest(
            "/dict/getDicts",
            {
                dictName: "USER_SIZE"
            },
            function (res) {
                that.setData({
                    userSizeList : res.data.dicts,
                    usualSize    : res.data.dicts[0].dictValue
                });
            }
        );
    },
    bindSizeChange: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value);
        this.setData({
            sizeIndex: e.detail.value,
            usualSize: this.data.userSizeList[e.detail.value].dictValue
        });
    },
    bindDateChange: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value);
        this.setData({
            birthday: e.detail.value
        });
    },
    bindMobileBlur: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value);
        this.setData({
            mobile: e.detail.value
        });
    },
    bindCodeBlur: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value);
        this.setData({
            code: e.detail.value
        });
    },
    bindInviteCodeBlur: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value);
        this.setData({
            inviteCode: e.detail.value
        });
    },
    sendSMSCode: function () {
        var that = this;

        if (!that.validate("sms")) {
            that.hideTopTips();
            return;
        }

        that.setData({
            isGetSMSCode: true
        });
        if (that.data.isTimer) {
            return;
        }

        that.timer();

        app.wxRequest("/message/getCode", {mobile: that.data.mobile, type: 1}, function () {}, function () {});
    },
    timer: function () {
        var that = this;

        that.setData({
            isTimer : true,
            smsText : --that.data.isTimerCount + 's',
            _t      : setTimeout(function () {
                if (that.data.isTimerCount == 0) {
                    that.clearTimer();
                    return;
                }

                var nextTimerCount = --that.data.isTimerCount;
                that.setData({
                    isTimerCount : nextTimerCount,
                    smsText : nextTimerCount + 's',
                    _t      : setTimeout(that.timer, 1000)
                });
            }, 1000)
        });
    },
    clearTimer: function () {
        this.setData({
            isTimer: false,
            isTimerCount: 60,
            smsText: '重新获取'
        });
        clearTimeout(this.data._t);
    },
    hideTopTips: function () {
        var that = this;

        setTimeout(function () {
            that.setData({
                showTopTips: ""
            });
        }, 2000);
    },
    validate: function (type) {
        var message = "";

        if (type == "submit") {
            if (this.data.birthday == "选择生日") {
                message = "请选择生日";
            } else if (this.data.usualSize == "") {
                message = "请选择尺码偏好";
            } else if (this.data.mobile == "") {
                message = "请输入手机号码";
            } else if (!/^1\d{10}$/.test(this.data.mobile)) {
                message = "请输入合法的手机号";
            } else if (this.data.code == "") {
                message = "请输入验证码";
            }
        } else if (type == "sms") {
            if (this.data.mobile == "") {
                message = "请输入手机号码";
            } else if (!/^1\d{10}$/.test(this.data.mobile)) {
                message = "请输入合法的手机号";
            }
        }

        if (message) {
            this.setData({
                showTopTips: message
            });
            return false;
        }

        return true;
    },
    userComplete: function () {
        var that = this;

        if (!that.validate("submit")) {
            that.hideTopTips();
            return;
        }

        app.wxRequest(
            "/user/updateUser",
            {
                age        : that.data.birthday,
                usualSize  : that.data.usualSize,
                mobile     : that.data.mobile,
                code       : that.data.code,
                inviteCode : that.data.inviteCode
            },
            function (res) {
                if (res.code == 1) {
                    wx.switchTab({
                        url: "/pages/index/index"
                    });
                }
            },
            function (err) {
                that.setData({
                    showTopTips: err.message || "保存信息错误"
                });
            }
        );
    }
});
