const util = require("../../../utils/util.js");
const app = getApp();

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
    onShareAppMessage: null,
    getUserSizeDicts: function () {
        let content = this;

        app.wxRequest("/dict/getDicts", {
            dictName: "USER_SIZE"
        }, function (res) {
            let data = res.data;

            if (res.code == 1) {
                content.setData({
                    userSizeList : data.dicts,
                    usualSize    : data.dicts[0].dictId
                });
            }
            else {
                app.showToast("获取尺码偏好字典失败", "none");
            }
        }, function () {
            app.showToast("获取尺码偏好字典错误", "none");
        });
    },
    onLoad: function () {
        this.getUserSizeDicts();
    },

    bindSizeChange: function (e) {
        this.setData({
            sizeIndex: e.detail.value,
            usualSize: this.data.userSizeList[e.detail.value].dictId
        });
    },
    bindDateChange: function (e) {
        this.setData({
            birthday: e.detail.value
        });
    },
    bindMobileBlur: function (e) {
        this.setData({
            mobile: e.detail.value
        });
    },
    bindCodeBlur: function (e) {
        this.setData({
            code: e.detail.value
        });
    },
    bindInviteCodeBlur: function (e) {
        this.setData({
            inviteCode: e.detail.value
        });
    },

    timer: function () {
        let content = this;

        let isTimerCount = content.data.isTimerCount;
        isTimerCount--;

        content.setData({
            isTimer : true,
            smsText : isTimerCount + 's',
            _t      : setTimeout(function () {
                let isTimerCount = content.data.isTimerCount;
                if (isTimerCount <= 1) {
                    content.clearTimer();
                    return;
                }

                var nextTimerCount = --isTimerCount;

                content.setData({
                    isTimerCount : nextTimerCount,
                    smsText : nextTimerCount + 's',
                    _t      : setTimeout(content.timer, 1000)
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
    sendSMSCode: function () {
        let content = this;

        if (!content.validate("sms")) {
            return;
        }

        content.setData({
            isGetSMSCode: true
        });

        if (content.data.isTimer) {
            return;
        }

        content.timer();

        app.wxRequest("/message/getCode", {mobile: content.data.mobile, type: 1}, function () {}, function () {});
    },

    validate: function (type) {
        let message = "";

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
            app.showToast(message, "none");
            return false;
        }

        return true;
    },

    userComplete: function () {
        var content = this;

        if (!content.data.isGetSMSCode) {
            return app.showToast("请先获取验证码", "none");
        }

        if (!content.validate("submit")) {
            return;
        }

        app.wxRequest(
            "/user/updateUser",
            {
                age        : content.data.birthday,
                usualSize  : "," + content.data.usualSize + ",",
                mobile     : content.data.mobile,
                code       : content.data.code,
                inviteCode : content.data.inviteCode
            },
            function (res) {
                if (res.code == 1) {
                    app.redirect("/pages/index/index", "switchTab");
                }
                else {
                    app.showToast(res.message || "保存完善信息失败");
                }
            },
            function (err) {
                app.showToast(err.message || "保存完善信息错误");
            }
        );
    }
});
