package com.csg.alexandr.builder.controller;

import com.csg.alexandr.builder.configurator.entity.*;
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
import java.util.*;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Value("${project.documentEntityPackage}")
    private String entityPackage = "";

    @Value("${project.documentExceptionPackage}")
    private String exceptionPackage = "";

    @Value("${project.documentRepositoryPackage}")
    private String repositoryPackage = "";

    @Value("${project.documentServicePackage}")
    private String servicePackage = "";

    @Value("${project.documentControllerPackage}")
    private String controllerPackage = "";

    @Value("${project.basePackage}")
    private String basePackage = "";

    @Value("${project.utilsPackage}")
    private String utilsPackage = "";

    @Value("${project.documentFeaturePackage}")
    private String featurePackage = "";

    @Value("${project.moduleEntityPackage}")
    private String moduleEntityPackage = "";

    @Value("${project.path}")
    private String projectPath = "";

    private List<Catalog> catalogs = new ArrayList<>();
    private List<Document> documents = new ArrayList<>();
    private MongoTemplate mongoTemplate;

    public DocumentController(MongoTemplate mongoTemplate) {
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
        return "document";
    }

    @PostMapping
    public String save(@ModelAttribute Document document, Model model) {
        Document documentFromDB = this.mongoTemplate.findById(document.id, Document.class);
        documentFromDB.presentationField = document.presentationField;
        System.out.println("Saving document: " + documentFromDB);
        writeTemplates(documentFromDB, document.name);

        model.addAttribute("thumbnails", this.thumbnails);
        model.addAttribute("catalogs", this.catalogs);
        model.addAttribute("documents", this.documents);
        model.addAttribute("selectedDocument", new Document());
        model.addAttribute("selectedCatalog", new Catalog());
        return "welcome";
    }

    private void writeTemplates(Document document, String name) {
        String entityTemplate = processFile("tpl/doc_entity.java", document, name);
        String exceptionTemplate = processFile("tpl/doc_exception.java", document, name);
        String repositoryTemplate = processFile("tpl/doc_repository.java", document, name);
        String serviceTemplate = processFile("tpl/doc_service.java", document, name);
        String serviceImplTemplate = processFile("tpl/doc_service_impl.java", document, name);
        String controllerTemplate = processFile("tpl/doc_controller.java", document, name);
        String featureCreateTemplate = processFile("tpl/doc_feature_create.java", document, name);
        String featureDeleteTemplate = processFile("tpl/doc_feature_delete.java", document, name);
        String featureFindAllTemplate = processFile("tpl/doc_feature_find_all.java", document, name);
        String featureFindOneTemplate = processFile("tpl/doc_feature_find_one.java", document, name);
        String featureUpdateTemplate = processFile("tpl/doc_feature_update.java", document, name);

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

        if (document.hierarchy.hierarchical) {
            String featureGetHierarchyLengthTemplate = processFile("tpl/doc_feature_get_hierarchy_length.java", document, name);
            System.out.println(featureGetHierarchyLengthTemplate);
            saveFile("featureGetHierarchyLength", featureGetHierarchyLengthTemplate, name);
        }

    }

    private String processFile(String sourceName, Document document, String name) {
        StringBuilder result = new StringBuilder("");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(sourceName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                line = replaceBasic(line);
                line = replaceAllPossibleResults(line, document, name);

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
                filename = foldername + "Create" + name + "DocumentsFeature.java";
                break;
            case "featureDelete":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "Delete" + name + "DocumentsFeature.java";
                break;
            case "featureUpdate":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "Update" + name + "DocumentsFeature.java";
                break;
            case "featureFindAll":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "FindAll" + name + "DocumentsFeature.java";
                break;
            case "featureFindOne":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "FindOne" + name + "DocumentsFeature.java";
                break;
            case "featureGetHierarchyLength":
                foldername = this.projectPath + "/src/main/java/" + featurePackage.replaceAll("\\.", "/") + "/" + name + "/";
                directory = new File(foldername);
                if (!directory.exists()) { try { directory.mkdir(); } catch(SecurityException se) {} }
                filename = foldername + "GetHierarchyLength" + name + "DocumentsFeature.java";
                break;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String replaceAllPossibleResults(String line, Document document, String name) {
        if (line.contains("?(dbName)"))
            line = line.replaceAll("\\?\\(dbName\\)", document.name.toLowerCase());

        if (line.contains("?(name)"))
            line = line.replaceAll("\\?\\(name\\)", name);

        if (line.contains("?(nameCamelcase)"))
            line = line.replaceAll("\\?\\(name\\)", toCamelCase(name));

        if (line.contains("?(nameLowercase)"))
            line = line.replaceAll("\\?\\(nameLowercase\\)", name.toLowerCase());

        if (line.contains("?(synonym)"))
            line = line.replaceAll("\\?\\(synonym\\)", document.synonym);

        if (line.contains("?(id)"))
            line = line.replaceAll("\\?\\(id\\)", document.id);

        if (line.contains("?(showName)"))
            line = line.replaceAll("\\?\\(showName\\)", String.valueOf(document.showName));

        if (line.contains("?(showCode)"))
            line = line.replaceAll("\\?\\(showCode\\)", String.valueOf(document.code.showCode));

        if (line.contains("?(objectPresentation)"))
            line = line.replaceAll("\\?\\(objectPresentation\\)", String.valueOf(document.objectPresentation));

        if (line.contains("?(objectPresentationCamelcase)"))
            line = line.replaceAll("\\?\\(objectPresentationCamelcase\\)", toCamelCase(document.objectPresentation));

        if (line.contains("?(listPresentation)"))
            line = line.replaceAll("\\?\\(listPresentation\\)", String.valueOf(document.listPresentation));

        if (line.contains("?(presentationField)"))
            line = line.replaceAll("\\?\\(presentationField\\)", String.valueOf(document.presentationField));

        if (line.contains("?(uniqueness)"))
            line = line.replaceAll("\\?\\(uniqueness\\)", String.valueOf(document.uniqueness));

        if (line.contains("?(nameLength)"))
            line = line.replaceAll("\\?\\(nameLength\\)", String.valueOf(document.nameLength));

        if (line.contains("?(hierarchy)"))
            line = line.replaceAll("\\?\\(hierarchy\\)", hierarchyToString(document.hierarchy));

        if (line.contains("?(requisites)"))
            line = line.replace("?(requisites)", requisitesToString(document.requisites));

        if (line.contains("?(tableParts)"))
            line = line.replace("?(tableParts)", tablePartsToString(document.tableParts));

//        if (line.contains("?(predefined)"))
//            line = line.replace("?(predefined)", predefinedToString(document.));

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
                StringBuilder reqSb = new StringBuilder("Arrays.asList(\n\n\t\t\t\t");
                int j = 1;
                for (Requisite r : tp.requisites) {
                    String tmp = String.format("new Requisite(\"%s\", \"%s\", \"%s\", new Type(TypeName.%s, %s, %s, %s, %s, %s%s), %s, %s, %s, \"%s\")%s", r.id, r.name, r.synonym, r.type.name, r.type.length, r.type.unlimited, r.type.accuracy, r.type.nonnegative, (r.type.name.equals(TypeName.Date)?r.type.dateComposition:null), (r.type.links != null && r.type.links.size() > 0 ? ", Collections.singletonList(\""+r.type.links.get(0)+"\")" : ""), r.required, r.passwordMode, r.standard, (r.value == null?"":r.value), (j == tp.requisites.size()?"\n\t\t\t))":","));
                    reqSb.append(tmp + (j==tp.requisites.size()?"\n":"\n\n\t\t\t"));

                    j++;
                }

                tpTmp += reqSb.toString();
            } else tpTmp += "new ArrayList())";

            tpTmp += (i == tableParts.size() ? ");" : ",\n");

            result.append(tpTmp);

            i++;
        }

        return result.toString();
    }

    private String hierarchyToString(Hierarchy hierarchy) {
        if (hierarchy == null) {
            return "hierarchy = null;";
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format("hierarchy.hierarchical = %s;\n", hierarchy.hierarchical));
        result.append(String.format("hierarchy.limitLevelCount = %s;\n", hierarchy.limitLevelCount));
        result.append(String.format("hierarchy.levelCount = %s;\n", hierarchy.levelCount));

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
