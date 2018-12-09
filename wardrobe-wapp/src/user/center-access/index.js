const app = getApp();

Page({
    data: {
        wardrobeInfo: {},
        locksList: [],
    },
    getWardrobeList: function () {
        let content = this;

        app.wxRequest("/device/devices", {}, function (res) {
            let date = new Date();
            let end = date.getTime() + 2 * 24 * 60 * 60 * 1000;

            if (res.code == 1) {
                let wardrobe = res.data.list[0];
                content.setData({
                    wardrobeInfo: wardrobe
                });

                content.getWardrobeOrderLocks();
            }
        });
    },
    getWardrobeOrderLocks: function () {
        let content = this;
        /*
        {"code":"1","message":"操作成功","data":{"sumOldPrice":2279.0,"code":"1","sumPrice":1823.2,"data":{"commoditys":[{"rfidEpc":"02 12 24 20","price":799.0,"dbid":82.0,"resourcePath":"https://oss-admin.oss-cn-beijing.aliyuncs.com/img/0e27323d529446718b2cc0f953aef614.jpg","name":"柜子1号","count":1.0,"commName":"麂皮连衣裙","couPrice":799.0,"cid":336.0},{"rfidEpc":"02 12 20 50","price":1480.0,"dbid":94.0,"resourcePath":"https://oss-admin.oss-cn-beijing.aliyuncs.com/img/7ee835e407894240b248973836205d35_YS.jpg","name":"柜子4号","count":1.0,"commName":"材质优选|开叉喇叭裤","couPrice":1480.0,"cid":102.0}],"sumPrice":2279.0},"cpid":"0","freight":0.0,"discount":0.8,"message":"操作成功","useBalance":"2","coupons":[{"dictValue":"满1000元可使用","serviceType":100008,"uid":84,"dictValue2":"满1000减100","createTime":"2018-10-15 12:05:50","cpid":6,"couponPrice":100.00,"updateTime":null,"fullPrice":1000.00,"dueTime":"2018-12-14","status":"1"},{"dictValue":"满1000元可使用","serviceType":100008,"uid":84,"dictValue2":"满1000减100","createTime":"2018-10-15 12:05:50","cpid":8,"couponPrice":100.00,"updateTime":null,"fullPrice":1000.00,"dueTime":"2018-12-14","status":"1"},{"dictValue":"满2500元可使用","serviceType":100008,"uid":84,"dictValue2":"满2500减300","createTime":"2018-10-15 12:05:50","cpid":7,"couponPrice":300.00,"updateTime":null,"fullPrice":2500.00,"dueTime":"2018-12-14","status":"2"}],"ycoid":0,"couponPrice":0.0,"userDiscountSubPrice":455.8,"useYcoid":"0","sumOldDisPrice":1823.2}}
        */
        app.wxRequest("/order/nowCanOpenLock", {did: content.data.wardrobeInfo.did}, function (res) {
            if (res.code == 1) {
                let data = res.data.locks;

                if (data.length > 0) {
                    content.setData({
                        locksList: data
                    });
                }
                else {
                    content.setData({
                        locksList: null
                    });
                }
            }
        });
    },
    openLock: function (e) {
        app.wxRequest("/relay/openLock", {lockId: e.currentTarget.dataset.id}, function (res) {
            if (res.code == 1) {
                app.showToast("开柜成功", "success");
            }
        });
    },
    onShow: function () {
        this.getWardrobeList();
    },
    toCartOrder: function () {
        app.redirect("/pages/goods/reserve-settle/index?did=" + this.data.wardrobeInfo.did, "navigateTo");
    },
     leave: function () {
        let content = this;

        wx.showModal({
            title: "提 示",
            content: "您确定要离开吗？确认完后会立即开门",
            success: function (res) {
                if (res.confirm) {
                    app.wxRequest("/relay/closeDoor", {did: 6}, function (res) {
                        if (res.code == 1) {
                            app.showToast("开门成功", "success");
                        }
                    });
                }
            }
        });
    }
});
