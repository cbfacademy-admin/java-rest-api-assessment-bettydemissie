package com.cbfacademy.apiassessment.exemptionhandling;

public class AlreadyExistsExemption extends RuntimeException {
    public AlreadyExistsExemption(String message) {
        super(message);
    }
}
