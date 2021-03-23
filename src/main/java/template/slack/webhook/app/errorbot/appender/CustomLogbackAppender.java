package template.slack.webhook.app.errorbot.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import template.slack.webhook.app.errorbot.model.ErrorLog;
import template.slack.webhook.app.errorbot.service.SlackApi;

@Component
@RequiredArgsConstructor
public class CustomLogbackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private final SlackApi slackApi;

    @Override
    public void doAppend(ILoggingEvent eventObject) {
        super.doAppend(eventObject);
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (eventObject.getLevel().equals(Level.ERROR))
            toSlack(getErrorLog(eventObject));
    }

    private void toSlack(ErrorLog errorLog) {
        slackApi.call(errorLog);
    }

    private ErrorLog getErrorLog(ILoggingEvent eventObject) {
        return new ErrorLog(eventObject);
    }

}
