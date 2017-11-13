package com.csg.alexandr.builder.controller;

import com.csg.alexandr.builder.configurator.entity.Catalog;
import com.csg.alexandr.builder.configurator.entity.Document;
import com.csg.alexandr.builder.configurator.entity.Requisite;
import com.csg.alexandr.builder.configurator.entity.TablePart;
import com.csg.alexandr.builder.configurator.entity.enums.TypeName;
import com.csg.alexandr.builder.entity.Thumbnail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("/directory")
public class DirectoryController {

    @Value("${project.directoryEntityPackage}")
    private String entityPackage = "";

    @Value("${project.directoryExceptionPackage}")
    private String exceptionPackage = "";

    @Value("${project.directoryRepositoryPackage}")
    private String repositoryPackage = "";

    @Value("${project.directoryServicePackage}")
    private String servicePackage = "";

    @Value("${project.directoryControllerPackage}")
    private String controllerPackage = "";

    @Value("${project.basePackage}")
    private String basePackage = "";

    @Value("${project.utilsPackage}")
    private String utilsPackage = "";

    @Value("${project.directoryFeaturePackage}")
    private String featurePackage = "";

    @Value("${project.moduleEntityPackage}")
    private String moduleEntityPackage = "";

    @Value("${project.path}")
    private String projectPath = "";

    private List<Catalog> catalogs = new ArrayList<>();
    private List<Document> documents = new ArrayList<>();
    private MongoTemplate mongoTemplate;

    public DirectoryController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

        catalogs.addAll(this.mongoTemplate.findAll(Catalog.class));
        documents.addAll(this.mongoTemplate.findAll(Document.class));
    }

    private List<Thumbnail> thumbnails = Arrays.asList(
            new Thumbnail("Довідник", "Довідник", "img/directory.ico", "Зображення довідника", "/directory"),
            new Thumbnail("Документ", "Документ", "img/document.png", "Зображення документа", "/document"),
            new Thumbnail("Константа", "Константа", "img/constant.svg", "Зображення константи", "/constant")
    );

    @GetMapping
    public String get(Model model) {
        return "directory";
    }

    @PostMapping
    public String save(@ModelAttribute Catalog catalog, Model model) {
        Catalog catalogFromDB = this.mongoTemplate.findById(catalog.id, Catalog.class);
        catalogFromDB.presentationField = catalog.presentationField;
        System.out.println("Saving catalog: " + catalogFromDB);
        writeTemplates(catalogFromDB, catalog.name);

        model.addAttribute("thumbnails", this.thumbnails);
        model.addAttribute("catalogs", this.catalogs);
        model.addAttribute("documents", this.documents);
        model.addAttribute("selectedCatalog", new Catalog());
        model.addAttribute("selectedDocument", new Document());
        return "welcome";
    }

    private void writeTemplates(Catalog catalog, String name) {
        String entityTemplate = processFile("tpl/dir_entity.java", catalog, name);
        String exceptionTemplate = processFile("tpl/dir_exception.java", catalog, name);
        String repositoryTemplate = processFile("tpl/dir_repository.java", catalog, name);
        String serviceTemplate = processFile("tpl/dir_service.java", catalog, name);
        String serviceImplTemplate = processFile("tpl/dir_service_impl.java", catalog, name);
        String controllerTemplate = processFile("tpl/dir_controller.java", catalog, name);
        String featureCreateTemplate = processFile("tpl/dir_feature_create.java", catalog, name);
        String featureDeleteTemplate = processFile("tpl/dir_feature_delete.java", catalog, name);
        String featureFindAllTemplate = processFile("tpl/dir_feature_find_all.java", catalog, name);
        String featureFindOneTemplate = processFile("tpl/dir_feature_find_one.java", catalog, name);
        String featureUpdateTemplate = processFile("tpl/dir_feature_update.java", catalog, name);

        System.out.println(entityTemplate);
        System.out.println(exceptionTemplate);
        System.out.println(repositoryTemplate);
        System.out.println(serviceTemplate);
        System.out.println(serviceImplTemplate);
        System.out.println(controllerTemplate);
        System.out.println(featureCreateTemplate);
        System.out.println(featureDeleteTemplate);
        System.out.println(featureFindAllTemplate);
        System.out.println(featureFindOneTemplate);
        System.out.println(featureUpdateTemplate);

        saveFile("entity", entityTemplate, name);
        saveFile("exception", exceptionTemplate, name);
        saveFile("repository", repositoryTemplate, name);
        saveFile("service", serviceTemplate, name);
        saveFile("serviceImpl", serviceImplTemplate, name);
        saveFile("controller", controllerTemplate, name);
        saveFile("featureCreate", featureCreateTemplate, name);
        saveFile("featureDelete", featureDeleteTemplate, name);
        saveFile("featureFindAll", featureFindAllTemplate, name);
        saveFile("featureFindOne", featureFindOneTemplate, name);
        saveFile("featureUpdate", featureUpdateTemplate, name);

        if (catalog.hierarchy.hierarchical) {
            String featureGetHierarchyLengthTemplate = processFile("tpl/dir_feature_get_hierarchy_length.java", catalog, name);
            System.out.println(featureGetHierarchyLengthTemplate);
            saveFile("featureGetHierarchyLength", featureGetHierarchyLengthTemplate, name);
        }

    }

    private String processFile(String sourceName, Catalog catalog, String name) {
        StringBuilder result = new StringBuilder("");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(sourceName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                line = replaceBasic(line);
                line = replaceAllPossibleResults(line, catalog, name);

                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    private void saveFile(String type, String template, String name) {
        String foldername = "", filename = "";
        File directory = null;
        switch (type) {
            case "entity":
                foldername = this.projectPath + "/src/main/java/" + entityPackage.replaceAll("\\.", "/") + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + name + ".java";
                break;
            case "exception":
                foldername = this.projectPath + "/src/main/java/" + exceptionPackage.replaceAll("\\.", "/") + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + name + "NotFoundException.java";
                break;
            case "repository":
                foldername = this.projectPath + "/src/main/java/" + repositoryPackage.replaceAll("\\.", "/") + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + name + "Repository.java";
                break;
            case "service":
                foldername = this.projectPath + "/src/main/java/" + servicePackage.replaceAll("\\.", "/") + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + name + "Service.java";
                break;
            case "serviceImpl":
                foldername = this.projectPath + "/src/main/java/" + servicePackage.replaceAll("\\.", "/") + "/impl/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + name + "ServiceImpl.java";
                break;
            case "controller":
                foldername = this.projectPath + "/src/main/java/" + controllerPackage.replaceAll("\\.", "/") + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + name + "RestController.java";
                break;
            case "featureCreate":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "Create" + name + "DirectoriesFeature.java";
                break;
            case "featureDelete":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "Delete" + name + "DirectoriesFeature.java";
                break;
            case "featureUpdate":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "Update" + name + "DirectoriesFeature.java";
                break;
            case "featureFindAll":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "FindAll" + name + "DirectoriesFeature.java";
                break;
            case "featureFindOne":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "FindOne" + name + "DirectoriesFeature.java";
                break;
            case "featureGetHierarchyLength":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "GetHierarchyLength" + name + "DirectoriesFeature.java";
                break;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String replaceAllPossibleResults(String line, Catalog catalog, String name) {
        if (line.contains("?(dbName)"))
            line = line.replaceAll("\\?\\(dbName\\)", catalog.name.toLowerCase());

        if (line.contains("?(name)"))
            line = line.replaceAll("\\?\\(name\\)", name);

        if (line.contains("?(nameCamelcase)"))
            line = line.replaceAll("\\?\\(name\\)", toCamelCase(name));

        if (line.contains("?(nameLowercase)"))
            line = line.replaceAll("\\?\\(nameLowercase\\)", name.toLowerCase());

        if (line.contains("?(synonym)"))
            line = line.replaceAll("\\?\\(synonym\\)", catalog.synonym);

        if (line.contains("?(id)"))
            line = line.replaceAll("\\?\\(id\\)", catalog.id);

        if (line.contains("?(showName)"))
            line = line.replaceAll("\\?\\(showName\\)", String.valueOf(catalog.showName));

        if (line.contains("?(showCode)"))
            line = line.replaceAll("\\?\\(showCode\\)", String.valueOf(catalog.code.showCode));

        if (line.contains("?(objectPresentation)"))
            line = line.replaceAll("\\?\\(objectPresentation\\)", String.valueOf(catalog.objectPresentation));

        if (line.contains("?(objectPresentationCamelcase)"))
            line = line.replaceAll("\\?\\(objectPresentationCamelcase\\)", toCamelCase(catalog.objectPresentation));

        if (line.contains("?(listPresentation)"))
            line = line.replaceAll("\\?\\(listPresentation\\)", String.valueOf(catalog.listPresentation));

        if (line.contains("?(presentationField)"))
            line = line.replaceAll("\\?\\(presentationField\\)", String.valueOf(catalog.presentationField));

        if (line.contains("?(uniqueness)"))
            line = line.replaceAll("\\?\\(uniqueness\\)", String.valueOf(catalog.uniqueness));

        if (line.contains("?(nameLength)"))
            line = line.replaceAll("\\?\\(nameLength\\)", String.valueOf(catalog.nameLength));

        if (line.contains("?(hierarchical)"))
            line = line.replaceAll("\\?\\(hierarchical\\)", String.valueOf(catalog.hierarchy.hierarchical));

        if (line.contains("?(limitLevelCount)"))
            line = line.replaceAll("\\?\\(limitLevelCount\\)", String.valueOf(catalog.hierarchy.limitLevelCount));

        if (line.contains("?(levelCount)"))
            line = line.replaceAll("\\?\\(levelCount\\)", String.valueOf(catalog.hierarchy.levelCount));

        if (line.contains("?(requisites)"))
            line = line.replace("?(requisites)", requisitesToString(catalog.requisites));

        if (line.contains("?(tableParts)"))
            line = line.replace("?(tableParts)", tablePartsToString(catalog.tableParts));

//        if (line.contains("?(predefined)"))
//            line = line.replace("?(predefined)", predefinedToString(catalog.));

        return line;
    }

    private String toCamelCase(String source) {
        String[] parts = source.split(" ");
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    private String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }

    private String toPretty(String s) {
        StringBuilder result = new StringBuilder();
        String[] r = s.split("(?=\\p{Lu})");
        for (String str : r) {
            result.append(str + " ");
        }
        return result.toString();
    }

    private String requisitesToString(List<Requisite> requisites) {
        StringBuilder result = new StringBuilder();

        if (requisites.size() == 0) {
            result.append("requisites = new ArrayList();");
            return result.toString();
        }

        result.append("requisites = Arrays.asList(\n\t\t\t");
        int i = 1;
        for (Requisite r : requisites) {
            String tmp = String.format("new Requisite(\"%s\", \"%s\", \"%s\", new Type(TypeName.%s, %s, %s, %s, %s, %s%s), %s, %s, %s, \"%s\")%s", r.id, r.name, r.synonym, r.type.name, r.type.length, r.type.unlimited, r.type.accuracy, r.type.nonnegative, (r.type.name.equals(TypeName.Date)?r.type.dateComposition:null), (r.type.links != null && r.type.links.size() > 0 ? ", Collections.singletonList(\""+r.type.links.get(0)+"\")" : ""), r.required, r.passwordMode, r.standard, (r.value == null?"":r.value), (i == requisites.size()?"\n\t\t);":","));
            result.append(tmp + (i==requisites.size()?"\n":"\n\n\t\t\t"));

            i++;
        }

        return result.toString();
    }

    private String tablePartsToString(List<TablePart> tableParts) {
        StringBuilder result = new StringBuilder();

        if (tableParts.size() == 0) {
            result.append("tableParts = new ArrayList();");
            return result.toString();
        }

        result.append("tableParts = Arrays.asList(\n\t\t\t");
        int i = 1;
        for (TablePart tp : tableParts) {
            String tpTmp = String.format("new TablePart(\"%s\", \"%s\", ", tp.name, tp.synonym);

            if (tp.requisites != null && tp.requisites.size() > 0) {
                StringBuilder reqSb = new StringBuilder("Arrays.asList(\n");
                int j = 1;
                for (Requisite r : tp.requisites) {
                    String tmp = String.format("new Requisite(\"%s\", \"%s\", \"%s\", new Type(TypeName.%s, %s, %s, %s, %s, %s%s), %s, %s, %s, \"%s\")%s", r.id, r.name, r.synonym, r.type.name, r.type.length, r.type.unlimited, r.type.accuracy, r.type.nonnegative, (r.type.name.equals(TypeName.Date)?r.type.dateComposition:null), (r.type.links != null && r.type.links.size() > 0 ? ", Collections.singletonList(\""+r.type.links.get(0)+"\")" : ""), r.required, r.passwordMode, r.standard, (r.value == null?"":r.value), (j == tp.requisites.size()?"\n\t\t);":","));
                    reqSb.append(tmp + (j==tp.requisites.size()?"\n":"\n\n\t\t\t"));

                    j++;
                }

                tpTmp += reqSb.toString();
            } else tpTmp += "new ArrayList())";

            tpTmp += (i == tableParts.size() ? "\n);" : ",\n");

            result.append(tpTmp);

            i++;
        }

        return result.toString();
    }

    private String replaceBasic(String line) {
        if (line.contains("?(entityPackage)"))
            line = line.replaceAll("\\?\\(entityPackage\\)", entityPackage);

        if (line.contains("?(exceptionPackage)"))
            line = line.replaceAll("\\?\\(exceptionPackage\\)", exceptionPackage);

        if (line.contains("?(repositoryPackage)"))
            line = line.replaceAll("\\?\\(repositoryPackage\\)", repositoryPackage);

        if (line.contains("?(servicePackage)"))
            line = line.replaceAll("\\?\\(servicePackage\\)", servicePackage);

        if (line.contains("?(controllerPackage)"))
            line = line.replaceAll("\\?\\(controllerPackage\\)", controllerPackage);

        if (line.contains("?(basePackage)"))
            line = line.replaceAll("\\?\\(basePackage\\)", basePackage);

        if (line.contains("?(utilsPackage)"))
            line = line.replaceAll("\\?\\(utilsPackage\\)", utilsPackage);

        if (line.contains("?(moduleEntityPackage)"))
            line = line.replaceAll("\\?\\(moduleEntityPackage\\)", moduleEntityPackage);

        if (line.contains("?(featurePackage)"))
            line = line.replaceAll("\\?\\(featurePackage\\)", featurePackage);

        return line;
    }
}
