package com.csg.alexandr.builder.configurator.entity;

import com.csg.alexandr.builder.configurator.entity.enums.ReportType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandrmyagkiy on 10.10.17.
 */
@Document(collection = "report")
public class Report extends MetaObject implements Serializable {

    public List<String> datasources;
    public ReportType reportType;
    public String dateStart;
    public String dateEnd;

    public Report() {
        this.datasources = new ArrayList<>();
        this.reportType = ReportType.Table;
    }
}
