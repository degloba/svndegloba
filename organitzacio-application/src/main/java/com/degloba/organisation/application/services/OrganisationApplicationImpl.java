package com.degloba.organisation.application.services;



import com.degloba.organitzacio.domain.persistence.rdbms.jpa.IOrganisationRepository;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.OrgLineMgmt;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Organitzacio;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Party;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Post;
import com.degloba.organitzacio.facade.dtos.PostDto;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;

import java.util.Date;

import javax.inject.Inject;

/**
 * 
 * @author degloba
 * 
 * @category implementat amb JPA
 *
 */
public class OrganisationApplicationImpl implements IOrganitzacioService {
	
	
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
    public void creaOrganitzacio(Organitzacio orgToCreate,
                                   Organitzacio parent, Date date) {
        orgToCreate.setCreateDate(date);
        orgToCreate.save();
        new OrgLineMgmt(parent, orgToCreate, date).save();
    }

    @Override
    public void terminateParty(Party party, Date date) {
        party.terminate(date);

    }

    @Override
    public void changeParentOfOrganization(Organitzacio organitzacio,
                                           Organitzacio newParent, Date date) {
        OrgLineMgmt.getByResponsible(organitzacio, date).terminate(date);
        new OrgLineMgmt(newParent, organitzacio, date).save();
    }

    @Override
    public void createPostUnderOrganization(Post post,
                                            Organitzacio organitzacio, Date date) {
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
	public void cretePost(PostDto post) {
		// TODO Auto-generated method stub
		
	}



}
