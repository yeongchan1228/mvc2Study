package mvc.mvc2Study.springmvc.fileupload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.fileupload.controller.dto.ItemDto;
import mvc.mvc2Study.springmvc.fileupload.domain.Item;
import mvc.mvc2Study.springmvc.fileupload.domain.UploadFile;
import mvc.mvc2Study.springmvc.fileupload.file.FileStore;
import mvc.mvc2Study.springmvc.fileupload.repository.FileItemRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileItemController {

    private final FileItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemDto itemDto){
        return "fileupload/item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemDto itemDto, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile attachFile = fileStore.storeFile(itemDto.getAttachFile());
        List<UploadFile> uploadFiles = fileStore.storeFiles(itemDto.getImageFiles());

        Item item = new Item(itemDto.getItemName(), attachFile, uploadFiles);
        Item saveItem = itemRepository.itemSave(item);

        redirectAttributes.addAttribute("itemId", item.getId());
        return "redirect:/file/items/{itemId}";
    }

    @GetMapping("/file/items/{id}")
    public String items(@PathVariable Long id, Model model){
        Item findItem = itemRepository.findById(id);
        model.addAttribute("item", findItem);

        return "fileupload/item-view";
    }

    @ResponseBody
    @GetMapping("images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        Item findItem = itemRepository.findById(itemId);
        String storeFileName = findItem.getAttachFile().getStoreFileName();
        String uploadFileName = findItem.getAttachFile().getUploadFileName();

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        String contentDisposition = "attachment; filename=\""+uploadFileName + "\""; // 다운 받기 위한 규약 -> 헤더에 추가

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

}
