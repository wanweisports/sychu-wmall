package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.exception.MessageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 只有Controller报错才会执行此类
 */
@ControllerAdvice
public class SystemExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseBean exceptionHandler(Exception e, HttpServletResponse response)throws Exception{
        System.out.println("【错误日志】：" + e.getMessage());
        e.printStackTrace(); //可以保存到数据库
        if(e instanceof MessageException) {
            return new ResponseBean(false, e.getMessage());
        }
        return new ResponseBean(false);
    }

}
