package infrastructure.repository.jpa;

// JPA
import javax.persistence.EntityManager;

public interface IBaseAggregateRootJpaRepository <K,E>{

	 public E load(K id);

	 public void delete(K id);

	 public void persist(E entitat); 

	 public E save(E entitat);
	    
	 public EntityManager getEntityManager();

	 public void setEntityManager(EntityManager entityManager); 

}
