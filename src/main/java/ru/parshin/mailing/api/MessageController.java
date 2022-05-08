package ru.parshin.mailing.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.parshin.mailing.domain.Message;
import ru.parshin.mailing.service.MessageService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok().body(messageService.getAllMessages());
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/messages").toUriString());
        return ResponseEntity.created(uri).body(messageService.saveMessage(message));
    }
}
