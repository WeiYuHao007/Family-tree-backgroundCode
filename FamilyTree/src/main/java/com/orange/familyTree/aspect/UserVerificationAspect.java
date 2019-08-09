package com.orange.familyTree.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.orange.familyTree.pojo.util.ResultFactory;

@Aspect
@Component
@Order(1)
public class UserVerificationAspect {

	//匹配切点（除去登入与注册）
	@Pointcut("execution(* com.orange.familyTree.controller.*.*(..))"
			+ " && !execution(* com.orange.familyTree.controller.PublicUserController.*(..))")
	public void accountVerificationAspect() {
		
	}
	
	//登陆验证
	@Around("accountVerificationAspect()")
	@ResponseBody
	public Object verify(ProceedingJoinPoint jp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession(false);
		if (session == null) {
			String message = "身份令牌失效，请重新登陆以获取新的令牌。";
			return ResultFactory.buildAuthenticationResult(message);
		}
		return jp.proceed();
	}
}
