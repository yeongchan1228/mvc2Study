package mvc.mvc2Study.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    @RequestMapping(value = "/hello-basic")
    public String helloBasic(){
        log.info("hello-basic");

        return "ok!";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String GetMappingV1(){
        log.info("mapping-get-v1");

        return "ok!";
    }


    @GetMapping("/mapping-get-v2")
    public String GetMappingV2(){
        log.info("mapping-get-v2");

        return "ok!";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(
//            @PathVariable(name = "userId") String userId
            @PathVariable String userId
    ){
        log.info("userID = {}", userId);

        return "ok!";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPathV2(@PathVariable String userId, @PathVariable String orderId){
        log.info("userId = {}, orderId = {}", userId, orderId);

        return "ok!";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug") // 파라미터 정보 지정 ?mode-debug가 있어야 호출됨
    public String mappingParam(){
        log.info("mapping-param");

        return "ok!";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug") // header에 mode = debug가 있어야 한다.
    public String mappingHeader(){
        log.info("mapping-header");

        return "ok!";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }
    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
