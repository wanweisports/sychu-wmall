var app = getApp()

Page({
  data: {
    userSexList: ["帅哥", "美女"],
    userAgeList: ["85后", "90后"]
  },
  onLoad() {
    this.getStyleDicts();
    this.getUserSizeDicts();
    this.getUserSexDicts();
    this.getUserAgeDicts();
  },
  getStyleDicts: function () {
    app.wxRequest(
      "/dict/getDicts",
      {
        dictName: "COMM_STYLE"
      },
      function (res) {
        console.log(res)
      },
      function (err) {
        console.log(err)
      }
    );
  },
  getUserSizeDicts: function () {
    app.wxRequest(
      "/dict/getDicts",
      {
        dictName: "USER_SIZE"
      },
      function (res) {
        console.log(res)
      },
      function (err) {
        console.log(err)
      }
    );
  },
  getUserSexDicts: function () {
    app.wxRequest(
      "/dict/getDicts",
      {
        dictName: "USER_SEX"
      },
      function (res) {
        console.log(res)
      },
      function (err) {
        console.log(err)
      }
    );
  },
  getUserAgeDicts: function () {
    app.wxRequest(
      "/dict/getDicts",
      {
        dictName: "USER_AGE"
      },
      function (res) {
        console.log(res)
      },
      function (err) {
        console.log(err)
      }
    );
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
