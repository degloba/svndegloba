package com.degloba.viatges.application.services;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.viatges.application.SearchCriteria;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Hotel;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Usuari;


/**
 * @category Implementació d'un servei de {@link Reserva} basat en JPA.</br>
 * Delega en un {@link EntityManager} l'execució de les crides d'accés a dades contra el repositori.</br> 
 * El contenidor de gestió proporciona la referència d’{@link EntityManager} (Spring)
 * automatically.
 */
@Service("ReservaService")
@Repository
public class JpaReservaService implements IViatgesService, Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Reserva> findReservas(String username) {
		if (username != null) {
			return em.createQuery("select b from com.degloba.viatges.domain.Reserva b where b.user.username = :username order by b.checkinDate")
					.setParameter("username", username).getResultList();
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Hotel> findHotels(SearchCriteria criteria, int firstResult, String orderBy, boolean ascending) {
		String pattern = getSearchPattern(criteria);
		orderBy = (orderBy != null) ? orderBy : "name";
		String orderDirection = (ascending) ? " ASC" : " DESC";
		return em
				.createQuery(
						"select h from Hotel h where lower(h.name) like :pattern or lower(h.city) like :pattern "
								+ "or lower(h.zip) like :pattern or lower(h.address) like :pattern order by h."
								+ orderBy + orderDirection).setParameter("pattern", pattern)
								.setMaxResults(criteria.getPageSize()).setFirstResult(firstResult).getResultList();
	}

	@Transactional(readOnly = true)
	public int getNumberOfHotels(SearchCriteria criteria) {
		String pattern = getSearchPattern(criteria);
		Long count = (Long) em
				.createQuery(
						"select count(h.id) from Hotel h where lower(h.name) like :pattern or lower(h.city) like :pattern "
								+ "or lower(h.zip) like :pattern or lower(h.address) like :pattern")
								.setParameter("pattern", pattern).getSingleResult();
		return count.intValue();
	}

	@Transactional(readOnly = true)
	public Hotel buscarHotelById(Long id) {
		return em.find(Hotel.class, id);
	}

	@Transactional(readOnly = true)
	public Reserva creaReserva(Long hotelId, String username) {
		Hotel hotel = em.find(Hotel.class, hotelId);
		Usuari usuari = buscarUsuari(username);
		Reserva reserva = new Reserva(hotel, usuari);
		return reserva;
	}

	@Transactional
	public void persistReserva(Reserva reserva) {
		em.persist(reserva);
	}

	@Transactional
	public void cancelReserva(Reserva reserva) {
		reserva = em.find(Reserva.class, reserva.getId());
		if (reserva != null) {
			em.remove(reserva);
		}
	}

	// helpers

	private String getSearchPattern(SearchCriteria criteria) {
		if (StringUtils.hasText(criteria.getSearchString())) {
			return "%" + criteria.getSearchString().toLowerCase().replace('*', '%') + "%";
		} else {
			return "%";
		}
	}

	@Override
	public Usuari buscarUsuari(String username) {
		return (Usuari) em.createQuery("select u from com.degloba.viatges.domain.User u where u.username = :username")
				.setParameter("username", username).getSingleResult();
	}

/*	@Override
	public List<Hotel> findHotels(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public void cancelaReserva(Long Reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuari login(String usrname, String pw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reserva buscarReservaById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> buscarHotels(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserva> buscarReserves(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BaseAggregateRoot> T getEntity(Class<T> entityClass, AggregateId entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creaReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

}