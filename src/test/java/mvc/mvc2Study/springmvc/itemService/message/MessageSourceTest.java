package mvc.mvc2Study.springmvc.itemService.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired MessageSource messageSource;

    @Test
    public void messageTest() throws Exception {
        // given
        // 1. 기본
        String result = messageSource.getMessage("hello", null, Locale.KOREA);
        assertThat(result).isEqualTo("안녕");

        // 2. 예외 발생
        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);

        // 3. 기본 메시지
        String no_code = messageSource.getMessage("no_code", null, "기본 메시지 입니다.", null);
        assertThat(no_code).isEqualTo("기본 메시지 입니다.");

        // 4. 매개변수
        String message = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");

        // 5. 국제화
        String hello = messageSource.getMessage("hello", null, Locale.ENGLISH);
        assertThat(hello).isEqualTo("hello");
    }
}
