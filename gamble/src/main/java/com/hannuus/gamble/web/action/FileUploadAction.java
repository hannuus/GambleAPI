package com.hannuus.gamble.web.action;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hannuus.gamble.comm.GambleAPIErrorCode;
import com.hannuus.gamble.comm.SystemConstants;
import com.hannuus.core.json.JsonResultStatus;
import com.hannuus.core.json.JsonVo;


@Controller
@RequestMapping("/upload")
public class FileUploadAction {
	
	@Autowired
	private HttpServletRequest request;
	private static Logger logger = LogManager.getLogger(FileUploadAction.class);
	public static final List<String> ALLOW_TYPES = Arrays.asList(
            "image/jpg","image/jpeg","image/png","image/gif"
    );
	
	@ResponseBody
	@RequestMapping(value = "/userlogo.json", method = RequestMethod.POST)
	public JsonVo<String> uploadUserLogo(@RequestParam("file") MultipartFile file) {
		return doUpload(file, SystemConstants.UPLOAD_FOLDER_USER_LOGO);
	}
	
	@ResponseBody
	@RequestMapping(value = "/image.json", method = RequestMethod.POST)
	public JsonVo<String> uploadImage(@RequestParam("file") MultipartFile file) {
		return doUpload(file, SystemConstants.UPLOAD_FOLDER);
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin.json", method = RequestMethod.POST)
	public JsonVo<String> uploadAdminImage(@RequestParam("file") MultipartFile file) {
		return doUpload(file, SystemConstants.UPLOAD_ADMIN_FOLDER);
	}
	
	private JsonVo<String> doUpload(MultipartFile file, String uploadPath) {
		JsonVo<String> json = new JsonVo<String>();
		json.setStatus(JsonResultStatus.Failed.getValue());
		json.setErrcode(GambleAPIErrorCode.EmptyUploadFile.getCode()); 
		json.setErrmsg(GambleAPIErrorCode.EmptyUploadFile.getReasoning());
		if (!file.isEmpty()) {
			try {
				String fileNamePath = uploadPath + System.currentTimeMillis()
						+ getExtensionName(file.getOriginalFilename());
				String filePath = request.getSession().getServletContext()
						.getRealPath("/")
						+ File.separator + fileNamePath;
				// 图片大小超出限制
				if (file.getSize() > 1024 * 1024 * 2) {
					json.setErrcode(GambleAPIErrorCode.UnSupportFileSize.getCode());
					json.setErrmsg(GambleAPIErrorCode.UnSupportFileSize.getReasoning());
					json.setStatus(JsonResultStatus.Failed.getValue());
					return json;
				}
				// 不支持的图片类型
				if (!ALLOW_TYPES.contains(file.getContentType())) {
					json.setErrcode(GambleAPIErrorCode.UnSupoortFileType.getCode());
					json.setErrmsg(GambleAPIErrorCode.UnSupoortFileType.getReasoning());
					json.setStatus(JsonResultStatus.Failed.getValue());
					return json;
				}
				// 转存图片
				file.transferTo(new File(filePath));
				json.setStatus(JsonResultStatus.Success.getValue());
				json.setResult(fileNamePath);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				json.setStatus(JsonResultStatus.Failed.getValue());
				json.setErrcode(GambleAPIErrorCode.ImageUploadFailed.getCode());
				json.setErrmsg(GambleAPIErrorCode.ImageUploadFailed.getReasoning() + " Details: " + e.getMessage());
			}
		}
		return json;
	}
	
	private String getExtensionName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return "."+filename.substring(dot + 1);   
            }   
        }   
        return filename;   
    }   

}
