package infrastructure.repository.jpa;

// JPA
import javax.persistence.criteria.Predicate;

// DDD
import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;



/**
 * 
 * @author degloba
 * 
 * @category Defineix un Repository utilitzant JPA (per tant lligat a la tecnologia de persistencia), 
 * pero nomes lligat a BaseEntity (Generic)
 * @param <E> JPA Entity Type (DDD: Aggregate, Entity)
 * @param <K>
 * @param <K> key type
 */
public class GenericJpaRepositoryForBaseEntity<K,E extends BaseEntity> extends GenericJpaRepository<K,E>{
		
	  
	public E Find(Predicate predicate) {
			// TODO Auto-generated method stub
			return null;
		}
	
	
	public void delete(K id){
		E entity = load(id);
		entity.markAsRemoved();		
		save(entity);	
	}
	
	
	public int Delete(Predicate predicate) {
		// TODO Auto-generated method stub
		return 0;
	}
	  

	public  E Create(E entitat) {
		// TODO Auto-generated method stub
		return null;
	}



	public  int Update(E entitat) {
		// TODO Auto-generated method stub
		return 0;
	}
	  

	  /**
	   * Return all the Entitats
	   * @param kind : of kind Entitat
	   * @return  Entitats
	   */
	  public static Iterable<BaseAggregateRoot> getAllEntitats(String kind) {
		return null;
	    //return Util.listEntities(kind, null, null);
	  }
	
	
}
