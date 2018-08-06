// app.js

App({
    onLaunch: function () {
        wx.setStorageSync('mallName', "闪衣橱");

        this.login();
    },

    login : function () {
        var that = this;
        var token = that.globalData.token;

        var that = this;

        wx.login({
            success: function (res) {
                var code = res.code;

                wx.getUserInfo({
                    success: function (res) {
                        var iv = res.iv;
                        var encryptedData = res.encryptedData;

                        wx.request({
                            url: that.config.getApiHost() + '/login',
                            data: {
                                code: code,
                                encryptedData: encryptedData,
                                iv: iv
                            },
                            success: function (res) {
                                wx.hideLoading();
                                console.log(res);
                                //that.login();

                                wx.request({
                                    url: 'http://192.168.207.156:8070/dict/getDicts?dictName=USER_SEX',
                                    data: {}, // 设置请求的 参数
                                    header: {
                                        'Cookie': 'JSESSIONID=' + res.data.data.sessionId
                                    },
                                    success: function (res) {
                                        console.log(res);
                                        //that.login();
                                    }
                                });
                            }
                        });
                    }
                });
            }
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

    wxRequest: function (url, data, success, fail) {
        success = success || new function;
        fail = fail || new function;

        wx.request({
            url: this.config.getApiHost() + url,
            data: data, 
            header: {
                'Cookie': 'JSESSIONID=' +this.globalData.sessionId
            },
            success: function (res) {
                console.log("[REQUEST]：" + res);
                success();
            },
            fail: function (err) {
                fail();
            }
        });
    },

    globalData: {
        userInfo: null,
        sessionId: ""
    },

    config: {
        getApiHost: function () {
            return "http://192.168.207.156:8070";
        },
        version: "1.0",
        shareProfile: '百款精品商品，总有一款适合您' // 首页转发的时候话术
    }
});
