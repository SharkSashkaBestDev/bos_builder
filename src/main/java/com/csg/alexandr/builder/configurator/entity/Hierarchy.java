package com.csg.alexandr.builder.configurator.entity;


import com.csg.alexandr.builder.configurator.entity.enums.HierarchyType;

import java.io.Serializable;

/**
 * Created by alexandrmyagkiy on 10.10.17.
 */
public class Hierarchy implements Serializable {

    public Boolean hierarchical;
    public Boolean hierarchyFolder;
    public String hierarchyParent;
    public HierarchyType hierarchyType;
    public Boolean limitLevelCount;
    public Integer levelCount;

    public Hierarchy() {
        this.hierarchical = false;
        this.hierarchyFolder = false;
        this.hierarchyType = HierarchyType.HIERARCHY_FOLDERS_AND_ITEMS;
        this.limitLevelCount = false;
        this.levelCount = 2;
    }

    public Hierarchy(Boolean hierarchical, Boolean hierarchyFolder, String hierarchyParent, HierarchyType hierarchyType, Boolean limitLevelCount, Integer levelCount) {
        this.hierarchical = hierarchical;
        this.hierarchyFolder = hierarchyFolder;
        this.hierarchyParent = hierarchyParent;
        this.hierarchyType = hierarchyType;
        this.limitLevelCount = limitLevelCount;
        this.levelCount = levelCount;
    }
}
