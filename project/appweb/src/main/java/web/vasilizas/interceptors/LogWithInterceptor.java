package web.vasilizas.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LogWithInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, @NotNull Object handler, ModelAndView modelAndView) {
        log.info(" postHandle request : method - {}; url - {}", req.getMethod(), ServletUriComponentsBuilder.fromRequest(req).toUriString());
        log.info(" postHandle response status : {} ", resp.getStatus());
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, @NotNull Object handler) {
        log.info(" preHandle  request : method -  {}; url  - {}", req.getMethod(), ServletUriComponentsBuilder.fromRequest(req).toUriString());
        log.info(" preHandle response status : {} ", resp.getStatus());
        return true;
    }
}
