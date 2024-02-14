package com.retrospective.test.exception;

public class RetrospectiveNotFoundException extends RuntimeException {

    public RetrospectiveNotFoundException(String retrospectiveName) {
        super("Retrospective not found with name: " + retrospectiveName);
    }
}
