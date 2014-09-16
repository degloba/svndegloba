package com.degloba.boundedContext.domain;

import domain.annotations.DomainRepository;

@DomainRepository
public interface IClientRepository<K> {
       
    Client save(Client client);

}
