// app.js

App({
    onLaunch: function () {
        this.setCookie('syc_appName', "衣否");

        this.toLogin();
    },

    toLogin: function () {
        let sessionId = this.globalData.sessionId;
        if (!!sessionId) {
            return this.checkSession(this.checkUserComplete, this.login);
        }

        this.login();
    },

    checkSession: function (success, fail) {
        success = success || new Function;
        fail = fail || new Function;

        wx.showLoading({
            title: "加载中",
            mask: true
        });

         wx.checkSession({
            success: function () {
                wx.hideLoading();

                success();
            },
            fail: function () {
                wx.hideLoading();

                fail();
            }
        });
    },

    login: function () {
        let content = this;

        wx.showLoading({
            title: "加载中",
            mask: true
        });

        wx.login({
            success: function (res) {
                let code = res.code;

                wx.getUserInfo({
                    withCredentials: true,
                    success: function (res) {
                        let iv = res.iv;
                        let encryptedData = res.encryptedData;

                        wx.hideLoading();

                        content.wxRequest('/login', {
                            code: code,
                            encryptedData: encryptedData,
                            iv: iv
                        }, function (res) {
                            if (res.code == 1) {
                                content.globalData.sessionId = res.data.sessionId;
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
                            } else {
                                content.showToast("授权登录失败", "none");
                            }
                        }, function (err) {
                            content.showLog("[F][/login]：" + JSON.stringify(err));
                            content.showToast("授权登录错误", "none");
                        });
                    },
                    fail: function (err) {
                        wx.hideLoading();

                        content.showLog("[F][wx.getUserInfo]：" + JSON.stringify(err));
                        //content.showToast("用户授权失败", "none");
                    }
                });
            }
        });
    },

    checkUserComplete: function (success, fail) {
        let content = this;

        content.wxRequest(content.config.getApiHost() + '/user/isPerfect', {}, function (res) {
            if (res.data.isPerfect == 2) {
                wx.redirectTo({
                    url: "/pages/user/complete/index"
                });
            }
            else {
                success(res);
            }
        }, function (err) {
            content.showLog("[F][/user/isPerfect]：" + JSON.stringify(err));
            fail(err)
        });
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

        wx.showLoading({
            title: "加载中",
            mask: true
        });

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
                success(res.data);

                wx.hideLoading();
            },
            fail: function (err) {
                content.showLog("[F][" + url + "]：" + JSON.stringify(err));
                fail(err);

                wx.hideLoading();
            }
        });
    },
    // 微信支付
    wxPay: function (orderId, redirectUrl) {
        let content = this;

        this.wxRequest('/order/wxPayPackage', {
                orderId: orderId
            },
            function (res) {
                if (res.code == 1) {
                    let data = res.data;

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

                            content.redirect(redirectUrl, "navigateTo");
                        }
                    });
                }
                else if (res.code == 2) {
                    content.redirect(redirectUrl, "navigateTo");
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
    onShareAppMessage: function () {
        return {
            title : this.getCookie('syc_appName') + '——' + this.config.shareProfile,
            path  : '/pages/landing/index',
            withShareTicket: true,
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

        if (type == "navigateTo") {
            return wx.navigateTo({
                url: url
            });
        }

        if (type == "switchTab") {
            return wx.switchTab({
                url: url
            });
        }

        if (type == "reLaunch") {
            return wx.reLaunch({
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

    // 获取store
    getCookie: function (key) {
        return wx.getStorageSync(key);
    },
    // 写入store
    setCookie: function (key, value) {
        wx.setStorageSync(key, value);
    },
    // 清除store
    clearCookie: function (key) {
        wx.removeStorageSync(key);
    },
    // 清除所有store
    clearCookieAll: function () {
        wx.clearStorageSync();
    },

    globalData: {
        userInfo: null,
        sessionId: "",
        isPerfect: false
    },

    config: {
        getApiHost: function () {
            //return "http://127.0.0.1:8070";
            return "https://mystore.yifoutech.com/api";
            //return "https://mystore.yifoutech.cn/api";
        },
        version: "1.0",
        shareProfile: '百款精品商品，总有一款适合您',
        isDebug: false
    }
});
