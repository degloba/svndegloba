package com.degloba.organisation.application.services;


import com.degloba.organisation.domain.persistence.rdbms.jpa.IOrganisationRepository;

// Application


// Domain
import com.degloba.organisation.domain.persistence.rdbms.jpa.OrgLineMgmt;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Organization;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Party;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Post;

import java.util.Date;

import javax.inject.Inject;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;

import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;


public class OrganisationApplicationImpl implements IOrganisationService {
	
	
    @Inject
    private IOrganisationRepository organisationRepository;

   public IOrganisationRepository getRepository() {
		return organisationRepository;
	}

	public void setRepository(IOrganisationRepository organisationRepository) {
		this.organisationRepository = organisationRepository;
	}

 
    @Override
    public <T extends BaseAggregateRoot> T getEntity(Class<T> entityClass, AggregateId entityId) {
        return organisationRepository.get(entityClass, entityId);
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
        /////post.setOrganization(organization);
        post.setCreateDate(date);
        post.save();
    }


	public OrganisationApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganisationApplicationImpl(IOrganisationRepository organisationRepository) {
		super();
		this.organisationRepository = organisationRepository;
	}

	@Override
	public void cretePost(Post post) {
		// TODO Auto-generated method stub
		organisationRepository.save(post);
	}

	

}
