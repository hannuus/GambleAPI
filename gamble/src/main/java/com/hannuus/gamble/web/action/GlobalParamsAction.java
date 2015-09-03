package com.hannuus.gamble.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hannuus.core.json.JsonVo;
import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.domain.page.PageQueryCallback;
import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.gamble.web.service.GlobalParamsService;

/**
 * 全局参数设置API
 * 
 * @author cuesky
 * @date 2015年9月3日 上午11:26:49
 */
@Controller
@RequestMapping("/global")
public class GlobalParamsAction extends BaseAction {

	@Autowired
	GlobalParamsService globalParamsService;

	/**
	 * 分页查询所有全局参数
	 * 
	 * @param map
	 *            域模型
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	@RequestMapping("/listParams")
	public ModelAndView listParams(ModelMap map, int pageNum, int pageSize) {
		pageQuery(pageNum, pageSize, map,
				new PageQueryCallback<GlobalParams>() {
					@Override
					public PageDTO<GlobalParams> query(PageParams params) {
						return globalParamsService.findGlobalParamsPage(params);
					}
				});
		return new ModelAndView("/global/params_list");
	}

	/**
	 * 至添加全局参数类型页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddParamsType")
	public ModelAndView toAddParamsType() {
		return new ModelAndView("/global/params_type_edit");
	}

	/**
	 * For ajax<br>
	 * 判断是否存在一个全局参数类型
	 * 
	 * @param params
	 * @return 1-存在 0-不存在
	 */
	@ResponseBody
	@RequestMapping("/isTypeExists.json")
	public JsonVo<Integer> isTypeExists(GlobalParams params) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		boolean flag = globalParamsService.isTypeExists(params);
		if (flag) {
			json.setResult(1);
		} else {
			json.setResult(0);
		}
		return json;
	}

	/**
	 * 添加一个全局参数
	 * 
	 * @param map
	 * @param params
	 * @return
	 */
	@RequestMapping("/doAddParamsType")
	public ModelAndView doAddParamsType(ModelMap map, GlobalParams params) {
		globalParamsService.addGlobalParams(params);
		return listParams(map, 1, 0);
	}

	/**
	 * 至更新全局参数类型页面
	 * 
	 * @return
	 */
	@RequestMapping("/toUpdateParamsType")
	public ModelAndView toUpdateParamsType(ModelMap map, Long id) {
		GlobalParams params = globalParamsService.findGlobalParams(id);
		map.put("params", params);
		return new ModelAndView("/global/params_type_edit");
	}

	/**
	 * 更新全局参数类型
	 * 
	 * @return
	 */
	@RequestMapping("/doUpdateParamsType")
	public ModelAndView doUpdateParamsType(ModelMap map, String oldType_name,
			String oldTypeValue, GlobalParams params) {
		globalParamsService.updateGlobalParamsType(oldType_name, oldTypeValue,
				params);
		return listParams(map, 1, 0);
	}

	/**
	 * 删除全局参数
	 * 
	 * @return
	 */
	@RequestMapping("/doDeleteParamsType")
	public ModelAndView doDeleteParamsType(ModelMap map, GlobalParams params) {
		globalParamsService.deleteGlobalParamsType(params);
		return listParams(map, 1, 0);
	}

	/**
	 * 至添加全局参数页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddParams")
	public ModelAndView toAddParams(ModelMap map) {
		// 查询所有参数类型
		List<GlobalParams> types = globalParamsService.findParamsTypes();
		map.put("types", types);
		return new ModelAndView("/global/params_edit");
	}

	/**
	 * For ajax<br>
	 * 判断是否存在一个全局参数
	 * 
	 * @param params
	 * @return 1-存在 0-不存在
	 */
	@ResponseBody
	@RequestMapping("/isParamsExists.json")
	public JsonVo<Integer> isParamsExists(GlobalParams params) {
		JsonVo<Integer> json = new JsonVo<Integer>();
		boolean flag = globalParamsService.isParamsExists(params);
		if (flag) {
			json.setResult(1);
		} else {
			json.setResult(0);
		}
		return json;
	}

	/**
	 * 添加全局参数
	 * 
	 * @return
	 */
	@RequestMapping("/doAddParams")
	public ModelAndView doAddParams(ModelMap map, GlobalParams params) {
		globalParamsService.addGlobalParams(params);
		return listParams(map, 1, 0);
	}

	/**
	 * 至更新全局参数页面
	 * 
	 * @return
	 */
	@RequestMapping("/toUpdateParams")
	public ModelAndView toUpdateParams(ModelMap map, Long id) {
		GlobalParams params = globalParamsService.findGlobalParams(id);
		map.put("params", params);
		return new ModelAndView("/global/params_edit");
	}

	/**
	 * 更新全局参数
	 * 
	 * @return
	 */
	@RequestMapping("/doUpdateParams")
	public ModelAndView doUpdateParams(ModelMap map, GlobalParams params) {
		globalParamsService.updateGlobalParams(params);
		return listParams(map, 1, 0);
	}

	/**
	 * 删除全局参数
	 * 
	 * @return
	 */
	@RequestMapping("/doDeleteParams")
	public ModelAndView doDeleteParams(ModelMap map, Long id) {
		globalParamsService.deleteGlobalParams(id);
		return listParams(map, 1, 0);
	}

}
