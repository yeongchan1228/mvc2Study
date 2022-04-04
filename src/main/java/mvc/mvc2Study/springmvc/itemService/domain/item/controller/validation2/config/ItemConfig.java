package mvc.mvc2Study.springmvc.itemService.domain.item.controller.validation2.config;

import mvc.mvc2Study.springmvc.itemService.domain.item.controller.validation2.ItemValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ItemConfig implements WebMvcConfigurer {

//    @Override
//    public Validator getValidator() {
//        return new ItemValidator();
//    }
}
