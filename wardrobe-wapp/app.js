// app.js

App({
    onLaunch: function () {
        wx.setStorageSync('mallName', "闪衣橱");
    },

    toLogin: function () {
        var sessionId = this.globalData.sessionId;
        if (!!sessionId) {
            return this.checkSession(this.checkUserComplete, this.login);
        }

        this.login();
    },

    checkSession: function (success, fail) {
        var that = this;

        success = success || new Function;
        fail = fail || new Function;

         wx.checkSession({
            success: function () {
                success();
            },
            fail: function () {
                fail();
            }
        });
    },

    login: function () {
        var that = this;

        wx.login({
            success: function (res) {
                //console.log(res);
                var code = res.code;

                wx.getUserInfo({
                    success: function (res) {
                        //console.log(res);
                        var iv = res.iv;
                        var encryptedData = res.encryptedData;

                        that.wxRequest(
                            '/login',
                            {
                                code: code,
                                encryptedData: encryptedData,
                                iv: iv
                            },
                            function (res) {
                                if (res.code == 1) {
                                    that.globalData.sessionId = res.data.sessionId;
                                    if (res.data.perfect == 2) {
                                        wx.redirectTo({
                                            url: "/pages/user/complete/index"
                                        });
                                    }
                                    else {
                                        wx.switchTab({
                                            url: "/pages/index/index"
                                        });
                                    }
                                }
                            },
                            function (err) {
                                //console.log("[F][/login]" + JSON.stringify(err));
                                //console.log(err);
                            }
                        );
                    },
                    fail: function (err) {
                        //console.log("[F][wx.getUserInfo]" + JSON.stringify(err));
                        wx.redirectTo({
                            url: "/pages/landing/index"
                        });
                    }
                });
            }
        });
    },

    checkUserComplete: function (success, fail) {
        var that = this;

        that.wxRequest(
            that.config.getApiHost() + '/user/isPerfect',
            {},
            function (res) {
                if (res.data.isPerfect == 2) {
                    wx.redirectTo({
                        url: "/pages/user/complete/index"
                    });
                }
                else {
                    success(res);
                }
            },
            function (err) {
                //console.log("[F][/user/isPerfect]" + JSON.stringify(err));
                fail(err)
            }
        );
    },

    sendTempleMsg: function (orderId, trigger, template_id, form_id, page, postJsonString){
        var that = this;

        wx.request({
            url: that.config.getApiHost() + '/template-msg/put',
            method:'POST',
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            data: {
                token: that.globalData.token,
                type:0,
                module:'order',
                business_id: orderId,
                trigger: trigger,
                template_id: template_id,
                form_id: form_id,
                url:page,
                postJsonString: postJsonString
            },
            success: function (res) {
                //console.log('*********************');
                //console.log(res.data);
                //console.log('*********************');
            }
        })
    },

    wxRequest: function (url, data, success, fail, isNotLogin) {
        let content = this;

        success = success || new Function();
        fail = fail || new Function();
        isNotLogin = isNotLogin || false;

        var header = isNotLogin ? {} : {
            'Cookie': 'JSESSIONID=' + content.globalData.sessionId
        };

        wx.request({
            url    : content.config.getApiHost() + url,
            data   : data,
            header : header,
            success: function (res) {
                content.showLog(("[R][" + url + "]：" + JSON.stringify(res));
                success(res.data);
            },
            fail: function (err) {
                content.showLog("[F][" + url + "]：" + JSON.stringify(err));
                fail(err);
            }
        });
    },
    // 微信支付
    wxPay: function (orderId, redirectUrl) {
        this.wxRequest('/order/wxPayPackage', {
                orderId: orderId
            },
            function (res) {
                if (res.code == 1) {
                    var data = res.data;

                    wx.requestPayment({
                        timeStamp: data.timeStamp,
                        nonceStr: data.nonceStr,
                        package: data.package,
                        signType: data.signType,
                        paySign: data.paySign,
                        fail: function (err) {
                            wx.showToast({title: '支付失败:' + err})
                        },
                        success: function () {
                            wx.showToast({title: '支付成功'});

                            wx.reLaunch({
                                url: redirectUrl
                            });
                        }
                    });
                }
                else if (res.code == 2) {
                    wx.reLaunch({
                        url: redirectUrl
                    });
                }
                else {
                    wx.showToast({title: '调起支付失败:' + res.message})
                }
            },
            function (err) {
                wx.showToast({title: '调起支付错误:' + err})
            }
        );
    },
    onShareAppMessage: function() {
        return {
            title: wx.getStorageSync('mallName') + '——' + this.config.shareProfile,
            path: '/pages/landing/index',
            success: function(res) {
                // 转发成功
            },
            fail: function(res) {
                // 转发失败
            }
        }
    },
    // 页面跳转
    redirect: function (url, type) {

        if (type == "switchTab") {
            return wx.switchTab({
                url: url
            });
        }

        return wx.redirectTo({
            url: url
        });
    },
    // 显示提示
    showToast: function (message, type) {
        wx.showToast({
            title: message,
            mask: true,
            icon: type || "none"
        });
    },
    // 调试日志
    showLog: function (message) {
        let now = (new Date()).getTime();
        if (this.isDebug) {
            console.log(`[${now}]：${message}`);
        }
    },

    globalData: {
        userInfo: null,
        sessionId: "",
        isPerfect: false
    },

    config: {
        getApiHost: function () {
            //return "http://127.0.0.1:8070";
            //return "http://192.168.1.105:8070";
            return "https://mystore.jonham.cn/api";
        },
        version: "1.0",
        shareProfile: '百款精品商品，总有一款适合您',
        isDebug: false
    }
});
