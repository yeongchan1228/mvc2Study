package mvc.mvc2Study.springmvc.basic.response;

import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.basic.HelloData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok!");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok!", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok!";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData("hello", 20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK) // 응답 상태 반환, 상태를 동적으로 변경하고 싶으면 ResponseEntity 사용
    @GetMapping("/response-body-json-v1")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData("hello", 20);

        return helloData;
    }

}
