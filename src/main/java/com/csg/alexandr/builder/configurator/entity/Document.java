package com.csg.alexandr.builder.configurator.entity;


import com.csg.alexandr.builder.configurator.entity.enums.AllowDisallow;
import com.csg.alexandr.builder.configurator.entity.enums.DocumentMovementsDeleting;
import com.csg.alexandr.builder.configurator.entity.enums.DocumentPeriodicity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandrmyagkiy on 10.10.17.
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "document")
public class Document extends MetaObject implements Serializable {

    public DocumentPeriodicity periodicity;
    public AllowDisallow carryingOut;
    public AllowDisallow operationalCarryingOut;
    public DocumentMovementsDeleting movementsDeleting;
    public List<MovementsRule> movementsRules;

    public Document() {
        code = new Code(true);
        requisites = new ArrayList<>();
        tableParts = new ArrayList<>();
        owners = new ArrayList<>();
        this.periodicity = DocumentPeriodicity.WithinYear;
        this.carryingOut = AllowDisallow.Allow;
        this.operationalCarryingOut = AllowDisallow.Allow;
        this.movementsDeleting = DocumentMovementsDeleting.AutoWhenCancel;
        this.movementsRules = new ArrayList<>();
    }
}
