package com.example.news_portal.aop;

import com.example.news_portal.service.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Aspect
@Component
@Slf4j
public class NewsAccessVerifiableAspect { // PUT, DELETE /api/comment/{newsId}/{userId}
    @Autowired
    NewsService newsService;

    @Around("@annotation(UserVerification)")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Around method: {} is called", proceedingJoinPoint.getSignature().getName());

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        var pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long userIdThatInitializedRequest = Long.parseLong(pathVariables.get("userId"));
        Long newsId = Long.parseLong(pathVariables.get("newsId"));
        Long userIdThatCreatedNews = newsService.findById(newsId).getUser().getId();
        if (userIdThatInitializedRequest.equals(userIdThatCreatedNews)) {
            return proceedingJoinPoint.proceed();
        } else {
            log.info("Editing and deleting a news is allowed only to the user who created it");
            return null;
        }
    }
}
