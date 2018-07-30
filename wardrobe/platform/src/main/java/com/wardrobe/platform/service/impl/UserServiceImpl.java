package com.wardrobe.platform.service.impl;

import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.po.UserInfo;
import com.wardrobe.common.util.DateUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IUserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Override
    public UserInfo getUserInfo(String unionId){
        return baseDao.queryByHqlFirst("FROM UserInfo u WHERE u.unionId = ?", unionId);
    }

    /*
     * 授权添加用户
     */
    @Override
    public void addUser(UserInfo userInfo){
        Timestamp timestamp = DateUtil.getNowDate();
        userInfo.setCreateTime(timestamp);
        userInfo.setRegisterTime(timestamp);
        baseDao.save(userInfo, null);
    }

    /*
     * 用户完善资料
     */
    @Override
    public void updateUser(UserPerfectBean userPerfectBean){
        String unionId = userPerfectBean.getUnionId();
        UserInfo userInfo = getUserInfo(unionId);
        String inviteCode = userPerfectBean.getInviteCode();

        userInfo.setSex(userPerfectBean.getSex());
        userInfo.setAge(userPerfectBean.getAge());
        userInfo.setDressStyle(userPerfectBean.getDressStyle());
        userInfo.setUsualSize(userPerfectBean.getUsualSize());
        userInfo.setMobile(userPerfectBean.getMobile());
        userInfo.setInviteCode(inviteCode);


        if(StrUtil.isNotBlank(inviteCode)){ //有邀请人时
            Integer inviteCodeUserId = getUserIdByInviteCode(inviteCode);
            if(inviteCodeUserId != null){
                userInfo.setInvitedBy(inviteCodeUserId);
                //其他操作：如邀请人增加积分..等等

            }
        }
        baseDao.save(userInfo, userInfo.getUid());
    }

    /*
     * 邀请人是否存在
     */
    @Override
    public Integer getUserIdByInviteCode(String inviteCode){
        return StrUtil.objToInt(baseDao.getUniqueObjectResult("SELECT uid FROM user_info u WHERE u.inviteCode = ?", inviteCode));
    }

}
