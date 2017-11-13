package com.csg.alexandr.builder.configurator.entity.additional;


import com.csg.alexandr.builder.configurator.entity.Account;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alexandrmyagkiy on 03.11.17.
 */
public class AccountsList extends ArrayList<Account> implements Serializable {

    public AccountsList() {
        super();
    }
}
