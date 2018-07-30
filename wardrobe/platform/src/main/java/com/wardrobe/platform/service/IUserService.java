package com.wardrobe.platform.service;

import com.wardrobe.common.bean.UserPerfectBean;
import com.wardrobe.common.po.UserInfo;

/**
 * Created by 雷达 on 2018/7/30.
 */
public interface IUserService {

    UserInfo getUserInfo(String unionId);

    void addUser(UserInfo userInfo);

    void updateUser(UserPerfectBean userPerfectBean);

    Integer getUserIdByInviteCode(String inviteCode);

}
