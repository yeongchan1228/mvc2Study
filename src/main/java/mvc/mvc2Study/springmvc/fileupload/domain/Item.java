package mvc.mvc2Study.springmvc.fileupload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private UploadFile attachFile; // 첨부 파일
    private List<UploadFile> imageFiles; // 이미지들

    public Item(String itemName, UploadFile attachFile, List<UploadFile> imageFiles) {
        this.itemName = itemName;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }
}
