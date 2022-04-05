package mvc.mvc2Study.springmvc.itemService.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>(); // 동시 접근 고려

    /**
     * SessionId 생성 (임의의 추정 불가능한 값)
     * 세션 저장소에 SesstionId와 보관할 값 저장
     * SessionId로 응답 쿠키를 생성해서 전송
     */
    public void createSession(Object value, HttpServletResponse response){
        // SessionId 생성 및 저장
        String sessionId = UUID.randomUUID().toString();

        sessionStore.put(sessionId, value);

        // 쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);

        // 쿠키 전송
        response.addCookie(mySessionCookie);
    }

    /**
     * 세션 조회
     */
    public Object getSession(HttpServletRequest request){
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);

        if(cookie != null) return sessionStore.get(cookie.getValue());

        return null;
    }

    /**
     * 세션 만료
     */
    public void expire(HttpServletRequest request){
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        if(cookie != null)
            sessionStore.remove(cookie.getValue());
    }

    // 쿠키 찾기
    public Cookie findCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;

        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME)).findAny().orElse(null);

    }

}
