package com.cbfacademy.apiassessment.model.enums;

public enum Department {
    GROUP_FUNCTIONS,
    ASSET_MANAGEMENT,
    INVESTMENT_BANKING,
    WEALTH_MANAGEMENT,
    PRIVATE_CORPORATE;

    public String getDepartment() {
        return this.name();
    }
}