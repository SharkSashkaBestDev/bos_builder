package com.csg.alexandr.builder.configurator.entity.additional;

import com.csg.alexandr.builder.configurator.entity.Catalog;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alexandrmyagkiy on 13.10.17.
 */
public class CatalogsList extends ArrayList<Catalog> implements Serializable {

    public CatalogsList() {
        super();
    }
}
