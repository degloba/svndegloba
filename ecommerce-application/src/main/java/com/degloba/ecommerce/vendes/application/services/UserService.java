package com.degloba.ecommerce.vendes.application.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.degloba.domain.specifications.SearchCriteria;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.User;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.UserSearchQueryCriteriaConsumer;



@Repository
public class UserService implements IUserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> searchUser(List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> query = builder.createQuery(User.class);
        final Root r = query.from(User.class);

        Predicate predicate = builder.conjunction();
        UserSearchQueryCriteriaConsumer searchConsumer = new UserSearchQueryCriteriaConsumer(predicate, builder, r);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(final User entity) {
        entityManager.persist(entity);
    }



}
