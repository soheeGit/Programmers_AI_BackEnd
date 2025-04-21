package org.example.bootrestapi.service;

import org.apache.coyote.BadRequestException;
import org.example.bootrestapi.model.entity.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAll();
    Recipe save(Recipe recipe) throws BadRequestException; // JPA
    void delete(long id);

    Recipe findById(long id);
}