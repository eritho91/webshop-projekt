package se.iths.erikthorell.webshopprojekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "se.iths.erikthorell")
public class WebshopProjektApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebshopProjektApplication.class, args);
    }
}