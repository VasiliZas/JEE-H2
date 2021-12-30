package web.vasilizas.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

@Slf4j
@Component
public class LogWithInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp, @NotNull Object handler, ModelAndView modelAndView) throws UnsupportedEncodingException {
        logUrl(req, resp);
        logHeaders(req);
        //logBody(req);
    }

    private void logUrl(HttpServletRequest req, HttpServletResponse resp) {
        log.info(" postHandle request : method - {}; url - {}", req.getMethod(), ServletUriComponentsBuilder.fromRequest(req).toUriString());
        log.info(" postHandle response status : {} ", resp.getStatus());
    }

    private void logHeaders(HttpServletRequest req) {
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            log.info(" +++++++++++++++++++++");
            log.info(" logHeaders - [{}]: {}", name, req.getHeader(name));
        }
    }


//    private void logBody(HttpServletRequest req) throws UnsupportedEncodingException {
//        RealCachingRequestWrapper reqWrapper = (RealCachingRequestWrapper) req;
//        String body = new String(reqWrapper.getContentAsByteArray(), req.getCharacterEncoding());
//        log.info(" ======================");
//        log.debug("Request Body:  {}", body);
//    }

    @Override
    public boolean preHandle(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp, @NotNull Object handler) {
        logUrl(req, resp);
        return true;
    }
}
