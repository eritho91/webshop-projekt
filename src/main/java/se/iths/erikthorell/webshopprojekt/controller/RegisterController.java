package se.iths.erikthorell.webshopprojekt.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.erikthorell.webshopprojekt.model.AppUser;
import se.iths.erikthorell.webshopprojekt.service.AppUserService;

@Controller
public class RegisterController {

    private final AppUserService appUserService;

    public RegisterController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("appUser") AppUser appUser,
                               BindingResult bindingResult) {

        if (!appUser.isConsent()) {
            bindingResult.rejectValue("consent", "error.appUser", "Du måste godkänna integritetspolicyn.");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            appUserService.registerUser(appUser);
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("email", "error.appUser", e.getMessage());
            return "register";
        }

        return "redirect:/login";
    }
}