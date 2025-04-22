package org.example.bootswagger.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "안녕!")
public class TestController {
    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/welcome")
    @ApiResponse(
            responseCode = "204"
    )
    public ResponseEntity<Void> welcome() {
        return ResponseEntity.noContent().build();
    }
}