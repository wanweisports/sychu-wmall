"use strict";App({onLaunch:function(){this.setCookie("syc_appName","衣否")},toLogin:function(){var e=this;if(this.globalData.sessionId)return this.checkSession(this.checkUserComplete,function(){e.globalData.sessionId="",e.login()});this.login()},checkSession:function(e,t){e=e||new Function,t=t||new Function,wx.showLoading({title:"加载中",mask:!0}),wx.checkSession({success:function(){wx.hideLoading(),e()},fail:function(){wx.hideLoading(),t()}})},login:function(){var e=this;wx.showLoading({title:"加载中",mask:!0}),wx.login({success:function(t){var i=t.code;wx.getUserInfo({withCredentials:!0,success:function(t){var o=t.iv,n=t.encryptedData;wx.hideLoading(),e.wxRequest("/login",{code:i,encryptedData:n,iv:o},function(t){1==t.code?(e.globalData.sessionId=t.data.sessionId,e.getCookie("syc_leave")?e.redirect(e.getCookie("syc_leave"),"redirectTo"):e.redirect(e.getCookie("syc_target"),e.getCookie("syc_redirect"))):e.showToast("授权登录失败","none")},function(t){e.showLog("[F][/login]："+JSON.stringify(t)),e.showToast("授权登录错误","none")})},fail:function(t){wx.hideLoading(),e.showLog("[F][wx.getUserInfo]："+JSON.stringify(t)),e.showToast("用户授权失败","none")}})}})},checkUserComplete:function(e,t){var i=this;i.wxRequest("/user/isPerfect",{},function(e){1==e.code&&(2==e.data.perfect?i.redirect("/pages/user/complete/index"):i.redirect("/pages/index/index","switchTab"))},function(e){i.showLog("[F][/user/isPerfect]："+JSON.stringify(e)),t(e)})},checkUserStatus:function(e){var t=this;t.wxRequest("/user/userCenter",{},function(t){if(1==t.code&&1==t.data.didStatus)return e(!0);e(!1)},function(i){t.showLog("[F][/user/userCenter]："+JSON.stringify(i)),e(!1)})},updateUserStatus:function(e,t){var i=this;e=e||2,i.wxRequest("/user/updateDidStatus",{didStatus:e},function(e){t(1==e.code?!0:!1)},function(e){i.showLog("[F][/user/updateDidStatus]："+JSON.stringify(e)),t(!1)})},sendTempleMsg:function(e,t,i,o,n,s){var a=this;wx.request({url:a.config.getApiHost()+"/template-msg/put",method:"POST",header:{"content-type":"application/x-www-form-urlencoded"},data:{token:that.globalData.token,type:0,module:"order",business_id:e,trigger:t,template_id:i,form_id:o,url:n,postJsonString:s},success:function(e){}})},wxRequest:function(e,t,i,o,n,s){var a=this;s=s||{isLoading:!0},s.isLoading&&wx.showLoading({title:"加载中",mask:!0}),i=i||new Function,o=o||new Function,n=n||!1;var c=n?{}:{Cookie:"JSESSIONID="+a.globalData.sessionId};e=-1==e.indexOf(a.config.getApiHost())?a.config.getApiHost()+e:e,wx.request({url:e,data:t,header:c,success:function(t){s.isLoading&&wx.hideLoading(),10==t.data.code?(a.globalData.sessionId="",a.redirect("/pages/landing/index","reLaunch")):20==t.data.code?wx.showModal({title:"提 示",content:"请先完善信息！",showCancel:!1,success:function(t){t.confirm&&(e.indexOf("/order/saveOrder")>-1?a.redirect("/pages/user/complete/index?next=cart","navigateTo"):e.indexOf("/order/saveReserveOrder")>-1?a.redirect("/pages/user/complete/index?next=room","navigateTo"):e.indexOf("/relay/openDoor")>-1?a.redirect("/pages/user/complete/index?next=center","navigateTo"):a.redirect("/pages/user/complete/index","navigateTo"))}}):i(t.data)},fail:function(t){a.showLog("[F]["+e+"]："+JSON.stringify(t)),o(t),s.isLoading&&wx.hideLoading()}})},wxPay:function(e,t){var i=this;this.wxRequest("/order/wxPayPackage",{orderId:e},function(e){if(1==e.code){var o=e.data;wx.requestPayment({timeStamp:o.timeStamp,nonceStr:o.nonceStr,package:o.package,signType:o.signType,paySign:o.paySign,fail:function(e){wx.showToast({title:"支付失败:"+e})},success:function(){wx.showToast({title:"支付成功"}),i.redirect(t,"navigateTo")}})}else 2==e.code?i.redirect(t,"navigateTo"):i.showToast("调起支付失败:"+e.message)},function(e){i.showToast("调起支付错误:"+e)})},onShareAppMessage:function(e){return e=e||{},e.title=e.title||this.config.shareProfile,e.path=e.path||"/pages/index/index",e.imgUrl=e.imgUrl||"",{title:e.title,path:e.path,imgUrl:e.imgUrl,success:function(e){},fail:function(e){}}},redirect:function(e,t){return"navigateTo"==t?wx.navigateTo({url:e}):"switchTab"==t?wx.switchTab({url:e}):"reLaunch"==t?wx.reLaunch({url:e}):wx.redirectTo({url:e})},showToast:function(e,t){setTimeout(function(){wx.showToast({title:e,mask:!0,icon:t||"none"}),setTimeout(function(){wx.hideToast()},2e3)},0)},showLog:function(e){var t=(new Date).getTime();this.config.isDebug&&console.log("["+t+"]："+e)},getCookie:function(e){return wx.getStorageSync(e)},setCookie:function(e,t){wx.setStorageSync(e,t)},clearCookie:function(e){wx.removeStorageSync(e)},clearCookieAll:function(){wx.clearStorageSync()},globalData:{userInfo:null,sessionId:"",isPerfect:!1},config:{getApiHost:function(){return"https://mystore.yifoutech.com/api"},version:1545103713841,shareProfile:"百款精品商品，总有一款适合您",isDebug:!1}});