package com.degloba.boundedContext.domain.client;

import domain.annotations.DomainRepository;

@DomainRepository
public interface IClientRepository<K> {
       
    Client save(Client client);

}
