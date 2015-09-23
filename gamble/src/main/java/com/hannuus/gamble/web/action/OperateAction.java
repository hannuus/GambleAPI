package com.hannuus.gamble.web.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.domain.page.PageQueryCallback;
import com.hannuus.gamble.model.User;
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
	public ModelAndView doDeleteOper(ModelMap map, Long id) {
		operateService.deleteOper(id);
		return listOpers(map, 1, 0);
	}

	public ModelAndView listTopics() {
		return null;
	}

	public ModelAndView toBatchAddTopics() {
		return null;
	}

	public ModelAndView doBatchAddTopics(HttpServletRequest request) {
		return null;
	}

}
