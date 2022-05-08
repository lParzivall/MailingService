package ru.parshin.mailing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.parshin.mailing.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("" +
            "SELECT CASE WHEN COUNT(u) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Client u " +
            "WHERE u.phoneNumber = ?1"
    )
    Boolean selectExistsPhoneNumber(String phoneNumber);
}
