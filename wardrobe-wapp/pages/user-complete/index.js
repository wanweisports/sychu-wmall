var app = getApp()

Page({
  data: {
    sexIndex: 0,
    userSexList: [],
    ageIndex: 0,
    userAgeList: [],
    userStyleList: [],
    userSizeList: []
  },
  onLoad() {
    this.getStyleDicts();
    this.getUserSizeDicts();
    this.getUserSexDicts();
    this.getUserAgeDicts();
  },
  getStyleDicts: function () {
    var that = this;

    app.wxRequest(
      "/dict/getDicts",
      {
        dictName: "COMM_STYLE"
      },
      function (res) {
        if (res.code == 1) {
          that.setData({
            userStyleList: res.data.dicts
          });
        }
      }
    );
  },
  bindStyleTap: function (e) {
    console.log(e)
  },
  getUserSizeDicts: function () {
    var that = this;

    app.wxRequest(
      "/dict/getDicts",
      {
        dictName: "USER_SIZE"
      },
      function (res) {
        that.setData({
            userSizeList: res.data.dicts
        });
      }
    );
  },
  bindSizeTap: function (e) {
    console.log(e)
  },
  getUserSexDicts: function () {
    var that = this;

    app.wxRequest(
      "/dict/getDicts",
      {
        dictName: "USER_SEX"
      },
      function (res) {
        if (res.code == 1) {
          that.setData({
            userSexList: res.data.dicts
          });
        }
      }
    );
  },
  bindSexChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      sexIndex: e.detail.value
    })
  },
  getUserAgeDicts: function () {
    var that = this;

    app.wxRequest(
      "/dict/getDicts",
      {
        dictName: "USER_AGE"
      },
      function (res) {
        if (res.code == 1) {
          that.setData({
            userAgeList: res.data.dicts
          });
        }
      }
    );
  },
  bindAgeChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      ageIndex: e.detail.value
    })
  },
  getSMSCode: function () {
    var that = this;

    app.wxRequest(
      "/message/getCode",
      {
        mobile: "15801303167",
        type: 2
      },
      function (res) {
        console.log(res)
      },
      function (err) {
        console.log(err)
      }
    );
  },
  submitSMSCode: function () {
    var that = this;

    app.wxRequest(
      "/user/checkOldMobile",
      {
        mobile: "15801303167",
        code: "1234"
      },
      function (res) {
        console.log(res)
      },
      function (err) {
        console.log(err)
      }
    );
  }
})
