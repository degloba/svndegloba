package com.degloba.organisation.impl;

import java.util.Date;

import com.degloba.organisation.application.services.IOrganitzacioService;
import com.degloba.organisation.application.services.OrganisationApplicationImpl;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.IOrganisationRepository;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Organitzacio;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Party;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Post;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Ignore;


public class OrganisationApplicationImplIntegratedTest {

    private IOrganitzacioService instance;

    protected IOrganisationRepository repository;

    @Before
    public void setUp() {
        repository = mock(IOrganisationRepository.class);
        instance = new OrganisationApplicationImpl(repository);
        BaseAggregateRoot.setRepository(repository);
    }

    @After
    public void tearDown() {
    	BaseAggregateRoot.setRepository(null);
    }

    /**
     * Test of createOrganization method, of class OrganisationApplicationImpl.
     */
    @Ignore
    @Test
    public void testCreateOrganization() {
        System.out.println("createOrganization");
        Organitzacio orgToCreate = null;
        Organitzacio parent = null;
        Date date = null;
        instance.creaOrganitzacio(orgToCreate, parent, date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of terminateParty method, of class OrganisationApplicationImpl.
     */
    @Ignore
    @Test
    public void testTerminateParty() {
        System.out.println("terminateParty");
        Party party = null;
        Date date = null;
        instance.terminateParty(party, date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeParentOfOrganization method, of class OrganisationApplicationImpl.
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
     * Test of createPostUnderOrganization method, of class OrganisationApplicationImpl.
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
    
}
