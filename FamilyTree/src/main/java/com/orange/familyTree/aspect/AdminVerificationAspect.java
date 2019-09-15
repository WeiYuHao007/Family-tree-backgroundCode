package com.orange.familyTree.aspect;

import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.GenealogyService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@Order(2)
public class AdminVerificationAspect {
    @Autowired
    private GenealogyService genealogyService;

    @Pointcut("execution(* com.orange.familyTree.controller.AdminPersonController.*(..))" +
            "|| execution(* com.orange.familyTree.controller.AdminGenealogyController.*(..))")
    public void genealogyVerificationPointcut() {
    }

    // 权限验证（验证管理权限）
    @Around("genealogyVerificationPointcut()")
    public Object adminVerification(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取用户Id
        HttpSession session = request.getSession(false);
        Long userId = (Long)session.getAttribute("SESSION_USERID");
        // 从请求路径中获取请求图谱名称
        Map<String, Object> pathVariables = (Map<String, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String genealogyName = (String) pathVariables.get("tree-name");
        List<Long> adminsList = genealogyService.findGenealogyAdminsByName(genealogyName);
        for(Long admin: adminsList) {
            if(admin.longValue() == userId.longValue()) {
                return jp.proceed();
            }
        }
        // 未通过权限验证
        return ResultFactory.buildAuthenticationResult("您无管理该图谱的权限。");
    }
}
