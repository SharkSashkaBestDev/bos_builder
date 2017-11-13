package com.csg.alexandr.builder.configurator.entity.enums;

/**
 * Created by alexandrmyagkiy on 21.10.17.
 */
public enum AccountsChartType {

    Active("Активний"),
    Passive("Пасивний"),
    ActivePassive("Активний/Пасивний");

    private String presentation;

    AccountsChartType(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
    }

}
