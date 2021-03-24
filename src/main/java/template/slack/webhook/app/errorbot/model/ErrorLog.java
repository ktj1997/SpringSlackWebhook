package template.slack.webhook.app.errorbot.model;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;

@Getter
@NoArgsConstructor
public class ErrorLog {
    private String message;
    private String trace;
    private String header;
    private String body;
    private String parameters;
    private final Date timestamp = new Date();

    public ErrorLog(ILoggingEvent event, String header, String body, String parameters) {
        this.message = event.getMessage();
        this.header = header;
        this.body = body;
        this.parameters = parameters;
    }
    public void setTrace(ILoggingEvent event) {
        IThrowableProxy proxy = event.getThrowableProxy();
        if (proxy != null)
            trace = getStackTrace(proxy);
        else
            trace = "";
    }

    private String getStackTrace(IThrowableProxy proxy) {
        StringBuilder sb = new StringBuilder();
        for(StackTraceElementProxy st :proxy.getStackTraceElementProxyArray())
            sb.append(st.getStackTraceElement().toString()).append("\n");
        return sb.toString();
    }
}
