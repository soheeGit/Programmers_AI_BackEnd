package org.example.bootsunflower.repository;

import org.example.bootsunflower.entity.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromptRepository extends JpaRepository<Prompt, String> {
}