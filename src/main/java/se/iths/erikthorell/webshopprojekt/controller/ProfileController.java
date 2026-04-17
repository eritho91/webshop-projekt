package se.iths.erikthorell.webshopprojekt.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.erikthorell.webshopprojekt.model.AppUser;
import se.iths.erikthorell.webshopprojekt.service.ProfileService;

@Controller
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String showProfile(Authentication authentication, Model model) {
        AppUser user = profileService.getLoggedInUser(authentication);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/email-data")
    public String emailUserData(Authentication authentication) {
        profileService.sendUserDataByEmail(authentication);
        return "redirect:/profile?sent";
    }

    @PostMapping("/profile/delete")
    public String deleteAccount(Authentication authentication,
                                HttpServletRequest request,
                                HttpServletResponse response) {

        profileService.deleteLoggedInUser(authentication);

        new SecurityContextLogoutHandler().logout(request, response, authentication);

        return "redirect:/?deleted";
    }
}