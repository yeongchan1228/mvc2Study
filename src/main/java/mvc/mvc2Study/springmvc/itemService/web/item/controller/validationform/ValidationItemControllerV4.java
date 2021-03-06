package mvc.mvc2Study.springmvc.itemService.web.item.controller.validationform;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.itemService.web.item.controller.validationform.dto.ItemSaveDto;
import mvc.mvc2Study.springmvc.itemService.web.item.controller.validationform.dto.ItemUpdateDto;
import mvc.mvc2Study.springmvc.itemService.domain.item.entity.Item;
import mvc.mvc2Study.springmvc.itemService.domain.item.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation4/items")
@RequiredArgsConstructor
public class ValidationItemControllerV4 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation4/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation4/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("itemSaveDto", new ItemSaveDto());
        return "validation4/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute ItemSaveDto itemSaveDto, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {

        int sum = itemSaveDto.getPrice() * itemSaveDto.getQuantity();
        if(sum <= 10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, sum}, null);
        }


        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("bindingResult = {}", bindingResult);
            // bindingResult는 알아서 Model에 담긴다.
            return "validation4/addForm";
        }

        // 성공 시
        Item item = new Item(itemSaveDto.getItemName(), itemSaveDto.getPrice(), itemSaveDto.getQuantity());
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation4/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation4/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateDto itemUpdateDto, BindingResult bindingResult) {

        if(itemUpdateDto.getQuantity() != null) {
            int sum = itemUpdateDto.getPrice() * itemUpdateDto.getQuantity();
            if (sum <= 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, sum}, null);
            }
        }

        if(bindingResult.hasErrors()){
            log.info("edit error = {}", bindingResult);
            return "validation4/editForm";
        }

        Item item = new Item(itemUpdateDto.getItemName(), itemUpdateDto.getPrice(), itemUpdateDto.getQuantity());
        itemRepository.update(itemId, item);
        return "redirect:/validation4/items/{itemId}";
    }


}
