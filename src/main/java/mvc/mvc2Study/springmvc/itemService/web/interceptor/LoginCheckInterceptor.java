package mvc.mvc2Study.springmvc.itemService.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.itemService.web.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 시작 {}", requestURI);
        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            log.info("미인증 사용자 요청 {}", requestURI);
            response.sendRedirect("/login?redirectUrl=" + requestURI);
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("인증 체크 인터셉터 종료 {}", request.getRequestURI());
    }
}
