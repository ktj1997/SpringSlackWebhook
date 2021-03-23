package template.slack.webhook.app.errorbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import template.slack.webhook.app.errorbot.model.ErrorLog;

@Service
@RequiredArgsConstructor
public class SlackApi {

    @Value("${slack.url}")
    String webHookURL;

    private RestTemplate restTemplate = new RestTemplate();

    public void call(ErrorLog errorLog) {
        restTemplate.postForEntity(webHookURL, errorLog, null);
    }
}
