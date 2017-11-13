package com.csg.alexandr.builder.configurator.entity.enums;

/**
 * Created by alexandrmyagkiy on 28.05.17.
 */
public enum CodeType {

    String("Рядок"),
    Number("Число");

    private String presentation;

    CodeType(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
    }

}
