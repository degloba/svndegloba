package domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * 
 * @author degloba
 * 
 * @category Defineix la Entitat Base del Domini
 * Lligat al Domini.Totes les entitats del Domini hereden d'ella
 * 
 */


@Entity
@MappedSuperclass
public abstract class BaseEntity extends Entitat {

    // ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
    // annotated as ordinal in sake of performance
    public static enum EntityStatus {
        ACTIVE, ARCHIVE
    }

    
	public static final String FIND_ALL =  "Entity.findAll";

	public static final String TOTAL_RESULT = "Entity.totalResult";

    //entityId because ID can mean something (some domain concept) in some Bounded Context
    @Id
    @GeneratedValue
    private Long entityId;

	@Version
    private Long version;

    @Enumerated(EnumType.ORDINAL)
    private EntityStatus entityStatus = EntityStatus.ACTIVE;

    public void markAsRemoved() {
        entityStatus = EntityStatus.ARCHIVE;
    }

    public Long getEntityId() {
        return entityId;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }
}
