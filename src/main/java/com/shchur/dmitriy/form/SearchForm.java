package com.shchur.dmitriy.form;

/**
 * Search by name form.
 *
 * @author Dmitriy Shchur
 */
public class SearchForm {

    private String searchString;

    private int pageSize;

    public SearchForm() {
    }

    public SearchForm(String searchString, int pageSize) {
        this.searchString = searchString;
        this.pageSize = pageSize;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
