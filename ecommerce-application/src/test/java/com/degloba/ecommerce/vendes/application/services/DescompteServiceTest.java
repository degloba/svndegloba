package com.degloba.ecommerce.vendes.application.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.degloba.domain.annotations.InternalApplicationService;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IDescompteRepository;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

/**
 * @author degloba
 * 
 * @category Servei de descompte
 *
 */
@ExtendWith(MockitoExtension.class)
public class DescompteServiceTest {

	@Mock
    private IDescompteRepository descompteRepository;

    @InjectMocks
    private DescompteService DescompteService;
}
