package org.example.bootrestapi.controller;

import org.apache.coyote.BadRequestException;
import org.example.bootrestapi.model.dto.RecipeDTO;
import org.example.bootrestapi.model.entity.Recipe;
import org.example.bootrestapi.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Spring
@RequestMapping("/api/recipe") // Spring MVC
@CrossOrigin
//@CrossOrigin(origins = "localhost:5500")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
//    public List<Recipe> getAllRecipes() {
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        // localhost:8080/api/recipe
        //        Recipe r = new Recipe();
        //        r.setName("커리");
        //        r.setDescription("맛있는 커리");
        //        recipeService.save(r);
        return ResponseEntity.ok(recipeService.findAll());
    }

    @PostMapping
//    public Recipe addRecipe(@RequestBody RecipeDTO recipeDTO) {
    public ResponseEntity<Recipe> addRecipe(@RequestBody RecipeDTO recipeDTO) throws BadRequestException {
//        try {
        // 변환로직 1. 컨트롤러 - Body / Param
        // 2. 서비스
        Recipe recipe = new Recipe();
        // 레시피 이름은 50자를 넘을 필요가 없을 듯합니다... 50자로 하죠!
        recipe.setName(recipeDTO.name());
        recipe.setDescription(recipeDTO.description());
//        return recipeService.save(recipe);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recipeService.save(recipe));
//        } catch (BadRequestException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
    }

    @DeleteMapping("/{id}") // recipe/{id}. recipe?id=???
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        recipeService.delete(id);
        return ResponseEntity.noContent().build();
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        // 204는 response body가 없다!
        // 200 등의 다른 코드를 쓰거나...
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable long id, @RequestBody RecipeDTO recipeDTO) throws BadRequestException {
        Recipe oldRecipe = recipeService.findById(id); // update -> 기존에 있는...
        oldRecipe.setName(recipeDTO.name());
        oldRecipe.setDescription(recipeDTO.description());
        return ResponseEntity.status(HttpStatus.CREATED) // 200, 201
                .body(recipeService.save(oldRecipe));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<Recipe> updateName(@PathVariable long id, @RequestBody RecipeDTO recipeDTO) throws BadRequestException {
        Recipe oldRecipe = recipeService.findById(id);
        oldRecipe.setName(recipeDTO.name());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recipeService.save(oldRecipe));
        // patch -> void.는 경우.
    }

}