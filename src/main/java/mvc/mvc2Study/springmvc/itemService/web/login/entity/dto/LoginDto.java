package mvc.mvc2Study.springmvc.itemService.web.login.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {
    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
