package com.system.gestionautomobile.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final ObjectMapper objectMapper;

    public LoggingAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Before("execution(* com.system.gestionautomobile.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {} ,Class Name:{}", joinPoint.getSignature().getName(),joinPoint.getArgs(), joinPoint.getSignature().getDeclaringTypeName());
    }


    @Pointcut("@annotation(LogActivity)")
    public void logActivityPointcut() {}

    @AfterReturning(pointcut = "logActivityPointcut()", returning = "returnValue")
    public void logActivity( JoinPoint joinPoint, Object returnValue) {
        // Extract user information from JWT claims
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String method = request.getMethod();
        String endpoint = request.getRequestURI();
        String requestBody = null;

        // Put user information into MDC
        MDC.put("username", username);
        MDC.put("method", method);
        MDC.put("endpoint", endpoint);
        MDC.put("requestBody", requestBody);

        // Convert request body to JSON string
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            requestBody = objectMapper.writeValueAsString(joinPoint.getArgs());
        } catch (Exception e) {
            logger.error("Error converting request body to JSON: {}", e.getMessage());
        }

        //logger.info("User {} accessed the endpoint", username);
        logger.info("User {} accessed {} {} - Request Body: \n{}",
                username, method, endpoint, requestBody);

        // Clear MDC
        MDC.clear();
    }


}


