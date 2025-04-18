package org.example.bootrest.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeminiResponseDTO(
        List<Candidate> candidates
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Candidate(Content content) {}
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Content(List<Part> parts) {}
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Part(String text) {}
}