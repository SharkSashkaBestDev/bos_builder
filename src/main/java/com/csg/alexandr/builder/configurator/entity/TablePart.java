package com.csg.alexandr.builder.configurator.entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandrmyagkiy on 10.10.17.
 */
public class TablePart implements Serializable {

    public String name;
    public String synonym;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) public List<Requisite> requisites;

    public TablePart() {
        requisites = new ArrayList<>();
    }

    public TablePart(String name, String synonym) {
        this.name = name;
        this.synonym = synonym;
        this.requisites = new ArrayList<>();
    }

    public TablePart(String name, String synonym, List<Requisite> requisites) {
        this.name = name;
        this.synonym = synonym;
        this.requisites = requisites;
    }

    @Override
    public String toString() {
        return "TablePart{" +
                "name='" + name + '\'' +
                ", synonym='" + synonym + '\'' +
                ", requisites=" + requisites +
                '}';
    }
}
