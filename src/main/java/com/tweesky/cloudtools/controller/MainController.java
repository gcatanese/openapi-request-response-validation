package com.tweesky.cloudtools.controller;

import com.tweesky.cloudtools.dto.RequestData;
import com.tweesky.cloudtools.dto.ResponseData;
import com.tweesky.cloudtools.util.SchemaUtil;
import com.tweesky.cloudtools.validator.OpenApiValidator;
import com.tweesky.cloudtools.validator.OpenApiValidatorObject;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping
public class MainController {

    String schema = "src/test/resources/schema/ManagementService-v1.json";

    private final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping(value = "/test")
    ResponseEntity<?> test(HttpServletRequest request) throws URISyntaxException {
        return ResponseEntity.ok().body("Ok");
    }

    @PostMapping(value = "/validate")
    ResponseEntity<?> post(@RequestBody RequestData request) throws URISyntaxException, IOException {

        OpenApiValidator validator = new OpenApiValidator(SchemaUtil.getContent(schema));

        var responseData = validator.validate(OpenApiValidatorObject.forMethod(request.getMethod())
                .withPath(request.getPath())
                .withRequestBody(request.getRequestAsJson())
                .withResponseBody(request.getResponseAsJson())
                .withResponseContentType("application/json")
                .withStatus(request.getStatusCode())
        );

        return ResponseEntity.ok().body(responseData);
    }

}
