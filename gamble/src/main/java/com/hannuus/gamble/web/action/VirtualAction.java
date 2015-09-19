package com.hannuus.gamble.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hannuus.gamble.comm.JsonVo;
import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.domain.page.PageQueryCallback;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.web.service.VirtualService;

/**
 * 该类用来行使如下操作：<br>
 * 1、虚拟用户CRUD<br>
 * 2、虚拟内容CRUD<br>
 * 3、虚拟回复CRUD<br>
 * 
 * @author cuesky
 * @date 2015年9月18日下午4:39:14
 */
@Controller
@RequestMapping("/virtual")
public class VirtualAction extends BaseAction {

	@Autowired
	VirtualService virtualService;

	/**
	 * 分页查询所有虚拟用户
	 * 
	 * @param map
	 *            域模型
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	@RequestMapping("/listUsers")
	public ModelAndView listUsers(ModelMap map, int pageNum, int pageSize) {
		pageQuery(pageNum, pageSize, map, new PageQueryCallback<User>() {
			@Override
			public PageDTO<User> query(PageParams params) {
				return virtualService.findUserPage(params);
			}
		});
		return new ModelAndView("/virtual/user_list");
	}

	/**
	 * 至批量新增虚拟用户界面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddUsers")
	public ModelAndView toAddUser() {
		return new ModelAndView("/virtual/user_edit");
	}

	/**
	 * 检查seed是否可用
	 * 
	 * @param seed
	 * @return 1-可用 0-不可用
	 */
	@ResponseBody
	@RequestMapping("/isSeedAvailable.json")
	public JsonVo<Integer> isSeedAvailable(String seed) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		boolean flag = virtualService.isSeedAvailable(seed);
		if (flag) {
			json.setResult(1);
		} else {
			json.setResult(0);
		}
		return json;
	}

	/**
	 * 批量添加虚拟用户
	 * 
	 * @param map
	 *            域模型
	 * @param seed
	 *            用于生成虚拟用户名的种子
	 * @param num
	 *            生成的虚拟用户数量
	 * @return
	 */
	@RequestMapping("/doBatchAddUsers")
	public ModelAndView doBatchAddUsers(ModelMap map, String seed, int num) {
		virtualService.batchAddeUsers(seed, num);
		return listUsers(map, 1, 0);
	}

}
