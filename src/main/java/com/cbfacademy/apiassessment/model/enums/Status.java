package com.cbfacademy.apiassessment.model.enums;

public enum Status {
    PENDING,
    IN_PROGRESS,
    COMPLETE;

    public String getStatus() {
        return this.name();
    }
}
