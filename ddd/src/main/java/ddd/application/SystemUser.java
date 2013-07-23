/**
 * 
 */
package ddd.application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

//import ddd.sales.domain.Client;

/**
 * Mock implmentation of currenly authenticated user.
 * 
 * @author Slawek
 */
@Component
public class SystemUser {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @return any user id
	 */
	@SuppressWarnings("unchecked")
	public Long getUserId() {
		// return userId;
		//List<Client> clients = entityManager.createQuery("from Client").getResultList();
		//return clients.get(0).getEntityId();
		return null; // PERE CAL CAMBIAR!!!!!!!!!
	}
}
