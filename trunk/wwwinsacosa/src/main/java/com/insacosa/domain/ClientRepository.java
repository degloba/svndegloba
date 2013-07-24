package com.insacosa.domain;

import ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface ClientRepository {

    Client load(Long clientId);

}
