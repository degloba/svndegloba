package com.degloba.missatgeria.facadeImpl;

import java.util.Date;

import javax.inject.Inject;

import com.degloba.missatgeria.facadeImpl.assembler.OrganizationAssembler;
import com.degloba.missatgeria.facadeImpl.assembler.PostAssembler;
import com.degloba.organisation.application.services.IOrganitzacioService;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Organitzacio;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Party;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Post;
import com.degloba.organitzacio.facade.dtos.OrganitzacioDto;
import com.degloba.organitzacio.facade.dtos.PostDto;
import com.degloba.organitzacio.facade.ui.IOrganitzacioFacade;
import com.degloba.persistence.rdbms.jpa.AggregateId;

import lombok.Value;


/**
 * @author degloba
 * 
 */
@Value
public class OrganitzacioFacadeImpl implements IOrganitzacioFacade {

    @Inject
    protected IOrganitzacioService application;

    public OrganitzacioFacadeImpl(IOrganitzacioService application) {
        this.application = application;
    }

    @Override
    public void creaOrganitzacio(OrganitzacioDto orgToCreate, AggregateId parentOrgId, Date date) {
        Organitzacio organitzacio = new OrganizationAssembler().toEntity(orgToCreate);
        Organitzacio parent = application.getEntity(Organitzacio.class, parentOrgId);
        application.creaOrganitzacio(organitzacio, parent, date);
    }

    @Override
    public void terminateParty(AggregateId partyId, Date date) {
        Party party = application.getEntity(Party.class, partyId);
        application.terminateParty(party, date);
    }

    @Override
    public void changeParentOfOrganization(AggregateId organizationId, AggregateId newParentId, Date date) {
        Organitzacio organitzacio = application.getEntity(Organitzacio.class, organizationId);
        Organitzacio parent = application.getEntity(Organitzacio.class, newParentId);
        application.changeParentOfOrganization(organitzacio, parent, date);
    }

    @Override
    public void createPostUnderOrganization(PostDto postDto, AggregateId organizationId, Date date) {
        Post post = new PostAssembler().toEntity(postDto);
        Organitzacio organitzacio = application.getEntity(Organitzacio.class, organizationId);
        application.createPostUnderOrganization(post, organitzacio, date);
    }

    @Override
    public PostDto getPost(AggregateId postId) {
        Post post = application.getEntity(Post.class, postId);
        return new PostAssembler().toDto(post);
    }


	@Override
	public void setPost(PostDto post) {
		// TODO Auto-generated method stub
		application.cretePost(post);
		
	}
	
}
