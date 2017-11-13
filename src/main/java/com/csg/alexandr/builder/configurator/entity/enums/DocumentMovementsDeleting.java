package com.csg.alexandr.builder.configurator.entity.enums;

/**
 * Created by alexandrmyagkiy on 29.07.17.
 */
public enum DocumentMovementsDeleting {

    AutoWhenCancel("Видаляти автоматично при відміні проведення"),
    Auto("Видаляти автоматично"),
    NotAuto("Не видаляти автоматично");

    private String presentation;

    DocumentMovementsDeleting(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
    }

}
