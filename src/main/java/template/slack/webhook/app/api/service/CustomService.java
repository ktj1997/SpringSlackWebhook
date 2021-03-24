package template.slack.webhook.app.api.service;

import org.springframework.stereotype.Service;
import template.slack.webhook.app.exception.CustomException;

@Service
public class CustomService {
    public String getService(int num) {
        if (num % 2 == 0)
            throw new IllegalArgumentException("짝수");
        else
            return "홀수";
    }

    public void postService() {

    }

    public void putService() {

    }
}
