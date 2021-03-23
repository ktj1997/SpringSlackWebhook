package template.slack.webhook.app.exception;

public class CustomException extends ApiException {
    public CustomException(String msg) {
        super(msg);
    }
}
