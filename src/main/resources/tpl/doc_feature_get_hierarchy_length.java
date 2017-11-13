package ?(featurePackage).?(name);

import ?(basePackage).BosPlatformApplication;
import ?(moduleEntityPackage).BOSObject;
import ?(moduleEntityPackage).Feature;
import ?(moduleEntityPackage).FeatureOutput;
import ?(moduleEntityPackage).enums.BasicScope;
import ?(entityPackage).?(name);
import ?(servicePackage).?(name)Service;
import ?(utilsPackage).CommonApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GetHierarchyLength?(name)DocumentsFeature extends Feature {

    private final CommonApplicationProperties properties;
    private final ?(name)Service service;

    @Autowired
    public GetHierarchyLength?(name)DocumentsFeature(CommonApplicationProperties properties, ?(name)Service service) {
        this.properties = properties;
        this.service = service;
    }

    @PostConstruct
    private void init() throws ClassNotFoundException {
        BOSObject bOSObject = new BOSObject("?(id)", null, "java.lang.Long", "?(name)", "?(synonym)", "Отримати поточну глибину ієрархії документа '?(name)'");

        name = "Отримати поточну глибину ієрархії документа '?(name)'";
        description = "Функціональність, яка надає можливість отримувати глибину ієрархії усіх документів '?(name)'";
        outputs.add(new FeatureOutput(BosPlatformApplication.MAIN_MODULE_ID,
                    new BasicScope[]{ BasicScope.read },
                    true,
                    bOSObject,
                properties.getModuleUrl() + "rest/?(nameLowercase)/hierarchy/length",
                    HttpMethod.GET));
        moduleId = BosPlatformApplication.MAIN_MODULE_ID;
    }
}
