package template.slack.webhook.app.errorbot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import template.slack.webhook.app.exception.CustomException;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JsonUtil {
    private static ObjectMapper staticObjectMapper;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void setObjectMapper() {

        System.out.println("Set static ObjectMapper");
        staticObjectMapper = objectMapper;
        System.out.println(staticObjectMapper);
    }

    public static <T> String toJson(T data) {
        try {
            return staticObjectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new CustomException("Json Parsing Error");
        }
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        try {
            return staticObjectMapper.readValue(json, cls);
        } catch (Exception e) {
            throw new CustomException("Json Parsing Error");
        }
    }

    public static <T> String toPrettyJson(String jsonString) {
        try {
            if (jsonString.length() > 0) {
                Object jsonObject = fromJson(jsonString, Object.class);
                return staticObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            } else
                return "";
        } catch (Exception e) {
            throw new CustomException("Json Parsing Error");
        }
    }
}
