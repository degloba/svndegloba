package ddd.infrastructure.repo.jpa;

import ddd.domain.BaseEntity;

/**
 * 
 * @author degloba
 * @category Defineix un Repository utilitzant JPA (per tant lligat a la tecnologia de persistencia), 
 * però només lligat a BaseEntity
 * @param <E> JPA Entity Type (DDD: Aggregate, Entity)
 * @param <K> key type
 */
public class GenericJpaRepositoryForBaseEntity<E extends BaseEntity> extends GenericJpaRepository<E, Long>{
		
	public void delete(Long id){
		E entity = load(id);
		entity.markAsRemoved();		
		save(entity);	
	}
}
