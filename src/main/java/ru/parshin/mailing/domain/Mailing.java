package ru.parshin.mailing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mailing {
    @Id
    @SequenceGenerator(
            name = "mailing_sequence",
            sequenceName = "mailing_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mailing_sequence"
    )
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String text;
    private String code;
}
