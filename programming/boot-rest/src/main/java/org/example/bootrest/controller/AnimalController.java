package org.example.bootrest.controller;

import org.apache.coyote.BadRequestException;
import org.example.bootrest.model.domain.Animal;
import org.example.bootrest.model.dto.AnimalRequestDTO;
import org.example.bootrest.service.AnimalService;
import org.example.bootrest.service.GeminiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    private final AnimalService animalService;
    private final GeminiService geminiService;

    public AnimalController(AnimalService animalService, GeminiService geminiService) {
        this.animalService = animalService;
        this.geminiService = geminiService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping
    public List<Animal> all() {
        return animalService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AnimalRequestDTO dto) throws Exception {
        String story = geminiService.makeStory(dto);
        animalService.create(dto.toAnimal(story));
        return new ResponseEntity<>(HttpStatus.CREATED);    // 201
        // JPA - 객체 : 저장된 데이터 자체를 돌려주면서 201
        // MyBatis - 201 상태만 줘.
    }
}
