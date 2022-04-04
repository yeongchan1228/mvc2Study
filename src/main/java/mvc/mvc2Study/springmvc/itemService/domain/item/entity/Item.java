package mvc.mvc2Study.springmvc.itemService.domain.item.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000",
//        message = "총합이 10000 넘게 입력해 주세요.") -> 사용하지 않는다. 기능이 너무 약함. 이 부분은 자바 코드로 작성.
public class Item {

    private Long id;

    @NotBlank(message = "공백X") // 빈칸 + 공백
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000) // hibernate Validator가 동작할 때만 적용됨
    private Integer price;

    @NotNull
    @Max(9999)
    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
