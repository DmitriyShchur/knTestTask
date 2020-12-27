package com.shchur.dmitriy.model;

/**
 * Person model.
 *
 * @author Dmitriy Shchur
 */
public class PersonInfo {

    /**
     * Identifier.
     */
    private Long id;

    /**
     * Name.
     */
    private String name;

    /**
     * Photo url.
     */
    private String photo_url;

    public PersonInfo(Long id, String name, String photo_url) {
        this.id = id;
        this.name = name;
        this.photo_url = photo_url;
    }

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

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
