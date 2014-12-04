package com.degloba.boundedContext.domain.modules.client;

import domain.annotations.DomainRepository;

@DomainRepository
public interface IClientRepository<K> {
       
    Client save(Client client);

}
