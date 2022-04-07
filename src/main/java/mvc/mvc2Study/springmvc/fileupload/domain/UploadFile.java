package mvc.mvc2Study.springmvc.fileupload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {

    private String UploadFileName;
    private String StoreFileName; // 같은 이름의 파일 이름을 사용했을 경우 안 덮어쓰이게 UUID로 사용
}
