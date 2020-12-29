package com.shchur.dmitriy.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shchur.dmitriy.form.SearchForm;
import com.shchur.dmitriy.model.PersonInfo;
import com.shchur.dmitriy.service.PersonService;

/**
 * Main controller with request mapping.
 *
 * @author Dmitriy Shchur
 */
@Controller
public class MainController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloPage() {
        return "helloPage.html";
    }

    @RequestMapping(value = "/listPersons", method = RequestMethod.GET)
    public String listPersons(Model model, @RequestParam("page") Optional<Integer> page,
        @RequestParam("size") Optional<Integer> size, @RequestParam("searchString") Optional<String> searchString) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        String searchStringValue = searchString.orElse("");

        Page<PersonInfo> personsPage = searchString.isPresent()
                ? personService.findPaginated(searchStringValue, PageRequest.of(currentPage - 1, pageSize))
                : personService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        SearchForm searchForm = new SearchForm(searchStringValue, pageSize);
        fillModelWithAttributes(model, searchForm, personsPage);
        return "personsPage.html";
    }

    @RequestMapping(value = "/listPersons", method = RequestMethod.POST)
    public String processListPersons(Model model, SearchForm searchForm) {
        String searchStringValue = searchForm.getSearchString();
        int pageSize = searchForm.getPageSize();
        Page<PersonInfo> personsPage = personService.findPaginated(searchStringValue, PageRequest.of(0, pageSize));

        fillModelWithAttributes(model, searchForm, personsPage);
        return "personsPage.html";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage() {
        return "error.html";
    }

    private void fillModelWithAttributes(Model model, SearchForm searchForm, Page<PersonInfo> personsPage) {
        model.addAttribute("searchForm", searchForm);

        model.addAttribute("personsPage", personsPage);

        model.addAttribute("pageNumbers", getPageNumbers(personsPage));
    }

    private List<Integer> getPageNumbers(Page<PersonInfo> personsPage) {
        int totalPages = personsPage.getTotalPages();

        if (totalPages > 0) {
            return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
        }
        else {
            return Collections.emptyList();
        }
    }
}
