package com.wardrobe.controller;

import com.wardrobe.common.annotation.NotProtected;
import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IXcxService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 1、前端检测有无session，没有的话wx.login()，获取session_key 。
 2、通过session_key 调用后台接口，换取session。
 3、前端缓存session。
 4、如果前端检测有session，调用wx.checkSession()，判断session_key 有效期，然后请求业务。
 */
@Controller
public class XCXController extends BaseController {

    @Autowired
    private IXcxService xcxService;

    @NotProtected
    @ResponseBody
    @RequestMapping("login")
    public ResponseBean xcxLogin(String code, String iv, String encryptedData, HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        System.out.println(sessionId);
        Map<String, Object> data = new HashMap<>();
        System.out.println(session.getAttribute("sessionId"));
        JSONObject jsonObject = xcxService.xcxLogn(StrUtil.objToStr(session.getAttribute("sessionId")), code, IPlatformConstant.APP_ID, IPlatformConstant.APP_SECRET, iv, encryptedData);
        System.out.println(jsonObject);
        String unionId = jsonObject.getString("unionId");
        data.put("sessionId", sessionId); //前端使用
        data.put("unionId", unionId);
        session.setAttribute("sessionId", jsonObject.getString("openid") + "_" + jsonObject.getString("session_key"));
        request.getSession().setAttribute(IPlatformConstant.LOGIN_USER_ID, unionId); //session不一样，需要解决
        System.out.println("=========================================================================================");
        return new ResponseBean(data);
    }

    @NotProtected
    @ResponseBody
    @RequestMapping("notLogin")
    public ResponseBean notLogin(){
        return new ResponseBean(IPlatformConstant.FAIL_NOT_LOGIN_CODE, null);
    }

    @ResponseBody
    @RequestMapping("notPerfect")
    public ResponseBean notPerfect(){
        return new ResponseBean(IPlatformConstant.FAIL_NOT_PERFECT_CODE, null);
    }

}
