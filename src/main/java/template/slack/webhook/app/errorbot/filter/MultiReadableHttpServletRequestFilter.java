package template.slack.webhook.app.errorbot.filter;

import org.apache.catalina.filters.RequestFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import template.slack.webhook.app.errorbot.MultiReadableServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MultiReadableHttpServletRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new MultiReadableServletRequest(request), response);
    }
}
