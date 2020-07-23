package com.degloba.persistence.rdbms.api.jpa;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * @category Un agregat és un grup d'objectes de negoci que sempre han de ser
 *           consistents. Per tant, guardem i actualitzem els agregats en el seu
 *           conjunt dins d’una transacció. L'agregat és un patró important en
 *           DDD, que ajuda a mantenir la consistència dels nostres objectes
 *           empresarials. Tanmateix, la idea d’agregat també és útil fora del
 *           context del DDD.
 */
@SuppressWarnings("serial")
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AggregateId implements Serializable {

	@Column(name = "aggregateId", length = 255, unique = true, nullable = false)
	@NonNull
	private String aggregateId;


	public static AggregateId generate() {
		return new AggregateId(UUID.randomUUID().toString());
	}

}
