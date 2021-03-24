package template.slack.webhook.app.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import template.slack.webhook.app.api.response.Response;
import template.slack.webhook.app.api.service.CustomService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class Controller {

    private final CustomService customService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<String> getHandler(@RequestParam int code) {
        return new Response(HttpStatus.OK, customService.getService(code));
    }

    @PostMapping
    public Response postHandler(HttpServletRequest request) throws IOException {

        return null;
    }

    @PutMapping
    public Response putHandler() {
        return null;
    }

}
