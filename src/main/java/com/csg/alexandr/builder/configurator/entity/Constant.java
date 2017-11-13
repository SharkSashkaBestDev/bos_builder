package com.csg.alexandr.builder.configurator.entity;

import com.csg.alexandr.builder.configurator.entity.enums.TypeName;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandrmyagkiy on 10.10.17.
 */
@Document(collection = "constant")
public class Constant extends MetaObject implements Serializable {

    public Boolean passwordMode;
    public List<Object> predefined;
    public Boolean standard;

    public Constant() {
        type = new Type(TypeName.String, 10, false, null, null, null);
        this.passwordMode = false;
        this.predefined = new ArrayList<>();
        this.standard = false;
    }
}
