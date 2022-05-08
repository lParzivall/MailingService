package ru.parshin.mailing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.parshin.mailing.domain.Mailing;
import ru.parshin.mailing.repository.MailingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MailingService {

    private final MailingRepository mailingRepository;

    public Mailing saveMailing(Mailing mailing) {
        return mailingRepository.save(mailing);
    }

    public List<Mailing> getAllMailings() {
        log.info("Fetching all mailings");
        return mailingRepository.findAll();
    }
}
