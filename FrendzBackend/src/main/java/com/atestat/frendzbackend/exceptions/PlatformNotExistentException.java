package com.atestat.frendzbackend.exceptions;

public class PlatformNotExistentException extends Exception {
    public PlatformNotExistentException(String errorMessage) {
        super(errorMessage);
    }
}
