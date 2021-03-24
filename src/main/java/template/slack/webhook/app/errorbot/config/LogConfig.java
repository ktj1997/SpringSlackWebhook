package template.slack.webhook.app.errorbot.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Getter
@Configuration
public class LogConfig {

    @Value("${slack.url}")
    private String URL;

    @Value("${slack.channel}")
    private String channel;

    @Value("${slack.servername}")
    private String name;

    private Level level = Level.ERROR;
}
