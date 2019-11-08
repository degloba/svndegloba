package com.degloba.cqrs.querys;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.degloba.adreces.Address;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddressQueryHandler {

    private final EntityManager entityManager;

    @QueryHandler
    public AddressByIdQueryResult handle(AddressByIdQuery query) {
        Address address = entityManager.find(Address.class, query.getAddressId());
        return new AddressByIdQueryResult(address.getAddressId(),
                address.getPersonId(), address.getStreetAndNumber(), address.getZipCode());
    }

}
