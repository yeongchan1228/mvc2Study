package mvc.mvc2Study.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest(){
        String name ="Spring";

        // log level
        log.debug("debug log = {}", name); // 개발 서버에서 볼 때
        log.trace("trace log = {}", name); // 운영 서버에서

        // 실제 로그 출력 해당 상태로 표시됨
        log.info("info log = {}", name);
        log.warn("warn log = {}", name); // 경고
        log.error("error log = {}", name); // 에러

        // sout -> 모든 레벨에서도 무조건 출력된다.

        return "ok";
    }
}
