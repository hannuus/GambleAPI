package com.hannuus.gamble.web.validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 校验规则容器
 * 
 * @author cuesky
 * @date 2015年9月10日 下午9:37:17
 */
@Component
public class RulesContainer {

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 根据params提供的ruleId返回所有的校验规则
	 * 
	 * @param params
	 * @return
	 */
	public List<Validator> getRules(ValidateParams params) {
		List<Validator> list = new ArrayList<Validator>();
		WebApplicationContext context = ContextLoader
				.getCurrentWebApplicationContext();
		String packageName = getRulesPackageName(params);
		Map<String, Validator> map = context.getBeansOfType(Validator.class);
		Collection<Validator> beans = map.values();
		for (Validator validator : beans) {
			// 只存入指定包名下的Validator Bean
			boolean isNameValid = validator.getClass().getPackage().getName()
					.startsWith(packageName);
			// 忽略Validator.order为负数的Validator
			boolean isOrderValid = validator.getOrder() >= 0;
			if (isNameValid && isOrderValid) {
				list.add(validator);
			}
		}
		// 根据Validator.order正向排序
		Collections.sort(list);
		loggerTrace(list);
		return list;
	}

	private void loggerTrace(List<Validator> list) {
		logger.debug("Prepared to execute the validate rules:");
		for (Validator validator : list) {
			logger.debug("[" + validator.getOrder() + "]-"
					+ validator.getClass().getSimpleName());
		}
	}

	/**
	 * 根据params提供的ruleId返回指定包名
	 * 
	 * @param params
	 * @return
	 */
	private String getRulesPackageName(ValidateParams params) {
		String ruleId = params.getRuleId();
		String packageName = Validator.NS + "." + ruleId;
		return packageName;
	}

}
