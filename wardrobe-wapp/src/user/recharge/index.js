const app = getApp();

Page({
    data: {
        balance: "",
        dictId: "",
        rechargeList  : []
    },
    onShareAppMessage: null,
    setSelected: function (index) {
        this.data.rechargeList.forEach(function (item) {
            item.selected = false;
        });

        if (index >= 0) {
            this.data.rechargeList[index].selected = true;
        }

        this.setData({
            rechargeList : this.data.rechargeList
        });
    },
    getUserRechargeList: function () {
        let content = this;

        app.wxRequest("/dict/getDicts", {
            dictName: "RECHARGE_TYPE"
        }, function (res) {
            let data = res.data;

            if (res.code == 1) {
                data.dicts[0].selected = true;

                content.setData({
                    rechargeList : data.dicts,
                    dictId : data.dicts[0].dictId,
                    balance : data.dicts[0].dictValue,
                });
            }
            else {
                app.showToast("获取充值字典失败", "none");
            }
        }, function () {
            app.showToast("获取充值字典错误", "none");
        });
    },
    onShow: function () {
        this.getUserRechargeList();
    },
    bindRechargeTap: function (e) {
        let data = e.currentTarget.dataset;

        console.log(data);

        this.setSelected(data.index);

        this.setData({
            balance : data.balance,
            dictId  : data.id
        });
    },
    bindBalanceBlur: function (e) {
        this.setSelected(-1);

        this.setData({
            balance : e.detail.value,
            dictId  : ""
        });
    },
    bindSave: function () {
        let price = this.data.balance;

        if (price == "" || price * 1 < 0) {
            wx.showModal({
                title: '错误',
                content: '请填写正确的充值金额',
                showCancel: false
            });
            return;
        }

        //wxpay.wxpay(app, price, 0, "/pages/my/index");
    }
});
