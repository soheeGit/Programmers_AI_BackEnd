package org.example.bootjwtkakao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
@CrossOrigin
public class DataController {
    @GetMapping("/hello")
    public ResponseEntity<ResponseDTO> hello() {
        return ResponseEntity.ok(new ResponseDTO("Hello World"));
    }

    public record ResponseDTO(String data) {}
}