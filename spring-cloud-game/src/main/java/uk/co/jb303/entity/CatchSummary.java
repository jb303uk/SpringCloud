package uk.co.jb303.entity;

import jakarta.persistence.*;

import java.util.Base64;

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
	private String shinyIcon;
	private String userUUID;
//    @Lob
//    private byte[] blob;
    
	public String getPokemonName() { return pokemonName; }
    public Integer getCatchId() { return catchId; }
    public Integer getSpeciesId() { return speciesId; }
    public Boolean getIsShiny() { return isShiny; }
    public Integer getIVsAttack() { return ivsAttack; }      
    public Integer getIVsdefense() { return ivsDefense; }
    public Integer getIVsstamina() { return ivsStamina; }
    public Integer getIVsPercent() { return ivsPercent; }
    public String getShinyIcon() { return shinyIcon; }
    public String getuserUUID() { return userUUID; }
    
//    @Transient
//    public String getImageBase64() {
//        if (this.blob == null || this.blob.length == 0) {
//            return null;
//        }
//        String base64Data = Base64.getEncoder().encodeToString(this.blob);
//        return "data:image/png;base64," + base64Data;
//    }
    
}
