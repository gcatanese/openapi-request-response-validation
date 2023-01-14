package com.tweesky.cloudtools.controller;

import com.tweesky.cloudtools.schema.SchemaMap;
import com.tweesky.cloudtools.schema.SchemaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
public class InitController {

    private final Logger log = LoggerFactory.getLogger(InitController.class);

    @PostMapping(value = "/init")
    ResponseEntity<?> init(@RequestParam String collectionId, @RequestBody String json) {
        log.info("/init collectionId:" + collectionId);

        SchemaMap.set(collectionId, json);

        return ResponseEntity.ok().body(collectionId);
    }

    @PostMapping(value = "/initFromFile")
    ResponseEntity<?> initFromFile(@RequestParam String collectionId, @RequestParam("file") MultipartFile file) {
        log.info("/init collectionId:" + collectionId);

        try {
            SchemaMap.set(collectionId, SchemaUtil.getContent(file.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().body(collectionId);
    }

    @GetMapping(value = "/listSchemaKeys")
    ResponseEntity<?> listSchemaKeys() {
        log.info("/listSchemaKeys");

        return ResponseEntity.ok().body(SchemaMap.getKeys());
    }
}
