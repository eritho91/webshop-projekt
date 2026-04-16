package se.iths.erikthorell.webshopprojekt.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class MagicLinkOneTimeTokenGenerationSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

    private final JavaMailSender mailSender;

    private final OneTimeTokenGenerationSuccessHandler redirectHandler =
            new RedirectOneTimeTokenGenerationSuccessHandler("/ott/sent");

    public MagicLinkOneTimeTokenGenerationSuccessHandler(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       OneTimeToken oneTimeToken) throws IOException, ServletException {

        String magicLink = UriComponentsBuilder.fromUriString(UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .fragment(null)
                .path("/login/ott")
                .queryParam("token", oneTimeToken.getTokenValue())
                .toUriString();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(oneTimeToken.getUsername());
        message.setSubject("Din inloggningslänk");
        message.setText("Klicka på länken för att slutföra inloggningen:\n" + magicLink);

        mailSender.send(message);

        redirectHandler.handle(request, response, oneTimeToken);
    }
}