package se.iths.erikthorell.webshopprojekt.service;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.ott.OneTimeToken;
import se.iths.erikthorell.springmessenger.model.Email;
import se.iths.erikthorell.springmessenger.service.MessageService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MagicLinkMockTest {

    @Mock
    private MessageService messageService;

    @Mock
    private OneTimeToken oneTimeToken;

    private MagicLinkOneTimeTokenGenerationSuccessHandler handler;

    @BeforeEach
    void setUp() {
        handler = new MagicLinkOneTimeTokenGenerationSuccessHandler(messageService);
    }

    @Test
    void handle_shouldSendEmailWithMagicLink() throws ServletException, IOException {
        when(oneTimeToken.getUsername()).thenReturn("erik@test.se");
        when(oneTimeToken.getTokenValue()).thenReturn("abc123");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("https");
        request.setServerName("example.com");
        request.setServerPort(443);
        request.setRequestURI("/ott/generate");

        MockHttpServletResponse response = new MockHttpServletResponse();

        handler.handle(request, response, oneTimeToken);

        ArgumentCaptor<Email> emailCaptor = ArgumentCaptor.forClass(Email.class);
        verify(messageService).send(emailCaptor.capture());

        Email email = emailCaptor.getValue();

        assertEquals("erik@test.se", email.getRecipient());
        assertEquals("Din inloggningslänk", email.getSubject());
        assertTrue(email.getMessage().contains("Klicka på länken för att slutföra inloggningen:"));
        assertTrue(email.getMessage().contains("https://example.com/login/ott?token=abc123"));
    }

    @Test
    void handle_shouldRedirectToOttSentPage() throws ServletException, IOException {
        when(oneTimeToken.getUsername()).thenReturn("erik@test.se");
        when(oneTimeToken.getTokenValue()).thenReturn("abc123");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("https");
        request.setServerName("example.com");
        request.setServerPort(443);
        request.setRequestURI("/ott/generate");

        MockHttpServletResponse response = new MockHttpServletResponse();

        handler.handle(request, response, oneTimeToken);

        assertEquals("/ott/sent", response.getRedirectedUrl());
    }
}