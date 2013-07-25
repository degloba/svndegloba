package com.insacosa.domain.repositories;

import com.insacosa.domain.Client;

import ddd.domain.annotations.DomainRepository;

@DomainRepository
public interface ClientRepository {

    Client load(Long clientId);

}
