package ru.parshin.mailing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.parshin.mailing.domain.Client;
import ru.parshin.mailing.repository.ClientRepository;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;

    public Client saveClient(Client client) {
        Boolean existsPhoneNumber = clientRepository
                .selectExistsPhoneNumber(client.getPhoneNumber());
        if (existsPhoneNumber) {
            log.info("phone number {} already taken", client.getPhoneNumber());
            throw new IllegalStateException("phone number " + client.getPhoneNumber() + " already taken");
        }
        boolean isValidNumber = Pattern.compile("^7\\d{10}$")
                .matcher(client.getPhoneNumber())
                .matches();
        if (!isValidNumber) {
            log.info("Can't saving new client to the database. Not valid phone number {}", client.getPhoneNumber());
            throw new IllegalStateException("phone number not valid");
        }
        log.info("Saving new client {} to the database", client);
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        log.info("Fetching all clients");
        return clientRepository.findAll();
    }

    public void deleteClient(Long clientId) {
        boolean exists = clientRepository.existsById(clientId);
        if (!exists) {
            throw new IllegalStateException(
                    "client with id " + clientId + " does not exists");
        }
        clientRepository.deleteById(clientId);
    }

    public void updateClient(Long clientId,
                             String code,
                             String tag,
                             String timezone,
                             String phoneNumber) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalStateException(
                        "client with id " + clientId + " does not exists"
                ));
        if (code != null &&
                code.length() > 0 &&
                !Objects.equals(client.getCode(), code)) {
            client.setCode(code);
        }
        if (tag != null &&
                tag.length() > 0 &&
                !Objects.equals(client.getTag(), tag)) {
            client.setTag(tag);
        }
        if (timezone != null &&
                timezone.length() > 0 &&
                !Objects.equals(client.getTimezone(), timezone)) {
            client.setTimezone(timezone);
        }
        if (phoneNumber != null &&
                phoneNumber.length() == 11 &&
                !Objects.equals(client.getPhoneNumber(), phoneNumber)) {
            boolean isValidNumber = Pattern.compile("^7\\d{10}$")
                    .matcher(phoneNumber)
                    .matches();
            if (!isValidNumber) {
                log.info("Can't update client. Not valid phone number {}", client.getPhoneNumber());
                throw new IllegalStateException("phone number not valid");
            }
            client.setPhoneNumber(phoneNumber);
        }
        log.info("Client {} updated in the database", client);
    }
}
