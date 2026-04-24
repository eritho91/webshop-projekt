package se.iths.erikthorell.webshopprojekt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.iths.erikthorell.webshopprojekt.model.AppUser;
import se.iths.erikthorell.webshopprojekt.model.Role;
import se.iths.erikthorell.webshopprojekt.repository.AppUserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceMockTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AppUserService appUserService;

    @BeforeEach
    void setUp() {
        appUserService = new AppUserService(appUserRepository, passwordEncoder);
    }

    @Test
    void registerUser_shouldSaveUserWithEncodedPasswordAndRoleUser() {
        AppUser user = new AppUser();
        user.setEmail("erik@test.se");
        user.setPassword("password123");
        user.setConsent(true);

        when(appUserRepository.existsByEmail("erik@test.se")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(appUserRepository.save(user)).thenReturn(user);

        AppUser result = appUserService.registerUser(user);

        assertEquals("encodedPassword", result.getPassword());
        assertEquals(Role.ROLE_USER, result.getRole());

        verify(appUserRepository).existsByEmail("erik@test.se");
        verify(passwordEncoder).encode("password123");
        verify(appUserRepository).save(user);
    }

    @Test
    void registerUser_shouldThrowExceptionWhenEmailAlreadyExists() {
        AppUser user = new AppUser();
        user.setEmail("erik@test.se");
        user.setPassword("password123");
        user.setConsent(true);

        when(appUserRepository.existsByEmail("erik@test.se")).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> appUserService.registerUser(user)
        );

        assertEquals("E-postadressen används redan.", exception.getMessage());

        verify(appUserRepository).existsByEmail("erik@test.se");
        verify(passwordEncoder, never()).encode(anyString());
        verify(appUserRepository, never()).save(any(AppUser.class));
    }

    @Test
    void deleteUserByEmail_shouldDeleteUser() {
        when(appUserRepository.deleteByEmail("erik@test.se")).thenReturn(1L);

        assertDoesNotThrow(() -> appUserService.deleteUserByEmail("erik@test.se"));

        verify(appUserRepository).deleteByEmail("erik@test.se");
    }

    @Test
    void deleteUserByEmail_shouldThrowExceptionWhenUserDoesNotExist() {
        when(appUserRepository.deleteByEmail("missing@test.se")).thenReturn(0L);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> appUserService.deleteUserByEmail("missing@test.se")
        );

        assertEquals("Ingen användare hittades med den e-postadressen.", exception.getMessage());

        verify(appUserRepository).deleteByEmail("missing@test.se");
    }
}