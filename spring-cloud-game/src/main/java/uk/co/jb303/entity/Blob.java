package uk.co.jb303.entity;

import jakarta.persistence.*;

import java.util.Base64;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "BLOB") // The name of your database view
public class Blob {

    @Id
    private Integer Id; // Must map to a column with unique values in your view
    @Lob
    private byte[] blob;
    
    public Integer getIdentifier() { return getIdentifier(); }
    public Boolean getIsShiny() { return getIsShiny(); }

    
    @Transient
    public String getImageBase64() {
        if (this.blob == null || this.blob.length == 0) {
            return null;
        }
        String base64Data = Base64.getEncoder().encodeToString(this.blob);
        return "data:image/png;base64," + base64Data;
    }
    
}
