package mvc.mvc2Study.springmvc.converter;

import mvc.mvc2Study.springmvc.converter.converter.IntegerToStringConverter;
import mvc.mvc2Study.springmvc.converter.converter.IpPortToStringConverter;
import mvc.mvc2Study.springmvc.converter.converter.StringToIntegerConverter;
import mvc.mvc2Study.springmvc.converter.converter.StringToIpPortConverter;
import mvc.mvc2Study.springmvc.converter.type.IpPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ConverterTest {
    
    @Test
    public void stringToInteger() throws Exception {
        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        Integer result = stringToIntegerConverter.convert("10");
        assertThat(result).isInstanceOf(Integer.class);
    }

    @Test
    public void integerToString() throws Exception {
        IntegerToStringConverter integerToStringConverter = new IntegerToStringConverter();
        String result = integerToStringConverter.convert(10);
        assertThat(result).isInstanceOf(String.class);
    }
    
    @Test
    public void stringToIpPort() throws Exception {
        StringToIpPortConverter stringToIpPortConverter = new StringToIpPortConverter();
        String str = "127.0.0.1:8080";
        IpPort result = stringToIpPortConverter.convert(str);

        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

    @Test
    public void IpPortToString() throws Exception {
        IpPortToStringConverter ipPortToStringConverter = new IpPortToStringConverter();
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        String result = ipPortToStringConverter.convert(ipPort);

        assertThat(result).isEqualTo("127.0.0.1:8080");
    }
}
