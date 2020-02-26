package com.degloba.person.query;

import com.degloba.person.aggregate.Person;
import com.degloba.persones.cqrs.querys.PersonByIdQueryResult;
import com.degloba.persones.cqrs.querys.PersonaByIdQuery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonQueryHandler {

    private final EntityManager entityManager;

    @QueryHandler
    public PersonByIdQueryResult handle(PersonaByIdQuery query){
        Person person = entityManager.find(Person.class, query.getPersonId());
        return new PersonByIdQueryResult(person.getId(), person.getAdrecaId(),
                person.getNomComplet());
    }

}
