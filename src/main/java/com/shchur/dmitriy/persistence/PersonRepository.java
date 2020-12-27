package com.shchur.dmitriy.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
     * @param startWith first record sequence number
     * @param count number of records
     * @return
     */
    public List<Person> findAll(int startWith, int count) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Person.class);
        return findByCriteria(criteria, startWith, count);
    }

    /**
     * Return count of Person records.
     *
     * @return count of records
     */
    public int count() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Person.class);
        return countByCriteria(criteria);
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
