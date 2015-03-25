package com.degloba.organisation.application.impl;

import com.degloba.organisation.application.OrganisationApplication;
import com.degloba.organisation.domain.OrgLineMgmt;
import com.degloba.organisation.domain.Organization;
import com.degloba.organisation.domain.Party;
import com.degloba.organisation.domain.Post;

import java.util.Date;
import javax.inject.Inject;

import com.degloba.domain.EntityRepository;

public class OrganisationApplicationImpl implements OrganisationApplication {

    @Inject
    private EntityRepository repository;

    public OrganisationApplicationImpl(EntityRepository repository) {
        this.repository = repository;
    }
    
    
    @Override
    public <T extends com.degloba.domain.seedwork.Entity> T getEntity(Class<T> entityClass, Long entityId) {
        return repository.get(entityClass, entityId);
    }

    @Override
    public void createOrganization(Organization orgToCreate,
                                   Organization parent, Date date) {
        orgToCreate.setCreateDate(date);
        orgToCreate.save();
        new OrgLineMgmt(parent, orgToCreate, date).save();
    }

    @Override
    public void terminateParty(Party party, Date date) {
        party.terminate(date);

    }

    @Override
    public void changeParentOfOrganization(Organization organization,
                                           Organization newParent, Date date) {
        OrgLineMgmt.getByResponsible(organization, date).terminate(date);
        new OrgLineMgmt(newParent, organization, date).save();
    }

    @Override
    public void createPostUnderOrganization(Post post,
                                            Organization organization, Date date) {
        post.setOrganization(organization);
        post.setCreateDate(date);
        post.save();
    }


	public OrganisationApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
