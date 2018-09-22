package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.annotation.NotPerfect;
import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.enum_.MobileMessageEnum;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.UserCollection;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.platform.service.IUserCouponService;
import com.wardrobe.platform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@RequestMapping("user")
@Controller
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserCouponService userCouponService;

    /*
     * 用户完善资料
     */
    @NotPerfect
    @ResponseBody
    @RequestMapping("updateUser")
    public ResponseBean updateUser(UserPerfectBean userPerfectBean, String code){
        System.out.println(JsonUtils.fromJson(userPerfectBean));
        if(!super.checkMobileMessage(MobileMessageEnum.USER_PERFECT, userPerfectBean.getMobile(), code)) throw new MessageException("验证码错误");
        userPerfectBean.setUserId(getUserInfo().getUid());
        userService.updateUser(userPerfectBean);
        return new ResponseBean(true);
    }

    /*
     * 邀请人是否存在，并不能是自己
     */
    @ResponseBody
    @RequestMapping("checkInviteCode")
    public ResponseBean checkInviteCode(String inviteCode){
        userService.checkInviteCode(inviteCode, getUserInfo().getUid());
        return new ResponseBean(true);
    }

    /*
     * 个人中心
     */
    @ResponseBody
    @RequestMapping("userCenter")
    public ResponseBean userCenter(){
        return new ResponseBean(userService.getUserCenter(getUserInfo().getUid()));
    }

    /*
     * 个人设置
     */
    @ResponseBody
    @RequestMapping("userSetting")
    public ResponseBean userSetting(){
        return new ResponseBean(userService.getUserSetting(getUserInfo().getUid()));
    }

    /*
     * 修改手机号，验证第一步原手机号
     */
    @ResponseBody
    @RequestMapping("checkOldMobile")
    public ResponseBean checkOldMobile(String mobile, String code){
        boolean success = super.checkMobileMessage(MobileMessageEnum.USER_UPDATE_MOBILE_OLD, mobile, code);
        if(!success) {
            return new ResponseBean(false, "验证码不正确，请重新输入");
        }
        return new ResponseBean(true);
    }

    /*
     * 修改手机号，验证及修改第二步新手机号
     */
    @ResponseBody
    @RequestMapping("updateNewMobile")
    public ResponseBean updateNewMobile(String mobile, String code){
        if(!super.checkMobileMessage(MobileMessageEnum.USER_UPDATE_MOBILE_NEW, mobile, code)) throw new MessageException("验证码不正确，请重新输入");

        userService.updateUserMobile(getUserInfo().getUid(), mobile);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("isPerfect")
    public ResponseBean isPerfect(){
        boolean isPerfect = userService.userIsPerfect(getUserInfo().getUid());
        return new ResponseBean(new HashMap(1, 1){{put("isPerfect", isPerfect ? IDBConstant.LOGIC_STATUS_YES  : IDBConstant.LOGIC_STATUS_NO);}});
    }

    @Desc("用户充值")
    @ResponseBody
    @RequestMapping("userRecharge")
    public ResponseBean userRecharge(int dictId, double price){
        int oid = userService.saveUserRecharge(dictId, getUserInfo().getUid(), price);
        return new ResponseBean(new HashMap(){{put("oid", oid);}});
    }

    @Desc("用户优惠券列表")
    @ResponseBody
    @RequestMapping("userCouponList")
    public ResponseBean userCouponList(){
        return new ResponseBean(new HashMap(){{put("coupons", userCouponService.getUserCouponList(getUserInfo().getUid()));}});
    }

    @Desc("用户添加收藏")
    @ResponseBody
    @RequestMapping("saveCollection")
    public ResponseBean saveCollection(UserCollection userCollection){
        userCollection.setUid(getUserInfo().getUid());
        userService.saveCollection(userCollection);
        return new ResponseBean(true);
    }

    @Desc("用户删除收藏")
    @ResponseBody
    @RequestMapping("cancelCollection")
    public ResponseBean cancelCollection(int cid){
        userService.deleteCollection(cid, getUserInfo().getUid());
        return new ResponseBean(true);
    }

}











