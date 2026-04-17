package se.iths.erikthorell.webshopprojekt.util;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.iths.erikthorell.webshopprojekt.model.AppUser;
import se.iths.erikthorell.webshopprojekt.model.Role;
import se.iths.erikthorell.webshopprojekt.repository.AppUserRepository;

@Component
public class DatabaseInit {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInit(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createUser() {
        String email = "erikgthorell@gmail.com";

        if (appUserRepository.findByEmail(email).isPresent()) {
            return;
        }

        AppUser user = new AppUser();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRole(Role.ROLE_ADMIN);
        user.setConsent(true);

        appUserRepository.save(user);
    }
}