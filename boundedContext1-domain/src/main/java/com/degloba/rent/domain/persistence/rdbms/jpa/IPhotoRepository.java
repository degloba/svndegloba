package com.degloba.rent.domain.persistence.rdbms.jpa;

import com.degloba.domain.annotations.DomainRepository;


@DomainRepository
public interface IPhotoRepository {

	public Photo load(long id);

	public void save(Photo client);
}
