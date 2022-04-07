package mvc.mvc2Study.springmvc.exception.api.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.exception.api.exception.UserException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

     private final ObjectMapper om = new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if(ex instanceof UserException){
                log.info("UserException resolver to 400");
                String accept = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if(accept.equals("application/json")){
                    Map<String, Object> errorResult = new HashMap<>();
                    errorResult.put("ex", ex.getClass());
                    errorResult.put("message", ex.getMessage());

                    // 응답 헤더
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    String result = om.writeValueAsString(errorResult);
                    // 응답 바디
                    response.getWriter().write(result);

                    return new ModelAndView(); // 정상 흐름으로 변환
                }else {
                    return new ModelAndView("error/500");
                }
            }
        } catch (IOException e) {
            log.error("userResolver error", ex);
        }

        return null;
    }
}
