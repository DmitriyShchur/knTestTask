package com.shchur.dmitriy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Person entity.
 *
 * @author Dmitriy Shchur
 */
@Entity
@Table(name = "person")
public class Person {

    /**
     * Identifier.
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Person name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Path to person photo. This path is relative to the photos host address.
     */
    @Column(name = "photo_path")
    private String photoPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
