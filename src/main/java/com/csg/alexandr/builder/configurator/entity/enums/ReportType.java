package com.csg.alexandr.builder.configurator.entity.enums;

/**
 * Created by alexandrmyagkiy on 30.07.17.
 */
public enum ReportType {

    List("Список"),
    Table("Таблиця"),
    Diagram("Діаграма");

    private String presentation;

    ReportType(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
    }

}
