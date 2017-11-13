package com.csg.alexandr.builder.configurator.entity;

import java.io.Serializable;

/**
 * Клас, який відображає загальну спрощену
 * форму будь-якого об'єкта метаданих.
 *
 * @author Мягкий Олександр
 * @version 0.1
 * @see Serializable
 */
public class MetadataObjectForm implements Serializable {

    /** Поле для зберігання унікального ідентифікатора кожного об'єкта метаданих */
    public String id;
    /** Поле для зберігання назви кожного об'єкта метаданих */
    public String name;
    /** Поле для зберігання синоніма кожного об'єкта метаданих */
    public String synonym;
    /** Поле для зберігання коментарю кожного об'єкта метаданих */
    public String comment;
    /** Поле для зберігання url адреси кожного об'єкта метаданих */
    public String url;
    /**
     * Поле для зберігання унікального ідентифікатора
     * батьківського модуля кожного об'єкта метаданих
     */
    public String moduleId;

    /**
     * Конструктор.
     */
    public MetadataObjectForm() {}

}
