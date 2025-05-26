package org.example.bootpromreview.controller;

import io.micrometer.core.annotation.Counted;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class PromController {
    @GetMapping("/200")
    public ResponseEntity<Void> get200() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/201")
    public ResponseEntity<Void> get201() {
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/400")
    public ResponseEntity<Void> get400() {
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/wait")
    public ResponseEntity<Void> getWait() throws InterruptedException {
        Thread.sleep(new Random().nextInt(1000, 5000));
        return ResponseEntity.ok().build();
    }

    @Counted(value = "count.money", description = "Money count")
//    @Counted("count.money")
    @GetMapping("/money")
    public ResponseEntity<Void> getMoney() {
        return ResponseEntity.ok().build();
    }
}