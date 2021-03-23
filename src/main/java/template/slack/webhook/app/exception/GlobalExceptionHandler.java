package template.slack.webhook.app.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import template.slack.webhook.app.api.response.Response;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public Response manageAllError(Exception e) {
        log.error(e.getMessage(), e);
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
}
