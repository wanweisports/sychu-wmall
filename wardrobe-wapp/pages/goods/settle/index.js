"use strict";var app=getApp();Page({data:{goodsListId:[],goodsList:[],allGoodsPrice:0,yunPrice:0,allGoodsAndYunPrice:0,curCoupon:null,couponsIndex:0,serviceType:2,cpid:0,coupons:[],hasCoupons:!1,ycoidList:[],ycoidIndex:0,ycoid:0,useYcoid:0,hasYcoid:!1,userInfo:{}},getCartSettle:function(){var e=this;app.wxRequest("/commodity/settlement",{scids:e.data.goodsListId.join(",")},function(t){if(1==t.code){var a=t.data.coupons,s=t.data.ycoid,o=[];!!a&&a.forEach(function(e){1==e.status&&(e.textShow=e.dictValue,o.push(e))}),e.setData({goodsList:t.data.list,coupons:o.length>0?[{textShow:"不使用优惠券",cpid:""}].concat(o):[{textShow:"无可用优惠券",cpid:""}],hasCoupons:o.length>0,couponPrice:0,allGoodsPrice:t.data.sumOldDisPrice,allGoodsAndYunPrice:t.data.sumPrice,ycoid:t.data.ycoid,useYcoid:0,ycoidList:s>0?[{textShow:"不使用薏米",ycoid:"0"},{textShow:t.data.ycoid+"薏米",ycoid:t.data.ycoid}]:[{textShow:"没有薏米",ycoid:"0"}],hasYcoid:s>0,yunPrice:t.data.freight})}})},countCartSettle:function(){var e=this;app.wxRequest("/commodity/settlementCount",{scids:e.data.goodsListId.join(","),serviceType:e.data.serviceType,cpid:e.data.cpid},function(t){1==t.code&&e.setData({allGoodsAndYunPrice:t.data.sumPrice,yunPrice:t.data.freight,couponPrice:t.data.couponPrice,useYcoid:t.data.useYcoid})})},bindCouponsChange:function(e){var t=this.data.coupons[e.detail.value];this.setData({couponsIndex:e.detail.value,curCoupon:t,serviceType:1,cpid:t.cpid}),t.cpid&&this.countCartSettle()},bindYcoidChange:function(e){this.data.ycoidList[e.detail.value];this.setData({ycoidIndex:e.detail.value,serviceType:2}),this.countCartSettle()},onLoad:function(){var e=this,t=[],a=app.getCookie("shopCartInfo");a&&a.shopList&&(t=a.shopList.filter(function(e){return e.active}));for(var s=[],o=0;o<t.length;o++)s.push(t[o].scid);e.setData({goodsListId:s}),e.getCartSettle()},createOrder:function(){var e=this;if(!e.data.curAddressData)return void wx.showModal({title:"提 示",content:"请先设置您的收货地址！",showCancel:!1});var t={expressName:e.data.curAddressData.linkMan,expressMobile:e.data.curAddressData.mobile,expressAddress:e.data.curAddressData.address,scids:e.data.goodsListId.join(","),serviceType:e.data.serviceType,cpid:e.data.cpid};app.wxRequest("/order/saveOrder",t,function(e){app.clearCookie("shopCartInfo"),app.wxPay(e.data.oid,"/pages/user/order-list/index")})},addAddress:function(){var e=this;wx.chooseAddress({success:function(t){e.setData({curAddressData:{linkMan:t.userName,mobile:t.telNumber,address:t.provinceName+" "+t.cityName+" "+t.countyName+" "+t.detailInfo}})}})},selectAddress:function(){var e=this;wx.chooseAddress({success:function(t){e.setData({curAddressData:{linkMan:t.userName,mobile:t.telNumber,address:t.provinceName+" "+t.cityName+" "+t.countyName+" "+t.detailInfo}})}})}});