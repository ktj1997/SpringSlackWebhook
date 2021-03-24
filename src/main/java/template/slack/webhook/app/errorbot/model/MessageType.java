package template.slack.webhook.app.errorbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    HEADER, BODY, PARAMETER,ERROR_MESSAGE,TRACE
}
