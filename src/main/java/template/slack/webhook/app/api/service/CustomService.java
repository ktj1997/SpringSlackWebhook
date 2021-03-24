package template.slack.webhook.app.api.service;

import org.springframework.stereotype.Service;
import template.slack.webhook.app.exception.CustomException;

import java.io.InputStream;

@Service
public class CustomService {
    public String getService(int num) {
        if (num % 2 == 0)
            throw new RuntimeException("짝수");
        else
            return "홀수";
    }

    public String postService(InputStream body) {
        if(System.currentTimeMillis() % 2 == 0)
            throw new RuntimeException("실패");
        else
                return "성공"+body.toString();
    }

    public String putService(InputStream body) {
        if(System.currentTimeMillis() % 2 == 0)
            throw new CustomException("실패");
        else
            return "성공"+body.toString();
    }
}
