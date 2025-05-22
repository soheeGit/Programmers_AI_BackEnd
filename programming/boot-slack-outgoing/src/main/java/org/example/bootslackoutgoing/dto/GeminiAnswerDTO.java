package org.example.bootslackoutgoing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeminiAnswerDTO(List<Candidates> candidates) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Candidates(Content content) { }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Content(List<Part> parts){ }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Part(String text) {}
}


