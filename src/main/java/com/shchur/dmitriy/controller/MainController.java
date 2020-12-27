package com.shchur.dmitriy.controller;

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

    @RequestMapping(value = "/listPersons", method = RequestMethod.GET)
    public String listPersons(Model model,
        @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<PersonInfo> personsPage = personService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("personsPage", personsPage);

        int totalPages = personsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "personsPage.html";
    }
}
