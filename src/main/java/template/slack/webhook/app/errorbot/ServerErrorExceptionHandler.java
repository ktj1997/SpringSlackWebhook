package template.slack.webhook.app.errorbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import template.slack.webhook.app.api.response.Response;
import template.slack.webhook.app.errorbot.util.JsonUtil;

@Order(2)
@RestController
@ControllerAdvice
public class ServerErrorExceptionHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Throwable.class)
    public Response internalServerErrorHandler(Throwable t) {
        logger.error(t.getMessage(), t);
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, t.getMessage());
    }
}
