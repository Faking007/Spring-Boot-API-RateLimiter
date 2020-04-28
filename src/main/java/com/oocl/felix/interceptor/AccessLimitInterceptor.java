package com.oocl.felix.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.oocl.felix.annotation.AccessLimit;
import java.lang.reflect.Method;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AccessLimitInterceptor implements HandlerInterceptor {

    private RateLimiter rateLimiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (!method.isAnnotationPresent(AccessLimit.class)) {
                return true;
            }
            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
            if (Objects.isNull(accessLimit)) {
                return true;
            }
            if (Objects.isNull(rateLimiter)) {
                rateLimiter = RateLimiter.create(accessLimit.queryPerSecond());
            }
            rateLimiter.acquire();
        }
        return true;
    }
}
