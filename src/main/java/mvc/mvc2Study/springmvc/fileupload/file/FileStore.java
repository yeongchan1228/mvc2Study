package mvc.mvc2Study.springmvc.fileupload.file;

import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.fileupload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir + filename;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            if(!file.isEmpty()){
                uploadFiles.add(storeFile(file));
            }
        }
        return uploadFiles;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
           return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        // image.png -> png 추출
        String ext = extractedExt(originalFilename);

        // 서버에 저장하는 파일명
        String storeFileName = getStoreFileName(ext);
        log.info("storeFileName = {}", storeFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFilename, storeFileName);
    }

    private String getStoreFileName(String ext) {
        String uuid = UUID.randomUUID().toString();

        String storeFileName = uuid + "." + ext;
        return storeFileName;
    }

    private String extractedExt(String originalFilename) {
        int index = originalFilename.lastIndexOf(".");
        String form = originalFilename.substring(index + 1);
        return form;
    }

}
