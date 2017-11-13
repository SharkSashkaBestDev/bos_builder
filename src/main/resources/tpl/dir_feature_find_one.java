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
public class FindOne?(name)DirectoriesFeature extends Feature {

    private final CommonApplicationProperties properties;

    @Autowired
    public FindOne?(name)DirectoriesFeature(CommonApplicationProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    private void init() throws ClassNotFoundException {
        BOSObject bOSObject = new BOSObject("?(id)", null, "com.csg.alexandr.bos.configurator.entity.Catalog", "?(name)", "?(synonym)", "Відомості про довідник '?(name)'");

        name = "Відомості про довідник '?(name)'";
        description = "Функціональність, яка надає можливість отримувати довідник '?(name)' по його унікальному ідентифікатору";
        outputs.add(new FeatureOutput(BosPlatformApplication.MAIN_MODULE_ID,
                    new BasicScope[]{ BasicScope.read },
                    true,
                    bOSObject,
                properties.getModuleUrl() + "rest/?(nameLowercase)/",
                    HttpMethod.GET));
        moduleId = BosPlatformApplication.MAIN_MODULE_ID;
    }
}
