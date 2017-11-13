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
public class Create?(name)DocumentsFeature extends Feature {

    private final CommonApplicationProperties properties;
    private final ?(name)Service service;

    @Autowired
    public Create?(name)DocumentsFeature(CommonApplicationProperties properties, ?(name)Service service) {
        this.properties = properties;
        this.service = service;
    }

    @PostConstruct
    private void init() throws ClassNotFoundException {
        BOSObject bOSObject = new BOSObject("?(id)", null, "com.csg.alexandr.bos.configurator.entity.Document", "?(name)", "?(synonym)", "Створити новий документ '?(name)'");

        name = "Створити новий документ '?(name)'";
        description = "Функціональність, яка надає можливість створювати нові документи '?(name)'";
        outputs.add(new FeatureOutput(BosPlatformApplication.MAIN_MODULE_ID,
                    new BasicScope[]{ BasicScope.append },
                    true,
                    bOSObject,
                properties.getModuleUrl() + "rest/?(nameLowercase)",
                    HttpMethod.POST));
        moduleId = BosPlatformApplication.MAIN_MODULE_ID;
    }
}
