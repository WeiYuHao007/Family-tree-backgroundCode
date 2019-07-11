package com.orange.familyTree.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.orange.familyTree.exceptions.AccountException;

@Aspect
@Component
public class AccountAspect {

	//匹配切点（除去登入与注册）
	@Pointcut("execution(* com.orange.familyTree.controller.*.*(..))"
			+ " && !execution(* com.orange.familyTree.controller.IndexController.*(..))")
	public void accountAspect() {
		
	}
	
	//环绕通知：实现权限管理
	@Around("accountAspect()")
	@ResponseBody
	public Object around(ProceedingJoinPoint jp) throws Throwable, AccountException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return jp.proceed();
	}
}
