package mvc.mvc2Study.springmvc.itemService.web.item.controller.validation2;

import mvc.mvc2Study.springmvc.itemService.domain.item.entity.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        // 파라미터로 넘어오는 class가 Item.class에 지원이 되는지
        // Item == clazz
        // Item == subItem
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName", "required", null);
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity() > 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        // 복합 룰 검증
        if(item.getQuantity() != null && item.getPrice() != null){
            int result = item.getQuantity() * item.getPrice();
            if(result < 10000){
                errors.reject("totalPrinceMin", new Object[]{10000, result}, null);
            }
        }
    }
}
