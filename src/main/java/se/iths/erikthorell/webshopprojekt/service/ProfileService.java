package se.iths.erikthorell.webshopprojekt.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.springmessenger.model.Email;
import se.iths.erikthorell.springmessenger.service.MessageService;
import se.iths.erikthorell.webshopprojekt.model.AppUser;
import se.iths.erikthorell.webshopprojekt.repository.AppUserRepository;

@Service
public class ProfileService {

    private final AppUserRepository appUserRepository;
    private final MessageService messageService;

    public ProfileService(AppUserRepository appUserRepository, MessageService messageService) {
        this.appUserRepository = appUserRepository;
        this.messageService = messageService;
    }

    public AppUser getLoggedInUser(Authentication authentication) {
        String email = authentication.getName();

        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Användaren hittades inte"));
    }

    public void sendUserDataByEmail(Authentication authentication) {
        AppUser user = getLoggedInUser(authentication);

        Email email = new Email();
        email.setRecipient(user.getEmail());
        email.setSubject("Dina kontouppgifter");
        email.setMessage(
                "Här är dina uppgifter:\n\n" +
                        "E-post: " + user.getEmail() + "\n" +
                        "Roll: " + user.getRole() + "\n" +
                        "Samtycke: " + user.isConsent()
        );

        messageService.send(email);
    }

    public void deleteLoggedInUser(Authentication authentication) {
        AppUser user = getLoggedInUser(authentication);
        appUserRepository.delete(user);
    }
}