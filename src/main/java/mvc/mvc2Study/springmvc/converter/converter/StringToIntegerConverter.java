package mvc.mvc2Study.springmvc.converter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        log.info("source = {}", source);
        return Integer.valueOf(source);
    }
}
