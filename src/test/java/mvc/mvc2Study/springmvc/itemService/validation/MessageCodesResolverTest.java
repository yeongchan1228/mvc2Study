package mvc.mvc2Study.springmvc.itemService.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    public void messageCodesResolverObjectError() throws Exception {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");

        assertThat(messageCodes).containsExactly("required.item", "required");
    }
    
    @Test
    public void messageCodesResolverFieldError() throws Exception {
        String[] strings = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);

        assertThat(strings).containsExactly("required.item.itemName", "required.itemName", "required.java.lang.String", "required");
    }
}
