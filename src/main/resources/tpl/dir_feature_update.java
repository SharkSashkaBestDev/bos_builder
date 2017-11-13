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
public class Update?(name)DirectoriesFeature extends Feature {

    private final CommonApplicationProperties properties;
    private final ?(name)Service service;

    @Autowired
    public Update?(name)DirectoriesFeature(CommonApplicationProperties properties, ?(name)Service service) {
        this.properties = properties;
        this.service = service;
    }

    @PostConstruct
    private void init() throws ClassNotFoundException {
        BOSObject CurrencyBOSObject = new BOSObject("?(id)", null, "com.csg.alexandr.bos.configurator.entity.Catalog", "?(name)", "?(synonym)", "Оновити існуючий довідник '?(name)'");

        name = "Оновити існуючий довідник '?(name)'";
        description = "Функціональність, яка надає можливість оновлювати існуючі довідники '?(name)'";
        outputs.add(new FeatureOutput(BosPlatformApplication.MAIN_MODULE_ID,
                    new BasicScope[]{ BasicScope.rewrite },
                    true,
                    CurrencyBOSObject,
                properties.getModuleUrl() + "rest/?(nameLowercase)",
                    HttpMethod.PUT));
        moduleId = BosPlatformApplication.MAIN_MODULE_ID;
    }
}
