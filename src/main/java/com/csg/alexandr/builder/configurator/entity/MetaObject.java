package com.csg.alexandr.builder.configurator.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by alexandrmyagkiy on 10.10.17.
 */
public abstract class MetaObject implements Serializable {

    @Id
    public String id;
    public String name;
    public String presentationField;
    public Integer nameLength;
    public Code code;
    public Type type;
    public String synonym;
    public String comment;
    public String objectPresentation;
    public String listPresentation;
    public Hierarchy hierarchy;
    public Boolean deleted;
    public Boolean nameRequired;
    public Boolean uniqueness;
    public Boolean showName;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) public List<Requisite> requisites;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) public List<TablePart> tableParts;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) public List<String> owners;

//    @CreatedDate
//    @org.hibernate.annotations.Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class)
    public LocalDateTime creationTime;

//    @LastModifiedDate
//    @org.hibernate.annotations.Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class)
    public LocalDateTime modificationTime;

    public MetaObject() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.presentationField = "";
        this.nameLength = 25;
        this.synonym = "";
        this.comment = "";
        this.deleted = false;
        this.nameRequired = true;
        this.uniqueness = true;
        this.showName = true;
    }

    public void addRequisite(Requisite requisite) {
        if (this.requisites == null)
            this.requisites = new ArrayList<>();
        this.requisites.add(requisite);
    }

    public void deleteRequisite(Requisite requisite) {
        if (this.requisites == null) {
            this.requisites = new ArrayList<>();
            return;
        }
        this.requisites.remove(requisite);
    }

    public void addTablePart(TablePart tablePart) {
        if (this.tableParts == null)
            this.tableParts = new ArrayList<>();
        this.tableParts.add(tablePart);
    }

    public void deleteTablePart(TablePart tablePart) {
        if (this.tableParts == null) {
            this.tableParts = new ArrayList<>();
            return;
        }
        this.tableParts.remove(tablePart);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentationField() {
        return presentationField;
    }

    public void setPresentationField(String presentationField) {
        this.presentationField = presentationField;
    }
}
