package com.shchur.dmitriy.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shchur.dmitriy.entity.Person;
import com.shchur.dmitriy.model.PersonInfo;
import com.shchur.dmitriy.persistence.PersonRepository;

/**
 * Service for Person objects.
 *
 * @author Dmitriy Shchur
 */
@Service
public class PersonService {

    private Environment env;

    private PersonRepository personRepository;

    private String photosHostAddress;

    @Autowired
    public PersonService(Environment env, PersonRepository personRepository) {
        this.env = env;
        this.personRepository = personRepository;
        this.photosHostAddress = env.getProperty("photos.host.address");
    }

    /**
     * Return Page with Person objects according pagination information.
     *
     * @param pageable pagination information
     * @return page with Person objects
     */
    public Page<PersonInfo> findPaginated(Pageable pageable) {
        return findPaginated(Optional.empty(), pageable);
    }

    /**
     * Return Page with Person objects according pagination information and match search string in name.
     *
     * @param searchString search string
     * @param pageable pagination information
     * @return page with Person objects
     */
    public Page<PersonInfo> findPaginated(String searchString, Pageable pageable) {
        return findPaginated(Optional.of(searchString), pageable);
    }

    private Page<PersonInfo> findPaginated(Optional<String> searchString, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        int count = personRepository.count(searchString);

        List<Person> list = count < startItem
                ? Collections.emptyList()
                : personRepository.findAll(searchString, startItem, pageSize);

        return new PageImpl<>(toPersonInfoList(list), PageRequest.of(currentPage, pageSize), count);
    }

    private List<PersonInfo> toPersonInfoList(List<Person> person) {
        return person.stream()
                .map(this::toPersonInfo)
                .collect(Collectors.toList());
    }

    private PersonInfo toPersonInfo(Person person) {
        return new PersonInfo(person.getId(), person.getName(), photosHostAddress + person.getPhotoPath());
    }
}
