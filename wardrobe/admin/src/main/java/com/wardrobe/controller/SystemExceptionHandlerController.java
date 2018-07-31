package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.interceptor.InterceptorHelp;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 只有Controller报错才会执行此类
 */
@ControllerAdvice
public class SystemExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseBean exceptionHandler(Exception e, HttpServletRequest request)throws Exception{
        System.out.println(DateUtil.formatDate(new Date()) + "【错误日志】：" + e.getMessage());
        if(InterceptorHelp.isAjax(request)) {
            e.printStackTrace();
            if(e instanceof MessageException) {
                return new ResponseBean(e.getMessage());
            }else{
                return new ResponseBean(false);
            }
        }else{
            e.printStackTrace(); //可以保存到数据库
        }
        return null;
    }

}
