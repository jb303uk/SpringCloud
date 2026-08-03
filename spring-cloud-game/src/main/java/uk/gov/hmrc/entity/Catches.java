package uk.gov.hmrc.entity;

import java.util.concurrent.ThreadLocalRandom;

import jakarta.persistence.*;

@Entity
@Table(name = "catches")
public class Catches {

    @Id
    @Column(name = "catch_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catch_seq")
    @SequenceGenerator(
            name = "catch_seq", 
            sequenceName = "CATCHES_SEQ", 
            allocationSize = 1
        )
    private Integer catchId;
	private Integer species_id;

    
    public Catches() {
    }

    public Integer getCatchId() {
        return catchId;
    }

    public Integer getSpeciesId() {
        return species_id;
    }
    
    @PrePersist
    @Column(name = "species_id", nullable = false, updatable = false)

    protected void onCreate() {
        if (this.species_id == null) {
            // Generates a random number from 1 to 512 inclusive
            this.species_id = ThreadLocalRandom.current().nextInt(1, 513);
        }
    }

}
