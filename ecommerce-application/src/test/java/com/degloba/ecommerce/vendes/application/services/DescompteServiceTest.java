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

import com.degloba.domain.annotations.InternalApplicationService;

import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

/**
 * @author degloba
 * 
 * @category Servei de descompte
 *
 */
public class DescompteServiceTest {

	private UserService userService;
    private DescompteRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void shouldSavedUserSuccessfully() {
        User user = new User(null, "siva@gmail.com","siva","Siva");
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
        given(userRepository.save(user)).willAnswer(invocation -> invocation.getArgument(0));

        User savedUser = userService.createUser(user);

        assertThat(savedUser).isNotNull();

        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingEmail() {
        User user = new User(1L, "siva@gmail.com","siva","Siva");
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        assertThrows(UserRegistrationException.class, () -> {
            userService.createUser(user);
        });

        verify(userRepository, never()).save(any(User.class));
    }

}
