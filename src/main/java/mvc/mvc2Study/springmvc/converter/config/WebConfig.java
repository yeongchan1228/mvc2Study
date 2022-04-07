package mvc.mvc2Study.springmvc.converter.config;

import mvc.mvc2Study.springmvc.converter.converter.IntegerToStringConverter;
import mvc.mvc2Study.springmvc.converter.converter.IpPortToStringConverter;
import mvc.mvc2Study.springmvc.converter.converter.StringToIntegerConverter;
import mvc.mvc2Study.springmvc.converter.converter.StringToIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
        registry.addConverter(new IntegerToStringConverter());
    }
}
