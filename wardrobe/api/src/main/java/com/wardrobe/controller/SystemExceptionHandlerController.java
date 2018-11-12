package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.exception.MessageException;
import com.wardrobe.common.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 只有Controller报错才会执行此类
 */
@ControllerAdvice
public class SystemExceptionHandlerController {

    private Logger logger = Logger.getLogger(SystemExceptionHandlerController.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseBean exceptionHandler(Exception e, HttpServletResponse response)throws Exception{
        logger.error(DateUtil.dateToString(new Date(), DateUtil.YYYYMMDDHHMMSS) + "【api错误日志】：" + e.getMessage());
        e.printStackTrace(); //可以保存到数据库
        if(e instanceof MessageException) {
            return new ResponseBean(false, e.getMessage());
        }
        return new ResponseBean(false);
    }

}
