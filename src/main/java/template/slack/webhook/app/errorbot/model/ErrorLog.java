package template.slack.webhook.app.errorbot.model;

import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import template.slack.webhook.app.errorbot.util.MDCUtil;

import java.time.LocalDateTime;

@Getter
@ToString
public class ErrorLog {
    String message;
    String header;
    String body;
    String parameters;
    LocalDateTime timestamp = LocalDateTime.now();

    public ErrorLog(ILoggingEvent event,String header, String body,String parameters) {
        this.message = event.getMessage();
        this.header = header;
        this.body = body;
        this.parameters = parameters;
    }
}
