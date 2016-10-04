package com.degloba.organisation.facade.impl;

import com.degloba.organisation.domain.Party;
import com.degloba.organisation.domain.Post;
import com.degloba.organisation.facade.assembler.OrganizationAssembler;

import java.util.Date;

import javax.inject.Inject;

import com.degloba.organisation.application.api.OrganisationService;
import com.degloba.organisation.domain.Organization;
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
    protected OrganisationService application;

    public OrganisationFacadeImpl(OrganisationService application) {
        this.application = application;
    }

    @Override
    public void createOrganization(OrganizationDto orgToCreate, long parentOrgId, Date date) {
        Organization organization = new OrganizationAssembler().toEntity(orgToCreate);
        Organization parent = application.getEntity(Organization.class, parentOrgId);
        application.createOrganization(organization, parent, date);
    }

    @Override
    public void terminateParty(long partyId, Date date) {
        Party party = application.getEntity(Party.class, partyId);
        application.terminateParty(party, date);
    }

    @Override
    public void changeParentOfOrganization(long organizationId, long newParentId, Date date) {
        Organization organization = application.getEntity(Organization.class, organizationId);
        Organization parent = application.getEntity(Organization.class, newParentId);
        application.changeParentOfOrganization(organization, parent, date);
    }

    @Override
    public void createPostUnderOrganization(PostDto postDto, long organizationId, Date date) {
        Post post = new PostAssembler().toEntity(postDto);
        Organization organization = application.getEntity(Organization.class, organizationId);
        application.createPostUnderOrganization(post, organization, date);
    }

    @Override
    public PostDto getPost(long postId) {
        Post post = application.getEntity(Post.class, postId);
        return new PostAssembler().toDto(post);
    }

	public OrganisationFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganisationService getApplication() {
		return application;
	}

	public void setApplication(OrganisationService application) {
		this.application = application;
	}

	@Override
	public void setPost(Post post) {
		// TODO Auto-generated method stub
		application.cretePost(post);
		
	}
	
}
