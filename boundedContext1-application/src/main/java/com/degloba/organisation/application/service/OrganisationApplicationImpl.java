package com.degloba.organisation.application.service;


// Application


// Domain
import com.degloba.organisation.domain.persistence.rdbms.jpa.OrgLineMgmt;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Organization;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Party;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Post;

import java.util.Date;

import com.degloba.domain.persistence.rdbms.jpa.BaseEntity;

// Domain

import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;


public class OrganisationApplicationImpl implements IOrganisationService {
	
	
    //////@Inject
    private IEntityRepository organisationRepository;

   public IEntityRepository getRepository() {
		return organisationRepository;
	}

	public void setRepository(IEntityRepository organisationRepository) {
		this.organisationRepository = organisationRepository;
	}

 
    @Override
    public <T extends BaseEntity> T getEntity(Class<T> entityClass, long entityId) {
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
        post.setOrganization(organization);
        post.setCreateDate(date);
        post.save();
    }


	public OrganisationApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganisationApplicationImpl(IEntityRepository organisationRepository) {
		super();
		this.organisationRepository = organisationRepository;
	}

	@Override
	public void cretePost(Post post) {
		// TODO Auto-generated method stub
		organisationRepository.save(post);
	}

	

}
