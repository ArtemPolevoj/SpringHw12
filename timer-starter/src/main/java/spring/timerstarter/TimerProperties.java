package spring.timerstarter;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "timer")
public class TimerProperties  {

}
