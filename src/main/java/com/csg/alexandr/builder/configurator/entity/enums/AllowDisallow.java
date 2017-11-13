package com.csg.alexandr.builder.configurator.entity.enums;

/**
 * Created by alexandrmyagkiy on 29.07.17.
 */
public enum AllowDisallow {

    Allow("Дозволити"),
    Disallow("Заборонити");

    private String presentation;

    AllowDisallow(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
    }

}
