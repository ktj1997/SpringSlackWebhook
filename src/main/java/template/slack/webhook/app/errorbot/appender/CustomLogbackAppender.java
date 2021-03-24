package template.slack.webhook.app.errorbot.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;
import template.slack.webhook.app.errorbot.config.LogConfig;
import template.slack.webhook.app.errorbot.model.ErrorLog;
import template.slack.webhook.app.errorbot.model.MessageType;
import template.slack.webhook.app.errorbot.util.JsonUtil;
import template.slack.webhook.app.errorbot.util.MDCUtil;
import template.slack.webhook.app.exception.CustomException;

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

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (eventObject.getLevel().equals(Level.ERROR))
            toSlack(getErrorLog(eventObject));
    }

    private void toSlack(ErrorLog errorLog) {
        SlackField errorMessage = makeSlackField(MessageType.ERROR_MESSAGE, errorLog.getMessage(), false);
        SlackField headerField = makeSlackField(MessageType.HEADER, errorLog.getHeader(), true);
        SlackField bodyField = makeSlackField(MessageType.BODY, errorLog.getBody(), false);
        SlackField parameterField = makeSlackField(MessageType.PARAMETER, errorLog.getParameters(), true);

        SlackAttachment slackAttachment = makeSlackAttachment(List.of(errorMessage, headerField, bodyField, parameterField), errorLog.getTrace());
        SlackMessage slackMessage = makeSlackMessage(List.of(slackAttachment));
        new SlackApi(logConfig.getURL()).call(slackMessage);
    }

    private ErrorLog getErrorLog(ILoggingEvent eventObject) {
        ErrorLog errorLog = new ErrorLog(eventObject, MDCUtil.get(MDCUtil.HEADER_MAP_MDC), MDCUtil.get(MDCUtil.BODY_MDC), MDCUtil.get(MDCUtil.PARAMETER_MAP_MDC));
        errorLog.setTrace(eventObject);

        return errorLog;
    }

    private SlackMessage makeSlackMessage(List<SlackAttachment> slackAttachment) {
        SlackMessage slackMessage = new SlackMessage("");
        slackMessage.setAttachments(slackAttachment);
        slackMessage.setChannel(logConfig.getChannel());
        slackMessage.setUsername(logConfig.getName());

        return slackMessage;
    }

    private SlackAttachment makeSlackAttachment(List<SlackField> slackFields, String trace) {
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setTitle("Internal Server Error");
        slackAttachment.setFallback("Error occurred!");
        slackAttachment.setColor("danger");
        slackAttachment.setFields(slackFields);
        slackAttachment.setText(trace);

        return slackAttachment;
    }

    private SlackField makeSlackField(MessageType type, String text, boolean shouldJson) {
        SlackField slackField = new SlackField();
        slackField.setTitle(type.toString());
        if (shouldJson)
            slackField.setValue(JsonUtil.toPrettyJson(text));
        else
            slackField.setValue(text);
        return slackField;
    }
}
