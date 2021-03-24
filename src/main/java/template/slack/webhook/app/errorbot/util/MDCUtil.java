package template.slack.webhook.app.errorbot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.slf4j.spi.MDCAdapter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MDCUtil {
    public static final String HEADER_MAP_MDC = "HEADER_MAP_MDC";
    public static final String PARAMETER_MAP_MDC = "PARAMETER_MAP_MDC";
    public static final String BODY_MDC = "BODY_MDC";

    private static MDCAdapter mdc = MDC.getMDCAdapter();

    public static void setJsonHeader(String key, Map<String, String> data) throws JsonProcessingException {
        mdc.put(key, JsonUtil.toJson(data));
    }

    public static void setJsonParameter(String key, Map<String, String> data) throws JsonProcessingException {
        mdc.put(key, JsonUtil.toJson(data));
    }

    public static void setJsonBody(String key, String data) {
        mdc.put(key, data);
    }

    public static String get(String key) {
        return mdc.get(key);
    }

}
