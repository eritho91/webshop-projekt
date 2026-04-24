package se.iths.erikthorell.webshopprojekt.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import se.iths.erikthorell.springmessenger.model.Email;
import se.iths.erikthorell.springmessenger.service.MessageService;

import java.io.IOException;

@Component
public class MagicLinkOneTimeTokenGenerationSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

    private final MessageService messageService;

    private final OneTimeTokenGenerationSuccessHandler redirectHandler =
            new RedirectOneTimeTokenGenerationSuccessHandler("/ott/sent");

    public MagicLinkOneTimeTokenGenerationSuccessHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       OneTimeToken oneTimeToken) throws IOException, ServletException {

        System.out.println("MAGIC LINK HANDLER KÖRS");
        System.out.println("TOKEN USERNAME = " + oneTimeToken.getUsername());

        String magicLink = UriComponentsBuilder.fromUriString(UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .fragment(null)
                .path("/login/ott")
                .queryParam("token", oneTimeToken.getTokenValue())
                .toUriString();

        System.out.println("MAGIC LINK:");
        System.out.println(magicLink);

        Email email = new Email();
        email.setRecipient(oneTimeToken.getUsername());
        email.setSubject("Din inloggningslänk");
        email.setMessage("Klicka på länken för att slutföra inloggningen:\n" + magicLink);

        messageService.send(email);

        redirectHandler.handle(request, response, oneTimeToken);
    }
}