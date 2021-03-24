package template.slack.webhook.app.errorbot.filter;

import org.slf4j.MDC;
import template.slack.webhook.app.errorbot.model.RequestWrapper;
import template.slack.webhook.app.errorbot.util.MDCUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * MDC --> Map형식으로 여러 메타데이터를 넣을 수 있다.
 * 멀티클라이언트 환경에서 다른 클라이언트의 값을 구별하여 추적하게 할 수 있는 Map
 * ThreadLocal에 구별할 수 있는 키 값을 저장하여 Thread가 존재하는 한 계속 사용가능
 */
public class MdcFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestWrapper requestWrapper = RequestWrapper.of((HttpServletRequest) request);
        MDCUtil.setJsonHeader(MDCUtil.HEADER_MAP_MDC, requestWrapper.getHeader());
        MDCUtil.setJsonBody(MDCUtil.BODY_MDC, requestWrapper.getBody());
        MDCUtil.setJsonParameter(MDCUtil.PARAMETER_MAP_MDC, requestWrapper.getParameter());
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
