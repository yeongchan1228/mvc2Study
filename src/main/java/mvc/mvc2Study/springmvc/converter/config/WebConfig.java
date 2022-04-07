package mvc.mvc2Study.springmvc.converter.config;

import mvc.mvc2Study.springmvc.converter.converter.IntegerToStringConverter;
import mvc.mvc2Study.springmvc.converter.converter.IpPortToStringConverter;
import mvc.mvc2Study.springmvc.converter.converter.StringToIntegerConverter;
import mvc.mvc2Study.springmvc.converter.converter.StringToIpPortConverter;
import mvc.mvc2Study.springmvc.converter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 주석 처리 우선순위 -> Formatter도 String <-> Number 인데 Converter(Integer<->String)를 등록하면 우선 순위에 의해 Formatter가 씹힌다.
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        // Formatter 추가
        registry.addFormatter(new MyNumberFormatter());
    }
}
