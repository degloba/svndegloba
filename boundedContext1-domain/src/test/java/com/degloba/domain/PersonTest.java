package com.degloba.domain;


import org.junit.Before;
import org.junit.Test;

import com.degloba.organisation.domain.persistence.rdbms.jpa.ImType;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Person;
import com.degloba.rent.domain.persistence.rdbms.jpa.IRentRepository;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonTest extends AbstractIntegrationTest {

    private Person person1;
    private Person person2;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        person1 = new Person("a", "b");
        person1.setIdNumber("p1");
        person1.setIm(ImType.QQ, "349591542");
        person1.setIm(ImType.MSN, "cnyangyu@hotmail.com");
        person1.save();

        person2 = new Person("a", "c");
        person2.setIdNumber("p2");
        person2.setIm(ImType.QQ, "666666");
        person2.setIm(ImType.MSN, "degloba@degloba.com");
        person2.save();
    }

    @Test
    public void testGetIms() {
        String jpql = "select o from Person o join o.ims i where KEY(i) = :imType and i = :im";
        IRentRepository repository = InstanceFactory.getInstance(IRentRepository.class);
        List<Person> persons = repository.createJpqlQuery(jpql)
                .addParameter("imType", ImType.QQ).addParameter("im", "666666").list();
/*        assertFalse(persons.contains(person1));
        assertTrue(persons.contains(person2));*/
    }

}
