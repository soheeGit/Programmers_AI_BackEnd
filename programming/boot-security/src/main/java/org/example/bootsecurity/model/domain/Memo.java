package org.example.bootsecurity.model.domain;

public record Memo(Long id, String text, String createdAt) {
    public static Memo fromText(String text) {
        return new Memo(0L, text, "");
    }
}
