package com.degloba.persistence.rdbms.api.jpa;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * @category Una classe d'entitat abstracta que proporciona atributs d'identificació i versió, 
 * així com mètodes bàsics de persistència
 *
 * @author degloba
 *
 */
@MappedSuperclass
public abstract class AbstractEntity extends BaseEntity {

    private static final long serialVersionUID = 8882145540383345037L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter @Setter private Long id;

    @Version
    @Column(name = "version")
    @Getter @Setter private int version;

}
