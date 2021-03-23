package template.slack.webhook.app.errorbot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


public class ParseUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Map<String, String> data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }
}
