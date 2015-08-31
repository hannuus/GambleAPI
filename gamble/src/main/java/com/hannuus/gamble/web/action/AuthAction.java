package com.hannuus.gamble.web.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hannuus.gamble.comm.R;
import com.hannuus.gamble.domain.page.PageDTO;
import com.hannuus.gamble.domain.page.PageParams;
import com.hannuus.gamble.model.User;
import com.hannuus.gamble.web.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthAction extends BaseAction {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	AuthService authService;

	@RequestMapping("/listUsers")
	public ModelAndView listUsers(ModelMap map, int pageNum, int pageSize) {
		PageParams params = new PageParams(pageNum, pageSize);
		logger.debug(params);
		PageDTO<User> page = authService.findUsersPage(params);
		logger.debug(page.getTotal());
		return new ModelAndView("/auth/user_list", R.page.attr_key, page);
	}

}
