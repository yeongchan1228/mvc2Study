package mvc.mvc2Study.springmvc.converter.converter;

import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.converter.type.IpPort;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {
    @Override
    public IpPort convert(String source) {
        log.info("convert source = {}", source);
        String[] str = source.split(":");

        return new IpPort(str[0], Integer.parseInt(str[1]));
    }
}
