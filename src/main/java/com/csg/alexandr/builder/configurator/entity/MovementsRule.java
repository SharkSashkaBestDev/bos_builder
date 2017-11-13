package com.csg.alexandr.builder.configurator.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovementsRule {
    public String id;
    public String dtAccountNumber;
    public String ctAccountNumber;
    public String operationType;
    public List<Requisite> dtAnalytics;
    public List<Requisite> ctAnalytics;
    public Boolean active;

    public MovementsRule() {
        this.id = UUID.randomUUID().toString();

        this.dtAnalytics = new ArrayList<>();
        this.ctAnalytics = new ArrayList<>();
        this.active = true;
    }

    public MovementsRule(String dtAccountNumber, String ctAccountNumber, String operationType, List<Requisite> dtAnalytics, List<Requisite> ctAnalytics) {
        this.id = UUID.randomUUID().toString();

        this.dtAccountNumber = dtAccountNumber;
        this.ctAccountNumber = ctAccountNumber;
        this.operationType = operationType;
        this.dtAnalytics = dtAnalytics;
        this.ctAnalytics = ctAnalytics;
        this.active = true;
    }
}
