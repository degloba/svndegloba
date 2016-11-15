package com.degloba.organisation.facade.impl;

import com.degloba.organisation.domain.persistence.rdbms.jpa.Party;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Post;
import com.degloba.organisation.facade.assembler.OrganizationAssembler;

import java.util.Date;

import javax.inject.Inject;

import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.organisation.application.services.IOrganisationService;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Organization;
import com.degloba.organisation.facade.OrganisationFacade;
import com.degloba.organisation.facade.OrganizationDto;
import com.degloba.organisation.facade.PostDto;
import com.degloba.organisation.facade.assembler.PostAssembler;


/**
 * @author degloba
 * 
 * RDBMS/JPA
 */
public class OrganisationFacadeImpl implements OrganisationFacade {

    @Inject
    protected IOrganisationService application;

    public OrganisationFacadeImpl(IOrganisationService application) {
        this.application = application;
    }

    @Override
    public void createOrganization(OrganizationDto orgToCreate, AggregateId parentOrgId, Date date) {
        Organization organization = new OrganizationAssembler().toEntity(orgToCreate);
        Organization parent = application.getEntity(Organization.class, parentOrgId);
        application.createOrganization(organization, parent, date);
    }

    @Override
    public void terminateParty(AggregateId partyId, Date date) {
        Party party = application.getEntity(Party.class, partyId);
        application.terminateParty(party, date);
    }

    @Override
    public void changeParentOfOrganization(AggregateId organizationId, AggregateId newParentId, Date date) {
        Organization organization = application.getEntity(Organization.class, organizationId);
        Organization parent = application.getEntity(Organization.class, newParentId);
        application.changeParentOfOrganization(organization, parent, date);
    }

    @Override
    public void createPostUnderOrganization(PostDto postDto, AggregateId organizationId, Date date) {
        Post post = new PostAssembler().toEntity(postDto);
        Organization organization = application.getEntity(Organization.class, organizationId);
        application.createPostUnderOrganization(post, organization, date);
    }

    @Override
    public PostDto getPost(AggregateId postId) {
        Post post = application.getEntity(Post.class, postId);
        return new PostAssembler().toDto(post);
    }

	public OrganisationFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IOrganisationService getApplication() {
		return application;
	}

	public void setApplication(IOrganisationService application) {
		this.application = application;
	}

	@Override
	public void setPost(Post post) {
		// TODO Auto-generated method stub
		application.cretePost(post);
		
	}
	
}
