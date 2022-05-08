package ru.parshin.mailing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.parshin.mailing.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
