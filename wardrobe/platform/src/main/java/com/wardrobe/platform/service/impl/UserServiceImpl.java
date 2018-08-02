package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.po.UserAccount;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IUserAccountService;
import com.wardrobe.platform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private IUserAccountService userAccountService;

    @Override
    public UserInfo getUserInfo(int uid){
        return baseDao.getToEvict(UserInfo.class, uid);
    }

    @Override
    public UserInfo getUserInfoByUnionId(String unionId){
        return baseDao.queryByHqlFirst("FROM UserInfo WHERE unionId = ?1", unionId);
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
        Map<String, Object> data = new HashMap<>(4, 1);

        UserInfo userInfo = getUserInfo(uid);
        data.put("nickname", userInfo.getNickname());
        data.put("rank", userInfo.getRank());

        UserAccount userAccount = userAccountService.getUserAccount(uid);
        data.put("balance", userAccount.getBalance().doubleValue());
        data.put("ycoid", userAccount.getYcoid().doubleValue());
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
    public void updateUserMobile(int uid, String newMoblie){
        UserInfo userInfo = getUserInfo(uid);
        userInfo.setMobile(newMoblie);
        baseDao.save(userInfo, uid);
    }

    @Override
    public boolean userIsPerfect(int uid){
        return IDBConstant.LOGIC_STATUS_YES.equals(baseDao.getUniqueObjectResult("SELECT isPerfect FROM user_info WHERE uid = ?1", uid));
    }

}














