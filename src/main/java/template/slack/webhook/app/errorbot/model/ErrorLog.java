package template.slack.webhook.app.errorbot.model;

import ch.qos.logback.classic.spi.ILoggingEvent;
import template.slack.webhook.app.errorbot.util.MDCUtil;

import java.time.LocalDateTime;

public class ErrorLog {
    String message;
    String header;
    String body;
    String parameters;
    LocalDateTime timestamp = LocalDateTime.now();

    public ErrorLog(ILoggingEvent event) {
        this.message = event.getMessage();
        this.header = MDCUtil.getJsonHeader();
        this.body = MDCUtil.getJsonBody();
        this.parameters = MDCUtil.getJsonParameter();
    }
}
