package template.slack.webhook.app.errorbot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class JsonUtil {
    private static ObjectMapper staticObjectMapper;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void setObjectMapper() {
        staticObjectMapper = objectMapper;
    }

    public static <T> String toJson(T data) throws JsonProcessingException {
        return staticObjectMapper.writeValueAsString(data);
    }

}
