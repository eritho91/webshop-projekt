package se.iths.erikthorell.webshopprojekt.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.webshopprojekt.model.AppUser;
import se.iths.erikthorell.webshopprojekt.model.Role;
import se.iths.erikthorell.webshopprojekt.repository.AppUserRepository;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser registerUser(AppUser user) {
        if (appUserRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("E-postadressen används redan.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);

        return appUserRepository.save(user);
    }
}