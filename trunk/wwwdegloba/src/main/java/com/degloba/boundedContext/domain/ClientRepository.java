package com.degloba.boundedContext.domain;

import domain.annotations.DomainRepository;

@DomainRepository
public interface ClientRepository<K> {
    
    Client load(K clientId);
    
    Client save(Client client);

}
