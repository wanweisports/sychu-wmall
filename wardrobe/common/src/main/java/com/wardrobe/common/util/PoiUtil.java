package com.wardrobe.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PoiUtil {
	
	public static String getEncodingFileName(String fileName, String userAgent) {
        try {
            String encodingFileName = URLEncoder.encode(fileName, "UTF8");
            // 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
            if (userAgent != null) {
                userAgent = userAgent.toLowerCase();
                // IE浏览器，只能采用URLEncoder编码
                if (userAgent.indexOf("msie") != -1) {
                    fileName = "filename=\"" + encodingFileName + "\"";
                }
                // Opera浏览器只能采用filename*
                else if (userAgent.indexOf("opera") != -1) {
                    fileName = "filename*=UTF-8''" + encodingFileName;
                }
                // Safari浏览器，只能采用ISO编码的中文输出
                else if (userAgent.indexOf("safari") != -1) {
                    fileName = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
                }
                // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
                else if (userAgent.indexOf("mozilla") != -1) {
                    fileName = "filename*=UTF-8''" + encodingFileName;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }
	
}
