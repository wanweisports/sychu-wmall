package com.wardrobe.common.util;

import com.wardrobe.common.constant.IDBConstant;
import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.po.OtherResource;
import com.wardrobe.common.po.SysResources;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FileUtil {
	
	private static Logger logger = Logger.getLogger(FileUtil.class);
	
	public static OtherResource saveFile(MultipartHttpServletRequest multipartRequest, OtherResource resourceInfo) throws Exception {
		if(multipartRequest != null){
			String fileName = com.wardrobe.common.util.CustomizedPropertyConfigurer.getPhotoPath().toString();
			String absolutePath = new File("").getAbsolutePath();
			if(absolutePath.contains("bin")){
    			absolutePath = absolutePath.replace("bin", "webapps") + fileName;
    		}else{
    			absolutePath = absolutePath + File.separator + "webapps" + File.separator + fileName;
    		}
			logger.info("absolutePath===>" + absolutePath);
			int size = multipartRequest.getMultiFileMap().size();
			System.out.println("size===>" + size);
			for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = it.next();
		        MultipartFile imgFile = multipartRequest.getFile(key);
		        String originalFilename = imgFile.getOriginalFilename();
		        System.out.println("imgFile.getOriginalFilename()===>" + originalFilename);
		        if (originalFilename.length() > 0) {
		        	String imgName = System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));
		        	int b = -1;
		    		byte[] by = new byte[8096];
		    		OutputStream out = new FileOutputStream(absolutePath + imgName);
		    		InputStream inputStream = imgFile.getInputStream();
		    		while ((b=inputStream.read(by)) >= 0) {
		    			out.write(by, 0, b);
		    		}
		    		out.flush();
		    		out.close();
		    		if(resourceInfo == null){
		    			resourceInfo = new OtherResource();
		    		}
                    /*resourceInfo.setFileSize(String.valueOf(imgFile.getSize()));
                    resourceInfo.setResourceName(originalFilename);
                    resourceInfo.setResourcePath(imgName);
                    resourceInfo.setIsFlag("1");
                    resourceInfo.setAdddate(DateUtil.dateToString(new Date(), DateUtil.YYYYMMDDHHMMSS));*/
                    return resourceInfo;
		        }
			}
		}
		return null;
	}

	public static List<MultipartFile> getMultipartFiles(MultipartHttpServletRequest multipartRequest) {
		List<MultipartFile> multipartFiles = new ArrayList<MultipartFile>();
		if(multipartRequest != null){
            for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext(); ) {
                String key = it.next();
                MultipartFile file = multipartRequest.getFile(key);
                String originalFilename = file.getOriginalFilename();
                if (originalFilename.length() > 0) {
                	multipartFiles.add(file);
                }
            }
        }
		return multipartFiles;
	}

	public static StringBuilder saveFile(MultipartFile multipartFile, HttpServletRequest request) throws IOException {
		String originalFilename = multipartFile.getOriginalFilename();
		StringBuilder photoPath = new StringBuilder();
		photoPath.append(CustomizedPropertyConfigurer.getPhotoPath().toString());
		photoPath.append(StrUtil.getUUID());
		photoPath.append(originalFilename.substring(originalFilename.indexOf(".")));
		File file = new File(request.getSession().getServletContext().getRealPath("") + photoPath.toString());
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if(!file.exists()) {
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
		return photoPath;
	}

	public static List<SysResources> getSpringUpload(MultipartHttpServletRequest multipartRequest, String fold) throws IOException{
		return saveMultipartFiles(springUpload(multipartRequest), fold);
	}

	public static List<MultipartFile> springUpload(MultipartHttpServletRequest multipartRequest){
		List<MultipartFile> fileList = new ArrayList();
		if(multipartRequest != null) {
			for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext(); ) {
				String key = it.next();
				MultipartFile file = multipartRequest.getFile(key);
				if (file.getOriginalFilename().length() > 0) {
					fileList.add(file);
				}
			}
		}
		return fileList;
	}

	public static List<SysResources> saveMultipartFiles(List<MultipartFile> multipartFiles, String fold) throws IOException{
		List<SysResources> resources = new ArrayList();
		Timestamp timestamp = new Timestamp(new Date().getTime());
		multipartFiles.stream().forEach((multipartFile) -> {
			String originalFilename = multipartFile.getOriginalFilename();
			String fileName = getFileName(timestamp, originalFilename);
			SysResources resource = new SysResources();
			try{
				resource.setInputStream(multipartFile.getInputStream());
			}catch (Exception e){}
			resource.setResourceName(fileName);
			resource.setResourceOriginal(originalFilename);
			resource.setFileSize(multipartFile.getSize());
			resource.setStatus(IDBConstant.LOGIC_STATUS_YES);
			resource.setName(multipartFile.getName());
			resource.setResourcePath(fold + "/" + fileName);
			resource.setCreateTime(timestamp);
			resource.setResourceServiceParentId(null);  //上传之后修改
			resource.setResourceServiceId(IDBConstant.ZERO); //上传之后修改
			resource.setResourceServiceType(StrUtil.EMPTY); //上传之后修改
			resource.setResourceType(StrUtil.EMPTY); //上传之后修改
			resources.add(resource);
		});
		return resources;
	}

	public static String getFileName(Timestamp timestamp, String originalFilename) {
		return StrUtil.getUUID() + IPlatformConstant.YS_IMG_SUFFIX + originalFilename.substring(originalFilename.lastIndexOf("."));
	}

	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

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
