package mvc.mvc2Study.springmvc.itemService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.itemService.domain.Item;
import mvc.mvc2Study.springmvc.itemService.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class basicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> all = itemRepository.findAll();
        model.addAttribute("items", all);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
//    public String saveV1(
//            @RequestParam("itemName") String itemName,
//            @RequestParam("price") int price,
//            @RequestParam("quantity") Integer quantity,
//            Model model
//    ){
//        Item item = new Item(itemName, price, quantity);
//        model.addAttribute("item", item);
//        return "basic/item";
//    }

    @PostMapping("/add")
    // Item 객체를 생성한 후 model에 item으로 담아준다.
    // @ModelAttribute name 속성을 지정 안하면 객체명의 앞 글자를 소문자로 바꾼 후 model에 담아준다. -> 즉 name 속성 생략 가능
    public String saveV2(@ModelAttribute/*("item")*/ Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
//        model.addAttribute("Item", item);

        // redirectAttributes에 저장한 것 중 사용 안한 것은 쿼리 파라미터 형식으로 넘어간다.
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(
            @PathVariable Long itemId,
            Model model
    ){
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String update(
        @ModelAttribute Item item,
        @PathVariable Long itemId
    ){
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
