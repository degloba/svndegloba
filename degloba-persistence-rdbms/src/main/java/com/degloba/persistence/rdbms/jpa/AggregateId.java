package com.degloba.persistence.rdbms.jpa;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;

import lombok.Data;

/**
 * @category Un agregat és un grup d'objectes de negoci que sempre han de ser
 *           consistents. Per tant, guardem i actualitzem els agregats en el seu
 *           conjunt dins d’una transacció. L'agregat és un patró important en
 *           DDD, que ajuda a mantenir la consistència dels nostres objectes
 *           empresarials. Tanmateix, la idea d’agregat també és útil fora del
 *           context del DDD.
 * 
 *           https://github.com/BottegaIT/ddd-leaven-v2
 */
@SuppressWarnings("serial")
@Embeddable
@Data
public class AggregateId implements Serializable {

	@Column(name = "aggregateId", length = 255, unique = true, nullable = false)
	private String aggregateId;

	public AggregateId(String aggregateId) {
		Validate.notNull(aggregateId);
		this.aggregateId = aggregateId;
	}

	public AggregateId() {

	}

	public static AggregateId generate() {
		return new AggregateId(UUID.randomUUID().toString());
	}

	@Override
	public int hashCode() {
		return aggregateId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AggregateId other = (AggregateId) obj;
		if (aggregateId == null) {
			if (other.aggregateId != null)
				return false;
		} else if (!aggregateId.equals(other.aggregateId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return aggregateId;
	}

	/*
	 * @Column(name = "aggregateId", length = 255, unique=true, nullable=false)
	 * public String getAggregateId() { return aggregateId; }
	 */

	/*
	 * Comentat perque dona error
	 * 
	 * public void setAggregateId(String aggregateId) { this.aggregateId =
	 * aggregateId; }
	 */

}
