package ddd.infrastructure.repo.jpa;

// JPA
import javax.persistence.criteria.Predicate;

// Domini
import ddd.domain.BaseEntity;


/**
 * 
 * @author degloba
 * @category Defineix un Repository utilitzant JPA (per tant lligat a la tecnologia de persistencia), 
 * però només lligat a BaseEntity (Generic)
 * @param <E> JPA Entity Type (DDD: Aggregate, Entity)
 * @param <K> key type
 */
public class GenericJpaRepositoryForBaseEntity<E extends BaseEntity> extends GenericJpaRepository<E, Long>{
		
	  
	public <E> E Find(Predicate predicate) {
			// TODO Auto-generated method stub
			return null;
		}
	
	
	public void delete(Long id){
		E entity = load(id);
		entity.markAsRemoved();		
		save(entity);	
	}
	
	
	public int Delete(Predicate predicate) {
		// TODO Auto-generated method stub
		return 0;
	}
	  

	public <E> E Create(E entitat) {
		// TODO Auto-generated method stub
		return null;
	}



	public <E> int Update(E entitat) {
		// TODO Auto-generated method stub
		return 0;
	}
	  

	  /**
	   * Return all the Entitats
	   * @param kind : of kind Entitat
	   * @return  Entitats
	   */
	  public static Iterable<BaseEntity> getAllEntitats(String kind) {
		return null;
	    //return Util.listEntities(kind, null, null);
	  }
	
	
}
