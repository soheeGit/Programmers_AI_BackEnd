package org.example.bootrest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.bootrest.model.dto.AnimalRequestDTO;

import java.io.IOException;

public interface GeminiService {
    String makeStory(AnimalRequestDTO dto) throws Exception;
}
