package mvc.mvc2Study.springmvc.itemService.domain.item.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
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
