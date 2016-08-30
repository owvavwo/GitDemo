package com.hui.software;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile; 

@Controller
public class UploadFileController implements ServletContextAware {
	private ServletContext servletContext;
	private static final Logger logger  = LoggerFactory.getLogger(UploadFileController.class);
	
	
	@Override
	public void setServletContext(ServletContext context) { 
		this.servletContext = context;
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String handleUploadData(String whatever, @RequestParam("file") CommonsMultipartFile file) {
		if(!file.isEmpty()) { 
			String path = servletContext.getRealPath("/files/");    //��ñ��ش洢·��
			logger.info("The path is " + path);
			
			File filePath  = new File(path);                      //��������ļ����ļ���
			if(!filePath.exists()) {
				filePath.mkdirs();
			} 
			
			String fileName = file.getOriginalFilename(); 
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			
			File realFile  = new File(path, new Date().getTime() + fileType);
			try {
				file.getFileItem().write(realFile);;
			} catch ( Exception e) {
				e.printStackTrace();
				return "redirect:/upload_error";
			}
			
			return "upload_ok";
		} else {
			return "redirect:/upload_error";
		}
		
		
	}
	
	@RequestMapping("/upload_ok")
	public String apply1() {
		return "upload_ok";
	}
	
	@RequestMapping("/upload_error")
	public String apply2() {
		return "upload_error";
	}

}
