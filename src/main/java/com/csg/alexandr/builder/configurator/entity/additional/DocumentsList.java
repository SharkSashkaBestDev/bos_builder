package com.csg.alexandr.builder.configurator.entity.additional;


import com.csg.alexandr.builder.configurator.entity.Document;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alexandrmyagkiy on 13.10.17.
 */
public class DocumentsList extends ArrayList<Document> implements Serializable {

    public DocumentsList() {
        super();
    }
}
