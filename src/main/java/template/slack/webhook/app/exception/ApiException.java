package template.slack.webhook.app.exception;

public abstract class ApiException extends RuntimeException {
    public ApiException(String msg) {
        super(msg);
    }
}
