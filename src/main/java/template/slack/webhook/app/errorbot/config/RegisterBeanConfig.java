package template.slack.webhook.app.errorbot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import template.slack.webhook.app.errorbot.filter.MdcFilter;
import template.slack.webhook.app.errorbot.filter.MultiReadableHttpServletRequestFilter;

@Configuration
public class RegisterBeanConfig {

    @Bean
    public FilterRegistrationBean<MultiReadableHttpServletRequestFilter> multiReadableHttpServletRequestFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        MultiReadableHttpServletRequestFilter multiReadableHttpServletRequestFilter
                = new MultiReadableHttpServletRequestFilter();
        filterRegistrationBean.setFilter(multiReadableHttpServletRequestFilter);
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;

    }

    @Bean
    public FilterRegistrationBean<MdcFilter> mdcFilterRegister() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        MdcFilter mdcFilter = new MdcFilter();
        filterRegistrationBean.setFilter(mdcFilter);
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
}
