package ru.parshin.mailing.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.parshin.mailing.domain.Client;
import ru.parshin.mailing.service.ClientService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getMailings() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/clients").toUriString());
        return ResponseEntity.created(uri).body(clientService.saveClient(client));
    }

    @DeleteMapping( "/clients/{clientId}")
    public void deleteClient(
            @PathVariable("clientId") Long clientId) {
        clientService.deleteClient(clientId);
    }

    @PutMapping( "/clients/{clientId}")
    public void updateAppUser(
            @PathVariable("clientId") Long clientId,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String timezone,
            @RequestParam(required = false) String phoneNumber) {
        clientService.updateClient(clientId, code, tag, timezone, phoneNumber);
    }
}
