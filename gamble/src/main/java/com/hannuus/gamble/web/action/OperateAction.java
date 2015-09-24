package com.hannuus.gamble.web.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.domain.page.PageQueryCallback;
import com.hannuus.gamble.model.Topic;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.utils.GambleUtils;
import com.hannuus.gamble.web.service.OperateService;

/**
 * 运营Web API
 * 
 * @author cuesky
 * @date 2015年9月21日下午4:19:13
 */
@Controller
@RequestMapping("/oper")
public class OperateAction extends BaseAction {

	@Autowired
	OperateService operateService;

	/**
	 * 分页查询运营人员
	 * 
	 * @param map
	 *            域模型
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	@RequestMapping("/listOpers")
	public ModelAndView listOpers(ModelMap map, int pageNum, int pageSize) {
		pageQuery(pageNum, pageSize, map, new PageQueryCallback<User>() {
			@Override
			public PageDTO<User> query(PageParams params) {
				return operateService.findOperPage(params);
			}
		});
		return new ModelAndView("/oper/oper_list");
	}

	/**
	 * 至新增或新增运营人员页面
	 * 
	 * @param map
	 *            域模型
	 * @param id
	 *            用户ID
	 * @return
	 */
	@RequestMapping("/toEditOper")
	public ModelAndView toEditOper(ModelMap map, Long id) {
		if (id != null) {
			User user = operateService.findOperById(id);
			map.put("user", user);
		}
		return new ModelAndView("/oper/oper_edit");
	}

	/**
	 * 新增或编辑运营人员资料
	 * 
	 * @param map
	 *            域模型
	 * @param user
	 *            运营人员
	 * @return
	 */
	@RequestMapping("/doEditOper")
	public ModelAndView doEditOper(ModelMap map, User user) {
		operateService.saveOrUpdateOper(user);
		return listOpers(map, 1, 0);
	}

	/**
	 * 根据ID删除一条运营人员记录
	 * 
	 * @param map
	 *            域模型
	 * @param id
	 *            运营人员ID
	 * @return
	 */
	@RequestMapping("/doDeleteOper")
	public ModelAndView doDeleteOper(ModelMap map, Long id) {
		operateService.deleteOper(id);
		return listOpers(map, 1, 0);
	}

	/**
	 * 分页查询虚拟主题
	 * 
	 * @param map
	 *            域模型
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	@RequestMapping("/listTopics")
	public ModelAndView listTopics(ModelMap map, int pageNum, int pageSize) {
		pageQuery(pageNum, pageSize, map, new PageQueryCallback<Topic>() {
			@Override
			public PageDTO<Topic> query(PageParams params) {
				return operateService.findTopicPage(params);
			}
		});
		return new ModelAndView("/oper/topic_list");
	}

	/**
	 * 至上传主题页面
	 * 
	 * @return
	 */
	@RequestMapping("/toBatchAddTopics")
	public ModelAndView toBatchAddTopics() {
		return new ModelAndView("/oper/topic_upload");
	}

	// public JsonVo<Integer> validateUserIds(HttpServletRequest request) {
	// JsonVo<Integer> json = new JsonVo<Integer>();
	// ServletContext servletContext = request.getSession()
	// .getServletContext();
	// MultipartFile file = getUploadFile(request, servletContext);
	// return json;
	// }

	/**
	 * 批量新增主题
	 * 
	 * @param map
	 *            域模型
	 * @param request
	 *            复合请求
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doBatchAddTopics")
	public ModelAndView doBatchAddTopics(ModelMap map,
			HttpServletRequest request) throws Exception {
		ServletContext servletContext = request.getSession()
				.getServletContext();
		String uploadPath = servletContext.getRealPath("/")
				+ R.global.upload_path;
		MultipartFile file = getUploadFile(request, servletContext);
		// 保存至本地
		saveFile(uploadPath, file);
		// 批量新增
		operateService.batchAddTopics(file.getInputStream());
		return listTopics(map, 1, 0);
	}

	private MultipartFile getUploadFile(HttpServletRequest request,
			ServletContext servletContext) {
		MultipartFile file = null;
		CommonsMultipartResolver resolver = new CommonsMultipartResolver(
				servletContext);
		if (resolver.isMultipart(request)) {
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			file = req.getFile(R.oper.upload_file_name);
		}
		return file;
	}

	/**
	 * @param uploadPath
	 *            文件上传路径
	 * @param file
	 *            上传的文件对象
	 * @throws IOException
	 */
	private void saveFile(String uploadPath, MultipartFile file)
			throws IOException {
		String fileName = file.getOriginalFilename();
		fileName = GambleUtils.File.appendTimestamp(fileName);
		String path = uploadPath + "/" + fileName;
		file.transferTo(new File(path));
	}
}
