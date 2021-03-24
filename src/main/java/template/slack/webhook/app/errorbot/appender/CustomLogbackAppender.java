package template.slack.webhook.app.errorbot.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import template.slack.webhook.app.errorbot.config.LogConfig;
import template.slack.webhook.app.errorbot.model.ErrorLog;
import template.slack.webhook.app.errorbot.util.JsonUtil;
import template.slack.webhook.app.errorbot.util.MDCUtil;

import java.util.List;

public class CustomLogbackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private LogConfig logConfig;

    public CustomLogbackAppender(LogConfig logConfig) {
        this.logConfig = logConfig;
    }

    @Override
    public void doAppend(ILoggingEvent eventObject) {
        super.doAppend(eventObject);
    }

    @SneakyThrows
    @Override
    protected void append(ILoggingEvent eventObject) {
        if (eventObject.getLevel().equals(Level.ERROR)) {
            System.out.println("BeforeCall");
            toSlack(getErrorLog(eventObject));
            System.out.println("AfterCall");
        }
    }

    private void toSlack(ErrorLog errorLog) throws JsonProcessingException {
        System.out.println("API START");
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setTitle(errorLog.getMessage());

        SlackMessage slackMessage = new SlackMessage(logConfig.getChannel(), logConfig.getName(), JsonUtil.toJson(errorLog));
        slackMessage.setAttachments(List.of(slackAttachment));

        System.out.println("Call Api");
        new SlackApi(logConfig.getURL()).call(slackMessage);
    }

    private ErrorLog getErrorLog(ILoggingEvent eventObject) {
        System.out.println("makeErroLog");
        return new ErrorLog(eventObject, MDCUtil.get(MDCUtil.HEADER_MAP_MDC), MDCUtil.get(MDCUtil.BODY_MDC), MDCUtil.get(MDCUtil.PARAMETER_MAP_MDC));
    }
}
