package ru.parshin.mailing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.parshin.mailing.domain.Mailing;

public interface MailingRepository extends JpaRepository<Mailing, Long> {

}
