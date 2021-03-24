package template.slack.webhook.app.exception;

public class JsonParsingException extends ApiException {
    public JsonParsingException() {
        super("Json Parsing Error");
    }
}
