package com.example.news_portal.aop;

import com.example.news_portal.exception.AccessVerifiableException;
import com.example.news_portal.service.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.text.MessageFormat;
import java.util.Map;

@Aspect
@Component
public class NewsAccessVerifiableAspect {
    @Autowired
    NewsService newsService;

    @Before("@annotation(NewsAccessVerifiable)")
    public void accessBefore(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        var pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long userIdThatInitializedRequest = Long.parseLong(pathVariables.get("userId"));
        Long newsId = Long.parseLong(pathVariables.get("newsId"));
        Long userIdThatCreatedNews = newsService.findById(newsId).getUser().getId();
        if (!userIdThatInitializedRequest.equals(userIdThatCreatedNews)) {
            throw new AccessVerifiableException(MessageFormat.format("User with ID {0} does not have access to this operation", userIdThatInitializedRequest));
        }
    }
}
