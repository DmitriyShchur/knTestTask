package com.shchur.dmitriy.form;

/**
 * Search by name form.
 *
 * @author Dmitriy Shchur
 */
public class SearchForm {

    private String searchString;

    public SearchForm() {
    }

    public SearchForm(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
