package template.slack.webhook.app.errorbot.model;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RequestWrapper {
    private HttpServletRequest request;

    public static RequestWrapper of(HttpServletRequest request) {
        return new RequestWrapper(request);
    }

    private RequestWrapper(HttpServletRequest request) {
        this.request = request;
    }

    public Map<String, String> getHeader() {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headerMap.put(name, request.getHeader(name));
        }
        return headerMap;
    }

    public String getBody() throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String buffer = null;
        while ((buffer = br.readLine()) != null) {
            if (sb.length() > 0)
                sb.append("\n");
            sb.append(buffer);
        }
        return sb.toString();
    }

    public Map<String, String> getParameter() {
        Map<String, String> parameterMap = new HashMap<>();
        Enumeration<String> parameterKey = request.getParameterNames();
        while (parameterKey.hasMoreElements()) {
            String key = parameterKey.nextElement();
            parameterMap.put(key, request.getParameter(key));
        }
        return parameterMap;
    }
}
