package template.slack.webhook.app.api.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response<T> {
    int status;
    T data;

    public Response(HttpStatus httpStatus, T data) {
        this.status = httpStatus.value();
        this.data = data;
    }
}
