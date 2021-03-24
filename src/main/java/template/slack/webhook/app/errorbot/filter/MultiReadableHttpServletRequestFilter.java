package template.slack.webhook.app.errorbot.filter;

import template.slack.webhook.app.errorbot.MultiReadableServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MultiReadableHttpServletRequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new MultiReadableServletRequest((HttpServletRequest) request), response);
    }

}
