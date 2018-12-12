package com.wardrobe.controller;

import com.wardrobe.common.annotation.Desc;
import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.enum_.MobileMessageEnum;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.UserCollection;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.view.UserInputView;
import com.wardrobe.platform.service.IUserCouponService;
import com.wardrobe.platform.service.IUserService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

    @Desc("用户收藏列表")
    @ResponseBody
    @RequestMapping("userCollections")
    public ResponseBean userCollections(UserInputView userInputView){
        userInputView.setUid(getUserInfo().getUid());
        PageBean pageBean = userService.userCollections(userInputView);
        return new ResponseBean(setPageInfo(pageBean));
    }

    @ResponseBody
    @RequestMapping("updateDidStatus")
    public ResponseBean updateDidStatus(String didStatus){
        userService.updateDidStatus(getUserInfo().getUid(), didStatus);
        return new ResponseBean(true);
    }

    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream(new File("D:\\Users\\HHHHH\\0-shiyijian2018-07-17\\img\\2.JPG"));

        BufferedImage bufImg = ImageIO.read(inputStream);// 把图片读入到内存中
        // 压缩代码
        bufImg = Thumbnails.of(bufImg).width(750).keepAspectRatio(true).outputQuality(0.9).asBufferedImage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();// 存储图片文件byte数组
        ImageIO.write(bufImg, "jpg", bos); // 图片写入到 ImageOutputStream
        ByteArrayInputStream input = new ByteArrayInputStream(bos.toByteArray());
        FileUtils.copyInputStreamToFile(input, new File("D:\\Users\\HHHHH\\0-shiyijian2018-07-17\\img\\12.jpg"));
    }


}











