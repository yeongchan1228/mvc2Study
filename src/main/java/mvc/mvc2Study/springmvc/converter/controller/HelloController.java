package mvc.mvc2Study.springmvc.converter.controller;

import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.converter.type.IpPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request, HttpServletResponse response){
        String data = request.getParameter("data"); // 문자 타입 조회
        int dataInteger = Integer.parseInt(data); // 문자 -> 숫자
        log.info("data = {}", dataInteger);
        return "ok!";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data){
        log.info("data = {}", data.getClass());
        return "ok!";
    }

    @GetMapping("/ip-port")
    public String helloV2(@RequestParam IpPort data){
        log.info("data = {}", data);
        return "ok!";
    }
}
