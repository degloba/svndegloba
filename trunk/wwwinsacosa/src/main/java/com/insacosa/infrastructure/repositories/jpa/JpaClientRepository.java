package com.insacosa.infrastructure.repositories.jpa;

import com.insacosa.domain.Client;
import com.insacosa.domain.repositories.ClientRepository;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.infrastructure.repo.jpa.GenericJpaRepository;


@DomainRepositoryImpl
public class JpaClientRepository extends GenericJpaRepository<Client, Long> implements ClientRepository {
}
