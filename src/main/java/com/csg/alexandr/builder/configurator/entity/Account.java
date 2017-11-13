package com.csg.alexandr.builder.configurator.entity;

import com.csg.alexandr.builder.configurator.entity.enums.AccountsChartType;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by alexandrmyagkiy on 21.10.17.
 */
@Document(collection = "accounts")
public class Account implements Serializable {

    public String id;
    public Code code;
    public Account parent;
    public String name;
    public String order;
    @Enumerated(EnumType.STRING)
    public AccountsChartType type;
    public Boolean offBalance;
    public Boolean deleted;
    public List<Map<String, Boolean>> accountingFeatures;
    public Boolean standard;

    public Account() {
        this.id = UUID.randomUUID().toString();
        this.code = new Code();
        this.code.showCode = true;
        this.parent = null;
        this.name = "";
        this.order = "";
        this.offBalance = false;
        this.deleted = false;
        this.accountingFeatures = new ArrayList<>();
        this.standard = false;
    }

    public Account(String id, Code code, String name, String order, AccountsChartType type, Boolean offBalance, List<Map<String, Boolean>> accountingFeatures, Account parent, Boolean standard) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.order = order;
        this.type = type;
        this.offBalance = offBalance;
        this.accountingFeatures = accountingFeatures;
        this.parent = parent;
        this.standard = standard;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", order='" + order + '\'' +
                ", type=" + type +
                ", offBalance=" + offBalance +
                ", deleted=" + deleted +
                '}';
    }
}
