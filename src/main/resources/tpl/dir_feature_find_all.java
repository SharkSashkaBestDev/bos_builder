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
public class FindAll?(name)DirectoriesFeature extends Feature {

    private final CommonApplicationProperties properties;
    private final ?(name)Service service;

    @Autowired
    public FindAll?(name)DirectoriesFeature(CommonApplicationProperties properties, ?(name)Service service) {
        this.properties = properties;
        this.service = service;
    }

    @PostConstruct
    private void init() throws ClassNotFoundException {
        BOSObject bOSObject = new BOSObject("?(id)", null, "java.util.List", "?(name)", "?(synonym)", "Відомості про усі довідники '?(name)'");

        name = "Відомості про усі довідники '?(name)'";
        description = "Функціональність, яка надає можливість отримувати список усіх довідників '?(name)'";
        outputs.add(new FeatureOutput(BosPlatformApplication.MAIN_MODULE_ID,
                    new BasicScope[]{ BasicScope.read },
                    true,
                    bOSObject,
                properties.getModuleUrl() + "rest/?(nameLowercase)",
                    HttpMethod.GET));
        moduleId = BosPlatformApplication.MAIN_MODULE_ID;
    }

    @Override
    public void run() {
        outputs.get(0).bosObject.data = this.service.findAll();
    }
}
