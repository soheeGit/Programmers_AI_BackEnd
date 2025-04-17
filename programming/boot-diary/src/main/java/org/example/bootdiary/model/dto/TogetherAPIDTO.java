package org.example.bootdiary.model.dto;

import java.util.List;

public record TogetherAPIDTO(String model, List<Message> messages) {
    public record Message(String role, String content) {}
}