package com.csg.alexandr.builder.controller;

import com.csg.alexandr.builder.configurator.entity.Catalog;
import com.csg.alexandr.builder.configurator.entity.Document;
import com.csg.alexandr.builder.entity.Thumbnail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class WelcomeController {

    @Value("${project.path}")
    private String projectPath = "~/Desktop/tmp";

    private MongoTemplate mongoTemplate;

    private List<Catalog> catalogs = new ArrayList<>();
    private List<Document> documents = new ArrayList<>();

    public WelcomeController(MongoTemplate mongoTemplate) {
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
    public String welcome(Model model) {
        model.addAttribute("projectPath", this.projectPath);
        model.addAttribute("thumbnails", this.thumbnails);
        model.addAttribute("catalogs", this.catalogs);
        model.addAttribute("documents", this.documents);
        model.addAttribute("selectedCatalog", new Catalog());
        model.addAttribute("selectedDocument", new Document());
        return "welcome";
    }

}
