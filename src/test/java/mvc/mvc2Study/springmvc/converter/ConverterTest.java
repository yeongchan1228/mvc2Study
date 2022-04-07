package mvc.mvc2Study.springmvc.converter;

import mvc.mvc2Study.springmvc.converter.converter.IntegerToStringConverter;
import mvc.mvc2Study.springmvc.converter.converter.StringToIntegerConverter;
import org.assertj.core.api.Assertions;
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
}
