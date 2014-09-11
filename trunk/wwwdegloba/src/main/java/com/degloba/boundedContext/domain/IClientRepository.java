package com.degloba.boundedContext.domain;

import domain.annotations.DomainRepository;

@DomainRepository
public interface IClientRepository<K> {
    
    Client load(K clientId);
    
    Client save(Client client);

}
