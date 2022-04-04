package mvc.mvc2Study.springmvc.itemService.validation;

import mvc.mvc2Study.springmvc.itemService.domain.item.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@SpringBootTest
public class BeanValidationTest {
    
    @Test
    public void validation() throws Exception {
        // given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // when
        Item item = new Item();
        item.setItemName(" "); // 공백
        item.setPrice(0);
        item.setQuantity(10000);

        // then
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        for (ConstraintViolation<Item> violation : violations) {
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());
        }
    }
}
