package uk.co.jb303.domain;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

@Serdeable
@Entity
@Table(name = "EMPLOYEES")
public record Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer employeeId,
    String firstName,
    String lastName,
    String email
) {}