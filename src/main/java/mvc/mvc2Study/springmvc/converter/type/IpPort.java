package mvc.mvc2Study.springmvc.converter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode // ip, port가 같은 객체는 같다. 원래는 주소 값이 달라서 false
// 127.0.0.1:8080 -> ip = 127.0.0.1, port = 8080
public class IpPort {

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
