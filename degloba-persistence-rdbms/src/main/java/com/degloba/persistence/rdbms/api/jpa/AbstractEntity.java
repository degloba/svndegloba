package com.degloba.persistence.rdbms.api.jpa;

import javax.persistence.*;

/**
 * Una classe d'entitat abstracta que proporciona atributs d'identificació i versió, 
 * així com mètodes bàsics de persistència
 *
 * @author yang
 *
 */
@MappedSuperclass
public abstract class AbstractEntity extends BaseEntity {

    private static final long serialVersionUID = 8882145540383345037L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private int version;

    @Override
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
