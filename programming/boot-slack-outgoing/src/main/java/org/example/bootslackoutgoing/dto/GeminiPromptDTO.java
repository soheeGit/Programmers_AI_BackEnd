package org.example.bootslackoutgoing.dto;

import java.util.List;

public record GeminiPromptDTO(List<Content> contents) {
    public record Content(List<Part> parts){ }
    public record Part(String text) {}
}