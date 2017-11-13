package com.csg.alexandr.builder.configurator.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandrmyagkiy on 21.10.17.
 */
@Document(collection = "accounts_chart")
public class AccountsChart extends MetaObject implements Serializable {

    public List<String> accountingFeatures;
    public String codeMask;
    public Boolean codeAutoOrdering;
    public List<Account> accounts;

    public AccountsChart() {
        super();
        code = new Code(true);
        hierarchy = new Hierarchy();
        this.accountingFeatures = new ArrayList<>();
        this.codeMask = "";
        this.codeAutoOrdering = true;
        this.accounts = new ArrayList<>();
    }
}
