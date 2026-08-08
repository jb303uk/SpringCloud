package uk.gov.hmrc.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "CATCHES_VIEW") // The name of your database view
public class CatchSummary {

    @Id
    private Integer catchId; // Must map to a column with unique values in your view
    
    private String pokemonName;
	private Integer speciesId;
	private Boolean isShiny;
	private Integer ivsAttack;
	private Integer ivsDefense;
	private Integer ivsStamina;
	private Integer ivsPercent;

	public String getPokemonName() { return pokemonName; }
    public Integer getCatchId() { return catchId; }
    public Integer getSpeciesId() { return speciesId; }
    public Boolean getIsShiny() { return isShiny; }
    public Integer getIVsAttack() { return ivsAttack; }      
    public Integer getIVsdefense() { return ivsDefense; }
    public Integer getIVsstamina() { return ivsStamina; }
    public Integer getIVsPercent() { return ivsPercent; }
    
}
