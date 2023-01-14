package com.tweesky.cloudtools.controller;

import com.tweesky.cloudtools.dto.RequestData;
import com.tweesky.cloudtools.schema.SchemaMap;
import com.tweesky.cloudtools.validator.OpenApiValidator;
import com.tweesky.cloudtools.validator.OpenApiValidatorObject;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Map;

import static com.tweesky.cloudtools.OpenApiValidatorApplication.DEFAULT_SCHEMA_KEY;

@RestController
@RequestMapping
public class MainController {

    private final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping(value = "/test")
    ResponseEntity<?> test(HttpServletRequest request) throws URISyntaxException {
        return ResponseEntity.ok().body("Ok");
    }

    @PostMapping(value = "/validate")
    ResponseEntity<?> validate(@RequestBody Map<String, Object> req) {
        RequestData request = new RequestData(req);

        String key = request.getCollectionId().isEmpty() ? DEFAULT_SCHEMA_KEY : request.getCollectionId();

        OpenApiValidator validator = new OpenApiValidator(SchemaMap.get(key));

        var responseData = validator.validate(OpenApiValidatorObject.forMethod(request.getMethod())
                .withPath(request.getPath())
                .withHeaders(request.getHeaders())
                .withRequestBody(request.getRequestAsJson())
                .withResponseBody(request.getResponseAsJson())
                .withResponseContentType("application/json")
                .withStatus(request.getStatusCode())
        );

        return ResponseEntity.ok().body(responseData);
    }

}
