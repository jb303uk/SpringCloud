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
	private Integer ivsAttack;
	private Integer ivsDefense;
	private Integer ivsStamina;
    @Column(name = "USERUUID")
    private String USERUUID;
    
    public Catches() {}

    public Integer getCatchId() { return catchId; }
    public Integer getSpeciesId() { return species_id; }
    public Boolean getIsShiny() { return is_shiny; }
    public Integer getIVsAttack() { return ivsAttack; }      
    public Integer getIVsdefense() { return ivsDefense; }
    public Integer getIVsstamina() { return ivsStamina; }
    public String getUSERUUID() { return USERUUID; }
    @PrePersist
    public void prePersist() {
    	chooseSpecies();
    	chooseIsShiny();
    	ivsAttack();
    	ivsDefense();
    	ivsStamina();
    }
    
    @Column(name = "species_id", nullable = false, updatable = false)
    protected void chooseSpecies() {
        if (this.species_id == null) {
            this.species_id = ThreadLocalRandom.current().nextInt(0, 152);
        }
    }
    
    @Column(name = "is_shiny", nullable = false, updatable = false)
    protected void chooseIsShiny() {
        if (this.is_shiny == null) {
            this.is_shiny = (ThreadLocalRandom.current().nextInt(0, 512) == 0);
        }
    }    

    @Column(name = "ivs_attack", nullable = false, updatable = false)
    void ivsAttack() {
        if (this.ivsAttack == null) {
            this.ivsAttack = ThreadLocalRandom.current().nextInt(0,16);
        }
    }

    @Column(name = "ivs_defense", nullable = false, updatable = false)
    void ivsDefense() {
        if (this.ivsDefense == null) {
            this.ivsDefense = ThreadLocalRandom.current().nextInt(0, 16);
        }
    }    

    @Column(name = "ivs_stamina", nullable = false, updatable = false)
    void ivsStamina() {
        if (this.ivsStamina == null) {
            this.ivsStamina = ThreadLocalRandom.current().nextInt(0, 16);
        }
    }
}
