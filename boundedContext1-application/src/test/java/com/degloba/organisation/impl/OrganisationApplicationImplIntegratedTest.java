package com.degloba.organisation.impl;

import java.util.Date;

import com.degloba.organisation.application.services.IOrganisationService;
import com.degloba.organisation.application.services.OrganisationApplicationImpl;
import com.degloba.organisation.domain.persistence.rdbms.jpa.IOrganisationRepository;
//Domain (organisation)
import com.degloba.organisation.domain.persistence.rdbms.jpa.Organization;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Party;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Post;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Ignore;


public class OrganisationApplicationImplIntegratedTest {

    private IOrganisationService instance;

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
        Organization orgToCreate = null;
        Organization parent = null;
        Date date = null;
        instance.createOrganization(orgToCreate, parent, date);
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
        Organization organization = null;
        Organization newParent = null;
        Date date = null;
        instance.changeParentOfOrganization(organization, newParent, date);
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
        Organization organization = null;
        Date date = null;
        instance.createPostUnderOrganization(post, organization, date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
