package com.csg.alexandr.builder.configurator.entity.enums;

/**
 * Created by alexandrmyagkiy on 29.07.17.
 */
public enum DocumentPeriodicity {

    WithinYear("В межах року"),
    WithinQuarter("В межах кварталу"),
    WithinMonth("В межах місяця"),
    WithinDay("В межах дня"),
    NotPeriodical("Не періодичний");

    private String presentation;

    DocumentPeriodicity(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
    }

}
