package com.hannuus.gamble.web.action;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hannuus.gamble.bean.Province;
import com.hannuus.gamble.vo.JsonVo;
import com.hannuus.gamble.web.service.IBaseInfoService;

/**
 * The system base info action, like province/city/district etc.
 * @author aelns
 *
 */
@Controller
@RequestMapping("/")
public class RootAction extends BaseAction {
	
	@Autowired
	IBaseInfoService baseInfoService;
	
	private static final Logger logger = Logger.getLogger(RootAction.class);
	
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public JsonVo<List<Province>> province(ModelMap modelMap) {
		JsonVo<List<Province>> json = new JsonVo<List<Province>>();
		try {
			List<Province> list = baseInfoService.getAllProvinceList();
			if (CollectionUtils.isNotEmpty(list)) {
				json.setT(list);
			}
			modelMap.put("list", list);
		} catch (Exception e) {
			logger.error(e);
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}