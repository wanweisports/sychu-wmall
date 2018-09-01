package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.SysDict;
import com.wardrobe.common.po.UserAccount;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.util.Arith;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.JsonUtils;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.UserInputView;
import com.wardrobe.platform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private IUserAccountService userAccountService;

    @Autowired
    private IUserCouponService userCouponService;

    @Autowired
    private ISysRankService sysRankService;

    @Override
    public UserInfo getUserInfo(int uid){
        return baseDao.getToEvict(UserInfo.class, uid);
    }

    @Override
    public UserInfo getUserInfoByOpenId(String unionId){
        return baseDao.queryByHqlFirst("FROM UserInfo WHERE openId = ?1", unionId);
    }

    /*
     * 授权登录添加用户
     */
    @Override
    public void addUser(UserInfo userInfo){
        baseDao.save(userInfo.init(), null);
        userAccountService.initUserAccount(userInfo.getUid(), userInfo.getRegisterTime());
    }

    /*
     * 用户完善资料
     */
    @Override
    public void updateUser(UserPerfectBean userPerfectBean){
        int userId = userPerfectBean.getUserId();
        UserInfo userInfo = getUserInfo(userId);
        String inviteCode = userPerfectBean.getInviteCode();

        userInfo.setSex(userPerfectBean.getSex());
        userInfo.setAge(userPerfectBean.getAge());
        userInfo.setDressStyle(userPerfectBean.getDressStyle());
        userInfo.setUsualSize(userPerfectBean.getUsualSize());
        userInfo.setMobile(userPerfectBean.getMobile());
        userInfo.setInviteCode(inviteCode);
        userInfo.setIsPerfect(IDBConstant.LOGIC_STATUS_YES); //已完善资料


        if(StrUtil.isNotBlank(inviteCode)){ //有邀请人时, 并且不能是自己的邀请码
            Integer inviteCodeUserId = checkInviteCode(inviteCode, userId);
            userInfo.setInvitedBy(inviteCodeUserId);
            //其他操作：如邀请人增加积分..等等


        }
        baseDao.save(userInfo, userInfo.getUid());
    }

    /*
     * 邀请人是否存在，并不能是自己
     */
    @Override
    public Integer checkInviteCode(String inviteCode, int uid){
        Integer inviteCodeUserId = getUserIdByInviteCode(inviteCode);
        if(inviteCodeUserId == null) throw new MessageException("邀请码不存在！");
        if(inviteCodeUserId == uid) throw new MessageException("邀请码不能是自己的！");
        return inviteCodeUserId;
    }

    private Integer getUserIdByInviteCode(String inviteCode){
        return StrUtil.objToInt(baseDao.getUniqueObjectResult("SELECT uid FROM user_info u WHERE u.inviteCode = ?1", inviteCode));
    }

    /*
     * 个人中心
     */
    @Override
    public Map<String, Object> getUserCenter(int uid){
        Map<String, Object> data = new HashMap<>(6, 1);

        UserInfo userInfo = getUserInfo(uid);
        data.put("nickname", userInfo.getNickname());
        data.put("headImg", userInfo.getHeadImg());

        UserAccount userAccount = userAccountService.getUserAccount(uid);
        data.put("balance", userAccount.getBalance().doubleValue());
        data.put("ycoid", userAccount.getYcoid());
        data.put("rank", userAccount.getRank());
        data.put("point", userAccount.getScore());
        return data;
    }

    @Override
    public Map<String, Object> getUserSetting(int uid){
        Map<String, Object> data = new HashMap<>(3, 1);

        UserInfo userInfo = getUserInfo(uid);
        String mobile = userInfo.getMobile();
        data.put("nickname", userInfo.getNickname());
        data.put("mobile", mobile);
        data.put("mobileEllipsis", new StringBuilder(mobile).replace(3, 6, "XXXX"));
        return data;
    }

    @Override
    public void updateUserMobile(int uid, String newMoblie) {
        UserInfo userInfo = getUserInfo(uid);
        userInfo.setMobile(newMoblie);
        baseDao.save(userInfo, uid);
    }

    @Override
    public boolean userIsPerfect(int uid){
        return IDBConstant.LOGIC_STATUS_YES.equals(baseDao.getUniqueObjectResult("SELECT isPerfect FROM user_info WHERE uid = ?1", uid));
    }

    @Override
    public synchronized void saveUserRecharge(int dictId, int userId, double price){
        userAccountService.addRechargeOrderInfo(userId, dictId, price);
    }

    @Override
    public PageBean getUserListIn(UserInputView userInputView) {
        PageBean pageBean = getUsersIn(userInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.stream().forEach((map) -> {
            getUserType(map);
            getUserAccountType(map);
        });
        return pageBean;
    }

    private Map<String, Object> getUserType(Map<String, Object> map) {
        String dressStyle = StrUtil.objToStr(map.get("dressStyle"));
        String usualSize = StrUtil.objToStr(map.get("usualSize"));
        map.put("dressStyleName", getTypes(dressStyle, IDBConstant.COMM_STYLE));
        map.put("usualSizeName", getTypes(usualSize, IDBConstant.USER_SIZE));
        map.put("sexName", dictService.getDictValue(IDBConstant.USER_SEX, StrUtil.objToStr(map.get("sex"))));
        Integer invitedBy = StrUtil.objToInt(map.get("invitedBy"));
        if(invitedBy != null){
            map.put("invitedByUserName", getUserInfo(invitedBy).getNickname());
        }
        return map;
    }

    private Map<String, Object> getUserAccountType(Map<String, Object> map) {
        map.put("rankName", sysRankService.getRankInfoByRank(StrUtil.objToInt(map.get("rank"))).getRankName());
        return map;
    }

    private PageBean getUsersIn(UserInputView userInputView){

        String nickname = userInputView.getNickname();
        String mobile = userInputView.getMobile();

        StringBuilder headSql = new StringBuilder("SELECT ui.*, ua.balance, ua.ycoid, ua.score, ua.rank");
        StringBuilder bodySql = new StringBuilder(" FROM user_info ui, user_account ua");
        StringBuilder whereSql = new StringBuilder(" WHERE ui.uid = ua.uid");
        if(StrUtil.isNotBlank(nickname)){
            whereSql.append(" AND ui.nickname = :nickname");
        }
        if (StrUtil.isNotBlank(mobile)) {
            whereSql.append(" AND ui.mobile = :mobile");
        }
        return super.getPageBean(headSql, bodySql, whereSql, userInputView);
    }

    @Override
    public Map<String, Object> getMembersDetailIn(int userId){
        Map<String, Object> data = new HashMap(4, 1);
        data.put("user", getUserType(JsonUtils.fromJson(getUserInfo(userId))));
        data.put("userAccount", getUserAccountType(JsonUtils.fromJson(userAccountService.getUserAccount(userId))));
        data.put("userCoupon", userCouponService.getUserCoupons(userId));

        return data;
    }

}














