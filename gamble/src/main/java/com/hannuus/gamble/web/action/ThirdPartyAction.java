package com.hannuus.gamble.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hannuus.gamble.model.GlobalParams;
import com.hannuus.gamble.web.service.ThirdPartyService;

/**
 * Just for self-maintenance
 * 
 * @author cuesky
 * @date 2015年9月3日 下午9:33:46
 */
@Controller
@RequestMapping("/thirdParty")
public class ThirdPartyAction extends BaseAction {

	@Autowired
	ThirdPartyService thirdPartyService;

	/**
	 * 查询所有第三方API
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list() {
		List<GlobalParams> thirdParties = thirdPartyService.findAllThirdParty();
		return new ModelAndView("/thirdparty/party_list", "thirdParties",
				thirdParties);
	}

	/**
	 * 至更新第三方API页面
	 * 
	 * @param id
	 *            GlobalParams ID
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(Long id) {
		GlobalParams party = thirdPartyService.findThirdParty(id);
		return new ModelAndView("/thirdparty/party_edit", "party", party);
	}

	/**
	 * 更新第三方API<br>
	 * 根据GlobalParams Id 更新value
	 * 
	 * @param party
	 * @return
	 */
	@RequestMapping("/doUpdate")
	public ModelAndView doUpdate(GlobalParams party) {
		thirdPartyService.updateThirdParty(party);
		return list();
	}

}
