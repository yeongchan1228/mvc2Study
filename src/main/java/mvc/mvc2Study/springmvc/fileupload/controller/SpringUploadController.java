package mvc.mvc2Study.springmvc.fileupload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {

    @Value("${file.dir}")
    private String filePath;

    @GetMapping("/upload")
    public String newFile(){
        return "fileupload/upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam MultipartFile file,
                           HttpServletRequest request) throws IOException {
        log.info("request = {}", request);
        log.info("itemName = {}", itemName);
        log.info("file = {}", file);

        if(!file.isEmpty()){
            String path = filePath + itemName + ".png";

            log.info("파일 저장 = {}", path);
            file.transferTo(new File(path));
        }

        return "fileupload/upload-form";
    }
}
