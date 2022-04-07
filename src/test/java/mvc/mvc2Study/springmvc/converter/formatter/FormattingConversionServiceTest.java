package mvc.mvc2Study.springmvc.converter.formatter;

import mvc.mvc2Study.springmvc.converter.converter.IpPortToStringConverter;
import mvc.mvc2Study.springmvc.converter.converter.StringToIpPortConverter;
import mvc.mvc2Study.springmvc.converter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {

    @Test
    public void formattionConversinService() throws Exception {

        DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService();

        // 컨버터 등록
        defaultFormattingConversionService.addConverter(new IpPortToStringConverter());
        defaultFormattingConversionService.addConverter(new StringToIpPortConverter());

        // 포맷터 등록 (컨버터 + 포맷터 동시 적용)
        defaultFormattingConversionService.addFormatter(new MyNumberFormatter());


        // convert()만 사용해도 Convert, Formatter 적용

        // 컨버터 사용
        IpPort result = defaultFormattingConversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));

        // 포맷터 사용
        String convert = defaultFormattingConversionService.convert(1000, String.class);
        Long convert2 = defaultFormattingConversionService.convert("1,000", Long.class);
        assertThat(convert).isEqualTo("1,000");
        assertThat(convert2).isEqualTo(1000L);

    }
}
