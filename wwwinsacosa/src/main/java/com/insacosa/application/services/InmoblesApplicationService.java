package com.insacosa.application.services;


import java.util.List;

import javax.inject.Inject;


import application.ApplicationEventPublisher;
import application.SystemUser;
import application.annotation.ApplicationService;

import com.insacosa.domain.*;
import com.insacosa.domain.repositories.InmoblesRepository;


import com.insacosa.vo.FotoForm;
import com.insacosa.webui.FotoItemDto;
import com.insacosa.webui.InmobleItemDto;

import com.insacosa.webui.SolicitudItemDto;
import com.insacosa.webui.UsuariItemDto;

/**
 * @author degloba
 *
 * @category Defineix el Servei d'Aplicació per l'entitat de Domini "Inmobles"
 */
@ApplicationService
public class InmoblesApplicationService<T extends Inmobles> extends GenericApplicationServiceForBaseEntity<Long,T>
	 {
	
	
    @Inject
    private InmoblesRepository inmoblesRepository;

    @Inject
    private SystemUser systemUser;

    @Inject
    private ApplicationEventPublisher eventPublisher;
	

    GenericApplicationServiceForBaseEntity<Long,Inmobles>  g;
    
    
    private void GetInmoblesById(Long id) {
    	
    	g.ds.CreateService().Get(id);
    	
    }
    
	
	public void modificarInmoble(Inmobles inmoble) {
		
		
	}


	public void eliminarInmoble(Long id) {
		ds.CreateService().Remove(id);
	}



	public void solicitarInmobles(Solicituds solicitud) {
		// TODO Auto-generated method stub
		
	}


	

	public void afegirFoto(Fotos foto) {
		// TODO Auto-generated method stub
		
	}


	public void modificarFoto(Fotos foto) {
		// TODO Auto-generated method stub
		
	}


	public void eliminarFoto(Fotos foto) {
		// TODO Auto-generated method stub
		
	}


	public List<Fotos> fotosInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		return null;
	}





	public void afegirCaractInmoble(Long caractinmoble, Long inmoble) {

/*		Inmobles i = inmoblesRepository.load(inmoble);
		Caracteristiques c = caracteristiquesRepository.find(caractinmoble);
	 	i.getCaracteristiqueses().add(c);*/
		
	}


	public void modificarValorCaract(String string, String keyInmoble,
			String value) {
		// TODO Auto-generated method stub
		
	}


	public void eliminarValorCaract(Long keyCaracteristica, String keyInmoble) {
		// TODO Auto-generated method stub
		
	}




	public void eliminarSolicitud(SolicitudItemDto solicitud) {
		// TODO Auto-generated method stub
		
	}


	public void afegirInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		
	}


	public List<FotoForm> inmoblesVenedor(UsuariItemDto usuari) {
		// TODO Auto-generated method stub
		return null;
	}


	public void eliminarFoto(FotoItemDto foto) {
		// TODO Auto-generated method stub
		
	}


	public void eliminarValorCaract(Object next, String keyInmoble) {
		// TODO Auto-generated method stub
		
	}



	public InmobleItemDto esborrarInmoble(String keyInmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	public InmoblesRepository getInmoblesRepository() {
		return inmoblesRepository;
	}


	public void setInmoblesRepository(InmoblesRepository inmoblesRepository) {
		this.inmoblesRepository = inmoblesRepository;
	}


	public SystemUser getSystemUser() {
		return systemUser;
	}


	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}


	public ApplicationEventPublisher getEventPublisher() {
		return eventPublisher;
	}


	public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}


	
	

}
