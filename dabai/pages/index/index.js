//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: getApp().data.openId,
    toastText: '',
    userInfo: {},
    saoyisao: false,//控制显示
    name: '',//网点名字
    company: '',     //网点企业
    wd:'',
    hasUserInfo: false,
    isShowToast: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },

  onShow() {
    // 登录
    wx.login({
      success: res => {
        var that = this
        var appid = 'wx6d0679b11da9a628'; //填写微信小程序appid  
        var secret = '9fe4d75c618996538852cd0500c6ebe9'; //填写微信小程序secret  
        //调用request请求api转换登录凭证  
        wx.request({
          url: "https://erp.mod-softs.com/wxapi.php",
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          method: "POST",
          data: { fun: "Openid", code: res.code },
          complete: function (res) {
            getApp().data.openId = res.data
          }
        })
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    var that = this
    if (that.data.wd!=''){
      //保存网点编码，全局变量

      wx.request({
        url: "https://erp.mod-softs.com/wxapi.php",
        header: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        method: "POST",
        data: { fun: "findCustomerByNo", customerNo: that.data.wd },
        complete: function (res) {
          if (res == null || res.data == null) {
            console.error('网络请求失败');
            that.setData({
              count: 1500,
              toastText: '网络请求失败',
              isShowToast: true
            });
            that.showToast();
            return;
          }
          if (res.data.success == 0) {
            return;
          }
          if (getApp().data.customerNo != res.data.customerNo) {
            getApp().data.carts = []
            getApp().data.hasList = false
          }
          getApp().data.customerNo = res.data.customerNo,
            getApp().data.cartsao = true;
          that.setData({
            name: res.data.name,
            company: res.data.company,
            saoyisao: true
          });
        }
      })
    }
    
    
  },
  showToast: function () {  
    var _this = this;  
    // toast时间  
    _this.data.count = parseInt(_this.data.count) ? parseInt(_this.data.count) : 3000;  
    // 显示toast  
    _this.setData({  
      isShowToast: true,  
    });  
    // 定时器关闭  
    setTimeout(function () {  
      _this.setData({  
        isShowToast: false  
      });  
    }, _this.data.count);  
  },
  scanCode2: function () {
    var that = this
    wx.showModal({
      title: '',
      content: '请扫描货架上方二维码',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')
          wx.scanCode({
            success: function (res) {

              //保存网点编码，全局变量

              wx.request({
                url:"https://erp.mod-softs.com/wxapi.php",
                header: {
                  "Content-Type": "application/x-www-form-urlencoded"
                },
                method: "POST", 
                data: { fun: "findCustomerByNo", customerNo: res.result },
                complete: function (res) {
                
                  if (res == null || res.data == null) {
                    console.error('网络请求失败');
                    that.setData({
                      count: 1500,
                      toastText: '网络请求失败',
                      isShowToast: true
                    });
                    that.showToast(); 
                    return;
                  }
                  if (res.data.success==0){
                    that.setData({
                      count: 1500, 
                      toastText: '没有找到该网点',
                      isShowToast: true
                    });
                    that.showToast(); 
                    return;
                  }
                  if (getApp().data.customerNo != res.data.customerNo){
                    getApp().data.carts = []
                    getApp().data.hasList=false
                  }
                  getApp().data.customerNo = res.data.customerNo,
                  getApp().data.cartsao = true;
                  that.setData({
                    name: res.data.name,
                    company: res.data.company,
                    saoyisao: true
                  });
                }
              })  
            },
            fail: function (res) {
            }
          })
        }
      }
    })
  },
  scanCode3: function () {
    var that = this
    wx.scanCode({
      success: function (res) {


        wx.request({
          url: "https://erp.mod-softs.com/wxapi.php",
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          method: "POST",
          data: { fun: "findGoodsByNo", customerNo: getApp().data.customerNo, goodsNo: res.result },
          complete: function (res) {

            if (res == null || res.data == null) {
              console.error('网络请求失败');
              that.setData({
                count: 1500,
                toastText: '网络请求失败',
                isShowToast: true
              });
              that.showToast();
              return;
            }
            if (res.data.success == 0) {
              that.setData({
                count: 1500,
                toastText: '没有找到该商品',
                isShowToast: true
              });
              that.showToast();
              return;
            }
            getApp().data.goodsNo = res.data.goodsNo
            
            for (var i = 0; i < getApp().data.carts.length; i++) {
              if (getApp().data.carts[i].id == res.data.goodsNo){
                getApp().data.carts[i].num = getApp().data.carts[i].num+1;
                wx.showToast({
                  title: '已添加购物车',
                  icon: 'success',
                  duration: 2000
                })
                return;
              }
            }

            //保存商品编码，全局变量
            var newarray = [
              {
                id: res.data.goodsNo, title: res.data.name, image: res.data.img, num: 1, price: res.data.price, selected: true, customerNo: res.data.customerNo, goodsNo: res.data.goodsNo
              }
            ]
            getApp().data.carts = getApp().data.carts.concat(newarray);
            getApp().data.hasList = true
            wx.showToast({
              title: '已添加购物车',
              icon: 'success',
              duration: 2000
            })



          }
        })

        
      },
      fail: function (res) {
      }
    })


  },
    scanCode: function () {
    var that = this
    wx.showModal({
      title: '',
      content: '请扫描货架上方二维码',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')
          wx.scanCode({
            success: function (res) {
              that.addCart();
              wx.showToast({
                title: '已添加购物车',
                icon: 'success',
                duration: 2000
              })
            },
            fail: function (res) {
            }
          })
        }
      }
    })


  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  onLoad: function (options) {
    var that = this
    var wd = options['wd'];
    that.setData({
      wd: wd
    })

  }, 
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})
