package mvc.mvc2Study.springmvc.converter.converter;

import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.converter.type.IpPort;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        log.info("converter source = {}", source);

        return source.getIp() + ":" + String.valueOf(source.getPort());
    }
}
