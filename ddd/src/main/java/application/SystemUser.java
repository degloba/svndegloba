/**
 * 
 */
package application;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//import sales.domain.Client;

/**
 * Mock implmentation of currenly authenticated user.
 * 
 * @author Slawek
 */
@Component
public class SystemUser implements ISystemUser{

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
	private EntityManager entityManager;

	/**
	 * @return any user id
	 */
	public Long getUserId() {
		// return userId;
		//List<Client> clients = entityManager.createQuery("from Client").getResultList();
		//return clients.get(0).getEntityId();
		return null; // PERE CAL CAMBIAR!!!!!!!!!
	}
}
