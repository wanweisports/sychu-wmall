const app = getApp();

Page({
    data: {
        isSupported: wx.canIUse('button.open-type.getUserInfo'),
    },
    onShareAppMessage: null,
    onLoad: function (options) {
        let target = "/pages/index/index";

        options = options || {};

        if (options.target == "detail") {
            app.setCookie('syc_target', "/pages/goods/details/index?id=" + options.cid);
            app.setCookie('syc_redirect', "redirectTo");
            app.setCookie('syc_inviteCode', options.share || "");
        }
        else if (options.target == "home") {
            app.setCookie('syc_target', "/pages/index/index");
            app.setCookie('syc_redirect', "switchTab");
            app.setCookie('syc_inviteCode', options.share || "");
        }
        else if (options.target == "list") {
            app.setCookie('syc_target', "/pages/goods/list/index");
            app.setCookie('syc_redirect', "switchTab");
            app.setCookie('syc_inviteCode', options.share || "");
        }
        else if (options.target == "center") {
            app.setCookie('syc_target', "/pages/index/index");
            app.setCookie('syc_redirect', "switchTab");
            app.setCookie('syc_inviteCode', options.share || "");
        }
        else {
            app.setCookie('syc_target', "/pages/index/index");
            app.setCookie('syc_redirect', "switchTab");
            app.setCookie('syc_inviteCode', options.share || "");
        }

        app.toLogin();
    },
    onGetUserInfo: function (event) {
        if (event.type.toLowerCase() == "getuserinfo" && event.detail.errMsg.toLowerCase() == "getuserinfo:ok") {
            app.globalData.sessionId = "";
            app.toLogin();
        }
        else {
            app.showToast("不支持的微信版本", "none");
        }
    }
});
