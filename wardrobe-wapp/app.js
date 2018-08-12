// app.js

App({
    onLaunch: function () {
        wx.setStorageSync('mallName', "闪衣橱");

        var sessionId = this.globalData.sessionId;
        if (!!sessionId) {
            return this.checkSession(this.checkUserComplete, this.login);
        };

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
                console.log(res);
                var code = res.code;

                wx.getUserInfo({
                    success: function (res) {
                        console.log(res);
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
                                            url: "/pages/user-complete/index"
                                        });
                                    }
                                }
                            },
                            function (err) {
                                console.log("[F][/login]" + JSON.stringify(err));
                                console.log(err);
                            }
                        );
                    },
                    fail: function (err) {
                        console.log("[F][wx.getUserInfo]" + JSON.stringify(err));
                        wx.redirectTo({
                            url: "/pages/auth/index"
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
                        url: "/pages/user-complete/index"
                    });
                }
                else {
                    success(res);
                }
            },
            function (err) {
                console.log("[F][/user/isPerfect]" + JSON.stringify(err));
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
        var that = this;

        success = success || new Function;
        fail = fail || new Function;

        var header = isNotLogin ? {} : {
            'Cookie': 'JSESSIONID=' + that.globalData.sessionId
        };

        wx.request({
            url: that.config.getApiHost() + url,
            data: data, 
            header: header,
            success: function (res) {
                console.log("[R][" + url + "]：" + JSON.stringify(res));
                success(res.data);
            },
            fail: function (err) {
                console.log("[F][" + url + "]：" + JSON.stringify(err));
                fail(err);
            }
        });
    },

    globalData: {
        userInfo: null,
        sessionId: "",
        isPerfect: false
    },

    config: {
        getApiHost: function () {
            return "http://127.0.0.1:8070";
        },
        version: "1.0",
        shareProfile: '百款精品商品，总有一款适合您' // 首页转发的时候话术
    }
});
