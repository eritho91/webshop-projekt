package se.iths.erikthorell.webshopprojekt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import se.iths.erikthorell.webshopprojekt.model.AppUser;
import se.iths.erikthorell.webshopprojekt.model.Role;
import se.iths.erikthorell.webshopprojekt.repository.AppUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        customUserDetailsService = new CustomUserDetailsService(appUserRepository);
    }

    @Test
    void loadUserByUsername_shouldReturnUserDetailsWhenUserExists() {
        AppUser appUser = new AppUser();
        appUser.setEmail("erik@test.se");
        appUser.setPassword("encodedPassword");
        appUser.setRole(Role.ROLE_USER);
        appUser.setConsent(true);

        when(appUserRepository.findByEmail("erik@test.se")).thenReturn(Optional.of(appUser));

        UserDetails result = customUserDetailsService.loadUserByUsername("erik@test.se");

        assertEquals("erik@test.se", result.getUsername());
        assertEquals("encodedPassword", result.getPassword());
        assertTrue(result.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));

        verify(appUserRepository).findByEmail("erik@test.se");
    }

    @Test
    void loadUserByUsername_shouldThrowExceptionWhenUserDoesNotExist() {
        when(appUserRepository.findByEmail("missing@test.se")).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> customUserDetailsService.loadUserByUsername("missing@test.se")
        );

        assertEquals("missing@test.se", exception.getMessage());

        verify(appUserRepository).findByEmail("missing@test.se");
    }
}