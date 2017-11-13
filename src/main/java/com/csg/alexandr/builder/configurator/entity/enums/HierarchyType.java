package com.csg.alexandr.builder.configurator.entity.enums;

/**
 * Created by alexandrmyagkiy on 28.05.17.
 */
public enum HierarchyType {

    HIERARCHY_FOLDERS_AND_ITEMS("Ієрархія груп та елементів"),
    HIERARCHY_FOLDERS("Ієрархія груп"),
    HIERARCHY_ITEMS("Ієрархія елементів");

    private String presentation;

    HierarchyType(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
    }

}
