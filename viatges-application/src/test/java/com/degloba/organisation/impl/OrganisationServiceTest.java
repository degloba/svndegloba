package com.degloba.organisation.impl;

import java.util.Date;

// Domain


import com.degloba.utils.DateUtils;
import com.degloba.organisation.application.services.IOrganitzacioService;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Company;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Department;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.IOrganisationRepository;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Organitzacio;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Party;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Post;

import static org.hamcrest.CoreMatchers.*;

import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;


public abstract class OrganisationServiceTest {

    private IOrganitzacioService instance;

    protected IOrganisationRepository repository;

    @Before
    public void setUp() {
        instance = createInstance();
        repository = mock(IOrganisationRepository.class);
        //AbstractEntity.setRepository(repository);
    }

    @After
    public void tearDown() {
        //AbstractEntity.setRepository(null);
    }

    /**
     * Test of createOrganization method, of class OrganisationApplication.
     */
    @Test
    public void testCreateOrganization() {
        System.out.println("createOrganization");
        Organitzacio orgToCreate = new Department("dept");
        Organitzacio parent = new Company("headquarter");
        Date date = DateUtils.date(2012, 1, 1);
        instance.creaOrganitzacio(orgToCreate, parent, date);
        assertThat(orgToCreate.getCreateDate(), is(date));
/*        verify(repository).save(orgToCreate);
        verify(repository).save(new OrgLineMgmt(parent, orgToCreate, date));*/
    }

    /**
     * Test of terminateParty method, of class OrganisationApplication.
     */
    @Ignore
    @Test
    public void testTerminateParty() {
        System.out.println("terminateParty");
        Party party = mock(Party.class);
        Date date = DateUtils.date(2012, 1, 1);
        instance.terminateParty(party, date);
        verify(party).terminate(date);
    }

    /**
     * Test of changeParentOfOrganization method, of class
     * OrganisationApplication.
     */
    @Ignore
    @Test
    public void testChangeParentOfOrganization() {
        System.out.println("changeParentOfOrganization");
        Organitzacio organitzacio = null;
        Organitzacio newParent = null;
        Date date = null;
        instance.changeParentOfOrganization(organitzacio, newParent, date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPostUnderOrganization method, of class
     * OrganisationApplication.
     */
    @Ignore
    @Test
    public void testCreatePostUnderOrganization() {
        System.out.println("createPostUnderOrganization");
        Post post = null;
        Organitzacio organitzacio = null;
        Date date = null;
        instance.createPostUnderOrganization(post, organitzacio, date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    protected abstract IOrganitzacioService createInstance();

}
