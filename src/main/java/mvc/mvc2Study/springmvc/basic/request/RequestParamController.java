package mvc.mvc2Study.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        log.info("v1 username = {}, age = {}", username, age);

        response.getWriter().write("ok!");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("useranme") String memberName, @RequestParam("age") String memberAge){

        log.info("v2 username = {}, age = {}", memberName, memberAge);

        return "ok!";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam String age){

        log.info("v3 username = {}, age = {}", username, age);

        return "ok!";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    // 기본 타입 (String, int...) -> @RequestParam 생략 가능 -> 직관적이지 않아 사용 고려
    public String requestParamV4(String username, int age){

        log.info("v4 username = {}, age = {}", username, age);

        return "ok!";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    // required = true -> 해당 파라미터가 반드시 있어야 한다.
    // default -> required = true
    // required = true -> username = "" -> username이 있다고 보고 실행된다. // null과 ""(빈 문자열)의 차이
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age
    ){

        log.info("required username = {}, age = {}", username, age);

        return "ok!";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    // null, "" 둘 다 처리
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") Integer age
    ){

        log.info("default username = {}, age = {}", username, age);

        return "ok!";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    // null, "" 둘 다 처리
    // Key-Value -> Map
    // Key-MultiValue -> MultivalueMap
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap
            ){

        log.info("map username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));

        return "ok!";
    }
}
