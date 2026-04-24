package se.iths.erikthorell.webshopprojekt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import se.iths.erikthorell.webshopprojekt.model.AppUser;
import se.iths.erikthorell.webshopprojekt.model.Role;
import se.iths.erikthorell.webshopprojekt.repository.AppUserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AppUserServiceH2Test {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        appUserRepository.deleteAll();
    }

    @Test
    void registerUser_shouldSaveUserInH2Database() {
        AppUser user = new AppUser();
        user.setEmail("erik@test.se");
        user.setPassword("password123");
        user.setConsent(true);

        AppUser result = appUserService.registerUser(user);

        assertNotNull(result.getId());
        assertEquals("erik@test.se", result.getEmail());
        assertEquals(Role.ROLE_USER, result.getRole());
        assertTrue(passwordEncoder.matches("password123", result.getPassword()));
        assertTrue(appUserRepository.existsByEmail("erik@test.se"));
    }

    @Test
    void registerUser_shouldThrowExceptionWhenEmailAlreadyExistsInH2Database() {
        AppUser user = new AppUser();
        user.setEmail("erik@test.se");
        user.setPassword("password123");
        user.setConsent(true);

        appUserService.registerUser(user);

        AppUser duplicateUser = new AppUser();
        duplicateUser.setEmail("erik@test.se");
        duplicateUser.setPassword("password456");
        duplicateUser.setConsent(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> appUserService.registerUser(duplicateUser)
        );

        assertEquals("E-postadressen används redan.", exception.getMessage());
    }

    @Test
    void deleteUserByEmail_shouldDeleteUserFromH2Database() {
        AppUser user = new AppUser();
        user.setEmail("erik@test.se");
        user.setPassword("password123");
        user.setConsent(true);

        appUserService.registerUser(user);

        appUserService.deleteUserByEmail("erik@test.se");

        assertFalse(appUserRepository.existsByEmail("erik@test.se"));
    }

    @Test
    void deleteUserByEmail_shouldThrowExceptionWhenUserDoesNotExistInH2Database() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> appUserService.deleteUserByEmail("missing@test.se")
        );

        assertEquals("Ingen användare hittades med den e-postadressen.", exception.getMessage());
    }
}