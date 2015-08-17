package com.hannuus.gamble.web.service.impl;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 * @author aelns
 *
 */
@Aspect
@Component
public class LogAspectJ {
	
	public Logger logger = Logger.getLogger(LogAspectJ.class);

	/**
	 * 使用@Pointcut注解定义一个切入点，切入点的名字为anyMethod()， 切入点正则表达式execution(* com.hannuus.gamble.web.action.*(..))
	 * 的意思是拦截com.hannuus.gamble.web.action.*类中的所有方法， 不论方法参数有无，也不管返回结果为何类型。
	 * */
	@Pointcut("execution(* com.hannuus.gamble.web.action.*Action.*(..))")
	private void anyMethod() {
		
	}
	
	/**
	 * 定义前置通知
	 * @param joinpoint
	 */
	@Before("anyMethod()")
	public void myBeforeAdvice(JoinPoint joinpoint) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
				+ "类的" + joinpoint.getSignature().getName();
		logger.info("前置通知:" + classAndMethod + "方法开始执行！");
	}

	/**
	 * 定义后置通知
	 * @param joinpoint
	 */
	@AfterReturning("anyMethod()")
	public void myAfterReturningAdvice(JoinPoint joinpoint) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
				+ "类的" + joinpoint.getSignature().getName();
		logger.info("后置通知:" + classAndMethod + "方法执行正常结束！");
	}
	
	/**
	 * 定义异常通知
	 * @param joinpoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "anyMethod()", throwing = "e")
	public void myAfterThrowingAdvice(JoinPoint joinpoint, Exception e) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
				+ "类的" + joinpoint.getSignature().getName();
		logger.info("异常通知:" + classAndMethod + " 方法抛出异常：" + e.getMessage());
	}

	/**
	 * 定义最终通知
	 * @param joinpoint
	 */
	@After("anyMethod()")
	public void myAfterAdvice(JoinPoint joinpoint) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
				+ "类的" + joinpoint.getSignature().getName();
		logger.info("最终通知:" + classAndMethod + "方法执行结束！");
	}
	
	/**
	 * 定义环绕通知
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("anyMethod()")
	public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		long begintime = System.currentTimeMillis();// 记下开始时间
		// 传递给连接点对象进行接力处理
		Object result = pjp.proceed();
		long endtime = System.currentTimeMillis();// 记下结束时间
		String classAndMethod = pjp.getTarget().getClass().getName() + "类的"
				+ pjp.getSignature().getName();
		logger.info("环绕通知:" + classAndMethod + "方法执行结束,  耗时"
				+ (endtime - begintime) + "毫秒！");
		return result;
	}
}