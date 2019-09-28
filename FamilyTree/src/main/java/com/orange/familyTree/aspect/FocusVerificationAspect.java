package com.orange.familyTree.aspect;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.HandlerMapping;

import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.GenealogyService;

@Aspect
@Component
@Order(3)
public class FocusVerificationAspect {
	
	@Autowired
	private GenealogyService genealogyService;

	@Pointcut("execution(* com.orange.familyTree.controller.ProtectedPersonController.*(..))" +
			"|| execution(* com.orange.familyTree.controller.ProtectedGenealogyController.*(..))")
	public void personVerificationPointcut() {
	}

	// 权限验证（验证关注权限）
	@Around("personVerificationPointcut()")
	public Object focusVerification(ProceedingJoinPoint jp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 获取用户Id
		HttpSession session = request.getSession(false);
		Long userId = (Long)session.getAttribute("SESSION_USERID");
		// 从请求路径中获取请求图谱名称
		Map<String, Object> pathVariables = (Map<String, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String genealogyName = (String) pathVariables.get("tree-name");
		List<Long> followersList = genealogyService.findGenealogyFollowersIdByName(genealogyName);
		for(Long follower: followersList) {
			if(follower.longValue() == userId.longValue()) {
				return jp.proceed();
			}
		}
		// 未通过权限验证
		return ResultFactory.buildAuthenticationResult("您无查看该图谱的权限。");
	}
}
