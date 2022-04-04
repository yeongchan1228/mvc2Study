package mvc.mvc2Study.springmvc.itemService.domain.item.controller.validation2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.itemService.domain.item.entity.Item;
import mvc.mvc2Study.springmvc.itemService.domain.item.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

//     해당 컨트롤러에 validator를 등록한다.
    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(itemValidator);
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation2/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수 입니다."));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            bindingResult.addError(new FieldError("item", "price", "가격은 1000원에서 1000000원까지 허용합니다"));
        }
        if(item.getQuantity() == null || item.getQuantity() > 9999) {
            bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9,999 까지 허용합니다."));

        }

        // 복합 룰 검증
       if(item.getQuantity() != null && item.getPrice() != null){
           int result = item.getQuantity() * item.getPrice();
           if(result < 10000){
               bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이여야 합니다. 현재 값 = " + result));
           }
       }

       // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("bindingResult = {}", bindingResult);
            // bindingResult는 알아서 Model에 담긴다.
            return "validation2/addForm";
        }

        // 성공 시
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false,null,  null , "상품 이름은 필수 입니다."));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1000원에서 1000000원까지 허용합니다"));
        }
        if(item.getQuantity() == null || item.getQuantity() > 9999) {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, null, null, "수량은 최대 9,999 까지 허용합니다."));

        }

        // 복합 룰 검증
        if(item.getQuantity() != null && item.getPrice() != null){
            int result = item.getQuantity() * item.getPrice();
            if(result < 10000){
                bindingResult.addError(new ObjectError("item", null, null, "가격 * 수량의 합은 10,000원 이상이여야 합니다. 현재 값 = " + result));
            }
        }

        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("bindingResult = {}", bindingResult);
            // bindingResult는 알아서 Model에 담긴다.
            return "validation2/addForm";
        }

        // 성공 시
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation2/items/{itemId}";
    }

    /**
     * 오류 메시지 적용
     */
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false,new String[]{"required.item.itemName"},  null , null));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 10000}, null));
        }
        if(item.getQuantity() == null || item.getQuantity() > 9999) {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999}, null));

        }

        // 복합 룰 검증
        if(item.getQuantity() != null && item.getPrice() != null){
            int result = item.getQuantity() * item.getPrice();
            if(result < 10000){
                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, result}, null));
            }
        }

        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("bindingResult = {}", bindingResult);
            // bindingResult는 알아서 Model에 담긴다.
            return "validation2/addForm";
        }

        // 성공 시
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation2/items/{itemId}";
    }

    /**
     * rejectValue(), reject() 사용
     */
//    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {

        if(bindingResult.hasErrors()){ // 타입 오류 시 스프링이 error를 넣어주기 때문에 앞에서 호출 -> 타입 에러 시 그 부분만 오류 메시지 출력
            log.info("bindingResult = {}", bindingResult);
            // bindingResult는 알아서 Model에 담긴다.
            return "validation2/addForm";
        }

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.rejectValue("itemName", "required", null);
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity() > 9999) {
            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        // 복합 룰 검증
        if(item.getQuantity() != null && item.getPrice() != null){
            int result = item.getQuantity() * item.getPrice();
            if(result < 10000){
                bindingResult.reject("totalPrinceMin", new Object[]{10000, result}, null);
            }
        }

        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("bindingResult = {}", bindingResult);
            // bindingResult는 알아서 Model에 담긴다.
            return "validation2/addForm";
        }

        // 성공 시
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {

        itemValidator.validate(item, bindingResult);

        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("bindingResult = {}", bindingResult);
            // bindingResult는 알아서 Model에 담긴다.
            return "validation2/addForm";
        }

        // 성공 시
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation2/items/{itemId}";
    }

    // @Validated -> 해당 객체에 대해서 검증기가 수행된다. 검증된 결과가 bindingResult에 담긴다. -> 검증기를 실행해라.
    // 여러 개일 경우 만들어 놓은 supports() 가 동작해서 적용할 수 있는 validator를 찾는다.
    @PostMapping("/add")
    public String addItemV6(@Validated @ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {


        // 검증 실패 시 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("bindingResult = {}", bindingResult);
            // bindingResult는 알아서 Model에 담긴다.
            return "validation2/addForm";
        }

        // 성공 시
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation2/items/{itemId}";
    }


}
