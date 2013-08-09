package com.insacosa.domain;

import javax.inject.Inject;

import com.insacosa.domain.repositories.CaracteristiquesRepository;
import com.insacosa.domain.repositories.InmoblesRepository;
import com.insacosa.domain.repositories.SolicitudsRepository;

import ddd.domain.IEntityService;
import ddd.domain.annotations.DomainService;
import ddd.domain.sharedkernel.Money;

/**
 * Sample Domain Service that contains logic that:
 * <ul>
 * <li> Does not have a natural place in any aggregate - we don't want to bloat Order with issuance of the Invoice
 * <li> Has broad dependencies - we don't want Order to become a 'God Class'
 * <li> Is used only (or not many) in one Use Case/user Story so is not essential for any Aggregate
 * <ul>
 * 
 * Notice that this Domain Service is managed by Container in order to be able to inject dependencies like Repo  
 * 
 * @author Slawek
 *
 */
@DomainService
public class CaracteristiquesDominiService<K,T extends Caracteristiques> extends GenericDomainServiceForBaseEntity<K,T>{
	
	@Inject
	private CaracteristiquesRepository caracteristiquesRepository;
	
	
	
}
