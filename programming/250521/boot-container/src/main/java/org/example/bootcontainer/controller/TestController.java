package org.example.bootcontainer.controller;

import lombok.RequiredArgsConstructor;
import org.example.bootcontainer.dto.AnimalDTO;
import org.example.bootcontainer.entity.Animal;
import org.example.bootcontainer.repository.AnimalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {
    private final AnimalRepository animalRepository;

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello world");
    }

    @GetMapping("/animals")
    public ResponseEntity<List<AnimalDTO>> getAnimals() {
        return ResponseEntity.ok(
                animalRepository.findAll()
                        .stream()
                        .map(el -> new AnimalDTO(el.getName()))
                        .toList()
        );
    }

    @PostMapping("/animals")
    public ResponseEntity<Animal> addAnimal(@RequestBody AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setName(animalDTO.name());
        animalRepository.save(animal);
        // return ResponseEntity.status(201).body(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(animal);
    }
}