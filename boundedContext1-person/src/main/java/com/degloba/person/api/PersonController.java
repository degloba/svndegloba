package com.degloba.person.api;

import com.degloba.cqrs.commands.CreatePersonCommand;
import com.degloba.cqrs.commands.RequestPrivateAddressAssignmentCommand;
import com.degloba.person.dto.AddressDto;
import com.degloba.person.dto.PersonDto;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.Future;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final CommandGateway commandGateway;

    @PostMapping("/persons")
    public Future<UUID> createPerson(@RequestBody @Valid PersonDto personDto) {
        log.debug("[Person][API] Creating a person: {}", personDto);
        return commandGateway.send(new CreatePersonCommand(UUID.randomUUID().toString(), personDto.getFullName()));
    }

    @PostMapping("/person/{personId}/address")
    public Future<String> assignPrivateAddress(@RequestParam String personId, @RequestBody @Valid AddressDto dto) {
        log.debug("[Person][API] Request to assign new private address, person: {}, address: {}", personId, dto);
        return commandGateway.send(new RequestPrivateAddressAssignmentCommand(personId,
                dto.getStreetAndNumber(),
                dto.getZipCode()));
    }

}