var app = getApp();

Page({
    data: {
        isGetSMSCode  : false,

        isTimer       : false,
        isTimerCount  : 60,
        smsText       : "获取验证码",
        _t            : null,

        mobile: "",
        code: ""
    },
    onShareAppMessage: null,
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
    sendSMSCode: function () {
        var that = this;

        if (!that.validate("sms")) {
            return;
        }

        that.setData({
            isGetSMSCode: true
        });
        if (that.data.isTimer) {
            return;
        }

        that.timer();

        app.wxRequest("/message/getCode", {mobile: that.data.mobile, type: 3}, function () {}, function () {});
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
    validate: function (type) {
        var message = "";

        if (type == "submit") {
            if (this.data.mobile == "") {
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
            wx.showModal({
                title: '提示',
                content: message,
                showCancel: false
            });
            return false;
        }

        return true;
    },
    submitSMSCode: function () {
        var that = this;

        if (!that.validate("submit")) {
            return;
        }

        app.wxRequest(
            "/user/updateNewMobile",
            {
                mobile: that.data.mobile,
                code: that.data.code
            },
            function (res) {
                if (res.code == 1) {
                    wx.switchTab({
                        url: "/pages/user/center/index"
                    });
                }
            },
            function (err) {
                console.log(err)
            }
        );
    }
});
