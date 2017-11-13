package com.csg.alexandr.builder.configurator.entity;


import com.csg.alexandr.builder.configurator.entity.enums.CodeType;

import java.io.Serializable;

/**
 * Created by alexandrmyagkiy on 10.10.17.
 */
public class Code implements Serializable {

    public String value;
    public Integer codeLength;
    public CodeType codeType;
    public Boolean autonumbering;
    public Boolean showCode;

    public Code() {
        this.value = "";
        this.codeLength = 9;
        this.codeType = CodeType.String;
        this.autonumbering = true;
        this.showCode = false;
    }

    public Code(String value, Integer codeLength, CodeType codeType, Boolean autonumbering, Boolean showCode) {
        this.value = value;
        this.codeLength = codeLength;
        this.codeType = codeType;
        this.autonumbering = autonumbering;
        this.showCode = showCode;
    }

    public Code(Boolean showCode) {
        this.value = "";
        this.showCode = showCode;
        this.codeLength = 9;
        this.codeType = CodeType.String;
        this.autonumbering = true;
    }
}
