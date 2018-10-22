//index.js
const app = getApp();

Page({
    data: {
        goodsList: {
            saveHidden: true,
            totalPrice: 0,
            allSelect: true,
            noSelect: false,
            list: []
        },
        delBtnWidth: 120    //删除按钮宽度单位（rpx）
    },
    toListPage:function(){
        app.redirect("/pages/goods/list/index", "switchTab");
    },
    // 获取购物车列表
    getShoppingCart: function () {
        let content = this;

        app.wxRequest("/commodity/userShoppingCarts", {shoppingType: 1}, function (res) {
            var data = res.data;

            if (res.code == 1) {
                if (data && data.list && data.list.length > 0) {
                    data.totalPrice = data.sumPrice || 0;
                    data.saveHidden = true;
                    data.allSelect = true;
                    data.noSelect = false;
                    for (let i = 0; i < data.list.length; i++) {
                        //pic,name,label,price,number,left,active
                        data.list[i].pic = data.list[i].resourcePath;
                        data.list[i].name = data.list[i].commName;
                        data.list[i].label = "颜色：" + data.list[i].colorName + "、尺码：" + data.list[i].size;
                        data.list[i].number = data.list[i].count;
                        data.list[i].left = '';
                        data.list[i].active = false;
                    }
                }

                content.setData({
                    goodsList: data
                });

                content.setGoodsList(content.getSaveHide(), content.totalPrice(), content.allSelect(), content.noSelect(), data.list);
            }
        });
    },
    // 删除购物车列表
    deleteShoppingCart: function (scid) {
        app.wxRequest("/commodity/delShoppingCart", {scids: scid}, function (res) {});
    },
    //获取元素自适应后的实际宽度
    getEleWidth: function (w) {
        var real = 0;

        try {
            var res = wx.getSystemInfoSync().windowWidth;
            var scale = (750 / 2) / (w / 2);  //以宽度750px设计稿做宽度的自适应

            real = Math.floor(res / scale);

            return real;
        } catch (e) {
            return false;
        }
    },
    initEleWidth: function () {
        var delBtnWidth = this.getEleWidth(this.data.delBtnWidth);
        this.setData({
            delBtnWidth: delBtnWidth
        });
    },
    onLoad: function () {
        this.initEleWidth();
        this.getShoppingCart();
    },
    touchS: function (e) {
        if (e.touches.length == 1) {
            this.setData({
                startX: e.touches[0].clientX
            });
        }
    },
    touchM: function (e) {
        let index = e.currentTarget.dataset.index;

        if (e.touches.length == 1) {
            let moveX = e.touches[0].clientX;
            let disX = this.data.startX - moveX;
            let delBtnWidth = this.data.delBtnWidth;
            let left = "";

            if (disX == 0 || disX < 0) {
                left = "margin-left: 0px";
            } else if (disX > 0 ) {
                left = "margin-left: -" + disX + "px";
                if (disX >= delBtnWidth) {
                    left = "left: -" + delBtnWidth + "px";
                }
            }

            let list = this.data.goodsList.list;
            if (index != "" && index != null) {
                list[parseInt(index)].left = left;
                this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
            }
        }
    },
    touchE: function (e) {
        let index = e.currentTarget.dataset.index;

        if (e.changedTouches.length == 1) {
            let endX = e.changedTouches[0].clientX;
            let disX = this.data.startX - endX;
            let delBtnWidth = this.data.delBtnWidth;
            //如果距离小于删除按钮的1/2，不显示删除按钮
            let left = disX > delBtnWidth / 2 ? "margin-left: -" + delBtnWidth + "px" : "margin-left: 0px";

            let list = this.data.goodsList.list;
            if (index !== "" && index != null) {
                list[parseInt(index)].left = left;
                this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
            }
        }
    },
    delItem: function (e) {
        let index = e.currentTarget.dataset.index;
        let list = this.data.goodsList.list;
        let del = list.splice(index, 1);

        this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
        this.deleteShoppingCart(del[0].scid);
    },
    selectTap:function(e){
        var index = e.currentTarget.dataset.index;
        var list = this.data.goodsList.list;
        if(index!=="" && index != null){
            list[parseInt(index)].active = !list[parseInt(index)].active ;
            this.setGoodsList(this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),list);
        }
    },
    totalPrice: function () {
        var list = this.data.goodsList.list;
        var total = 0;
        for (var i = 0 ; i < list.length ; i++) {
            var curItem = list[i];
            if (curItem.active) {
                total += parseFloat(curItem.price) * curItem.number;
            }
        }
        total = parseFloat(total.toFixed(2));//js浮点计算bug，取两位小数精度
        return total;
    },
    allSelect:function(){
        var list = this.data.goodsList.list;
        var allSelect = false;
        for(var i = 0 ; i < list.length ; i++){
            var curItem = list[i];
            if(curItem.active){
                allSelect = true;
            }else{
                allSelect = false;
                break;
            }
        }
        return allSelect;
    },
    noSelect:function(){
        var list = this.data.goodsList.list;
        var noSelect = 0;
        for(var i = 0 ; i < list.length ; i++){
            var curItem = list[i];
            if(!curItem.active){
                noSelect++;
            }
        }
        if(noSelect == list.length){
            return true;
        }else{
            return false;
        }
    },
    setGoodsList: function(saveHidden, total, allSelect, noSelect, list) {
        this.setData({
            goodsList:{
                saveHidden: saveHidden,
                totalPrice: total,
                allSelect: allSelect,
                noSelect: noSelect,
                list: list
            }
        });

        let shopCartInfo = {}, tempNumber = 0;
        shopCartInfo.shopList = list;
        for(let i = 0; i < list.length; i++) {
            tempNumber = tempNumber + list[i].number
        }
        shopCartInfo.shopNum = tempNumber;

        app.setCookie("shopCartInfo", shopCartInfo);
    },
    bindAllSelect:function(){
        var currentAllSelect = this.data.goodsList.allSelect;
        var list = this.data.goodsList.list;
        if(currentAllSelect){
            for(var i = 0 ; i < list.length ; i++){
                var curItem = list[i];
                curItem.active = false;
            }
        }else{
            for(var i = 0 ; i < list.length ; i++){
                var curItem = list[i];
                curItem.active = true;
            }
        }

        this.setGoodsList(this.getSaveHide(),this.totalPrice(),!currentAllSelect,this.noSelect(),list);
    },
    jiaBtnTap:function(e){
        var index = e.currentTarget.dataset.index;
        var list = this.data.goodsList.list;
        if(index!=="" && index != null){
            if(list[parseInt(index)].number<10){
                list[parseInt(index)].number++;
                this.setGoodsList(this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),list);
            }
        }
    },
    jianBtnTap:function(e){
        var index = e.currentTarget.dataset.index;
        var list = this.data.goodsList.list;
        if(index!=="" && index != null){
            if(list[parseInt(index)].number>1){
                list[parseInt(index)].number-- ;
                this.setGoodsList(this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),list);
            }
        }
    },
    editTap:function(){
        var list = this.data.goodsList.list;
        for(var i = 0 ; i < list.length ; i++){
            var curItem = list[i];
            curItem.active = false;
        }
        this.setGoodsList(!this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),list);
    },
    saveTap:function(){
        var list = this.data.goodsList.list;
        for(var i = 0 ; i < list.length ; i++){
            var curItem = list[i];
            curItem.active = true;
        }
        this.setGoodsList(!this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),list);
    },
    getSaveHide:function(){
        var saveHidden = this.data.goodsList.saveHidden;
        return saveHidden;
    },
    // 删除
    deleteSelected: function () {
        let list = this.data.goodsList.list;
        let scid = [];

        list = list.filter(function(curGoods) {
            if (curGoods.active) {
                scid.push(curGoods.scid);
            }
            return !curGoods.active;
        });
        this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
        this.deleteShoppingCart(scid.join(","));
    },
    toPayOrder2: function () {
        app.redirect("/pages/goods/settle/index", "navigateTo");
    }
});
