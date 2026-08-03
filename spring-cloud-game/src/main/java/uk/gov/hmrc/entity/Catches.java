package uk.gov.hmrc.entity;

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

    
    public Catches() {
    }

    public Integer getCatchId() {
        return catchId;
    }

    public void setCatchId(Integer catchId) {
        this.catchId = catchId;
    }

}
