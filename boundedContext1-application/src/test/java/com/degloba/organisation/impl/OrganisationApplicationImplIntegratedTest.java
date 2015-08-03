package com.degloba.organisation.impl;

import java.util.Date;

import com.degloba.domain.AbstractEntity;
import com.degloba.domain.EntityRepository;
import com.degloba.organisation.application.api.OrganisationService;
import com.degloba.organisation.application.impl.OrganisationApplicationImpl;
import com.degloba.organisation.domain.Organization;
import com.degloba.organisation.domain.Party;
import com.degloba.organisation.domain.Post;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Ignore;


public class OrganisationApplicationImplIntegratedTest {

    private OrganisationService instance;

    protected EntityRepository repository;

    @Before
    public void setUp() {
        repository = mock(EntityRepository.class);
        instance = new OrganisationApplicationImpl(repository);
        AbstractEntity.setRepository(repository);
    }

    @After
    public void tearDown() {
        AbstractEntity.setRepository(null);
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
