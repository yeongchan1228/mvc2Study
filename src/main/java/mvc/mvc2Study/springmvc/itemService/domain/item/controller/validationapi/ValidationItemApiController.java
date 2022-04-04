package mvc.mvc2Study.springmvc.itemService.domain.item.controller.validationapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.itemService.domain.item.controller.validationform.dto.ItemSaveDto;
import mvc.mvc2Study.springmvc.itemService.domain.item.entity.Item;
import mvc.mvc2Study.springmvc.itemService.domain.item.repository.ItemRepository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
@RequiredArgsConstructor
public class ValidationItemApiController {

    private final ItemRepository itemRepository;

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveDto itemSaveDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("검증 오류 발생 errors = {}", bindingResult);
            return bindingResult.getAllErrors();
        }

        Item item = new Item(itemSaveDto.getItemName(), itemSaveDto.getPrice(), itemSaveDto.getQuantity());
        Item saveItem = itemRepository.save(item);
        return saveItem;
    }
}
