package uk.gov.hmrc.demoapp.model;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "rabbit_messages")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class RabbitMessage {

    @Id
    @Column
    private Long id;

    @Column
    private String message;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;
}
