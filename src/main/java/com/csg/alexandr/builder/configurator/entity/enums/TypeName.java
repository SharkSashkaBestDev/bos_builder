package com.csg.alexandr.builder.configurator.entity.enums;

/**
 * Created by alexandrmyagkiy on 30.05.17.
 */
public enum TypeName {

    String("Рядок"),
    Number("Число"),
    Boolean("Булево"),
    Date("Дата"),
    List("Список значень"),
    CatalogLink("Посилання на довідник"),
    DocumentLink("Посилання на документ"),
    ReportLink("Посилання на звіт"),
    Constant("Константа"),
    Any("Будь-який тип");

    private String presentation;

    TypeName(String presentation) {
        this.presentation = presentation;
    }

    public java.lang.String getPresentation() {
        return presentation;
    }
}
