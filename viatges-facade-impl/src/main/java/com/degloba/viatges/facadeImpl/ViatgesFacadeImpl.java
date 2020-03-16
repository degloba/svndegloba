package com.degloba.viatges.facadeImpl;

import java.util.Date;

import javax.inject.Inject;

import com.degloba.viatges.application.services.IViatgesService;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.viatges.facade.dtos.ReservaDto;
import com.degloba.viatges.facade.ui.IViatgesFacade;
import com.degloba.viatges.facadeImpl.assembler.ReservaAssembler;

import lombok.Value;


/**
 * @author degloba
 * 
 */
@Value
public class ViatgesFacadeImpl implements IViatgesFacade {

    @Inject
    protected IViatgesService application;

    public ViatgesFacadeImpl(IViatgesService application) {
        this.application = application;
    }

    @Override
    public void creaReserva(ReservaDto reservaDto) {
        Reserva reserva = new ReservaAssembler().toEntity(reservaDto);
        application.creaReserva(reserva);
    }

/*    @Override
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
		
	}*/
	
}
