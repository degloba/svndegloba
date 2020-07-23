package com.degloba.ecommerce.vendes.application.services;

/*import com.sivalabs.myservice.entities.User;
import com.sivalabs.myservice.exception.UserRegistrationException;
import com.sivalabs.myservice.repositories.UserRepository;*/
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.degloba.ecommerce.comandes.IComandaRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;



/**
 *
 * @author degloba
 */

public class ComandesServiceTest {

	@Mock
    private IComandaRepository comandaRepository;

    @InjectMocks
    private UserService userService;


	}
