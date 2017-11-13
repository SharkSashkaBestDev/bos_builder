package com.csg.alexandr.builder.configurator.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alexandrmyagkiy on 10.10.17.
 */
@Document(collection = "catalog")
public class Catalog extends MetaObject implements Serializable {

    public Catalog() {
        hierarchy = new Hierarchy();
        code = new Code();
        requisites = new ArrayList<>();
        tableParts = new ArrayList<>();
        owners = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
