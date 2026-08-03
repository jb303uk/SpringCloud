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
	private Boolean is_shiny;

    
    public Catches() {
    }

    public Integer getCatchId() {
        return catchId;
    }

    public Integer getSpeciesId() {
        return species_id;
    }

    public Boolean getIsShiny() {
        return is_shiny;
    }
       
    @PrePersist
    @Column(name = "species_id", nullable = false, updatable = false)
    protected void chooseSpecies() {
        if (this.species_id == null) {
            // Generates a random number from 1 to 512 inclusive
            this.species_id = ThreadLocalRandom.current().nextInt(1, 513);
        }
    }
    @Column(name = "is_shiny", nullable = false, updatable = false)
    protected void chooseIsShiny() {
        if (this.is_shiny == null) {
            // Generates a random number from 1 to 512 inclusive
            this.is_shiny = (ThreadLocalRandom.current().nextInt(1, 2) == 1);
        }
    }


}
