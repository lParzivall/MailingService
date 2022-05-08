package ru.parshin.mailing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.parshin.mailing.domain.Message;
import ru.parshin.mailing.repository.MessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        log.info("Fetching all messages");
        return messageRepository.findAll();
    }
}
