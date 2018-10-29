package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.*;
import com.wardrobe.common.util.*;
import com.wardrobe.common.view.UserInputView;
import com.wardrobe.platform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private ICommodityService commodityService;

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
        String inviteCode = StrUtil.objToStr(StrUtil.initCode(4));
        while (getUserIdByInviteCode(inviteCode) != null){
            inviteCode = StrUtil.objToStr(StrUtil.initCode(4));
        }
        userInfo.setInviteCode(inviteCode);
        baseDao.save(userInfo.init(), null);
        userAccountService.initUserAccount(userInfo);

        //送优惠券
        //平台新注册用户，均获赠一张满1000-100优惠券。有效期2个月。
        //平台试运营期间（截至11月30日），新注册用户额外赠送2张优惠券：一张满1000-100，一张满2500-300。有效期2个月。
        List<SysCouponRule> couponRules = userCouponService.getSysCouponRules(IDBConstant.LOGIC_STATUS_YES);//1：注册赠送优惠券类型
        userCouponService.addUserCoupons(couponRules, userInfo.getUid());
    }

    /*
     * 用户完善资料
     */
    @Override
    public void updateUser(UserPerfectBean userPerfectBean){

        int userId = userPerfectBean.getUserId();
        UserInfo ui = baseDao.queryByHqlFirst("FROM UserInfo WHERE mobile = ?1 AND uid != ?2", userPerfectBean.getMobile(), userId);
        if(ui != null) throw new MessageException("手机号已被注册，请重新输入！");

        UserInfo userInfo = getUserInfo(userId);
        synchronized (userInfo.getOpenId().intern()) {
            String inviteCode = userPerfectBean.getInviteCode();

            //userInfo.setSex(userPerfectBean.getSex());
            userInfo.setAge(userPerfectBean.getAge());
            userInfo.setDressStyle(userPerfectBean.getDressStyle());
            userInfo.setUsualSize(userPerfectBean.getUsualSize());
            userInfo.setMobile(userPerfectBean.getMobile());
            userInfo.setIsPerfect(IDBConstant.LOGIC_STATUS_YES); //已完善资料

            if (StrUtil.isNotBlank(inviteCode)) { //有邀请人时, 并且不能是自己的邀请码
                Integer inviteCodeUserId = checkInviteCode(inviteCode, userId);
                userInfo.setInvitedBy(inviteCodeUserId);
                //其他操作：如邀请人增加积分..等等

            }
            //根据手机号查询是否是老用户
            UserOldInfo userOldInfo = baseDao.queryByHqlFirst("FROM UserOldInfo WHERE mobile = ?1", userInfo.getMobile());
            if (userOldInfo != null) {
                UserAccount userAccount = userAccountService.getUserAccount(userId);
                userAccount.setScore(userAccount.getScore() + userOldInfo.getScore());
                baseDao.save(userAccount, userAccount.getUid());
                //计算等级
                userAccountService.updateRank(userId);
            }
            baseDao.save(userInfo, userInfo.getUid());
        }
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
        Map<String, Object> data = new HashMap<>(8, 1);

        UserInfo userInfo = getUserInfo(uid);
        data.put("nickname", userInfo.getNickname());
        data.put("headImg", userInfo.getHeadImg());
        data.put("inviteCode", userInfo.getInviteCode());

        UserAccount userAccount = userAccountService.getUserAccount(uid);
        data.put("balance", userAccount.getBalance().doubleValue());
        data.put("ycoid", userAccount.getYcoid());
        data.put("rank", userAccount.getRank());
        data.put("point", userAccount.getScore());

        data.put("couponCount", userCouponService.getUserCouponCount(uid));
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
    public synchronized int saveUserRecharge(int dictId, int userId, double price){
        return userAccountService.addRechargeOrderInfo(userId, dictId, price);
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
        String invitedBy = userInputView.getInvitedBy();

        StringBuilder headSql = new StringBuilder("SELECT ui.*, ua.balance, ua.ycoid, ua.score, ua.rank");
        StringBuilder bodySql = new StringBuilder(" FROM user_info ui, user_account ua");
        StringBuilder whereSql = new StringBuilder(" WHERE ui.uid = ua.uid");
        if(StrUtil.isNotBlank(nickname)){
            whereSql.append(" AND ui.nickname LIKE :nickname");
            userInputView.setNickname("%" + nickname + "%");
        }
        if (StrUtil.isNotBlank(mobile)) {
            whereSql.append(" AND ui.mobile = :mobile");
        }
        if(StrUtil.isNotBlank(invitedBy)){
            whereSql.append(" AND EXISTS(SELECT 1 FROM user_info uin WHERE uin.uid = ui.invitedBy AND uin.nickname LIKE :invitedBy)");
            userInputView.setInvitedBy("%" + invitedBy + "%");
        }
        return super.getPageBean(headSql, bodySql, whereSql, userInputView);
    }

    @Override
    public Map<String, Object> getMembersDetailIn(int userId){
        Map<String, Object> data = new HashMap(3, 1);
        data.put("user", getUserType(JsonUtils.fromJson(getUserInfo(userId))));
        data.put("userAccount", getUserAccountType(JsonUtils.fromJson(userAccountService.getUserAccount(userId))));
        data.put("userCoupon", userCouponService.getUserCoupons(userId));

        return data;
    }

    @Override
    public void saveCollection(UserCollection userCollection){
        if(getUserCollection(userCollection.getCid(), userCollection.getUid()) == null) {
            userCollection.setCreateTime(DateUtil.getNowDate());
            baseDao.save(userCollection, null);
        }
    }

    @Override
    public void deleteCollection(int cid, int uid){
        UserCollection userCollection = getUserCollection(cid, uid);
        if(userCollection != null){
            baseDao.delete(userCollection);
        }
    }

    @Override
    public UserCollection getUserCollection(int cid, int uid){
        return baseDao.queryByHqlFirst("FROM UserCollection WHERE cid = ?1 AND uid = ?2", cid, uid);
    }

    @Override
    public PageBean userCollections(UserInputView userInputView){
        Integer uid = userInputView.getUid();
        StringBuilder headSql = new StringBuilder("SELECT uc.*, ci.commName, ci.price, ci.brandName");
        StringBuilder bodySql = new StringBuilder(" FROM user_collection uc, commodity_info ci");
        StringBuilder whereSql = new StringBuilder(" WHERE uc.cid = ci.cid AND uc.uid = :uid AND ci.status = '").append(IDBConstant.LOGIC_STATUS_YES).append("'");
        PageBean pageBean = super.getPageBean(headSql, bodySql, whereSql, userInputView);
        List<Map<String, Object>> list = pageBean.getList();
        list.stream().forEach(commodity -> {
            commodity.put("resourcePath", commodityService.getFmImg(StrUtil.objToInt(commodity.get("cid"))));//0表示封面图
        });
        return pageBean;
    }

}














