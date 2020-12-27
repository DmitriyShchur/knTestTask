package com.shchur.dmitriy.persistence;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.shchur.dmitriy.entity.Person;

/**
 * Repository for Person entities.
 *
 * @author Dmitriy Shchur
 */
@Repository
@Transactional
public class PersonRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public PersonRepository() {
    }

    /**
     * Return person list.
     *
     * @param name person name
     * @param startWith first record sequence number
     * @param count number of records
     * @return
     */
    public List<Person> findAll(Optional<String> name, int startWith, int count) {
        DetachedCriteria criteria = getPersonCriteria(name);
        return findByCriteria(criteria, startWith, count);
    }

    /**
     * Return count of Person records.
     *
     * @param name person name
     * @return count of records
     */
    public int count(Optional<String> name) {
        DetachedCriteria criteria = getPersonCriteria(name);
        return countByCriteria(criteria);
    }

    private DetachedCriteria getPersonCriteria(Optional<String> name) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Person.class);
        if (name.isPresent() && !StringUtils.isEmpty(name.get())) {
            criteria.add(Restrictions.like("name", name.get(), MatchMode.ANYWHERE).ignoreCase());
        }
        return criteria;
    }

    private int countByCriteria(DetachedCriteria criteria) {
        criteria.setProjection(Projections.rowCount());
        Criteria executableCriteria = criteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        return ((Number) executableCriteria.uniqueResult()).intValue();
    }

    private  List<Person> findByCriteria(DetachedCriteria criteria, int first, int count) {
        Criteria executableCriteria = criteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        if (first >= 0) {
            executableCriteria.setFirstResult(first);
        }
        if (count > 0) {
            executableCriteria.setMaxResults(count);
        }
        return executableCriteria.list();
    }
}
