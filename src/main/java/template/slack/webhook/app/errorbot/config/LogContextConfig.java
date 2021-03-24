package template.slack.webhook.app.errorbot.config;

import ch.qos.logback.classic.LoggerContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import template.slack.webhook.app.errorbot.appender.CustomLogbackAppender;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class LogContextConfig {

    private final LogConfig logConfig;

    @PostConstruct
    public void init() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        CustomLogbackAppender customLogbackAppender = new CustomLogbackAppender(logConfig);
        customLogbackAppender.setContext(loggerContext);
        customLogbackAppender.setName("customLogbackAppender");
        customLogbackAppender.start();

        loggerContext.getLogger("ROOT").addAppender(customLogbackAppender);
    }
}
