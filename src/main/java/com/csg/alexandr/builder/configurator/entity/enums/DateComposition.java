package com.csg.alexandr.builder.configurator.entity.enums;

import java.io.Serializable;

/**
 * Created by alexandrmyagkiy on 30.05.17.
 */
public enum DateComposition implements Serializable {
    Date("Дата"),
    Time("Час"),
    DateAndTime("Дата і час");

    private String presentation;

    DateComposition(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
    }
}
