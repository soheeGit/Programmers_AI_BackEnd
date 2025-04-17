package org.example.bootdiary.exception;

import org.apache.coyote.BadRequestException;

public class BadFileException extends BadRequestException {
    public BadFileException(String message) {
        super(message);
    }
}
