package ?(entityPackage);

import ?(basePackage).configurator.entity.Catalog;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.mongodb.core.mapping.Document;
import com.csg.alexandr.bos.configurator.entity.Requisite;
import com.csg.alexandr.bos.configurator.entity.Type;
import com.csg.alexandr.bos.configurator.entity.enums.TypeName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

@Document(collection = "?(dbName)")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ?(name) extends Catalog implements Serializable {

    public ?(name)() {
        super();

        showName = ?(showName);
        code.showCode = ?(showCode);
        code.codeLength = 9;
        code.autonumbering = true;
        objectPresentation = "?(objectPresentation)";
        listPresentation = "?(listPresentation)";
        presentationField = "?(presentationField)";
        uniqueness = ?(uniqueness);
        nameLength = ?(nameLength);
        hierarchy.hierarchical = ?(hierarchical);
        hierarchy.limitLevelCount = ?(limitLevelCount);
        hierarchy.levelCount = ?(levelCount);

        ?(requisites)

        ?(tableParts)
    }

    @Override
    public String toString() {
        return "?(name){" +
                "id='" + id + '\'' +
                ", Номер='" + code.value + '\'' +
                ", Назва='" + name + '\'' +
                ", Реквізити=" + requisites +
                '}';
    }
}
