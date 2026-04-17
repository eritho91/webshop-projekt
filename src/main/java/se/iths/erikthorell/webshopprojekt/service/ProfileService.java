package se.iths.erikthorell.webshopprojekt.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.webshopprojekt.model.AppUser;
import se.iths.erikthorell.webshopprojekt.repository.AppUserRepository;

@Service
public class ProfileService {

    private final AppUserRepository appUserRepository;
    private final JavaMailSender mailSender;

    public ProfileService(AppUserRepository appUserRepository, JavaMailSender mailSender) {
        this.appUserRepository = appUserRepository;
        this.mailSender = mailSender;
    }

    public AppUser getLoggedInUser(Authentication authentication) {
        String email = authentication.getName();

        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Användaren hittades inte"));
    }

    public void sendUserDataByEmail(Authentication authentication) {
        AppUser user = getLoggedInUser(authentication);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Dina kontouppgifter");
        message.setText(
                "Här är dina uppgifter:\n\n" +
                        "E-post: " + user.getEmail() + "\n" +
                        "Roll: " + user.getRole() + "\n" +
                        "Samtycke: " + user.isConsent()
        );

        mailSender.send(message);
    }

    public void deleteLoggedInUser(Authentication authentication) {
        AppUser user = getLoggedInUser(authentication);
        appUserRepository.delete(user);
    }
}