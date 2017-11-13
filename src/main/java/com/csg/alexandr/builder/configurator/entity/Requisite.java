package com.csg.alexandr.builder.configurator.entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by alexandrmyagkiy on 10.10.17.
 */
public class Requisite implements Serializable {

    public String id;
    public String name;
    public String synonym;
    public Type type;
    public Boolean required;
    public Boolean passwordMode;
    public Boolean standard;
    public Object value;

    public Requisite() {
        id = UUID.randomUUID().toString();
    }

    public Requisite(String id, String name, String synonym, Type type, Boolean required, Boolean passwordMode, Boolean standard, Object value) {
        this.id = id;
        this.name = name;
        this.synonym = synonym;
        this.type = type;
        this.required = required;
        this.passwordMode = passwordMode;
        this.standard = standard;
        this.value = value;
    }
}
