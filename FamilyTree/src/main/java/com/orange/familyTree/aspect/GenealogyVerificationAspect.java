package com.orange.familyTree.aspect;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.GenealogyService;

@Aspect
@Component
@Order(2)
public class GenealogyVerificationAspect {
	@Autowired
	private GenealogyService genealogyService;

	@Pointcut("execution(* com.orange.familyTree.controller.ProtectedAccountController.*(..))")
	public void genealogyVerificationPointcut() {
		
	}
	
	//族谱权限验证
	@Around("genealogyVerificationPointcut()")
	public Object genealogyVerification(ProceedingJoinPoint jp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession(false);
		String genealogyName = request.getParameter("genealogyName");
		Integer telephoneNumber = (Integer)session.getAttribute("SESSION_TELEPHONENUMBER");
		List<Integer> followersList = genealogyService.findFollowersByGenealogy(genealogyName);
		for(Integer follower: followersList) {
			if(follower.intValue() == telephoneNumber.intValue()) {
				return jp.proceed();
			}
		}
		String message = "您未关注该图谱。"; 
		Result result = ResultFactory.buildAuthenticationResult(message);
		return result;
	}
}
