package uk.gov.hmrc.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "CATCHES_VIEW") // The name of your database view
public class CatchSummary {

    @Id
    private Integer catchId; // Must map to a column with unique values in your view
    
    private String pokemon_name;
	private Integer species_id;
	private Boolean is_shiny;
	private Integer ivsAttack;
	private Integer ivsDefense;
	private Integer ivsStamina;

	public String getPokemonName() { return pokemon_name; }
    public Integer getCatchId() { return catchId; }
    public Integer getSpeciesId() { return species_id; }
    public Boolean getIsShiny() { return is_shiny; }
    public Integer getIVsAttack() { return ivsAttack; }      
    public Integer getIVsdefense() { return ivsDefense; }
    public Integer getIVsstamina() { return ivsStamina; }
    
}
