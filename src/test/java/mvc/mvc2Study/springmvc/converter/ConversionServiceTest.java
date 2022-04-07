package mvc.mvc2Study.springmvc.converter;

import mvc.mvc2Study.springmvc.converter.converter.IntegerToStringConverter;
import mvc.mvc2Study.springmvc.converter.converter.IpPortToStringConverter;
import mvc.mvc2Study.springmvc.converter.converter.StringToIntegerConverter;
import mvc.mvc2Study.springmvc.converter.converter.StringToIpPortConverter;
import mvc.mvc2Study.springmvc.converter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    @Test
    public void conversionService(){
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());

        //
        Integer result = conversionService.convert("10", Integer.class);
        assertThat(result).isInstanceOf(Integer.class);

        String result2 = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(result2).isEqualTo("127.0.0.1:8080");
    }
}
