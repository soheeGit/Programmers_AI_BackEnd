package org.example.bootthymeleaf.model.dto;

import lombok.Data;

@Data
public class UpdateWordForm {
    private String newWord;
    private String uuid;
}