package ru.evilsnow.otususers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    private static final String OK_STATIC_RESPONSE = "{\"status\": \"OK\"}";

    @GetMapping
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok(OK_STATIC_RESPONSE);
    }

}
