package org.example.bootdiary.exception;

import org.apache.coyote.BadRequestException;

public class BadDataException extends BadRequestException {
    public BadDataException(String message) {
        super(message);
    }
}