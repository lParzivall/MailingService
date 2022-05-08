package ru.parshin.mailing.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.parshin.mailing.domain.Mailing;
import ru.parshin.mailing.service.MailingService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MailingController {

    private final MailingService mailingService;

    @GetMapping("/mailings")
    public ResponseEntity<List<Mailing>> getMailings() {
        return ResponseEntity.ok().body(mailingService.getAllMailings());
    }

    @PostMapping("/mailings")
    public ResponseEntity<Mailing> addMailing(@RequestBody Mailing mailing) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/mailings").toUriString());
        return ResponseEntity.created(uri).body(mailingService.saveMailing(mailing));
    }



}