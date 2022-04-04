package mvc.mvc2Study.springmvc.itemService.domain.item.controller.validationform.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemSaveDto {

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private int price;

    @NotNull
    @Max(9999)
    private int quantity;
}
