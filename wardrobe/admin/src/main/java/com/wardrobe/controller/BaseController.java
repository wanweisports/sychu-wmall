package com.wardrobe.controller;

import com.wardrobe.common.bean.PageBean;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.common.view.BaseInputView;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class BaseController {
	public static final String REQUEST_HEAD = "text/html; charset=UTF-8";
	
	public static final String APPLICATION_JSON = "application/json";
    
	public static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	
	protected String redirect(String path) {
        return new StringBuilder(UrlBasedViewResolver.REDIRECT_URL_PREFIX).append(path).toString();
    }

    protected String forward(String path) {
        return new StringBuilder(UrlBasedViewResolver.FORWARD_URL_PREFIX).append(path).toString();
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
	
    protected void setPageInfo(Model model, PageBean pageBean, String pageURL, BaseInputView baseInputView){
		model.addAttribute("page", pageBean);

		int splitPage = 5;
		int currentPage = pageBean.getCurrentPage();
		int totalPage = pageBean.getTotalPage();
		int startPage = 1, endPage = totalPage;
		if(currentPage > splitPage){
			startPage = currentPage - splitPage + 1;
			model.addAttribute("prefix", true);
		}

		if(currentPage+splitPage < totalPage){
			endPage = currentPage + splitPage;
			model.addAttribute("suffix", true);
		}
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		//参数
		model.addAttribute("pageURL", pageURL + "?syc=" + StrUtil.objToHtmlParams(baseInputView));
    }

    protected String getStreamResult(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		InputStream in=request.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len=in.read(buffer))!=-1){
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
		return new String(out.toByteArray(),"utf-8");
	}
    
}
