package com.csg.alexandr.builder.configurator.entity;


import com.csg.alexandr.builder.configurator.entity.enums.DateComposition;
import com.csg.alexandr.builder.configurator.entity.enums.TypeName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandrmyagkiy on 10.10.17.
 */
public class Type implements Serializable {

    public String name;
    public String presentation;
    public Integer length;
    public Boolean unlimited;
    public Integer accuracy;
    public Boolean nonnegative;
    public String dateComposition;
    public List<String> links;

    public Type() {
        this.links = new ArrayList<>();
        this.name = TypeName.String.name();
        this.presentation = TypeName.String.getPresentation();
        this.length = 10;
        this.dateComposition = DateComposition.Date.name();
    }

    public Type(TypeName name, Integer length, Boolean unlimited, Integer accuracy, Boolean nonnegative, DateComposition dateComposition) {
        this.name = name != null ? name.name() : TypeName.String.name();
        this.presentation = name != null ? name.getPresentation() : TypeName.String.getPresentation();
        this.length = length;
        this.unlimited = unlimited;
        this.accuracy = accuracy;
        this.nonnegative = nonnegative;
        this.dateComposition = dateComposition != null ? dateComposition.name() : DateComposition.Date.name();
        this.links = new ArrayList<>();
    }

    public Type(TypeName name, Integer length, Boolean unlimited, Integer accuracy, Boolean nonnegative, DateComposition dateComposition, List<String> links) {
        this.name = name != null ? name.name() : TypeName.String.name();
        this.presentation = name != null ? name.getPresentation() : TypeName.String.getPresentation();
        this.length = length;
        this.unlimited = unlimited;
        this.accuracy = accuracy;
        this.nonnegative = nonnegative;
        this.dateComposition = dateComposition != null ? dateComposition.name() : DateComposition.Date.name();
        this.links = links;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name=" + name +
                ", presentation=" + presentation +
                ", length=" + length +
                ", unlimited=" + unlimited +
                ", accuracy=" + accuracy +
                ", nonnegative=" + nonnegative +
                ", dateComposition=" + dateComposition +
                '}';
    }
}
