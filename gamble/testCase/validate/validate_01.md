#说明
基于Spring和JSR303或JSR349的验证框架存在如下弊端：

- 与JavaBean耦合度太高

- 验证逻辑比较分散，不便于集中维护

所以单独封装此简易版

#组件介绍
所有类库都位于com.hannuus.gamble.web.validate下

- ValidateEngine 验证入口类  
	所有需要使用业务规则进行校验的Controller都可以注入该类实例  
	详见BaseAction.validate()  
	该类校验方式为短路型校验，即只要有一条规则不通过，则中断校验流程并返回校验结果
	
- RulesContainer 业务规则容器

- Validator 所有校验规则的基类  
	该类通过getOrder()来维持多个校验规则之间的执行顺序(按照从小到大的顺序执行每条校验规则)  
	order无须从0开始，也无须保持连续，但是多个规则之间的order不可重复，并且负数将被忽略  
	该类的扩展类可以实现所有静态校验，也可以注入Mapper进行基于DB查询的动态校验  
	如使用Mapper则所有的映射文件须集中放置在com.hannuus.gamble.web.validate.mapper中

- ValidateParams 验证所需参数的封装类  
	ruleId：用来标识某个业务场景所使用的所有校验规则所处的命名空间  
	如ruleId为abc，则相关的校验规则实现类必须集中放置在com.hannuus.gamble.web.validate.validator.abc下  
	target：被校验的对象，可为任意类型

- ValidateResult 验证结果封装类

#测试用例
见TestAction.testValidate()  
使用任何用户登录后，访问如下URL即可看到后台输出的校验执行情况  
http://localhost:8080/gamble/api/test/validate