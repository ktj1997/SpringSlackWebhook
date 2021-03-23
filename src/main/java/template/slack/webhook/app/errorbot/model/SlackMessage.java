package template.slack.webhook.app.errorbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class SlackMessage {
    String header;
    String body;
    String parameter;
}
