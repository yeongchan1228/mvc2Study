package mvc.mvc2Study.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.basic.HelloData;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String bodyMessage = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("bodyMessage = {}", bodyMessage);

        HelloData helloData = objectMapper.readValue(bodyMessage, HelloData.class);
        log.info("helloData = {}", helloData);

        response.getWriter().write("ok!");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String bodyMessage) throws IOException {
        log.info("bodyMessage = {}", bodyMessage);

        HelloData helloData = objectMapper.readValue(bodyMessage, HelloData.class);
        log.info("helloData = {}", helloData);

        return "ok!";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("helloData = {}", helloData);

        return "ok!";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData helloData = httpEntity.getBody();

        log.info("helloData = {}", helloData);

        return "ok!";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("helloData = {}", helloData);

        return helloData;
    }
}
