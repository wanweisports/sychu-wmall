"use strict";var app=getApp();Page({data:{goodsList:{saveHidden:!0,totalPrice:0,allSelect:!0,noSelect:!1,list:[]},delBtnWidth:120},toListPage:function(){app.redirect("/pages/goods/list/index","switchTab")},getShoppingCart:function(){var t=this;app.wxRequest("/commodity/userShoppingCarts",{shoppingType:2},function(e){var i=e.data;if(1==e.code){if(i&&i.list&&i.list.length>0){i.totalPrice=i.sumPrice||0,i.saveHidden=!0,i.allSelect=!0,i.noSelect=!1;for(var s=0;s<i.list.length;s++)i.list[s].pic=i.list[s].resourcePath,i.list[s].name=i.list[s].commName,i.list[s].label="颜色："+i.list[s].colorName+"、尺码："+i.list[s].size,i.list[s].number=i.list[s].count,i.list[s].left="",i.list[s].active=!1}t.setData({goodsList:i}),t.setGoodsList(t.getSaveHide(),t.totalPrice(),t.allSelect(),t.noSelect(),i.list)}})},deleteShoppingCart:function(t){app.wxRequest("/commodity/delShoppingCart",{scids:t},function(t){})},getEleWidth:function(t){try{var e=wx.getSystemInfoSync().windowWidth,i=375/(t/2);return Math.floor(e/i)}catch(t){return!1}},initEleWidth:function(){var t=this.getEleWidth(this.data.delBtnWidth);this.setData({delBtnWidth:t})},onLoad:function(){this.initEleWidth(),this.getShoppingCart()},touchS:function(t){1==t.touches.length&&this.setData({startX:t.touches[0].clientX})},touchM:function(t){var e=t.currentTarget.dataset.index;if(1==t.touches.length){var i=t.touches[0].clientX,s=this.data.startX-i,a=this.data.delBtnWidth,o="";0==s||s<0?o="margin-left: 0px":s>0&&(o="margin-left: -"+s+"px",s>=a&&(o="left: -"+a+"px"));var l=this.data.goodsList.list;""!=e&&null!=e&&(l[parseInt(e)].left=o,this.setGoodsList(this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),l))}},touchE:function(t){var e=t.currentTarget.dataset.index;if(1==t.changedTouches.length){var i=t.changedTouches[0].clientX,s=this.data.startX-i,a=this.data.delBtnWidth,o=s>a/2?"margin-left: -"+a+"px":"margin-left: 0px",l=this.data.goodsList.list;""!==e&&null!=e&&(l[parseInt(e)].left=o,this.setGoodsList(this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),l))}},delItem:function(t){var e=t.currentTarget.dataset.index,i=this.data.goodsList.list,s=i.splice(e,1);this.setGoodsList(this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),i),this.deleteShoppingCart(s[0].scid)},selectTap:function(t){var e=t.currentTarget.dataset.index,i=this.data.goodsList.list;""!==e&&null!=e&&(i[parseInt(e)].active=!i[parseInt(e)].active,this.setGoodsList(this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),i))},totalPrice:function(){for(var t=this.data.goodsList.list,e=0,i=0;i<t.length;i++){var s=t[i];s.active&&(e+=parseFloat(s.price)*s.number)}return e=parseFloat(e.toFixed(2))},allSelect:function(){for(var t=this.data.goodsList.list,e=!1,i=0;i<t.length;i++){if(!t[i].active){e=!1;break}e=!0}return e},noSelect:function(){for(var t=this.data.goodsList.list,e=0,i=0;i<t.length;i++){t[i].active||e++}return e==t.length},setGoodsList:function(t,e,i,s,a){this.setData({goodsList:{saveHidden:t,totalPrice:e,allSelect:i,noSelect:s,list:a}});var o={},l=0;o.shopList=a;for(var n=0;n<a.length;n++)l+=a[n].number;o.shopNum=l,app.setCookie("shopOrderInfo",o)},bindAllSelect:function(){var t=this.data.goodsList.allSelect,e=this.data.goodsList.list;if(t)for(var i=0;i<e.length;i++){var s=e[i];s.active=!1}else for(var i=0;i<e.length;i++){var s=e[i];s.active=!0}this.setGoodsList(this.getSaveHide(),this.totalPrice(),!t,this.noSelect(),e)},jiaBtnTap:function(t){var e=t.currentTarget.dataset.index,i=this.data.goodsList.list;""!==e&&null!=e&&i[parseInt(e)].number<10&&(i[parseInt(e)].number++,this.setGoodsList(this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),i))},jianBtnTap:function(t){var e=t.currentTarget.dataset.index,i=this.data.goodsList.list;""!==e&&null!=e&&i[parseInt(e)].number>1&&(i[parseInt(e)].number--,this.setGoodsList(this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),i))},editTap:function(){for(var t=this.data.goodsList.list,e=0;e<t.length;e++){t[e].active=!1}this.setGoodsList(!this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),t)},saveTap:function(){for(var t=this.data.goodsList.list,e=0;e<t.length;e++){t[e].active=!0}this.setGoodsList(!this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),t)},getSaveHide:function(){return this.data.goodsList.saveHidden},deleteSelected:function(){var t=this.data.goodsList.list,e=[];t=t.filter(function(t){return t.active&&e.push(t.scid),!t.active}),this.setGoodsList(this.getSaveHide(),this.totalPrice(),this.allSelect(),this.noSelect(),t),this.deleteShoppingCart(e.join(","))},toPayOrder2:function(){app.redirect("/pages/goods/reserve/index","navigateTo")}});