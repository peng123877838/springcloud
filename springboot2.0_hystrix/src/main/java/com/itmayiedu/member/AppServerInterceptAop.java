package com.itmayiedu.member;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 * @author pengyejian
 */
@Aspect
@Component
public class AppServerInterceptAop {
	
	

	/**
	 * 跟踪执行完成时间
	 */

	/**
	 * /** 预约挂号服务拦截
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 */

	@Around("(execution(* com9.itmayiedu..*Controller.*(..)))")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object controll(ProceedingJoinPoint proceedingJoinPoint) {
		System.out.println("####################拦截器处理请求#################");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		HttpServletResponse response = attributes.getResponse();
		String method = request.getMethod();
		if (method == null) {
			method = "";
		}
		long startTime = System.currentTimeMillis();
		
		
		try {
			Object obj = proceedingJoinPoint.proceed();// 调用执行目标方法
			String useTime = (System.currentTimeMillis() - startTime) + "毫秒";
			// 处理完请求，返回内容
			// System.out.println("响应时间["+useTime+"]响应数据 " + obj);
			System.out.println("响应时间[" + useTime + "]");
			return obj;
		} catch (Throwable e) {
			// logger.error("Controller处理异常", e);
			System.out.println("记录监控错误码");
			
			e.printStackTrace();
			return "error......";
		}
		
	}

}
