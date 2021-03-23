package template.slack.webhook.app.errorbot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.MDC;
import org.slf4j.spi.MDCAdapter;

import java.util.LinkedHashMap;
import java.util.Map;

public class MDCUtil {
    public static final String HEADER_MAP_MDC = "HEADER_MAP_MDC";
    public static final String PARAMETER_MAP_MDC = "PARAMETER_MAP_MDC";
    public static final String BODY_MDC = "BODY_MDC";

    private static MDCAdapter mdc = MDC.getMDCAdapter();

    public static void setJsonHeader(String key, Map<String, String> data) throws JsonProcessingException {
        mdc.put(key, ParseUtil.toJson(data));
    }

    public static void setJsonParameter(String key, Map<String, String> data) throws JsonProcessingException {
        mdc.put(key, ParseUtil.toJson(data));
    }

    public static void setJsonBody(String key, String data) {
        mdc.put(key, data);
    }

    public static String getJsonHeader() {
        return mdc.get(HEADER_MAP_MDC);
    }

    public static String getJsonBody() {
        return mdc.get(BODY_MDC);
    }

    public static String getJsonParameter() {
        return mdc.get(PARAMETER_MAP_MDC);
    }
}
